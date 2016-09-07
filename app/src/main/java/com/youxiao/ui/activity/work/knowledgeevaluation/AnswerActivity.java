package com.youxiao.ui.activity.work.knowledgeevaluation;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.youxiao.R;
import com.youxiao.base.BaseActivity;

/**
 * 答题
 */
public class AnswerActivity extends BaseActivity{

    private static final String TAG = AnswerActivity.class.getSimpleName();
    private Button mButton_AnswerSheet;
    private TextView mTextView_AnswerSheet;
    private LinearLayout mLinearLayout_AnswerSheet;
    private View mView_Shadow;
    int y;
    int height;
    int bottom;
    int top;
    private Button mButton_Test;
    private boolean mIsMoved = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_answer);
        super.init();
    }


    @Override
    public void initView() {

    }

    @Override
    public void initData() {
    }

    @Override
    public void initEvent() {

    }
}
