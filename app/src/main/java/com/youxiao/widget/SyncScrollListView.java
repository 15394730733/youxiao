package com.youxiao.widget;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.ListView;

/**
 * 同步滚动ListView
 *
 * @author StomHong
 * @since 2016-07-11
 */
public class SyncScrollListView extends ListView {

    private ListViewScrollListener mListViewScrollListener;

    public SyncScrollListView(Context context) {
        super(context);
    }

    public SyncScrollListView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public SyncScrollListView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    public void setListViewScrollListener(ListViewScrollListener listViewScrollListener) {
        this.mListViewScrollListener = listViewScrollListener;
    }


    @Override
    protected void onScrollChanged(int l, int t, int oldl, int oldt) {
        super.onScrollChanged(l, t, oldl, oldt);
        if(mListViewScrollListener != null) {
            mListViewScrollListener.onScrollChanged(this, l, t, oldl, oldt);
        }
    }

    public interface ListViewScrollListener {
        /**
         * ListView滚动监听
         * @param scrollView
         * @param l
         * @param t
         * @param oldl
         * @param oldt
         */
        void onScrollChanged(SyncScrollListView scrollView, int l, int t, int oldl, int oldt);

    }

}
