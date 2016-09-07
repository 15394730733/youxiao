package com.youxiao.ui.activity.work.attendancesignature;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.youxiao.R;
import com.youxiao.base.BaseActivity;

/**
 * 签到
 *
 * @author StomHong
 * @since 2016-7-20
 *
 */
public class SignatureActivity extends BaseActivity implements View.OnClickListener{

    private TextView mTextView_SignatureTime;
    private TextView mTextView_SignatureAddress;
    private LinearLayout mLinearLayout_Back;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signature);

        super.init();
    }

    @Override
    public void initView() {
        mTextView_SignatureTime = (TextView) findViewById(R.id.id_tv_signature_time);
        mTextView_SignatureAddress = (TextView) findViewById(R.id.id_tv_signature_address);
        mLinearLayout_Back = (LinearLayout) findViewById(R.id.id_ll_signature_back);
    }

    @Override
    public void initData() {
        Intent intent = getIntent();
        String name = intent.getStringExtra("name");
        String address = intent.getStringExtra("address");
        String time = intent.getStringExtra("time");
        if (!"".equals(name)){
            address = name+"，"+address;
        }
        mTextView_SignatureTime.setText(time);
        mTextView_SignatureAddress.setText(address);
    }

    @Override
    public void initEvent() {
        mLinearLayout_Back.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.id_ll_signature_back:
                finish();
                break;
            case R.id.id_iv_photo:

                break;
            default:
                break;
        }
    }
}
