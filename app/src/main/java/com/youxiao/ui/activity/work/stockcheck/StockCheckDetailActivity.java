package com.youxiao.ui.activity.work.stockcheck;

import android.os.Bundle;
import android.view.Window;

import com.youxiao.base.BaseActivity;
import com.youxiao.R;

/**
 * 明细
 */
public class StockCheckDetailActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_stock_check_detail);
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
