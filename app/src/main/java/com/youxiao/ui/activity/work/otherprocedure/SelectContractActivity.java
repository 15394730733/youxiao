package com.youxiao.ui.activity.work.otherprocedure;

import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.TextView;

import com.youxiao.R;
import com.youxiao.base.BaseActivity;
import com.youxiao.ui.fragment.MicroChatFragment;

public class SelectContractActivity extends BaseActivity implements View.OnClickListener {

    private TextView mTextView_Cancel;
    private FrameLayout mFrameLayout_Contract;
    private MicroChatFragment mMicroChatFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_select_contract);
        super.init();
    }

    @Override
    public void initView() {

        mTextView_Cancel = (TextView) findViewById(R.id.id_tv_cancel);
    }

    @Override
    public void initData() {
        FragmentManager fm = getSupportFragmentManager();
        FragmentTransaction ft = fm.beginTransaction();
        if (mMicroChatFragment == null){
            mMicroChatFragment = new MicroChatFragment();
            ft.add(R.id.id_fl_select_contract,mMicroChatFragment);
        }else {
            ft.show(mMicroChatFragment);
        }
        ft.commit();
    }

    @Override
    public void initEvent() {
        mTextView_Cancel.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.id_tv_cancel:
                finish();
                overridePendingTransition(0,R.anim.activity_exit);
                break;
            default:
                break;
        }
    }
}
