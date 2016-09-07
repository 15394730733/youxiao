package com.youxiao.util;

import android.app.Activity;
import android.content.Context;
import android.os.IBinder;
import android.view.inputmethod.InputMethodManager;

/**
 * 隐藏输入法
 *
 * @author StomHong
 * @since 2016-07-28
 */
public class HideInputMethod {

    /**
     * 隐藏输入法
     */
    public static void hideInputMethod(Activity activity) {
        InputMethodManager imm = (InputMethodManager) activity.getSystemService(Context.INPUT_METHOD_SERVICE);
        if (imm.isActive()) {
            if (activity.getCurrentFocus() != null) {
                IBinder token = activity.getCurrentFocus().getWindowToken();
                imm.hideSoftInputFromWindow(token, InputMethodManager.HIDE_NOT_ALWAYS);
            }
        }
    }
}
