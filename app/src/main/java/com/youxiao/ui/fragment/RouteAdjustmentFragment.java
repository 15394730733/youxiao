package com.youxiao.ui.fragment;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import com.youxiao.R;
import com.youxiao.adapter.CommonAdapter;
import com.youxiao.adapter.ViewHolder;

import java.util.ArrayList;
import java.util.List;

/** 路线调整Fragment
 * @author StomHong
 * @since 2016-3-25
 */
public class RouteAdjustmentFragment extends Fragment {


    private ListView mListView;
    private CommonAdapter<String> mAdapter;
    private List<String> mData;


    public RouteAdjustmentFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.tab_for_client_details2,container,false);
        initView(view);
        initData();
        initEvent();
        return view;
    }

    private void initEvent() {

    }

    private void initData() {
        mData = new ArrayList<>();
        for (int i = 0;i<12;i++){
            mData.add("");
        }
        mAdapter = new CommonAdapter<String>(getActivity(),mData,R.layout.item_route_adjustment) {
            @Override
            public void convert(ViewHolder holder, String s) {

            }
        };
        mListView.setAdapter(mAdapter);

    }

    private void initView(View view) {
        mListView = (ListView) view.findViewById(R.id.lv_customer);
    }

}
