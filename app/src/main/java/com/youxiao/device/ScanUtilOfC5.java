package com.youxiao.device;

import android.os.Handler;
import android.util.Log;
import android.view.KeyEvent;



public class ScanUtilOfC5 {

	
	GetScanDateOfC5 getScanDateOfC5;
	public ScanUtilOfC5(GetScanDateOfC5 getScanDateOfC5) {
		this.getScanDateOfC5 = getScanDateOfC5;
	}
	
	Handler handler = new Handler(){
		public void handleMessage(android.os.Message msg) {
			try {
				Log.e("msg","" + msg.what);
				if(msg.what == 10){
					Log.e("msg", msg.obj.toString());
					if(getScanDateOfC5 != null){
						getScanDateOfC5.GetDate(msg.obj.toString());
					}
				}

			} catch (Exception e) {
				e.printStackTrace();
			}

		};
	};
	/**
	 * 初始化
	 */
	public void onStart(){
		try {
			Scanner.m_handler = handler;
			Scanner.InitSCA();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * 开启
	 * @param keyCode
	 * @param event
	 */
	public boolean open(int keyCode, KeyEvent event){
		try {
	    		if((keyCode==220))  {
	    			//扫描开始
	    			Log.e("启动", "启动");
	    			Scanner.Read(); 
	    			return false;
	    		}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return true;
	}
	
	public interface GetScanDateOfC5{
		public void GetDate(String data);
	}
}
