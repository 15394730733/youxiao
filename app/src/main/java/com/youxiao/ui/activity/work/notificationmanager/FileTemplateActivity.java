package com.youxiao.ui.activity.work.notificationmanager;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.AdapterView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.RelativeLayout;

import com.youxiao.base.BaseActivity;
import com.youxiao.ui.activity.sales.commodityselector.CommoditySearchActivity;
import com.youxiao.R;
import com.youxiao.adapter.CommonAdapter;
import com.youxiao.adapter.ViewHolder;

import java.util.ArrayList;
import java.util.List;

/**
 * 文档模板
 */
public class FileTemplateActivity extends BaseActivity implements AdapterView.OnItemClickListener {


    private ListView mListView_File;
    private CommonAdapter<String> mAdapter;
    private LinearLayout mLinearLayout_Back;
    private RelativeLayout mRelativeLayout_Search;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_file_template);

        super.init();
    }


    @Override
    public void initView() {
        mListView_File = (ListView) findViewById(R.id.id_lv_file_template);
        mLinearLayout_Back = (LinearLayout) findViewById(R.id.id_ll_file_template_back);
        mRelativeLayout_Search = (RelativeLayout) findViewById(R.id.id_rl_file_template_search);
    }

    @Override
    public void initData() {
        List<String> files = new ArrayList<>();
        files.add("");
        files.add("");
        files.add("");
        files.add("");
        files.add("");
        mAdapter = new CommonAdapter<String>(this, files, R.layout.item_document) {
            @Override
            public void convert(ViewHolder holder, String s) {

            }
        };
        mListView_File.setAdapter(mAdapter);

    }

    @Override
    public void initEvent() {
        mListView_File.setOnItemClickListener(this);
        mLinearLayout_Back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        mRelativeLayout_Search.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                intent.setClass(FileTemplateActivity.this, CommoditySearchActivity.class);
                startActivity(intent);
            }
        });
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        Intent intent = new Intent();
        intent.setClass(this, DocumentContentActivity.class);
        startActivity(intent);
    }
}
