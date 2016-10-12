package com.youxiao.ui.activity.me.accountandsafety;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.telephony.TelephonyManager;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.youxiao.base.BaseActivity;
import com.youxiao.R;
import com.youxiao.ui.activity.login.MyApplication;
import com.youxiao.util.SpUtil;
import com.youxiao.util.ToastUtil;

import org.json.JSONObject;

import static com.youxiao.util.SpUtil.BASE_URL;

/**
 * 解除绑定
 */
public class RemoveBoundActivity extends BaseActivity implements View.OnClickListener, TextWatcher {

    private LinearLayout back;
    private Button accomplish;
    private EditText accountNumber, password;
    private String Imei = "";
    private String account = "";
    private String password1 = "";
    private final String user_reg = "^[0-9]{11}$";//用户名的正则表达式
    private JSONObject jsonObject;


    private Handler handler = new Handler() {

        public void handleMessage(Message msg) {
            try {
                boolean flag = jsonObject.getBoolean("flag");
                String result = jsonObject.getString("result");
                if (flag) {
                    SharedPreferences.Editor edit = SpUtil.getSp().edit();
                    edit.clear();
                    edit.commit();
                    ToastUtil.show("解除绑定成功！");
                    Intent intent = new Intent();
                    intent.setClass(RemoveBoundActivity.this, SafetyActivity.class);
                    startActivity(intent);


                } else {
                    ToastUtil.show("" + result);
                }

            } catch (Exception ex) {
                ex.printStackTrace();

            }

        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_remove_bound);
        TelephonyManager telephonyManager = (TelephonyManager) getSystemService(TELEPHONY_SERVICE);
        Imei = telephonyManager.getDeviceId();
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
                account = accountNumber.getText().toString();
                password1 = password.getText().toString();
                if (account.isEmpty()) {
                    ToastUtil.show("输入账号不能为空！");
                } else if (!account.matches(user_reg)) {
                    ToastUtil.show("账号输入格式有误！");
                }
                if (password1.isEmpty()) {
                    ToastUtil.show("输入密码不能为空！");
                }
                getInfo();
                break;
        }
    }

    private void getInfo() {
        try {

            JSONObject js = new JSONObject();
            SharedPreferences sharedPreferences = SpUtil.getSp();
            String ids = sharedPreferences.getString(SpUtil.ID, "");
            SharedPreferences.Editor edit = SpUtil.getSp().edit();

            edit.clear();
            edit.commit();
            js.put("id", ids);
            js.put("distrId", 1);
            js.put("name", "");
            js.put("sex", "");
            js.put("headPicture", "");
            js.put("username", account);
            js.put("phonePassword", password1);
            js.put("address", "");
            js.put("imei", Imei);
            js.put("phoneToken", "");

            JsonObjectRequest jsonRequest1 = new JsonObjectRequest(
                    Request.Method.POST, BASE_URL + "/Space/unbind", js,
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
            Log.e("error", "联网失败");
            ex.printStackTrace();

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
