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
 * 职员
 *
 * @author StomHong
 * @since 2016-8-3
 */
public class EmployeeFragment extends Fragment implements SwipeRefreshLayout.OnRefreshListener {

    private static EmployeeFragment mInstance;
    private LinkedList<String> mListItems;
    private ArrayAdapter<String> mAdapter;
    public SwipeRefreshLayout mSwipeRefreshLayout;
    private ListView mListView_Employee;

    private Handler mHandler = new Handler();
    private Runnable mRunnableDone = new Runnable() {
        @Override
        public void run() {
            mListItems.addFirst("员工");
            mSwipeRefreshLayout.setRefreshing(false);
            mAdapter.notifyDataSetChanged();
        }
    };

    @SuppressLint("ValidFragment")
    private EmployeeFragment() {
        // Required empty public constructor
    }

    public static EmployeeFragment newInstance() {
        if (mInstance == null) {
            mInstance = new EmployeeFragment();
        }
        return mInstance;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_employee, container, false);
        initView(view);
        initData();
        initEvent();
        return view;
    }

    @Override
    public void onResume() {
        super.onResume();
        mSwipeRefreshLayout.setRefreshing(true);
    }

    private void initView(View view) {
        mSwipeRefreshLayout = (SwipeRefreshLayout) view.findViewById(R.id.swipe_refresh_layout);
        mListView_Employee = (ListView) view.findViewById(R.id.lv_employee);
    }

    private void initData() {
        mSwipeRefreshLayout.setColorSchemeResources(R.color.red400);
        mListItems = new LinkedList<>();
        mListItems.addAll(Arrays.asList(mStrings));
        mAdapter = new ArrayAdapter<>(getActivity(), android.R.layout.simple_list_item_1, mListItems);
        mListView_Employee.setAdapter(mAdapter);


    }

    private void initEvent() {
        mSwipeRefreshLayout.setOnRefreshListener(this);
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
        mHandler.postDelayed(mRunnableDone, 3000);
    }
}
