package com.youxiao.ui.activity.work.displaymanager;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.RelativeLayout;

import com.youxiao.base.BaseActivity;
import com.youxiao.ui.activity.sales.commodityselector.CommoditySearchActivity;
import com.youxiao.R;
import com.youxiao.adapter.CommonAdapter;
import com.youxiao.adapter.ViewHolder;
import com.youxiao.util.SetListViewHeight;

import java.util.ArrayList;
import java.util.List;

/**
 * 陈列管理
 *
 * @author StomHong
 * @since 2016-7-27
 */
public class DisplayManagerActivity extends BaseActivity implements View.OnClickListener{

    private ListView mListView_CreditNote;
    private LinearLayout mLinearLayout_Back;
    private List<String> mDatas;
    private CommonAdapter<String> mAdapter;
    private RelativeLayout mRelativeLayout_Search;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_display_manager);

        super.init();
    }

    @Override
    public void initView() {
        mLinearLayout_Back = (LinearLayout) findViewById(R.id.id_ll_credit_note_manager_back);
        mListView_CreditNote = (ListView) findViewById(R.id.id_lv_credit_note);
        mRelativeLayout_Search = (RelativeLayout) findViewById(R.id.id_rl_procurement_stock_select_provider);
    }

    @Override
    public void initData() {
        mDatas = new ArrayList<>();
        for (int i=0;i<15;i++){
            mDatas.add("");
        }

        mAdapter = new CommonAdapter<String>(this, mDatas, R.layout.item_display_manager) {
            @Override
            public void convert(ViewHolder holder, String s) {

                holder.setViewOnClick(new ViewHolder.ViewClick() {

                    @Override
                    public void onClick(View v, int position) {
                        Intent intent = new Intent();
                        if (v.getId() == R.id.id_iv_credit_note_collection) {
                            intent.setClass(DisplayManagerActivity.this, DisplayOperationActivity.class);
                        } else if (v.getId() == R.id.id_iv_credit_note_detail) {
                            intent.setClass(DisplayManagerActivity.this, DisplayDetailActivity.class);
                        }
                        startActivity(intent);
                    }
                }, R.id.id_iv_credit_note_collection, R.id.id_iv_credit_note_detail);
            }
        };
        mListView_CreditNote.setAdapter(mAdapter);
        SetListViewHeight.setListViewHeightBasedOnChildren(mListView_CreditNote);
        mListView_CreditNote.setFocusable(false);
    }

    @Override
    public void initEvent() {
        mLinearLayout_Back.setOnClickListener(this);
        mRelativeLayout_Search.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.id_ll_credit_note_manager_back:
                finish();
                break;
            case R.id.id_ll_procurement_stock_select_provider:
                Intent intent = new Intent();
                intent.setClass(this, CommoditySearchActivity.class);
                startActivity(intent);
            default:
                break;
        }
    }
}
