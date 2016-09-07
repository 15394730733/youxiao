package com.youxiao.ui.activity.work.customermanager;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.LinearLayout;
import android.widget.ListView;

import com.youxiao.R;
import com.youxiao.base.BaseActivity;

import java.util.ArrayList;
import java.util.List;

/**
 * 陈列方式
 * @author StomHong
 * @since 2016-4-10
 *
 */
public class SelectDisplayWayActivity extends BaseActivity implements AdapterView.OnItemClickListener {

    private ListView mListView_DisplayWay;
    private LinearLayout mLinearLayout_Back;
    private List<String> mDatas;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_select_display_way);

        super.init();
    }

    @Override
    public void initView() {

        mLinearLayout_Back = (LinearLayout) findViewById(R.id.id_lay_select_display_way_back);
        mListView_DisplayWay = (ListView) findViewById(R.id.id_lv_select_display_way);
    }

    @Override
    public void initData() {
        mDatas = new ArrayList<>();

        for (int i = 0;i < 5;++i){

            mDatas.add("fff");
        }
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, mDatas);
        mListView_DisplayWay.setAdapter(adapter);
    }

    @Override
    public void initEvent() {

        mListView_DisplayWay.setOnItemClickListener(this);
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
        intent.putExtra("data", mDatas.get(position));
        setResult(Activity.RESULT_OK, intent);
        finish();
    }
}
