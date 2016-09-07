package com.youxiao.service;

import android.content.Context;

import com.baidu.location.BDLocation;
import com.baidu.location.BDLocationListener;
import com.baidu.location.LocationClient;
import com.baidu.location.LocationClientOption;
import com.baidu.mapapi.model.LatLng;
import com.baidu.mapapi.utils.DistanceUtil;

/**
 * @author StomHong
 * @since 2016-4-2
 */
public class MyLocation implements BDLocationListener {

    private Context mContext;

    public MyLocation() {

    }

    public MyLocation(Context context, LocationClient locationClient) {

        mContext = context;
        initLocation(context, locationClient);

    }

    /**
     * 初始化定位设置
     */
    private void initLocation(Context context, LocationClient locationClient) {

        LocationClientOption option = new LocationClientOption();
        //高精度定位模式：这种定位模式下，会同时使用网络定位和GPS定位，优先返回最高精度的定位结果；
        //低功耗定位模式：这种定位模式下，不会使用GPS，只会使用网络定位（Wi-Fi和基站定位）；
        //仅用设备定位模式：这种定位模式下，不需要连接网络，只使用GPS进行定位，这种模式下不支持室内环境的定位。
        option.setLocationMode(LocationClientOption.LocationMode.Hight_Accuracy);//可选，默认高精度，设置定位模式，高精度，低功耗，仅设备
        option.setCoorType("gcj02");//可选，默认gcj02，设置返回的定位结果坐标系
        option.setScanSpan(0);//可选，默认0，即仅定位一次，设置发起定位请求的间隔需要大于等于1000ms才是有效的
        option.setIsNeedAddress(true);//可选，设置是否需要地址信息，默认不需要（反编译获得具体位置，只有网络定位才可以）
        option.setOpenGps(true);//可选，默认false,设置是否使用gps
        option.setLocationNotify(true);//可选，默认false，设置是否当gps有效时按照1S1次频率输出GPS结果
        option.setIsNeedLocationDescribe(true);//可选，默认false，设置是否需要位置语义化结果，可以在BDLocation.getLocationDescribe里得到，结果类似于“在北京天安门附近”
        option.setIgnoreKillProcess(false);//可选，默认true，定位SDK内部是一个SERVICE，并放到了独立进程，设置是否在stop的时候杀死这个进程，默认不杀死
        option.SetIgnoreCacheException(false);//可选，默认false，设置是否收集CRASH信息，默认收集
        option.setEnableSimulateGps(false);//可选，默认false，设置是否需要过滤gps仿真结果，默认需要
        locationClient.setLocOption(option);
    }

    @Override
    public void onReceiveLocation(BDLocation location) {

    }

    /**
     * 计算距离（单位：m）
     *
     * @param Point1 起始坐标
     * @param Point2 终点坐标
     * @return
     */
    public static double calcDistance(LatLng Point1, LatLng Point2) {
        return DistanceUtil.getDistance(Point1, Point2);
    }

    /**
     * 计算距离（单位：m）
     *
     * @param lat1 起始坐标（经度）
     * @param lng1 起始坐标（纬度）
     * @param lat2 终点坐标（经度）
     * @param lng2 终点坐标（纬度）
     * @return
     */
    public static double calcDistance(double lat1, double lng1, double lat2, double lng2) {
        LatLng Point1 = new LatLng(lat1, lng1);
        LatLng Point2 = new LatLng(lat2, lng2);
        return calcDistance(Point1, Point2);
    }
}