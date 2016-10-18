package com.youxiao.ui.activity.login;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.view.View.OnClickListener;
import android.view.WindowManager;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation.AnimationListener;
import android.view.animation.Animation;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.google.gson.Gson;
import com.lidroid.xutils.ViewUtils;
import com.lidroid.xutils.view.annotation.ViewInject;
import com.youxiao.model.FastExperienceBean;
import com.youxiao.ui.activity.MainActivity;
import com.youxiao.R;
import com.youxiao.ui.activity.register.RegisterActivity;
import com.youxiao.util.SpUtil;

import org.json.JSONException;
import org.json.JSONObject;

import java.security.MessageDigest;
import java.util.ArrayList;

/**
 *
 */
public class SplashActivity extends Activity implements OnClickListener {
    @ViewInject(R.id.viewpager)
    private ImageView viewpager; // 图片
    @ViewInject(R.id.button1)
    private Button button1;
    @ViewInject(R.id.button2)
    private Button button2;
    @ViewInject(R.id.viewpager1)
    private ImageView viewpager1; // 图片
    private int[] drawable = {R.drawable.bg1, R.drawable.bg2, R.drawable.bg3,R.drawable.bg4};
    public static final int ANIMATION_TIME = 8000;
    public AlphaAnimation aa, aa1;
    public int index = 0;
    public int index1 = 1;
    public boolean isok = true;
    private SharedPreferences sharedPreferences;
    private TextView mTextView_FastExperience;
    private JSONObject jsonObject;
    private FastExperienceBean.FastExperience data;
    private Handler mHandler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            try {
                //从服务器获取到结果
                boolean flag = jsonObject.getBoolean("flag");
                String result = jsonObject.getString("result");
                if (msg.what == 0) {
                    if (flag) {
                        //登录成功
                        //进入到主界面
                        Intent intent = new Intent(SplashActivity.this, MainActivity.class);
                        startActivity(intent);
                        finish();
                    } else {
                        //登录失败
                        Toast.makeText(SplashActivity.this, result, Toast.LENGTH_SHORT).show();
                        return;
                    }
                }else if(msg.what==1){
                    Gson gson=new Gson();
                    FastExperienceBean fastExperienceBean = gson.fromJson(jsonObject + "", FastExperienceBean.class);
                    data = fastExperienceBean.data;
                    int distrId = data.distrId;
                    int id = data.id;
                    String phonePassword = data.phonePassword;
                    String username = data.username;
                    //把这四个保存起来
                    ArrayList<String> list=new ArrayList<>();
                    list.add(0,username);
                    list.add(1,phonePassword);
                    list.add(2,distrId+"");
                    list.add(3,id+"");
                    SharedPreferences.Editor edit = SpUtil.getSp().edit();
                    edit.putString(SpUtil.USER_NAME,list.get(0));
                    edit.putString(SpUtil.PWD,list.get(1));
                    edit.putString(SpUtil.DISTR_ID,list.get(2));
                    edit.putString(SpUtil.ID,list.get(3));
                    edit.commit();
                }
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // 全屏
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        requestWindowFeature(Window.FEATURE_LEFT_ICON);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);

        setContentView(R.layout.activity_splash);
//        TODO 判断本地是否有token 如果有 再次请求服务器
        SharedPreferences sp = SpUtil.getSp();
        if(TextUtils.isEmpty("")){

        }else{
            getInfo( sp.getString(SpUtil.USER_NAME,""),sp.getString(SpUtil.PWD,""));
            return;
        }
        ViewUtils.inject(this);
        sharedPreferences = getSharedPreferences("user_info", Activity.MODE_PRIVATE);
        viewpager.setScaleType(ImageView.ScaleType.FIT_XY);
        viewpager1.setScaleType(ImageView.ScaleType.FIT_XY);
        button1.setOnClickListener(this);
        button2.setOnClickListener(this);
        // 图片渐变模糊度始终
        aa = new AlphaAnimation(1.0f, 0f);
        aa1 = new AlphaAnimation(0f, 1.0f);
        // 渐变时间
        aa.setDuration(ANIMATION_TIME);
        aa1.setDuration(ANIMATION_TIME);
        if (isok) {
            viewpager.setImageResource(drawable[index]);
            viewpager1.setImageResource(drawable[index1]);
            isok = false;
        }
        viewpager.setAnimation(aa);
        viewpager1.setAnimation(aa1);
        aa.setAnimationListener(new Animation.AnimationListener() {
            public void onAnimationStart(Animation arg0) {
            }

            public void onAnimationRepeat(Animation arg0) {
            }

            public void onAnimationEnd(Animation arg0) {
                aa.start();
                index = index + 1;
                if (index <= 4) {
                    if (index == 4) {
                        index = 0;
                    }
                    viewpager.setImageResource(drawable[index]);
                    viewpager.setAnimation(aa);
                }
            }
        });
        aa1.setAnimationListener(new AnimationListener() {
            public void onAnimationStart(Animation arg0) {
            }

            public void onAnimationRepeat(Animation arg0) {
            }

            public void onAnimationEnd(Animation arg0) {

                aa1.start();

                index1 = index1 + 1;
                if (index1 <= 4) {
                    if (index1 == 4) {
                        index1 = 0;
                    }
                    viewpager1.setImageResource(drawable[index1]);
                    viewpager1.setAnimation(aa1);
                }
            }
        });
        //快速体验 跳到主界面
        findViewById(R.id.tv_fast_experience).setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                getFastExperienceInfo();
                Intent intent = new Intent(SplashActivity.this, MainActivity.class);
                startActivity(intent);
            }
        });
    }


    @Override
    protected void onResume() {
        super.onResume();
    }

//    /**
//     * 对密码进行加密
//     *
//     * @param str
//     * @return
//     */
//    public static String MD5(String str) {
//        MessageDigest md5 = null;
//        try {
//            md5 = MessageDigest.getInstance("MD5");
//        } catch (Exception e) {
//            e.printStackTrace();
//            return "";
//        }
//
//        char[] charArray = str.toCharArray();
//        byte[] byteArray = new byte[charArray.length];
//
//        for (int i = 0; i < charArray.length; i++) {
//            byteArray[i] = (byte) charArray[i];
//        }
//        byte[] md5Bytes = md5.digest(byteArray);
//
//        StringBuffer hexValue = new StringBuffer();
//        for (int i = 0; i < md5Bytes.length; i++) {
//            int val = ((int) md5Bytes[i]) & 0xff;
//            if (val < 16) {
//                hexValue.append("0");
//            }
//            hexValue.append(Integer.toHexString(val));
//        }
//        return hexValue.toString();
//    }

    /**
     * 快速体验请求服务器
     */
    public void getFastExperienceInfo(){
        try {
            JSONObject object = new JSONObject();
            JsonObjectRequest jsonRequest1 = new JsonObjectRequest(
                    Request.Method.POST, SpUtil.BASE_URL + "/login/experience", object,
                    new Response.Listener<JSONObject>() {

                        @Override
                        public void onResponse(JSONObject response) {
                            if (response != null) {
                                Log.e("msg",response+"");
                                jsonObject = response;
                                Message message = new Message();
                                message.what = 1;
                                mHandler.sendMessageDelayed(message, 1000);
                            }
                        }
                    }, new Response.ErrorListener() {
                @Override
                public void onErrorResponse(VolleyError error) {
                    Log.e("error",error.toString());
                }
            });
            MyApplication.queues.add(jsonRequest1);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.button1://登录
                //跳转到登录界面
                Intent loginIntent = new Intent(SplashActivity.this, LoginActivity.class);
                startActivity(loginIntent);
                break;
            case R.id.button2://注册
                //跳转到注册页面
                Intent regIntent = new Intent(SplashActivity.this, RegisterActivity.class);
                startActivity(regIntent);
                break;

        }
    }
    public void getInfo(String userName, String md5password) {
        try {
            JSONObject object = new JSONObject();
            object.put("account_id", userName);
            object.put("account_pwd", md5password);
            JsonObjectRequest jsonRequest1 = new JsonObjectRequest(
                    Request.Method.POST, SpUtil.BASE_URL+ "/login", object,
                    new Response.Listener<JSONObject>() {
                        @Override
                        public void onResponse(JSONObject response) {
                            if (response != null) {
                                Log.e("msg",response+"");
                                jsonObject = response;
                                Message message = new Message();
                                message.what = 0;
                                mHandler.sendMessageDelayed(message, 1000);
                            }
                        }
                    }, new Response.ErrorListener() {
                @Override
                public void onErrorResponse(VolleyError error) {
                    Log.e("error",error.toString());
                }
            });
            MyApplication.queues.add(jsonRequest1);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
