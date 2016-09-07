package com.youxiao.ui.fragment;


import android.annotation.SuppressLint;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.youxiao.R;

import java.util.Arrays;
import java.util.LinkedList;

/**
 * 商品
 *
 * @author StomHong
 * @since 2016-8-3
 */
public class CommodityFragment extends Fragment implements SwipeRefreshLayout.OnRefreshListener{

    private static CommodityFragment mInstance;
    private LinkedList<String> mListItems;
    private ArrayAdapter<String> mAdapter;
    public SwipeRefreshLayout mSwipeRefreshLayout;
    private ListView mListView_Commodity;

    private Handler mHandler = new Handler();
    private Runnable mRunnableDone = new Runnable() {
        @Override
        public void run() {
            mListItems.addFirst("商品");
            mSwipeRefreshLayout.setRefreshing(false);
            mAdapter.notifyDataSetChanged();
        }
    };


    @SuppressLint("ValidFragment")
    private CommodityFragment() {
        // Required empty public constructor
    }

    public static CommodityFragment newInstance(){
        if (mInstance == null){
            mInstance = new CommodityFragment();
        }
        return mInstance;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_commodity, container, false);
        initView(view);
        initData();
        initEvent();
        return view;
    }

    private void initEvent() {
        mSwipeRefreshLayout.setOnRefreshListener(this);
        mSwipeRefreshLayout.setRefreshing(true);

    }


    private void initView(View view) {
        mSwipeRefreshLayout = (SwipeRefreshLayout) view.findViewById(R.id.swipe_refresh_layout);
        mListView_Commodity = (ListView) view.findViewById(R.id.lv_commodity);
    }

    private void initData() {
        mSwipeRefreshLayout.setColorSchemeResources(R.color.red400);
        mListItems = new LinkedList<>();
        mListItems.addAll(Arrays.asList(mStrings));
        mAdapter = new ArrayAdapter<>(getActivity(), android.R.layout.simple_list_item_1, mListItems);
        mListView_Commodity.setAdapter(mAdapter);
    }

    @Override
    public void onResume() {
        super.onResume();
mSwipeRefreshLayout.setRefreshing(true);
    }


    private String[] mStrings = {"Abbaye de Belloc", "Abbaye du Mont des Cats", "Abertam", "Abondance", "Ackawi",
            "Acorn", "Adelost", "Affidelice au Chablis", "Afuega'l Pitu", "Airag", "Airedale", "Aisy Cendre",
            "Allgauer Emmentaler", "Abbaye de Belloc", "Abbaye du Mont des Cats", "Abertam", "Abondance", "Ackawi",
            "Acorn", "Adelost", "Affidelice au Chablis", "Afuega'l Pitu", "Airag", "Airedale", "Aisy Cendre",
            "Allgauer Emmentaler"};

    @Override
    public void onRefresh() {
        refresh();
    }

    private void refresh() {
        mHandler.postDelayed(mRunnableDone,3000);
    }
}
