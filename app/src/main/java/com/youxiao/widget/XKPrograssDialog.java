package com.youxiao.widget;

import com.youxiao.R;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup.LayoutParams;
import android.widget.TextView;

public class XKPrograssDialog extends Dialog{

	Context context;
	View view;
	public XKPrograssDialog(Context context, int theme) {
		super(context, theme);
		this.context = context; 
		view = LayoutInflater.from(context).inflate(R.layout.dialog_view, null);
	}

	/**
	 * 设置文字
	 * @param message
	 */
	public void setDialogText(String message){
		try {
			TextView dialogText = (TextView) view.findViewById(R.id.showtext);
			dialogText.setText(message);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		
		this.addContentView(view, new LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT));
		this.setCancelable(false);
	}
}
