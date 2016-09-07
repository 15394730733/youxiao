package com.youxiao.ui.fragment;

import android.content.Context;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import com.youxiao.R;
import com.youxiao.widget.XKPrograssDialog;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * @author StomHong
 */
public class ItemForCommodityFragment extends Fragment implements View.OnClickListener {
    private double saleAmount = 0;
    private String countCollect = "";
    private ListView mListView_Commodity;
    private Button mBtn_AddCommodity;
    private List<HashMap<String, String>> mDatas = new ArrayList<>();
    private TextView sales_count_total;
    private TextView sales_price_total;
    private XKPrograssDialog mDialog;
//    Handler layoutHandler = new SalesFragment().new LayoutHandler();
    private static final String TAG = SalesFragment.class.getSimpleName();

    public Handler m_Handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            switch (msg.what) {
                case 0:
                    sales_count_total.setText(countCollect);
                    sales_price_total.setText(String.format("%.2f", saleAmount) + "");
                    break;
                case 1:
                    InputMethodManager imm = (InputMethodManager) getActivity().getSystemService(Context.INPUT_METHOD_SERVICE);
                    imm.toggleSoftInput(0, InputMethodManager.HIDE_NOT_ALWAYS);
                    break;
                case 2:
                    break;
            }
        }
    };

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.item_for_commodity, container, false);

        initView(view);
        initData();
        initEvent();
        return view;
    }

    private void initData() {

        mDialog = new XKPrograssDialog(getActivity(),R.style.Theme_dialog);
        mDialog.setDialogText("数据加载中");
        mDialog.show();
    }

    @Override
    public void onResume() {
        super.onResume();

    }

    private void initView(View view) {

        mBtn_AddCommodity = (Button) view.findViewById(R.id.id_btn_marketing_add_commodity);
        mListView_Commodity = (ListView) view.findViewById(R.id.id_lv_marketing_commodity_info);
        sales_count_total = (TextView) view.findViewById(R.id.item_for_commodity_item_sales_total_NonRetailCount);
        sales_price_total = (TextView) view.findViewById(R.id.item_for_commodity_item_sales_total_price);
    }

    private void initEvent() {

        mBtn_AddCommodity.setOnClickListener(this);
    }


    @Override
    public void onClick(View v) {

    }



}
