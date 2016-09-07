package com.youxiao.ui.activity.me.aboutus;

import android.os.Bundle;
import android.view.View;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.youxiao.base.BaseActivity;
import com.youxiao.R;

/**
 * 官方网站
 */
public class OfficialWebsiteActivity extends BaseActivity implements View.OnClickListener {

    private WebView mWebView;
    private ImageView mImageView_Back;
    private ImageView mImageView_Forward;
    private LinearLayout mLinearLayout_Back;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_official_website);
        super.init();
    }

    @Override
    public void initView() {
        mWebView = (WebView) findViewById(R.id.webview);
        mLinearLayout_Back = (LinearLayout) findViewById(R.id.ll_back);
        mImageView_Back = (ImageView) findViewById(R.id.iv_back);
        mImageView_Forward = (ImageView) findViewById(R.id.iv_forward);
    }

    @Override
    public void initData() {
        WebChromeClient wcc = new WebChromeClient() {
            @Override
            public void onReceivedTitle(WebView view, String title) {
                super.onReceivedTitle(view, title);
            }
        };
        // 设置setWebChromeClient对象
        mWebView.setWebChromeClient(wcc);
        mWebView.zoomOut();
        // 创建WebViewClient对象
        WebViewClient wvc = new WebViewClient() {
            @Override
            public boolean shouldOverrideUrlLoading(WebView view, String url) {
                // 使用自己的WebView组件来响应Url加载事件，而不是使用默认浏览器器加载页面
                mWebView.loadUrl(url);
                return true;
            }
        };
        mWebView.setWebViewClient(wvc);
        mWebView.loadUrl("http://www.xaoke.com.cn/");
        mWebView.setInitialScale(0);
        WebSettings settings = mWebView.getSettings();
        settings.setJavaScriptEnabled(true);
        settings.setUseWideViewPort(true);
        settings.setLoadWithOverviewMode(true);
        //设置支持缩放
        settings.setSupportZoom(true);


    }

    @Override
    public void initEvent() {
        mImageView_Forward.setOnClickListener(this);
        mImageView_Back.setOnClickListener(this);
        mLinearLayout_Back.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.iv_back:
                mWebView.goBack();
                break;
            case R.id.iv_forward:
                mWebView.goForward();
                break;
            case R.id.ll_back:
                finish();
                break;
            default:
                break;
        }
    }
}
