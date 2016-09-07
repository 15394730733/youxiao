package com.youxiao.widget;

import android.content.Context;
import android.util.AttributeSet;
import android.view.ViewGroup;

/**
 * @author StomHong
 * @since 2016-06-13
 */
public class AnswerSheetLayout extends ViewGroup {



    public AnswerSheetLayout(Context context) {
        this(context,null);
    }

    public AnswerSheetLayout(Context context, AttributeSet attrs) {
        this(context, attrs,0);
    }

    public AnswerSheetLayout(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);

    }

    @Override
    protected void onLayout(boolean changed, int l, int t, int r, int b) {

    }
}
