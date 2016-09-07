package com.youxiao.blueService;

import android.app.Activity;
import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothDevice;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.youxiao.R;
import com.youxiao.ui.activity.BlueService;

import java.util.Set;

/**
 * 本次活动将出现一个对话框。它列出了后发现在该地区的任何配对的设备，
 * 检测设备。 由用户选择的，
 * 当一个设备被发送回设备的MAC地址到父活动的结果意图。
 * Created by 张小布 on 2016/5/11.
 */
public class DeviceListActivity extends Activity{
    public static final int MESSAGE_STATE_CHANGE = 1;//改变消息状态
    public static final int MESSAGE_READ = 2;//读消息
    public static final int MESSAGE_WRITE = 3;//写消息
    public static final int MESSAGE_DEVICE_NAME = 4;//信息设备名称
    public static final int MESSAGE_TOAST = 5;//消息吐司

    private static final int REQUEST_CONNECT_DEVICE = 1;//请求连接装置
    private static final int REQUEST_ENABLE_BT = 2;//请求启用蓝牙(BT)
    //所连接的设备的名称
    public static String mConnectedDeviceName;
    public static final String TOAST = "toast";
    public static Intent service;
    //将收到的蓝夜服务处理程序的按键名称
    public static final String DEVICE_NAME = "device_name";//设备名称
    //调试
    private static final String TAG = "DeviceListActivity";
    private static final boolean D = true;
    //额外的设备地址
    public static String EXTRA_DEVICE_ADDRESS = "device_address";
    private static Context context;
    //成员字段
    private BluetoothAdapter mBTAdapter;//代表本地的蓝牙连接器(蓝牙无线)
    private ArrayAdapter<String> mPairedDevicesArrayAdapter;// 配对设备阵列适配器
    private ArrayAdapter<String> mNewDevicesArrayAdapter;// 新设备阵列适配器
    public static TextView mTextView;
    public static SharedPreferences deviceName;
    public static SharedPreferences.Editor editor;
    //广播接收器，监听发现的设备和变化的标题，当发现完成
    private final BroadcastReceiver mReceiver = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            String action = intent.getAction();
            //如果发现设备
            if (BluetoothDevice.ACTION_FOUND.equals(action)){
                //获取蓝牙设备对象的意图
                BluetoothDevice device = intent.getParcelableExtra(BluetoothDevice.EXTRA_DEVICE);
                //如果已经配对，跳过它，因为它已经发布
                if (device.getBondState() != BluetoothDevice.BOND_BONDED){
                    mNewDevicesArrayAdapter.add(device.getName() + "\n" + device.getAddress());
                }
                //如果发现后，更改活动标题，行动发现完成
            }else if (BluetoothAdapter.ACTION_DISCOVERY_FINISHED.equals(action)){
                setProgressBarIndeterminateVisibility(false);
                setTitle(R.string.select_device);//选择连接的设备
            }
        }
    };

    public static final Handler mHandler = new Handler(){
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            switch (msg.what){
                case MESSAGE_STATE_CHANGE://改变消息状态
                    switch (msg.arg1){
                        case BluetoothService.STATE_CONNECTED:// 连接状态
                            mTextView.setText(R.string.title_connected_to);//已连接
                            mTextView.append(mConnectedDeviceName);//所连接的设备名称
                            // Toast.makeText(context, "连接成功",
                            // Toast.LENGTH_LONG).show();
                            break;
                        case BluetoothService.STATE_CONNECTING:
                            mTextView.setText(R.string.title_connecting);//正在连接…
                            break;
                        case BluetoothService.STATE_LISTEN:// 监听状态
                        case BluetoothService.STATE_NONE:
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
                    mConnectedDeviceName = msg.getData().getString(DEVICE_NAME);
                    Toast.makeText(context,	"连接到" + mConnectedDeviceName,
                            Toast.LENGTH_SHORT).show();
                    editor = deviceName.edit();
                    editor.putString("DeviceName", mConnectedDeviceName);
                    editor.commit();
                    break;
                case MESSAGE_TOAST:
                    mTextView.setText(R.string.title_not_connected);
                    Toast.makeText(context,
                            msg.getData().getString(TOAST), Toast.LENGTH_SHORT)
                            .show();
                    editor = deviceName.edit();
                    editor.putString("DeviceName", "");
                    editor.commit();
                    if(BTPrinterDemo.mService!=null)
                        BTPrinterDemo.mService.stop();
                    break;
            }
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        context = this;
        //获取本地蓝牙适配器
        mTextView = (TextView) findViewById(R.id.title_prompt);
        mBTAdapter = BluetoothAdapter.getDefaultAdapter();
    }
    private void init(){
        deviceName = getSharedPreferences("blue",Activity.MODE_PRIVATE);
        setContentView(R.layout.device_list);
        Button scanButton = (Button) findViewById(R.id.button_scan);
        scanButton.setVisibility(View.GONE);
        mPairedDevicesArrayAdapter = new ArrayAdapter<String>(this,R.layout.device_name);
        mNewDevicesArrayAdapter = new ArrayAdapter<String>(this,R.layout.device_name);
        //查找和设置的ListView配对设备
        ListView pairedListView = (ListView) findViewById(R.id.paired_devices);
        pairedListView.setAdapter(mPairedDevicesArrayAdapter);
        //在列表视图中的所有设备的onClick监听器
        pairedListView.setOnItemClickListener(mDeviceClickListener);
        //寻找和设立新发现的设备的ListView
        ListView newDevicesListView = (ListView) findViewById(R.id.new_devices);
        newDevicesListView.setAdapter(mNewDevicesArrayAdapter);
        newDevicesListView.setOnItemClickListener(mDeviceClickListener);
        //注册时，发现设备的广播
        IntentFilter filter = new IntentFilter(BluetoothDevice.ACTION_FOUND);
        //广播接收器，监听发现的设备和变化的标题，当发现完成
        this.registerReceiver(mReceiver,filter);
        //获取一组目前已配对设备
        //调用getBondedDevices()可以找到已经配对的蓝牙设备
        Set<BluetoothDevice> pairedDevices = mBTAdapter.getBondedDevices();
        //如果有配对的设备，每一个阵列适配器
        if (pairedDevices.size() > 0){
            findViewById(R.id.title_paired_devices).setVisibility(View.VISIBLE);
            for (BluetoothDevice device :
                 pairedDevices) {
                mPairedDevicesArrayAdapter.add(device.getName() + "\n" + device.getAddress());
            }
        }else {
            String noDevices = getResources().getText(R.string.none_paired).toString();
            mPairedDevicesArrayAdapter.add(noDevices);
        }
        mTextView = (TextView) findViewById(R.id.title_prompt);
        String nameStr = deviceName.getString("DeviceName","");
        if (!nameStr.equals("")){
            mTextView.setText(R.string.title_connected_to);//已连接
        }else {
            mTextView.setText(R.string.title_not_connected);//没有连接
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (D){
            switch (requestCode){
                case REQUEST_ENABLE_BT:
                    //当请求时，以使蓝牙返回
                    if (resultCode == Activity.RESULT_OK){
                        //蓝牙已启动，以建立一个会话
                        init();
                    }else {
                        //用户没有启用蓝牙或发生错误
                        //蓝牙没有启动 直接退出程序
                        Toast.makeText(this, R.string.bt_not_enabled_leaving, Toast.LENGTH_SHORT).show();
                        this.finish();
                    }
                    break;
            }
        }
    }

    /**
     * 销毁
     */
    @Override
    protected void onDestroy() {
        super.onDestroy();
        //注销广播监听器
        try{
            this.unregisterReceiver(mReceiver);
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    private void doDiscovery(){
        if (D){
            //启动装置发现与蓝牙适配器
            setProgressBarIndeterminateVisibility(true);
            setTitle(R.string.scanning);
            //打开字幕的新设备
            findViewById(R.id.title_new_devices).setVisibility(View.VISIBLE);
            //如果我们一经发现，停止
            if (mBTAdapter.isDiscovering()){
                mBTAdapter.cancelDiscovery();//取消发现
            }
            //请求发现蓝牙适配器
            mBTAdapter.startDiscovery();//开始发现
        }
    }

    /**
     * 在列表视图中的所有设备的OnClick监听器
     */
    private AdapterView.OnItemClickListener mDeviceClickListener = new AdapterView.OnItemClickListener() {
        @Override
        public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
            try {
                //取消发现
                mBTAdapter.cancelDiscovery();
                //获取设备的MAC地址，它是在“视图”在过去的17个字符
                String info = ((TextView)view).getText().toString();
                //没有匹配的设备
                String noDevices = (String) getResources().getText(R.string.none_paired);
                //没有找到设备
                String noNewDevice = (String) getResources().getText(R.string.none_found);
                deviceName = getSharedPreferences("blue", Activity.MODE_PRIVATE);
                String address = info.substring(info.length() - 17);
                editor = deviceName.edit();
                editor.putString("blueaddress", address);
                editor.commit();
                Intent intent = new Intent("blueService");
                intent.putExtra(EXTRA_DEVICE_ADDRESS,address);
                intent.setClass(DeviceListActivity.this, BlueService.class);
                startActivity(intent);

            }catch (Exception e){
                e.printStackTrace();
            }
        }
    };

    @Override
    protected void onStart() {
        super.onStart();
        if (D){
            //如果BT没有启动，请求蓝牙启用
            //setupChat()接着就会在onActivityResult执行
            //调用isEnabled()方法来确认BluetoothAdapter蓝牙通信是否已启动
            if (!mBTAdapter.isEnabled()){
                //请求启用
                Intent enableIntent = new Intent(BluetoothAdapter.ACTION_REQUEST_ENABLE);
                startActivityForResult(enableIntent,REQUEST_ENABLE_BT);//请求启用蓝牙
            }
        }
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case R.id.disconnect:
                //断开
                Intent intent = new Intent("blueService");
                intent.setClass(DeviceListActivity.this,BlueService.class);
                stopService(intent);
                mTextView.setText(R.string.title_not_connected);
                editor = deviceName.edit();
                editor.putString("DeviceName","");
                editor.commit();
                return true;
            case R.id.find:
                doDiscovery();
                return true;
        }
        return false;
    }
}
