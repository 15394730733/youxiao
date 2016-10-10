package com.youxiao.ui.activity.sales.settlement;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;

import com.youxiao.R;
import com.youxiao.base.BaseActivity;
import com.youxiao.ui.activity.work.creditnotemanager.CreditNoteCollectionActivity;
import com.youxiao.ui.activity.work.depositmanager.DepositOperationActivity;
import com.youxiao.ui.activity.work.displaymanager.DisplayOperationActivity;
import com.youxiao.ui.activity.work.rebatemanager.RebateOperationActivity;
import com.youxiao.ui.activity.work.scoremanager.ScoreManagerActivity;

/**
 * 结算
 */
public class SettlementActivity extends BaseActivity implements View.OnClickListener {

    private LinearLayout mLinearLayout_Back;
    private RelativeLayout mRelativeLayout_DepositManager;
    private RelativeLayout mRelativeLayout_DisplayManager;
    private RelativeLayout mRelativeLayout_RebateManager;
    private RelativeLayout mRelativeLayout_CreditNoteManager;
    private RelativeLayout mRelativeLayout_ScoreManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settlement);
        super.init();
    }

    @Override
    public void initView() {
        mRelativeLayout_CreditNoteManager = (RelativeLayout) findViewById(R.id.rl_credit_note_manager);
        mRelativeLayout_DepositManager = (RelativeLayout) findViewById(R.id.rl_deposit_manager);
        mRelativeLayout_DisplayManager = (RelativeLayout) findViewById(R.id.rl_display_manager);
        mRelativeLayout_RebateManager = (RelativeLayout) findViewById(R.id.rl_rebate_manager);
        mRelativeLayout_ScoreManager = (RelativeLayout) findViewById(R.id.rl_score_manager);
        mLinearLayout_Back = (LinearLayout) findViewById(R.id.ll_back);
    }

    @Override
    public void initData() {
        mRelativeLayout_DepositManager.setOnClickListener(this);
        mRelativeLayout_DisplayManager.setOnClickListener(this);
        mRelativeLayout_RebateManager.setOnClickListener(this);
        mRelativeLayout_CreditNoteManager.setOnClickListener(this);
        mRelativeLayout_ScoreManager.setOnClickListener(this);
        mLinearLayout_Back.setOnClickListener(this);
    }

    @Override
    public void initEvent() {

    }

    @Override
    public void onClick(View v) {
        Intent intent = new Intent();
        switch (v.getId()) {
            case R.id.ll_back:
                finish();
                break;
            case R.id.rl_deposit_manager:
                intent.setClass(this, DepositOperationActivity.class);
                startActivity(intent);
                break;
            case R.id.rl_display_manager:
                intent.setClass(this, DisplayOperationActivity.class);
                startActivity(intent);
                break;
            case R.id.rl_rebate_manager:
                intent.setClass(this, RebateOperationActivity.class);
                startActivity(intent);
                break;
            case R.id.rl_credit_note_manager:
                intent.setClass(this, CreditNoteCollectionActivity.class);
                startActivity(intent);
                break;
            case R.id.rl_score_manager:
                intent.setClass(this, ScoreManagerActivity.class);
                startActivity(intent);
                break;


        }
    }
}
