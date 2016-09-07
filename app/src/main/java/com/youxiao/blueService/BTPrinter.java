package com.youxiao.blueService;

import android.bluetooth.BluetoothAdapter;

public class BTPrinter {
	private BluetoothService mService;
	// 所连接的设备的名称
		private String mConnectedDeviceName = null;
		// 本地蓝牙适配器
		private BluetoothAdapter mBluetoothAdapter = null;
		
	public BTPrinter(BluetoothService mService) {
		// TODO Auto-generated constructor stub
		this.mService=mService;
		mBluetoothAdapter = BluetoothAdapter.getDefaultAdapter();
	}
}
