package com.youxiao.ui.activity.work.otherprocedure;

import android.os.Bundle;
import android.view.Window;

import com.youxiao.base.BaseActivity;
import com.youxiao.R;

/**
 * 我发起的
 */
public class ILaunchActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ilaunch);
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
