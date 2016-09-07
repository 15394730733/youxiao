package com.youxiao.ui.activity.statement.salescontrast;

import android.os.Bundle;
import android.view.Window;

import com.youxiao.base.BaseActivity;
import com.youxiao.R;

/**
 * 销售对比
 */
public class SalesContrastActivity extends BaseActivity {






    @Override
    protected void onCreate(Bundle savedInstanceState) {
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sales_contrast);
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
