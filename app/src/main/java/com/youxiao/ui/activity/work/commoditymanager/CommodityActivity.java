package com.youxiao.ui.activity.work.commoditymanager;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.View;
import android.view.Window;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.youxiao.R;
import com.youxiao.base.BaseActivity;
import com.youxiao.ui.fragment.CommodityBandFragment;
import com.youxiao.ui.fragment.CommodityDrawerFragment;
import com.youxiao.ui.fragment.ProcurementOrderFragment;
import com.youxiao.ui.fragment.ProcurementStockFragment;

/**
 * 商品管理
 *
 * @author StomHong
 * @since 2016-7-9
 */
public class CommodityActivity extends BaseActivity implements View.OnClickListener {

    private CommodityBandFragment mCommodityBandFragment;
    private CommodityDrawerFragment mCommodityDrawerFragment;
    private ImageView mImageView_ChangeFragment;



    private LinearLayout mLinearLayout_Back;

    private enum FragmentType {
        COMMODITY_BAND, COMMODITY_DRAWER
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_commodity);

        super.init();
    }

    @Override
    public void initView() {
        mImageView_ChangeFragment = (ImageView) findViewById(R.id.id_iv_menu);
        mLinearLayout_Back = (LinearLayout) findViewById(R.id.id_lay_commodity_back);

    }

    @Override
    public void initData() {
        selectFragment(FragmentType.COMMODITY_BAND);
    }

    @Override
    public void initEvent() {
        mLinearLayout_Back.setOnClickListener(this);
        mImageView_ChangeFragment.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.id_iv_menu:
                if (mCommodityBandFragment.isVisible()) {
                    selectFragment(FragmentType.COMMODITY_DRAWER);
                } else if (mCommodityDrawerFragment.isVisible()) {
                    selectFragment(FragmentType.COMMODITY_BAND);
                }
                break;
            case R.id.id_lay_commodity_back:
                finish();
                break;
            default:
                break;
        }

    }


    /**
     * 选择Fragment
     *
     * @param fragmentType
     */
    private void selectFragment(FragmentType fragmentType) {
        FragmentManager fm = getSupportFragmentManager();
        FragmentTransaction ft = fm.beginTransaction();
        hideAllFragment(ft);
        switch (fragmentType) {
            case COMMODITY_BAND:
                if (mCommodityBandFragment == null) {
                    mCommodityBandFragment = CommodityBandFragment.newInstance();
                    ft.add(R.id.id_fl_commodity, mCommodityBandFragment, ProcurementOrderFragment.class.getSimpleName());
                } else {
                    ft.show(mCommodityBandFragment);
                }
                break;
            case COMMODITY_DRAWER:
                if (mCommodityDrawerFragment == null) {
                    mCommodityDrawerFragment = CommodityDrawerFragment.newInstance();
                    ft.add(R.id.id_fl_commodity, mCommodityDrawerFragment, ProcurementStockFragment.class.getSimpleName());
                } else {
                    ft.show(mCommodityDrawerFragment);
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
        hideFragment(ft, mCommodityBandFragment);
        hideFragment(ft, mCommodityDrawerFragment);
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

}
