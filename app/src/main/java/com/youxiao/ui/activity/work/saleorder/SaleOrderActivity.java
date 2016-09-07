package com.youxiao.ui.activity.work.saleorder;

import android.os.Bundle;
import android.os.Handler;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.youxiao.R;
import com.youxiao.adapter.SalesOrderAdapter;
import com.youxiao.base.BaseActivity;
import com.youxiao.widget.FakeDialogLayout;

import java.util.ArrayList;
import java.util.List;

/**
 * 销售订单
 *
 * @author StomHong
 * @since 2016-7-14
 */
public class SaleOrderActivity extends BaseActivity implements View.OnClickListener,
        SwipeRefreshLayout.OnRefreshListener,FakeDialogLayout.ViewVisible,FakeDialogLayout.ItemViewClick {

    private LinearLayout mLinearLayout_Back;
    private LinearLayout mLinearLayout_OrderFilter;
    private LinearLayout mLinearLayout_SelectBill;

    private ImageView mImageView_Multi_Select;
    private ImageView mImageView_Commit;

    private List<String> mData;

    private RecyclerView mRecyclerView_Bill;
    private FakeDialogLayout mFakeDialogLayout;

    private SwipeRefreshLayout mSwipeRefreshLayout;
    private SalesOrderAdapter mSalesOrderAdapter;


    private Handler mHandler = new Handler();
    private Runnable mRunnable = new Runnable() {
        @Override
        public void run() {
            mSwipeRefreshLayout.setRefreshing(false);
        }
    };


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sale_order);

        super.init();
    }


    @Override
    public void initView() {
        mLinearLayout_Back = (LinearLayout) findViewById(R.id.id_ll_sale_order_back);
        mLinearLayout_OrderFilter = (LinearLayout) findViewById(R.id.id_ll_sale_order_filter);
        mRecyclerView_Bill = (RecyclerView) findViewById(R.id.id_rv_sale_order);

        mImageView_Commit = (ImageView) findViewById(R.id.id_iv_sale_order_commit);
        mImageView_Multi_Select = (ImageView) findViewById(R.id.id_iv_multi_select);

        mSwipeRefreshLayout = (SwipeRefreshLayout) findViewById(R.id.swipe_refresh_layout);
        mFakeDialogLayout = (FakeDialogLayout) findViewById(R.id.fakedialoglayout);
    }

    @Override
    public void initData() {
        mData = new ArrayList<>();
        for (int i = 0; i < 15; i++) {
            if (i % 2 == 0) {
                mData.add("已生成调拨单");
            } else {
                mData.add("未生成调拨单");
            }
        }
        mSalesOrderAdapter = new SalesOrderAdapter(this, mData);
        mRecyclerView_Bill.setAdapter(mSalesOrderAdapter);

        // 创建一个线性布局管理器
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        // 设置布局管理器
        mRecyclerView_Bill.setLayoutManager(layoutManager);

        mSwipeRefreshLayout.setColorSchemeResources(R.color.red400);

        List<String> data = new ArrayList<>();
        data.add("今日单据");
        data.add("已使用");
        data.add("未使用");
        mFakeDialogLayout.setListData(data);
    }

    @Override
    public void initEvent() {
        mSwipeRefreshLayout.setOnRefreshListener(this);

        mImageView_Multi_Select.setOnClickListener(this);
        mImageView_Commit.setOnClickListener(this);
        mLinearLayout_Back.setOnClickListener(this);
        mLinearLayout_OrderFilter.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.id_ll_sale_order_back:
                finish();
                break;
            case R.id.id_ll_sale_order_filter:
                if (mFakeDialogLayout.getVisibility() == View.GONE) {
                    mFakeDialogLayout.show(this, this);
                } else {
                    mFakeDialogLayout.hide();
                }
                break;
            case R.id.id_iv_sale_order_commit:
                break;
            case R.id.id_iv_multi_select:
                break;
            default:
                break;
        }
    }


    @Override
    public void onRefresh() {
        mHandler.postDelayed(mRunnable, 3000);
    }

    @Override
    public void onItemViewClick() {

    }

    @Override
    public void onViewVisible(boolean visible) {

    }
}
