package com.youxiao.ui.activity.statement.salesdetail;

import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;

import com.youxiao.R;
import com.youxiao.base.BaseActivity;

/**
 * 销售明细
 */
public class SalesDetailActivity extends BaseActivity implements View.OnClickListener {

    private LinearLayout mLinearLayout_Back;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sales_detail);
        super.init();
    }

    @Override
    public void initView() {
        mLinearLayout_Back = (LinearLayout) findViewById(R.id.id_ll_sales_detail_back);
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
            case R.id.id_ll_sales_detail_back:
                finish();
                break;
            default:
                break;
        }
    }
}
