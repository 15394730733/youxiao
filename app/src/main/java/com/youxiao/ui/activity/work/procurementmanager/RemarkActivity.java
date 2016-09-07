package com.youxiao.ui.activity.work.procurementmanager;

import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;

import com.youxiao.R;
import com.youxiao.base.BaseActivity;

/**
 * 单据备注
 *
 * @author StomHong
 * @since 2016-7-1
 */
public class RemarkActivity extends BaseActivity {

    private LinearLayout mLinearLayout_Back;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_remark);

        super.init();
    }

    @Override
    public void initView() {
        mLinearLayout_Back = (LinearLayout) findViewById(R.id.id_ll_back);
    }

    @Override
    public void initData() {

    }

    @Override
    public void initEvent() {
        mLinearLayout_Back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (v.getId() == R.id.id_ll_back){
                    finish();
                }
            }
        });
    }
}
