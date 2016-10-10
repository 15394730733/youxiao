package com.youxiao.ui.activity;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.youxiao.R;
import com.youxiao.base.BaseActivity;
import com.youxiao.ui.fragment.MeFragment;
import com.youxiao.ui.fragment.MicroChatFragment;
import com.youxiao.ui.fragment.SalesFragment;
import com.youxiao.ui.fragment.StatementFragment;
import com.youxiao.ui.fragment.WorkFragment;

/**
 * 主Activity，整个app的入口界面
 *
 * @author StomHong
 */
public class MainActivity extends BaseActivity implements View.OnClickListener {

    private RelativeLayout mRelativeLayout_Marketing;
    private RelativeLayout mRelativeLayout_Work;
    private RelativeLayout mRelativeLayout_Statement;
    private RelativeLayout mRelativeLayout_MicroChat;
    private RelativeLayout mRelativeLayout_Me;

    private ImageView mImageView_Sales;
    private ImageView mImageView_Work;
    private ImageView mImageView_Statement;
    private ImageView mImageView_MicroChat;
    private ImageView mImageView_Me;

    private TextView mTextView_Sales;
    private TextView mTextView_Work;
    private TextView mTextView_Statement;
    private TextView mTextView_MicroChat;
    private TextView mTextView_Me;

    private SalesFragment mSalesFragment;
    private WorkFragment mWorkFragment;
    private MicroChatFragment mMicroChatFragment;
    private StatementFragment mStatementFragment;
    private MeFragment mMeFragment;

    private static final int SALES = 0x000;
    private static final int WORK = 0x001;
    private static final int MICRO_CHAT = 0x002;
    private static final int STATEMENT = 0x003;
    private static final int ME = 0x004;

    private MainActivityListener mListener;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        super.init();
    }

    @Override
    public void initData() {
        selectFragment(SALES);
    }

    @Override
    public void initEvent() {
        mRelativeLayout_Marketing.setOnClickListener(this);
        mRelativeLayout_Work.setOnClickListener(this);
        mRelativeLayout_MicroChat.setOnClickListener(this);
        mRelativeLayout_Statement.setOnClickListener(this);
        mRelativeLayout_Me.setOnClickListener(this);
        mSalesFragment.setFragmentListener(new SalesFragment.SalesFragmentListener() {
            @Override
            public void doFragment(boolean tabSwitch) {
               switchTab (tabSwitch);
            }
        });
    }

    @Override
    public void initView() {

        mRelativeLayout_Marketing = (RelativeLayout) findViewById(R.id.rl_sales);
        mRelativeLayout_MicroChat = (RelativeLayout) findViewById(R.id.rl_micro_chat);
        mRelativeLayout_Statement = (RelativeLayout) findViewById(R.id.rl_statement);
        mRelativeLayout_Me = (RelativeLayout) findViewById(R.id.rl_me);
        mRelativeLayout_Work = (RelativeLayout) findViewById(R.id.rl_work);

        mImageView_Sales = (ImageView) findViewById(R.id.iv_main_marketing);
        mImageView_Work = (ImageView) findViewById(R.id.iv_main_work);
        mImageView_Statement = (ImageView) findViewById(R.id.iv_main_statement);
        mImageView_MicroChat = (ImageView) findViewById(R.id.iv_main_micro_chat);
        mImageView_Me = (ImageView) findViewById(R.id.iv_main_me);

        mTextView_Sales = (TextView) findViewById(R.id.tv_main_marketing);
        mTextView_Work = (TextView) findViewById(R.id.tv_main_work);
        mTextView_Statement = (TextView) findViewById(R.id.tv_main_statement);
        mTextView_MicroChat = (TextView) findViewById(R.id.tv_main_micro_chat);
        mTextView_Me = (TextView) findViewById(R.id.tv_main_me);

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.rl_sales:
                if (SalesFragment.COMMODITY_ISSELECTED) {
                    mListener.doActivity(1);
                } else {
                    selectFragment(SALES);
                }
                break;
            case R.id.rl_work:
                if (SalesFragment.COMMODITY_ISSELECTED) {
                    mListener.doActivity(2);
                } else {
                    selectFragment(WORK);
                }
                break;
            case R.id.rl_micro_chat:
                if (SalesFragment.COMMODITY_ISSELECTED) {
                    mListener.doActivity(3);
                } else {
                    selectFragment(MICRO_CHAT);
                }
                break;
            case R.id.rl_statement:
                if (SalesFragment.COMMODITY_ISSELECTED) {
                    mListener.doActivity(4);
                } else {
                    selectFragment(STATEMENT);
                }
                break;
            case R.id.rl_me:
                if (SalesFragment.COMMODITY_ISSELECTED) {
                    mListener.doActivity(5);
                } else {
                    selectFragment(ME);
                }
                break;
            default:
                break;
        }

    }


    /**
     * 根据用户的点击，改变页面和颜色
     *
     * @param fragment TabIndex枚举类的下标
     */
    private void selectFragment(int fragment) {
        resetAllTab();

        FragmentManager fm = getSupportFragmentManager();
        FragmentTransaction ft = fm.beginTransaction();
        hideAllFragment(ft);
        int red = getResources().getColor(R.color.red400);
        switch (fragment) {
            case SALES:
                mImageView_Sales.setImageResource(R.drawable.sales_pre);
                mTextView_Sales.setTextColor(red);
                if (mSalesFragment == null) {
                    ft.add(R.id.id_lay_container, mSalesFragment = new SalesFragment(), "sales");
                } else {
                    ft.show(mSalesFragment);
                }
                break;
            case WORK:
                mImageView_Work.setImageResource(R.drawable.work_pre);
                mTextView_Work.setTextColor(red);
                if (mWorkFragment == null) {
                    ft.add(R.id.id_lay_container, mWorkFragment = new WorkFragment(), "work");
                } else {
                    ft.show(mWorkFragment);
                }
                break;
            case MICRO_CHAT:
                mImageView_MicroChat.setImageResource(R.drawable.micro_chat_pre);
                mTextView_MicroChat.setTextColor(red);
                if (mMicroChatFragment == null) {
                    ft.add(R.id.id_lay_container, mMicroChatFragment = new MicroChatFragment(), "microChat");
                } else {
                    ft.show(mMicroChatFragment);
                }
                break;
            case STATEMENT:
                mImageView_Statement.setImageResource(R.drawable.statement_pre);
                mTextView_Statement.setTextColor(red);
                if (mStatementFragment == null) {
                    ft.add(R.id.id_lay_container, mStatementFragment = new StatementFragment(), "statement");
                } else {
                    ft.show(mStatementFragment);
                }
                break;
            case ME:
                mImageView_Me.setImageResource(R.drawable.me_pre);
                mTextView_Me.setTextColor(red);
                if (mMeFragment == null) {
                    ft.add(R.id.id_lay_container, mMeFragment = new MeFragment(), "me");
                } else {
                    ft.show(mMeFragment);
                }
                break;
            default:
                break;
        }
        ft.commit();
    }

    /**
     * 重置所有Tab
     */
    private void resetAllTab() {
        mImageView_Sales.setImageResource(R.drawable.sales);
        mImageView_Work.setImageResource(R.drawable.work);
        mImageView_MicroChat.setImageResource(R.drawable.micro_chat);
        mImageView_Statement.setImageResource(R.drawable.statement);
        mImageView_Me.setImageResource(R.drawable.me);

        int grey = getResources().getColor(R.color.grey500);
        mTextView_Sales.setTextColor(grey);
        mTextView_Work.setTextColor(grey);
        mTextView_Statement.setTextColor(grey);
        mTextView_MicroChat.setTextColor(grey);
        mTextView_Me.setTextColor(grey);
    }

    /**
     * 切换tab的图片和文字
     */
    private void switchTab(boolean b) {
        if (b){
            resetAllTab();
            mImageView_Sales.setImageResource(R.drawable.sales_pre);
            mTextView_Sales.setTextColor(getResources().getColor(R.color.red400));
            mTextView_Sales.setText("营销");
            mTextView_Work.setText("工作");
            mTextView_MicroChat.setText("微通");
            mTextView_Statement.setText("报表");
            mTextView_Me.setText("我");
        }else {
            mImageView_Sales.setImageResource(R.drawable.set_to_return_goods);
            mImageView_Work.setImageResource(R.drawable.set_to_display);
            mImageView_MicroChat.setImageResource(R.drawable.set_to_gift);
            mImageView_Statement.setImageResource(R.drawable.turn_to_stock);
            mImageView_Me.setImageResource(R.drawable.delete_line);
            mTextView_Sales.setText("设为退货");
            mTextView_Work.setText("设为陈列");
            mTextView_MicroChat.setText("设为赠品");
            mTextView_Statement.setText("转为存货");
            mTextView_Me.setText("删除行");
            mTextView_Sales.setTextColor(getResources().getColor(R.color.grey500));
        }
    }

    /**
     * 隐藏所有的Fragment
     *
     * @param ft 事务对象实例
     */
    private void hideAllFragment(FragmentTransaction ft) {
        hideFragment(ft, mSalesFragment);
        hideFragment(ft, mWorkFragment);
        hideFragment(ft, mMicroChatFragment);
        hideFragment(ft, mStatementFragment);
        hideFragment(ft, mMeFragment);
    }

    /**
     * 隐藏Fragment
     *
     * @param ft       fragment事务对象
     * @param fragment fragment对象
     */
    private void hideFragment(FragmentTransaction ft, Fragment fragment) {
        if (fragment == null)
            return;
        ft.hide(fragment);
    }
    /**
     * 为了让Fragment能够响应Activity的操作而写的接口
     */
    public interface MainActivityListener {
        void doActivity(int tabClicked);
    }

    public void setActivityListener(MainActivityListener listener) {
        mListener = listener;
    }
}
