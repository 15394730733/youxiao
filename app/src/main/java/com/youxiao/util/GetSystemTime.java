package com.youxiao.util;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

/**
 * 根据给定的字符串格式，返回指定格式的时间
 *
 * @author StomHong
 * @since 2016-07-28
 */
public class GetSystemTime {

    /**
     * 获取系统时间
     *  @param format the format you want
     * @return 指定格式的时间
     */
    public static String getTime(String format) {
        long currentTimeMillis = System.currentTimeMillis();
        SimpleDateFormat sdf = new SimpleDateFormat(format, Locale.CHINESE);
        return sdf.format(new Date(currentTimeMillis));
    }
}
