package com.youxiao.ui.activity.me.accountandsafety;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;

import com.youxiao.R;

/**
 * 账号与安全
 */
public class SafetyActivity extends Activity implements View.OnClickListener {
    private RelativeLayout accountNumber, relieveBound, amendPassword, amendGesturePassword;
    private LinearLayout back;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_safety);
        initView();
        initEvent();
        initData();
    }

    private void initView() {
        accountNumber = (RelativeLayout) findViewById(R.id.activity_safety_RL_account_number);
        relieveBound = (RelativeLayout) findViewById(R.id.activity_safety_RL_relieve_bound);
        amendPassword = (RelativeLayout) findViewById(R.id.activity_safety_RL_amend_password);
        amendGesturePassword = (RelativeLayout) findViewById(R.id.activity_safety_RL_amend_gesture_password);
        back = (LinearLayout) findViewById(R.id.activity_safety_LL_back);
    }

    private void initData() {

    }

    private void initEvent() {
        accountNumber.setOnClickListener(this);
        relieveBound.setOnClickListener(this);
        amendPassword.setOnClickListener(this);
        amendGesturePassword.setOnClickListener(this);
        back.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        Intent intent = new Intent();
        switch (v.getId()) {
            case R.id.activity_safety_LL_back:
                finish();
                break;
            case R.id.activity_safety_RL_account_number:
                break;
            case R.id.activity_safety_RL_relieve_bound:
                intent.setClass(this,RemoveBoundActivity.class);
                startActivity(intent);
                break;
            case R.id.activity_safety_RL_amend_password:
                intent.setClass(this,AmendPwdActivity.class);
                startActivity(intent);
                break;
            case R.id.activity_safety_RL_amend_gesture_password:
                intent.setClass(this,AmendGesturePasswordActivity.class);
                startActivity(intent);
                break;
        }
    }
}
