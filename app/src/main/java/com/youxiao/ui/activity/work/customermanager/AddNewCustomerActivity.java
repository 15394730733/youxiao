package com.youxiao.ui.activity.work.customermanager;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.youxiao.R;
import com.youxiao.base.BaseActivity;

/**
 * 新增客户
 */
public class AddNewCustomerActivity extends BaseActivity implements View.OnClickListener {

    private LinearLayout mLinearLayout_Back;

    private EditText mEditText_CustomerCard;
    private EditText mEditText_CustomerName;

    private EditText mEditText_CustomerTelephone;
    private EditText mEditText_Contacts;
    private EditText mEditText_MobilePhoneA;
    private EditText mEditText_MobilePhoneB;
    private EditText mEditText_DisplayArea;
    private EditText mEditText_OperatingArea;

    private TextView mTextView_CustomerAddress;
    private TextView mTextView_DistributionRoute;
    private TextView mTextView_PriceType;
    private TextView mTextView_CustomerArea;
    private TextView mTextView_DisplayWay;
    private TextView mTextView_AccountWay;
    private TextView mTextView_CustomerType;

    private LinearLayout mLinearLayout_DistributionRoute;
    private LinearLayout mLinearLayout_PriceType;
    private LinearLayout mLinearLayout_CustomerArea;
    private LinearLayout mLinearLayout_DisplayWay;
    private LinearLayout mLinearLayout_AccountWay;
    private LinearLayout mLinearLayout_CustomerType;

    private TextView mTextView_CurrentPosition;
    private TextView mTextView_Submit;

    private static final int SELECT_ACCOUNT_WAY = 201;
    private static final int SELECT_CUSTOMER_AREA = 202;
    private static final int SELECT_CUSTOMER_TYPE = 203;
    private static final int SELECT_DISPLAY_WAY = 204;
    private static final int SELECT_DISTRIBUTION_ROUTE = 205;
    private static final int SELECT_PRICE_TYPE = 206;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_client);

        super.init();

    }

    @Override
    public void initView() {

        mLinearLayout_Back = (LinearLayout) findViewById(R.id.id_lay_add_client_back);

    }

    @Override
    public void initData() {

//        if (GLOBAL.LOCATION.getAddrStr() != null) {
//            mTextView_CurrentPosition.setText(GLOBAL.LOCATION.getAddrStr());
//            mTextView_CurrentPosition.getPaint().setFlags(Paint.LINEAR_TEXT_FLAG);
//            mTextView_CustomerAddress.setText(GLOBAL.LOCATION.getAddrStr());
//        }


    }

    @Override
    public void initEvent() {

        mLinearLayout_Back.setOnClickListener(this);
        mTextView_Submit.setOnClickListener(this);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (resultCode == Activity.RESULT_OK) {
            String datas = (String) data.getExtras().get("data");
            switch (requestCode) {
                case SELECT_ACCOUNT_WAY:
                    mTextView_AccountWay.setText(datas);
                    break;
                case SELECT_CUSTOMER_AREA:
                    mTextView_CustomerArea.setText(datas);
                    break;
                case SELECT_CUSTOMER_TYPE:
                    mTextView_CustomerType.setText(datas);
                    break;
                case SELECT_DISPLAY_WAY:
                    mTextView_DisplayWay.setText(datas);
                    break;
                case SELECT_DISTRIBUTION_ROUTE:
                    mTextView_DistributionRoute.setText(datas);
                    break;
                case SELECT_PRICE_TYPE:
                    mTextView_PriceType.setText(datas);
                    break;
            }
        }

    }

    @Override
    public void onClick(View v) {
        Intent intent = new Intent();
        switch (v.getId()) {
            case R.id.id_lay_add_client_back:
                finish();
                break;
            case R.id.id_tv_add_client_submit:
                break;
            case R.id.id_et_add_client_customer_card:
                break;
            default:
                break;
        }
    }

}
