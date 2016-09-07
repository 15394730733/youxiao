package com.youxiao.ui.activity.work.rebatemanager;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.LinearLayout;
import android.widget.ListView;

import com.youxiao.base.BaseActivity;
import com.youxiao.R;
import com.youxiao.adapter.CommonAdapter;
import com.youxiao.adapter.ViewHolder;
import com.youxiao.util.SetListViewHeight;

import java.util.ArrayList;
import java.util.List;

/**
 * 返利管理
 *
 * @author StomHong
 * @since 2016-7-27
 */
public class RebateManagerActivity extends BaseActivity implements View.OnClickListener{

    private ListView mListView_CreditNote;
    private LinearLayout mLinearLayout_Back;
    private List<String> mDatas;
    private CommonAdapter<String> mAdapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rebate_manager);

        super.init();
    }

    @Override
    public void initView() {
        mLinearLayout_Back = (LinearLayout) findViewById(R.id.id_ll_credit_note_manager_back);
        mListView_CreditNote = (ListView) findViewById(R.id.id_lv_credit_note);
    }

    @Override
    public void initData() {
        mDatas = new ArrayList<>();
        for (int i=0;i<15;i++){
            mDatas.add("");
        }

        mAdapter = new CommonAdapter<String>(this, mDatas, R.layout.item_rebate_manager) {
            @Override
            public void convert(ViewHolder holder, String s) {

                holder.setViewOnClick(new ViewHolder.ViewClick() {

                    @Override
                    public void onClick(View v, int position) {
                        Intent intent = new Intent();
                        if (v.getId() == R.id.id_iv_credit_note_collection) {
                            intent.setClass(RebateManagerActivity.this, RebateOperationActivity.class);
                        } else if (v.getId() == R.id.id_iv_credit_note_detail) {
                            intent.setClass(RebateManagerActivity.this, RebateDetailActivity.class);
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
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.id_ll_credit_note_manager_back:
                finish();
                break;
            default:
                break;
        }
    }
}
