package com.youxiao.service;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;

import com.baidu.location.LocationClient;

import java.util.List;
import java.util.Map;


/**
 * 优销服务类
 */
public class YouxiaoService extends Service {

    private static final String TAG = YouxiaoService.class.getSimpleName();

    private Map<String, String> mCustMap;
    private LocationClient mLocationClient;
    MyLocation mMyListener;
    List<Map<String, String>> listMap;


    public YouxiaoService() {
    }

    @Override
    public void onCreate() {
        super.onCreate();
        mLocationClient = new LocationClient(this);
        mMyListener = new MyLocation(this, mLocationClient);
        //注册监听函数
        mLocationClient.registerLocationListener(mMyListener);
        mLocationClient.start();

    }

    @Override
    public IBinder onBind(Intent intent) {

        return null;
    }

    @Override
    public boolean onUnbind(Intent intent) {
        return super.onUnbind(intent);
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {

        return super.onStartCommand(intent, flags, startId);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
    }






}
