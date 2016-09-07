package com.youxiao.ui.activity;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.youxiao.R;
import com.youxiao.base.BaseActivity;
import com.youxiao.imp.OnPaneOpenAndCloseListener;
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
public class MainActivity extends BaseActivity implements View.OnClickListener, OnPaneOpenAndCloseListener {

    private RelativeLayout mRelativeLayout_Marketing;
    private RelativeLayout mRelativeLayout_Work;
    private RelativeLayout mRelativeLayout_Statement;
    private RelativeLayout mRelativeLayout_MicroChat;
    private RelativeLayout mMeLayout;

    private ImageView mImageView_Marketing;
    private ImageView mImageView_Work;
    private ImageView mImageView_Statement;
    private ImageView mImageView_MicroChat;
    private ImageView mImageView_Me;

    private TextView mTextView_Marketing;
    private TextView mTextView_Work;
    private TextView mTextView_Statement;
    private TextView mTextView_MicroChat;
    private TextView mTextView_Me;

    private Fragment mSalesFragment;
    private Fragment mWorkFragment;
    private Fragment mMicroChatFragment;
    private Fragment mStatementFragment;
    private Fragment mMeFragment;

    private static final int MARKETING = 0x000;
    private static final int WORK = 0x001;
    private static final int MICRO_CHAT = 0x002;
    private static final int STATEMENT = 0x003;
    private static final int ME = 0x004;
    public LinearLayout mLinearLayout_Commit;
    private LinearLayout mLinearLayout_MainTab;
    private TextView mTextView_Commit;

    private ViewClick mClick;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        super.init();
    }

    @Override
    public void initData() {
        selectFragment(MARKETING);

    }

    @Override
    public void initEvent() {

        mRelativeLayout_Marketing.setOnClickListener(this);
        mRelativeLayout_Work.setOnClickListener(this);
        mRelativeLayout_MicroChat.setOnClickListener(this);
        mRelativeLayout_Statement.setOnClickListener(this);
        mMeLayout.setOnClickListener(this);
        mTextView_Commit.setOnClickListener(this);
    }

    @Override
    public void initView() {

        mRelativeLayout_Marketing = (RelativeLayout) findViewById(R.id.rl_marketing);
        mRelativeLayout_MicroChat = (RelativeLayout) findViewById(R.id.rl_micro_chat);
        mRelativeLayout_Statement = (RelativeLayout) findViewById(R.id.rl_statement);
        mMeLayout = (RelativeLayout) findViewById(R.id.rl_me);
        mRelativeLayout_Work = (RelativeLayout) findViewById(R.id.rl_work);

        mImageView_Marketing = (ImageView) findViewById(R.id.iv_main_marketing);
        mImageView_Work = (ImageView) findViewById(R.id.iv_main_work);
        mImageView_Statement = (ImageView) findViewById(R.id.iv_main_statement);
        mImageView_MicroChat = (ImageView) findViewById(R.id.iv_main_micro_chat);
        mImageView_Me = (ImageView) findViewById(R.id.iv_main_me);

        mTextView_Marketing = (TextView) findViewById(R.id.tv_main_marketing);
        mTextView_Work = (TextView) findViewById(R.id.tv_main_work);
        mTextView_Statement = (TextView) findViewById(R.id.tv_main_statement);
        mTextView_MicroChat = (TextView) findViewById(R.id.tv_main_micro_chat);
        mTextView_Me = (TextView) findViewById(R.id.tv_main_me);

        mLinearLayout_Commit = (LinearLayout) findViewById(R.id.ll_marketing_commit);
        mLinearLayout_MainTab = (LinearLayout) findViewById(R.id.ll_main_tab);
        mTextView_Commit = (TextView) findViewById(R.id.tv_commit);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.rl_marketing:
                selectFragment(MARKETING);
                break;
            case R.id.rl_work:
                selectFragment(WORK);
                break;
            case R.id.rl_micro_chat:
                selectFragment(MICRO_CHAT);
                break;
            case R.id.rl_statement:
                selectFragment(STATEMENT);
                break;
            case R.id.rl_me:
                selectFragment(ME);
                break;
            case R.id.tv_commit:
                mClick.onViewClick(mTextView_Commit);
                onPaneOpenAndClose(false);
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
            case MARKETING:
                mImageView_Marketing.setImageResource(R.drawable.marketing_pre);
                mTextView_Marketing.setTextColor(red);
                if (mSalesFragment == null) {
                    mSalesFragment = new SalesFragment();
                    mClick = (ViewClick) mSalesFragment;
                    ft.add(R.id.id_lay_container, mSalesFragment, "sales");
                } else {
                    ft.show(mSalesFragment);
                }
                break;
            case WORK:
                mImageView_Work.setImageResource(R.drawable.work_pre);
                mTextView_Work.setTextColor(red);
                if (mWorkFragment == null) {
                    mWorkFragment = new WorkFragment();
                    ft.add(R.id.id_lay_container, mWorkFragment, "work");
                } else {
                    ft.show(mWorkFragment);
                }
                break;
            case MICRO_CHAT:
                mImageView_MicroChat.setImageResource(R.drawable.micro_chat_pre);
                mTextView_MicroChat.setTextColor(red);
                if (mMicroChatFragment == null) {
                    mMicroChatFragment = new MicroChatFragment();
                    ft.add(R.id.id_lay_container, mMicroChatFragment, "microChat");
                } else {
                    ft.show(mMicroChatFragment);
                }
                break;
            case STATEMENT:
                mImageView_Statement.setImageResource(R.drawable.statement_pre);
                mTextView_Statement.setTextColor(red);
                if (mStatementFragment == null) {
                    mStatementFragment = new StatementFragment();
                    ft.add(R.id.id_lay_container, mStatementFragment, "statement");
                } else {
                    ft.show(mStatementFragment);
                }
                break;
            case ME:
                mImageView_Me.setImageResource(R.drawable.me_pre);
                mTextView_Me.setTextColor(red);
                if (mMeFragment == null) {
                    mMeFragment = new MeFragment();
                    ft.add(R.id.id_lay_container, mMeFragment, "me");
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
        mImageView_Marketing.setImageResource(R.drawable.marketing);
        mImageView_Work.setImageResource(R.drawable.work);
        mImageView_MicroChat.setImageResource(R.drawable.micro_chat);
        mImageView_Statement.setImageResource(R.drawable.statement);
        mImageView_Me.setImageResource(R.drawable.me);

        int grey = getResources().getColor(R.color.grey500);
        mTextView_Marketing.setTextColor(grey);
        mTextView_Work.setTextColor(grey);
        mTextView_Statement.setTextColor(grey);
        mTextView_MicroChat.setTextColor(grey);
        mTextView_Me.setTextColor(grey);
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

    @Override
    public void onPaneOpenAndClose(boolean visible) {
        if (visible) {
            mLinearLayout_Commit.setVisibility(View.VISIBLE);
            mLinearLayout_MainTab.setVisibility(View.INVISIBLE);
        } else {
            mLinearLayout_Commit.setVisibility(View.INVISIBLE);
            mLinearLayout_MainTab.setVisibility(View.VISIBLE);
        }
    }


    public interface ViewClick {

        void onViewClick(View v);
    }

}
