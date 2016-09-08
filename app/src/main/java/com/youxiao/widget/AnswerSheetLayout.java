package com.youxiao.widget;

import android.content.Context;
import android.graphics.Canvas;
import android.support.v4.view.ViewCompat;
import android.support.v4.widget.ViewDragHelper;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;

/**
 * @author StomHong
 * @since 2016-06-13
 */
public class AnswerSheetLayout extends ViewGroup {


    private static final float TOUCH_SLOP_SENSITIVITY = 1.f;

    private ViewDragHelper mDragger;
    private View mDragView;
    private Context mContext;


    public AnswerSheetLayout(Context context) {
        this(context, null);
    }

    public AnswerSheetLayout(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public AnswerSheetLayout(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        mContext = context;
        mDragger = ViewDragHelper.create(this, TOUCH_SLOP_SENSITIVITY, new ViewDragCallback());


    }

    @Override
    protected void onLayout(boolean changed, int l, int t, int r, int b) {
//        mDragView.setTop(Util.getScreenHeight(mContext));
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
    }

    @Override
    public boolean onInterceptTouchEvent(MotionEvent ev) {
        return mDragger.shouldInterceptTouchEvent(ev);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        mDragger.processTouchEvent(event);
        return true;
    }


    @Override
    public void computeScroll() {
        super.computeScroll();
        if (mDragger.continueSettling(true)) {
            ViewCompat.postInvalidateOnAnimation(this);
//            invalidate();
        }
    }

    @Override
    protected void onFinishInflate() {
        super.onFinishInflate();
        mDragView = getChildAt(1);
    }

    private class ViewDragCallback extends ViewDragHelper.Callback {
        @Override
        public boolean tryCaptureView(View child, int pointerId) {

            return mDragView == child;
        }

        @Override
        public void onViewReleased(View releasedChild, float xvel, float yvel) {
            super.onViewReleased(releasedChild, xvel, yvel);
            //手指拖动释放回调


        }

        @Override
        public int clampViewPositionVertical(View child, int top, int dy) {
            return super.clampViewPositionVertical(child, top, dy);
        }

        @Override
        public void onViewPositionChanged(View changedView, int left, int top, int dx, int dy) {
            super.onViewPositionChanged(changedView, left, top, dx, dy);
        }
    }


}
