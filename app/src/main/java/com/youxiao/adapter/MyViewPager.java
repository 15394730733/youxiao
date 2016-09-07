package com.youxiao.adapter;

import android.content.Context;
import android.support.v4.view.ViewPager;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.WindowManager;

public class MyViewPager extends ViewPager {

	public MyViewPager(Context context, AttributeSet attrs) {
		super(context, attrs);
		// TODO Auto-generated constructor stub
	}

	public MyViewPager(Context context) {
		super(context);
		// TODO Auto-generated constructor stub
	}

	@Override
	public boolean onTouchEvent(MotionEvent arg0) {
		// TODO Auto-generated method stub
		// super.onTouchEvent(arg0)
		return super.onTouchEvent(arg0);
	}

	// 请求事件分发
	float oldx;
	float oldy;

	@Override
	public boolean dispatchTouchEvent(MotionEvent ev) {
		float x = ev.getX();
		float y = ev.getY();
		switch (ev.getAction()) {
		case MotionEvent.ACTION_DOWN:
			oldx = x;
			oldy = y;
			getParent().requestDisallowInterceptTouchEvent(true);
			break;
		case MotionEvent.ACTION_MOVE:
			if (Math.abs(x - oldx) > Math.abs(y - oldy)) {
				if (x - oldx > 10) {
					getParent().requestDisallowInterceptTouchEvent(false);
				} else {
					getParent().requestDisallowInterceptTouchEvent(true);
				}
			}
			break;
		case MotionEvent.ACTION_CANCEL:
			getParent().requestDisallowInterceptTouchEvent(true);
			break;
		}

		return super.dispatchTouchEvent(ev);
	}

	/**
	 * 创建点击事件接口
	 * 
	 * @author wanpg
	 */

	@Override
	public boolean onInterceptTouchEvent(MotionEvent arg0) {
		// 触摸在子ViewPager所在的页面和子ViewPager控件高度之内时
		// 返回false，此时将会将触摸的动作传给子ViewPager
		// 当拦截触摸事件到达此位置的时候，返回true，
		// 说明将onTouch拦截在此控件，进而执行此控件的onTouchEvent
		// super.onInterceptTouchEvent(arg0)
		if (getCurrentItem() == getWidth() && arg0.getY() == getHeight()) {
			return true;
		}

		return super.onInterceptTouchEvent(arg0);
	}

	int disWidth;
	int childVPHeigh = 0;

	@SuppressWarnings("deprecation")
	public void init(Context context) {
		// 获取 屏幕宽高
		WindowManager manager = (WindowManager) context
				.getSystemService(Context.WINDOW_SERVICE);
		disWidth = manager.getDefaultDisplay().getWidth(); // 根據拼命的密度來獲取dp的值相應的px
		childVPHeigh = (int) (context.getResources().getDisplayMetrics().density
				* disWidth + 0.5f);

	}
}
