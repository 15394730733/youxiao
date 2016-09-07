package com.youxiao.widget;

import android.app.Dialog;
import android.content.Context;
import android.graphics.drawable.AnimationDrawable;
import android.view.Gravity;
import android.view.View;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.TextView;

import com.youxiao.R;


/**
 *
 * Created by xiaobu on 2016/6/1.
 */
public class LoadingDialog extends Dialog{


    public LoadingDialog(Context context) {
        super(context);

    }

    public LoadingDialog(Context context, int theme) {
        super(context, theme);

    }
    /**
     * 当窗口焦点改变时调用
     */
    @Override
    public void onWindowFocusChanged(boolean hasFocus) {
        ImageView imageView = (ImageView) findViewById(R.id.rotationImageView);
        // 获取ImageView上的动画背景
        AnimationDrawable anim = (AnimationDrawable) imageView.getBackground();
        // 开始动画
        anim.start();
    }

    /**
     * 给Dialog设置提示信息
     *
     * @param message
     */
    public LoadingDialog setMessage(CharSequence message) {
        if (message != null && message.length() > 0) {
            findViewById(R.id.message).setVisibility(View.VISIBLE);
            TextView txt = (TextView) findViewById(R.id.message);
            txt.setText(message);
            txt.invalidate();
        }
        return this;
    }

    /**
     * 弹出自定义LoadingDialog
     *
     * @param context
     *            上下文
     * @param message
     *            提示
     * @param cancelable
     *            是否按返回键取消
     * @param cancelListener
     *            按下返回键监听
     * @return
     */
    public  static LoadingDialog buider(Context context,CharSequence message, boolean cancelable, OnCancelListener cancelListener) {
        LoadingDialog dialog = new LoadingDialog(context, R.style.LoadingDialogStyle);
        dialog.setTitle("");
        dialog.setContentView(R.layout.layout_loading_dialog);
        TextView msgText = (TextView) dialog.findViewById(R.id.message);
        if (message == null || message.length() == 0) {
            msgText.setVisibility(View.GONE);
        } else {
            msgText.setText(message);
        }
        // 按返回键是否取消
        dialog.setCancelable(cancelable);
        // 监听返回键处理
        dialog.setOnCancelListener(cancelListener);
        // 设置居中
        dialog.getWindow().getAttributes().gravity = Gravity.CENTER;
        WindowManager.LayoutParams lp = dialog.getWindow().getAttributes();
        // 设置背景层透明度
        lp.dimAmount = 0.3f;
        dialog.getWindow().setAttributes(lp);
        // dialog.getWindow().addFlags(WindowManager.LayoutParams.FLAG_BLUR_BEHIND);
        dialog.show();
        return dialog;
    }

}
