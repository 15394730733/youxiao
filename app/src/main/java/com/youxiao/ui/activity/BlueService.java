package com.youxiao.ui.activity;

import android.app.Activity;
import android.app.Service;
import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothDevice;
import android.bluetooth.BluetoothSocket;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Handler;
import android.os.IBinder;
import android.os.Message;
import android.support.annotation.Nullable;
import android.widget.TextView;
import android.widget.Toast;

import com.youxiao.R;
import com.youxiao.blueService.BluetoothService;
import com.youxiao.blueService.DeviceListActivity;

import java.io.OutputStream;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

/**
 *
 * Created by 张小布 on 2016/5/11.
 */
public class BlueService extends Service {

    public static final int MESSAGE_STATE_CHANGE = 1;// 改变消息状态
    public static final int MESSAGE_READ = 2;// 读取消息
    public static final int MESSAGE_WRITE = 3;// 写消息
    public static final int MESSAGE_DEVICE_NAME = 4;// 信息设备名称
    public static final int MESSAGE_TOAST = 5;// 消息吐司

    static SharedPreferences deviceName;
    static SharedPreferences.Editor editor;
    public List<String> message = new ArrayList<String>();
    boolean flag = false;
    TextView mTextView;
    private static final String NAME = "BTPrinter";//蓝牙打印机
    // UUID必须是这样的
    // 此应用程序的唯一UUID
    private static final UUID MY_UUID = UUID.fromString("00001101-0000-1000-8000-00805F9B34FB");
    BluetoothSocket mmSocket;
    OutputStream out;
    String address;

    public static String mConnectedDeviceName;
    private BluetoothAdapter mBluetoothAdapter = null;


    Handler handler = new Handler(){
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            try {
                switch (msg.what) {
                    case MESSAGE_STATE_CHANGE:// 改变消息状态
                        switch (msg.arg1) {
                            case BluetoothService.STATE_CONNECTED:// 连接状态
                                if(mTextView == null){
                                    break;
                                }
                                mTextView.setText(R.string.title_connected_to);//已连接
                                mTextView.append(mConnectedDeviceName);//所连接的设备名称
                                // Toast.makeText(context, "连接成功",
                                // Toast.LENGTH_LONG).show();
                                break;
                            case BluetoothService.STATE_CONNECTING:
                                if(mTextView == null){
                                    break;
                                }
                                mTextView.setText(R.string.title_connecting);//正在连接…
                                break;
                            case BluetoothService.STATE_LISTEN:// 监听状态
                            case BluetoothService.STATE_NONE:
                                if(mTextView == null){
                                    break;
                                }
                                mTextView.setText(R.string.title_not_connected);//没有连接
                                // Toast.makeText(context, "没有连接",
                                // Toast.LENGTH_LONG).show();
                                break;
                        }
                        break;
                    case MESSAGE_WRITE:// 写消息
                        break;
                    case MESSAGE_READ:// 读消息
                        break;
                    case MESSAGE_DEVICE_NAME:
                        // 保存所连接的设备的名称
                        if(message.size() > 0 ){
                            for(String mes : message){
                                //BTPrinterDemo.sendMessages2(mes);
                                BlueService.this.sendMessage(mes);
                            }
                            message.clear();
                        }
                        mConnectedDeviceName = msg.getData().getString("device_name");
                        Toast.makeText(getApplicationContext(), "连接到" + mConnectedDeviceName,
                                Toast.LENGTH_SHORT).show();
                        editor = deviceName.edit();
                        editor.putString("DeviceName", mConnectedDeviceName);
                        editor.commit();
                        //onDestroy();
                        break;
                    case MESSAGE_TOAST:
                        if(mTextView != null){
                            mTextView.setText(R.string.title_not_connected);
                        }

					/*Toast.makeText(getApplicationContext(),
							msg.getData().getString(TOAST), Toast.LENGTH_SHORT)
							.show();*/
                        editor = deviceName.edit();
                        editor.putString("DeviceName", "");
                        editor.commit();
				/*	if(BTPrinterDemo.mService!=null){
						BTPrinterDemo.mService.stop();
						BTPrinterDemo.mService = null;
					}*/
                        //	onDestroy();
                        if(message.size() > 0 ){

                            Intent it = new Intent();
                            it.setClass(BlueService.this, DeviceListActivity.class);
                            it.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                            startActivity(it);
                        }
                        break;
                }
            }catch (Exception e){
                e.printStackTrace();
            }
        }
    };

    @Override
    public void onCreate() {
        super.onCreate();
    }

    @Override
    public void onDestroy() {
        try {
            editor = deviceName.edit();
            editor.putString("DeviceName", "");
            editor.commit();
            if (mmSocket != null){
                mmSocket.close();
            }
            mmSocket = null;
        }catch (Exception e){
            e.printStackTrace();
            mmSocket = null;
        }
        super.onDestroy();
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        try {

            try {
                if (mBluetoothAdapter == null) {
                    mBluetoothAdapter = BluetoothAdapter.getDefaultAdapter();
                }
            } catch (Exception e) {
                e.printStackTrace();
            }

            if (mBluetoothAdapter == null || !mBluetoothAdapter.isEnabled()) {
                mBluetoothAdapter.enable();
            }

            this.mTextView = DeviceListActivity.mTextView;
            if (deviceName == null) {
                deviceName = getSharedPreferences("blue", Activity.MODE_PRIVATE);
            }

            address = null;
            String msg = null;

            if (intent == null) {
                address = deviceName.getString("DeviceAddress", "");
            } else {
                address = intent.getExtras().getString(DeviceListActivity.EXTRA_DEVICE_ADDRESS);
                msg = intent.getExtras().getString("message");
                if (msg != null && !"".equals(msg)) {
                    message.add(msg);
                }
            }

            if (address == null || "00".equals(address)) {
                address = deviceName.getString("blueaddress", "");
            }
            if ("".equals(address)) {
                Intent it = new Intent();
                it.setClass(BlueService.this, DeviceListActivity.class);
                it.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                startActivity(it);
                return super.onStartCommand(intent, flags, startId);
            }
            if (mBluetoothAdapter == null){
                mBluetoothAdapter = BluetoothAdapter.getDefaultAdapter();
            }
            if (BluetoothAdapter.checkBluetoothAddress(address)){
                BluetoothDevice device = mBluetoothAdapter.getRemoteDevice(address);
                connect(device);
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        return super.onStartCommand(intent, flags, startId);
    }

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    public void connect(BluetoothDevice device){
        BluetoothSocket tmp = null;
        if (mmSocket != null){
            Toast.makeText(getApplicationContext(),"已连接设备",Toast.LENGTH_SHORT).show();
            return;
        }
        if (this.mTextView != null){
            mTextView.setText("正在连接...");
        }
        //蓝牙与蓝牙设备的连接套接字
        try {
            tmp = device.createRfcommSocketToServiceRecord(MY_UUID);
            mBluetoothAdapter.cancelDiscovery();
        }catch (Exception e){
            e.printStackTrace();
        }
        mmSocket = tmp;
        try {
            mmSocket.connect();
            out = mmSocket.getOutputStream();
            String strname = device.getName();
            SharedPreferences preferences = getSharedPreferences("user_info", Activity.MODE_PRIVATE);
            String status = preferences.getString("device_switch", "");
            boolean isencryption;
            boolean device_switch;
            if (status.equals("open")){
                device_switch = true;
            }else {
                device_switch = false;
            }
            if (strname.equalsIgnoreCase("XK-JG") || strname.equalsIgnoreCase("XK598")){
                if (device_switch){
                    isencryption = false;
                }else {
                    isencryption = true;
                }
            }else {
                if (device_switch){
                    isencryption = true;
                }else {
                    isencryption = false;
                }
            }

            if (message.size() > 0){
                for (String mes:
                     message) {
                    if (isencryption){
                        BlueService.this.sendMessage2(mes);
                    }else {
                        BlueService.this.sendMessage(mes);
                    }
                }
                message.clear();
            }
            Toast.makeText(getApplicationContext(),"连接到" + device.getName(),Toast.LENGTH_SHORT).show();
            editor = deviceName.edit();
            editor.putString("DeviceName",device.getName());
            editor.commit();
            if (this.mTextView != null){
                mTextView.setText("已连接" + device.getName());
            }
        }catch (Exception e){
            e.printStackTrace();
            try {
                Intent it = new Intent();
                it.setClass(BlueService.this, DeviceListActivity.class);
                it.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                startActivity(it);
                if(this.mTextView != null){
                    mTextView.setText("无连接");
                }
                if(mmSocket != null){
                    mmSocket.close();
                    mmSocket = null;
                }

            } catch (Exception e2) {
                e2.printStackTrace();
            }
        }
    }

    /**
     * 打印非加密
     *
     * @param message
     */
    public void sendMessage(String message){
        try {
            if (out != null){
                if (message.length() > 0){
                    byte[] send;
                    try {
                        byte[] send2 = message.getBytes("GB2312");
                        send = new byte[send2.length + 10];
                        for (int i = 0; i < send2.length; i++){
                            send[i] = send2[i];
                        }
                    }catch (UnsupportedEncodingException e){
                        send = message.getBytes();
                    }
                    if (send.length > 300){
                        int c = send.length / 300;
                        int n = send.length % 300;
                        for (int i = 0; i < c; i++){
                            byte[] b = new byte[300];
                            for (int j = i * 300;j < (i+1)*300;j++){
                                b[j - i * 300] = send[j];
                            }
                            out.write(b);
                        }
                        if (n > 0){
                            byte[] b = new byte[n + 10];
                            for (int i = c * 300; i < send.length; i++){
                                b[i - c * 300] = send[i];
                            }
                            out.write(b);
                        }
                    }else {
                        out.write(send);
                    }
                }
            }
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    /**
     * 打印加密数据
     *
     * @param message
     */
    public void sendMessage2(String message){

        try {
            if(out != null){
                if (message.length() > 0) {
                    byte[] date = getPrintData(message);
                    out.write(date);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

    }
    public byte[] getPrintData(String message){
        try {

            byte[] bufferCommond = message.getBytes("GB2312");


            byte[] addbt = printHexString(address.toLowerCase());

            for(int i = 0;i < bufferCommond.length ;i++){
                bufferCommond[i] ^=addbt[i%12];
            }
            return bufferCommond;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
    public byte[]   printHexString( String address) {

        String[] ss = address.split(":");
        address = "";
        for(String s : ss){
            address += s;
        }

        byte[] b = address.getBytes();
        byte[] bb = new byte[b.length];
        String a = "";
        for (int i = 0; i < b.length; i++) {
            String hex = Integer.toHexString(b[i] & 0xFF);
            if (hex.length() == 1) {
                hex = '0' + hex;
            }
            //Log.e("address", hex);
            bb[i] = Byte.decode("0x" + hex);
        }

        return bb;
    }
}
