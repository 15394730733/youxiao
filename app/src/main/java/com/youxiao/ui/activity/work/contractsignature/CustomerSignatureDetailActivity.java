package com.youxiao.ui.activity.work.contractsignature;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.youxiao.R;
import com.youxiao.base.BaseActivity;

public class CustomerSignatureDetailActivity extends BaseActivity {

    private LinearLayout mLinearLayout_Back;
    private ImageView mImageView_CustomerSignature;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_customer_signature_detail);
        super.init();
    }

    @Override
    public void initView() {
        mLinearLayout_Back = (LinearLayout) findViewById(R.id.id_ll_customer_signature_detail_back);
        mImageView_CustomerSignature = (ImageView) findViewById(R.id.id_iv_customer_signature);
    }

    @Override
    public void initData() {
        String pathName = getIntent().getStringExtra("pathName");
        Bitmap bitmap = BitmapFactory.decodeFile(pathName);
        mImageView_CustomerSignature.setImageBitmap(bitmap);
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
