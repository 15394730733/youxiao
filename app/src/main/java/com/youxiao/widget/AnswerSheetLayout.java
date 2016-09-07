package com.youxiao.widget;

import android.content.Context;
import android.graphics.Canvas;
import android.support.v4.view.ViewCompat;
import android.support.v4.widget.ViewDragHelper;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;

/**
 * @author StomHong
 * @since 2016-06-13
 */
public class AnswerSheetLayout extends FrameLayout {

    private static final String TAG = AnswerSheetLayout.class.getSimpleName();

    private ViewDragHelper mDragHelper;
    private ViewGroup mTopContent;
    private ViewGroup mMainContent;
    private int mHeight;
    private int mWidth;
    private int mRange;

    public AnswerSheetLayout(Context context) {
        this(context, null);
    }

    public AnswerSheetLayout(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public AnswerSheetLayout(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        // a.初始化 (通过静态方法)
        mDragHelper = ViewDragHelper.create(this, mCallback);
    }

    ViewDragHelper.Callback mCallback = new ViewDragHelper.Callback() {
        // c. 重写事件

        // 1. 根据返回结果决定当前child是否可以拖拽
        // child 当前被拖拽的View
        // pointerId 区分多点触摸的id
        @Override
        public boolean tryCaptureView(View child, int pointerId) {
            Log.d(TAG, "tryCaptureView: " + child);
            return true;
        }

        @Override
        public void onViewCaptured(View capturedChild, int activePointerId) {
            Log.d(TAG, "onViewCaptured: " + capturedChild);
            // 当capturedChild被捕获时,调用.
            super.onViewCaptured(capturedChild, activePointerId);
        }

        @Override
        public int getViewVerticalDragRange(View child) {
            return mRange;
        }

        // 2. 根据建议值 修正将要移动到的(横向)位置   (重要)
        // 此时没有发生真正的移动
        @Override
        public int clampViewPositionVertical(View child, int top, int dy) {
            if (child == mMainContent) {
                top = fixTop(top);
            }
            return top;
        }

        // 3. 当View位置改变的时候, 处理要做的事情 (更新状态, 伴随动画, 重绘界面)
        // 此时,View已经发生了位置的改变
        @Override
        public void onViewPositionChanged(View changedView, int left, int top, int dx, int dy) {
            super.onViewPositionChanged(changedView, left, top, dx, dy);


            Log.d(TAG, "onViewPositionChanged: " + "top: " + top + " dy: " + dy);
            int newTop = top;
            if (changedView == mTopContent) {
                // 把当前变化量传递给mMainContent
                mTopContent.layout(0, 0, mTopContent.getWidth(), mRange);
                newTop = mTopContent.getTop() + dy;
            }
            // 进行修正
            newTop = fixTop(newTop);

            if (changedView == mMainContent) {
                // 当左面板移动之后, 再强制放回去.
                mTopContent.layout(0, newTop-mRange,  mWidth, newTop + mRange);
                mMainContent.layout(0, newTop, mWidth, newTop + mHeight);
            }

            // 为了兼容低版本, 每次修改值之后, 进行重绘
            invalidate();
        }

        // 4. 当View被释放的时候, 处理的事情(执行动画)
        @Override
        public void onViewReleased(View releasedChild, float xvel, float yvel) {
            // View releasedChild 被释放的子View
            // float xvel 水平方向的速度, 向右为+
            // float yvel 竖直方向的速度, 向下为+
            Log.d(TAG, "onViewReleased: " + "xvel: " + xvel + " yvel: " + yvel);
            super.onViewReleased(releasedChild, xvel, yvel);

        }

    };

    // b.传递触摸事件
    @Override
    public boolean onInterceptTouchEvent(MotionEvent ev) {
        // 传递给mDragHelper
        return mDragHelper.shouldInterceptTouchEvent(ev);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        try {
            mDragHelper.processTouchEvent(event);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return true;
    }


    @Override
    protected void onFinishInflate() {
        super.onFinishInflate();
        // 容错性检查 (至少有俩子View, 子View必须是ViewGroup的子类)
        if (getChildCount() < 2) {
            throw new IllegalStateException("布局至少有俩孩子. Your ViewGroup must have 2 children at least.");
        }
        if (!(getChildAt(0) instanceof ViewGroup && getChildAt(1) instanceof ViewGroup)) {
            throw new IllegalArgumentException("子View必须是ViewGroup的子类. Your children must be an instance of ViewGroup");
        }
        mTopContent = (ViewGroup) getChildAt(0);
        mMainContent = (ViewGroup) getChildAt(1);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        mHeight = mMainContent.getMeasuredHeight();
        mWidth =  mMainContent.getMeasuredWidth();
        mRange = mTopContent.getMeasuredHeight();

    }

    @Override
    protected void onLayout(boolean changed, int left, int top, int right, int bottom) {
        super.onLayout(changed, left, top, right, bottom);
        mTopContent.layout(0, -mRange,mTopContent.getWidth(),0);
        mMainContent.layout(0, 0, mWidth, mHeight);
        if(mDragHelper.smoothSlideViewTo(mMainContent,0,0)){
            // 返回true代表还没有移动到指定位置, 需要刷新界面.
            // 参数传this(child所在的ViewGroup)
            ViewCompat.postInvalidateOnAnimation(this);
        }
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        // 1. 触发一个平滑动画
//        mDragHelper.smoothSlideViewTo(mTopContent,0,-mRange);
//        mDragHelper.smoothSlideViewTo(mMainContent,0,0);
    }

    @Override
    public void computeScroll() {
        super.computeScroll();
        // 2. 持续平滑动画 (高频率调用)
        if(mDragHelper.continueSettling(true)){
            //  如果返回true, 动画还需要继续执行
            ViewCompat.postInvalidateOnAnimation(this);
        }
    }

    /**
     * 根据范围修正上边值
     *
     * @param top
     * @return
     */
    private int fixTop(int top) {
        if (top < 0) {
            return 0;
        } else if (top > mRange) {
            return mRange;
        }
        return top;
    }
}
