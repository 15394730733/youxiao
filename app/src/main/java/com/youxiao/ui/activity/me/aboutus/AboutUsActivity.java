package com.youxiao.ui.activity.me.aboutus;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;

import com.tencent.mm.sdk.modelmsg.SendMessageToWX;
import com.tencent.mm.sdk.modelmsg.WXMediaMessage;
import com.tencent.mm.sdk.modelmsg.WXWebpageObject;
import com.tencent.mm.sdk.openapi.IWXAPI;
import com.tencent.mm.sdk.openapi.WXAPIFactory;
import com.youxiao.R;

import java.io.ByteArrayOutputStream;

/**
 * 关于我们
 */
public class AboutUsActivity extends Activity implements View.OnClickListener {

    private LinearLayout back;
    private RelativeLayout officialWebsite, focusOnWeChat, shareWeChat, feedback;
    private IWXAPI api;
    private static final String APP_ID = "wxa442517984e1b848";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_about_us);
        initView();
        initEvent();
        initData();
    }

    private void initView() {
        back = (LinearLayout) findViewById(R.id.activity_about_us_back);
        officialWebsite = (RelativeLayout) findViewById(R.id.activity_about_us_official_website);
        focusOnWeChat = (RelativeLayout) findViewById(R.id.activity_about_us_Focus_on_WeChat);
        shareWeChat = (RelativeLayout) findViewById(R.id.activity_about_us_share_WeChat);
        feedback = (RelativeLayout) findViewById(R.id.activity_about_us_feedback);
    }

    private void initEvent() {
        back.setOnClickListener(this);
        officialWebsite.setOnClickListener(this);
        focusOnWeChat.setOnClickListener(this);
        shareWeChat.setOnClickListener(this);
        feedback.setOnClickListener(this);
    }

    private void initData() {
        //注册api到微信
        api = WXAPIFactory.createWXAPI(this, APP_ID, true);
        api.registerApp(APP_ID);
    }

    @Override
    public void onClick(View v) {
        Intent intent = new Intent();
        switch (v.getId()) {
            case R.id.activity_about_us_back:
                finish();
                break;
            case R.id.activity_about_us_official_website:
                intent.setClass(this, OfficialWebsiteActivity.class);
                startActivity(intent);
                break;
            case R.id.activity_about_us_Focus_on_WeChat:
                intent.setClass(this, FocusOnWeChatActivity.class);
                startActivity(intent);
                break;
            case R.id.activity_about_us_share_WeChat:
                WXWebpageObject webpage = new WXWebpageObject();
                webpage.webpageUrl = "http://www.xaoke.com.cn";
                WXMediaMessage msg = new WXMediaMessage(webpage);
                msg.title = "做分销，用优销";
                msg.description = "做分销，用优销";
                Bitmap thumb = BitmapFactory.decodeResource(getResources(), R.drawable.share_logo);
                msg.thumbData = bmpToByteArray(thumb, true);
                SendMessageToWX.Req req = new SendMessageToWX.Req();
                req.transaction = "webpage" + String.valueOf(System.currentTimeMillis());
                req.message = msg;
                req.scene = SendMessageToWX.Req.WXSceneTimeline;
                api.sendReq(req);
                break;
            case R.id.activity_about_us_feedback:
                break;
        }
    }

    /**
     * @param bmp
     * @param needRecycle
     * @return
     */
    public static byte[] bmpToByteArray(final Bitmap bmp, final boolean needRecycle) {
        ByteArrayOutputStream output = new ByteArrayOutputStream();
        bmp.compress(Bitmap.CompressFormat.PNG, 100, output);
        if (needRecycle) {
            bmp.recycle();
        }
        byte[] result = output.toByteArray();
        try {
            output.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }

}
