package com.youxiao.ui.activity.work.reimbursementprocedure;

import android.app.AlertDialog;
import android.app.Dialog;
import android.os.Bundle;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.RelativeLayout;

import com.youxiao.base.BaseActivity;
import com.youxiao.R;
import com.youxiao.adapter.CommonAdapter;
import com.youxiao.adapter.ViewHolder;
import com.youxiao.util.ListViewUtil;
import com.youxiao.widget.Util;

import java.util.ArrayList;
import java.util.List;

/**
 * 报销流程
 */
public class ReimbursementProcedureActivity extends BaseActivity {


    private LinearLayout mLinearLayout_Back;
    private LinearLayout mLinearLayout_Photo;
    private ListView mListView_ReimbursementDetail;
    private List<Integer> mDatas;
    private CommonAdapter<Integer> mAdapter;
    private RelativeLayout mRelativeLayout_AddReimbursementDetail;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reimbursement_procedure);
        super.init();
    }

    @Override
    public void initView() {
        mLinearLayout_Photo = (LinearLayout) findViewById(R.id.id_ll_reimbursement_photo);
        mLinearLayout_Back = (LinearLayout) findViewById(R.id.id_ll_reimbursement_back);
        mListView_ReimbursementDetail = (ListView) findViewById(R.id.id_lv_reimbursement_detail);
        mRelativeLayout_AddReimbursementDetail = (RelativeLayout) findViewById(R.id.id_rl_add_reimbursement_detail);
    }

    @Override
    public void initData() {
        mDatas = new ArrayList<>();
        mDatas.add(1);
        mAdapter = new CommonAdapter<Integer>(this, mDatas, R.layout.item_reimbursement_detail) {
            @Override
            public void convert(ViewHolder holder, Integer integer) {
                holder.setText(R.id.id_tv_reimbursement_detail, "报销明细" + "(" + integer + ")");
                if (integer == 1) {
                    holder.setViewVisibility(R.id.id_tv_reimbursement_detail_cancel, false);
                }else {
                    holder.setViewVisibility(R.id.id_tv_reimbursement_detail_cancel, true);
                }
                holder.setViewOnClick(new ViewHolder.ViewClick() {
                    @Override
                    public void onClick(View v, int position) {
                        mDatas.remove(mDatas.size()-1);
                        ListViewUtil.setListViewHeightBasedOnChildren(mListView_ReimbursementDetail);
                    }
                }, R.id.id_tv_reimbursement_detail_cancel);
            }
        };
        mListView_ReimbursementDetail.setAdapter(mAdapter);

    }

    @Override
    public void initEvent() {
        mLinearLayout_Back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        mRelativeLayout_AddReimbursementDetail.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int i = mDatas.get(mDatas.size()-1);
                mDatas.add( i+ 1);
                ListViewUtil.setListViewHeightBasedOnChildren(mListView_ReimbursementDetail);
            }
        });
        mLinearLayout_Photo.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                if (event.getAction() == MotionEvent.ACTION_UP) {
                    View view = LayoutInflater.from(ReimbursementProcedureActivity.this).inflate(R.layout.item_photo_dialog, null);
                    final Dialog dialog = new AlertDialog.Builder(getContext(), R.style.dialog1).create();
                    dialog.show();
                    dialog.setContentView(view);
                    WindowManager.LayoutParams params = dialog.getWindow().getAttributes();
                    params.width = Util.getScreenWidth(getContext());
                    params.dimAmount = 0.3f;
                    params.gravity = Gravity.BOTTOM;
                    dialog.getWindow().setAttributes(params);
                    view.findViewById(R.id.id_rl_cancel).setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            dialog.dismiss();
                        }
                    });
                }
                return true;
            }
        });
    }
}
