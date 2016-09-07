package com.youxiao.widget;

import android.content.Context;
import android.graphics.Color;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.AbsListView;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.FrameLayout;
import android.widget.ListView;
import android.widget.TextView;

import com.youxiao.R;

import java.util.List;

/**
 * 模拟Dialog效果的选择布局
 *
 * @author StomHong
 */
public class FakeDialogLayout extends FrameLayout implements AdapterView.OnItemClickListener {

    private ListView mListView;
    private int mClickPosition;
    private List<String> mData;
    private Animation mAnimation;
    private ItemViewClick mItemViewClick;
    private ViewVisible mViewVisible;


    public FakeDialogLayout(Context context) {
        this(context, null);

    }

    public FakeDialogLayout(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public FakeDialogLayout(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initView(context);
        initEvent();
    }

    private void initView(Context context) {
        setBackgroundColor(Color.parseColor("#66414141"));
        mListView = new ListView(context);
        mListView.setLayoutParams(new AbsListView.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,
                ViewGroup.LayoutParams.WRAP_CONTENT));
        mListView.setBackgroundColor(getResources().getColor(R.color.grey0));
        addView(mListView);
    }

    private void initData() {
        ArrayAdapter adapter = new ArrayAdapter<String>(getContext(), android.R.layout.simple_list_item_1, mData) {
            @Override
            public View getView(int position, View convertView, ViewGroup parent) {
                View view = super.getView(position, convertView, parent);
                TextView textView = (TextView) view.findViewById(android.R.id.text1);
                textView.setTextColor(getResources().getColor(R.color.grey700));
                return view;
            }
        };
        mListView.setAdapter(adapter);
    }

    private void initEvent() {
        mListView.setOnItemClickListener(this);
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        mClickPosition = position;
        hide();
        mItemViewClick.onItemViewClick();

    }

    /**
     * 获取点击item的位置
     *
     * @return
     */
    public int getClickPosition() {
        return mClickPosition;
    }

    /**
     * 获取ListView对象
     *
     * @return
     */
    public ListView getListView() {
        return mListView;
    }

    /**
     * 给Adapter设置数据
     *
     * @param data
     */
    public void setListData(List<String> data) {
        mData = data;
        initData();
    }

    /**
     * 获取点击item的内容
     *
     * @return
     */
    public String getItemContent() {
        return mData.get(mClickPosition);
    }

    /**
     * 显示FakeDialogLayout
     */
    public void show(ItemViewClick itemViewClick,ViewVisible viewVisible) {
        setVisibility(VISIBLE);
        mAnimation = AnimationUtils.loadAnimation(getContext(), R.anim.fake_dialog_layout_enter);
        mAnimation.setAnimationListener(new MyAnimationListener());
        mListView.setAnimation(mAnimation);
        mListView.setVisibility(VISIBLE);
        mItemViewClick = itemViewClick;
        mViewVisible = viewVisible;
    }

    /**
     * 隐藏FakeDialogLayout
     */
    public void hide() {
        mAnimation = AnimationUtils.loadAnimation(getContext(), R.anim.fake_dialog_layout_exit);
        mAnimation.setAnimationListener(new MyAnimationListener());
        mListView.setAnimation(mAnimation);
        mListView.setVisibility(GONE);


    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        if (event.getAction() == MotionEvent.ACTION_UP) {
            hide();
        }
        return true;
    }

    /**
     * 动画执行监听
     */
    private class MyAnimationListener implements Animation.AnimationListener {


        @Override
        public void onAnimationStart(Animation animation) {

        }

        @Override
        public void onAnimationEnd(Animation animation) {
            if (mListView.getVisibility() == GONE) {
                setVisibility(GONE);
                mViewVisible.onViewVisible(false);
            } else if (mListView.getVisibility() == VISIBLE) {
                setVisibility(VISIBLE);
                mViewVisible.onViewVisible(true);
            }
        }

        @Override
        public void onAnimationRepeat(Animation animation) {

        }
    }

    /**
     * itemView点击监听
     */
    public interface ItemViewClick {

        void onItemViewClick();
    }

    /**
     * 此布局是否可见监听
     */
    public interface ViewVisible{

        void onViewVisible(boolean visible);
    }

}
