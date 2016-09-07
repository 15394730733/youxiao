package com.youxiao.ui.activity.work.customermanager;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.LinearLayout;
import android.widget.ListView;

import com.youxiao.R;
import com.youxiao.base.BaseActivity;

import java.util.ArrayList;
import java.util.List;

public class SelectPriceTypeActivity extends BaseActivity implements AdapterView.OnItemClickListener{


    private ListView mListView_PriceType;
    private LinearLayout mLinearLayout_Back;
    private List<String> mDatas;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_select_price_type);

        super.init();
    }

    @Override
    public void initView() {

        mListView_PriceType = (ListView) findViewById(R.id.id_lv_select_price_type);
        mLinearLayout_Back = (LinearLayout) findViewById(R.id.id_lay_select_price_type_back);
    }

    @Override
    public void initData() {
        mDatas = new ArrayList<>();

        for (int i = 0;i < 5;++i){

            mDatas.add("fff");
        }
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this,android.R.layout.simple_list_item_1,mDatas);
        mListView_PriceType.setAdapter(adapter);
    }

    @Override
    public void initEvent() {

        mListView_PriceType.setOnItemClickListener(this);
        mLinearLayout_Back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }


    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        Intent intent = new Intent();
        intent.putExtra("data",mDatas.get(position));
        setResult(Activity.RESULT_OK,intent);
        finish();
    }
}
