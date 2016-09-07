package com.youxiao.model;

import android.util.Log;

import java.util.Arrays;
import java.util.Calendar;
import java.util.List;
import java.util.Locale;

/**
 * 业务类，专门处理显示数据，负责给适配器提供数据
 *
 * @author StomHong
 * @since 2016-08-19
 */
public class CalendarBiz {

    private Integer[] mDays = new Integer[7];
    private Integer[] mYears = new Integer[7];
    private Integer[] mMonths = new Integer[7];
    private Calendar mCalendar;
    private int DAY;
    int dayOfWeek;
    int day;

    public CalendarBiz() {
        mCalendar = Calendar.getInstance(Locale.CHINESE);
        dayOfWeek = mCalendar.get(Calendar.DAY_OF_WEEK);
        day = mCalendar.get(Calendar.DATE);
    }

    /**
     * 获取要显示的一周日期
     *
     * @return
     */
    public List<Integer> getDays(int week) {
        //周日的日期
        int saturdayOfPrevious;
        Log.d("TAG", dayOfWeek + "......................................");
        Log.d("TAG", week + "week......................................");
        Log.d("TAG", day + "......................................");

        mCalendar.set(Calendar.DATE, day + week * 7);
        dayOfWeek = mCalendar.get(Calendar.DAY_OF_WEEK);
        day = mCalendar.get(Calendar.DATE);


        switch (dayOfWeek) {
            case 1:
                //获取一周时间，包括年和月
                mCalendar.set(Calendar.DATE, day - 1);
                saturdayOfPrevious = mCalendar.get(Calendar.DATE);
                setDaysMonthsYearsOfWeek(saturdayOfPrevious);
                break;
            case 2:
                mCalendar.set(Calendar.DATE, day - 2);
                saturdayOfPrevious = mCalendar.get(Calendar.DATE);
                setDaysMonthsYearsOfWeek(saturdayOfPrevious);
                break;
            case 3:
                mCalendar.set(Calendar.DATE, day - 3);
                saturdayOfPrevious = mCalendar.get(Calendar.DATE);
                setDaysMonthsYearsOfWeek(saturdayOfPrevious);
                break;
            case 4:
                mCalendar.set(Calendar.DATE, day - 4);
                saturdayOfPrevious = mCalendar.get(Calendar.DATE);
                setDaysMonthsYearsOfWeek(saturdayOfPrevious);
                break;
            case 5:
                mCalendar.set(Calendar.DATE, day - 5);
                saturdayOfPrevious = mCalendar.get(Calendar.DATE);
                setDaysMonthsYearsOfWeek(saturdayOfPrevious);
                break;
            case 6:
                mCalendar.set(Calendar.DATE, day - 6);
                saturdayOfPrevious = mCalendar.get(Calendar.DATE);
                setDaysMonthsYearsOfWeek(saturdayOfPrevious);
                break;
            case 7:
                mCalendar.set(Calendar.DATE, day - 7);
                saturdayOfPrevious = mCalendar.get(Calendar.DATE);
                setDaysMonthsYearsOfWeek(saturdayOfPrevious);
                break;
        }
        return Arrays.asList(mDays);
    }

    /**
     * @param saturdayOfPrevious
     */
    private void setDaysMonthsYearsOfWeek(int saturdayOfPrevious) {
        for (int i = 0; i < 7; i++) {
            getNextDay(saturdayOfPrevious + i, i);
//            Log.d("TAG", mDays[i] + "day......................................");
//            Log.d("TAG", mMonths[i] + "month......................................");
//            Log.d("TAG", mYears[i] + "year......................................");
        }
    }

    /**
     * 获取给定日期的后一天
     *
     * @param currentDay
     * @return
     */
    private void getNextDay(int currentDay, int index) {
        mCalendar.set(Calendar.DATE, currentDay + 1);
        mDays[index] = mCalendar.get(Calendar.DAY_OF_MONTH);
        mMonths[index] = mCalendar.get(Calendar.MONTH) + 1;
        mYears[index] = mCalendar.get(Calendar.YEAR);
    }

    /**
     * 获取给定日期的前一天
     *
     * @param currentDay
     * @return
     */
    private int getPreviousDay(int currentDay) {
        mCalendar.set(Calendar.DATE, currentDay - 1);
        return mCalendar.get(Calendar.DATE);
    }


    /**
     * 获取要显示的一周日期
     *
     * @return
     */
    public String getYear(int position) {
        return Arrays.asList(mYears).get(position) + "年";
    }

    /**
     * 获取要显示的一周日期
     *
     * @return
     */
    public String getMonth(int position) {
        return changeToChinese(Arrays.asList(mMonths).get(position));
    }

    /**
     * 将
     *
     * @param month
     * @return
     */
    private String changeToChinese(int month) {
        String strMonth = "";
        switch (month) {
            case 1:
                strMonth = "一月";
                break;
            case 2:
                strMonth = "二月";
                break;
            case 3:
                strMonth = "三月";
                break;
            case 4:
                strMonth = "四月";
                break;
            case 5:
                strMonth = "五月";
                break;
            case 6:
                strMonth = "六月";
                break;
            case 7:
                strMonth = "七月";
                break;
            case 8:
                strMonth = "八月";
                break;
            case 9:
                strMonth = "九月";
                break;
            case 10:
                strMonth = "十月";
                break;
            case 11:
                strMonth = "十一月";
                break;
            case 12:
                strMonth = "十二月";
                break;
        }
        return strMonth;
    }


}
