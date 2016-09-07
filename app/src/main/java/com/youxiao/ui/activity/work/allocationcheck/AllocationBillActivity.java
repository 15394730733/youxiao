package com.youxiao.ui.activity.work.allocationcheck;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.youxiao.R;
import com.youxiao.base.BaseActivity;
import com.youxiao.ui.activity.work.procurementmanager.RemarkActivity;

/**
 * 调拨单
 */
public class AllocationBillActivity extends BaseActivity implements View.OnClickListener {

    private LinearLayout mLinearLayout_Back;
    private ImageView mImageView_Remark;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_allocation_bill);
        super.init();
    }

    @Override
    public void initView() {
        mLinearLayout_Back = (LinearLayout) findViewById(R.id.id_ll_commodity_allocation_back);
        mImageView_Remark = (ImageView) findViewById(R.id.id_iv_remark);
    }

    @Override
    public void initData() {

    }

    @Override
    public void initEvent() {
        mLinearLayout_Back.setOnClickListener(this);
        mImageView_Remark.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.id_ll_commodity_allocation_back:
                finish();
                break;
            case R.id.id_iv_remark:
                Intent intent = new Intent();
                intent.setClass(this, RemarkActivity.class);
                startActivity(intent);
                break;
            default:
                break;
        }
    }
}
