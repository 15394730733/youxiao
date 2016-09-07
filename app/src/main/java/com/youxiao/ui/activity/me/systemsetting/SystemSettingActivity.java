package com.youxiao.ui.activity.me.systemsetting;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;

import com.youxiao.base.BaseActivity;
import com.youxiao.R;

/**
 * 张小布
 * 系统设置
 */
public class SystemSettingActivity extends BaseActivity implements View.OnClickListener {

    private LinearLayout mLinearLayout_Back;
    private LinearLayout mLinearLayout_DataFeedback;
    private LinearLayout mLinearLayout_PushMessage;
    private LinearLayout mLinearLayout_CommoditySelector;
    private LinearLayout mLinearLayout_PrintSetting;
    private LinearLayout mLinearLayout_WiFiAutoUploading;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_system_setting);
        super.init();
    }

    @Override
    public void initView() {
        mLinearLayout_CommoditySelector = (LinearLayout) findViewById(R.id.ll_commodity_selector);
        mLinearLayout_Back = (LinearLayout) findViewById(R.id.ll_system_setting_back);
        mLinearLayout_PrintSetting = (LinearLayout) findViewById(R.id.ll_print_setting);
        mLinearLayout_WiFiAutoUploading = (LinearLayout) findViewById(R.id.ll_wifi_auto_uploading);
        mLinearLayout_PushMessage = (LinearLayout) findViewById(R.id.ll_push_message);
        mLinearLayout_DataFeedback = (LinearLayout) findViewById(R.id.ll_data_feedback);

    }

    @Override
    public void initData() {

    }

    @Override
    public void initEvent() {
        mLinearLayout_CommoditySelector.setOnClickListener(this);
        mLinearLayout_Back.setOnClickListener(this);
        mLinearLayout_PrintSetting.setOnClickListener(this);
        mLinearLayout_WiFiAutoUploading.setOnClickListener(this);
        mLinearLayout_DataFeedback.setOnClickListener(this);
        mLinearLayout_PushMessage.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        Intent intent = new Intent();
        switch (v.getId()) {
            case R.id.ll_back:
                finish();
                break;
            case R.id.ll_push_message:
                break;
            case R.id.ll_commodity_selector:
                intent.setClass(this, CommoditySelectorActivity.class);
                startActivity(intent);
                break;
            case R.id.ll_print_setting:
                intent.setClass(this, PrintSettingActivity.class);
                startActivity(intent);
                break;
            case R.id.ll_wifi_auto_uploading:
                intent.setClass(this, WifiAutoUploadingActivity.class);
                startActivity(intent);
                break;
            case R.id.ll_data_feedback:
                break;
            default:
                break;
        }

    }

}
