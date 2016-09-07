package com.youxiao.ui.activity.work.attendancesignature;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.View;
import android.view.Window;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;

import com.baidu.mapapi.map.BaiduMap;
import com.baidu.mapapi.map.BitmapDescriptor;
import com.baidu.mapapi.map.BitmapDescriptorFactory;
import com.baidu.mapapi.map.MapStatus;
import com.baidu.mapapi.map.MapStatusUpdate;
import com.baidu.mapapi.map.MapStatusUpdateFactory;
import com.baidu.mapapi.map.MapView;
import com.baidu.mapapi.map.MyLocationConfiguration;
import com.baidu.mapapi.map.MyLocationData;
import com.baidu.mapapi.model.LatLng;
import com.baidu.mapapi.search.core.PoiInfo;
import com.baidu.mapapi.search.geocode.GeoCodeResult;
import com.baidu.mapapi.search.geocode.GeoCoder;
import com.baidu.mapapi.search.geocode.OnGetGeoCoderResultListener;
import com.baidu.mapapi.search.geocode.ReverseGeoCodeOption;
import com.baidu.mapapi.search.geocode.ReverseGeoCodeResult;
import com.youxiao.R;
import com.youxiao.adapter.CommonAdapter;
import com.youxiao.adapter.ViewHolder;
import com.youxiao.base.BaseActivity;

import java.util.ArrayList;
import java.util.List;

/**
 * 位置微调
 *
 * @author StomHong
 * @since 2016-7-20
 */
public class FineAdjustmentActivity extends BaseActivity implements View.OnClickListener,
        AdapterView.OnItemClickListener {

    private static final String TAG = FineAdjustmentActivity.class.getSimpleName();
    private MapView mMapView;
    private BaiduMap mBaiduMap;
    private TextView mTextView_Cancel;
    private TextView mTextView_Complete;
    private ListView mListView_AdjustmentLocation;
    private BitmapDescriptor mCurrentMarker;
    private List<PoiInfo> mNearAddresses;
    private int mPosition;
    private CommonAdapter<PoiInfo> mAdapter;
    private MyLocationData mLocData;
    private String name = "";
    private String address = "";
    private double mLatitude;
    private double mLongitude;
    private View mView;

    Handler mHandler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            if (msg.what == 0) {
                mAdapter = new CommonAdapter<PoiInfo>(FineAdjustmentActivity.this, mNearAddresses, R.layout.item_fineadjustment_address) {
                    @Override
                    public void convert(ViewHolder holder, PoiInfo poiInfo) {
                        holder.setText(R.id.id_tv_address_detail, poiInfo.address);
                        holder.setText(R.id.id_tv_address_name, poiInfo.name);
                        holder.setViewVisibility(R.id.id_iv_address_selected,false);
                    }
                };
                mListView_AdjustmentLocation.setAdapter(mAdapter);
            }
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fine_adjustment);

        super.init();
    }

    @Override
    protected void onResume() {
        super.onResume();
        mMapView.onResume();
    }

    @Override
    protected void onPause() {
        super.onPause();
        mMapView.onPause();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mMapView.onDestroy();

    }

    @Override
    public void initView() {
        mTextView_Cancel = (TextView) findViewById(R.id.id_tv_cancel);
        mTextView_Complete = (TextView) findViewById(R.id.id_tv_complete);
        mListView_AdjustmentLocation = (ListView) findViewById(R.id.id_lv_adjustment_location);

        mMapView = (MapView) findViewById(R.id.id_mv_fine_adjustment);
    }
    @Override
    public void initData() {

            setMapPosition();
            getNearByAddress();

    }

    /**
     * 获取指定位置周边的地址
     */
    private void getNearByAddress() {
        mNearAddresses = new ArrayList<>();

        ReverseGeoCodeOption reverseGeoCodeOption = new ReverseGeoCodeOption();
//        reverseGeoCodeOption.location(new LatLng(GLOBAL.LOCATION.getLatitude(),GLOBAL.LOCATION.getLongitude()));

        final GeoCoder geoCoder = GeoCoder.newInstance();
        geoCoder.reverseGeoCode(reverseGeoCodeOption);
        geoCoder.setOnGetGeoCodeResultListener(new OnGetGeoCoderResultListener() {

            @Override
            public void onGetGeoCodeResult(GeoCodeResult geoCodeResult) {

            }

            @Override
            public void onGetReverseGeoCodeResult(ReverseGeoCodeResult reverseGeoCodeResult) {
                if (reverseGeoCodeResult != null) {
                    if (reverseGeoCodeResult.getPoiList() != null && reverseGeoCodeResult.getPoiList().size() > 0) {
                        reverseGeoCodeResult.getAddress();
                        mNearAddresses.addAll(reverseGeoCodeResult.getPoiList());
                        Message msg = new Message();
                        msg.what = 0;
                        mHandler.sendMessage(msg);
                    }

                }

            }
        });
    }

    @Override
    public void initEvent() {
        mTextView_Cancel.setOnClickListener(this);
        mTextView_Complete.setOnClickListener(this);

        mListView_AdjustmentLocation.setOnItemClickListener(this);

    }

    /**
     * 将给定的坐标位置在地图中标识出来
     */
    private void setMapPosition() {

        mMapView.showScaleControl(false);
        mMapView.showZoomControls(false);
        mBaiduMap = mMapView.getMap();
        mBaiduMap.setMyLocationEnabled(true);
        MapStatus mapStatus = new MapStatus.Builder()
                .zoom(18f)//3到21
                .build();
        MapStatusUpdate mapStatusUpdate = MapStatusUpdateFactory.newMapStatus(mapStatus);
        mBaiduMap.setMapStatus(mapStatusUpdate);
        mLocData = new MyLocationData.Builder()
                .direction(0)
//                .latitude(GLOBAL.LOCATION.getLatitude())
//                .longitude(GLOBAL.LOCATION.getLongitude())
                .build();
        mBaiduMap.setMyLocationData(mLocData);
        mCurrentMarker = BitmapDescriptorFactory.fromResource(R.drawable.zuobiao);
        mBaiduMap.setMyLocationConfigeration(new MyLocationConfiguration(
                MyLocationConfiguration.LocationMode.FOLLOWING, true, mCurrentMarker));
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.id_tv_cancel:
                finish();
                overridePendingTransition(0, R.anim.activity_exit);
                break;
            case R.id.id_tv_complete:
                if (!"".equals(name)){
                    Intent intent = new Intent();
                    intent.putExtra("name",name);
                    intent.putExtra("address",address);
                    intent.putExtra("latitude",mLatitude);
                    intent.putExtra("longitude",mLongitude);
                    setResult(Activity.RESULT_OK,intent);
                    finish();
                }
                break;
            default:
                break;
        }
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        if (mView != null){
            mView.findViewById(R.id.id_iv_address_selected).setVisibility(View.GONE);
        }
        mView = view;
        view.findViewById(R.id.id_iv_address_selected).setVisibility(View.VISIBLE);

        address = mNearAddresses.get(position).address;
        name = mNearAddresses.get(position).name;
        LatLng latLng = mNearAddresses.get(position).location;
        mLatitude = latLng.latitude;
        mLongitude = latLng.longitude;

        mLocData = new MyLocationData.Builder()
                .direction(0)
                .latitude(mLatitude)
                .longitude(mLongitude)
                .build();
        mBaiduMap.setMyLocationConfigeration(new MyLocationConfiguration(
                MyLocationConfiguration.LocationMode.FOLLOWING, true, mCurrentMarker));
        mBaiduMap.setMyLocationData(mLocData);
    }


}
