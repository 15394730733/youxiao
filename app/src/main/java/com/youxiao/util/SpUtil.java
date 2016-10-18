package com.youxiao.util;

import android.content.Context;
import android.content.SharedPreferences;

import com.youxiao.ui.activity.login.MyApplication;

/**
 * Created by CXP on 2016/9/11.
 */
public class SpUtil {
    //    public static final String BASE_URL = "http://114.55.61.94:8080/youxiaoservice";//接口地址
    public static final String BASE_URL = "http://114.55.61.94:8089/youxiaoApp";
    private static final String sp_name = "config";
    public static final String USER_NAME = "username";
    public static final String PWD = "pwd";
    public static final String DISTR_ID = "distrId";
    public static final String ID = "id";
    public static final String IMEI="imei";

    public static SharedPreferences getSp() {
        return MyApplication.getContext().getSharedPreferences(sp_name, Context.MODE_PRIVATE);
    }
}
