package com.youxiao.ui.fragment;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.youxiao.ui.activity.me.VersionInformationActivity;
import com.youxiao.ui.activity.me.aboutus.AboutUsActivity;
import com.youxiao.ui.activity.me.accountandsafety.SafetyActivity;
import com.youxiao.ui.activity.me.systemsetting.SystemSettingActivity;
import com.youxiao.R;

/**
 * 我
 */
public class MeFragment extends Fragment implements View.OnClickListener {


    private RelativeLayout systemSetting;
    private RelativeLayout safety;
    private RelativeLayout systemMessage;
    private RelativeLayout aboutUs;

    private View view_back;
    private Dialog dialog1;
    private LayoutInflater mLayoutInflater;
    private AlertDialog.Builder builder;


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.item_fragment_me, container, false);
        initView(view);
        initEvent();
        return view;
    }

    private void initEvent() {
        systemSetting.setOnClickListener(this);
        safety.setOnClickListener(this);
        systemMessage.setOnClickListener(this);
        aboutUs.setOnClickListener(this);
    }

    private void initView(View view) {
        systemSetting = (RelativeLayout) view.findViewById(R.id.item_fragment_me_RL_systemSetting);
        safety = (RelativeLayout) view.findViewById(R.id.item_fragment_me_RL_safety);
        systemMessage = (RelativeLayout) view.findViewById(R.id.item_fragment_me_RL_systemMessage);
        aboutUs = (RelativeLayout) view.findViewById(R.id.about_us);
    }

    @Override
    public void onClick(View v) {
        Intent intent = new Intent();
        switch (v.getId()) {
            case R.id.item_fragment_me_RL_systemSetting:
                intent.setClass(getActivity(), SystemSettingActivity.class);
                startActivity(intent);
                break;
            case R.id.youxiao_user_btn_back://退出应用程序
                mLayoutInflater = LayoutInflater.from(getActivity());
                view_back = mLayoutInflater.inflate(R.layout.my_alert_dialog_layout, null);

                TextView mydialog_title = (TextView) view_back.findViewById(R.id.mydialog_title);
                TextView mydialog_content = (TextView) view_back.findViewById(R.id.mydialog_content);
                Button mydialog_confirm = (Button) view_back.findViewById(R.id.mydialog_confirm);
                Button mydialog_concle = (Button) view_back.findViewById(R.id.mydialog_cancel);
                mydialog_title.setText(R.string.Hint);
                mydialog_content.setText(R.string.backActivity);
                mydialog_confirm.setText(R.string.sure);
                mydialog_concle.setText(R.string.cancel);
                mydialog_confirm.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        System.exit(0);
                        dialog1.dismiss();
                    }
                });
                mydialog_concle.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        dialog1.dismiss();
                    }
                });
                builder = new AlertDialog.Builder(getActivity());
                dialog1 = builder.create();
                dialog1.show();
                dialog1.setContentView(view_back);
                break;
            case R.id.item_fragment_me_RL_safety:
                intent.setClass(getActivity(), SafetyActivity.class);
                startActivity(intent);
                break;
            case R.id.item_fragment_me_RL_systemMessage:
                intent.setClass(getActivity(), VersionInformationActivity.class);
                startActivity(intent);
                break;
            case R.id.about_us:
                intent.setClass(getActivity(), AboutUsActivity.class);
                startActivity(intent);
                break;
            default:
                break;
        }
    }
}
