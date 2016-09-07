package com.youxiao.ui.activity.statement.currentstock;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.widget.SwipeRefreshLayout;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;

import com.youxiao.R;
import com.youxiao.adapter.CommonAdapter;
import com.youxiao.adapter.ViewHolder;
import com.youxiao.base.BaseActivity;
import com.youxiao.ui.activity.work.customermanager.CustomerSearchActivity;

import java.util.ArrayList;
import java.util.List;

/**
 * 即时库存
 */
public class CurrentStockActivity extends BaseActivity implements View.OnClickListener,
        SwipeRefreshLayout.OnRefreshListener {

    private LinearLayout mLinearLayout_Back;
    private SwipeRefreshLayout mSwipeRefreshLayout;
    private ListView mListView_CurrentStock;
    private ImageView mImageView_SearchCustomer;

    private Handler mHandler = new Handler();
    private final Runnable mRefreshDone = new Runnable() {

        @Override
        public void run() {
            mSwipeRefreshLayout.setRefreshing(false);
        }

    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_current_stock);
        super.init();
    }

    @Override
    public void initView() {
        mLinearLayout_Back = (LinearLayout) findViewById(R.id.id_ll_current_stock_back);
        mSwipeRefreshLayout = (SwipeRefreshLayout) findViewById(R.id.swipe_refresh_layout);
        mListView_CurrentStock = (ListView) findViewById(R.id.lv_current_stock);
        mImageView_SearchCustomer = (ImageView) findViewById(R.id.iv_search_customer);
    }

    @Override
    public void initData() {
        mSwipeRefreshLayout.setColorSchemeResources(R.color.red400);
        List<String> mData = new ArrayList<>();
        for (int i = 0; i < 15; i++) {
            mData.add("");
        }
        CommonAdapter<String> mAdapter = new CommonAdapter<String>(this, mData, R.layout.item_current_stock) {
            @Override
            public void convert(ViewHolder holder, String s) {

            }
        };
        mListView_CurrentStock.setAdapter(mAdapter);
    }

    @Override
    public void initEvent() {
        mLinearLayout_Back.setOnClickListener(this);
        mSwipeRefreshLayout.setOnRefreshListener(this);
        mImageView_SearchCustomer.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.id_ll_current_stock_back:
                finish();
                break;
            case R.id.iv_search_customer:
                Intent intent = new Intent();
                intent.setClass(this, CustomerSearchActivity.class);
                startActivity(intent);
                break;
            default:
                break;
        }
    }

    @Override
    public void onRefresh() {
        refresh();
    }

    private void refresh() {
        mHandler.postDelayed(mRefreshDone, 3000);
    }
}
