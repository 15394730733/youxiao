package com.youxiao.ui.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;

import com.youxiao.R;
import com.youxiao.ui.activity.sales.commodityselector.CommoditySearchActivity;

/**
 * 消息
 * <p/>
 * Created by 张小布 on 2016/4/22.
 */
public class MessageFragment extends Fragment implements View.OnClickListener {

    private RelativeLayout search,one, two, three, fourth, five;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.enterprise_message, container, false);
        initView(view);
        initEvent();
        return view;
    }

    private void initView(View view) {
        search = (RelativeLayout) view.findViewById(R.id.enterprise_message_search);
        one = (RelativeLayout) view.findViewById(R.id.enterprise_message_one);
        two = (RelativeLayout) view.findViewById(R.id.enterprise_message_two);
        three = (RelativeLayout) view.findViewById(R.id.enterprise_message_three);
        fourth = (RelativeLayout) view.findViewById(R.id.enterprise_message_fourth);
        five = (RelativeLayout) view.findViewById(R.id.enterprise_message_five);
    }

    private void initEvent() {
        search.setOnClickListener(this);
        one.setOnClickListener(this);
        two.setOnClickListener(this);
        three.setOnClickListener(this);
        fourth.setOnClickListener(this);
        five.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.enterprise_message_search:
                startActivity(new Intent(getActivity(), CommoditySearchActivity.class));
                break;
            case R.id.enterprise_message_one:
                break;
            case R.id.enterprise_message_two:
                break;
            case R.id.enterprise_message_three:
                break;
            case R.id.enterprise_message_fourth:
                break;
            case R.id.enterprise_message_five:
                break;
        }
    }
}
