package com.youxiao.ui.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.youxiao.ui.activity.statement.currentstock.CurrentStockActivity;
import com.youxiao.ui.activity.statement.customerstockdetail.CustomerStockDetailActivity;
import com.youxiao.ui.activity.statement.expirationdatewarning.ExpirationDateWarningActivity;
import com.youxiao.ui.activity.statement.newcustomerquery.NewCustomerQueryActivity;
import com.youxiao.ui.activity.statement.salescontrast.SalesContrastActivity;
import com.youxiao.ui.activity.statement.salesdaily.SalesDailyQueryActivity;
import com.youxiao.ui.activity.statement.salesdetail.SalesDetailActivity;
import com.youxiao.ui.activity.statement.salessummarization.SalesSummarizationActivity;
import com.youxiao.ui.activity.statement.salestrend.SalesTrendActivity;
import com.youxiao.ui.activity.statement.stockeerlywarning.StockEarlyWarningActivity;
import com.youxiao.R;
import com.youxiao.adapter.StatementAdapter;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @author StomHong
 */
public class StatementFragment extends Fragment {

    private RecyclerView mRecyclerView;
    private StatementAdapter mAdapter;
    private List<Object> mData;
    private List<String> mNames;


    private Integer[] storeImage = new Integer[]{R.drawable.immediate_stock, R.drawable.safe_stock_warning,
            R.drawable.expiration_warning, R.drawable.gengduo};
    private Integer[] salesImage = new Integer[]{R.drawable.rijie, R.drawable.kaidan, R.drawable.huizong,
            R.drawable.sale_tendency, R.drawable.sale_comparison, R.drawable.gengduo};
    private Integer[] customerImage = new Integer[]{R.drawable.kupan, R.drawable.xinzwng, R.drawable.gengduo};

    private String[] customerName = new String[]{"客户库存明细", "新增客户查询", "更多"};
    private String[] storeName = new String[]{"即时库存", "库存预警", "保质期预警", "更多"};
    private String[] salesName = new String[]{"销售日结", "销售明细", "销售汇总", "销售走势", "销售对比", "更多"};

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.item_fragment_statement, container, false);
        initView(view);
        initData();
        initEvent();
        return view;


    }

    private void initView(View view) {
        mRecyclerView = (RecyclerView) view.findViewById(R.id.recyclerview);
    }

    private void initEvent() {
        mAdapter.setOnItemClickLitener(new StatementAdapter.OnItemClickLitener() {
            @Override
            public void onItemClick(View view, int position) {
                Intent intent = new Intent();
                switch (position) {
                    case 1://销售日结
                       intent.setClass(getActivity(),SalesDailyQueryActivity.class);
                        startActivity(intent);
                        break;
                    case 2://销售明细
                        intent.setClass(getActivity(),SalesDetailActivity.class);
                        startActivity(intent);
                        break;
                    case 3://销售汇总
                        intent.setClass(getActivity(),SalesSummarizationActivity.class);
                        startActivity(intent);
                        break;
                    case 4://销售走势
                        intent.setClass(getActivity(),SalesTrendActivity.class);
                        startActivity(intent);
                        break;
                    case 5://销售对比
                        intent.setClass(getActivity(),SalesContrastActivity.class);
                        startActivity(intent);
                        break;
                    case 6://更多
//                        intent.setClass(getActivity(),SalesDailyQueryActivity.class);
//                        startActivity(intent);
                        break;
                    case 8://客户库存明细
                        intent.setClass(getActivity(),CustomerStockDetailActivity.class);
                        startActivity(intent);
                        break;
                    case 9://新增客户查询
                        intent.setClass(getActivity(),NewCustomerQueryActivity.class);
                        startActivity(intent);
                        break;
                    case 10://更多
//                        intent.setClass(getActivity(),SalesDailyQueryActivity.class);
//                        startActivity(intent);
                        break;
                    case 12://即时库存
                        intent.setClass(getActivity(),CurrentStockActivity.class);
                        startActivity(intent);
                        break;
                    case 13://库存预警
                        intent.setClass(getActivity(),StockEarlyWarningActivity.class);
                        startActivity(intent);
                        break;
                    case 14://保质期预警
                        intent.setClass(getActivity(),ExpirationDateWarningActivity.class);
                        startActivity(intent);
                        break;
                    case 15://更多
//                        intent.setClass(getActivity(),SalesDailyQueryActivity.class);
//                        startActivity(intent);
                        break;
                    default:
                        break;
                }
            }

            @Override
            public void onItemLongClick(View view, int position) {

            }
        });
    }

    private void initData() {
        mData = new ArrayList<>();
        mData.add("销售类");
        mData.addAll(Arrays.asList(salesImage));
        mData.add("客户类");
        mData.addAll(Arrays.asList(customerImage));
        mData.add("仓库类");
        mData.addAll(Arrays.asList(storeImage));
        mNames = new ArrayList<>();
        mNames.add("");
        mNames.addAll(Arrays.asList(salesName));
        mNames.add("");
        mNames.addAll(Arrays.asList(customerName));
        mNames.add("");
        mNames.addAll(Arrays.asList(storeName));

        mAdapter = new StatementAdapter(mData, mNames);
        mRecyclerView.setAdapter(mAdapter);
        final GridLayoutManager layoutManager = new GridLayoutManager(getActivity(), 4);
        layoutManager.setSpanSizeLookup(new GridLayoutManager.SpanSizeLookup() {

            @Override
            public int getSpanSize(int position) {
                return mAdapter.isHeader(position) ? layoutManager.getSpanCount() : 1;
            }
        });
        mRecyclerView.setLayoutManager(layoutManager);
    }

}
