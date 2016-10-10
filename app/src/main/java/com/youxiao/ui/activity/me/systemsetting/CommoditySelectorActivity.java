package com.youxiao.ui.activity.me.systemsetting;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;

import com.youxiao.base.BaseActivity;
import com.youxiao.R;

/**
 * 商品选择器类型
 */
public class CommoditySelectorActivity extends BaseActivity implements View.OnClickListener {

    private RelativeLayout mRelativeLayout_StandardSelector;
    private RelativeLayout mRelativeLayout_ViewSelector;
    private ImageView mImageView_StandardSelector;
    private ImageView mImageView_ViewSelector;
    private ImageView mImageView_Commit;
    public static int SELECTOR_TYPE = 1;
    //视图商品选择
    public static final int VIEW_SELECTOR = 2;
    //标准商品选择
    public static final int STANDARD_SELECTOR = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_commodity_selector2);
        super.init();
    }

    @Override
    public void initView() {
        mImageView_StandardSelector = (ImageView) findViewById(R.id.iv_standard_selector);
        mImageView_ViewSelector = (ImageView) findViewById(R.id.iv_view_selector);
        mImageView_Commit = (ImageView) findViewById(R.id.iv_commit);

        mRelativeLayout_StandardSelector = (RelativeLayout) findViewById(R.id.rl_standard_selector);
        mRelativeLayout_ViewSelector = (RelativeLayout) findViewById(R.id.rl_view_selector);
    }

    @Override
    public void initData() {
        mImageView_StandardSelector.setVisibility(View.VISIBLE);
    }

    @Override
    public void initEvent() {
        mRelativeLayout_ViewSelector.setOnClickListener(this);
        mRelativeLayout_StandardSelector.setOnClickListener(this);
        mImageView_Commit.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.rl_standard_selector:
                mImageView_StandardSelector.setVisibility(View.VISIBLE);
                mImageView_ViewSelector.setVisibility(View.INVISIBLE);
                break;
            case R.id.rl_view_selector:
                mImageView_StandardSelector.setVisibility(View.INVISIBLE);
                mImageView_ViewSelector.setVisibility(View.VISIBLE);
                break;
            case R.id.iv_commit:
                if (mImageView_StandardSelector.getVisibility() == View.VISIBLE) {
                    SELECTOR_TYPE = STANDARD_SELECTOR;
                } else if (mImageView_ViewSelector.getVisibility() == View.VISIBLE) {
                    SELECTOR_TYPE = VIEW_SELECTOR;
                }
                finish();
                break;
        }
    }
}
