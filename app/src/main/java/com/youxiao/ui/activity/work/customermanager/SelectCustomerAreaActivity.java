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

import com.youxiao.base.BaseActivity;
import com.youxiao.R;

import java.util.ArrayList;
import java.util.List;

/**
 * 选择客户区域
 */
public class SelectCustomerAreaActivity extends BaseActivity implements AdapterView.OnItemClickListener {

    private ListView mListView_CustomerArea;

    private LinearLayout mLinearLayout_Back;
    private List<String> mDatas;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_select_customer_area);

        super.init();
    }

    @Override
    public void initView() {

        mLinearLayout_Back = (LinearLayout) findViewById(R.id.id_lay_select_customer_area_back);
        mListView_CustomerArea = (ListView) findViewById(R.id.id_lv_select_customer_area);
    }

    @Override
    public void initData() {

        mDatas = new ArrayList<>();
        for (int i = 0;i < 5;++i){
            mDatas.add("客户区域");
        }

        ArrayAdapter<String> adapter = new ArrayAdapter<>(this,android.R.layout.simple_list_item_1,mDatas);

        mListView_CustomerArea.setAdapter(adapter);
    }

    @Override
    public void initEvent() {

        mListView_CustomerArea.setOnItemClickListener(this);
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
        setResult(Activity.RESULT_OK, intent);
        finish();
    }
}
