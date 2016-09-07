package com.youxiao.ui.activity.work.procurementmanager;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.widget.SlidingPaneLayout;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewTreeObserver;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import com.youxiao.R;
import com.youxiao.base.BaseActivity;
import com.youxiao.ui.fragment.ProcurementOrderFragment;
import com.youxiao.ui.fragment.ProcurementReturnFragment;
import com.youxiao.ui.fragment.ProcurementStockFragment;
import com.youxiao.widget.FakeDialogLayout;
import com.youxiao.widget.Util;

import java.util.ArrayList;
import java.util.List;

/**
 * 采购管理
 *
 * @author StomHong
 * @since 2016-7-1
 */
public class ProcurementActivity extends BaseActivity implements View.OnClickListener,
        View.OnTouchListener,FakeDialogLayout.ItemViewClick,FakeDialogLayout.ViewVisible{

    private SlidingPaneLayout mSlidingLayout;
    private ListView mListView_LeftOne;
    private ListView mListView_LeftTwo;
    private ListView mListView_LeftThree;
    private ListView mListView_RightCommodity;

    private LinearLayout mLinearLayout_ProcurementOrder;
    private LinearLayout mLinearLayout_ProcurementStock;
    private LinearLayout mLinearLayout_ProcurementReturn;
    private LinearLayout mLinearLayout_Back;
    private LinearLayout mLinearLayout_SelectStock;

    private ImageView mImageView_ProcurementOrder;
    private ImageView mImageView_ProcurementStock;
    private ImageView mImageView_ProcurementReturn;
    private ImageView mImageView_Remark;
    private ImageView mImageView_Commit;
    private ImageView mImageView_TitleIndicator;

    private TextView mTextView_ProcurementOrder;
    private TextView mTextView_ProcurementStock;
    private TextView mTextView_ProcurementReturn;

    private TextView mTextView_Title;
    private String mStock;

    private ProcurementOrderFragment mProcurementOrderFragment;
    private ProcurementStockFragment mProcurementStockFragment;
    private ProcurementReturnFragment mProcurementReturnFragment;



    private List<String> mDatas;
    private List<String> mDatasRight;
    private FakeDialogLayout mFakeDialogLayout;
    private List<String> mStocks;





    private enum FragmentType {
        PROCUREMENT_ORDER, PROCUREMENT_STOCK, PROCUREMENT_RETURN
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_procurement);

        super.init();
    }

    @Override
    public void initView() {
        mSlidingLayout = (SlidingPaneLayout) findViewById(R.id.slidingpanelayout);

        mListView_LeftOne = (ListView) findViewById(R.id.id_left_pane_one);
        mListView_LeftTwo = (ListView) findViewById(R.id.id_left_pane_two);
        mListView_LeftThree = (ListView) findViewById(R.id.id_left_pane_three);
        mListView_RightCommodity = (ListView) findViewById(R.id.id_lv_commodity);

        mLinearLayout_ProcurementOrder = (LinearLayout) findViewById(R.id.id_ll_procurement_order);
        mLinearLayout_ProcurementStock = (LinearLayout) findViewById(R.id.id_ll_procurement_stock);
        mLinearLayout_ProcurementReturn = (LinearLayout) findViewById(R.id.id_ll_procurement_return);
        mLinearLayout_Back = (LinearLayout) findViewById(R.id.id_ll_back);
        mLinearLayout_SelectStock = (LinearLayout) findViewById(R.id.ll_select_stock);

        mImageView_ProcurementOrder = (ImageView) findViewById(R.id.id_iv_procurement_order);
        mImageView_ProcurementStock = (ImageView) findViewById(R.id.id_iv_procurement_stock);
        mImageView_ProcurementReturn = (ImageView) findViewById(R.id.id_iv_procurement_return);
        mImageView_Commit = (ImageView) findViewById(R.id.id_iv_procurement_commit_and_search);
        mImageView_Remark = (ImageView) findViewById(R.id.id_iv_procurement_remark_and_scan);
        mImageView_TitleIndicator = (ImageView) findViewById(R.id.id_iv_title_indicator);

        mTextView_ProcurementOrder = (TextView) findViewById(R.id.id_tv_procurement_order);
        mTextView_ProcurementStock = (TextView) findViewById(R.id.id_tv_procurement_stock);
        mTextView_ProcurementReturn = (TextView) findViewById(R.id.id_tv_procurement_return);

        mTextView_Title = (TextView) findViewById(R.id.id_tv_title);

        mFakeDialogLayout = (FakeDialogLayout) findViewById(R.id.fakedialoglayout);
    }

    @Override
    public void initData() {
        selectFragment(FragmentType.PROCUREMENT_ORDER);
        //左边的三个ListView分别占屏幕宽度的1/5
        int width = Util.getScreenWidth(this) / 5;
        mListView_LeftOne.setLayoutParams(new LinearLayout.LayoutParams(width, ViewGroup.LayoutParams.MATCH_PARENT));
        mListView_LeftTwo.setLayoutParams(new LinearLayout.LayoutParams(width, ViewGroup.LayoutParams.MATCH_PARENT));
        mListView_LeftThree.setLayoutParams(new LinearLayout.LayoutParams(width, ViewGroup.LayoutParams.MATCH_PARENT));

        mDatas = new ArrayList<>();
        for (int i = 0; i < 15; i++) {
            mDatas.add("其它分类");
        }

        mDatasRight = new ArrayList<>();
        for (int i = 1; i < 15; i++) {
            mDatasRight.add("金锣肉粒多200g" + i);
        }
        mListView_LeftOne.setAdapter(new ArrayAdapter<String>(this, R.layout.item_one_text, mDatas));

        mListView_LeftOne.setOnItemClickListener(new ListItemClickListener());
        mListView_LeftTwo.setAdapter(new ArrayAdapter<String>(this, R.layout.item_one_text, mDatas));
        mListView_LeftTwo.setOnItemClickListener(new ListItemClickListener());
        mListView_LeftThree.setAdapter(new ArrayAdapter<String>(this, R.layout.item_one_text, mDatas));
        mListView_LeftThree.setOnItemClickListener(new ListItemClickListener());

        mListView_RightCommodity.setAdapter(new ArrayAdapter<String>(this, R.layout.item_one_text, mDatasRight));

        mStocks = new ArrayList<>();
        mStocks.add("仓库1");
        mStocks.add("仓库2");
        mFakeDialogLayout.setListData(mStocks);
    }

    @Override
    public void initEvent() {
        mSlidingLayout.setPanelSlideListener(new SliderListener());
        //目前还不是很清楚这是干嘛的
//        mSlidingLayout.getViewTreeObserver().addOnGlobalLayoutListener(new FirstLayoutListener());

        mLinearLayout_ProcurementOrder.setOnClickListener(this);
        mLinearLayout_ProcurementStock.setOnClickListener(this);
        mLinearLayout_ProcurementReturn.setOnClickListener(this);

        mLinearLayout_Back.setOnClickListener(this);
        mLinearLayout_SelectStock.setOnClickListener(this);

        mImageView_Remark.setOnClickListener(this);
        mImageView_Commit.setOnClickListener(this);
        mSlidingLayout.setOnTouchListener(this);
    }

    @Override
    public boolean onTouch(View v, MotionEvent event) {
        if (v.getId() == R.id.slidingpanelayout) {
            if (mTextView_Title.getText().toString().equals("选择仓库")) {
                mSlidingLayout.closePane();
            }
        }
        return false;
    }

    @Override
    public void onClick(View v) {
        Intent intent;
        switch (v.getId()) {
            case R.id.id_ll_procurement_order:
                selectFragment(FragmentType.PROCUREMENT_ORDER);
                if (mSlidingLayout.isOpen())
                    mSlidingLayout.closePane();
                break;
            case R.id.id_ll_procurement_stock:
                selectFragment(FragmentType.PROCUREMENT_STOCK);
                if (mSlidingLayout.isOpen())
                    mSlidingLayout.closePane();
                break;
            case R.id.id_ll_procurement_return:
                selectFragment(FragmentType.PROCUREMENT_RETURN);
                if (mSlidingLayout.isOpen())
                    mSlidingLayout.closePane();
                break;
            case R.id.id_ll_back:
                finish();
                break;
            case R.id.ll_select_stock:
                if (mFakeDialogLayout.getVisibility() == View.GONE) {
                    mFakeDialogLayout.show(this,this);
                } else if (mFakeDialogLayout.getVisibility() == View.VISIBLE) {
                    mFakeDialogLayout.hide();
                }
                break;
            case R.id.id_iv_procurement_commit_and_search:
                intent = new Intent();
                if (mTextView_Title.getText().toString().equals("选择商品")) {
//                    intent.setClass(this,);//搜索
                } else {
//                    intent.setClass(this,RemarkActivity.class);//提交
                }
                startActivity(intent);
                break;
            case R.id.id_iv_procurement_remark_and_scan:
                intent = new Intent();
                if (mTextView_Title.getText().toString().equals("选择商品")) {
//                    intent.setClass(this,);//扫一扫
                } else {
                    intent.setClass(this, RemarkActivity.class);//备注
                }
                startActivity(intent);
                break;
            default:
                break;
        }
    }


    @Override
    public void onBackPressed() {
        if (mFakeDialogLayout.getVisibility() == View.VISIBLE) {
            mFakeDialogLayout.hide();
        } else {
            super.onBackPressed();
        }
    }


    @Override
    public void onItemViewClick() {
        mTextView_Title.setText(mFakeDialogLayout.getItemContent());
    }

    @Override
    public void onViewVisible(boolean visible) {
        if (visible){
            mImageView_TitleIndicator.setImageResource(R.drawable.top);
        }else {
            mImageView_TitleIndicator.setImageResource(R.drawable.down);
        }
    }

    /**
     * 选择Fragment
     *
     * @param fragmentType
     */
    private void selectFragment(FragmentType fragmentType) {
        resetAllTab();

        FragmentManager fm = getSupportFragmentManager();
        FragmentTransaction ft = fm.beginTransaction();
        hideAllFragment(ft);

        int red = getResources().getColor(R.color.red400);
        switch (fragmentType) {
            case PROCUREMENT_ORDER:
                mImageView_ProcurementOrder.setImageResource(R.drawable.procurement_order_selected);
                mTextView_ProcurementOrder.setTextColor(red);
                if (mProcurementOrderFragment == null) {
                    mProcurementOrderFragment = ProcurementOrderFragment.newInstance();
                    ft.add(R.id.id_right_pane, mProcurementOrderFragment, ProcurementOrderFragment.class.getSimpleName());
                } else {
                    ft.show(mProcurementOrderFragment);
                }
                break;
            case PROCUREMENT_STOCK:
                mImageView_ProcurementStock.setImageResource(R.drawable.procurement_stock_selected);
                mTextView_ProcurementStock.setTextColor(red);
                if (mProcurementStockFragment == null) {
                    mProcurementStockFragment = ProcurementStockFragment.newInstance();
                    ft.add(R.id.id_right_pane, mProcurementStockFragment, ProcurementStockFragment.class.getSimpleName());
                } else {
                    ft.show(mProcurementStockFragment);
                }
                break;
            case PROCUREMENT_RETURN:
                mImageView_ProcurementReturn.setImageResource(R.drawable.procurement_return_selected);
                mTextView_ProcurementReturn.setTextColor(red);
                if (mProcurementReturnFragment == null) {
                    mProcurementReturnFragment = ProcurementReturnFragment.newInstance();
                    ft.add(R.id.id_right_pane, mProcurementReturnFragment, ProcurementReturnFragment.class.getSimpleName());
                } else {
                    ft.show(mProcurementReturnFragment);
                }
                break;
            default:
                break;
        }
        ft.commit();
    }

    /**
     * 隐藏所有Fragment
     *
     * @param ft
     */
    private void hideAllFragment(FragmentTransaction ft) {
        hideFragment(ft, mProcurementReturnFragment);
        hideFragment(ft, mProcurementStockFragment);
        hideFragment(ft, mProcurementOrderFragment);
    }

    /**
     * 隐藏Fragment
     *
     * @param ft
     * @param fragment
     */
    private void hideFragment(FragmentTransaction ft, Fragment fragment) {
        if (fragment == null)
            return;
        ft.hide(fragment);
    }

    /**
     * 重置所有Tab
     */
    private void resetAllTab() {
        mImageView_ProcurementOrder.setImageResource(R.drawable.procurement_order_normal);
        mImageView_ProcurementStock.setImageResource(R.drawable.procurement_stock_normal);
        mImageView_ProcurementReturn.setImageResource(R.drawable.procurement_return_normal);

        int grey = getResources().getColor(R.color.grey500);
        mTextView_ProcurementReturn.setTextColor(grey);
        mTextView_ProcurementOrder.setTextColor(grey);
        mTextView_ProcurementStock.setTextColor(grey);
    }

    /**
     * This list item click listener implements very simple view switching by changing
     * the primary content text. The slider is closed when a selection is made to fully
     * reveal the content.
     */
    private class ListItemClickListener implements ListView.OnItemClickListener {
        @Override
        public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
            //do something when click item of left

        }
    }

    /**
     * This panel slide listener updates the title accordingly for each panel state.
     */
    private class SliderListener extends SlidingPaneLayout.SimplePanelSlideListener {

        @Override
        public void onPanelOpened(View panel) {
            super.onPanelOpened(panel);
            //pane完全打开，这时要改变right_pane的布局
            mListView_RightCommodity.setVisibility(View.VISIBLE);

            mImageView_Remark.setImageResource(R.drawable.saomiao2x_pre);
            mImageView_Commit.setImageResource(R.drawable.search_red);
            mStock = mTextView_Title.getText().toString();
            mTextView_Title.setText("选择商品");
            mImageView_TitleIndicator.setVisibility(View.GONE);
        }

        @Override
        public void onPanelClosed(View panel) {
            super.onPanelClosed(panel);
            if (!mTextView_Title.getText().toString().equals("选择仓库")) {
                //pane完全关闭，这时要改变right_pane的布局
                mListView_RightCommodity.setVisibility(View.GONE);
                mImageView_Remark.setImageResource(R.drawable.remark);
                mImageView_Commit.setImageResource(R.drawable.commit);
                mTextView_Title.setText(mStock);
                mImageView_TitleIndicator.setVisibility(View.VISIBLE);
            }
        }

        @Override
        public void onPanelSlide(View panel, float slideOffset) {
            super.onPanelSlide(panel, slideOffset);

        }
    }

    /**
     * This global layout listener is used to fire an event after first layout occurs
     * and then it is removed. This gives us a chance to configure parts of the UI
     * that adapt based on available space after they have had the opportunity to measure
     * and layout.
     */
    private class FirstLayoutListener implements ViewTreeObserver.OnGlobalLayoutListener {
        @Override
        public void onGlobalLayout() {

        }
    }

}
