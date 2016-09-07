package com.youxiao.ui.fragment;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.youxiao.R;

/** 基本信息Fragment
 * @author StomHong
 * @since 2016-3-25
 */
public class BasicInfoFragment extends Fragment {

    private static final String TAG = BasicInfoFragment.class.getSimpleName();

    private TextView mTextView_DisplayArea;
    private TextView mTextView_OperatingArea;
    private TextView mTextView_AccountWay;
    private TextView mTextView_CustomerAddress;
    private TextView mTextView_CustomerCard;
    private TextView mTextView_DisplayWay;
    private TextView mTextView_PriceType;
    private TextView mTextView_Contact;
    private TextView mTextView_MobilePhoneA;
    private TextView mTextView_MobilePhoneB;
    private TextView mTextView_DistributeRoute;
    private TextView mTextView_CustomerName;
    private TextView mTextView_CustomerType;
    private TextView mTextView_CustomerArea;
    private TextView mTextView_CustomerTelephone;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.tab_for_client_details1,container,false);
        initView(view);
        initData();
        initEvent();
        return view;
    }

    private void initEvent() {


    }

    private void initData() {


////        STR_T_ARCH_CUSTOMER  selectedCustomer = (STR_T_ARCH_CUSTOMER)getActivity().getIntent().getSerializableExtra("selectedCustomer");
//        if (selectedCustomer != null) {
////            mTextView_AccountWay.setText(selectedCustomer.getCHECKOUT_STATUS());
////            mTextView_OperatingArea.setText(selectedCustomer.getOPERATING_AREA());
////            mTextView_DistributeRoute.setText(GLOBAL.LINE_NAME);
////            mTextView_MobilePhoneB.setText(selectedCustomer.getMOBILE2());
////            mTextView_MobilePhoneA.setText(selectedCustomer.getMOBILE1());
////            mTextView_CustomerAddress.setText(selectedCustomer.getCUST_ADDR());
////            mTextView_CustomerCard.setText(selectedCustomer.getCUST_NO());
////            mTextView_Contact.setText(selectedCustomer.getCONTACT());
////            mTextView_DisplayArea.setText(selectedCustomer.getDISPLAY_AREA());
////            mTextView_DisplayWay.setText(selectedCustomer.getDISPLAY_NAME());
////            mTextView_PriceType.setText(selectedCustomer.getPRICE_TYPE());
////            mTextView_CustomerName.setText(selectedCustomer.getCUST_NAME());
////            mTextView_CustomerArea.setText(selectedCustomer.getCUST_GROUP());
////            mTextView_CustomerTelephone.setText(selectedCustomer.getTEL_NO());
////            mTextView_CustomerType.setText(selectedCustomer.getCUST_TYPE());
//
//
//        }else {
//            Toast.makeText(getActivity(),"所获取的客户档案实例为空，请检查！",Toast.LENGTH_SHORT).show();
//        }

    }

    private void initView(View view) {

//        mTextView_OperatingArea = (TextView) view.findViewById(R.id.id_tv_client_details_basic_info_operating_area);
//        mTextView_AccountWay = (TextView) view.findViewById(R.id.id_tv_client_details_basic_info_account_way);
//        mTextView_CustomerAddress = (TextView) view.findViewById(R.id.id_tv_client_details_basic_info_customer_address);
//        mTextView_CustomerCard = (TextView) view.findViewById(R.id.id_tv_client_details_basic_info_customer_card);
//        mTextView_DisplayWay = (TextView) view.findViewById(R.id.id_tv_client_details_basic_info_display_way);
//        mTextView_DisplayArea = (TextView) view.findViewById(R.id.id_tv_client_details_basic_info_display_area);
//        mTextView_PriceType = (TextView) view.findViewById(R.id.id_tv_client_details_basic_info_price_type);
//        mTextView_Contact = (TextView) view.findViewById(R.id.id_tv_client_details_basic_info_contact);
//        mTextView_MobilePhoneA = (TextView) view.findViewById(R.id.id_tv_client_details_basic_info_mobile_phone_a);
//        mTextView_MobilePhoneB = (TextView) view.findViewById(R.id.id_tv_client_details_basic_info_mobile_phone_b);
//        mTextView_DistributeRoute = (TextView) view.findViewById(R.id.id_tv_client_details_basic_info_distribute_route);
//        mTextView_CustomerName = (TextView) view.findViewById(R.id.id_tv_client_details_basic_info_customer_name);
//        mTextView_CustomerType = (TextView) view.findViewById(R.id.id_tv_client_details_basic_info_customer_type);
//        mTextView_CustomerArea = (TextView) view.findViewById(R.id.id_tv_client_details_basic_info_customer_area);
//        mTextView_CustomerTelephone = (TextView) view.findViewById(R.id.id_tv_client_details_basic_info_customer_telephone);

    }

}
