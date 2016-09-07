package com.youxiao.ui.activity.login;

import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.youxiao.R;
import com.youxiao.base.BaseActivity;

/**
 * 登录页面
 * 正在重构
 */
public class LoginActivity extends BaseActivity implements OnClickListener {

    private static String TAG = LoginActivity.class.getSimpleName();
    private EditText mEditText_Account;
    private EditText mEditText_Password;
    private EditText mEditText_ConfigServerIP;
    private EditText mEditText_ConfigServerPort;

    private LinearLayout mLinearLayout_Keyboard;
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
    private LinearLayout mLinearLayout_Back;
    private TextView mTextView_More;
    private Button mButton_Login;
    private ImageView mImageView_Loading;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        super.init();
    }

    @Override
    public void initEvent() {
        mTextView_More.setOnClickListener(this);
        mButton_Login.setOnClickListener(this);
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
    }

    public void initView() {
        mButton_Login = (Button) findViewById(R.id.id_btn_login_login);
        mImageView_Loading = (ImageView) findViewById(R.id.id_iv_loading);

        mEditText_Password = (EditText) findViewById(R.id.id_et_login_user_password);
        mEditText_Account = (EditText) findViewById(R.id.id_et_login_user_name);
        mEditText_ConfigServerIP = (EditText) findViewById(R.id.config_server_edit);
        mEditText_ConfigServerPort = (EditText) findViewById(R.id.config_port_edit);
        mTextView_More = (TextView) findViewById(R.id.id_tv_login_more);

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
        mLinearLayout_Back = (LinearLayout) findViewById(R.id.id_login_back);
        mLinearLayout_Keyboard = (LinearLayout) findViewById(R.id.id_login_keyboard);

    }

    @Override
    public void initData() {

    }

    @Override
    protected void onResume() {
        super.onResume();

    }


    @Override
    public void onClick(View v) {

    }
}
