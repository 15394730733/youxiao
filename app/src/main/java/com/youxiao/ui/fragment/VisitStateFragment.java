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

/**
 * 拜访状态Fragment
 *
 * @author StomHong
 * @since 2016-3-25
 */
public class VisitStateFragment extends Fragment {


    private ListView mListView;
    private CommonAdapter<Integer> mAdapter;
    private List<Integer> mData;


    public VisitStateFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.tab_for_client_details3, container, false);
        initView(view);
        initData();
        initEvent();
        return view;
    }

    private void initData() {
        mData = new ArrayList<>();
        for (int i = 0; i < 12; i++) {
            mData.add(i);
        }
        mAdapter = new CommonAdapter<Integer>(getActivity(), mData, R.layout.item_visit_state) {
            @Override
            public void convert(ViewHolder holder, Integer integer) {
                if (integer == 0) {
                } else {
                    holder.setViewVisibility(R.id.top, true);
                }
                if (integer == 11) {
                    holder.setViewVisibility(R.id.bottom, false);
                } else {
                    holder.setViewVisibility(R.id.bottom, true);
                }
                if (integer % 2 == 0) {
                    holder.setImageFromResource(R.id.iv_visit_state, R.drawable.shape_circle_red);
                } else {
                    holder.setImageFromResource(R.id.iv_visit_state, R.drawable.image_ring);
                }

            }
        };
        mListView.setAdapter(mAdapter);
    }

    private void initEvent() {

    }

    private void initView(View view) {
        mListView = (ListView) view.findViewById(R.id.lv_visit_state);
    }

}
