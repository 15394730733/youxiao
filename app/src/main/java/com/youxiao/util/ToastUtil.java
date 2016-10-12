package com.youxiao.util;

import android.widget.Toast;

import com.youxiao.ui.activity.login.MyApplication;

/**
 * Created by CXP on 2016/9/11000001.
 */
public class ToastUtil {
    public static void show(String msg) {
        Toast.makeText(MyApplication.getContext(), msg, Toast.LENGTH_SHORT).show();
    }
}
