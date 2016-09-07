package com.youxiao.ui.activity.me.accountandsafety;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;

import com.youxiao.base.BaseActivity;
import com.youxiao.R;

/**
 * 解除绑定
 */
public class RemoveBoundActivity extends BaseActivity implements View.OnClickListener, TextWatcher {

    private LinearLayout back;
    private Button accomplish;
    private EditText accountNumber, password;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_remove_bound);
       super.init();
    }

    @Override
    public void initView() {
        back = (LinearLayout) findViewById(R.id.activity_remove_bound_LL_back);
        accomplish = (Button) findViewById(R.id.activity_remove_bound_btn_accomplish);
        accountNumber = (EditText) findViewById(R.id.activity_remove_bound_ET_account_number);
        password = (EditText) findViewById(R.id.activity_remove_bound_ET_password);

    }

    @Override
    public void initData() {

    }

    @Override
    public void initEvent() {
        back.setOnClickListener(this);
        accomplish.setOnClickListener(this);
        accountNumber.addTextChangedListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.activity_remove_bound_LL_back:
                finish();
                break;
            case R.id.activity_remove_bound_btn_accomplish://完成
                break;
        }
    }

    @Override
    public void beforeTextChanged(CharSequence s, int start, int count, int after) {

    }

    @Override
    public void onTextChanged(CharSequence s, int start, int before, int count) {
        String name = s.toString();
        if (!"".equals(name)) {
            accomplish.setBackgroundResource(R.drawable.btn_login_unclickable);
        } else {
            accomplish.setBackgroundResource(R.drawable.login_btn_void);
        }
    }

    @Override
    public void afterTextChanged(Editable s) {

    }
}
