package com.youxiao.ui.activity.statement.stockeerlywarning;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.widget.SwipeRefreshLayout;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import com.youxiao.R;
import com.youxiao.adapter.CommonAdapter;
import com.youxiao.adapter.ViewHolder;
import com.youxiao.base.BaseActivity;
import com.youxiao.ui.activity.work.customermanager.CustomerSearchActivity;

import java.util.ArrayList;
import java.util.List;

/**
 * 库存预警
 */
public class StockEarlyWarningActivity extends BaseActivity implements View.OnClickListener,
        SwipeRefreshLayout.OnRefreshListener {


    private SwipeRefreshLayout mSwipeRefreshLayout;
    private ListView mListView_CurrentStock;
    private ImageView mImageView_SearchCustomer;

    private Animation mDialogAnimation;
    private LinearLayout mLinearLayout_SelectStockStock;
    private View mView_SelectStockShadow;
    private TextView mTextView_Title;
    private LinearLayout mLinearLayout_Back;
    private LinearLayout mLinearLayout_SelectStock;
    private ImageView mImageView_Arrows;
    private TextView mTextView_Stock1;
    private TextView mTextView_Stock2;


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
        setContentView(R.layout.activity_stock_early_warning);
        super.init();
    }

    @Override
    public void initView() {
        mLinearLayout_Back = (LinearLayout) findViewById(R.id.id_ll_stock_early_warning_back);
        mSwipeRefreshLayout = (SwipeRefreshLayout) findViewById(R.id.swipe_refresh_layout);
        mListView_CurrentStock = (ListView) findViewById(R.id.lv_stock_early_warning);
        mImageView_SearchCustomer = (ImageView) findViewById(R.id.iv_search_customer);

        mLinearLayout_SelectStockStock = (LinearLayout) findViewById(R.id.id_ll_select_stock_stock);
        mView_SelectStockShadow = findViewById(R.id.id_view_select_stock_shadow);
        mLinearLayout_SelectStock = (LinearLayout) findViewById(R.id.ll_select_stock);
        mTextView_Title = (TextView) findViewById(R.id.id_tv_title);
        mImageView_Arrows = (ImageView) findViewById(R.id.id_iv_arrows);
        mTextView_Stock1 = (TextView) findViewById(R.id.id_tv_select_stock_stock_one);
        mTextView_Stock2 = (TextView) findViewById(R.id.id_tv_select_stock_stock_two);
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

        mLinearLayout_SelectStock.setOnClickListener(this);
        mLinearLayout_SelectStockStock.setOnClickListener(this);
        mTextView_Stock1.setOnClickListener(this);
        mTextView_Stock2.setOnClickListener(this);
        mView_SelectStockShadow.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.id_ll_stock_early_warning_back:
                finish();
                break;
            case R.id.iv_search_customer:
                Intent intent = new Intent();
                intent.setClass(this, CustomerSearchActivity.class);
                startActivity(intent);
                break;
            case R.id.ll_select_stock:
                if (mLinearLayout_SelectStockStock.getVisibility() == View.GONE) {
                    mDialogAnimation = AnimationUtils.loadAnimation(this, R.anim.fake_dialog_layout_enter);
                    mLinearLayout_SelectStockStock.setAnimation(mDialogAnimation);
                    mLinearLayout_SelectStockStock.setVisibility(View.VISIBLE);
                    mView_SelectStockShadow.setVisibility(View.VISIBLE);
                    mImageView_Arrows.setImageResource(R.drawable.top);
                } else {
                    mDialogAnimation = AnimationUtils.loadAnimation(this, R.anim.fake_dialog_layout_exit);
                    mLinearLayout_SelectStockStock.setAnimation(mDialogAnimation);
                    mLinearLayout_SelectStockStock.setVisibility(View.GONE);
                    mView_SelectStockShadow.setVisibility(View.GONE);
                    mImageView_Arrows.setImageResource(R.drawable.down);
                }
                break;
            case R.id.id_view_select_stock_shadow:
                mDialogAnimation = AnimationUtils.loadAnimation(this, R.anim.fake_dialog_layout_exit);
                mLinearLayout_SelectStockStock.setAnimation(mDialogAnimation);
                mLinearLayout_SelectStockStock.setVisibility(View.GONE);
                mView_SelectStockShadow.setVisibility(View.GONE);
                mImageView_Arrows.setImageResource(R.drawable.down);
                break;
            case R.id.id_tv_select_stock_stock_one:
                mTextView_Title.setText("按负数排序");
                mDialogAnimation = AnimationUtils.loadAnimation(this, R.anim.fake_dialog_layout_exit);
                mLinearLayout_SelectStockStock.setAnimation(mDialogAnimation);
                mLinearLayout_SelectStockStock.setVisibility(View.GONE);
                mView_SelectStockShadow.setVisibility(View.GONE);
                mImageView_Arrows.setImageResource(R.drawable.down);
                break;
            case R.id.id_tv_select_stock_stock_two:
                mTextView_Title.setText("按正数排序");
                mDialogAnimation = AnimationUtils.loadAnimation(this, R.anim.fake_dialog_layout_exit);
                mLinearLayout_SelectStockStock.setAnimation(mDialogAnimation);
                mLinearLayout_SelectStockStock.setVisibility(View.GONE);
                mView_SelectStockShadow.setVisibility(View.GONE);
                mImageView_Arrows.setImageResource(R.drawable.down);
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
