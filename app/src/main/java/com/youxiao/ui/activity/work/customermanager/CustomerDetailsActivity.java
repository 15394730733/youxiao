package com.youxiao.ui.activity.work.customermanager;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.View;
import android.view.Window;
import android.widget.LinearLayout;

import com.youxiao.base.BaseActivity;
import com.youxiao.ui.fragment.BasicInfoFragment;
import com.youxiao.ui.fragment.MapLocationFragment;
import com.youxiao.ui.fragment.RouteAdjustmentFragment;
import com.youxiao.ui.fragment.VisitStateFragment;
import com.youxiao.R;

/**
 * @author StomHong
 * @since 2016-3-24
 */
public class CustomerDetailsActivity extends BaseActivity implements View.OnClickListener {

    private static final String TAG = CustomerDetailsActivity.class.getSimpleName();

    private Fragment mBasicInfoFragment;
    private Fragment mRouteAdjustmentFragment;
    private Fragment mVisitStateFragment;
    private Fragment mGeographicalLocationFragment;

    private View mView_BasicInfo;
    private View mView_RouteAdjust;
    private View mView_VisitState;
    private View mView_GeographicalLocation;

    private LinearLayout mLinearLayout_BasicInfo;
    private LinearLayout mLinearLayout_RouteAdjust;
    private LinearLayout mLinearLayout_VisitState;
    private LinearLayout mLinearLayout_GeographicalLocation;
    private LinearLayout mLinearLayout_Back;

    /**
     * 枚举
     */
    private enum TabIndex {
        BASIC_INFO_FRAGMENT,
        ROUTE_ADJUSTMENT_FRAGMENT,
        GEOGRAPHICAL_LOCATION_FRAGMENT,
        VISIT_STATE_FRAGMENT
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_client_details);

        super.init();
    }

    @Override
    public void initView() {

        mView_BasicInfo = findViewById(R.id.id_view_client_details_basic_info);
        mView_GeographicalLocation = findViewById(R.id.id_view_client_details_geographical_location);
        mView_RouteAdjust = findViewById(R.id.id_view_client_details_route_adjustment);
        mView_VisitState = findViewById(R.id.id_view_client_details_visit_state);

        mLinearLayout_BasicInfo = (LinearLayout) findViewById(R.id.id_lay_client_details_basic_info);
        mLinearLayout_GeographicalLocation = (LinearLayout) findViewById(R.id.id_lay_client_details_geographical_location);
        mLinearLayout_RouteAdjust = (LinearLayout) findViewById(R.id.id_lay_client_details_route_adjustment);
        mLinearLayout_VisitState = (LinearLayout) findViewById(R.id.id_lay_client_details_visit_state);

        mLinearLayout_Back = (LinearLayout) findViewById(R.id.id_lay_client_details_back);
    }

    @Override
    public void initData() {

        switchItem(TabIndex.BASIC_INFO_FRAGMENT);
    }

    @Override
    public void initEvent() {

        mLinearLayout_VisitState.setOnClickListener(this);
        mLinearLayout_RouteAdjust.setOnClickListener(this);
        mLinearLayout_GeographicalLocation.setOnClickListener(this);
        mLinearLayout_BasicInfo.setOnClickListener(this);
        mLinearLayout_Back.setOnClickListener(this);
    }

    /**
     * 根据用户的点击，改变子布局页面和Tab标签
     *
     * @param index TabIndex枚举类的下标
     */
    private void switchItem(TabIndex index) {

        resetAllTab();
        FragmentManager fm = getSupportFragmentManager();
        FragmentTransaction ft = fm.beginTransaction();

        hideAllFragment(ft);

        switch (index) {

            case BASIC_INFO_FRAGMENT:
                mView_BasicInfo.setVisibility(View.VISIBLE);
                if (mBasicInfoFragment == null) {
                    mBasicInfoFragment = new BasicInfoFragment();
                    ft.add(R.id.id_lay_client_details_for_tab, mBasicInfoFragment);
                } else {
                    ft.show(mBasicInfoFragment);
                }
                break;

            case ROUTE_ADJUSTMENT_FRAGMENT:
                mView_RouteAdjust.setVisibility(View.VISIBLE);
                if (mRouteAdjustmentFragment == null) {
                    mRouteAdjustmentFragment = new RouteAdjustmentFragment();
                    ft.add(R.id.id_lay_client_details_for_tab, mRouteAdjustmentFragment);
                } else {
                    ft.show(mRouteAdjustmentFragment);
                }
                break;

            case VISIT_STATE_FRAGMENT:
                mView_VisitState.setVisibility(View.VISIBLE);
                if (mVisitStateFragment == null) {
                    mVisitStateFragment = new VisitStateFragment();
                    ft.add(R.id.id_lay_client_details_for_tab, mVisitStateFragment);
                } else {
                    ft.show(mVisitStateFragment);
                }
                break;

            case GEOGRAPHICAL_LOCATION_FRAGMENT:
                mView_GeographicalLocation.setVisibility(View.VISIBLE);
                if (mGeographicalLocationFragment == null) {
                    mGeographicalLocationFragment = new MapLocationFragment();
                    ft.add(R.id.id_lay_client_details_for_tab, mGeographicalLocationFragment);
                } else {
                    ft.show(mGeographicalLocationFragment);
                }

                break;

            default:
                break;
        }
        ft.commit();

    }

    /**
     * 重置所有Tab
     */
    private void resetAllTab() {

        mView_BasicInfo.setVisibility(View.INVISIBLE);
        mView_VisitState.setVisibility(View.INVISIBLE);
        mView_GeographicalLocation.setVisibility(View.INVISIBLE);
        mView_RouteAdjust.setVisibility(View.INVISIBLE);

    }

    /**
     * 隐藏所有的Fragment
     *
     * @param ft Fragment事务对象实例
     */
    private void hideAllFragment(FragmentTransaction ft) {

        hideFragment(ft, mGeographicalLocationFragment);
        hideFragment(ft, mVisitStateFragment);
        hideFragment(ft, mRouteAdjustmentFragment);
        hideFragment(ft, mBasicInfoFragment);
    }

    /**
     * 隐藏Fragment
     *
     * @param ft 事务对象实例
     */
    private void hideFragment(FragmentTransaction ft, Fragment fragment) {

        if (fragment == null) return;
        ft.hide(fragment);

    }

    @Override
    public void onClick(View v) {

        switch (v.getId()) {

            case R.id.id_lay_client_details_basic_info:
                switchItem(TabIndex.BASIC_INFO_FRAGMENT);
                break;

            case R.id.id_lay_client_details_route_adjustment:
                switchItem(TabIndex.ROUTE_ADJUSTMENT_FRAGMENT);
                break;

            case R.id.id_lay_client_details_visit_state:
                switchItem(TabIndex.VISIT_STATE_FRAGMENT);
                break;

            case R.id.id_lay_client_details_geographical_location:
                switchItem(TabIndex.GEOGRAPHICAL_LOCATION_FRAGMENT);
                break;

            case R.id.id_lay_client_details_back:
                finish();
                break;

            default:
                break;
        }
    }
}
