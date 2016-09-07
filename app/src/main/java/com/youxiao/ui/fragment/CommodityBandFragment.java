package com.youxiao.ui.fragment;


import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.RelativeLayout;

import com.youxiao.R;
import com.youxiao.adapter.CommonAdapter;
import com.youxiao.adapter.ViewHolder;
import com.youxiao.ui.activity.sales.commodityselector.CommoditySearchActivity;
import com.youxiao.util.SetListViewHeight;

import java.util.ArrayList;
import java.util.List;

/**
 * 商品
 *
 * @author StomHong
 * @since 2016-7-9
 */
public class CommodityBandFragment extends Fragment {

    private static CommodityBandFragment mInstance;

    private List<String> mCommodities;
    private ListView mListView_CommodityBand;
    private CommonAdapter<String> mAdapter;
    private RelativeLayout mRelativeLayout_CommoditySearch;

    @SuppressLint("ValidFragment")
    private CommodityBandFragment() {

    }

    public static CommodityBandFragment newInstance() {
        if (mInstance == null) {
            mInstance = new CommodityBandFragment();
        }
        return mInstance;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_commodity_band, container, false);
        initView(view);
        initData();
        initEvent();
        return view;
    }

    private void initEvent() {
        mRelativeLayout_CommoditySearch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                intent.setClass(getActivity(), CommoditySearchActivity.class);
                startActivity(intent);
            }
        });
    }

    private void initData() {
        mCommodities = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            mCommodities.add("");
        }
        mAdapter = new CommonAdapter<String>(getActivity(), mCommodities, R.layout.item_commodity_band) {
            @Override
            public void convert(ViewHolder holder, String s) {

            }
        };
        mListView_CommodityBand.setAdapter(mAdapter);
        SetListViewHeight.setListViewHeightBasedOnChildren(mListView_CommodityBand);
        mListView_CommodityBand.setFocusable(false);
    }

    private void initView(View view) {
        mListView_CommodityBand = (ListView) view.findViewById(R.id.id_lv_commodity_band);
        mRelativeLayout_CommoditySearch = (RelativeLayout) view.findViewById(R.id.address_list_fragment_search);

    }

}
