package com.youxiao.base;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

/**
 * @author StomHong
 * @since 2016-09-01
 */
public abstract class BaseFragment extends Fragment {

    private Context mContext;

    @Override
    public final void onAttach(Context context) {
        super.onAttach(context);
        mContext = context;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
    }

    @Nullable
    @Override
    public final View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(getRootView(),null);
        initView(v);
        initData();
        initEvent();
        return v;

    }

    protected abstract int getRootView();

    public abstract void initView(View v);

    public abstract void initData();

    public abstract void initEvent();
    public Context getContext(){
        return mContext;
    }


}
