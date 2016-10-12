package com.youxiao.ui.fragment;

import android.annotation.TargetApi;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Build;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.GridView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.youxiao.R;
import com.youxiao.adapter.CommonAdapter;
import com.youxiao.adapter.ViewHolder;
import com.youxiao.base.App;
import com.youxiao.base.BaseFragment;
import com.youxiao.ui.activity.login.SplashActivity;
import com.youxiao.ui.activity.me.VersionInformationActivity;
import com.youxiao.ui.activity.me.aboutus.AboutUsActivity;
import com.youxiao.ui.activity.me.accountandsafety.SafetyActivity;
import com.youxiao.ui.activity.me.personalinformation.ProvinceActivity;
import com.youxiao.ui.activity.me.systemsetting.SystemSettingActivity;
import com.youxiao.util.ScreenUtil;
import com.youxiao.util.SpUtil;
import com.youxiao.widget.Util;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 我
 */
public class MeFragment extends BaseFragment implements View.OnClickListener {


    private RelativeLayout systemSetting;
    private RelativeLayout safety;
    private RelativeLayout systemMessage;
    private RelativeLayout aboutUs;
    private RelativeLayout mRelativeLayout_InviteFriendExperience;
    private RelativeLayout mRelativeLayout_PersonalInformation;
    private RelativeLayout LogoutMe;

    private Dialog dialog1;
    private AlertDialog.Builder builder;

    private Integer[] image = new Integer[]{R.drawable.immediate_stock, R.drawable.safe_stock_warning,
            R.drawable.expiration_warning, R.drawable.weixin};

    private String[] name = new String[]{"QQ好友", "QQ空间", "微信好友", "微信朋友圈"};

    @Override
    protected int getRootView() {
        return R.layout.item_fragment_me;
    }

    @Override
    public void initEvent() {
        systemSetting.setOnClickListener(this);
        safety.setOnClickListener(this);
        systemMessage.setOnClickListener(this);
        aboutUs.setOnClickListener(this);
//        mRelativeLayout_InviteFriendExperience.setOnClickListener(this);
        mRelativeLayout_PersonalInformation.setOnClickListener(this);
        LogoutMe.setOnClickListener(this);


    }

    @Override
    public void initView(View view) {

        systemSetting = (RelativeLayout) view.findViewById(R.id.item_fragment_me_RL_systemSetting);
        safety = (RelativeLayout) view.findViewById(R.id.item_fragment_me_RL_safety);
        systemMessage = (RelativeLayout) view.findViewById(R.id.item_fragment_me_RL_systemMessage);
        aboutUs = (RelativeLayout) view.findViewById(R.id.about_us);
//        mRelativeLayout_InviteFriendExperience = (RelativeLayout) view.findViewById(R.id.rl_invite_friend_experience);
        mRelativeLayout_PersonalInformation = (RelativeLayout) view.findViewById(R.id.rl_edit_info);
        LogoutMe = (RelativeLayout) view.findViewById(R.id.log_out_account);

    }

    @Override
    public void initData() {

    }

    @TargetApi(Build.VERSION_CODES.HONEYCOMB)
    @Override
    public void onClick(View v) {
        Intent intent = new Intent();
        switch (v.getId()) {
            case R.id.item_fragment_me_RL_systemSetting:
                intent.setClass(getActivity(), SystemSettingActivity.class);
                startActivity(intent);
                break;
            case R.id.youxiao_user_btn_back://退出应用
//                View exitApp = LayoutInflater.from(getContext()).inflate(R.layout.my_alert_dialog_layout, null);
//                TextView mydialog_title = (TextView) exitApp.findViewById(R.id.mydialog_title);
//                TextView mydialog_content = (TextView) exitApp.findViewById(R.id.mydialog_content);
//                Button mydialog_confirm = (Button) exitApp.findViewById(R.id.mydialog_confirm);
//                Button mydialog_concle = (Button) exitApp.findViewById(R.id.mydialog_cancel);
//                mydialog_title.setText(R.string.Hint);
//                mydialog_content.setText(R.string.backActivity);
//                mydialog_confirm.setText(R.string.sure);
//                mydialog_concle.setText(R.string.cancel);
//                mydialog_confirm.setOnClickListener(new View.OnClickListener() {
//                    @Override
//                    public void onClick(View v) {
//                        System.exit(0);
//                        dialog1.dismiss();
//                    }
//                });
//                mydialog_concle.setOnClickListener(new View.OnClickListener() {
//                    @Override
//                    public void onClick(View v) {
//                        dialog1.dismiss();
//                    }
//                });
//                builder = new AlertDialog.Builder(getContext());
//                dialog1 = builder.create();
//                dialog1.show();
//                dialog1.setContentView(exitApp);
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
//            case R.id.rl_invite_friend_experience:
//                View shareView = LayoutInflater.from(getContext()).inflate(R.layout.share_dialog, null);
//                GridView gridView = (GridView) shareView.findViewById(R.id.gv_share);
//                TextView textView = (TextView) shareView.findViewById(R.id.tv_cancel);
//                List<Map<String, Object>> data = new ArrayList<>();
//                Map<String, Object> map;
//                for (int i = 0; i < 4; i++) {
//                    map = new HashMap<>();
//                    map.put("image", image[i]);
//                    map.put("name", name[i]);
//                    data.add(map);
//                }
//                CommonAdapter<Map<String,Object>> adapter = new CommonAdapter<Map<String,Object>>(getContext(), data, R.layout.item_share) {
//                    @Override
//                    public void convert(ViewHolder holder, Map<String,Object> map) {
//                        holder.setImageFromResource(R.id.image,(int) map.get("image"));
//                        holder.setText(R.id.text, (String) map.get("name"));
//                    }
//                };
//                gridView.setAdapter(adapter);
//                final Dialog dialog = new AlertDialog.Builder(getContext(), R.style.dialog1).create();
//                dialog.show();
//                dialog.setContentView(shareView);
//                textView.setOnClickListener(new View.OnClickListener() {
//                    @Override
//                    public void onClick(View v) {
//                        dialog.dismiss();
//                    }
//                });
//                WindowManager.LayoutParams params = dialog.getWindow().getAttributes();
//                params.width = ScreenUtil.getScreenWidth(getContext());
//                params.dimAmount = 0.3f;
//                params.gravity = Gravity.BOTTOM;
//                dialog.getWindow().setAttributes(params);
//                break;
            case R.id.rl_edit_info:
                intent.setClass(getContext(), ProvinceActivity.class);
                startActivity(intent);
                break;


            case R.id.log_out_account:
                SharedPreferences.Editor edit = SpUtil.getSp().edit();
                edit.clear();
                edit.commit();
                View exitApp = LayoutInflater.from(getContext()).inflate(R.layout.my_alert_dialog_layout, null);
                TextView mydialog_title = (TextView) exitApp.findViewById(R.id.mydialog_title);
                TextView mydialog_content = (TextView) exitApp.findViewById(R.id.mydialog_content);
                Button mydialog_confirm = (Button) exitApp.findViewById(R.id.mydialog_confirm);
                Button mydialog_concle = (Button) exitApp.findViewById(R.id.mydialog_cancel);
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
                builder = new AlertDialog.Builder(getContext());
                dialog1 = builder.create();
                dialog1.show();
                dialog1.setContentView(exitApp);
                break;
            default:
                break;
        }
    }
}
