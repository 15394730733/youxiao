package com.youxiao.ui.activity.work.allocationcheck;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.view.GestureDetectorCompat;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.GestureDetector;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.youxiao.base.BaseActivity;
import com.youxiao.R;

import java.util.ArrayList;
import java.util.List;

/**
 * 调拨审核
 */
public class AllocationCheckActivity extends BaseActivity implements View.OnClickListener,SwipeRefreshLayout.OnRefreshListener {


    private Animation mDialogAnimation;
    private LinearLayout mLinearLayout_SelectStockStock;
    private View mView_SelectStockShadow;
    private TextView mTextView_Title;
    private LinearLayout mLinearLayout_Back;
    private LinearLayout mLinearLayout_SelectStock;
    private ImageView mImageView_Arrows;
    private TextView mTextView_Stock1;
    private TextView mTextView_Stock2;
    private List<String> mDataset;
    private MyAdapter mAdapter;
    private RecyclerView mRecyclerView_Commodity;

    private SwipeRefreshLayout mSwipeRefreshLayout;


    private Handler mHandler = new Handler();
    private final Runnable mRefreshDone = new Runnable() {

        @Override
        public void run() {
            mSwipeRefreshLayout.setRefreshing(false);
        }

    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_allocation_check);
        super.init();
    }

    @Override
    public void initView() {
        mLinearLayout_SelectStockStock = (LinearLayout) findViewById(R.id.id_ll_select_stock_stock);
        mView_SelectStockShadow = findViewById(R.id.id_view_select_stock_shadow);
        mLinearLayout_Back = (LinearLayout) findViewById(R.id.id_ll_commodity_allocation_back);
        mLinearLayout_SelectStock = (LinearLayout) findViewById(R.id.ll_select_stock);
        mTextView_Title = (TextView) findViewById(R.id.id_tv_title);
        mImageView_Arrows = (ImageView) findViewById(R.id.id_iv_arrows);
        mTextView_Stock1 = (TextView) findViewById(R.id.id_tv_select_stock_stock_one);
        mTextView_Stock2 = (TextView) findViewById(R.id.id_tv_select_stock_stock_two);
        mRecyclerView_Commodity = (RecyclerView) findViewById(R.id.id_lv_commodity);
        mSwipeRefreshLayout = (SwipeRefreshLayout) findViewById(R.id.swipe_refresh_widget);
    }

    @Override
    public void initData() {
        mDataset = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            mDataset.add("");
        }
        mAdapter = new MyAdapter();
        // 创建一个线性布局管理器
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        // 设置布局管理器
        mRecyclerView_Commodity.setLayoutManager(layoutManager);

        mRecyclerView_Commodity.setAdapter(mAdapter);

        mSwipeRefreshLayout = (SwipeRefreshLayout) findViewById(R.id.swipe_refresh_widget);
        mSwipeRefreshLayout.setColorSchemeResources(R.color.red400);
        mSwipeRefreshLayout.setOnRefreshListener(this);
    }

    @Override
    public void initEvent() {
        mLinearLayout_Back.setOnClickListener(this);
        mLinearLayout_SelectStock.setOnClickListener(this);
        mLinearLayout_SelectStockStock.setOnClickListener(this);
        mTextView_Stock1.setOnClickListener(this);
        mTextView_Stock2.setOnClickListener(this);
//        mRecyclerView_Commodity.addOnItemTouchListener(new MyOnItemTouchListener(mRecyclerView_Commodity) {
//
//
//            @Override
//            public void onItemClick(RecyclerView.ViewHolder vh) {
//                Intent intent = new Intent();
//                intent.setClass(AllocationCheckActivity.this,AllocationBillActivity.class);
//                startActivity(intent);
//            }
//        });
    }


    @Override
    public void onClick(View v) {
        Intent intent = new Intent();
        switch (v.getId()) {
            case R.id.id_ll_commodity_allocation_back:
                finish();
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
                mTextView_Title.setText("仓库1");
                mDialogAnimation = AnimationUtils.loadAnimation(this, R.anim.fake_dialog_layout_exit);
                mLinearLayout_SelectStockStock.setAnimation(mDialogAnimation);
                mLinearLayout_SelectStockStock.setVisibility(View.GONE);
                mView_SelectStockShadow.setVisibility(View.GONE);
                mImageView_Arrows.setImageResource(R.drawable.down);
                break;
            case R.id.id_tv_select_stock_stock_two:
                mTextView_Title.setText("仓库2");
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

    private class MyAdapter extends RecyclerView.Adapter<MyAdapter.MyViewHolder> {


        @Override
        public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            // 创建一个View，简单起见直接使用系统提供的布局，就是一个TextView
            View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_allocation_check, parent, false);
            // 创建一个ViewHolder
            return new MyViewHolder(view);

        }

        @Override
        public void onBindViewHolder(MyViewHolder holder, int position) {
            // 绑定数据到ViewHolder上
//            holder.mTextView.setText(mDataset[position]);
        }

        @Override
        public int getItemCount() {
            return mDataset.size();
        }


        public class MyViewHolder extends RecyclerView.ViewHolder {
            public TextView mTextView;

            public MyViewHolder(View itemView) {
                super(itemView);
//                mTextView = (TextView) itemView;
            }
        }
    }

    public abstract class MyOnItemTouchListener implements RecyclerView.OnItemTouchListener {

        private GestureDetectorCompat mGestureDetectorCompat;
        private RecyclerView mRecyclerView;

        public MyOnItemTouchListener(RecyclerView recyclerView) {
            mRecyclerView = recyclerView;
            mGestureDetectorCompat = new GestureDetectorCompat(mRecyclerView.getContext(),
                    new MyGestureListener());
        }

        @Override
        public void onTouchEvent(RecyclerView rv, MotionEvent e) {
            mGestureDetectorCompat.onTouchEvent(e);
        }

        @Override
        public boolean onInterceptTouchEvent(RecyclerView rv, MotionEvent e) {
            mGestureDetectorCompat.onTouchEvent(e);
            return false;
        }

        public abstract void onItemClick(RecyclerView.ViewHolder vh);


        private class MyGestureListener extends GestureDetector.SimpleOnGestureListener {
            @Override
            public boolean onSingleTapUp(MotionEvent e) {
                View child = mRecyclerView_Commodity.findChildViewUnder(e.getX(), e.getY());
                if (child != null) {
                    RecyclerView.ViewHolder VH = mRecyclerView_Commodity.getChildViewHolder(child);
                    onItemClick(VH);
                }
                return true;
            }

            @Override
            public void onLongPress(MotionEvent e) {
//                View child = mRecyclerView_Bill.findChildViewUnder(e.getX(), e.getY());
//                if (child != null) {
//                    RecyclerView.ViewHolder VH = mRecyclerView_Bill.getChildViewHolder(child);
//                    onItemClick(VH);
//                }
            }


        }
    }

    private void refresh() {
        mHandler.removeCallbacks(mRefreshDone);
        mHandler.postDelayed(mRefreshDone, 1000);
    }
}
