package com.youxiao.ui.activity.work.commodityallocation;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.widget.SlidingPaneLayout;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import com.youxiao.R;
import com.youxiao.base.BaseActivity;
import com.youxiao.ui.activity.work.procurementmanager.RemarkActivity;
import com.youxiao.widget.FakeDialogLayout;
import com.youxiao.widget.Util;

import java.util.ArrayList;
import java.util.List;

/**
 * 商品调拨
 */
public class CommodityAllocationActivity extends BaseActivity implements View.OnClickListener,
        View.OnTouchListener, FakeDialogLayout.ItemViewClick, FakeDialogLayout.ViewVisible {

    private Animation mDialogAnimation;
    private TextView mTextView_Title;
    private LinearLayout mLinearLayout_Back;
    private LinearLayout mLinearLayout_SelectStock;
    private ImageView mImageView_Arrows;

    private SlidingPaneLayout mSlidingLayout;
    private ListView mListView_LeftOne;
    private ListView mListView_LeftTwo;
    private ListView mListView_LeftThree;
    private ListView mListView_RightCommodity;
    private List<String> mDatas;
    private List<String> mDatasRight;

    ImageView mImageView_Remark;
    private FakeDialogLayout mFakeDialogLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_commodity_allocation);
        super.init();
    }

    @Override
    public void initView() {
        mLinearLayout_Back = (LinearLayout) findViewById(R.id.id_ll_commodity_allocation_back);
        mLinearLayout_SelectStock = (LinearLayout) findViewById(R.id.ll_select_stock);
        mTextView_Title = (TextView) findViewById(R.id.id_tv_title);
        mImageView_Arrows = (ImageView) findViewById(R.id.id_iv_arrows);


        mSlidingLayout = (SlidingPaneLayout) findViewById(R.id.slidingpanelayout);

        mListView_LeftOne = (ListView) findViewById(R.id.id_left_pane_one);
        mListView_LeftTwo = (ListView) findViewById(R.id.id_left_pane_two);
        mListView_LeftThree = (ListView) findViewById(R.id.id_left_pane_three);
        mListView_RightCommodity = (ListView) findViewById(R.id.id_lv_commodity);

        mImageView_Remark = (ImageView) findViewById(R.id.id_iv_remark);
        mFakeDialogLayout = (FakeDialogLayout) findViewById(R.id.fakedialoglayout);
    }

    @Override
    public void initData() {
        //左边的三个ListView分别占屏幕宽度的1/5
        int width = Util.getScreenWidth(this) / 5;
        mListView_LeftOne.setLayoutParams(new LinearLayout.LayoutParams(width, ViewGroup.LayoutParams.MATCH_PARENT));
        mListView_LeftTwo.setLayoutParams(new LinearLayout.LayoutParams(width, ViewGroup.LayoutParams.MATCH_PARENT));
        mListView_LeftThree.setLayoutParams(new LinearLayout.LayoutParams(width, ViewGroup.LayoutParams.MATCH_PARENT));

        mDatas = new ArrayList<>();
        for (int i = 0; i < 20; i++) {
            mDatas.add("其它分类");
        }

        mDatasRight = new ArrayList<>();
        for (int i = 1; i < 20; i++) {
            mDatasRight.add("金锣肉粒多200g" + i);
        }
        mListView_LeftOne.setAdapter(new ArrayAdapter<String>(this, R.layout.item_one_text, mDatas));

        mListView_LeftOne.setOnItemClickListener(new ListItemClickListener());
        mListView_LeftTwo.setAdapter(new ArrayAdapter<String>(this, R.layout.item_one_text, mDatas));
        mListView_LeftTwo.setOnItemClickListener(new ListItemClickListener());
        mListView_LeftThree.setAdapter(new ArrayAdapter<String>(this, R.layout.item_one_text, mDatas));
        mListView_LeftThree.setOnItemClickListener(new ListItemClickListener());

        mListView_RightCommodity.setAdapter(new ArrayAdapter<String>(this, R.layout.item_one_text, mDatasRight));

        List<String> mData = new ArrayList<>();
        mData.add("仓库1");
        mData.add("仓库2");
        mFakeDialogLayout.setListData(mData);

    }

    @Override
    public void initEvent() {

        mSlidingLayout.setPanelSlideListener(new SliderListener());
        mSlidingLayout.setOnTouchListener(this);

        mLinearLayout_Back.setOnClickListener(this);
        mLinearLayout_SelectStock.setOnClickListener(this);
        mImageView_Remark.setOnClickListener(this);
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

        switch (v.getId()) {
            case R.id.id_ll_commodity_allocation_back:
                finish();
                break;
            case R.id.ll_select_stock:
                if (mFakeDialogLayout.getVisibility() == View.GONE) {
                    mFakeDialogLayout.show(this, this);
                } else {
                    mFakeDialogLayout.hide();
                }
                break;
            case R.id.id_iv_remark:
                Intent intent = new Intent();
                intent.setClass(this, RemarkActivity.class);//备注
                startActivity(intent);
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
        if (visible) {
            mImageView_Arrows.setImageResource(R.drawable.top);
        } else {
            mImageView_Arrows.setImageResource(R.drawable.down);
        }
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

        private String mStock;

        @Override
        public void onPanelOpened(View panel) {
            super.onPanelOpened(panel);
            //pane完全打开，这时要改变right_pane的布局
            mListView_RightCommodity.setVisibility(View.VISIBLE);
            mStock = mTextView_Title.getText().toString();
            mTextView_Title.setText("选择商品");
            mImageView_Arrows.setVisibility(View.GONE);
        }

        @Override
        public void onPanelClosed(View panel) {
            super.onPanelClosed(panel);
            if (!mTextView_Title.getText().toString().equals("选择仓库")) {
                //pane完全关闭，这时要改变right_pane的布局
                mListView_RightCommodity.setVisibility(View.GONE);
                mTextView_Title.setText(mStock);
                mImageView_Arrows.setVisibility(View.VISIBLE);
            }
        }

        @Override
        public void onPanelSlide(View panel, float slideOffset) {
            super.onPanelSlide(panel, slideOffset);

        }
    }
}
