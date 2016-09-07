package com.youxiao.ui.fragment;


import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AbsListView;
import android.widget.ListView;
import android.widget.RelativeLayout;

import com.youxiao.R;
import com.youxiao.adapter.CommonAdapter;
import com.youxiao.adapter.ViewHolder;
import com.youxiao.ui.activity.sales.commodityselector.CommoditySearchActivity;
import com.youxiao.widget.SyncScrollListView;

import java.util.ArrayList;
import java.util.List;

/**
 * 商品
 *
 * @author StomHong
 * @since 2016-7-9
 */
public class CommodityDrawerFragment extends Fragment implements SyncScrollListView.ListViewScrollListener {

    private static CommodityDrawerFragment mInstance;

    private List<String> mLeftCommodities;
    private List<String> mRightCommodities;
    private CommonAdapter<String> mLeftAdapter;
    private CommonAdapter<String> mRightAdapter;
    private SyncScrollListView mSyncScrollListView_CommodityLeft;
    private SyncScrollListView mSyncScrollListView_CommodityRight;
    private RelativeLayout mRelativeLayout_CommoditySearch;

    @SuppressLint("ValidFragment")
    private CommodityDrawerFragment() {
        // Required empty public constructor
    }

    /**
     * 获得此Fragment的实例对象
     *
     * @return CommodityDrawerFragment的实例对象
     */
    public static CommodityDrawerFragment newInstance() {
        if (mInstance == null) {
            mInstance = new CommodityDrawerFragment();
        }
        return mInstance;
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_commodity_drawer, container, false);
        initView(view);
        initData();
        initEvent();
        return view;
    }

    private void initData() {
        mLeftCommodities = new ArrayList<>();
        for (int i = 0; i < 15; i++) {
            mLeftCommodities.add("");
        }

        mRightCommodities = new ArrayList<>();
        for (int i = 0; i < 15; i++) {
            mRightCommodities.add("");
        }
        mLeftAdapter = new CommonAdapter<String>(getActivity(), mLeftCommodities, R.layout.item_commodity_drawer_left) {
            @Override
            public void convert(ViewHolder holder, String s) {

            }
        };
        mRightAdapter = new CommonAdapter<String>(getActivity(), mRightCommodities, R.layout.item_commodity_drawer_right) {
            @Override
            public void convert(ViewHolder holder, String s) {

            }
        };
        mSyncScrollListView_CommodityLeft.setAdapter(mLeftAdapter);
        mSyncScrollListView_CommodityRight.setAdapter(mRightAdapter);
        setListViewOnTouchAndScrollListener(mSyncScrollListView_CommodityLeft, mSyncScrollListView_CommodityRight);

    }

    private void initEvent() {
        mSyncScrollListView_CommodityRight.setListViewScrollListener(this);
        mSyncScrollListView_CommodityLeft.setListViewScrollListener(this);
        mRelativeLayout_CommoditySearch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                intent.setClass(getActivity(), CommoditySearchActivity.class);
                startActivity(intent);
            }
        });
    }

    private void initView(View view) {
        mSyncScrollListView_CommodityLeft = (SyncScrollListView) view.findViewById(R.id.id_lv_commodity_drawer_left);
        mSyncScrollListView_CommodityRight = (SyncScrollListView) view.findViewById(R.id.id_lv_commodity_drawer_right);
        mRelativeLayout_CommoditySearch = (RelativeLayout) view.findViewById(R.id.address_list_fragment_search);
    }

    @Override
    public void onScrollChanged(SyncScrollListView scrollView, int l, int t, int oldl, int oldt) {
        if (scrollView == mSyncScrollListView_CommodityLeft) {
            mSyncScrollListView_CommodityRight.scrollTo(l, t);
        } else if (scrollView == mSyncScrollListView_CommodityRight) {
            mSyncScrollListView_CommodityLeft.scrollTo(l, t);
        }

    }


    /**
     * 设置两个ListView联合滚动监听
     *
     * @param listView1
     * @param listView2
     */
    public void setListViewOnTouchAndScrollListener(final ListView listView1, final ListView listView2) {
        //设置listview2列表的scroll监听，用于滑动过程中左右不同步时校正
        listView2.setOnScrollListener(new AbsListView.OnScrollListener() {

            @Override
            public void onScrollStateChanged(AbsListView view, int scrollState) {
                //如果停止滑动
                if (scrollState == 0 || scrollState == 1 || scrollState == 2) {
                    //获得第一个子view
                    View subView = view.getChildAt(0);

                    if (subView != null) {
                        final int top = subView.getTop();
                        final int top1 = listView1.getChildAt(0).getTop();
                        final int position = view.getFirstVisiblePosition();

                        //如果两个首个显示的子view高度不等
//                        if (top != top1) {
                            listView1.setSelectionFromTop(position, top);
                            listView2.setSelectionFromTop(position, top);
//                        }
                    }
                }

            }

            @Override
            public void onScroll(AbsListView view, final int firstVisibleItem,
                                 int visibleItemCount, int totalItemCount) {
                View subView = view.getChildAt(0);
                if (subView != null) {
                    final int top = subView.getTop();

                    //      //如果两个首个显示的子view高度不等
                    int top1 = listView1.getChildAt(0).getTop();
//                    if (!(top1 - 7 < top && top < top1 + 7)) {
                        listView1.setSelectionFromTop(firstVisibleItem, top);
                        listView2.setSelectionFromTop(firstVisibleItem, top);
//                    }

                }
            }
        });

        //设置listview1列表的scroll监听，用于滑动过程中左右不同步时校正
        listView1.setOnScrollListener(new AbsListView.OnScrollListener() {

            @Override
            public void onScrollStateChanged(AbsListView view, int scrollState) {
                if (scrollState == 0 || scrollState == 1 || scrollState == 2) {
                    //获得第一个子view
                    View subView = view.getChildAt(0);

                    if (subView != null) {
                        final int top = subView.getTop();
                        final int top1 = listView2.getChildAt(0).getTop();
                        final int position = view.getFirstVisiblePosition();

                        //如果两个首个显示的子view高度不等
                        if (top != top1) {
                            listView1.setSelectionFromTop(position, top);
                            listView2.setSelectionFromTop(position, top);
                        }
                    }
                }
            }

            @Override
            public void onScroll(AbsListView view, final int firstVisibleItem,
                                 int visibleItemCount, int totalItemCount) {
                View subView = view.getChildAt(0);
                if (subView != null) {
                    final int top = subView.getTop();
                    listView1.setSelectionFromTop(firstVisibleItem, top);
                    listView2.setSelectionFromTop(firstVisibleItem, top);

                }
            }
        });
    }


}
