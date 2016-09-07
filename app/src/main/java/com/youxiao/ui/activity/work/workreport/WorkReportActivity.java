package com.youxiao.ui.activity.work.workreport;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.youxiao.base.BaseActivity;
import com.youxiao.R;
import com.youxiao.adapter.WorkReportAdapter;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * 工作日报
 */
public class WorkReportActivity extends BaseActivity implements View.OnClickListener {


    private LinearLayout mLinearLayout_Back;
    private ImageView mImageView_MyReception;
    private ImageView mImageView_MySend;
    private RecyclerView mRecyclerView;
    private List<Integer> mImages;
    private List<String> mNames;

    private WorkReportAdapter mAdapter;

    private Integer[] image = new Integer[]{R.drawable.day_report, R.drawable.week_report, R.drawable.month_report};
    private String[] name = new String[]{"日报", "周报", "月报"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_work_report);

        super.init();
    }

    @Override
    public void initView() {
        mLinearLayout_Back = (LinearLayout) findViewById(R.id.id_ll_work_report_back);
        mImageView_MyReception = (ImageView) findViewById(R.id.id_iv_my_reception);
        mImageView_MySend = (ImageView) findViewById(R.id.id_iv_my_send);

        mRecyclerView = (RecyclerView) findViewById(R.id.recyclerview);
    }

    @Override
    public void initData() {
        mImages = new ArrayList<>();
        mImages.addAll(Arrays.asList(image));
        mNames = new ArrayList<>();
        mNames.addAll(Arrays.asList(name));

        mAdapter = new WorkReportAdapter(mImages, mNames);
        mRecyclerView.setAdapter(mAdapter);
        mRecyclerView.setLayoutManager(new GridLayoutManager(this, 3));

    }

    @Override
    public void initEvent() {

        mImageView_MyReception.setOnClickListener(this);
        mImageView_MySend.setOnClickListener(this);
        mLinearLayout_Back.setOnClickListener(this);

        mAdapter.setOnItemClickLitener(new WorkReportAdapter.OnItemClickLitener() {
            @Override
            public void onItemClick(View view, int position) {
                Intent intent = new Intent();
                switch (position) {
                    case 0://日报
                        intent.setClass(getContext(), DayReportActivity.class);
                        break;
                    case 1://周报
                        intent.setClass(getContext(), WeekReportActivity.class);
                        break;
                    case 2:
                        intent.setClass(getContext(), MonthReportActivity.class);
                        break;
                }
                startActivity(intent);
            }

            @Override
            public void onItemLongClick(View view, int position) {

            }
        });

    }

    @Override
    public void onClick(View v) {
        Intent intent = new Intent();
        switch (v.getId()) {
            case R.id.id_ll_work_report_back:
                finish();
                break;
            case R.id.id_iv_my_reception:
                intent.setClass(this, ReportDetailActivity.class);
                startActivity(intent);
                break;
            case R.id.id_iv_my_send:
                intent.setClass(this, ReportDetailActivity.class);
                startActivity(intent);
                break;
            default:
                break;
        }
    }
}
