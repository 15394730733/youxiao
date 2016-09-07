package com.youxiao.device;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

import android.os.Handler;
import android.os.Message;
import android.util.Log;

/**
 * 汉德霍尔H901--扫码枪
 * 
 * @author Administrator
 * 
 */
public class ScanUtilsHandheld {

	public static final int SCAN = 1001;
	private SerialPortHandheld mSerialPort;
	private InputStream is;
	private OutputStream os;
	private int port = 0;
	private int baudrate = 9600;
	private int flags = 0;
	GetScanDate getScanDate;
	private Thread scanThread;

	public ScanUtilsHandheld(GetScanDate getScanDate) throws SecurityException,
			IOException {
		this.getScanDate = getScanDate;
		mSerialPort = new SerialPortHandheld(port, baudrate, flags);
		mSerialPort.scaner_poweron();
		is = mSerialPort.getInputStream();
		os = mSerialPort.getOutputStream();
		try {
			Thread.sleep(500);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		/** clear useless data **/
		byte[] temp = new byte[128];
		scanThread = new Thread(new scanrunnable(handler, is));
		scanThread.start();
		// is.read(temp);
	}

	public void interrupt() {
		scanThread.interrupt();
	}

	Handler handler = new Handler() {
		public void handleMessage(Message msg) {
			try {
				Log.e("msg", "" + msg.what);
				Log.e("msg", msg.obj.toString());
				// GetDate(msg.obj.toString());
				getScanDate.GetDate(msg.obj.toString());

			} catch (Exception e) {
				e.printStackTrace();
			}

		};
	};

	class scanrunnable implements Runnable {

		Handler handler;
		InputStream is;

		scanrunnable(Handler handler, InputStream is) {
			this.handler = handler;
			this.is = is;
		}

		public void run() {
			// TODO Auto-generated method stub

			try {
				int size = 0;
				byte[] buffer = new byte[2048];
				int available = 0;
				while (!isInterrupted()) {
					available = is.available();
					if (available > 0) {
						size = is.read(buffer);
						if (size > 0) {
							String dataStr = new String(buffer, 0, size);
							// Bundle bundle = new Bundle();
							// bundle.putString("data", dataStr);
							Message msg = new Message();
							msg.obj = dataStr;
							// msg.setData(bundle);
							handler.sendMessage(msg);
							// handler.post(r);
							// getScanDate.GetDate(dataStr);
						}
					}
				}
			} catch (IOException e) {
				// ���ش�����Ϣ
				e.printStackTrace();
			}

			// handler.sendMessage(msg)

		}

		private boolean isInterrupted() {
			// TODO Auto-generated method stub
			return Thread.currentThread().isInterrupted();
		}

	}

	/*
	 * Runnable scanrunnable = new Runnable() {
	 * 
	 * @Override public void run() {
	 * 
	 * try { int size = 0; byte[] buffer = new byte[2048]; int available = 0;
	 * while(!isInterrupted()){ available = is.available(); if(available > 0){
	 * size = is.read(buffer); if(size > 0){ String dataStr = new String(buffer,
	 * 0 , size); // Bundle bundle = new Bundle(); // bundle.putString("data",
	 * dataStr); Message msg = new Message(); // msg.setData(bundle); //
	 * m_Handler.sendMessage(msg); getScanDate.GetDate(dataStr); } } } } catch
	 * (IOException e) { //���ش�����Ϣ e.printStackTrace(); }
	 * 
	 * 
	 * 
	 * }
	 * 
	 * private boolean isInterrupted() { // TODO Auto-generated method stub
	 * return Thread.currentThread().isInterrupted(); } };
	 */

	public void scan() {
		if (mSerialPort.scaner_trig_stat() == true) {
			mSerialPort.scaner_trigoff();
			try {
				Thread.sleep(50);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		mSerialPort.scaner_trigon();
	}

	public void close() {
		if (mSerialPort != null) {
			mSerialPort.scaner_poweroff();
			try {
				if (is != null) {
					is.close();
				}
				if (os != null) {
					os.close();
				}
			} catch (IOException e) {
				e.printStackTrace();
			}
			mSerialPort.close(port);
		}
	}

	public interface GetScanDate {
		public void GetDate(String data);
	}

}
