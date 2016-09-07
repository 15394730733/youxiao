package com.youxiao.ui.activity.work.entrepotcheck;

import android.app.AlertDialog;
import android.app.Dialog;
import android.os.Bundle;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.youxiao.R;
import com.youxiao.base.BaseActivity;
import com.youxiao.widget.Util;

/**
 * 仓库盘点
 */
public class EntrepotCheckActivity extends BaseActivity implements View.OnClickListener {


    private Animation mDialogAnimation;
    private LinearLayout mLinearLayout_SelectStockStock;
    private View mView_SelectStockShadow;
    private TextView mTextView_Title;
    private LinearLayout mLinearLayout_Back;
    private LinearLayout mLinearLayout_SelectStock;
    private ImageView mImageView_Arrows;
    private TextView mTextView_Stock1;
    private TextView mTextView_Stock2;
    private ImageView mImageView_More;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_entrepot_check);
        super.init();
    }

    @Override
    public void initView() {
        mLinearLayout_SelectStockStock = (LinearLayout) findViewById(R.id.id_ll_select_stock_stock);
        mView_SelectStockShadow = findViewById(R.id.id_view_select_stock_shadow);
        mLinearLayout_Back = (LinearLayout) findViewById(R.id.id_ll_stock_check_back);
        mLinearLayout_SelectStock = (LinearLayout) findViewById(R.id.ll_select_stock);
        mTextView_Title = (TextView) findViewById(R.id.id_tv_title);
        mImageView_Arrows = (ImageView) findViewById(R.id.id_iv_arrows);
        mTextView_Stock1 = (TextView) findViewById(R.id.id_tv_select_stock_stock_one);
        mTextView_Stock2 = (TextView) findViewById(R.id.id_tv_select_stock_stock_two);
        mImageView_More = (ImageView) findViewById(R.id.id_iv_more);

    }

    @Override
    public void initData() {

    }

    @Override
    public void initEvent() {
        mLinearLayout_Back.setOnClickListener(this);
        mLinearLayout_SelectStock.setOnClickListener(this);
        mLinearLayout_SelectStockStock.setOnClickListener(this);
        mTextView_Stock1.setOnClickListener(this);
        mTextView_Stock2.setOnClickListener(this);
        mImageView_More.setOnClickListener(this);
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

}
