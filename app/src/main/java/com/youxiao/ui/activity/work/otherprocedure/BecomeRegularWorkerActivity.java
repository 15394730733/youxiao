package com.youxiao.ui.activity.work.otherprocedure;

import android.app.AlertDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.youxiao.base.BaseActivity;
import com.youxiao.R;
import com.youxiao.widget.Util;
import com.youxiao.widget.WheelView;

/**
 * 转正
 */
public class BecomeRegularWorkerActivity extends BaseActivity implements View.OnClickListener {


    private ImageView mImageView_Add;
    private LinearLayout mLinearLayout_Back;
    private LinearLayout mLinearLayout_EntryDate;
    private AlertDialog mProduceDateDialog;
    private View mTimePickerView;
    private WheelView mWheelView_Year;
    private WheelView mWheelView_Month;
    private WheelView mWheelView_Day;
    private TextView mTextView_EntryDate;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_become_regular_worker);
        super.init();
    }

    @Override
    public void initView() {
        mImageView_Add = (ImageView) findViewById(R.id.id_iv_add);
        mLinearLayout_Back = (LinearLayout) findViewById(R.id.id_ll_becom_regular_worker_back);
        mLinearLayout_EntryDate = (LinearLayout) findViewById(R.id.id_ll_entry_date);
        mTextView_EntryDate = (TextView) findViewById(R.id.id_tv_entry_date);
    }

    @Override
    public void initData() {

    }

    @Override
    public void initEvent() {
        mImageView_Add.setOnClickListener(this);
        mLinearLayout_EntryDate.setOnClickListener(this);
        mLinearLayout_Back.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.id_iv_add:
                Intent intent = new Intent();
                intent.setClass(this, SelectContractActivity.class);
                startActivity(intent);
                overridePendingTransition(R.anim.activity_enter, R.anim.activity_exit);
                break;
            case R.id.id_ll_becom_regular_worker_back:
                finish();
                break;
            case R.id.id_ll_entry_date:
                processDateDialog(mTextView_EntryDate);
                break;
            default:
                break;
        }
    }


    /**
     * 日期选择Dialog
     */
    private void processDateDialog(final TextView clickedView) {

        mTimePickerView = LayoutInflater.from(getContext()).inflate(R.layout.layout_time_picker_layout, null);

        TextView mTextView_Cancel = (TextView) mTimePickerView.findViewById(R.id.id_tv_time_picker_cancel);
        TextView mTextView_Confirm = (TextView) mTimePickerView.findViewById(R.id.id_tv_time_picker_confirm);
        mTextView_Cancel.setOnClickListener(this);
        mTextView_Confirm.setOnClickListener(this);
        mTextView_Cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mProduceDateDialog.dismiss();
            }
        });
        mTextView_Confirm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String day = mWheelView_Day.getSelectedText();
                String month = mWheelView_Month.getSelectedText();
                String year = mWheelView_Year.getSelectedText();
                String date = year + "年" + month + "月" + day + "日";
                clickedView.setText(date);
                mProduceDateDialog.dismiss();
            }
        });
        mWheelView_Year = (WheelView) mTimePickerView.findViewById(R.id.year);
        mWheelView_Month = (WheelView) mTimePickerView.findViewById(R.id.month);
        mWheelView_Day = (WheelView) mTimePickerView.findViewById(R.id.day);

        mProduceDateDialog = new AlertDialog.Builder(getContext(), R.style.dialog1).create();
        mProduceDateDialog.show();
        mProduceDateDialog.setContentView(mTimePickerView);
        WindowManager.LayoutParams params = mProduceDateDialog.getWindow().getAttributes();
        params.width = Util.getScreenWidth(getContext());
        params.dimAmount = 0.3f;
        params.gravity = Gravity.BOTTOM;
        mProduceDateDialog.getWindow().setAttributes(params);

    }
}
