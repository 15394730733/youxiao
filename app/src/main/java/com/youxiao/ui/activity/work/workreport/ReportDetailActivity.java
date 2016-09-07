package com.youxiao.ui.activity.work.workreport;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.widget.LinearLayout;
import android.widget.ProgressBar;

import com.youxiao.base.BaseActivity;
import com.youxiao.R;

import java.lang.ref.WeakReference;

/**
 * 日报详情
 */
public class ReportDetailActivity extends BaseActivity {

    private static final String TAG = ReportDetailActivity.class.getSimpleName();
    private LinearLayout mLinearLayout_Back;
    private ProgressBar mProgressBar;
    private MyHandler myHandler;
    static int progress = 0;


    Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            progress = progress + 1;
            mProgressBar.setProgress(progress);

        }
    };
    private Runnable mRunnable = new Runnable() {

        @Override
        public void run() {
            if (progress < 100) {
                myHandler.sendEmptyMessage(1);
                myHandler.postDelayed(mRunnable, 500);
            }
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_report_detail);
        super.init();
    }

    @Override
    public void initView() {
        mProgressBar = (ProgressBar) findViewById(R.id.id_pb_progress_bar);
        mLinearLayout_Back = (LinearLayout) findViewById(R.id.id_ll_work_report_back);

    }

    @Override
    public void initData() {
        new Thread(new Runnable() {
            @Override
            public void run() {
                if (progress < 100) {
                    handler.sendEmptyMessage(1);
                    handler.postDelayed(this, 500);
                }
            }
        }).start();

//        myHandler = new MyHandler(this);
//        new Thread(mRunnable).start();
    }

    @Override
    public void initEvent() {
        mLinearLayout_Back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }


    private static class MyHandler extends Handler {

        WeakReference<ReportDetailActivity> mWeakReferenceActivity;

        public MyHandler(ReportDetailActivity activity) {
            mWeakReferenceActivity = new WeakReference<>(activity);
        }

        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            if (msg.what == 1) {
                if (mWeakReferenceActivity != null) {
                    progress = progress + 1;
                    ReportDetailActivity activity = mWeakReferenceActivity.get();
                    activity.mProgressBar.setProgress(progress);

                    Log.d(TAG, "progress......................" + progress);
                }
            }
        }
    }
}
