package com.youxiao.ui.activity.work.vacationprocedure;

import android.app.AlertDialog;
import android.app.Dialog;
import android.os.Bundle;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.youxiao.base.BaseActivity;
import com.youxiao.R;
import com.youxiao.widget.Util;

/**
 * 请假流程
 */
public class VacationProcedureActivity extends BaseActivity implements View.OnClickListener {


    private LinearLayout mLinearLayout_Back;
    private ImageView mImageView_Photo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_vacation_procedure);
        super.init();
    }

    @Override
    public void initView() {
        mLinearLayout_Back = (LinearLayout) findViewById(R.id.id_ll_work_vacation_procedure_back);
        mImageView_Photo = (ImageView) findViewById(R.id.id_ll_work_photo);
    }

    @Override
    public void initData() {

    }

    @Override
    public void initEvent() {
        mLinearLayout_Back.setOnClickListener(this);
        mImageView_Photo.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.id_ll_work_vacation_procedure_back:
                finish();
                break;
            case R.id.id_ll_work_photo:
                View view = LayoutInflater.from(this).inflate(R.layout.item_for_photo_dialog, null);
                final Dialog dialog = new AlertDialog.Builder(getContext(), R.style.dialog1).create();
                dialog.show();
                dialog.setContentView(view);
                WindowManager.LayoutParams params = dialog.getWindow().getAttributes();
                params.width = Util.getScreenWidth(getContext());
                params.dimAmount = 0.3f;
                params.gravity = Gravity.BOTTOM;
                dialog.getWindow().setAttributes(params);
                view.findViewById(R.id.id_tv_item_for_photo_dialog_cancel).setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        dialog.dismiss();
                    }
                });
                break;
            default:
                break;
        }
    }
}
