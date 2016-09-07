//package com.youxiao.ui.activity.work.customermanager;
//
//import android.app.Activity;
//import android.content.SharedPreferences;
//import android.os.Bundle;
//import android.util.Log;
//import android.view.Window;
//
//import com.andexert.calendarlistview.library.DatePickerController;
//import com.andexert.calendarlistview.library.DayPickerView;
//import com.andexert.calendarlistview.library.SimpleMonthAdapter;
//import com.youxiao.R;
//
//import java.text.SimpleDateFormat;
//import java.util.ArrayList;
//import java.util.Date;
//import java.util.List;
//import java.util.Locale;
//
//public class CalendarActivity extends Activity implements DatePickerController{
//
//    static final String TAG = CalendarActivity.class.getSimpleName();
//
//    private DayPickerView dayPickerView;
//    private String firstDateStr;
//    private String lastDateStr;
//    private SharedPreferences sharedPreferences;
//
//    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//        this.requestWindowFeature(Window.FEATURE_NO_TITLE);
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_calendar);
//
//        dayPickerView = (DayPickerView) findViewById(R.id.id_calendar_picker_view);
//        dayPickerView.setController(this);
//
//        sharedPreferences = getSharedPreferences("user_date",Activity.MODE_PRIVATE);
//    }
//
//    @Override
//    public int getMaxYear(){
//        return 2025;
//    }
//
//    @Override
//    public void onDayOfMonthSelected(int year, int month, int day){
//        //选定的日期
//        Log.e("Day Selected", day + " / " + month + " / " + year);
//    }
//
//    @Override
//    public void onDateRangeSelected(SimpleMonthAdapter.SelectedDays<SimpleMonthAdapter.CalendarDay> selectedDays){
//        //选定的日期段
//        List<String> mDatas = new ArrayList<>();
//        Date first = selectedDays.getFirst().getDate();
//        Date last = selectedDays.getLast().getDate();
//        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd",new Locale("zh","CN"));
//        firstDateStr = formatter.format(first);
//        lastDateStr = formatter.format(last);
//        mDatas.add(firstDateStr);
//        if (!firstDateStr.equals(lastDateStr)){
//            mDatas.add(lastDateStr);
//        }
//        onResultHandler();
//    }
//    /**
//     * 跳转到上一个页面
//     *
//     */
//    private void onResultHandler(){
//        SharedPreferences.Editor editor = sharedPreferences.edit();
//        editor.clear().commit();
//        editor.putString("BEGINDATE", firstDateStr);
//        editor.putString("ENDDATE", lastDateStr);
//        editor.commit();
//        this.finish();
//    }
//}
