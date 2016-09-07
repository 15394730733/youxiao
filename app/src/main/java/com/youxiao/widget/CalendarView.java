package com.youxiao.widget;

import android.content.Context;
import android.util.AttributeSet;
import android.view.GestureDetector;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.animation.AnimationUtils;
import android.widget.AbsListView;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.ViewFlipper;

import com.youxiao.R;
import com.youxiao.adapter.CommonAdapter;
import com.youxiao.adapter.ViewHolder;
import com.youxiao.model.CalendarBiz;

import java.util.ArrayList;
import java.util.List;

/**
 * 仿ios日历选择器
 *
 * @author StomHong
 * @since 2016-08-19
 */
public class CalendarView extends LinearLayout implements View.OnTouchListener, AdapterView.OnItemClickListener {


    private ViewFlipper mViewFlipper;
    private GridView mGridView;
    private GridView mGridView_Week;
    private MyGestureListener mGestureListener;
    private GestureDetector mGestureDetector;
    private int selectedPosition;

    private CommonAdapter<Integer> mAdapter;
    private CommonAdapter<String> mWeekAdapter;
    private List<Integer> mDatas;
    private List<String> mWeekDatas;
    CalendarBiz calendarBiz = new CalendarBiz();



    TextView mTextView_Month;
    TextView mTextView_Year;
    int week = 0;

    public CalendarView(Context context) {
        this(context, null);
    }

    public CalendarView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context);

    }

    private void init(Context context) {
        View view = LayoutInflater.from(context).inflate(R.layout.layout_calendar, this);
        mViewFlipper = (ViewFlipper) view.findViewById(R.id.viewflipper);
        mTextView_Month = (TextView) findViewById(R.id.tv_month);
        mTextView_Year = (TextView) findViewById(R.id.tv_year);

        mGridView_Week = (GridView) findViewById(R.id.gridview);
        mWeekDatas = new ArrayList<>();
        mWeekDatas.add("日");
        mWeekDatas.add("一");
        mWeekDatas.add("二");
        mWeekDatas.add("三");
        mWeekDatas.add("四");
        mWeekDatas.add("五");
        mWeekDatas.add("六");
        mWeekAdapter = new CommonAdapter<String>(context, mWeekDatas, R.layout.item_calendar_) {
            @Override
            public void convert(ViewHolder holder, String week) {
                holder.setText(R.id.tv_text, week);
            }
        };
        mGridView_Week.setAdapter(mWeekAdapter);
        mGestureListener = new MyGestureListener();
        mGestureDetector = new GestureDetector(context, mGestureListener);
        initGridView();
        mDatas = new ArrayList<>();
        mDatas.addAll(calendarBiz.getDays(0));

        mAdapter = new CommonAdapter<Integer>(context, mDatas, R.layout.item_calendar_) {
            @Override
            public void convert(ViewHolder holder, Integer integer) {
                holder.setText(R.id.tv_text, String.valueOf(integer));
            }
        };
        mGridView.setAdapter(mAdapter);
        mViewFlipper.addView(mGridView, 0);
//        mTextView_Year.setText(calendarBiz.getYear());
//        mTextView_Month.setText(calendarBiz.getMonth());
    }

    private void initGridView() {
        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(
                AbsListView.LayoutParams.MATCH_PARENT, AbsListView.LayoutParams.WRAP_CONTENT);
        mGridView = new GridView(getContext());
        mGridView.setNumColumns(7);
        mGridView.setGravity(Gravity.CENTER_VERTICAL);
//        mGridView.setSelector(new ColorDrawable(Color.TRANSPARENT));
        mGridView.setVerticalSpacing(1);
        mGridView.setHorizontalSpacing(1);

        mGridView.setOnTouchListener(this);
        mGridView.setOnItemClickListener(this);

        mGridView.setLayoutParams(params);
    }


    @Override
    public boolean onTouch(View view, MotionEvent motionEvent) {
        return mGestureDetector.onTouchEvent(motionEvent);

    }

    @Override
    public void onItemClick(AdapterView<?> adapterView, View view, int position, long l) {
        TextView textView = (TextView) view.findViewById(R.id.tv_text);
//        mGridView.setSelection(position);
//        textView.setBackgroundResource(R.drawable.noselect);

        selectedPosition = position;
        textView.setBackgroundResource(R.drawable.shape_circle_red);
//        textView.setTextColor("");
        mTextView_Year.setText(calendarBiz.getYear(position));
        mTextView_Month.setText(calendarBiz.getMonth(position));

    }

    /**
     * 手势监听
     */
    private class MyGestureListener implements GestureDetector.OnGestureListener {

        @Override
        public boolean onDown(MotionEvent motionEvent) {
            return false;
        }

        @Override
        public void onShowPress(MotionEvent motionEvent) {

        }

        @Override
        public boolean onSingleTapUp(MotionEvent motionEvent) {
            return false;
        }

        @Override
        public boolean onScroll(MotionEvent motionEvent, MotionEvent motionEvent1, float v, float v1) {
            return false;
        }

        @Override
        public void onLongPress(MotionEvent motionEvent) {

        }

        @Override
        public boolean onFling(MotionEvent e1, MotionEvent e2, float v, float v1) {
            //1、准备好数据
            //2、设置适配器
            //1、添加View
            int gvFlag = 0;

            if (e1.getX() - e2.getX() > 20) {//左滑
                mDatas.clear();
                week++;
                mDatas.addAll(calendarBiz.getDays(week));
                initGridView();
                mAdapter = new CommonAdapter<Integer>(getContext(), mDatas, R.layout.item_calendar_) {
                    @Override
                    public void convert(ViewHolder holder, Integer day) {
                        holder.setText(R.id.tv_text, String.valueOf(day));
                    }
                };
                mGridView.setAdapter(mAdapter);
                gvFlag++;
                mViewFlipper.addView(mGridView, gvFlag);

                mViewFlipper.setInAnimation(AnimationUtils.loadAnimation(getContext(),
                        R.anim.push_left_in));
                mViewFlipper.setOutAnimation(AnimationUtils.loadAnimation(getContext(),
                        R.anim.push_left_out));
                mViewFlipper.showNext();
                mViewFlipper.removeViewAt(0);
                return true;

            } else if (e1.getX() - e2.getX() < -20) {//右滑


                mDatas.clear();
                week--;
                mDatas.addAll(calendarBiz.getDays(week));
                initGridView();
                mAdapter = new CommonAdapter<Integer>(getContext(), mDatas, R.layout.item_calendar_) {
                    @Override
                    public void convert(ViewHolder holder, Integer day) {
                        holder.setText(R.id.tv_text, String.valueOf(day));
                    }
                };
                mGridView.setAdapter(mAdapter);
                gvFlag++;
                mViewFlipper.addView(mGridView, gvFlag);

                mViewFlipper.setInAnimation(AnimationUtils.loadAnimation(getContext(),
                        R.anim.push_right_in));
                mViewFlipper.setOutAnimation(AnimationUtils.loadAnimation(getContext(),
                        R.anim.push_right_out));
                mViewFlipper.showPrevious();
                mViewFlipper.removeViewAt(0);
                return true;
            }
            return false;
        }
    }


}
