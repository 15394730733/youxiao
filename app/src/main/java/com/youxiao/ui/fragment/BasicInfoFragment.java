package com.youxiao.ui.fragment;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.youxiao.R;
import com.youxiao.model.CustomerManagerBean;

/**
 * 基本信息Fragment
 *
 * @author StomHong
 * @since 2016-3-25
 */
public class BasicInfoFragment extends Fragment {

    private static final String TAG = BasicInfoFragment.class.getSimpleName();

    private TextView getcustomerNumber;//客户卡号
    private TextView getCustomerName;//客户名称
    private TextView getCustomerContact;//联系人
    private TextView getCustomerPhone;//客户电话
    private TextView getCustomerMobieA;//电话A
    private TextView getCustomerMobieB;//电话B
    private TextView getCustomerAddress;//客户地址
    private TextView getCustomerType;//客户类型
    private TextView getCustomerRoute;//分配路线
    private TextView getCustomerArea;//客户区域
    private TextView getCustomerPriceType;//价格类型
    private TextView getCustomerDisplayWay;//陈列方式
    private TextView getCustomerBalanceWay;//结算方式
    private TextView getCustomerDispalyArea;//陈列面积
    private TextView getCustomerBusinessArea;//营业面积

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.tab_for_client_details1, container, false);
        initView(view);
        initData();
        initEvent();
        return view;
    }

    private void initEvent() {

    }

    private void initData() {
        String custId = getArguments().getString("custId");//客户卡号
        String custName = getArguments().getString("custName");//客户名称
        String contact = getArguments().getString("contact");//联系人
        String telNo = getArguments().getString("telNo");//客户电话
        String mobilea = getArguments().getString("mobilea");//移动电话A
        String mobileb = getArguments().getString("mobileb");//移动电话B
        String Addresss = getArguments().getString("Addresss");//客户地址
        String customerTypeName = getArguments().getString("customerTypeName");//客户类型
        String line = getArguments().getString("line");//分配线路
        String customerArea = getArguments().getString("customerArea");//客户区域
        String Pricetype = getArguments().getString("Pricetype");//价格类型
        String displayName = getArguments().getString("displayName");//陈列方式
        String balance = getArguments().getString("balance");//结算方式
        String displayArea = getArguments().getString("displayArea");//陈列面积
        String businessArea = getArguments().getString("businessArea");//营业面积

        getcustomerNumber.setText(custId);
        getCustomerName.setText(custName);
        getCustomerContact.setText(contact);
        getCustomerPhone.setText(telNo);
        getCustomerMobieA.setText(mobilea);
        getCustomerMobieB.setText(mobileb);
        getCustomerAddress.setText(Addresss);
        getCustomerType.setText(customerTypeName);
        getCustomerRoute.setText(line);
        getCustomerArea.setText(customerArea);
        getCustomerPriceType.setText(Pricetype);
        getCustomerDisplayWay.setText(displayName);
        getCustomerBalanceWay.setText(balance);
        getCustomerDispalyArea.setText(displayArea);
        getCustomerBusinessArea.setText(businessArea);

    }

    private void initView(View view) {
        getcustomerNumber = (TextView) view.findViewById(R.id.customerCardNumber);
        getCustomerName = (TextView) view.findViewById(R.id.customerName);
        getCustomerContact = (TextView) view.findViewById(R.id.customerContact);
        getCustomerPhone = (TextView) view.findViewById(R.id.customerPhone);
        getCustomerMobieA = (TextView) view.findViewById(R.id.customerMobiePhoneA);
        getCustomerMobieB = (TextView) view.findViewById(R.id.customerMobiePhoneB);
        getCustomerAddress = (TextView) view.findViewById(R.id.customerAddress);
        getCustomerType = (TextView) view.findViewById(R.id.customerType);
        getCustomerRoute = (TextView) view.findViewById(R.id.customerDistributionRoute);
        getCustomerArea = (TextView) view.findViewById(R.id.customerArea);
        getCustomerPriceType = (TextView) view.findViewById(R.id.customerPriceType);
        getCustomerDisplayWay = (TextView) view.findViewById(R.id.customerWays);
        getCustomerBalanceWay = (TextView) view.findViewById(R.id.customerBalanceWay);
        getCustomerDispalyArea = (TextView) view.findViewById(R.id.customerDisplayArea);
        getCustomerBusinessArea = (TextView) view.findViewById(R.id.customerBusinessArea);


    }

}
