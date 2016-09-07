package com.youxiao.ui.activity.work.procurementmanager;

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
 * 选择供应商
 *
 * @author StomHong
 * @since 2016-7-1
 */
public class SelectProviderActivity extends BaseActivity implements AdapterView.OnItemClickListener {

    private ListView mListView_Provider;
    private List<String> mProviders;
    private LinearLayout mLinearLayout_Back;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_select_provider);

        super.init();
    }

    @Override
    public void initView() {
        mListView_Provider = (ListView) findViewById(R.id.id_lv_provider);
        mLinearLayout_Back = (LinearLayout) findViewById(R.id.id_ll_select_provider_back);
    }

    @Override
    public void initData() {
        mProviders = new ArrayList<>();
        mProviders.add("茅台集团");
        mProviders.add("郎酒集团");
        mProviders.add("红星集团");
        mProviders.add("小米");
        mProviders.add("魅族 ");
        mProviders.add("华为");
        mProviders.add("联想");
        mProviders.add("努比亚");
        mProviders.add("ZUK");
        ArrayAdapter adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, mProviders);
        mListView_Provider.setAdapter(adapter);
    }

    @Override
    public void initEvent() {
        mListView_Provider.setOnItemClickListener(this);
        mLinearLayout_Back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        String provider = mProviders.get(position);
        Intent intent = new Intent();
        intent.putExtra("provider", provider);
        setResult(Activity.RESULT_OK, intent);
        finish();
    }
}
