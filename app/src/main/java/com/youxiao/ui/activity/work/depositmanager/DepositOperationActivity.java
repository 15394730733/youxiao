package com.youxiao.ui.activity.work.depositmanager;

import android.content.Intent;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.view.Window;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.youxiao.R;
import com.youxiao.base.BaseActivity;
import com.youxiao.util.HideInputMethod;

/**
 * 存款操作
 *
 * @author StomHong
 * @since 2016-8-6
 */
public class DepositOperationActivity extends BaseActivity implements View.OnClickListener, View.OnTouchListener {

    private LinearLayout mLinearLayout_Back;
    private ImageView mImageView_Switch;
    private EditText mEditText_Collection;
    private EditText mEditeText_Remark;
    private TextView mTextView_Balance;
    private TextView mTextView_Customer;

    private LinearLayout mLinearLayout_NumberZero;
    private LinearLayout mLinearLayout_NumberOne;
    private LinearLayout mLinearLayout_NumberTwo;
    private LinearLayout mLinearLayout_NumberThree;
    private LinearLayout mLinearLayout_NumberFour;
    private LinearLayout mLinearLayout_NumberFive;
    private LinearLayout mLinearLayout_NumberSix;
    private LinearLayout mLinearLayout_NumberSeven;
    private LinearLayout mLinearLayout_NumberEight;
    private LinearLayout mLinearLayout_NumberNine;
    private LinearLayout mLinearLayout_delete;
    private LinearLayout mLinearLayout_Keyboard;
    private Animation mAnimation;
    private LinearLayout mLinearLayout_DepositType;
    private LinearLayout mLinearLayout_DepositTypeA;
    private LinearLayout mLinearLayout_DepositTypeB;
    private LinearLayout mLinearLayout_DepositTypeC;
    private LinearLayout mLinearLayout_DepositTypeD;
    private LinearLayout mLinearLayout_DepositTypeLayout;

    private ImageView mImageView_DepositType;
    private TextView mTextView_DepositType;
    private TextView mTextView_Type;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_deposit_operation);
        super.init();
    }

    @Override
    public void initView() {
        mLinearLayout_Back = (LinearLayout) findViewById(R.id.id_ll_credit_note_collection_back);
        mLinearLayout_DepositType = (LinearLayout) findViewById(R.id.id_ll_deposit_operation_type);
        mLinearLayout_DepositTypeLayout = (LinearLayout) findViewById(R.id.id_ll_deposit_operation_type_layout);

        mTextView_Type = (TextView) findViewById(R.id.id_tv_deposit_operation_deposit_type);
        mImageView_DepositType = (ImageView) findViewById(R.id.id_iv_deposit_operation_type);
        mTextView_DepositType = (TextView) findViewById(R.id.id_tv_deposit_operation_type);

        mImageView_Switch = (ImageView) findViewById(R.id.id_iv_deposit_operation_switch);
        mEditeText_Remark = (EditText) findViewById(R.id.id_et_credit_note_collection_remark);
        mEditText_Collection = (EditText) findViewById(R.id.id_et_credit_note_collection_current_receipt);
        mTextView_Balance = (TextView) findViewById(R.id.id_tv_credit_note_collection_balance);
        mTextView_Customer = (TextView) findViewById(R.id.id_tv_credit_note_collection_customer);

        mLinearLayout_DepositTypeA = (LinearLayout) findViewById(R.id.id_ll_deposit_operation_type_a);
        mLinearLayout_DepositTypeB = (LinearLayout) findViewById(R.id.id_ll_deposit_operation_type_b);
        mLinearLayout_DepositTypeC = (LinearLayout) findViewById(R.id.id_ll_deposit_operation_type_c);
        mLinearLayout_DepositTypeD = (LinearLayout) findViewById(R.id.id_ll_deposit_operation_type_d);

        mLinearLayout_Keyboard = (LinearLayout) findViewById(R.id.id_ll_contract_signature_keyboard);
        mLinearLayout_NumberZero = (LinearLayout) findViewById(R.id.id_lay_keyboard_number_0);
        mLinearLayout_NumberOne = (LinearLayout) findViewById(R.id.id_lay_keyboard_number_1);
        mLinearLayout_NumberTwo = (LinearLayout) findViewById(R.id.id_lay_keyboard_number_2);
        mLinearLayout_NumberThree = (LinearLayout) findViewById(R.id.id_lay_keyboard_number_3);
        mLinearLayout_NumberFour = (LinearLayout) findViewById(R.id.id_lay_keyboard_number_4);
        mLinearLayout_NumberFive = (LinearLayout) findViewById(R.id.id_lay_keyboard_number_5);
        mLinearLayout_NumberSix = (LinearLayout) findViewById(R.id.id_lay_keyboard_number_6);
        mLinearLayout_NumberSeven = (LinearLayout) findViewById(R.id.id_lay_keyboard_number_7);
        mLinearLayout_NumberEight = (LinearLayout) findViewById(R.id.id_lay_keyboard_number_8);
        mLinearLayout_NumberNine = (LinearLayout) findViewById(R.id.id_lay_keyboard_number_9);
        mLinearLayout_delete = (LinearLayout) findViewById(R.id.id_lay_keyboard_delete);
    }

    @Override
    public void initData() {
        mTextView_Balance.setText("+1,000,000.00");
        mTextView_Customer.setText("州城顺达批发");
    }

    @Override
    public void initEvent() {
        mImageView_Switch.setOnClickListener(this);
        mLinearLayout_Back.setOnClickListener(this);

        mLinearLayout_NumberZero.setOnClickListener(this);
        mLinearLayout_NumberOne.setOnClickListener(this);
        mLinearLayout_NumberTwo.setOnClickListener(this);
        mLinearLayout_NumberThree.setOnClickListener(this);
        mLinearLayout_NumberFour.setOnClickListener(this);
        mLinearLayout_NumberFive.setOnClickListener(this);
        mLinearLayout_NumberSix.setOnClickListener(this);
        mLinearLayout_NumberSeven.setOnClickListener(this);
        mLinearLayout_NumberEight.setOnClickListener(this);
        mLinearLayout_NumberNine.setOnClickListener(this);
        mLinearLayout_delete.setOnClickListener(this);
        mLinearLayout_DepositType.setOnClickListener(this);

        mLinearLayout_DepositTypeA.setOnClickListener(this);
        mLinearLayout_DepositTypeB.setOnClickListener(this);
        mLinearLayout_DepositTypeC.setOnClickListener(this);
        mLinearLayout_DepositTypeD.setOnClickListener(this);

        mEditText_Collection.setOnTouchListener(this);
    }

    @Override
    public void onBackPressed() {
        if (mLinearLayout_Keyboard.getVisibility() == View.VISIBLE) {
            mAnimation = AnimationUtils.loadAnimation(this, R.anim.keyboard_exit);
            mLinearLayout_Keyboard.setAnimation(mAnimation);
            mLinearLayout_Keyboard.setVisibility(View.GONE);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public void onClick(View v) {
        String number = mEditText_Collection.getText().toString();
        switch (v.getId()) {
            case R.id.id_ll_credit_note_collection_back:
                finish();
                break;
            case R.id.id_iv_credit_note_collection_detail:
                Intent intent = new Intent();
                intent.setClass(this, DepositManagerActivity.class);
                startActivity(intent);
                break;
            case R.id.id_lay_keyboard_number_0:
                mEditText_Collection.setText(number + "0");
                break;
            case R.id.id_lay_keyboard_number_1:
                mEditText_Collection.setText(number + "1");
                break;
            case R.id.id_lay_keyboard_number_2:
                mEditText_Collection.setText(number + "2");
                break;
            case R.id.id_lay_keyboard_number_3:
                mEditText_Collection.setText(number + "3");
                break;
            case R.id.id_lay_keyboard_number_4:
                mEditText_Collection.setText(number + "4");
                break;
            case R.id.id_lay_keyboard_number_5:
                mEditText_Collection.setText(number + "5");
                break;
            case R.id.id_lay_keyboard_number_6:
                mEditText_Collection.setText(number + "6");
                break;
            case R.id.id_lay_keyboard_number_7:
                mEditText_Collection.setText(number + "7");
                break;
            case R.id.id_lay_keyboard_number_8:
                mEditText_Collection.setText(number + "8");
                break;
            case R.id.id_lay_keyboard_number_9:
                mEditText_Collection.setText(number + "9");
                break;
            case R.id.id_lay_keyboard_delete:
                if (!"".equals(number)) {
                    number = number.substring(0, number.length() - 1);
                    mEditText_Collection.setText(number);
                }
                break;
            case R.id.id_ll_deposit_operation_type:
                if (mLinearLayout_DepositTypeLayout.getVisibility() == View.GONE) {
                    mLinearLayout_DepositTypeLayout.setVisibility(View.VISIBLE);
                    mImageView_DepositType.setImageResource(R.drawable.top);
                } else {
                    mLinearLayout_DepositTypeLayout.setVisibility(View.GONE);
                    mImageView_DepositType.setImageResource(R.drawable.select_client);

                }
                break;
            case R.id.id_ll_deposit_operation_type_a:
                mLinearLayout_DepositTypeLayout.setVisibility(View.GONE);
                mTextView_DepositType.setText("A类");
                break;
            case R.id.id_ll_deposit_operation_type_b:
                mLinearLayout_DepositTypeLayout.setVisibility(View.GONE);
                mTextView_DepositType.setText("B类");
                break;
            case R.id.id_ll_deposit_operation_type_c:
                mLinearLayout_DepositTypeLayout.setVisibility(View.GONE);
                mTextView_DepositType.setText("C类");
                break;
            case R.id.id_ll_deposit_operation_type_d:
                mLinearLayout_DepositTypeLayout.setVisibility(View.GONE);
                mTextView_DepositType.setText("D类");
                break;
            case R.id.id_iv_deposit_operation_switch:
                if (mTextView_Type.getText().toString().equals("存款收入")) {
                    mTextView_Type.setText("存款支出");
                } else {
                    mTextView_Type.setText("存款收入");
                }
                break;
            default:
                break;
        }
    }

    @Override
    public boolean onTouch(View v, MotionEvent event) {
        switch (v.getId()) {
            case R.id.id_et_credit_note_collection_current_receipt:
                HideInputMethod.hideInputMethod(this);
                mEditText_Collection.requestFocusFromTouch();
                if (mLinearLayout_Keyboard.getVisibility() == View.GONE) {
                    mAnimation = AnimationUtils.loadAnimation(this, R.anim.keyboard_enter);
                    mLinearLayout_Keyboard.setAnimation(mAnimation);
                    mLinearLayout_Keyboard.setVisibility(View.VISIBLE);
                }
                break;
            default:
                break;
        }
        return true;
    }
}
