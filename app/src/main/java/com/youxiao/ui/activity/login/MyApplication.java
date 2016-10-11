package com.youxiao.ui.activity.login;

import android.app.Application;
import android.content.Context;

import com.android.volley.RequestQueue;
import com.android.volley.toolbox.Volley;

/**
 * Created by Administrator on 2016/9/9.
 */
public class MyApplication extends Application {
    public static Context context;
    public static RequestQueue queues;

    @Override
    public void onCreate() {
        super.onCreate();
        context=getApplicationContext();

        try {
            queues = Volley.newRequestQueue(this);
        } catch (RuntimeException e) {
            e.printStackTrace();
        }
    }
    public static Context getContext(){
        return context;
    }
}
