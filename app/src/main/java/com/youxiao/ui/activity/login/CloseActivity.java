package com.youxiao.ui.activity.login;

import android.app.Activity;

import java.util.LinkedList;

/**
 * Created by 张小布 on 2016/3/12.
 */
public class CloseActivity {
    private static LinkedList<Activity> acys = new LinkedList<Activity>();

    public static Activity curActivity;

    public static void add(Activity acy) {
        acys.add(acy);
    }

    public static void remove(Activity acy) {
        acys.remove(acy);
    }

    public static void close() {
        Activity acy;
        while (acys.size() != 0)
        {
            acy = acys.poll();
            if (!acy.isFinishing())
            {
                acy.finish();
            }
        }
    }
}
