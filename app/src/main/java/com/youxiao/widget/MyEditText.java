package com.youxiao.widget;

import android.content.Context;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.widget.EditText;

public class MyEditText extends EditText {
	private final String TAG="EditTextView";
	private Drawable dRight;
	private Rect rBounds;
	
	public MyEditText(Context paramContext){
		super(paramContext);
		initEditText();
	}
	
	public MyEditText(Context paramContext,AttributeSet paramAttributeSet){
		super(paramContext,paramAttributeSet);
		initEditText();
	}
	
	public MyEditText(Context paramContext,AttributeSet paramAttributeSet,int paramInt){
		super(paramContext,paramAttributeSet,paramInt);
		initEditText();
	}
	
	private void initEditText() {
		setEditTextDrawable();
		addTextChangedListener(new TextWatcher() {

			public void onTextChanged(CharSequence paramCharSequence, int paramInt1, int paramInt2, int paramInt3) {
				MyEditText.this.setEditTextDrawable();
			}
			
		
			public void beforeTextChanged(CharSequence paramCharSequence, int paramInt1, int paramInt2,
					int paramInt3) {
				
			}
			
		
			public void afterTextChanged(Editable paramEditable) {
				
				
			}
		});
	}

	private void setEditTextDrawable() {
		if(getText().toString().length()==0){
			setCompoundDrawables(null, null, null, null);
		}else{
			setCompoundDrawables(null, null, this.dRight, null);
		}		
	}
	protected void onDetachedFromWindow(){
		super.onDetachedFromWindow();
		this.dRight=null;
		this.rBounds=null;
	}
	public boolean onTouchEvent(MotionEvent paramMotionEvent){
		if((this.dRight!=null)&&(paramMotionEvent.getAction()==1)){
			this.rBounds=this.dRight.getBounds();
			int i=(int)paramMotionEvent.getRawX();
			if(i>getRight()-3*this.rBounds.width()){
				setText("");
				paramMotionEvent.setAction(MotionEvent.ACTION_CANCEL);
			}
		}
		return super.onTouchEvent(paramMotionEvent);
	}
	public void setCompoundDrawables(Drawable paramDrawable1,Drawable paramDrawable2,Drawable paramDrawable3,Drawable paramDrawable4){
		if(paramDrawable3!=null){
			this.dRight=paramDrawable3;
		}
		super.setCompoundDrawables(paramDrawable1,paramDrawable2, paramDrawable3, paramDrawable4);
	}
}
