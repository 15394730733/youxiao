package com.youxiao.ui.activity.work.taskboost;

import android.app.AlertDialog;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.youxiao.base.BaseActivity;
import com.youxiao.ui.fragment.CommodityFragment;
import com.youxiao.ui.fragment.EmployeeFragment;
import com.youxiao.R;
import com.youxiao.util.GetSystemTime;
import com.youxiao.widget.Util;
import com.youxiao.widget.WheelView;

/**
 * 销售推进
 *
 * @author StomHong
 * @since 2016-8-3
 */
public class SalesBoostActivity extends BaseActivity implements View.OnClickListener {

    private LinearLayout mLinearLayout_Back;
    private CommodityFragment mCommodityFragment;
    private EmployeeFragment mEmployeeFragment;

    private RelativeLayout mRelativeLayout_Commodity;
    private RelativeLayout mRelativeLayout_Employee;
    private RelativeLayout mRelativeLayout_Date;

    private ImageView mImageView_IndicatorCommodity;
    private ImageView mImageView_IndicatorEmployee;

    private TextView mTextView_Commodity;
    private TextView mTextView_Employee;
    private TextView mTextView_Date;


    private View mTimePickerView;
    private AlertDialog mProduceDateDialog;

    private WheelView mWheelView_Year;
    private WheelView mWheelView_Month;
    private String currentYear;
    private String currentMonth;

    private enum FragmentType {
        COMMODITY, EMPLOYEE
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sales_boost);
        super.init();
    }

    @Override
    public void initView() {
        mLinearLayout_Back = (LinearLayout) findViewById(R.id.id_ll_sales_boost_back);
        mRelativeLayout_Commodity = (RelativeLayout) findViewById(R.id.id_rl_sales_boost_commodity);
        mRelativeLayout_Employee = (RelativeLayout) findViewById(R.id.id_rl_sales_boost_employee);
        mRelativeLayout_Date = (RelativeLayout) findViewById(R.id.id_rl_sales_boost_date);

        mImageView_IndicatorCommodity = (ImageView) findViewById(R.id.id_iv_sales_boost_indicator_commodity);
        mImageView_IndicatorEmployee = (ImageView) findViewById(R.id.id_iv_sales_boost_indicator_employee);
        mTextView_Commodity = (TextView) findViewById(R.id.id_tv_sales_boost_commodity);
        mTextView_Employee = (TextView) findViewById(R.id.id_tv_sales_boost_employee);
        mTextView_Date = (TextView) findViewById(R.id.id_tv_sales_boost_date);

    }

    @Override
    public void initData() {
        selectFragment(FragmentType.EMPLOYEE);
        currentYear = GetSystemTime.getTime("yyyy");
        currentMonth = GetSystemTime.getTime("MM");
        mTextView_Date.setText(currentYear + "年" + currentMonth + "月");
    }

    @Override
    public void initEvent() {
        mLinearLayout_Back.setOnClickListener(this);
        mRelativeLayout_Commodity.setOnClickListener(this);
        mRelativeLayout_Employee.setOnClickListener(this);
        mRelativeLayout_Date.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.id_ll_sales_boost_back:
                finish();
                break;
            case R.id.id_rl_sales_boost_commodity:
                selectFragment(FragmentType.COMMODITY);
                break;
            case R.id.id_rl_sales_boost_employee:
                selectFragment(FragmentType.EMPLOYEE);
                break;
            case R.id.id_rl_sales_boost_date:
                processDateDialog();
                break;
            case R.id.id_tv_time_picker_cancel:
                mProduceDateDialog.dismiss();
                break;
            case R.id.id_tv_time_picker_confirm:
                String year = mWheelView_Year.getSelectedText();
                String month = mWheelView_Month.getSelectedText();
                String date = year + "年" + month + "月";
                mTextView_Date.setText(date);

                break;
            default:
                break;
        }

    }

    private void selectFragment(FragmentType fragmentType) {
        FragmentManager fm = getSupportFragmentManager();
        FragmentTransaction ft = fm.beginTransaction();
        hideAllFragment(ft);
        Animation animation;
        switch (fragmentType) {
            case COMMODITY:
                animation = AnimationUtils.loadAnimation(this, R.anim.tab_indicator_right);
                mImageView_IndicatorCommodity.setAnimation(animation);
                mImageView_IndicatorCommodity.setVisibility(View.VISIBLE);
                mImageView_IndicatorEmployee.setVisibility(View.INVISIBLE);
                mTextView_Commodity.setTextColor(Color.parseColor("#ef5350"));
                mTextView_Employee.setTextColor(Color.parseColor("#616161"));

                if (mCommodityFragment == null) {
                    mCommodityFragment = CommodityFragment.newInstance();
                    ft.add(R.id.id_fl_tabcontent, mCommodityFragment, EmployeeFragment.class.getSimpleName());
                } else {
                    ft.show(mCommodityFragment);
                    mCommodityFragment.mSwipeRefreshLayout.setRefreshing(true);

                }
                break;
            case EMPLOYEE:
                if (mEmployeeFragment != null) {
                    animation = AnimationUtils.loadAnimation(this, R.anim.tab_indicator_left);
                    mImageView_IndicatorEmployee.setAnimation(animation);
                }
                mImageView_IndicatorEmployee.setVisibility(View.VISIBLE);
                mImageView_IndicatorCommodity.setVisibility(View.INVISIBLE);
                mTextView_Commodity.setTextColor(Color.parseColor("#616161"));
                mTextView_Employee.setTextColor(Color.parseColor("#ef5350"));
                if (mEmployeeFragment == null) {
                    mEmployeeFragment = EmployeeFragment.newInstance();
                    ft.add(R.id.id_fl_tabcontent, mEmployeeFragment, EmployeeFragment.class.getSimpleName());
                } else {
                    ft.show(mEmployeeFragment);
                    mEmployeeFragment.mSwipeRefreshLayout.setRefreshing(true);

                }
                break;
            default:
                break;
        }
        ft.commit();
    }


    /**
     * 隐藏所有Fragment
     *
     * @param ft
     */
    private void hideAllFragment(FragmentTransaction ft) {
        hideFragment(ft, mEmployeeFragment);
        hideFragment(ft, mCommodityFragment);
    }

    /**
     * 隐藏Fragment
     *
     * @param ft
     * @param fragment
     */
    private void hideFragment(FragmentTransaction ft, Fragment fragment) {
        if (fragment == null)
            return;
        ft.hide(fragment);
    }

    /**
     * 日期选择Dialog
     */
    private void processDateDialog() {

        mTimePickerView = LayoutInflater.from(getContext()).inflate(R.layout.layout_time_picker_layout2, null);

        TextView mTextView_Cancel = (TextView) mTimePickerView.findViewById(R.id.id_tv_time_picker_cancel);
        TextView mTextView_Confirm = (TextView) mTimePickerView.findViewById(R.id.id_tv_time_picker_confirm);
        mTextView_Cancel.setOnClickListener(this);
        mTextView_Confirm.setOnClickListener(this);

        mWheelView_Year = (WheelView) mTimePickerView.findViewById(R.id.year);
        mWheelView_Month = (WheelView) mTimePickerView.findViewById(R.id.month);

        mProduceDateDialog = new AlertDialog.Builder(getContext(), R.style.dialog1).create();
        mProduceDateDialog.show();
        mProduceDateDialog.setContentView(mTimePickerView);
        WindowManager.LayoutParams params = mProduceDateDialog.getWindow().getAttributes();
        params.width = Util.getScreenWidth(getContext());
        params.dimAmount = 0.3f;
        params.gravity = Gravity.BOTTOM;
        mProduceDateDialog.getWindow().setAttributes(params);

    }
}
