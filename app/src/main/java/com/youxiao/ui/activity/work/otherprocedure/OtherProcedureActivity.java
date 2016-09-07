package com.youxiao.ui.activity.work.otherprocedure;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.LinearLayout;

import com.youxiao.base.BaseActivity;
import com.youxiao.R;

/**
 * 其它流程
 */
public class OtherProcedureActivity extends BaseActivity implements View.OnClickListener{

    private LinearLayout mLinearLayout_Back;
    private LinearLayout mLinearLayout_Dimission;
    private LinearLayout mLinearLayout_BecomeRegularWorker;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_other_procedure);
        super.init();
    }

    @Override
    public void initView() {

        mLinearLayout_Back = (LinearLayout) findViewById(R.id.id_ll_approve_back);
        mLinearLayout_Dimission = (LinearLayout) findViewById(R.id.id_ll_approve_dimission);
        mLinearLayout_BecomeRegularWorker = (LinearLayout) findViewById(R.id.id_ll_approve_become_regular_worker);
    }

    @Override
    public void initData() {

    }

    @Override
    public void initEvent() {
        mLinearLayout_Back.setOnClickListener(this);
        mLinearLayout_Dimission.setOnClickListener(this);
        mLinearLayout_BecomeRegularWorker.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        Intent intent = new Intent();
        switch (v.getId()){
            case R.id.id_ll_approve_back:
                finish();
                break;
            case R.id.id_ll_approve_become_regular_worker:
                intent.setClass(this,BecomeRegularWorkerActivity.class);
                startActivity(intent);
                break;
            case R.id.id_ll_approve_dimission:
                intent.setClass(this,DimissionActivity.class);
                startActivity(intent);
                break;
            default:
                break;
        }
    }
}
