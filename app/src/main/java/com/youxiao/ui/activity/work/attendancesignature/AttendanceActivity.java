package com.youxiao.ui.activity.work.attendancesignature;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Matrix;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.baidu.mapapi.map.BaiduMap;
import com.baidu.mapapi.map.BitmapDescriptor;
import com.baidu.mapapi.map.BitmapDescriptorFactory;
import com.baidu.mapapi.map.MapStatus;
import com.baidu.mapapi.map.MapStatusUpdate;
import com.baidu.mapapi.map.MapStatusUpdateFactory;
import com.baidu.mapapi.map.MapView;
import com.baidu.mapapi.map.Marker;
import com.baidu.mapapi.map.MyLocationConfiguration;
import com.baidu.mapapi.map.MyLocationData;
import com.youxiao.R;
import com.youxiao.base.BaseActivity;
import com.youxiao.util.GetSystemTime;

import java.util.Calendar;
import java.util.Date;

/**
 * 考勤签到
 *
 * @author StomHong
 * @since 2016-7-20
 */
public class AttendanceActivity extends BaseActivity implements View.OnClickListener {

    private LinearLayout mLinearLayout_Back;
    private MapView mMapView;
    private BaiduMap mBaiduMap;
    private TextView mTextView_LocationAddress;
    private TextView mTextView_FineAdjustment;
    BitmapDescriptor mCurrentMarker;
    private TextView mTextView_Date;
    private TextView mTextView_Time;
    private TextView mTextView_Week;
    private LinearLayout mLinearLayout_Signature;
    private TextView mTextView_LocationName;
    private MyLocationData mLocData;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_attendance);

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
        mLinearLayout_Back = (LinearLayout) findViewById(R.id.id_ll_attendance_back);
        mLinearLayout_Signature = (LinearLayout) findViewById(R.id.id_ll_signature);

        mTextView_LocationAddress = (TextView) findViewById(R.id.id_tv_attendance_location_address);
        mTextView_FineAdjustment = (TextView) findViewById(R.id.id_tv_fine_adjustment);
        mTextView_Date = (TextView) findViewById(R.id.id_tv_date);
        mTextView_Time = (TextView) findViewById(R.id.id_tv_time);
        mTextView_Week = (TextView) findViewById(R.id.id_tv_week);
        mTextView_LocationName = (TextView) findViewById(R.id.id_tv_location_name);

        mMapView = (MapView) findViewById(R.id.id_mv_attendance);


    }

    @Override
    public void initData() {
//        if (GLOBAL.LOCATION != null) {
//            mTextView_LocationAddress.setText(GLOBAL.LOCATION.getAddrStr());
//            try {
//                setMapPosition();
//            }catch (Exception e){
//                e.printStackTrace();
//            }
//        }
        mTextView_Date.setText(GetSystemTime.getTime("yyyy.MM.dd"));
        mTextView_Time.setText(GetSystemTime.getTime("HH:mm"));
        mTextView_Week.setText(getWeek());

    }

    @Override
    public void initEvent() {
        mLinearLayout_Back.setOnClickListener(this);
        mTextView_FineAdjustment.setOnClickListener(this);
        mMapView.setOnClickListener(this);
        mLinearLayout_Signature.setOnClickListener(this);
        mBaiduMap.setOnMarkerClickListener(new BaiduMap.OnMarkerClickListener() {
            @Override
            public boolean onMarkerClick(Marker marker) {
                Toast.makeText(AttendanceActivity.this, "marker", Toast.LENGTH_SHORT).show();
                Intent intent = new Intent();
                intent.setClass(AttendanceActivity.this, FineAdjustmentActivity.class);
                startActivityForResult(intent, 0);
                overridePendingTransition(R.anim.activity_enter, 0);
                return true;
            }
        });
    }

    @Override
    public void onClick(View v) {
        Intent intent = new Intent();
        switch (v.getId()) {
            case R.id.id_ll_attendance_back:
                finish();
                break;
            case R.id.id_tv_fine_adjustment:
                intent.setClass(this, FineAdjustmentActivity.class);
                startActivityForResult(intent, 0);
                overridePendingTransition(R.anim.activity_enter, R.anim.activity_exit);
                break;
            case R.id.id_ll_signature:
                intent.setClass(this, SignatureActivity.class);
                intent.putExtra("address",mTextView_LocationAddress.getText());
                intent.putExtra("name",mTextView_LocationName.getText());
                intent.putExtra("time",mTextView_Time.getText());
                startActivity(intent);
                break;
            default:
                break;
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 0 && data != null) {
            String name = data.getStringExtra("name");
            String address = data.getStringExtra("address");
            double latitude = data.getDoubleExtra("latitude",0);
            double longitude = data.getDoubleExtra("longitude",0);

            mTextView_LocationName.setVisibility(View.VISIBLE);
            mTextView_LocationName.setText(name);
            mTextView_LocationAddress.setText(address);

            mBaiduMap = mMapView.getMap();
            mLocData = new MyLocationData.Builder()
                    .direction(0)
                    .latitude(latitude)
                    .longitude(longitude)
                    .build();
            mBaiduMap.setMyLocationData(mLocData);

            mCurrentMarker = BitmapDescriptorFactory.fromBitmap(getBitmap());

            mBaiduMap.setMyLocationConfigeration(new MyLocationConfiguration(
                    MyLocationConfiguration.LocationMode.FOLLOWING, true, mCurrentMarker));
        }
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
                .zoom(17.5f)//3到21
                .build();
        MapStatusUpdate mapStatusUpdate = MapStatusUpdateFactory.newMapStatus(mapStatus);
        mBaiduMap.setMapStatus(mapStatusUpdate);
        mLocData = new MyLocationData.Builder()
                .direction(0)
//                .latitude(GLOBAL.LOCATION.getLatitude())
//                .longitude(GLOBAL.LOCATION.getLongitude())
                .build();
        mBaiduMap.setMyLocationData(mLocData);
        mCurrentMarker = BitmapDescriptorFactory.fromBitmap(getBitmap());
        mBaiduMap.setMyLocationConfigeration(new MyLocationConfiguration(
                MyLocationConfiguration.LocationMode.FOLLOWING, true, mCurrentMarker));
    }

    /**
     * 缩小图片的尺寸
     *
     * @return 缩小后图片
     */
    private Bitmap getBitmap() {
        //加载需要操作的图片，这里是一张图片
        Bitmap bitmapOrg = BitmapFactory.decodeResource(getResources(), R.drawable.zuobiao);
        //获取这个图片的宽和高
        int width = bitmapOrg.getWidth();
        int height = bitmapOrg.getHeight();
        //定义预转换成的图片的宽度和高度
        int newWidth = 25;
        int newHeight = 30;
        //计算缩放率，新尺寸除原始尺寸
        float scaleWidth = ((float) newWidth) / width;
        float scaleHeight = ((float) newHeight) / height;
        // 创建操作图片用的matrix对象
        Matrix matrix = new Matrix();
        // 缩放图片动作
        matrix.postScale(scaleWidth, scaleHeight);
        return Bitmap.createBitmap(bitmapOrg,0,0,width,height,matrix,true);
    }

    /**
     * 判断当前日期是星期几，返回结果
     *
     * @return dayOfWeek 所需要的星期几
     *
     */
    private String getWeek() {
        String Week = "";
        Calendar c = Calendar.getInstance();
            c.setTime(new Date(System.currentTimeMillis()));
        switch (c.get(Calendar.DAY_OF_WEEK)) {
            case 1:
                Week += "星期天";
                break;
            case 2:
                Week += "星期一";
                break;
            case 3:
                Week += "星期二";
                break;
            case 4:
                Week += "星期三";
                break;
            case 5:
                Week += "星期四";
                break;
            case 6:
                Week += "星期五";
                break;
            case 7:
                Week += "星期六";
                break;
            default:
                break;
        }
        return Week;
    }

}
