package com.youxiao.ui.activity.me.accountandsafety;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.youxiao.base.BaseActivity;
import com.youxiao.R;

/**
 * 修改密码
 */
public class AmendPwdActivity extends BaseActivity implements View.OnClickListener, TextWatcher {

    private LinearLayout back;
    private EditText oldPwd, newPwd, newPwdAgain;
    private Button accomplish;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_amend_pwd);
        super.init();
    }

    @Override
    public void initView() {
        back = (LinearLayout) findViewById(R.id.activity_amend_pwd_LL_back);
        accomplish = (Button) findViewById(R.id.activity_amend_pwd_btn_accomplish);
        oldPwd = (EditText) findViewById(R.id.activity_amend_pwd_ET_oldPwd);
        newPwd = (EditText) findViewById(R.id.activity_amend_pwd_ET_newPwd);
//        newPwdAgain = (EditText) findViewById(R.id.activity_amend_pwd_ET_newPwd_again);
    }

    @Override
    public void initData() {

    }

    @Override
    public void initEvent() {
        back.setOnClickListener(this);
        accomplish.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.activity_amend_pwd_LL_back:
                finish();
                break;
            case R.id.activity_amend_pwd_btn_accomplish:
                String oldPassword = oldPwd.getText().toString().trim();
                String newPassword = newPwd.getText().toString().trim();
                String newPasswordAgain = newPwdAgain.getText().toString().trim();
                if (oldPassword.isEmpty()) {
                    Toast.makeText(this, "原密码不能为空!", Toast.LENGTH_SHORT).show();
                } else if (newPassword.isEmpty()) {
                    Toast.makeText(this, "新密码不能为空!", Toast.LENGTH_SHORT).show();
                } else if (newPasswordAgain.isEmpty()) {
                    Toast.makeText(this, "新密码不能为空!", Toast.LENGTH_SHORT).show();
                } else if (!newPassword.equals(newPasswordAgain)) {
                    Toast.makeText(this, "两次输入的新密码不一致", Toast.LENGTH_SHORT).show();
                }
                break;
        }
    }

    @Override
    public void beforeTextChanged(CharSequence s, int start, int count, int after) {

    }

    @Override
    public void onTextChanged(CharSequence s, int start, int before, int count) {
        if (!"".equals(s.toString())) {
            accomplish.setBackgroundResource(R.drawable.btn_login_unclickable);
        } else {
            accomplish.setBackgroundResource(R.drawable.login_btn_void);
        }
    }

    @Override
    public void afterTextChanged(Editable s) {

    }
}
