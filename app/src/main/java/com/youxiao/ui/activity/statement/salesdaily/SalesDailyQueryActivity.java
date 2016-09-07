package com.youxiao.ui.activity.statement.salesdaily;

import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;

import com.youxiao.R;
import com.youxiao.base.BaseActivity;

/**
 * 销售日结查询
 */
public class SalesDailyQueryActivity extends BaseActivity implements View.OnClickListener {

    private LinearLayout mLinearLayout_Back;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sales_daily_query);
        super.init();
    }

    @Override
    public void initView() {
        mLinearLayout_Back = (LinearLayout) findViewById(R.id.id_ll_sales_daily_query_back);

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
            case R.id.id_ll_sales_daily_query_back:
                finish();
                break;
            default:
                break;
        }


    }




}
