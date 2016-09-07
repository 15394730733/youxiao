package com.youxiao.ui.activity.statement.expirationdatewarning;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.PopupMenu;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.youxiao.base.BaseActivity;
import com.youxiao.ui.activity.work.customermanager.CustomerSearchActivity;
import com.youxiao.R;
import com.youxiao.adapter.CommonAdapter;
import com.youxiao.adapter.ViewHolder;

import java.util.ArrayList;
import java.util.List;

/**
 * 保质期预警
 */
public class ExpirationDateWarningActivity extends BaseActivity implements View.OnClickListener {


    private ListView mListView_StockCheck;
    private List<String> mDatas;
    private CommonAdapter<String> mAdapter;

    private Animation mDialogAnimation;
    private LinearLayout mLinearLayout_SelectStockStock;
    private View mView_SelectStockShadow;
    private TextView mTextView_Title;
    private LinearLayout mLinearLayout_Back;
    private LinearLayout mLinearLayout_SelectStock;
    private ImageView mImageView_Arrows;
    private TextView mTextView_Stock1;
    private TextView mTextView_Stock2;

    private ImageView mImageView_SearchCustomer;
    private ImageView mImageView_Add;
    private RelativeLayout mPopup;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_expiration_date_warning);
        super.init();
    }

    @Override
    public void initView() {
        mListView_StockCheck = (ListView) findViewById(R.id.lv_expiration_date_warning);
        mLinearLayout_Back = (LinearLayout) findViewById(R.id.id_ll_stock_check_back);
        mLinearLayout_SelectStockStock = (LinearLayout) findViewById(R.id.id_ll_select_stock_stock);
        mView_SelectStockShadow = findViewById(R.id.id_view_select_stock_shadow);
        mLinearLayout_SelectStock = (LinearLayout) findViewById(R.id.ll_select_stock);
        mTextView_Title = (TextView) findViewById(R.id.id_tv_title);
        mImageView_Arrows = (ImageView) findViewById(R.id.id_iv_arrows);
        mTextView_Stock1 = (TextView) findViewById(R.id.id_tv_select_stock_stock_one);
        mTextView_Stock2 = (TextView) findViewById(R.id.id_tv_select_stock_stock_two);
        mImageView_SearchCustomer = (ImageView) findViewById(R.id.iv_search_customer);
        mImageView_Add = (ImageView) findViewById(R.id.iv_add);
        mPopup = (RelativeLayout) findViewById(R.id.popup);
    }

    @Override
    public void initData() {
        mDatas = new ArrayList<>();
        mDatas.add("");
        mDatas.add("");
        mDatas.add("");
        mAdapter = new CommonAdapter<String>(this, mDatas, R.layout.item_expiration_date_warning) {
            @Override
            public void convert(ViewHolder holder, String s) {

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
        mView_SelectStockShadow.setOnClickListener(this);

        mImageView_Add.setOnClickListener(this);
        mImageView_SearchCustomer.setOnClickListener(this);
    }

    @Override
    public void onClick(final View v) {
        switch (v.getId()) {
            case R.id.id_ll_stock_check_back:
                finish();
                break;
            case R.id.iv_add:
                PopupMenu popup = new PopupMenu(this, mPopup);
                popup.getMenuInflater().inflate(R.menu.expiration_date_warning, popup.getMenu());
                popup.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
                    public boolean onMenuItemClick(MenuItem item) {
//                        Snackbar.make(item.getActionView(),item.getTitle(),Snackbar.LENGTH_SHORT).show();
                        return true;
                    }
                });
                popup.show();
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
                mTextView_Title.setText("按最长排序");
                mDialogAnimation = AnimationUtils.loadAnimation(this, R.anim.fake_dialog_layout_exit);
                mLinearLayout_SelectStockStock.setAnimation(mDialogAnimation);
                mLinearLayout_SelectStockStock.setVisibility(View.GONE);
                mView_SelectStockShadow.setVisibility(View.GONE);
                mImageView_Arrows.setImageResource(R.drawable.down);
                break;
            case R.id.id_tv_select_stock_stock_two:
                mTextView_Title.setText("按最短排序");
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
}
