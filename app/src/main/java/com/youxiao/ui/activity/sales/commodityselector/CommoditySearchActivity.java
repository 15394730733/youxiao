package com.youxiao.ui.activity.sales.commodityselector;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.EditText;
import android.widget.TextView;

import com.youxiao.R;

/**
 * 张小布
 *
 * 商品搜索
 */
public class CommoditySearchActivity extends Activity implements View.OnClickListener{

    private TextView cacel;
    private EditText editTextSearch;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_commodity_search);
//        View topView = findViewById(R.id.activity_commodity_search_title);
//        ImmersedStatusbarUtils.initAfterSetContentView(this, topView);
        initView();
        initData();
        initEvent();

    }

    private void initView(){
        cacel = (TextView) findViewById(R.id.activity_commodity_search_TV_cacel);
        editTextSearch = (EditText) findViewById(R.id.activity_commodity_search_ET_search);
    }

    private void initEvent(){
        cacel.setOnClickListener(this);
    }

    private void initData(){

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.activity_commodity_search_TV_cacel:
                this.finish();
                break;
        }
    }
}
