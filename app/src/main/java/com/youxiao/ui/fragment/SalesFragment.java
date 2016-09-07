package com.youxiao.ui.fragment;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SlidingPaneLayout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.youxiao.R;
import com.youxiao.imp.OnPaneOpenAndCloseListener;
import com.youxiao.ui.activity.MainActivity;
import com.youxiao.ui.activity.work.customermanager.CustomerManageActivity;
import com.youxiao.widget.ActionItem;
import com.youxiao.widget.TitlePopup;

import java.lang.reflect.Field;

/**
 * 营销页面
 *
 * @author StomHong
 */

public class SalesFragment extends Fragment implements View.OnClickListener, MainActivity.ViewClick {

    private static final String TAG = SalesFragment.class.getSimpleName();
    /**
     * 营销请求码
     **/
    public static final int MARKETING_FRAGMENT_REQUEST = 101;

    private LinearLayout mLinearLayout_SelectCustomer;

    private ImageView mImageView_CommoditySelect;
    private ImageView mImageView_More;
    private ImageView mImageView_Commit;
    //定义标题栏弹窗按钮
    private TitlePopup titlePopup;

    private OnPaneOpenAndCloseListener mListener;
    private SlidingPaneLayout mSlidingPaneLayout;
    private FloatingActionButton mFab;

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);

        try {
            mListener = (OnPaneOpenAndCloseListener) context;
        } catch (Exception e) {
            throw new ClassCastException(context + "must implement OnPaneOpenAndCloseListener");
        }
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.item_fragment_marketing, container, false);
        initView(view);
        initData();
        initEvent();
        return view;
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

    }

    @Override
    public void onResume() {
        super.onResume();

    }

    /**
     * 初始化数据
     */
    private void initData() {
        //给标题栏弹窗添加子类
        titlePopup.addAction(new ActionItem(getActivity(), "扫一扫", R.drawable.saoyisao));
        titlePopup.addAction(new ActionItem(getActivity(), "商品搜索", R.drawable.sousuo));
        titlePopup.addAction(new ActionItem(getActivity(), "销售模块", R.drawable.xiaoshou));
        titlePopup.addAction(new ActionItem(getActivity(), "跑单列表", R.drawable.paodan));
        titlePopup.addAction(new ActionItem(getActivity(), "结算", R.drawable.jiesuan));

        try {
            //通过反射来设置slidingPaneLayout滑出全屏
            Field field = SlidingPaneLayout.class.getDeclaredField("mOverhangSize");
            field.setAccessible(true);
            field.set(mSlidingPaneLayout, 0);
            mSlidingPaneLayout.setPanelSlideListener(new SlidingListener());
            mSlidingPaneLayout.setSliderFadeColor(getResources().getColor(android.R.color.transparent));
        } catch (Exception e) {
            e.printStackTrace();
        }

    }


    private void initEvent() {
        mLinearLayout_SelectCustomer.setOnClickListener(this);
        mFab.setOnClickListener(this);

        mImageView_CommoditySelect.setOnClickListener(this);
        mImageView_More.setOnClickListener(this);
        mImageView_Commit.setOnClickListener(this);
        mSlidingPaneLayout.setPanelSlideListener(new SlidingListener());
    }


    private void initView(View view) {
        mSlidingPaneLayout = (SlidingPaneLayout) view.findViewById(R.id.sliding_pane_layout);
        mFab = (FloatingActionButton) view.findViewById(R.id.fab);


        mImageView_CommoditySelect = (ImageView) view.findViewById(R.id.iv_marketing_select);
        mImageView_More = (ImageView) view.findViewById(R.id.iv_marketing_more);
        mImageView_Commit = (ImageView) view.findViewById(R.id.iv_sales_commit);

        mLinearLayout_SelectCustomer = (LinearLayout) view.findViewById(R.id.ll_marketing_select_customer);
        //实例化标题栏弹窗
        titlePopup = new TitlePopup(getActivity().getApplicationContext(), ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.fab:
                mSlidingPaneLayout.openPane();
                break;
            case R.id.iv_sales_commit:
                mSlidingPaneLayout.closePane();
                break;
            case R.id.iv_marketing_select:

                break;
            case R.id.iv_marketing_more:
                titlePopup.show(v);
                break;
            case R.id.ll_marketing_select_customer:
                handleSelectCustomer();
                break;
            default:
                break;
        }
    }


    private void handleSelectCustomer() {
        Intent intent = new Intent();
        intent.setClass(getActivity(), CustomerManageActivity.class);
        startActivity(intent);
    }

    @Override
    public void onViewClick(View v) {
        mSlidingPaneLayout.closePane();
    }


    private class SlidingListener implements SlidingPaneLayout.PanelSlideListener {

        @Override
        public void onPanelSlide(View panel, float slideOffset) {

        }

        @Override
        public void onPanelOpened(View panel) {
            mFab.setVisibility(View.INVISIBLE);
            mImageView_Commit.setVisibility(View.VISIBLE);
            mListener.onPaneOpenAndClose(true);
        }

        @Override
        public void onPanelClosed(View panel) {
            mFab.setVisibility(View.VISIBLE);
            mImageView_Commit.setVisibility(View.INVISIBLE);
            mListener.onPaneOpenAndClose(false);

        }
    }


}




