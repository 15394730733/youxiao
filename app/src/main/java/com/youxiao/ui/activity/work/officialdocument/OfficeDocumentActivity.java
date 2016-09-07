package com.youxiao.ui.activity.work.officialdocument;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.widget.SwipeRefreshLayout;
import android.view.View;
import android.view.Window;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.youxiao.base.BaseActivity;
import com.youxiao.ui.activity.sales.commodityselector.CommoditySearchActivity;
import com.youxiao.R;
import com.youxiao.adapter.CommonAdapter;
import com.youxiao.adapter.ViewHolder;

import java.util.ArrayList;
import java.util.List;

/**
 * 公文通知
 */
public class OfficeDocumentActivity extends BaseActivity implements View.OnClickListener,
        AdapterView.OnItemClickListener, SwipeRefreshLayout.OnRefreshListener {

    private LinearLayout mLinearLayout_Back;
    private RelativeLayout mRelativeLayout_Unread;
    private RelativeLayout mRelativeLayout_Read;
    private TextView mTextView_Read;
    private TextView mTextView_Unread;
    private ImageView mImageView_IndicatorUnread;
    private ImageView mImageView_IndicatorRead;
    private ListView mListView_OfficeDocument;
    private CommonAdapter<String> mAdapter;
    private List<String> mDatas;
    private ImageView mImageView_Search;
    private SwipeRefreshLayout mSwipeRefreshLayout;


    private Handler mHandler = new Handler();
    private final Runnable mRefreshDone = new Runnable() {

        @Override
        public void run() {
            mSwipeRefreshLayout.setRefreshing(false);
        }

    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_office_document);
        super.init();
    }

    @Override
    public void initView() {
        mLinearLayout_Back = (LinearLayout) findViewById(R.id.id_ll_office_document_back);
        mRelativeLayout_Read = (RelativeLayout) findViewById(R.id.id_rl_office_document_read);
        mRelativeLayout_Unread = (RelativeLayout) findViewById(R.id.id_rl_office_document_unread);

        mTextView_Read = (TextView) findViewById(R.id.id_tv_office_document_read);
        mTextView_Unread = (TextView) findViewById(R.id.id_tv_office_document_unread);

        mImageView_IndicatorRead = (ImageView) findViewById(R.id.id_iv_office_document_indicator_read);
        mImageView_IndicatorUnread = (ImageView) findViewById(R.id.id_iv_office_document_indicator_unread);
        mListView_OfficeDocument = (ListView) findViewById(R.id.id_lv_office_document);
        mImageView_Search = (ImageView) findViewById(R.id.id_iv_office_document_search);

        mSwipeRefreshLayout = (SwipeRefreshLayout) findViewById(R.id.swipe_refresh_layout);
    }

    @Override
    public void initData() {
        mSwipeRefreshLayout.setColorSchemeResources(R.color.red400);
        mDatas = new ArrayList<>();
        mDatas.add("");
        mDatas.add("");
        mDatas.add("");
        mDatas.add("");
        mAdapter = new CommonAdapter<String>(this, mDatas, R.layout.item_office_document) {
            @Override
            public void convert(ViewHolder holder, String s) {

            }
        };
        mListView_OfficeDocument.setAdapter(mAdapter);
    }

    @Override
    public void initEvent() {
        mRelativeLayout_Unread.setOnClickListener(this);
        mRelativeLayout_Read.setOnClickListener(this);
        mLinearLayout_Back.setOnClickListener(this);
        mListView_OfficeDocument.setOnItemClickListener(this);
        mImageView_Search.setOnClickListener(this);
        mSwipeRefreshLayout.setOnRefreshListener(this);
    }

    @Override
    public void onClick(View v) {
        Animation animation;
        switch (v.getId()) {
            case R.id.id_ll_office_document_back:
                finish();
                break;
            case R.id.id_rl_office_document_unread:
                animation = AnimationUtils.loadAnimation(this, R.anim.tab_indicator_left);
                mImageView_IndicatorUnread.setAnimation(animation);
                mImageView_IndicatorUnread.setVisibility(View.VISIBLE);
                mImageView_IndicatorRead.setVisibility(View.INVISIBLE);
                mTextView_Read.setTextColor(Color.parseColor("#616161"));
                mTextView_Unread.setTextColor(Color.parseColor("#ef5350"));
                mDatas.add("");
                mDatas.add("");
                mAdapter.notifyDataSetChanged();
                break;
            case R.id.id_rl_office_document_read:
                animation = AnimationUtils.loadAnimation(this, R.anim.tab_indicator_right);
                mImageView_IndicatorRead.setAnimation(animation);
                mImageView_IndicatorRead.setVisibility(View.VISIBLE);
                mImageView_IndicatorUnread.setVisibility(View.INVISIBLE);
                mTextView_Unread.setTextColor(Color.parseColor("#616161"));
                mTextView_Read.setTextColor(Color.parseColor("#ef5350"));
                mDatas.clear();
                mAdapter.notifyDataSetChanged();
                break;
            case R.id.id_iv_office_document_search:
                Intent intent = new Intent();
                intent.setClass(this, CommoditySearchActivity.class);
                startActivity(intent);
                break;
            default:
                break;
        }

    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        Intent intent = new Intent();
        intent.setClass(this, DocumentDetailActivity.class);
        startActivity(intent);

    }

    @Override
    public void onRefresh() {
        mHandler.postDelayed(mRefreshDone, 3000);
    }
}
