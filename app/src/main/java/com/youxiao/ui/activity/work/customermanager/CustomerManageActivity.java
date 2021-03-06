
package com.youxiao.ui.activity.work.customermanager;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.baidu.location.BDLocation;
import com.baidu.location.BDLocationListener;
import com.baidu.location.LocationClient;
import com.baidu.location.LocationClientOption;
import com.baidu.mapapi.SDKInitializer;
import com.baidu.mapapi.map.BaiduMap;
import com.baidu.mapapi.model.LatLng;
import com.baidu.mapapi.navi.BaiduMapAppNotSupportNaviException;
import com.baidu.mapapi.navi.BaiduMapNavigation;
import com.baidu.mapapi.navi.NaviParaOption;
import com.baidu.mapapi.utils.route.BaiduMapRoutePlan;
import com.google.gson.Gson;
import com.youxiao.adapter.CustomerManagerAdapter;
import com.youxiao.base.BaseActivity;
import com.youxiao.model.CustomerManagerBean;
import com.youxiao.ui.activity.login.MyApplication;
import com.youxiao.ui.activity.work.contractsignature.ContractSignatureActivity;
import com.youxiao.ui.activity.work.marketpatrol.MarketPatrolActivity;
import com.youxiao.ui.activity.work.photomanager.PhotographUploadActivity;
import com.youxiao.ui.fragment.SalesFragment;
import com.youxiao.R;
import com.youxiao.adapter.CommonAdapter;
import com.youxiao.util.SpUtil;
import com.youxiao.util.ToastUtil;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

/**
 * 客户档案
 *
 * @author StomHong
 * @since 2016-3-24
 */
public class CustomerManageActivity extends BaseActivity implements View.OnClickListener,
        AdapterView.OnItemClickListener, View.OnLongClickListener {

    private static final String TAG = CustomerManageActivity.class.getSimpleName();

    public Context context;
    private JSONObject jsonObject;
    private ImageView mImageView_AddClient;

    private TextView mTextView_SortByVisitor;
    private TextView mTextView_SortByTime;
    private TextView mTextView_SortByDistance;
    private TextView mTextView_SortByName;

    private LinearLayout mLinearLayout_Back;
    private LinearLayout mLinearLayout_Search;
    private LinearLayout mLinearLayout_Sort;
    private LinearLayout mLinearLayout_SortWay;
    private CustomerManagerAdapter customerManagerAdapter;

    private ListView mListView_Client;
    private CommonAdapter<String> mAdapter;
    private ArrayList<CustomerManagerBean.Customer> data;
    private View mView_Shadow;
    private CustomerManagerBean customerManagerBean;

    // 定位相关声明

    public BaiduMap baiduMap;
    public LocationClient locationClient;
    private double meLat;
    private double meLon;

    private Handler handler = new Handler() {
        public void handleMessage(Message msg) {
            try {
                boolean flag = jsonObject.getBoolean("flag");
                String result = jsonObject.getString("result");
                if (flag) {
                    Gson gson = new Gson();
                    customerManagerBean = gson.fromJson(jsonObject + "", CustomerManagerBean.class);
                    data = customerManagerBean.data;
                    LatLng lng=new LatLng(meLat,meLon);
                    CustomerManagerAdapter customerManagerAdapter = new CustomerManagerAdapter(context, data,lng);
                    mListView_Client.setAdapter(customerManagerAdapter);

                } else {
                    ToastUtil.show("暂无数据");
                }
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
    };


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        super.onCreate(savedInstanceState);
        SDKInitializer.initialize(this);
        locationClient = new LocationClient(this); // 实例化LocationClient类
        locationClient.registerLocationListener(myListener); // 注册监听函数
        this.setLocationOption();    //设置定位参数
        locationClient.start();
        setContentView(R.layout.activity_client_manage);
        context = this;
        init();
        getInfo();
    }

    @Override
    public void initView() {
        mTextView_SortByDistance = (TextView) findViewById(R.id.id_client_manage_sort_distance);
        mTextView_SortByName = (TextView) findViewById(R.id.id_client_manage_sort_name);
        mTextView_SortByTime = (TextView) findViewById(R.id.id_client_manage_sort_time);
        mTextView_SortByVisitor = (TextView) findViewById(R.id.id_client_manage_sort_visitor);

        mLinearLayout_Back = (LinearLayout) findViewById(R.id.id_lay_client_manage_back);
        mLinearLayout_Search = (LinearLayout) findViewById(R.id.id_lay_client_manage_search);
        mLinearLayout_Sort = (LinearLayout) findViewById(R.id.id_lay_client_manage_sort);
        mLinearLayout_SortWay = (LinearLayout) findViewById(R.id.id_lay_client_manage_sort_way);

        mListView_Client = (ListView) findViewById(R.id.id_lv_client_manage_client_info);
        mImageView_AddClient = (ImageView) findViewById(R.id.id_iv_client_manage_add_client);
        mView_Shadow = findViewById(R.id.id_view_client_manage_shadow);
    }

    @Override
    public void initData() {
        //listview条目点击事件
        mListView_Client.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                CustomerManagerBean.Customer customer = data.get(position);
                Intent intent = new Intent(context, CustomerDetailsActivity.class);
                intent.putExtra("customer", customer);
                startActivity(intent);

            }
        });
    }


    /**
     * 警告弹窗，询问是否使用百度地图导航
     */
    private void openBaiduMapDiaolog(final View view, final int position) {

        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("即将使用百度地图导航");
        builder.setPositiveButton("确定", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

                if (checkApkExist(CustomerManageActivity.this, "com.baidu.BaiduMap")) {
                    invokeBaiduMap(view, position);

                } else {
                    BaiduMapRoutePlan.setSupportWebRoute(true);

                }
                dialog.dismiss();
            }
        });
        builder.setNegativeButton("取消", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
            }
        });
        builder.show();
    }


    /**
     * 判断百度地图应用程序是否存在
     *
     * @param context
     * @param packageName
     * @return
     */
    private boolean checkApkExist(Context context, String packageName) {

        if (packageName == null || "".equals(packageName)) {
            return false;
        }
        try {
            context.getPackageManager().getApplicationInfo(packageName, PackageManager.GET_META_DATA);
            return true;
        } catch (PackageManager.NameNotFoundException e) {
            return false;
        }
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

    public BDLocationListener myListener = new BDLocationListener() {
        @Override
        public void onReceiveLocation(BDLocation location) {
            if (location == null)
                return;
            meLat = location.getLatitude();
            meLon = location.getLongitude();

        }
    };


    /**
     * 启动百度地图导航模式
     *
     * @param view
     * @param position 点击的位置
     */
    private void invokeBaiduMap(View view, int position) {
        // 天安门坐标
        double mLat1 = 69.915291;
        double mLon1 = 116.403857;
//         百度大厦坐标
        double mLat2 = 50.056858;
        double mLon2 = 116.308194;

        // 天安门坐标
//        double mLat1 = 35.839154;
//        double mLon1 = 116.246068;
////         百度大厦坐标
//        double mLat2 = 35.818205;
//        double mLon2 = 116.08159;
        // 百度大厦坐标
//        double mLat2 = Double.parseDouble(mDatas.get(position).getGPS_N());
//        double mLon2 = Double.parseDouble(mDatas.get(position).getGPS_E());

//        double mLat2 = GLOBAL.LOCATION.getLatitude();
//        double mLon2 = GLOBAL.LOCATION.getLongitude();
        LatLng pt1 = new LatLng(mLat1, mLon1);
        LatLng pt2 = new LatLng(mLat2, mLon2);


        // 构建 导航参数
        NaviParaOption para = new NaviParaOption()
                .startPoint(pt1).endPoint(pt2)
                .startName("天安门").endName("百度大厦");

        try {
            // 调起百度地图骑行导航
            BaiduMapNavigation.openBaiduMapBikeNavi(para, this);
        } catch (BaiduMapAppNotSupportNaviException e) {
            e.printStackTrace();

        }

    }

    @Override
    public void initEvent() {
        mTextView_SortByDistance.setOnClickListener(this);
        mTextView_SortByName.setOnClickListener(this);
        mTextView_SortByTime.setOnClickListener(this);
        mTextView_SortByVisitor.setOnClickListener(this);
        mLinearLayout_Back.setOnClickListener(this);
        mLinearLayout_Sort.setOnClickListener(this);
        mLinearLayout_Search.setOnClickListener(this);
        mImageView_AddClient.setOnClickListener(this);
        mView_Shadow.setOnLongClickListener(this);

    }

    @Override
    public void onClick(View v) {
        Intent intent = new Intent();
        switch (v.getId()) {
            case R.id.id_lay_client_manage_back:
                finish();
                break;
            case R.id.id_iv_client_manage_add_client:
                intent.setClass(this, AddNewCustomerActivity.class);
                startActivityForResult(intent, 301);
                break;
            case R.id.id_lay_client_manage_search:
                intent.setClass(this, CustomerSearchActivity.class);
                startActivity(intent);
                break;
            case R.id.id_lay_client_manage_sort:
                if (mLinearLayout_SortWay.getVisibility() == View.GONE) {
                    mView_Shadow.setVisibility(View.VISIBLE);
                    mLinearLayout_SortWay.setVisibility(View.VISIBLE);
                } else if (mLinearLayout_SortWay.getVisibility() == View.VISIBLE) {
                    setViewGone();
                }
                break;
            case R.id.id_client_manage_sort_distance:
                setViewGone();
                Toast.makeText(this, "按当前距离排序", Toast.LENGTH_SHORT).show();
                break;
            case R.id.id_client_manage_sort_name:
                setViewGone();
                //按商店名A-Z排序
                Toast.makeText(this, "按商店名A-Z排序", Toast.LENGTH_SHORT).show();
                break;
            case R.id.id_client_manage_sort_time:
                setViewGone();
                //按拜访时间排序
                Toast.makeText(this, "按拜访时间排序", Toast.LENGTH_SHORT).show();
                break;
            case R.id.id_client_manage_sort_visitor:
                setViewGone();
                //按访客状态排序
                Toast.makeText(this, "按访客状态排序", Toast.LENGTH_SHORT).show();
                break;
            default:
                break;
        }
    }

    /**
     * 把排序选项和阴影隐藏掉
     */
    private void setViewGone() {
        mView_Shadow.setVisibility(View.GONE);
        mLinearLayout_SortWay.setVisibility(View.GONE);
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

        //获取所点击Item的客户信息
        Intent intent = new Intent();
        String customerName = "州城";
        int requestCode = getIntent().getIntExtra("requestCode", 0);
        switch (requestCode) {
            case PhotographUploadActivity.PHOTOGRAPH_UPLOAD_REQUEST:
                intent.putExtra("customerName", customerName);
                setResult(Activity.RESULT_OK, intent);
                finish();
                break;
            case SalesFragment.MARKETING_FRAGMENT_REQUEST:
                intent.putExtra("customerName", customerName);
                setResult(Activity.RESULT_OK, intent);
                finish();
                break;
            case MarketPatrolActivity.MARKET_PATROL_REQUEST:
                intent.putExtra("customerName", customerName);
                setResult(Activity.RESULT_OK, intent);
                finish();
                break;
            case ContractSignatureActivity.CONTRACT_SIGNATURE_REQUEST:
                intent.putExtra("customerName", customerName);
                setResult(Activity.RESULT_OK, intent);
                finish();
                break;
//            case CashRewardActivity.CASH_REWARD_REQUEST:
//                intent.putExtra("customerName", customerName);
//                setResult(Activity.RESULT_OK, intent);
//                finish();
//                break;
//            case MatterRewardActivity.MATTER_REWARD_REQUEST:
//                intent.putExtra("customerName", customerName);
//                setResult(Activity.RESULT_OK, intent);
//                finish();
//                break;
            default:
                intent.putExtra("selectedCustomer", "州城");
                intent.setClass(this, CustomerDetailsActivity.class);
                startActivity(intent);
                break;
        }


    }

    @Override
    public boolean onLongClick(View v) {

        if (R.id.id_view_client_manage_shadow == v.getId()) {
            setViewGone();
        }
        return false;
    }

    /**
     * 请求服务器获取数据
     */
    public void getInfo() {
        try {
            SharedPreferences sp = SpUtil.getSp();
            String distr_id = sp.getString(SpUtil.DISTR_ID, "");
            String id = sp.getString(SpUtil.ID, "");
            JSONObject object = new JSONObject();
            object.put("employeeId", id);
            object.put("distributorId", distr_id);
            JsonObjectRequest jsonRequest1 = new JsonObjectRequest(
                    Request.Method.POST, SpUtil.BASE_URL + "/tArchCustomer/AppCustomerList", object,
                    new Response.Listener<JSONObject>() {
                        @Override
                        public void onResponse(JSONObject response) {
                            if (response != null) {
                                jsonObject = response;
                                Message message = new Message();
                                message.what = 0;
                                handler.sendMessageDelayed(message, 1000);
                            }
                        }
                    }, new Response.ErrorListener() {
                @Override
                public void onErrorResponse(VolleyError error) {
                    Log.e("error", "错误信息：" + error.toString());
                }
            });
            MyApplication.queues.add(jsonRequest1);
        } catch (Exception e) {
            Log.e("error", "联网失败");
            e.printStackTrace();
        }
    }
}
