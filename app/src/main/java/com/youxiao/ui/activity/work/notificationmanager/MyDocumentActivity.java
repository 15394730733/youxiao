package com.youxiao.ui.activity.work.notificationmanager;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;

import com.youxiao.base.BaseActivity;
import com.youxiao.R;

/**
 * 我的文档
 */
public class MyDocumentActivity extends BaseActivity implements View.OnClickListener {


    private LinearLayout mLinearLayout_Back;
    private RelativeLayout mRelativeLayout_FileTemplate;
    private RelativeLayout mRelativeLayout_RulesAndRegulations;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_document);

        super.init();

    }

    @Override
    public void initView() {
        mLinearLayout_Back = (LinearLayout) findViewById(R.id.id_ll_my_document_back);
        mRelativeLayout_RulesAndRegulations = (RelativeLayout) findViewById(R.id.id_rl_my_document_rules_and_regulations);
        mRelativeLayout_FileTemplate = (RelativeLayout) findViewById(R.id.id_rl_my_document_file_template);
    }

    @Override
    public void initData() {


    }

    @Override
    public void initEvent() {

        mLinearLayout_Back.setOnClickListener(this);
        mRelativeLayout_FileTemplate.setOnClickListener(this);
        mRelativeLayout_RulesAndRegulations.setOnClickListener(this);
    }


    @Override
    public void onClick(View v) {
        Intent intent = new Intent();
        switch (v.getId()) {
            case R.id.id_ll_my_document_back:
                finish();
                break;
            case R.id.id_rl_my_document_file_template:
                intent.setClass(this,FileTemplateActivity.class);
                startActivity(intent);
                break;
            case R.id.id_rl_my_document_rules_and_regulations:
                intent.setClass(this,RulesAndRegulationsActivity.class);
                startActivity(intent);
                break;
            default:
                break;
        }
    }
}
