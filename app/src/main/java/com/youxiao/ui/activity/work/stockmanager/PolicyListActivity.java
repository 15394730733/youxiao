//package com.youxiao.ui.activity.workActivity.stockManager;
//
//import android.app.Activity;
//import android.content.Intent;
//import android.graphics.Color;
//import android.os.Bundle;
//import android.view.View;
//import android.view.Window;
//import android.widget.AdapterView;
//import android.widget.LinearLayout;
//import android.widget.ListView;
//import android.widget.TextView;
//
//import com.youxiao.base.BaseActivity;
//import com.youxiao.R;
//import com.youxiao.adapter.ViewHolder;
//
//import java.util.ArrayList;
//import java.util.List;
//
///**
// * 政策列表
// */
//public class PolicyListActivity extends BaseActivity implements View.OnClickListener, AdapterView.OnItemClickListener {
//
//
//    private LinearLayout mLinearLayout_Back;
//    private LinearLayout mLinearLayout_Debt;
//    private LinearLayout mLinearLayout_Collection;
//    private LinearLayout mLinearLayout_All;
//
//    private TextView mTextView_Debt;
//    private TextView mTextView_Collection;
//    private TextView mTextView_All;
//    private CommonAdapter<ClientInfo> mAllAdapter;
//    private CommonAdapter<ClientInfo> mCollectionAdapter;
//    private CommonAdapter<ClientInfo> mDebtAdapter;
//    private ListView mListView_CreditNoteDetail;
//    private List<ClientInfo> mDatas;
//    private List<ClientInfo> mDatas1;
//    private List<ClientInfo> mDatas2;
//    ClientInfo mClientInfo;
//
//
//    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//        requestWindowFeature(Window.FEATURE_NO_TITLE);
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_policy_list);
//        super.init();
//    }
//
//    @Override
//    public void initView() {
//        mLinearLayout_Back = (LinearLayout) findViewById(R.id.id_ll_credit_note_detail_back);
//        mLinearLayout_Debt = (LinearLayout) findViewById(R.id.id_ll_credit_note_detail_debt);
//        mLinearLayout_Collection = (LinearLayout) findViewById(R.id.id_ll_credit_note_detail_collection);
//        mLinearLayout_All = (LinearLayout) findViewById(R.id.id_ll_credit_note_detail_all);
//
//        mTextView_Debt = (TextView) findViewById(R.id.id_tv_credit_note_detail_debt);
//        mTextView_Collection = (TextView) findViewById(R.id.id_tv_credit_note_detail_collection);
//        mTextView_All = (TextView) findViewById(R.id.id_tv_credit_note_detail_all);
//        mListView_CreditNoteDetail = (ListView) findViewById(R.id.id_lv_credit_note_detail);
//    }
//
//    @Override
//    public void initData() {
//
//        mDatas = new ArrayList<>();
//        for (int i = 0; i < 20; i++) {
//            mClientInfo = new ClientInfo("有效", "每件蒙牛果粒多空箱兑QQ星一瓶", "", "", "");
//            mDatas.add(mClientInfo);
//        }
//        mDatas1 = new ArrayList<>();
//        for (int i = 0; i < 20; i++) {
//            if (i % 2 == 0) {
//                mClientInfo = new ClientInfo("有效", "每件蒙牛果粒多空箱兑QQ星一瓶", "", "", "");
//                mDatas1.add(mClientInfo);
//            } else {
//                mClientInfo = new ClientInfo("过期", "每件蒙牛果粒多空箱兑QQ星一瓶", "", "", "");
//                mDatas1.add(mClientInfo);
//            }
//        }
//        mDatas2 = new ArrayList<>();
//        for (int i = 0; i < 20; i++) {
//            mClientInfo = new ClientInfo("过期", "每件蒙牛果粒多空箱兑QQ星一瓶", "", "", "");
//            mDatas2.add(mClientInfo);
//        }
//        mAllAdapter = new CommonAdapter<ClientInfo>(this, mDatas1, R.layout.item_policy_list) {
//            @Override
//            public void convert(final ViewHolder holder, ClientInfo s) {
//                holder.setText(R.id.id_rtv_policy_list, s.getClientName());
//                holder.setText(R.id.id_iv_policy_name, s.getLocation());
//            }
//        };
//        mListView_CreditNoteDetail.setAdapter(mAllAdapter);
//
//        mCollectionAdapter = new CommonAdapter<ClientInfo>(this, mDatas, R.layout.item_policy_list) {
//            @Override
//            public void convert(final ViewHolder holder, ClientInfo s) {
//                holder.setText(R.id.id_rtv_policy_list, s.getClientName());
//                holder.setText(R.id.id_iv_policy_name, s.getLocation());
//            }
//        };
//
//        mDebtAdapter = new CommonAdapter<ClientInfo>(this, mDatas2, R.layout.item_policy_list) {
//            @Override
//            public void convert(ViewHolder holder, ClientInfo s) {
//                holder.setText(R.id.id_rtv_policy_list, s.getClientName());
//                holder.setText(R.id.id_iv_policy_name, s.getLocation());
//            }
//        };
//
//        mTextView_All.setTextColor(Color.parseColor("#ef5350"));
//
//        int requestCode = getIntent().getIntExtra("requestCode", -1);
//        if (requestCode == 0) {
//            for (int i = 0; i < 20; i++) {
//                mClientInfo = new ClientInfo("有效", "每件蒙牛果粒多空箱兑2元", "", "", "");
//                mDatas.add(mClientInfo);
//            }
//            for (int i = 0; i < 20; i++) {
//                if (i % 2 == 0) {
//                    mClientInfo = new ClientInfo("有效", "每件蒙牛果粒多空箱兑2元", "", "", "");
//                    mDatas1.add(mClientInfo);
//                } else {
//                    mClientInfo = new ClientInfo("过期", "每件蒙牛果粒多空箱兑2元", "", "", "");
//                    mDatas1.add(mClientInfo);
//                }
//            }
//
//            for (int i = 0; i < 20; i++) {
//                mClientInfo = new ClientInfo("过期", "每件蒙牛果粒多空箱兑2元", "", "", "");
//                mDatas2.add(mClientInfo);
//            }
//            mCollectionAdapter.notifyDataSetChanged();
//            mAllAdapter.notifyDataSetChanged();
//            mDebtAdapter.notifyDataSetChanged();
//        }
//    }
//
//    @Override
//    public void initEvent() {
//        mLinearLayout_Back.setOnClickListener(this);
//        mLinearLayout_Debt.setOnClickListener(this);
//        mLinearLayout_Collection.setOnClickListener(this);
//        mLinearLayout_All.setOnClickListener(this);
//        mListView_CreditNoteDetail.setOnItemClickListener(this);
//    }
//
//
//    @Override
//    public void onClick(View v) {
//        switch (v.getId()) {
//            case R.id.id_ll_credit_note_detail_back:
//                finish();
//                break;
//            case R.id.id_ll_credit_note_detail_debt:
//                resetTextColor();
//                mTextView_Debt.setTextColor(Color.parseColor("#ef5350"));
//                mListView_CreditNoteDetail.setAdapter(mDebtAdapter);
//                break;
//            case R.id.id_ll_credit_note_detail_all:
//                resetTextColor();
//                mTextView_All.setTextColor(Color.parseColor("#ef5350"));
//                mListView_CreditNoteDetail.setAdapter(mAllAdapter);
//                break;
//            case R.id.id_ll_credit_note_detail_collection:
//                resetTextColor();
//                mTextView_Collection.setTextColor(Color.parseColor("#ef5350"));
//                mListView_CreditNoteDetail.setAdapter(mCollectionAdapter);
//                break;
//            default:
//                break;
//        }
//    }
//
//    private void resetTextColor() {
//        mTextView_Debt.setTextColor(Color.parseColor("#bdbdbd"));
//        mTextView_Collection.setTextColor(Color.parseColor("#bdbdbd"));
//        mTextView_All.setTextColor(Color.parseColor("#bdbdbd"));
//    }
//
//    @Override
//    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
//        Intent intent = new Intent();
//        intent.putExtra("policyName", mDatas.get(position).getLocation());
//        setResult(Activity.RESULT_OK, intent);
//        finish();
//    }
//}
