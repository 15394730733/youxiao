package com.youxiao.ui.activity.work.saleorder;

import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.LinearLayout;
import android.widget.ListView;

import com.youxiao.R;
import com.youxiao.adapter.CommonAdapter;
import com.youxiao.adapter.ViewHolder;
import com.youxiao.base.BaseActivity;
import com.youxiao.util.SetListViewHeight;

import java.util.ArrayList;
import java.util.List;

public class BillDetailActivity extends BaseActivity {


    private ListView mListView_BillDetail;
    private List<String> mProcurementOrders;
    private CommonAdapter<String> mAdapter;
    private LinearLayout mLinearLayout_Back;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bill_detail);

        super.init();
    }

    @Override
    public void initView() {
        mListView_BillDetail = (ListView) findViewById(R.id.id_lv_bill_detail);
        mLinearLayout_Back = (LinearLayout) findViewById(R.id.id_ll_bill_detail_back);
    }

    @Override
    public void initData() {
        mProcurementOrders = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            mProcurementOrders.add("");
        }
        mAdapter = new CommonAdapter<String>(this, mProcurementOrders, R.layout.item_procurement) {
            @Override
            public void convert(ViewHolder holder, String procurement) {

            }

        };
        mListView_BillDetail.setAdapter(mAdapter);
        SetListViewHeight.setListViewHeightBasedOnChildren(mListView_BillDetail);
    }

    @Override
    public void initEvent() {
        mLinearLayout_Back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }
}
