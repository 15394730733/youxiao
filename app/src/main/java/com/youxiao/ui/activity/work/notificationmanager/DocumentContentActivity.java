package com.youxiao.ui.activity.work.notificationmanager;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.LinearLayout;

import com.youxiao.R;

/**
 *
 */
public class DocumentContentActivity extends Activity {

    private LinearLayout mLinearLayout_Back;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_document_content);
        mLinearLayout_Back = (LinearLayout) findViewById(R.id.id_ll_document_content_back);
        mLinearLayout_Back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }
}
