package com.youxiao.ui.activity.work.contractsignature;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.youxiao.base.BaseActivity;
import com.youxiao.R;

/**
 * 查看经办签字
 */
public class ManagerSignatureDetailActivity extends BaseActivity {

    private LinearLayout mLinearLayout_Back;
    private ImageView mImageView_ManagerSignature;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_manager_signature_detail);
        super.init();
    }

    @Override
    public void initView() {
        mLinearLayout_Back = (LinearLayout) findViewById(R.id.id_ll_manager_signature_detail_back);
        mImageView_ManagerSignature = (ImageView) findViewById(R.id.id_iv_manager_signature);
    }

    @Override
    public void initData() {
        String pathName = getIntent().getStringExtra("pathName");
        Bitmap bitmap = BitmapFactory.decodeFile(pathName);
        mImageView_ManagerSignature.setImageBitmap(bitmap);
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
