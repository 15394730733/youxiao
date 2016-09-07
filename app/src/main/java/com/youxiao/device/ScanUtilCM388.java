package com.youxiao.device;

import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;

import android.os.Handler;
import android.os.Message;
import android.util.Log;

public class ScanUtilCM388 {
	private SerialPortCM388 serialPort;
	GetScanDate getScanDate;
	private InputStream dataInput;
	private OutputStream dataOutput;
	private FileWriter cmdOutput;
	private boolean mRestoreGpioStateHandled = false;
	public ScanUtilCM388(GetScanDate getScanDate) {
		this.getScanDate = getScanDate;
	
	}
	
	Handler hanldler = new Handler(){
		public void handleMessage(Message msg) {
			getScanDate.GetDate(msg.obj.toString());
		}
	};
	/**
	 * onResume方法中调用
	 */
	public void onResume() {
		try {
			serialPort = new RXTXPort("/dev/ttyMSM0");
			dataInput = serialPort.getInputStream();
			dataOutput = serialPort.getOutputStream();
			cmdOutput = new FileWriter("/dev/scan");
			
			serialPort.addEventListener(new SerialPortEventListener() {
				
				public void serialEvent(SerialPortEvent ev) {
					try {
						int type = ev.getEventType();
						if (type == SerialPortEvent.DATA_AVAILABLE) {
							byte[] readBuffer = new byte[512];
							int numBytes;
							String sReadBuff = "";
							
							while (dataInput.available() > 0) {
								numBytes = dataInput.read(readBuffer);
								String tmpR = new String(readBuffer, 0, numBytes,"UTF-8");
								sReadBuff += tmpR;
							}
							Log.e("sReadBuff", sReadBuff);
							Message msg = new Message();
							msg.obj = sReadBuff;
							hanldler.sendMessage(msg);
							
							if (!mRestoreGpioStateHandled) {
								hanldler.removeCallbacks(mRestoreGpioState);
								setScanGpioState(false);
							}
						}	
						
					} catch (Exception e) {
						e.printStackTrace();
					}
				}
			});
			serialPort.notifyOnDataAvailable(true);
			serialPort.setSerialPortParams(9600, SerialPortCM388.DATABITS_8,SerialPortCM388.STOPBITS_1, SerialPortCM388.PARITY_NONE);
			serialPort.setFlowControlMode(SerialPortCM388.FLOWCONTROL_NONE);

		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	/**
	 * 在onPause方法中调用
	 */
	public void onPause(){
		try {
			if (dataInput != null) {
				try {
					dataInput.close();
				} catch (IOException e2) {
					e2.printStackTrace();
				}
				dataInput = null;
			}
			if (dataOutput != null) {
				try {
					dataOutput.close();
				} catch (IOException e3) {
					e3.printStackTrace();
				}
				dataOutput = null;
			}

			if (cmdOutput != null) {
				try {
					cmdOutput.close();
				} catch (IOException e4) {
					e4.printStackTrace();
				}
			}
			if (serialPort != null) {
				serialPort.removeEventListener();
				serialPort.close();
			}
			serialPort = null;
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * 开启条码枪1
	 */
	 public void openScan() {
		
				setScanGpioState(false);
				try {
					Thread.sleep(10);
				} catch (Exception e) {
					e.printStackTrace();
				}
				setScanGpioState(true);
				mRestoreGpioStateHandled = false;
				hanldler.postDelayed(mRestoreGpioState, 3000);
			
			if (dataOutput == null) {
				return;
			}
			final byte[] scanByte = new byte[] { (byte)0x04,(byte)0xe4,(byte)0x04,(byte)0x00,(byte)0xff,(byte)0x14 };
			try {
				dataInput.reset();
				dataOutput.write(scanByte);
				dataOutput.flush();
			} catch (UnsupportedEncodingException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	
	/**
	 * 开关灯状态
	 * @param state
	 */
	
	private void setScanGpioState(boolean state) {
		if (cmdOutput != null) {
			try {
				cmdOutput.write(state ? '0' : '1');
				cmdOutput.flush();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
	public interface GetScanDate{
		public void GetDate(String data);
	}
	
	private final Runnable mRestoreGpioState = new Runnable() {
		public void run() {
			if (!mRestoreGpioStateHandled) {
				mRestoreGpioStateHandled = true;
				setScanGpioState(false);
			}
		}
	};
}
