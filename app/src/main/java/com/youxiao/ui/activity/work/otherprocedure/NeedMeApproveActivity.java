package com.youxiao.ui.activity.work.otherprocedure;

import android.os.Bundle;
import android.view.Window;

import com.youxiao.base.BaseActivity;
import com.youxiao.R;

/**
 * 待我审批
 */
public class NeedMeApproveActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_need_me_approve);
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
