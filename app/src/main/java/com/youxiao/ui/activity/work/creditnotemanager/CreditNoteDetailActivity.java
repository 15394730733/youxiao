package com.youxiao.ui.activity.work.creditnotemanager;

import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.AdapterView;
import android.widget.CheckBox;
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
 * 欠条明细
 *
 * @author StomHong
 * @since 2016-7-28
 */
public class CreditNoteDetailActivity extends BaseActivity implements View.OnClickListener, AdapterView.OnItemClickListener {

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
    private List<Integer> mPos = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_credit_note_detail);
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
                mDatas1.add("收款:+1,000.00");
            }else {
                mDatas1.add("欠款:-1,000.00");
            }
        }
        mAllAdapter = new CommonAdapter<String>(this, mDatas1, R.layout.item_credit_note_detail) {
            @Override
            public void convert(final ViewHolder holder, String s) {
                CheckBox checkBox = holder.getView(R.id.id_iv_credit_note_detail_checked);
                checkBox.setButtonDrawable(R.drawable.unselected);
                if (mPos.contains(holder.getPosition())) {
                    checkBox.setButtonDrawable(R.drawable.commit);
                }
            }
        };
        mListView_CreditNoteDetail.setAdapter(mAllAdapter);

        mCollectionAdapter = new CommonAdapter<String>(this, mDatas, R.layout.item_credit_note_detail) {
            @Override
            public void convert(final ViewHolder holder, String s) {
                CheckBox checkBox = holder.getView(R.id.id_iv_credit_note_detail_checked);
                checkBox.setButtonDrawable(R.drawable.unselected);
                if (mPos.contains(holder.getPosition())) {
                    checkBox.setButtonDrawable(R.drawable.commit);
                }
            }
        };



        mDebtAdapter = new CommonAdapter<String>(this, mDatas, R.layout.item_credit_note_detail_debt) {
            @Override
            public void convert(ViewHolder holder, String s) {
                CheckBox checkBox = holder.getView(R.id.id_iv_credit_note_detail_checked);
                checkBox.setButtonDrawable(R.drawable.unselected);
                if (mPos.contains(holder.getPosition())) {
                    checkBox.setButtonDrawable(R.drawable.commit);
                }
            }
        };

        mTextView_All.setTextColor(Color.parseColor("#ef5350"));
    }

    @Override
    public void initEvent() {
        mLinearLayout_Back.setOnClickListener(this);
        mLinearLayout_Debt.setOnClickListener(this);
        mLinearLayout_Collection.setOnClickListener(this);
        mLinearLayout_All.setOnClickListener(this);
        mListView_CreditNoteDetail.setOnItemClickListener(this);
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
                mPos.clear();
                break;
            case R.id.id_ll_credit_note_detail_all:
                resetTextColor();
                mTextView_All.setTextColor(Color.parseColor("#ef5350"));
                mListView_CreditNoteDetail.setAdapter(mAllAdapter);
                mPos.clear();
                break;
            case R.id.id_ll_credit_note_detail_collection:
                resetTextColor();
                mTextView_Collection.setTextColor(Color.parseColor("#ef5350"));
                mListView_CreditNoteDetail.setAdapter(mCollectionAdapter);
                mPos.clear();
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

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        if (mPos != null) {
            CheckBox checkBox = (CheckBox) view.findViewById(R.id.id_iv_credit_note_detail_checked);
            if (mPos.contains(position)) {

                checkBox.setButtonDrawable(R.drawable.unselected);
                mPos.remove((Integer) position);
            } else {
                checkBox.setButtonDrawable(R.drawable.commit);
                mPos.add(position);
            }
        }


    }
}
