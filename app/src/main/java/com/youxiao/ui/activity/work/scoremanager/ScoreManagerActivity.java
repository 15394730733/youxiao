package com.youxiao.ui.activity.work.scoremanager;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.LinearLayout;
import android.widget.ListView;

import com.youxiao.base.BaseActivity;
import com.youxiao.ui.activity.work.rebatemanager.RebateDetailActivity;
import com.youxiao.ui.activity.work.rebatemanager.RebateOperationActivity;
import com.youxiao.R;
import com.youxiao.adapter.CommonAdapter;
import com.youxiao.adapter.ViewHolder;
import com.youxiao.util.ListViewUtil;

import java.util.ArrayList;
import java.util.List;

/**
 * 积分管理
 */
public class ScoreManagerActivity extends BaseActivity implements View.OnClickListener{


    private ListView mListView_CreditNote;
    private LinearLayout mLinearLayout_Back;
    private List<String> mDatas;
    private CommonAdapter<String> mAdapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_score_manager);
        super.init();
    }

    @Override
    public void initView() {
        mLinearLayout_Back = (LinearLayout) findViewById(R.id.ll_score_manager_back);
        mListView_CreditNote = (ListView) findViewById(R.id.id_lv_credit_note);
    }

    @Override
    public void initData() {
        mDatas = new ArrayList<>();
        for (int i=0;i<15;i++){
            mDatas.add("可用积分:1000");
        }

        mAdapter = new CommonAdapter<String>(this, mDatas, R.layout.item_rebate_manager) {
            @Override
            public void convert(ViewHolder holder, String s) {
                holder.setText(R.id.tv_score,s);

                holder.setViewOnClick(new ViewHolder.ViewClick() {

                    @Override
                    public void onClick(View v, int position) {
                        Intent intent = new Intent();
                        if (v.getId() == R.id.id_iv_credit_note_collection) {
                            intent.setClass(getContext(), RebateOperationActivity.class);
                        } else if (v.getId() == R.id.id_iv_credit_note_detail) {
                            intent.setClass(getContext(), RebateDetailActivity.class);
                        }
                        startActivity(intent);
                    }
                }, R.id.id_iv_credit_note_collection, R.id.id_iv_credit_note_detail);
            }
        };
        mListView_CreditNote.setAdapter(mAdapter);
        ListViewUtil.setListViewHeightBasedOnChildren(mListView_CreditNote);
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
