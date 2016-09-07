package com.youxiao.ui.activity.work.contractsignature;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;

import com.youxiao.R;
import com.youxiao.base.BaseActivity;

/**
 * 费用类别
 *
 * @author StomHong
 * @since 2016-8-3
 */
public class ChargeTypeActivity extends BaseActivity implements View.OnClickListener {

    private LinearLayout mLinearLayout_Back;
    private LinearLayout mLinearLayout_StandeesCharge;
    private LinearLayout mLinearLayout_DisplayCharge;
    private LinearLayout mLinearLayout_MonopolyCharge;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_charge_type);

        super.init();
    }

    @Override
    public void initView() {
        mLinearLayout_Back = (LinearLayout) findViewById(R.id.id_ll_charge_type_back);
        mLinearLayout_StandeesCharge = (LinearLayout) findViewById(R.id.id_ll_charge_type_standees_charge);
        mLinearLayout_DisplayCharge = (LinearLayout) findViewById(R.id.id_ll_charge_type_display_charge);
        mLinearLayout_MonopolyCharge = (LinearLayout) findViewById(R.id.id_ll_charge_type_monopoly_charge);
    }

    @Override
    public void initData() {

    }

    @Override
    public void initEvent() {
        mLinearLayout_Back.setOnClickListener(this);
        mLinearLayout_StandeesCharge.setOnClickListener(this);
        mLinearLayout_DisplayCharge.setOnClickListener(this);
        mLinearLayout_MonopolyCharge.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        Intent intent = new Intent();
        switch (v.getId()) {
            case R.id.id_ll_charge_type_display_charge:
                intent.putExtra("chargeType", "陈列费");
                setResult(Activity.RESULT_OK, intent);
                finish();
                break;
            case R.id.id_ll_charge_type_monopoly_charge:
                intent.putExtra("chargeType", "专营费");
                setResult(Activity.RESULT_OK, intent);
                finish();
                break;
            case R.id.id_ll_charge_type_standees_charge:
                intent.putExtra("chargeType", "堆头费");
                setResult(Activity.RESULT_OK, intent);
                finish();
                break;
            case R.id.id_ll_charge_type_back:
                finish();
                break;
            default:
                break;
        }
    }
}
