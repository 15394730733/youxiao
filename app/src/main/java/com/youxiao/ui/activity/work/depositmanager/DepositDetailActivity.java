package com.youxiao.ui.activity.work.depositmanager;

import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import com.youxiao.R;
import com.youxiao.adapter.CommonAdapter;
import com.youxiao.adapter.ViewHolder;
import com.youxiao.base.BaseActivity;

import java.util.ArrayList;
import java.util.List;

/**
 * 存款明细
 *
 * @author StomHong
 * @since 2016-8-6
 */
public class DepositDetailActivity extends BaseActivity implements View.OnClickListener{


    private LinearLayout mLinearLayout_Back;
    private LinearLayout mLinearLayout_Debt;
    private LinearLayout mLinearLayout_Collection;
    private LinearLayout mLinearLayout_All;

    private TextView mTextView_Debt;
    private TextView mTextView_Collection;
    private TextView mTextView_All;
    private CommonAdapter<String> mAllAdapter;
    private CommonAdapter<String> mCollectionAdapter;
    private CommonAdapter<String> mDebtAdapter;
    private ListView mListView_CreditNoteDetail;
    private List<String> mDatas;
    private List<String> mDatas1;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_deposit_detail);
        super.init();
    }

    @Override
    public void initView() {
        mLinearLayout_Back = (LinearLayout) findViewById(R.id.id_ll_credit_note_detail_back);
        mLinearLayout_Debt = (LinearLayout) findViewById(R.id.id_ll_credit_note_detail_debt);
        mLinearLayout_Collection = (LinearLayout) findViewById(R.id.id_ll_credit_note_detail_collection);
        mLinearLayout_All = (LinearLayout) findViewById(R.id.id_ll_credit_note_detail_all);

        mTextView_Debt = (TextView) findViewById(R.id.id_tv_credit_note_detail_debt);
        mTextView_Collection = (TextView) findViewById(R.id.id_tv_credit_note_detail_collection);
        mTextView_All = (TextView) findViewById(R.id.id_tv_credit_note_detail_all);
        mListView_CreditNoteDetail = (ListView) findViewById(R.id.id_lv_credit_note_detail);
    }

    @Override
    public void initData() {
        mDatas = new ArrayList<>();
        for (int i = 0; i < 20; i++) {
            mDatas.add("");
        }
        mDatas1 = new ArrayList<>();
        for (int i = 0; i < 20; i++) {
            if (i % 2 == 0){
                mDatas1.add("存入:+1000,00.00");
            }else {
                mDatas1.add("支出:-1000,00.00");
            }
        }

        mAllAdapter = new CommonAdapter<String>(this, mDatas1, R.layout.item_deposit_detail) {
            @Override
            public void convert(final ViewHolder holder, String s) {
                holder.setText(R.id.id_tv_deposit,s);
            }
        };
        mListView_CreditNoteDetail.setAdapter(mAllAdapter);

        mCollectionAdapter = new CommonAdapter<String>(this, mDatas, R.layout.item_deposit_detail) {
            @Override
            public void convert(final ViewHolder holder, String s) {

            }
        };

        mDebtAdapter = new CommonAdapter<String>(this, mDatas, R.layout.item_deposit_detail_expenditure) {
            @Override
            public void convert(ViewHolder holder, String s) {

            }
        };

        mTextView_All.setTextColor(Color.parseColor("#ef5350"));
        mListView_CreditNoteDetail.setFocusable(false);
    }

    @Override
    public void initEvent() {
        mLinearLayout_Back.setOnClickListener(this);
        mLinearLayout_Debt.setOnClickListener(this);
        mLinearLayout_Collection.setOnClickListener(this);
        mLinearLayout_All.setOnClickListener(this);
    }


    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.id_ll_credit_note_detail_back:
                finish();
                break;
            case R.id.id_ll_credit_note_detail_debt:
                resetTextColor();
                mTextView_Debt.setTextColor(Color.parseColor("#ef5350"));
                mListView_CreditNoteDetail.setAdapter(mDebtAdapter);
                break;
            case R.id.id_ll_credit_note_detail_all:
                resetTextColor();
                mTextView_All.setTextColor(Color.parseColor("#ef5350"));
                mListView_CreditNoteDetail.setAdapter(mAllAdapter);
                break;
            case R.id.id_ll_credit_note_detail_collection:
                resetTextColor();
                mTextView_Collection.setTextColor(Color.parseColor("#ef5350"));
                mListView_CreditNoteDetail.setAdapter(mCollectionAdapter);
                break;
            default:
                break;
        }
    }

    private void resetTextColor() {
        mTextView_Debt.setTextColor(Color.parseColor("#bdbdbd"));
        mTextView_Collection.setTextColor(Color.parseColor("#bdbdbd"));
        mTextView_All.setTextColor(Color.parseColor("#bdbdbd"));
    }
}
