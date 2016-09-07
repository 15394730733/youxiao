package com.youxiao.ui.fragment;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.youxiao.R;
import com.youxiao.ui.activity.work.commoditymanager.CommodityActivity;

/**
 * 筛选
 *
 * @author StomHong on 2016-09-06
 */
public class ScreenFragment extends Fragment implements View.OnClickListener {


    private TextView mTextView_Cancel;
    private RelativeLayout mRelativeLayout_CustomerName;
    private RelativeLayout mRelativeLayout_CommitDateStart;
    private RelativeLayout mRelativeLayout_CommitDateEnd;
    private RelativeLayout mRelativeLayout_Reset;
    private RelativeLayout mRelativeLayout_Sure;

    private Activity mActivity;
    private CancelDrawerLayout mCancelDrawerLayout;

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        mActivity = (Activity) context;
        mCancelDrawerLayout = (CancelDrawerLayout) context;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_screen, container);
        initView(view);
        initEvent();
        return view;

    }

    private void initEvent() {
        mTextView_Cancel.setOnClickListener(this);
        mRelativeLayout_CustomerName.setOnClickListener(this);
        mRelativeLayout_CommitDateStart.setOnClickListener(this);
        mRelativeLayout_CommitDateEnd.setOnClickListener(this);
        mRelativeLayout_Sure.setOnClickListener(this);
        mRelativeLayout_Reset.setOnClickListener(this);
    }

    private void initView(View view) {
        mTextView_Cancel = (TextView) view.findViewById(R.id.tv_cancel);
        mRelativeLayout_CommitDateEnd = (RelativeLayout) view.findViewById(R.id.rl_commit_date_end);
        mRelativeLayout_CommitDateStart = (RelativeLayout) view.findViewById(R.id.rl_commit_date_start);
        mRelativeLayout_CustomerName = (RelativeLayout) view.findViewById(R.id.rl_commodity_name);
        mRelativeLayout_Reset = (RelativeLayout) view.findViewById(R.id.rl_reset);
        mRelativeLayout_Sure = (RelativeLayout) view.findViewById(R.id.rl_sure);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.rl_commit_date_end:
                break;
            case R.id.rl_commit_date_start:
                break;
            case R.id.rl_commodity_name:
                startActivity(new Intent(mActivity, CommodityActivity.class));
                mActivity.overridePendingTransition(R.anim.activity_enter, 0);
                break;
            case R.id.tv_cancel:
                mCancelDrawerLayout.cancel();
                break;
            case R.id.rl_reset:
                break;
            case R.id.rl_sure:
                mCancelDrawerLayout.cancel();
                break;
            default:
                break;
        }
    }

    public interface CancelDrawerLayout {

        void cancel();
    }

}
