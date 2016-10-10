package com.youxiao.util;

import android.content.Context;
import android.support.v7.app.AlertDialog;

/**
 *
 * @author StomHong on 2016/9/19.
 */
public class AlertDialogUtil {

    public static void create(Context context,int viewId){
        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        builder.setView(viewId);
        builder.show();
    }
}
