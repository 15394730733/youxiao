package com.youxiao.ui.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ListView;

import com.youxiao.R;
import com.youxiao.adapter.CommonAdapter;

import java.util.List;


/**
 * 微通
 * @author Stom
 */
public class MicroChatFragment extends Fragment implements View.OnClickListener{

    private ListView mListView_EnterpriseInfoFragment;

    private List<String> mDatas;
    private CommonAdapter<String> adapter;

    private Button addressList,message,sales;
    private AddressListFragment addressListFragment;
    private MessageFragment messageFragment;
    public static final int ADDRESSLIST_FRAGMENT_TYPE = 1;
    public static final int MESSAGE_FRAGMENT_TYPE = 2;
    public static final int SALES_FRAGMENT_TYPE = 3;

    public int currentFragmentType = -1;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.item_fragment_micro_chat, container, false);
        initView(view);
        initData(savedInstanceState);
        initEvent();
        return view;
    }

    private void initView(View view) {
        addressList = (Button) view.findViewById(R.id.btn_address_list);
        message = (Button) view.findViewById(R.id.btn_message);
        sales = (Button) view.findViewById(R.id.btn_sales);
    }

    private void initEvent() {
        addressList.setOnClickListener(this);
        message.setOnClickListener(this);
        sales.setOnClickListener(this);
    }

    private void initData(Bundle savedInstanceState) {
        FragmentManager fragmentManager = getFragmentManager();
        if (savedInstanceState != null) {
            int type = savedInstanceState.getInt("currentFragmentType");
            System.out.println("-------type-------"+type);
            addressListFragment = (AddressListFragment) fragmentManager.findFragmentByTag("addressListFragment");
            messageFragment = (MessageFragment) fragmentManager.findFragmentByTag("messageFragment");
            if (type > 0)
                loadFragment(type);
        } else {
            FragmentTransaction transaction = fragmentManager
                    .beginTransaction();
            Fragment oneFragment = fragmentManager.findFragmentByTag("messageFragment");
            Fragment twoFragment = fragmentManager.findFragmentByTag("salesCircleFragment");
            if (oneFragment != null) {
                transaction.replace(R.id.item_fragment_enterprise_frameLayout, oneFragment);
                transaction.commit();
            }else if (twoFragment != null){
                transaction.replace(R.id.item_fragment_enterprise_frameLayout, twoFragment);
                transaction.commit();
            }else {
                loadFragment(ADDRESSLIST_FRAGMENT_TYPE);
            }
        }
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putInt("lastFragmentTag", currentFragmentType);
    }

    private void switchFragment(int type) {
        switch (type) {
            case ADDRESSLIST_FRAGMENT_TYPE:
                loadFragment(ADDRESSLIST_FRAGMENT_TYPE);
                break;
            case MESSAGE_FRAGMENT_TYPE:
                loadFragment(MESSAGE_FRAGMENT_TYPE);
                break;
            case SALES_FRAGMENT_TYPE:
                loadFragment(SALES_FRAGMENT_TYPE);
                break;
        }

    }

    private void loadFragment(int type) {
        FragmentManager fragmentManager = getFragmentManager();
        FragmentTransaction transaction = fragmentManager.beginTransaction();
        if (type == ADDRESSLIST_FRAGMENT_TYPE) {
            if (addressListFragment == null) {
                addressListFragment = new AddressListFragment();

                transaction.add(R.id.item_fragment_enterprise_frameLayout, addressListFragment,"addressListFragment");
            } else {
                transaction.show(addressListFragment);
            }
            if (messageFragment != null) {
                transaction.hide(messageFragment);
            }

            currentFragmentType = ADDRESSLIST_FRAGMENT_TYPE;
        } else if (type == MESSAGE_FRAGMENT_TYPE){
            if (messageFragment == null) {
                messageFragment = new MessageFragment();
                transaction.add(R.id.item_fragment_enterprise_frameLayout, messageFragment,"messageFragment");
            } else {
                transaction.show(messageFragment);
            }
            if (addressListFragment != null) {
                transaction.hide(addressListFragment);
            }

            currentFragmentType = MESSAGE_FRAGMENT_TYPE;
        }else {

            if (addressListFragment != null){
                transaction.hide(addressListFragment);
            }
            if (messageFragment != null){
                transaction.hide(messageFragment);
            }
            currentFragmentType = SALES_FRAGMENT_TYPE;
        }
        transaction.commitAllowingStateLoss();
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.btn_address_list:
                addressList.setBackgroundResource(R.drawable.tongxunlu_a);
                message.setBackgroundResource(R.drawable.xiaoxi);
                sales.setBackgroundResource(R.drawable.xiaosoquan);
                switchFragment(ADDRESSLIST_FRAGMENT_TYPE);
                break;
            case R.id.btn_message:
                addressList.setBackgroundResource(R.drawable.tongxuanlu);
                message.setBackgroundResource(R.drawable.xiaoxi_a);
                sales.setBackgroundResource(R.drawable.xiaosoquan);
                switchFragment(MESSAGE_FRAGMENT_TYPE);
                break;
            case R.id.btn_sales:
                addressList.setBackgroundResource(R.drawable.tongxuanlu);
                message.setBackgroundResource(R.drawable.xiaoxi);
                sales.setBackgroundResource(R.drawable.xiaosouquan_a);
                switchFragment(SALES_FRAGMENT_TYPE);
                break;
        }
    }
}
