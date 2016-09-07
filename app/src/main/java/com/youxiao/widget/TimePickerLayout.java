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
public class TimePickerLayout extends LinearLayout {

    private WheelView mWheelYear;
    private WheelView mWheelMonth;
    private WheelView mWheelDay;

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
    ArrayList<String> mDays;

    public TimePickerLayout(Context context) {
        this(context,null);

    }

    public TimePickerLayout(Context context, AttributeSet attrs) {
        super(context, attrs);
        View view = LayoutInflater.from(getContext()).inflate(R.layout.layout_time_picker, this);
        mWheelYear = (WheelView) view.findViewById(R.id.year);
        mWheelMonth = (WheelView) view.findViewById(R.id.month);
        mWheelDay = (WheelView) view.findViewById(R.id.day);

        mWheelYear.setData(getYearData());
        mWheelMonth.setData(getMonthData());

        setDays(getCurrentYear(), getCurrentMonth());//设置显示日期

        mWheelYear.setDefault(Integer.parseInt(getCurrentYear()) % 2000 + 1);
        mWheelMonth.setDefault(Integer.parseInt(getCurrentMonth()));
        mWheelDay.setDefault(Integer.parseInt(getCurrentDay()));

        mWheelMonth.setOnSelectListener(mSelectListener);
        mWheelYear.setOnSelectListener(mSelectListener);
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
     * 获取当前选中的日期
     *
     * @return day the day that selected
     */
    public String getDay() {
        if (mWheelDay == null) {
            return null;
        }
        return mWheelDay.getSelectedText();
    }

    WheelView.OnSelectListener mSelectListener = new WheelView.OnSelectListener() {
        @Override
        public void endSelect(int id, String text) {
            int lastSelectedDay = Integer.parseInt(getDay());
            setDays(getYear(),getMonth());

            int daysInCurrentMonth = getDays(getYear(),getMonth());
            if (lastSelectedDay <= 28) {
                mWheelDay.setDefault(lastSelectedDay);
            }
            else if (lastSelectedDay == 29) {
                if (daysInCurrentMonth == 28) {
                    mWheelDay.setDefault(28);
                } else if (daysInCurrentMonth == 29) {
                    mWheelDay.setDefault(29);
                }
            } else if (lastSelectedDay == 30) {
                if (daysInCurrentMonth == 28) {
                    mWheelDay.setDefault(28);
                } else if (daysInCurrentMonth == 29) {
                    mWheelDay.setDefault(29);
                } else if (daysInCurrentMonth == 30) {
                    mWheelDay.setDefault(30);
                }
            } else if (lastSelectedDay == 31) {
                if (daysInCurrentMonth == 28) {
                    mWheelDay.setDefault(28);
                } else if (daysInCurrentMonth == 29) {
                    mWheelDay.setDefault(29);
                } else if (daysInCurrentMonth == 30) {
                    mWheelDay.setDefault(30);
                } else if (daysInCurrentMonth == 31) {
                    mWheelDay.setDefault(31);
                }
            }
        }

        @Override
        public void selecting(int id, String text) {

        }
    };

    /**
     * 根据年份和月份确定天数
     */
    private void setDays(String year, String month) {
        int selectedYear = Integer.parseInt(year);
        int selectedMonth = Integer.parseInt(month);
        switch (selectedMonth) {
            case JAN://1月
                mWheelDay.setData(getDays31());
                break;
            case FEB://2月
                //需要判断是否闰年
                if (selectedYear % 4 == 0 || selectedYear % 400 == 0) {
                    mWheelDay.setData(getDays29());
                } else {
                    mWheelDay.setData(getDays28());
                }
                break;
            case MAR://3月
                mWheelDay.setData(getDays31());
                break;
            case APR://4月
                mWheelDay.setData(getDays30());
                break;
            case MAY://5月
                mWheelDay.setData(getDays31());
                break;
            case JUN://6月
                mWheelDay.setData(getDays30());
                break;
            case JUL://7月
                mWheelDay.setData(getDays31());
                break;
            case AUG://8月
                mWheelDay.setData(getDays31());
                break;
            case SEP://9月
                mWheelDay.setData(getDays30());
                break;
            case OCT://10月
                mWheelDay.setData(getDays31());
                break;
            case NOV://11月
                mWheelDay.setData(getDays30());
                break;
            case DEC://12月
                mWheelDay.setData(getDays31());
                break;
            default:
                break;
        }

    }
    /**
     * 根据年份和月份确定天数
     */
    private int getDays(String year, String month) {
        int daysInCurrentMonth = -1;
        int selectedYear = Integer.parseInt(year);
        int selectedMonth = Integer.parseInt(month);
        switch (selectedMonth) {
            case JAN://1月
                daysInCurrentMonth = 31;
                break;
            case FEB://2月
                //需要判断是否闰年
                if (selectedYear % 4 == 0 || selectedYear % 400 == 0) {
                    daysInCurrentMonth = 29;
                } else {
                    daysInCurrentMonth = 28;
                }
                break;
            case MAR://3月
                daysInCurrentMonth = 31;
                break;
            case APR://4月
                daysInCurrentMonth = 30;
                break;
            case MAY://5月
                daysInCurrentMonth = 31;
                break;
            case JUN://6月
                daysInCurrentMonth = 30;
                break;
            case JUL://7月
                daysInCurrentMonth = 31;
                break;
            case AUG://8月
                daysInCurrentMonth = 31;
                break;
            case SEP://9月
                daysInCurrentMonth = 30;
                break;
            case OCT://10月
                mWheelDay.setData(getDays31());
                daysInCurrentMonth = 31;
                break;
            case NOV://11月
                daysInCurrentMonth = 30;
                break;
            case DEC://12月
                daysInCurrentMonth = 31;
                break;
            default:
                break;

        }
        return daysInCurrentMonth;
    }
    /**
     * 28天
     *
     * @return
     */
    private ArrayList<String> getDays28() {
        mDays = new ArrayList<>();
        for (int i = 1; i <= 28; i++) {
            mDays.add(String.valueOf(i));
        }
        return mDays;
    }

    /**
     * 29天
     *
     * @return
     */
    private ArrayList<String> getDays29() {
        mDays = new ArrayList<>();
        for (int i = 1; i <= 29; i++) {
            mDays.add(String.valueOf(i));
        }
        return mDays;
    }

    /**
     * 30天
     *
     * @return
     */
    private ArrayList<String> getDays30() {
        mDays = new ArrayList<>();
        for (int i = 1; i <= 30; i++) {
            mDays.add(String.valueOf(i));
        }
        return mDays;
    }

    /**
     * 31天
     *
     * @return
     */
    private ArrayList<String> getDays31() {
        mDays = new ArrayList<>();
        for (int i = 1; i <= 31; i++) {
            mDays.add(String.valueOf(i));
        }
        return mDays;
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

    /**
     * 获取系统当前日期
     *
     * @return
     */
    private String getCurrentDay() {
        String[] time = getSystemTime().split("-");
        return time[2];
    }
}
