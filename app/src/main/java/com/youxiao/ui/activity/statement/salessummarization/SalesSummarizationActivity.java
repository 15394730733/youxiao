package com.youxiao.ui.activity.statement.salessummarization;

import android.content.res.Resources;
import android.content.res.TypedArray;
import android.os.Bundle;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.view.View;
import android.view.ViewTreeObserver;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;

import com.youxiao.R;
import com.youxiao.base.BaseActivity;
import com.youxiao.ui.fragment.ScreenFragment;

/**
 * 销售汇总
 */
public class SalesSummarizationActivity extends BaseActivity implements View.OnClickListener,
        ScreenFragment.CancelDrawerLayout {


    private LinearLayout mLinearLayout_Back;
    private RelativeLayout mEndDrawer;
    private DrawerLayout mDrawerLayout;
    private ImageView mImageView_Screen;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sales_summarization);
        super.init();

    }

    @Override
    public void initView() {
        mLinearLayout_Back = (LinearLayout) findViewById(R.id.id_ll_sales_summarization_back);
        mEndDrawer = (RelativeLayout) findViewById(R.id.end_drawer);
        mDrawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);
        mImageView_Screen = (ImageView) findViewById(R.id.iv_screen);
    }

    @Override
    public void initData() {
        mDrawerLayout.setDrawerShadow(R.drawable.drawer_shadow_end, GravityCompat.END);
        mDrawerLayout.getViewTreeObserver().addOnPreDrawListener(
                new ViewTreeObserver.OnPreDrawListener() {
                    @Override
                    public boolean onPreDraw() {
                        // What is the width of the entire DrawerLayout?
                        final int drawerLayoutWidth = mDrawerLayout.getWidth();

                        // What is the action bar size?
                        final Resources.Theme theme = mDrawerLayout.getContext().getTheme();
                        final TypedArray a = theme.obtainStyledAttributes(
                                new int[]{android.support.v7.appcompat.R.attr.actionBarSize});
                        final int actionBarSize = a.getDimensionPixelSize(0, 0);
                        if (a != null) {
                            a.recycle();
                        }

                        // Compute the width of the drawer and set it on the layout params.
                        final int idealDrawerWidth = 5 * actionBarSize;
                        final int maxDrawerWidth = Math.max(0, drawerLayoutWidth - actionBarSize);
                        final int drawerWidth = Math.min(idealDrawerWidth, maxDrawerWidth);

                        final DrawerLayout.LayoutParams endDrawerLp =
                                (DrawerLayout.LayoutParams) mEndDrawer.getLayoutParams();
                        endDrawerLp.width = drawerWidth;
                        mEndDrawer.setLayoutParams(endDrawerLp);

                        // Remove ourselves as the pre-draw listener since this is a one-time
                        // configuration.
                        mDrawerLayout.getViewTreeObserver().removeOnPreDrawListener(this);
                        return true;
                    }
                });
    }

    @Override
    public void initEvent() {
        mLinearLayout_Back.setOnClickListener(this);
        mImageView_Screen.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.id_ll_sales_summarization_back:
                finish();
                break;
            case R.id.iv_screen:
                mDrawerLayout.openDrawer(mEndDrawer);
                mDrawerLayout.setStatusBarBackgroundColor(getResources().getColor(R.color.grey50));
                mDrawerLayout.setDrawerLockMode(DrawerLayout.LOCK_MODE_LOCKED_OPEN);
                break;
            default:
                break;
        }
    }

    @Override
    public void onBackPressed() {
        if (mDrawerLayout.isDrawerOpen(mEndDrawer)) {
            mDrawerLayout.closeDrawer(mEndDrawer);
        } else {
            super.onBackPressed();
        }
    }


    @Override
    public void cancel() {
        mDrawerLayout.setDrawerLockMode(DrawerLayout.LOCK_MODE_UNDEFINED);
        mDrawerLayout.closeDrawer(mEndDrawer);
    }
}
