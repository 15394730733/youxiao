package com.youxiao.ui.activity.work.stockcheck;

import android.app.AlertDialog;
import android.app.Dialog;
import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.widget.SwipeRefreshLayout;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import com.youxiao.base.BaseActivity;
import com.youxiao.R;
import com.youxiao.adapter.CommonAdapter;
import com.youxiao.adapter.ViewHolder;
import com.youxiao.widget.Util;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 库存核对
 */
public class StockCheckActivity extends BaseActivity implements SwipeRefreshLayout.OnRefreshListener,
        View.OnClickListener{

    private SwipeRefreshLayout mSwipeRefreshLayout;
    private ListView mListView_StockCheck;
    private List<Map<String, String>> mDatas;
    private CommonAdapter<Map<String, String>> mAdapter;

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

    private Runnable mRunnable = new Runnable() {
        @Override
        public void run() {
            mSwipeRefreshLayout.setRefreshing(false);
        }
    };


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_stock_check);
        super.init();
    }

    @Override
    public void initView() {
        mSwipeRefreshLayout = (SwipeRefreshLayout) findViewById(R.id.swipe_refresh_layout);
        mListView_StockCheck = (ListView) findViewById(R.id.lv_stock_check);
        mLinearLayout_Back = (LinearLayout) findViewById(R.id.id_ll_stock_check_back);
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

        mDatas = new ArrayList<>();

        Map<String, String> map = new HashMap<>();
        map.put("isApproved", "已认可");
        map.put("isChecked", "未审核");
        mDatas.add(map);
        map = new HashMap<>();
        map.put("isApproved", "未认可");
        map.put("isChecked", "已审核");
        mDatas.add(map);
        map = new HashMap<>();
        map.put("isApproved", "已认可");
        map.put("isChecked", "已审核");
        mDatas.add(map);
        map = new HashMap<>();
        map.put("isApproved", "未认可");
        map.put("isChecked", "未审核");
        mDatas.add(map);
        map.put("isApproved", "已认可");
        map.put("isChecked", "未审核");
        mDatas.add(map);
        map = new HashMap<>();
        map.put("isApproved", "未认可");
        map.put("isChecked", "已审核");
        mDatas.add(map);
        map = new HashMap<>();
        map.put("isApproved", "已认可");
        map.put("isChecked", "已审核");
        mDatas.add(map);
        map = new HashMap<>();
        map.put("isApproved", "未认可");
        map.put("isChecked", "未审核");
        mDatas.add(map);

        mAdapter = new CommonAdapter<Map<String, String>>(this, mDatas, R.layout.item_stock_check){
            @Override
            public void convert(ViewHolder holder, Map<String, String> map) {
                if (map.get("isApproved").equals("已认可")) {
                    holder.setText(R.id.id_tv_approved, "已认可");
                    holder.setTextColor(R.id.id_tv_approved, Color.parseColor("#bdbdbd"));
                } else if (map.get("isApproved").equals("未认可")) {
                    holder.setText(R.id.id_tv_approved, "未认可");
                    holder.setTextColor(R.id.id_tv_approved, Color.parseColor("#ef5350"));
                }
                if(map.get("isChecked").equals("已审核")){
                    holder.setImageFromResource(R.id.id_iv_approved,R.drawable.yishenhe);
                }else if (map.get("isChecked").equals("未审核")){
                    holder.setImageFromResource(R.id.id_iv_approved,R.drawable.weishenhe);
                }

            }
        };
        mListView_StockCheck.setAdapter(mAdapter);
    }

    @Override
    public void initEvent() {
        mLinearLayout_Back.setOnClickListener(this);
        mLinearLayout_SelectStock.setOnClickListener(this);
        mLinearLayout_SelectStockStock.setOnClickListener(this);
        mTextView_Stock1.setOnClickListener(this);
        mTextView_Stock2.setOnClickListener(this);
        mSwipeRefreshLayout.setOnRefreshListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.id_ll_stock_check_back:
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
            case R.id.id_iv_more:
                View view = LayoutInflater.from(getContext()).inflate(R.layout.item_stock_check_more, null);
                final Dialog dialog = new AlertDialog.Builder(getContext(), R.style.dialog1).create();
                dialog.show();
                dialog.setContentView(view);
                WindowManager.LayoutParams params = dialog.getWindow().getAttributes();
                params.width = Util.getScreenWidth(getContext());
                params.dimAmount = 0.3f;
                params.gravity = Gravity.BOTTOM;
                dialog.getWindow().setAttributes(params);
                view.findViewById(R.id.id_rl_cancel).setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        dialog.dismiss();
                    }
                });
            default:
                break;
        }
    }

    @Override
    public void onRefresh() {
        mHandler.postDelayed(mRunnable,3000);
    }
}
