package com.youxiao.ui.fragment;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.baidu.location.BDLocation;
import com.baidu.location.BDLocationListener;
import com.baidu.location.LocationClient;
import com.baidu.location.LocationClientOption;
import com.baidu.mapapi.SDKInitializer;
import com.baidu.mapapi.map.BaiduMap;
import com.baidu.mapapi.map.BitmapDescriptor;
import com.baidu.mapapi.map.BitmapDescriptorFactory;
import com.baidu.mapapi.map.MapView;
import com.baidu.mapapi.map.Marker;
import com.baidu.mapapi.map.MarkerOptions;
import com.baidu.mapapi.map.MyLocationData;
import com.baidu.mapapi.model.LatLng;
import com.baidu.mapapi.search.core.RouteLine;
import com.baidu.mapapi.search.core.SearchResult;
import com.baidu.mapapi.search.route.BikingRouteResult;
import com.baidu.mapapi.search.route.DrivingRoutePlanOption;
import com.baidu.mapapi.search.route.DrivingRouteResult;
import com.baidu.mapapi.search.route.OnGetRoutePlanResultListener;
import com.baidu.mapapi.search.route.PlanNode;
import com.baidu.mapapi.search.route.RoutePlanSearch;
import com.baidu.mapapi.search.route.TransitRouteResult;
import com.baidu.mapapi.search.route.WalkingRouteResult;
import com.baidu.mapapi.utils.DistanceUtil;
import com.baidu.mapapi.utils.OpenClientUtil;
import com.youxiao.R;
import com.youxiao.model.CustomerManagerBean;
import com.youxiao.util.DrivingRouteOverlay;
import com.youxiao.util.OverlayManager;
import com.youxiao.util.ToastUtil;

import java.io.File;

/**
 * 地理位置Fragment
 *
 * @author StomHong
 * @since 2016-3-25
 */
public class MapLocationFragment extends Fragment {


    public MapView mapView;
    public BaiduMap baiduMap;
    private Context context;
    private RouteLine route = null;
    OverlayManager routeOverlay = null;

    // 定位相关声明
    public LocationClient locationClient;
    //自定义图标
    BitmapDescriptor mCurrentMarker = null;
    boolean isFirstLoc = true;// 是否首次定位


    //测试数据
    private double latN;
    private double lonL;

    int nodeIndex = -1;
    DrivingRouteResult nowResultd = null;
    RoutePlanSearch mSearch = null;
    private double getLatN;//商店纬度
    private double getLonL;//商店经度

    @Override

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        SDKInitializer.initialize(getActivity().getApplication());
        final View view = inflater.inflate(R.layout.tab_for_client_details4, container, false);
        mapView = (MapView) view.findViewById(R.id.id_mv_client_details04_map_view);
        //获取商店经纬度信息
        getLatN = getArguments().getDouble("lat");
        getLonL = getArguments().getDouble("lon");

        mSearch = RoutePlanSearch.newInstance();
        baiduMap = mapView.getMap();

        locationClient = new LocationClient(getActivity()); // 实例化LocationClient类
        locationClient.registerLocationListener(myListener); // 注册监听函数
        baiduMap.setMyLocationEnabled(true);
        this.setLocationOption();    //设置定位参数
        locationClient.start(); // 开始定位
        // baiduMap.setMapType(BaiduMap.MAP_TYPE_NORMAL); // 设置为一般地图
        //markerd点击导航事件
        baiduMap.setOnMarkerClickListener(new BaiduMap.OnMarkerClickListener() {
            @Override
            public boolean onMarkerClick(Marker marker) {
                AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
                builder.setTitle("即将使用百度地图导航");
                builder.setPositiveButton("确定", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        try {
                            Intent intent = Intent.getIntent
                                    (("intent://map/direction?origin=latlng:34.264642646862," +
                                            "108.95108518068|name:我家&destination=大雁塔&mode=driving®ion=" +
                                            "西安&referer=Autohome|GasStation#Intent;scheme=bdapp;package=" +
                                            "com.baidu.BaiduMap;end"));
                            if (isInstallByread("com.baidu.BaiduMap")) {
                                startActivity(intent); //启动调用
                                Log.e("GasStation", "百度地图客户端已经安装");
                            } else {
                                ToastUtil.show("请安装百度地图！");
                                OpenClientUtil.getLatestBaiduMapApp(getActivity());
                                startActivity(intent);
                                Log.e("GasStation", "没有安装百度地图客户端");
                            }

                        } catch (Exception ex) {
                            ex.printStackTrace();

                        }

                        dialogInterface.dismiss();
                    }

                });
                builder.setNegativeButton("取消", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        dialogInterface.dismiss();
                    }
                });
                builder.show();

                return true;
            }
        });

        return view;
    }

    private boolean isInstallByread(String packageName) {
        return new File("/data/data/" + packageName).exists();
    }


    private void setLocationOption() {
        LocationClientOption option = new LocationClientOption();
        option.setOpenGps(true); // 打开GPS
        option.setLocationMode(LocationClientOption.LocationMode.Hight_Accuracy);// 设置定位模式
        option.setCoorType("bd09ll"); // 返回的定位结果是百度经纬度,默认值gcj02
        option.setScanSpan(5000); // 设置发起定位请求的间隔时间为5000ms
        option.setIsNeedAddress(true); // 返回的定位结果包含地址信息
        option.setNeedDeviceDirect(true); // 返回的定位结果包含手机机头的方向
        locationClient.setLocOption(option);

    }

    public void markTwo(LatLng start, BaiduMap baiduMap) {
        BitmapDescriptor bm1 = BitmapDescriptorFactory.fromResource(R.drawable.zuobiao);
        MarkerOptions option1 = new MarkerOptions();
        option1.position(start);
        option1.icon(bm1);
        baiduMap.addOverlay(option1);
    }

    public BDLocationListener myListener = new BDLocationListener() {
        @Override
        public void onReceiveLocation(BDLocation location) {
            if (location == null || mapView == null)
                return;

            MyLocationData locData = new MyLocationData.Builder()
                    .accuracy(location.getRadius())
                    // 此处设置开发者获取到的方向信息，顺时针0-360
                    .direction(100).latitude(location.getLatitude())
                    .longitude(location.getLongitude()).build();
            baiduMap.setMyLocationData(locData);    //设置定位数据
            latN = location.getLatitude();
            lonL = location.getLongitude();
            LatLng latLng1 = new LatLng(getLatN, getLonL);
            markTwo(latLng1, baiduMap);
            //对点连线操作
            LatLng latLng2 = new LatLng(latN, lonL);
//            List<LatLng> list = new ArrayList<>();
//            list.add(latLng1);
//            list.add(latLng2);
//            PolylineOptions polylineOptions = new PolylineOptions().width(10).color(Color.RED).points(list);
//            baiduMap.addOverlay(polylineOptions);
//            mSearch.drivingSearch(new DrivingRoutePlanOption().
//            from(PlanNode.withLocation(latLng1)).to(PlanNode.withLocation(latLng2)));

            mSearch = RoutePlanSearch.newInstance();
            mSearch.setOnGetRoutePlanResultListener(new routeListener());
            PlanNode startPlace = PlanNode.withLocation(latLng2);

            PlanNode endPlace = PlanNode.withLocation(latLng1);
            mSearch.drivingSearch((new DrivingRoutePlanOption())
                    .from(startPlace)
                    .to(endPlace));

        }
    };

    @Override
    public void onResume() {
        super.onResume();
        mapView.onResume();
    }

    @Override
    public void onPause() {
        super.onPause();
        mapView.onPause();

    }

    @Override
    public void onDestroy() {
        locationClient.stop();
        baiduMap.setMyLocationEnabled(false);
        super.onDestroy();
        mapView.onDestroy();
        mapView = null;
    }

    //路线规划
    class routeListener implements OnGetRoutePlanResultListener {
        @Override
        public void onGetBikingRouteResult(BikingRouteResult arg0) {

        }

        @Override
        public void onGetDrivingRouteResult(DrivingRouteResult result) {
            // TODO Auto-generated method stub
            //获取驾车线路规划结果
            if (result == null || result.error != SearchResult.ERRORNO.NO_ERROR) {

                ToastUtil.show("抱歉，未找到结果");

            }
            if (result.error == SearchResult.ERRORNO.AMBIGUOUS_ROURE_ADDR) {
                //起终点或途经点地址有岐义，通过以下接口获取建议查询信息
                //result.getSuggestAddrInfo()
                return;
            }
            if (result.error == SearchResult.ERRORNO.NO_ERROR) {
                DrivingRouteOverlay overlay2 = new DrivingRouteOverlay(baiduMap);
                overlay2.setData(result.getRouteLines().get(0));
                overlay2.addToMap();
                overlay2.zoomToSpan();
            }
        }

        @Override
        public void onGetTransitRouteResult(TransitRouteResult arg0) {
            // TODO Auto-generated method stub

        }

        @Override
        public void onGetWalkingRouteResult(WalkingRouteResult arg0) {
            // TODO Auto-generated method stub

        }
    }

    // 定制RouteOverly
    private class MyDrivingRouteOverlay extends DrivingRouteOverlay {
        public MyDrivingRouteOverlay(BaiduMap baiduMap) {
            super(baiduMap);
        }

    }
}
