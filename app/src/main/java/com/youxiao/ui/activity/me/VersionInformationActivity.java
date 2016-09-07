package com.youxiao.ui.activity.me;

import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;

import com.youxiao.R;
import com.youxiao.base.BaseActivity;


/**
 * 系统信息
 */
public class VersionInformationActivity extends BaseActivity implements View.OnClickListener {

    private LinearLayout mLinearLayout_Back;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_version_information);
        super.init();
    }

    @Override
    public void initView() {
        mLinearLayout_Back = (LinearLayout) findViewById(R.id.ll_version_information_back);
//        versionNumber = (TextView) findViewById(R.id.activity_version_information_RL_TV_version_number);
//        listNumber = (TextView) findViewById(R.id.activity_version_information_RL_TV_list_number);
//        portNumber = (TextView) findViewById(R.id.activity_version_information_RL_TV_port_number);
//        messageNumber = (TextView) findViewById(R.id.activity_version_information_RL_TV_message_number);
//        ipLocation = (TextView) findViewById(R.id.activity_version_information_RL_TV_ip_locationr);
//        datalibrary = (TextView) findViewById(R.id.activity_version_information_RL_TV_data_library);
//        versionsName = (TextView) findViewById(R.id.activity_version_information_RL_TV_version_name);
    }

    @Override
    public void initData() {

    }

    @Override
    public void initEvent() {
        mLinearLayout_Back.setOnClickListener(this);

    }


    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.ll_version_information_back:
                finish();
                break;
        }
    }
}
