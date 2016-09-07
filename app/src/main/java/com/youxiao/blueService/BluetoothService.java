package com.youxiao.blueService;

import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothDevice;
import android.bluetooth.BluetoothServerSocket;
import android.bluetooth.BluetoothSocket;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;

import com.youxiao.R;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.UUID;

/**
 * 这个类做了所有的工作，建立和管理蓝牙与其他设备的连接。它有一个线程监听 传入的连接，
 * 用于与设备连接的一个线程，和 线程连接时，进行数据传输。
 * Created by 张小布 on 2016/5/11.
 */
public class BluetoothService {
    //Debugging调试
    private static final String TAG = "BluetoothService";//蓝牙服务
    private static final boolean D = true;

    //SDP的名字记录在创建服务器套接字
    private static final String NAME = "BTPrinter";//蓝牙打印
    //UUID必须是这样子
    //此应用程序的唯一UUID
    private static final UUID MY_UUID = UUID.fromString("00001101-0000-1000-8000-00805F9B34FB");

    //成员字段
    private BluetoothAdapter mAdapter;//蓝牙适配器
    private Handler mHandler = DeviceListActivity.mHandler;//处理器
    private AcceptThread mAcceptThread;//接受线程
    private ConnectThread mConnectThread;//连接线程
    private ConnectedThread mConnectedThread;//保持线程的连接
    private int mState;//状态
    //蓝牙服务处理程序发送的消息类型
    public static final int MESSAGE_STATE_CHANGE = 1;// 改变消息状态
    public static final int MESSAGE_READ = 2;// 读取消息
    public static final int MESSAGE_WRITE = 3;// 写消息
    public static final int MESSAGE_DEVICE_NAME = 4;// 信息设备名称
    public static final int MESSAGE_TOAST = 5;// 消息吐司
    // 接收到的蓝牙服务处理程序的按键名称
    public static final String DEVICE_NAME = "device_name";// 设备名称
    public static final String TOAST = "toast";
    // 常数,表明当前的连接状态
    public static final int STATE_NONE = 0; // 没有连接
    public static final int STATE_LISTEN = 1; // 现在监听传入的连接
    public static final int STATE_CONNECTING = 2; // 现在启动的一个外向连接
    public static final int STATE_CONNECTED = 3; // 现在连接到远程设备
    static SharedPreferences deviceAddress;
    public Context context;

    /**
     * 构造函数，准备新的打印机会话
     *
     * @param context 上下文
     * @param handler 一个处理程序，将消息发送回的UI活动
     */
    public BluetoothService(Context context, Handler handler){
        mAdapter = BluetoothAdapter.getDefaultAdapter();
        mState = STATE_NONE;
        this.context = context;
    }

    /**
     * 设置的当前的连接状态
     * @param state 定义当前的连接状态
     */
    private synchronized void setState(int state){
        if (D){
            mState = state;
            mHandler.obtainMessage(MESSAGE_STATE_CHANGE,state,-1).sendToTarget();
        }
    }

    /**
     *
     * @return 返回当前的连接状态
     */
    public synchronized int getState(){
        return mState;
    }

    /**
     * 启动服务。具体开始接受线程开始一个会话在听(服务器)模式。
     * 调用的活动onResume()
     */
    public synchronized void start(){
        if (D){
            //取消任何线程试图建立连接
            if (mConnectThread != null){//连接线程
                mConnectThread.cancel();
                mConnectThread = null;
            }
            //取消当前正在运行的所有线程的连接
            if (mConnectedThread != null){//保持线程的连接
                mConnectedThread.cancel();
                mConnectedThread = null;
            }
        }
    }

    /**
     * 启动所连接的线程发起一个连接到远程设备
     * @param device 连接的蓝牙设备
     */
    public synchronized void connect(BluetoothDevice device){
        if (D){
            //取消任何线程试图建立连接
            if (mState == STATE_CONNECTING){
                if (mConnectThread != null){
                    mConnectThread.cancel();
                    mConnectThread = null;
                }
            }
            //取消当前正在运行的所有线程的连接
            if (mConnectedThread != null){
                mConnectedThread.cancel();
                mConnectedThread = null;
            }
            try {
                mConnectThread = new ConnectThread(device);
                mConnectThread.start();
                setState(STATE_CONNECTING);
            }catch (Exception e){
                e.printStackTrace();
            }
        }
    }

    /**
     * 启动所连接的线程开始管理蓝牙连接
     * @param socket 蓝牙套接字的连接
     * @param device 已连接的蓝牙设备
     */
    public synchronized void connected(BluetoothSocket socket, BluetoothDevice device){
        if (D){
            //取消线程完成连接
            if (mConnectThread != null){
                mConnectThread.cancel();
                mConnectThread = null;
            }
            //取消当前正在运行的所有线程的连接
            if (mConnectedThread != null){
                mConnectedThread.cancel();
                mConnectedThread = null;
            }
            //取消接收线程，因为我们只需要连接到一台设备
            if (mAcceptThread != null){
                mAcceptThread.cancel();
                mAcceptThread = null;
            }
            //启动线程来管理连接，并执行传输
            mConnectedThread = new ConnectedThread(socket);
            mConnectedThread.start();
            //发送连接设备的名字回到UI活动
            Message msg = mHandler.obtainMessage(MESSAGE_DEVICE_NAME);
            Bundle bundle = new Bundle();
            bundle.putString(DEVICE_NAME,device.getName());
            msg.setData(bundle);
            mHandler.sendMessage(msg);
            setState(STATE_CONNECTED);
        }

    }

    /**
     * 停止所有线程
     */
    public synchronized void stop(){
        try {
            if (D){
                setState(STATE_NONE);
                if (mConnectThread != null){
                    mConnectThread.cancel();
                    mConnectThread = null;
                }
                if (mConnectedThread != null) {
                    mConnectedThread.cancel();
                    mConnectedThread = null;
                }
                if (mAcceptThread != null) {
                    mAcceptThread.cancel();
                    mAcceptThread = null;
                }
            }
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    /**
     * 写在非同步方式的连接线程
     * @param out 写的字节数
     */
    public void write(byte[] out){
        try {
            //创建临时对象
            ConnectedThread r;
            //同步连接线程的一个副本
            synchronized (this){
                if (mState != STATE_CONNECTED){
                    return;
                }
                r = mConnectedThread;
            }
            //执行写不同步
            r.write(out);
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    /**
     * 表明，该连接请求失败并通知UI活动
     */
    private void connectionFailed(){//连接失败
        setState(STATE_LISTEN);
        //发送失败的消息返回到活动
        Message msg = mHandler.obtainMessage(MESSAGE_TOAST);
        Bundle bundle = new Bundle();
        bundle.putString(TOAST, R.string.cannot_devices+"");
        msg.setData(bundle);
        mHandler.sendMessage(msg);
    }

    /**
     * 表明，中断了连接，并通知用户界面活动
     */
    private void connectionLost(){
        setState(STATE_LISTEN);
        //发送失败的消息返回到活动
        Message msg = mHandler.obtainMessage(MESSAGE_TOAST);
        Bundle bundle = new Bundle();
        bundle.putString(TOAST,R.string.devices_connect_lose+"");
        msg.setData(bundle);
        mHandler.sendMessage(msg);
        stop();//停止所有线程
    }

    /**
     * 这个线程运行在监听传入的连接
     * 它想一个服务器客户端。它运行到一个连接被接受(或者直到取消)
     */
    public class AcceptThread extends Thread{
        //本地蓝牙服务器套接字
        private BluetoothServerSocket mmServerSocket;

        public AcceptThread(){
            BluetoothServerSocket tmp = null;
            //创建一个新的监听服务器套接字
            try {
                tmp = mAdapter.listenUsingInsecureRfcommWithServiceRecord(NAME,MY_UUID);

            }catch (Exception e){
                e.printStackTrace();
            }
            mmServerSocket = tmp;
        }

        public void acceptT(){
            if (D){
                setName("AcceptThread");
                BluetoothSocket socket = null;
                //如果我们不监听服务器套接字
                while (mState != STATE_CONNECTED){
                    try {
                        //这是一个阻塞调用，只会返回上一级
                        //连接成功或异常
                        socket = mmServerSocket.accept();
                    }catch (Exception e){
                        e.printStackTrace();
                        break;
                    }
                    //如果连接被接受
                    if (socket != null){
                        synchronized (BluetoothService.this){
                            switch (mState){
                                case STATE_LISTEN:
                                case STATE_CONNECTING:
                                    //正常情况。启动所连接的线程
                                    connected(socket, socket.getRemoteDevice());
                                    break;
                                case STATE_NONE:
                                case STATE_CONNECTED:
                                    //尚未准备就绪或已连接。终止新的套接字
                                    try {
                                        socket.close();
                                    }catch (IOException e){
                                        Log.e(TAG, "Could not close unwanted socket", e);
                                    }
                            }
                        }
                    }
                }
                if (D){
                    Log.i(TAG, "END mAcceptThread");
                }
            }
        }

        @Override
        public void run() {
            super.run();
            acceptT();
        }

        public void cancel(){
            if (D)
                Log.w(TAG, "cancel " + this);
            try {
                mmServerSocket.close();
            }catch (IOException e){
                Log.e(TAG, "close() of server failed", e);
            }
        }
    }

    /**
     * 这个线程运行，同时试图做一个向外的连接与设备，它运行的直通连接成或失败
     */
    private class ConnectThread extends Thread{

        private final BluetoothDevice mmDevice;
        private BluetoothSocket mmSocket;
        public ConnectThread(BluetoothDevice device){
            mmDevice = device;
            BluetoothSocket tmp = null;
            //蓝牙与蓝牙设备的连接套接字
            try {
                tmp = device.createInsecureRfcommSocketToServiceRecord(MY_UUID);
            }catch (Exception e){
                e.printStackTrace();
            }
            mmSocket = tmp;
        }

        @Override
        public void run() {
            super.run();
            setName("ConnectThread");
            //请务必取消查找 因为它会减慢连接
            mAdapter.cancelDiscovery();
            //建立连接的蓝牙套接字
            try {
                mmSocket.connect();
            }catch (IOException e){
                connectionFailed();
                //关闭套接字
                try {
                    mmSocket.close();

                }catch (IOException e2){
                    e2.printStackTrace();
                    Log.e(TAG, "unable to close() socket during connection failure", e2);
                }
                //启动服务，重新启动监听模式
                BluetoothService.this.start();
                return;
            }
            //重新设置连接
            synchronized (BluetoothService.this){
                mConnectThread = null;
            }
            //启动所连接的线程
            connected(mmSocket,mmDevice);
        }

        /**
         * 取消
         */
        public void cancel(){
            try {
                mmSocket.close();
            }catch (IOException e){
                e.printStackTrace();
                Log.e(TAG, "close() of connect socket failed", e);
            }
        }
    }

    /**
     * 这个线程 运行在连接与远程设备，它在处理所有传入和传出的传输
     */
    private class ConnectedThread extends Thread{
        private BluetoothSocket mmSocket;
        private InputStream mmInStream;//字节输入流
        private OutputStream mmOutStream;//输出流-字节输出流

        public ConnectedThread(BluetoothSocket socket){
            Log.w(TAG, "create ConnectedThread");
            mmSocket = socket;
            InputStream tmpIn = null;
            OutputStream tmpOut = null;
            //蓝牙Socket输入输出流
            try {
                tmpIn = socket.getInputStream();
                tmpOut = socket.getOutputStream();
            }catch (IOException e){
                Log.e(TAG, "temp sockets not created", e);
            }
            mmInStream = tmpIn;
            mmOutStream = tmpOut;
        }

        @Override
        public void run() {
            super.run();
            Log.i(TAG, "BEGIN mConnectedThread");
            int bytes;
            //保留监听的InputStream同时连接
            while (true){
                try {
                    byte[] buffer = new byte[256];
                    //从InputStream中读取
                    bytes = mmInStream.read(buffer);
                    if (bytes > 0) {
                        // 将所获得的字节发送到UI活动
                        mHandler.obtainMessage(MESSAGE_READ,bytes, -1, buffer).sendToTarget();
                    } else {
                        Log.e(TAG, "disconnected1111");
                        connectionLost();
                        // 新增的
                        if (mState != STATE_NONE) {
                            Log.e(TAG, "disconnected222");
                            // 启动服务，重新启动监听模式
                            BluetoothService.this.start();
                        }
                        break;
                    }
                }catch (IOException e){
                    Log.e(TAG, "disconnected", e);
                    connectionLost();
                    if (mState != STATE_NONE) {
                        // 启动服务，重新启动监听模式
                        BluetoothService.this.stop();
                    }
                    break;
                }
            }
        }
        /**
         * 写的OutStream连接.
         *
         * @param buffer
         *            要写入的字节
         */
        public void write(byte[] buffer) {
            try {
                mmOutStream.write(buffer);
                // 分享发送的消息的UI活动
                mHandler.obtainMessage(MESSAGE_WRITE, -1, -1,
                        buffer).sendToTarget();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        public void cancel() {
            try {
                mmSocket.close();
                boolean flag=mmSocket.isConnected();
            } catch (IOException e) {
                Log.e(TAG, "close() of connect socket failed", e);
            }
        }
    }
}
