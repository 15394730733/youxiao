package com.youxiao.ui.activity.me.accountandsafety;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.youxiao.R;
import com.youxiao.ui.activity.login.MyApplication;


import org.json.JSONException;
import org.json.JSONObject;

import static com.youxiao.util.SpUtil.BASE_URL;

/**
 * 修改密码
 */
public class AmendPwdActivity extends AppCompatActivity implements View.OnClickListener {

    private LinearLayout back;
    private EditText oldPwd, newPwd, newPwdConfirm, accountName;
    private Button accomplish;
    private JSONObject jsonObject;
    private final String pwd_reg = "^[0-9a-zA-Z]{6,18}$";//密码的正则表达式
    private final String user_reg = "^[0-9]{11}$";//用户名的正则表达式


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_amend_pwd);
        initView();
        initEvent();
    }


    public void initView() {
        back = (LinearLayout) findViewById(R.id.activity_amend_pwd_LL_back);
        accomplish = (Button) findViewById(R.id.activity_amend_pwd_btn_accomplish);
        oldPwd = (EditText) findViewById(R.id.activity_amend_pwd_ET_oldPwd);
        newPwd = (EditText) findViewById(R.id.activity_amend_pwd_ET_newPwd);
        newPwdConfirm = (EditText) findViewById(R.id.activity_amend_pwd_ET_newPwd_confirm);
        accountName = (EditText) findViewById(R.id.account_name);
    }


    public void initEvent() {
        back.setOnClickListener(this);
        accomplish.setOnClickListener(this);
    }

    private Handler handler = new Handler() {

        public void handleMessage(Message msg) {
            try {
                boolean flag = jsonObject.getBoolean("flag");
                String result = jsonObject.getString("result");
                if (flag) {
                    Toast.makeText(AmendPwdActivity.this, "修改密码成功", Toast.LENGTH_SHORT).show();
                    Intent intent=new Intent();
                    intent.setClass(AmendPwdActivity.this,SafetyActivity.class);
                    startActivity(intent);

                } else {
                    Toast.makeText(AmendPwdActivity.this, result, Toast.LENGTH_SHORT).show();
                }
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
    };


    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.activity_amend_pwd_LL_back:
                finish();
                break;
            case R.id.activity_amend_pwd_btn_accomplish:
                String oldPassword = oldPwd.getText().toString();//原密码
                String newPassword = newPwd.getText().toString();//新密码
                String newPasswordAgain = newPwdConfirm.getText().toString();//确认下新密码
                String accountname = accountName.getText().toString();//用户名

                if (oldPassword.isEmpty()) {
                    Toast.makeText(this, "原密码不能为空!", Toast.LENGTH_SHORT).show();


                } else if (newPassword.isEmpty()) {
                    Toast.makeText(this, "新密码不能为空!", Toast.LENGTH_SHORT).show();
                    if ((newPassword.length() > 5 && newPassword.length() < 20) &&
                            (newPasswordAgain.length() > 5 && newPasswordAgain.length() < 20)) {
                        Toast.makeText(AmendPwdActivity.this, "请输入6位数以上的密码!", Toast.LENGTH_SHORT).show();
                    }
                } else if (newPasswordAgain.equals(newPassword) == false) {

                    Toast.makeText(this, "两次输入的新密码不一致!", Toast.LENGTH_SHORT).show();
                } else if (accountname.isEmpty()) {

                    Toast.makeText(this, "用户名不能为空!", Toast.LENGTH_SHORT).show();
                    if (accountname.matches(user_reg)) {
                        Toast.makeText(this, "用户名格式输入错误!", Toast.LENGTH_SHORT).show();
                    }
                } else if (oldPassword.equals(newPassword)) {
                    Toast.makeText(this, "新旧密码不能相同!", Toast.LENGTH_SHORT).show();
                } else {

                }


                try {

                    final JSONObject jos = new JSONObject();
                    jos.put("account_id", accountname);
                    jos.put("account_pwd", oldPassword);
                    jos.put("code", "");
                    jos.put("token", "");
                    jos.put("phoneToken", "");
                    jos.put("account_newpwd", newPasswordAgain);


                    JsonObjectRequest jsonRequest1 = new JsonObjectRequest(
                            Request.Method.POST, BASE_URL + "/Space/updatePwd", jos,
                            new Response.Listener<JSONObject>() {
                                @Override
                                public void onResponse(JSONObject response) {
                                    if (response != null) {
                                        jsonObject = response;

                                        Message message = new Message();
                                        message.what = 0;
                                        handler.sendMessageDelayed(message, 1000);
                                    }
                                }
                            }, new Response.ErrorListener() {
                        @Override
                        public void onErrorResponse(VolleyError error) {
                            Log.e("error", "错误信息：" + error.toString());
                        }
                    });
                    MyApplication.queues.add(jsonRequest1);
                } catch (Exception ex) {

                }


                break;
        }
    }

//    @Override
//    public void beforeTextChanged(CharSequence s, int start, int count, int after) {
//
//    }
//
//    @Override
//    public void onTextChanged(CharSequence s, int start, int before, int count) {
//        if (!"".equals(s.toString())) {
//            accomplish.setBackgroundResource(R.drawable.btn_login_unclickable);
//        } else {
//            accomplish.setBackgroundResource(R.drawable.login_btn_void);
//        }
//    }
//
//    @Override
//    public void afterTextChanged(Editable s) {
//
//    }


}
