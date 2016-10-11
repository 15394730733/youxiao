package com.youxiao.ui.fragment;

import android.content.DialogInterface;
import android.content.Intent;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.widget.SlidingPaneLayout;
import android.support.v7.app.AlertDialog;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.youxiao.R;
import com.youxiao.adapter.CommonAdapter;
import com.youxiao.adapter.ViewHolder;
import com.youxiao.base.BaseFragment;
import com.youxiao.entity.Sales;
import com.youxiao.ui.activity.MainActivity;
import com.youxiao.ui.activity.me.systemsetting.CommoditySelectorActivity;
import com.youxiao.ui.activity.sales.settlement.SettlementActivity;
import com.youxiao.ui.activity.work.customermanager.CustomerManageActivity;
import com.youxiao.util.ListViewUtil;
import com.youxiao.widget.ActionItem;
import com.youxiao.widget.SHongSlidingLayout;
import com.youxiao.widget.TitlePopup;
import com.youxiao.widget.Util;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

/**
 * 营销页面ddddddddddddddddddddddddddd
 *
 * @author StomHong
 */
public class SalesFragment extends BaseFragment implements View.OnClickListener {

    private static final String TAG = SalesFragment.class.getSimpleName();
    /**
     * 营销请求码
     **/
    public static final int MARKETING_FRAGMENT_REQUEST = 101;
    public static boolean COMMODITY_ISSELECTED = false;

    private LinearLayout mLinearLayout_SelectCustomer;
    private LinearLayout mLinearLayout_SalesCommit;
    private View mView_Title;
    private View mView_Amount;
    private TextView mTextView_Commit;
    private TextView mTextView_SelectAllOrNull;
    private ImageView mImageView_CommoditySelect;
    private ImageView mImageView_More;
    private ImageView mImageView_Commit;
    //定义标题栏弹窗按钮
    private TitlePopup titlePopup;

    private SHongSlidingLayout mSlidingPaneLayout;
    private FloatingActionButton mFab;

    private FrameLayout mFrameLayout_PaneTop;
    private FrameLayout mFrameLayout_PaneBottom;
    private ListView mListView_LeftOne;
    private ListView mListView_LeftTwo;
    private ListView mListView_LeftThree;
    private ListView mListView_RightCommodity;
    private ListView mListView_SelecedCommodity;
    private ArrayList<String> mDatas;
    private ArrayList<String> mDatasRight;
    private CommonAdapter<String> mAdapter;
    private CommonAdapter<Sales> mSelectedAdapter;
    private CommonAdapter<Sales> mCheckAdapter;

    private SalesFragmentListener mListener;
    private MainActivity mainActivity;
    //侧滑选择的商品标志序号
    private List<Integer> mPos = new ArrayList<>();
    //选中要操作的商品
    private List<Sales> mData;
    private ListView mListView_CheckBox;
    //点击的tab标号
    private int tabIndex;
    private boolean textColorFlag = false;


    @Override
    protected int getRootView() {
        return R.layout.item_fragment_sales;
    }

    @Override
    public void onResume() {
        super.onResume();
        switch (CommoditySelectorActivity.SELECTOR_TYPE) {
            case CommoditySelectorActivity.STANDARD_SELECTOR:
                mFab.setVisibility(View.VISIBLE);
                mImageView_CommoditySelect.setVisibility(View.VISIBLE);
                mLinearLayout_SalesCommit.setVisibility(View.GONE);
                break;
            case CommoditySelectorActivity.VIEW_SELECTOR:
                mFab.setVisibility(View.GONE);
                mImageView_CommoditySelect.setVisibility(View.GONE);
                mLinearLayout_SalesCommit.setVisibility(View.VISIBLE);
                mSlidingPaneLayout.closePane();
                break;
        }

    }

    /**
     * 初始化数据
     */
    @Override
    public void initData() {
        mainActivity = (MainActivity) getContext();
        //给标题栏弹窗添加子类
        titlePopup.addAction(new ActionItem(getActivity(), "扫一扫", R.drawable.saoyisao));
        titlePopup.addAction(new ActionItem(getActivity(), "商品搜索", R.drawable.sousuo));
        titlePopup.addAction(new ActionItem(getActivity(), "销售模块", R.drawable.xiaoshou));
        titlePopup.addAction(new ActionItem(getActivity(), "订单销售", R.drawable.paodan));
        titlePopup.addAction(new ActionItem(getActivity(), "盘点销售", R.drawable.pandianxiaoshou));
        titlePopup.addAction(new ActionItem(getActivity(), "结算", R.drawable.jiesuan));
        titlePopup.addAction(new ActionItem(getActivity(), "清空", R.drawable.qingkong));

        try {
            //通过反射来设置slidingPaneLayout滑出全屏
            Field field = SlidingPaneLayout.class.getDeclaredField("mOverhangSize");
            field.setAccessible(true);
            field.set(mSlidingPaneLayout, 0);
            mSlidingPaneLayout.setPanelSlideListener(new SlidingListener());
            mSlidingPaneLayout.setSliderFadeColor(getResources().getColor(android.R.color.transparent));
        } catch (Exception e) {
            e.printStackTrace();
        }
        //左边的三个ListView分别占屏幕宽度的1/5
        int width = Util.getScreenWidth(getActivity()) / 5;
        mListView_LeftOne.setLayoutParams(new LinearLayout.LayoutParams(width, ViewGroup.LayoutParams.MATCH_PARENT));
        mListView_LeftTwo.setLayoutParams(new LinearLayout.LayoutParams(width, ViewGroup.LayoutParams.MATCH_PARENT));
        mListView_LeftThree.setLayoutParams(new LinearLayout.LayoutParams(width, ViewGroup.LayoutParams.MATCH_PARENT));
        mDatas = new ArrayList<>();
        for (int i = 0; i < 15; i++) {
            mDatas.add("其它分类");
        }
        mDatasRight = new ArrayList<>();
        for (int i = 1; i < 20; i++) {
            mDatasRight.add("金锣肉粒多200g" + i);
        }
        mListView_LeftOne.setAdapter(new ArrayAdapter<String>(getActivity(), R.layout.item_one_text, mDatas));
        mListView_LeftTwo.setAdapter(new ArrayAdapter<String>(getActivity(), R.layout.item_one_text, mDatas));
        mListView_LeftThree.setAdapter(new ArrayAdapter<String>(getActivity(), R.layout.item_one_text, mDatas));
        mAdapter = new CommonAdapter<String>(getActivity(), mDatasRight, R.layout.item_one_text) {
            @Override
            public void convert(final ViewHolder holder, String s) {
                holder.setText(R.id.one_text, s);
                if (mPos.contains(holder.getPosition())) {
                    holder.setTextColor(R.id.one_text, getResources().getColor(R.color.red400));
                } else {
                    holder.setTextColor(R.id.one_text, getResources().getColor(R.color.grey700));
                }
            }
        };
        mListView_RightCommodity.setAdapter(mAdapter);

        mData = new ArrayList<>();
        mSelectedAdapter = new CommonAdapter<Sales>(getActivity(), mData, R.layout.item_sales) {
            @Override
            public void convert(final ViewHolder holder, final Sales sales) {
                holder.setText(R.id.id_tv_commodity_name, sales.getName());
                if (sales.isSelect()) {//如果是选中的则改变字体颜色
                    holder.setTextColor(R.id.id_tv_commodity_name, getResources().getColor(R.color.red400));
                    holder.setTextColor(R.id.id_tv_commodity_bulk, getResources().getColor(R.color.red400));
                    holder.setTextColor(R.id.id_tv_commodity_entire, getResources().getColor(R.color.red400));
                } else {
                    holder.setTextColor(R.id.id_tv_commodity_name, getResources().getColor(R.color.grey700));
                    holder.setTextColor(R.id.id_tv_commodity_bulk, getResources().getColor(R.color.grey700));
                    holder.setTextColor(R.id.id_tv_commodity_entire, getResources().getColor(R.color.grey700));
                }
                if (COMMODITY_ISSELECTED) {
                    if (sales.isSelect()) {
                        switch (tabIndex) {
                            case 1:
                                holder.setViewVisibility(R.id.tv_rotate, true);
                                holder.setText(R.id.tv_rotate, "退货");
                                break;
                            case 2:
                                holder.setViewVisibility(R.id.tv_rotate, true);
                                holder.setText(R.id.tv_rotate, "陈列");
                                break;
                            case 3:
                                holder.setViewVisibility(R.id.tv_rotate, true);
                                holder.setText(R.id.tv_rotate, "赠品");
                                break;
                            case 4:
                                holder.setViewVisibility(R.id.tv_rotate, true);
                                holder.setText(R.id.tv_rotate, "存货");
                                break;
                        }
                    }
                }
                holder.setViewOnClick(new ViewHolder.ViewClick() {
                    @Override
                    public void onClick(View v, int position) {
                        //如果checkBox可见，则点击时操作checkBox；如果checkBox不可见，则点击时直接改变字体颜色
                        if (sales.isSelect()) {
                            sales.setSelect(false);
                        } else {
                            sales.setSelect(true);
                        }
                        if (mListView_CheckBox.isShown()) {
                            mCheckAdapter.notifyDataSetChanged();
                        } else {
                            mSelectedAdapter.notifyDataSetChanged();
                        }
                    }
                }, R.id.rl_commodity_name);
            }
        };
        mListView_SelecedCommodity.setAdapter(mSelectedAdapter);
        mCheckAdapter = new CommonAdapter<Sales>(getActivity(), mData, R.layout.item_check_commodity) {
            @Override
            public void convert(final ViewHolder holder, Sales sales) {
                if (mData.get(holder.getPosition()).isSelect()) {
                    holder.setCheckable(R.id.cb_check_commodity, true);
                } else {
                    holder.setCheckable(R.id.cb_check_commodity, false);
                }
                //点击checkBox
                holder.setViewOnClick(new ViewHolder.ViewClick() {
                    @Override
                    public void onClick(View v, int position) {
                        if (mData.get(holder.getPosition()).isSelect()) {
                            mData.get(holder.getPosition()).setSelect(false);
                        } else {
                            mData.get(holder.getPosition()).setSelect(true);
                        }
                        mCheckAdapter.notifyDataSetChanged();
                    }
                }, R.id.cb_check_commodity);
            }
        };
        mListView_CheckBox.setAdapter(mCheckAdapter);

    }

    @Override
    public void initEvent() {
        mLinearLayout_SelectCustomer.setOnClickListener(this);
        mFab.setOnClickListener(this);
        mTextView_SelectAllOrNull.setOnClickListener(this);
        mImageView_CommoditySelect.setOnClickListener(this);
        mImageView_More.setOnClickListener(this);
        mImageView_Commit.setOnClickListener(this);
        mSlidingPaneLayout.setPanelSlideListener(new SlidingListener());
        mTextView_Commit.setOnClickListener(this);
        mListView_RightCommodity.setOnItemClickListener(new RightItemClickListener());
        mListView_LeftThree.setOnItemClickListener(new ListItemClickListener());
        mListView_LeftOne.setOnItemClickListener(new ListItemClickListener());
        mListView_LeftTwo.setOnItemClickListener(new ListItemClickListener());

        mainActivity.setActivityListener(new MainActivity.MainActivityListener() {
            @Override
            public void doActivity(int tabClicked) {
                switch (tabClicked) {
                    case 1://设为退货
                        tabIndex = 1;
                        mSelectedAdapter.notifyDataSetChanged();
                        Toast.makeText(getActivity(), "设为退货", Toast.LENGTH_SHORT).show();
                        break;
                    case 2://设为陈列
                        tabIndex = 2;
                        mSelectedAdapter.notifyDataSetChanged();
                        Toast.makeText(getActivity(), "设为陈列", Toast.LENGTH_SHORT).show();
                        break;
                    case 3://设为赠品
                        tabIndex = 3;
                        mSelectedAdapter.notifyDataSetChanged();
                        Toast.makeText(getActivity(), "设为赠品", Toast.LENGTH_SHORT).show();
                        break;
                    case 4://转为存货
                        tabIndex = 4;
                        mSelectedAdapter.notifyDataSetChanged();
                        Toast.makeText(getActivity(), "转为存货", Toast.LENGTH_SHORT).show();
                        break;
                    case 5://删除行
                        for (int i = mData.size() - 1; i >= 0; i--) {
                            if (mData.get(i).isSelect()) {
                                mData.remove(i);
                            }
                        }
                        mCheckAdapter.notifyDataSetChanged();
                        mSelectedAdapter.notifyDataSetChanged();
                        ListViewUtil.setListViewHeightBasedOnChildren(mListView_SelecedCommodity);
                        if (mData.size() == 0) {
                            mImageView_CommoditySelect.setImageResource(R.drawable.multi_select);
                            mTextView_SelectAllOrNull.setVisibility(View.GONE);
                            mListView_CheckBox.setVisibility(View.GONE);
                            COMMODITY_ISSELECTED = false;
                            mListener.doFragment(true);
                            mView_Title.setVisibility(View.GONE);
                            mView_Amount.setVisibility(View.GONE);
                        }
                        break;
                }
            }
        });
    }

    @Override
    public void initView(View view) {
        mSlidingPaneLayout = (SHongSlidingLayout) view.findViewById(R.id.sliding_pane_layout);
        mFab = (FloatingActionButton) view.findViewById(R.id.fab);

        mListView_LeftOne = (ListView) view.findViewById(R.id.id_left_pane_one);
        mListView_LeftTwo = (ListView) view.findViewById(R.id.id_left_pane_two);
        mListView_LeftThree = (ListView) view.findViewById(R.id.id_left_pane_three);
        mListView_RightCommodity = (ListView) view.findViewById(R.id.id_lv_commodity);
        mListView_SelecedCommodity = (ListView) view.findViewById(R.id.id_lv_selected_commodity);
        mListView_CheckBox = (ListView) view.findViewById(R.id.lv_check_box);

//        mFrameLayout_PaneBottom = (FrameLayout) view.findViewById(R.id.fl_pane_bottom);
//        mFrameLayout_PaneTop = (FrameLayout) view.findViewById(R.id.fl_pane_top);

        mImageView_CommoditySelect = (ImageView) view.findViewById(R.id.iv_sales_select);
        mImageView_More = (ImageView) view.findViewById(R.id.iv_sales_more);
        mImageView_Commit = (ImageView) view.findViewById(R.id.iv_sales_commit);

        mTextView_Commit = (TextView) view.findViewById(R.id.tv_commit);
        mTextView_SelectAllOrNull = (TextView) view.findViewById(R.id.tv_select_all_or_null);

        mLinearLayout_SelectCustomer = (LinearLayout) view.findViewById(R.id.ll_sales_select_customer);
        mLinearLayout_SalesCommit = (LinearLayout) view.findViewById(R.id.ll_sales_commit);
        mView_Title = view.findViewById(R.id.view_title);
        mView_Amount = view.findViewById(R.id.view_amount);
        //实例化标题栏弹窗
        titlePopup = new TitlePopup(getActivity().getApplicationContext(), ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.fab:
                mSlidingPaneLayout.openPane();
                break;
            case R.id.iv_sales_commit:
                mSlidingPaneLayout.closePane();
                break;
            case R.id.iv_sales_select:
                if (COMMODITY_ISSELECTED) {
                    mImageView_CommoditySelect.setImageResource(R.drawable.multi_select);
                    mTextView_SelectAllOrNull.setVisibility(View.GONE);
                    //重置CheckBox状态
                    setSelect(false);
                    mCheckAdapter.notifyDataSetChanged();
                    mListView_CheckBox.setVisibility(View.GONE);
                    COMMODITY_ISSELECTED = false;
                    mListener.doFragment(true);
                    mView_Amount.setVisibility(View.GONE);
                    mView_Title.setVisibility(View.GONE);
                } else {
                    if (mData.size() > 0) {
                        mImageView_CommoditySelect.setImageResource(R.drawable.cancel_select);
                        mTextView_SelectAllOrNull.setVisibility(View.VISIBLE);
                        mTextView_SelectAllOrNull.setText("全选");
                        //将复选框列表设置为可见
                        mListView_CheckBox.setVisibility(View.VISIBLE);
                        mView_Amount.setVisibility(View.VISIBLE);
                        mView_Title.setVisibility(View.VISIBLE);
                        COMMODITY_ISSELECTED = true;
                        //更换Tab的图片和文字
                        mListener.doFragment(false);
                    } else {
                        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
                        builder.setTitle("未选择商品");
                        builder.setPositiveButton("确定", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                dialog.dismiss();
                            }
                        });
                        builder.setCancelable(false);
                        builder.show();
                    }
                }
                break;
            case R.id.iv_sales_more:
                titlePopup.show(v);
                break;
            case R.id.ll_sales_select_customer:
                //选择客户
                handleSelectCustomer();
                break;
            case R.id.tv_commit:
                Intent intent = new Intent(getActivity(), SettlementActivity.class);
                startActivity(intent);
                break;
            case R.id.tv_select_all_or_null:
                String str = mTextView_SelectAllOrNull.getText().toString();
                if ("全选".equals(str)) {
                    mTextView_SelectAllOrNull.setText("取消全选");
                    setSelect(true);
                } else if ("取消全选".equals(str)) {
                    mTextView_SelectAllOrNull.setText("全选");
                    setSelect(false);
                }
                mCheckAdapter.notifyDataSetChanged();
                break;
            default:
                break;
        }
    }

    /**
     * 将商品设置为全部选中或者空
     *
     * @param select
     */
    private void setSelect(boolean select) {
        for (int i = 0; i < mData.size(); i++) {
            mData.get(i).setSelect(select);
        }
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        //// TODO: 2016/9/19 处理选择客户返回结果
    }


    private void handleSelectCustomer() {
        Intent intent = new Intent();
        intent.setClass(getActivity(), CustomerManageActivity.class);
        startActivity(intent);
    }


    private class SlidingListener implements SHongSlidingLayout.PanelSlideListener {

        @Override
        public void onPanelSlide(View panel, float slideOffset) {

        }

        @Override
        public void onPanelOpened(View panel) {
            mFab.setVisibility(View.INVISIBLE);
            mImageView_Commit.setVisibility(View.VISIBLE);
            mListView_RightCommodity.setVisibility(View.VISIBLE);
            mImageView_CommoditySelect.setImageResource(R.drawable.saomiao2x_pre);
            mPos.clear();
            mAdapter.notifyDataSetChanged();
        }

        @Override
        public void onPanelClosed(View panel) {
            mFab.setVisibility(View.VISIBLE);
            mImageView_Commit.setVisibility(View.INVISIBLE);
            mListView_RightCommodity.setVisibility(View.GONE);
            mImageView_CommoditySelect.setImageResource(R.drawable.multi_select);

            for (int i = 0; i < mPos.size(); i++) {
                mData.add(new Sales(mDatasRight.get(mPos.get(i)), "", "", 0f, 0f, "", "", "", false));
            }
            mSelectedAdapter.notifyDataSetChanged();
            ListViewUtil.setListViewHeightBasedOnChildren(mListView_SelecedCommodity);
        }
    }

    private class ListItemClickListener implements ListView.OnItemClickListener {
        @Override
        public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
            //do something when click item of left

        }
    }

    private class RightItemClickListener implements ListView.OnItemClickListener {
        @Override
        public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
            if (mPos.contains(position)) {
                mPos.remove((Integer) position);
            } else {
                mPos.add(position);
            }
            mAdapter.notifyDataSetChanged();
        }
    }

    /**
     * 为了让Activity能够响应Fragment的操作而写的接口
     */
    public interface SalesFragmentListener {
        void doFragment(boolean tabSwitch);
    }

    public void setFragmentListener(SalesFragmentListener listener) {
        mListener = listener;
    }

}




