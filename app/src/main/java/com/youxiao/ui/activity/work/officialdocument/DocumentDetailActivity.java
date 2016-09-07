package com.youxiao.ui.activity.work.officialdocument;

import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.LinearLayout;

import com.youxiao.base.BaseActivity;
import com.youxiao.R;

/**
 * 公告详情
 */
public class DocumentDetailActivity extends BaseActivity {

    private LinearLayout mLinearLayout_Back;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_document_detail);
        super.init();
    }

    @Override
    public void initView() {
        mLinearLayout_Back = (LinearLayout) findViewById(R.id.id_ll_office_document_back);
    }

    @Override
    public void initData() {

    }

    @Override
    public void initEvent() {
        mLinearLayout_Back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }
}
