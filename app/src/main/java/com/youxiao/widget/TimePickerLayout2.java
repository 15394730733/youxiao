package com.youxiao.widget;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.LinearLayout;

import com.youxiao.R;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Locale;


/**
 * @author StomHong
 * @since 2016-6-28
 */
public class TimePickerLayout2 extends LinearLayout {

    private WheelView mWheelYear;
    private WheelView mWheelMonth;

    private static final int JAN = 1;
    private static final int FEB = 2;
    private static final int MAR = 3;
    private static final int APR = 4;
    private static final int MAY = 5;
    private static final int JUN = 6;
    private static final int JUL = 7;
    private static final int AUG = 8;
    private static final int SEP = 9;
    private static final int OCT = 10;
    private static final int NOV = 11;
    private static final int DEC = 12;

    public TimePickerLayout2(Context context) {
        this(context,null);

    }

    public TimePickerLayout2(Context context, AttributeSet attrs) {
        super(context, attrs);
        View view = LayoutInflater.from(getContext()).inflate(R.layout.layout_time_picker2, this);
        mWheelYear = (WheelView) view.findViewById(R.id.year);
        mWheelMonth = (WheelView) view.findViewById(R.id.month);

        mWheelYear.setData(getYearData());
        mWheelMonth.setData(getMonthData());

        mWheelYear.setDefault(Integer.parseInt(getCurrentYear()) % 2000 + 1);
        mWheelMonth.setDefault(Integer.parseInt(getCurrentMonth()));

    }

    @Override
    protected void onFinishInflate() {
        super.onFinishInflate();

    }

    /**
     * 获取年份区间
     *
     * @return years
     */
    private ArrayList<String> getYearData() {
        ArrayList<String> list = new ArrayList<>();
        for (int i = 2000; i <= 2050; i++) {
            list.add(String.valueOf(i));
        }
        return list;
    }

    /**
     * 获取月份区间
     *
     * @return months
     */
    private ArrayList<String> getMonthData() {
        ArrayList<String> list = new ArrayList<>();
        for (int i = 1; i <= 12; i++) {
            list.add(String.valueOf(i));
        }
        return list;
    }

    /**
     * 获取当前选中的年份
     *
     * @return year the year that selected
     */
    public String getYear() {
        if (mWheelYear == null) {
            return null;
        }
        return mWheelYear.getSelectedText();
    }

    /**
     * 获取当前选中的月份
     *
     * @return month the month that selected
     */
    public String getMonth() {
        if (mWheelMonth == null) {
            return null;
        }
        return mWheelMonth.getSelectedText();
    }

    /**
     * 获取系统时间
     *
     * @return
     */
    private String getSystemTime() {
        long time = System.currentTimeMillis();
        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd", Locale.CHINESE);
        return dateFormat.format(time);
    }

    /**
     * 获取系统当前年份
     *
     * @return
     */
    private String getCurrentYear() {
        String[] time = getSystemTime().split("-");
        return time[0];
    }

    /**
     * 获取系统当前月份
     *
     * @return
     */
    private String getCurrentMonth() {
        String[] time = getSystemTime().split("-");
        return time[1];
    }

}
