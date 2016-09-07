package com.youxiao.ui.activity.me.aboutus;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.LinearLayout;

import com.youxiao.R;

public class FocusOnWeChatActivity extends Activity implements View.OnClickListener{
    private LinearLayout back;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_focus_on_we_chat);
//        View topView = findViewById(R.id.activity_focus_on_we_chat_RL_title);
//        ImmersedStatusbarUtils.initAfterSetContentView(this, topView);
        initView();
        initEvent();
    }

    private void initView(){
        back = (LinearLayout) findViewById(R.id.activity_focus_on_we_chat_back);
    }

    private void initEvent(){
        back.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.activity_focus_on_we_chat_back:
                this.finish();
                break;
        }
    }


}
