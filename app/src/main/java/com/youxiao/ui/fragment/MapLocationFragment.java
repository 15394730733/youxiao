package com.youxiao.ui.fragment;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.baidu.mapapi.map.BaiduMap;
import com.baidu.mapapi.map.BitmapDescriptor;
import com.baidu.mapapi.map.BitmapDescriptorFactory;
import com.baidu.mapapi.map.MapStatus;
import com.baidu.mapapi.map.MapStatusUpdate;
import com.baidu.mapapi.map.MapStatusUpdateFactory;
import com.baidu.mapapi.map.MapView;
import com.baidu.mapapi.map.MyLocationConfiguration;
import com.baidu.mapapi.map.MyLocationData;
import com.youxiao.R;


/**
 * 地理位置Fragment
 *
 * @author StomHong
 * @since 2016-3-25
 */
public class MapLocationFragment extends Fragment {

    private MapView mMapView;
    private BaiduMap mBaiduMap;
    private BitmapDescriptor mCurrentMarker;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.tab_for_client_details4, container, false);


        return view;
    }

    @Override
    public void onResume() {
        super.onResume();
        //必须在onCreateView执行完之后才能得到MapView的实例
        setMapPosition();
        mMapView.onResume();
    }

    @Override
    public void onPause() {
        super.onPause();
        mMapView.onPause();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        mMapView.onDestroy();
        mCurrentMarker.recycle();
    }

    /**
     * 将给定的坐标位置在地图中标识出来
     */
    private void setMapPosition() {



        mMapView = (MapView) getActivity().findViewById(R.id.id_mv_client_details04_map_view);
        mBaiduMap = mMapView.getMap();
        mBaiduMap.setMyLocationEnabled(true);
        MapStatus mapStatus = new MapStatus.Builder()
                .zoom(15f)//3到21
                .build();
        MapStatusUpdate mapStatusUpdate = MapStatusUpdateFactory.newMapStatus(mapStatus);
        mBaiduMap.setMapStatus(mapStatusUpdate);
        MyLocationData locData = new MyLocationData.Builder()
                .direction(0)
//                .latitude(Double.parseDouble(selectedCustomer.getGPS_N()))
//                .longitude(Double.parseDouble(selectedCustomer.getGPS_E()))
                .build();
        mBaiduMap.setMyLocationData(locData);
        mCurrentMarker = BitmapDescriptorFactory.fromResource(R.drawable.zuobiao);
        mBaiduMap.setMyLocationConfigeration(new MyLocationConfiguration(
                MyLocationConfiguration.LocationMode.FOLLOWING, true, mCurrentMarker));
    }


}
