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
import android.widget.ImageView;

import com.youxiao.ui.activity.sales.scancode.MipcaActivityCapture;
import com.youxiao.ui.activity.work.knowledgeevaluation.KnowledgeEvaluationActivity;
import com.youxiao.ui.activity.work.allocationcheck.AllocationCheckActivity;
import com.youxiao.ui.activity.work.attendancesignature.AttendanceActivity;
import com.youxiao.ui.activity.work.commodityallocation.CommodityAllocationActivity;
import com.youxiao.ui.activity.work.commoditymanager.CommodityActivity;
import com.youxiao.ui.activity.work.contractsignature.ContractSignatureActivity;
import com.youxiao.ui.activity.work.creditnotemanager.CreditNoteManagerActivity;
import com.youxiao.ui.activity.work.customermanager.CustomerManageActivity;
import com.youxiao.ui.activity.work.depositmanager.DepositManagerActivity;
import com.youxiao.ui.activity.work.displaymanager.DisplayManagerActivity;
import com.youxiao.ui.activity.work.entrepotcheck.EntrepotCheckActivity;
import com.youxiao.ui.activity.work.marketpatrol.MarketPatrolActivity;
import com.youxiao.ui.activity.work.notificationmanager.MyDocumentActivity;
import com.youxiao.ui.activity.work.officialdocument.OfficeDocumentActivity;
import com.youxiao.ui.activity.work.otherprocedure.OtherProcedureActivity;
import com.youxiao.ui.activity.work.photomanager.PhotographUploadActivity;
import com.youxiao.ui.activity.work.procurementmanager.ProcurementActivity;
import com.youxiao.ui.activity.work.rebatemanager.RebateManagerActivity;
import com.youxiao.ui.activity.work.reimbursementprocedure.ReimbursementProcedureActivity;
import com.youxiao.ui.activity.work.saleorder.SaleOrderActivity;
import com.youxiao.ui.activity.work.scoremanager.ScoreManagerActivity;
import com.youxiao.ui.activity.work.stockcheck.StockCheckActivity;
import com.youxiao.ui.activity.work.stockmanager.StockManagerActivity;
import com.youxiao.ui.activity.work.taskboost.TaskBoostActivity;
import com.youxiao.ui.activity.work.taskreception.TaskReceptionActivity;
import com.youxiao.ui.activity.work.vacationprocedure.VacationProcedureActivity;
import com.youxiao.ui.activity.work.workreport.WorkReportActivity;
import com.youxiao.R;
import com.youxiao.adapter.WorkAdapter;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * 工作Fragment
 *
 * @author StomHong
 * @since 2016-3-11
 */
public class WorkFragment extends Fragment implements WorkAdapter.OnItemClickLitener{

    private RecyclerView mRecyclerView;
    private WorkAdapter mAdapter;
    private List<Object> mData;
    private List<String> mNames;
    private ImageView mImageView_SecretOrder;


    private Integer[] basicImage = new Integer[]{R.drawable.customer_archive, R.drawable.commodity_manager,
            R.drawable.sale_order, R.drawable.wendang, R.drawable.zhishi, R.drawable.gengduo};
    private Integer[] behaviorImage = new Integer[]{R.drawable.kaoqin, R.drawable.photo_upload, R.drawable.market_patrol,
            R.drawable.hetong, R.drawable.task_reception, R.drawable.task_boost, R.drawable.gengduo};
    private Integer[] storeImage = new Integer[]{R.drawable.procurement_manager, R.drawable.commodity_allocation,
            R.drawable.allocation_check, R.drawable.take_stock, R.drawable.stock_check, R.drawable.gengduo};
    private Integer[] financeImage = new Integer[]{R.drawable.debt_manager, R.drawable.order_payment_manager,
            R.drawable.prestore_stock_manager, R.drawable.award_manager, R.drawable.display_award_manager,
            R.drawable.rebate, R.drawable.score_manager, R.drawable.gengduo};
    private Integer[] officeImage = new Integer[]{R.drawable.tongzhi, R.drawable.gongzuoribao, R.drawable.qingjia,
            R.drawable.reimbursement_flow, R.drawable.chuxiao, R.drawable.other_flow, R.drawable.gengduo};

    private String[] behaviorName = new String[]{"考勤签到", "拍照上传", "市场巡查", "合同签订", "任务接收", "任务推进", "更多"};
    private String[] storeName = new String[]{"采购管理", "商品调拨", "调拨审核", "仓库盘点", "库存核对", "更多"};
    private String[] basicName = new String[]{"客户管理", "商品管理", "销售订单", "企业文档", "知识考评", "更多"};
    private String[] financeName = new String[]{"欠条管理", "存款管理", "存货管理", "兑奖管理", "陈列管理", "返利管理", "积分管理", "更多"};
    private String[] officeName = new String[]{"公文通知", "工作日报", "请假流程", "报销流程", "促销流程", "其它流程", "更多"};

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.item_fragment_work, container, false);
        initView(view);
        initData();
        initEvent();

        return view;
    }

    private void initView(View view) {
        mRecyclerView = (RecyclerView) view.findViewById(R.id.recyclerview);
        mImageView_SecretOrder = (ImageView) view.findViewById(R.id.iv_secret_order);
    }

    private void initEvent() {
        mImageView_SecretOrder.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                intent.setClass(getActivity(), MipcaActivityCapture.class);
                startActivity(intent);
            }
        });
        mAdapter.setOnItemClickLitener(this);
    }

    private void initData() {
        mData = new ArrayList<>();
        mData.add("基础类");
        mData.addAll(Arrays.asList(basicImage));
        mData.add("客户类");
        mData.addAll(Arrays.asList(behaviorImage));
        mData.add("仓库类");
        mData.addAll(Arrays.asList(storeImage));
        mData.add("财务类");
        mData.addAll(Arrays.asList(financeImage));
        mData.add("办公类");
        mData.addAll(Arrays.asList(officeImage));
        mNames = new ArrayList<>();
        mNames.add("");
        mNames.addAll(Arrays.asList(basicName));
        mNames.add("");
        mNames.addAll(Arrays.asList(behaviorName));
        mNames.add("");
        mNames.addAll(Arrays.asList(storeName));
        mNames.add("");
        mNames.addAll(Arrays.asList(financeName));
        mNames.add("");
        mNames.addAll(Arrays.asList(officeName));

        mAdapter = new WorkAdapter(mData, mNames);
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


    @Override
    public void onItemClick(View view, int position) {
        Intent intent = new Intent();
        switch (position) {
            case 1://客户管理
                intent.setClass(getActivity(), CustomerManageActivity.class);
                startActivity(intent);
                break;
            case 2://商品管理
                intent.setClass(getActivity(), CommodityActivity.class);
                startActivity(intent);
                break;
            case 3://销售订单
                intent.setClass(getActivity(), SaleOrderActivity.class);
                startActivity(intent);
                break;
            case 4://企业文档
                intent.setClass(getActivity(), MyDocumentActivity.class);
                startActivity(intent);
                break;
            case 5://知识考评
                intent.setClass(getActivity(), KnowledgeEvaluationActivity.class);
                startActivity(intent);
                break;
            case 6://更多
//                        intent.setClass(getActivity(),SalesDailyQueryActivity.class);
//                        startActivity(intent);
                break;
            case 8://考勤签到
                intent.setClass(getActivity(), AttendanceActivity.class);
                startActivity(intent);
                break;
            case 9://拍照上传
                intent.setClass(getActivity(), PhotographUploadActivity.class);
                startActivity(intent);
                break;
            case 10://市场巡查
                intent.setClass(getActivity(),MarketPatrolActivity.class);
                startActivity(intent);
                break;
            case 11://合同签订
                intent.setClass(getActivity(),ContractSignatureActivity.class);
                startActivity(intent);
                break;
            case 12://任务接收
                intent.setClass(getActivity(), TaskReceptionActivity.class);
                startActivity(intent);
                break;
            case 13://任务推进
                intent.setClass(getActivity(), TaskBoostActivity.class);
                startActivity(intent);
                break;
            case 14://更多
//                        intent.setClass(getActivity(), SalesDailyQueryActivity.class);
//                        startActivity(intent);
                break;
            case 16://采购管理
                intent.setClass(getActivity(),ProcurementActivity.class);
                startActivity(intent);
                break;
            case 17://商品调拨
                intent.setClass(getActivity(),CommodityAllocationActivity.class);
                startActivity(intent);
                break;
            case 18://调拨审核
                intent.setClass(getActivity(),AllocationCheckActivity.class);
                startActivity(intent);
                break;
            case 19://仓库盘点
                intent.setClass(getActivity(),EntrepotCheckActivity.class);
                startActivity(intent);
                break;
            case 20://库存核对
                intent.setClass(getActivity(),StockCheckActivity.class);
                startActivity(intent);
                break;
            case 21://更多
//                        intent.setClass(getActivity(),SalesDailyQueryActivity.class);
//                        startActivity(intent);
                break;
            case 23://欠条管理
                intent.setClass(getActivity(),CreditNoteManagerActivity.class);
                startActivity(intent);
                break;
            case 24://存款管理
                intent.setClass(getActivity(),DepositManagerActivity.class);
                startActivity(intent);
                break;
            case 25://存货管理
                intent.setClass(getActivity(),StockManagerActivity.class);
                startActivity(intent);
                break;
            case 26://兑奖管理
//                intent.setClass(getActivity(),RewardManagerActivity.class);
//                startActivity(intent);
                break;
            case 27://陈列管理
                intent.setClass(getActivity(),DisplayManagerActivity.class);
                startActivity(intent);
                break;
            case 28://返利管理
                intent.setClass(getActivity(),RebateManagerActivity.class);
                startActivity(intent);
                break;
            case 29://积分管理
                intent.setClass(getActivity(),ScoreManagerActivity.class);
                startActivity(intent);
                break;
            case 30://更多
//                        intent.setClass(getActivity(),SalesDailyQueryActivity.class);
//                        startActivity(intent);
                break;
            case 32://公文通知
                intent.setClass(getActivity(),OfficeDocumentActivity.class);
                startActivity(intent);
                break;
            case 33://工作日报
                intent.setClass(getActivity(),WorkReportActivity.class);
                startActivity(intent);
                break;
            case 34://请假流程
                intent.setClass(getActivity(),VacationProcedureActivity.class);
                startActivity(intent);
                break;
            case 35://报销流程
                intent.setClass(getActivity(),ReimbursementProcedureActivity.class);
                startActivity(intent);
                break;
            case 36://促销流程
//                        intent.setClass(getActivity(),SalesDailyQueryActivity.class);
//                        startActivity(intent);
                break;
            case 37://其它流程
                intent.setClass(getActivity(),OtherProcedureActivity.class);
                startActivity(intent);
                break;
            case 38://更多
//                        intent.setClass(getActivity(), SalesDailyQueryActivity.class);
//                        startActivity(intent);
                break;
            default:
                break;
        }
    }

    @Override
    public void onItemLongClick(View view, int position) {

    }
}
