package com.youxiao.base;

import android.app.Activity;
import android.app.Application;

import com.baidu.mapapi.SDKInitializer;

import java.util.ArrayList;
import java.util.List;

/**
 * @author StomHong
 * @since 2016-3-29
 */
public class App extends Application {

    public static List<Activity> mActivities;

    @Override
    public void onCreate() {
        super.onCreate();
        SDKInitializer.initialize(getApplicationContext());
        mActivities = new ArrayList<>();
    }

    /**
     * 每打开一个新Activity都将它加入集合里
     *
     * @param activity
     */
    public static void add(Activity activity){
        boolean isExisting = false;
        for (Activity act : mActivities){
            if (act == activity){
                isExisting = true;
                break;
            }
        }
        if (!isExisting)
            mActivities.add(activity);
    }

    /**
     * 关闭所有打开的Activity
     */
    public static void finishActivities(){
        for (Activity activity : mActivities){
            activity.finish();
        }
    }

    /**
     * 退出应用程序
     */
    public static void existApp(){
        finishActivities();
    }

}
