//package com.youxiao.ui.activity.salesActivity.salesModule;
//
//import android.app.Activity;
//import android.content.Context;
//import android.content.SharedPreferences;
//import android.os.Bundle;
//import android.view.View;
//import android.view.Window;
//import android.widget.ImageView;
//import android.widget.LinearLayout;
//import android.widget.RelativeLayout;
//import android.widget.TextView;
//
//import com.youxiao.R;
//import com.youxiao.util.ImmersedStatusbarUtils;
//
///**
// * 张小布
// *
// * 销售模板
// */
//public class SalesTemplateActivity extends Activity implements View.OnClickListener {
//
//    private RelativeLayout tradition_trench, typeTemplate, modern_trench, special_trench, typeTemplate1, typeTemplate2;
//    private ImageView traditionImageViewDown, modernImageViewDown, specialImageViewDown, traditionImageViewTop, modernImageViewTop,
//            specialImageViewTop;
//    private LinearLayout traditionAtype, traditionBtype, traditionCtype, traditionDtype,
//            modernAtype, modernBtype, modernCtype, modernDtype,
//            specialAtype, specialBtype, specialCtype, specialDtype,
//            linearLayoutBack;
//    private TextView textViewTradition, textViewModern, textViewSpecial,
//            textTraditionAtype, textTraditionBtype, textTraditionCtype, textTraditionDtype,
//            textModernAtype, textModernBtype, textModernCtype, textModernDtype,
//            textSpecialAtype, textSpecialBtype, textSpecialCtype, textSpecialDtype,
//            textViewSure;
//    private String tradition, modern, special;
//    private SharedPreferences sharedPreferences;
//
//    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//        requestWindowFeature(Window.FEATURE_NO_TITLE);
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_sales_template);
////        View topView = findViewById(R.id.activity_sales_template_title);
////        ImmersedStatusbarUtils.initAfterSetContentView(this, topView);
//        initView();
//        initEvent();
//        initData();
//    }
//
//    private void initView() {
//        tradition_trench = (RelativeLayout) findViewById(R.id.activity_sales_template_tradition_trench);
//        modern_trench = (RelativeLayout) findViewById(R.id.activity_sales_template_RL_modern_trench);
//        special_trench = (RelativeLayout) findViewById(R.id.activity_sales_template_RL_special_trench);
//        typeTemplate = (RelativeLayout) findViewById(R.id.activity_sales_template_tradition_trench_RL_type_trench);
//        typeTemplate1 = (RelativeLayout) findViewById(R.id.activity_sales_template_modern_trench_RL_type_trench);
//        typeTemplate2 = (RelativeLayout) findViewById(R.id.activity_sales_template_special_trench_RL_type_trench);
//
//        traditionImageViewDown = (ImageView) findViewById(R.id.activity_sales_template_IV_Adown);
//        modernImageViewDown = (ImageView) findViewById(R.id.activity_sales_template_IV_Bdown);
//        specialImageViewDown = (ImageView) findViewById(R.id.activity_sales_template_IV_Cdown);
//        traditionImageViewTop = (ImageView) findViewById(R.id.activity_sales_template_IV_Atop);
//        modernImageViewTop = (ImageView) findViewById(R.id.activity_sales_template_IV_Btop);
//        specialImageViewTop = (ImageView) findViewById(R.id.activity_sales_template_IV_Ctop);
//
//        traditionAtype = (LinearLayout) findViewById(R.id.activity_sales_template_tradition_trench_RL_LL_Atype_trench);
//        traditionBtype = (LinearLayout) findViewById(R.id.activity_sales_template_tradition_trench_RL_LL_Btype_trench);
//        traditionCtype = (LinearLayout) findViewById(R.id.activity_sales_template_tradition_trench_RL_LL_Ctype_trench);
//        traditionDtype = (LinearLayout) findViewById(R.id.activity_sales_template_tradition_trench_RL_LL_Dtype_trench);
//
//        modernAtype = (LinearLayout) findViewById(R.id.activity_sales_template_modern_trench_RL_LL_Atype_trench);
//        modernBtype = (LinearLayout) findViewById(R.id.activity_sales_template_modern_trench_RL_LL_Btype_trench);
//        modernCtype = (LinearLayout) findViewById(R.id.activity_sales_template_modern_trench_RL_LL_Ctype_trench);
//        modernDtype = (LinearLayout) findViewById(R.id.activity_sales_template_modern_trench_RL_LL_Dtype_trench);
//
//        specialAtype = (LinearLayout) findViewById(R.id.activity_sales_template_special_trench_RL_LL_Atype_trench);
//        specialBtype = (LinearLayout) findViewById(R.id.activity_sales_template_special_trench_RL_LL_Btype_trench);
//        specialCtype = (LinearLayout) findViewById(R.id.activity_sales_template_special_trench_RL_LL_Ctype_trench);
//        specialDtype = (LinearLayout) findViewById(R.id.activity_sales_template_special_trench_RL_LL_Dtype_trench);
//
//        textViewTradition = (TextView) findViewById(R.id.activity_sales_template_tradition_trench_TV_template);
//        textViewModern = (TextView) findViewById(R.id.activity_sales_template_modern_trench_TV_template);
//        textViewSpecial = (TextView) findViewById(R.id.activity_sales_template_special_trench_TV_template);
//
//        textTraditionAtype = (TextView) findViewById(R.id.avtivity_sales_template_tradition_Atype_trenchTextView);
//        textTraditionBtype = (TextView) findViewById(R.id.avtivity_sales_template_tradition_Btype_trenchTextView);
//        textTraditionCtype = (TextView) findViewById(R.id.avtivity_sales_template_tradition_Ctype_trenchTextView);
//        textTraditionDtype = (TextView) findViewById(R.id.avtivity_sales_template_tradition_Dtype_trenchTextView);
//
//        textModernAtype = (TextView) findViewById(R.id.avtivity_sales_template_modern_Atype_trenchTextView);
//        textModernBtype = (TextView) findViewById(R.id.avtivity_sales_template_modern_Btype_trenchTextView);
//        textModernCtype = (TextView) findViewById(R.id.avtivity_sales_template_modern_Ctype_trenchTextView);
//        textModernDtype = (TextView) findViewById(R.id.avtivity_sales_template_modern_Dtype_trenchTextView);
//
//        textSpecialAtype = (TextView) findViewById(R.id.avtivity_sales_template_special_Atype_trenchTextView);
//        textSpecialBtype = (TextView) findViewById(R.id.avtivity_sales_template_special_Btype_trenchTextView);
//        textSpecialCtype = (TextView) findViewById(R.id.avtivity_sales_template_special_Ctype_trenchTextView);
//        textSpecialDtype = (TextView) findViewById(R.id.avtivity_sales_template_special_Dtype_trenchTextView);
//
//        linearLayoutBack = (LinearLayout) findViewById(R.id.activity_sales_template_LL_back);
//        textViewSure = (TextView) findViewById(R.id.activity_sales_template_sure);
//
//    }
//
//    private void initEvent() {
//        tradition_trench.setOnClickListener(this);
//        modern_trench.setOnClickListener(this);
//        special_trench.setOnClickListener(this);
//        traditionAtype.setOnClickListener(this);
//        traditionBtype.setOnClickListener(this);
//        traditionCtype.setOnClickListener(this);
//        traditionDtype.setOnClickListener(this);
//        modernAtype.setOnClickListener(this);
//        modernBtype.setOnClickListener(this);
//        modernCtype.setOnClickListener(this);
//        modernDtype.setOnClickListener(this);
//        specialAtype.setOnClickListener(this);
//        specialBtype.setOnClickListener(this);
//        specialCtype.setOnClickListener(this);
//        specialDtype.setOnClickListener(this);
//        linearLayoutBack.setOnClickListener(this);
//        textViewSure.setOnClickListener(this);
//
//    }
//
//    private void initData() {
//        sharedPreferences = getSharedPreferences("user_info", Context.MODE_PRIVATE);
//        tradition = sharedPreferences.getString("tradition", "");
//        modern = sharedPreferences.getString("modern", "");
//        special = sharedPreferences.getString("special", "");
//        if (tradition.equals("")){
//            textViewTradition.setText("A类模板");
//        }else {
//            textViewTradition.setText(tradition);
//        }
//        if (modern.equals("")){
//            textViewModern.setText("A类模板");
//        }else {
//            textViewModern.setText(modern);
//        }
//        if (special.equals("")){
//            textViewSpecial.setText("A类模板");
//        }else {
//            textViewSpecial.setText(special);
//        }
//    }
//
//    @Override
//    public void onClick(View v) {
//        switch (v.getId()) {
//            case R.id.activity_sales_template_LL_back:
//                this.finish();
//                break;
//            case R.id.activity_sales_template_sure:
//                SharedPreferences.Editor userinfoEditor = sharedPreferences.edit();
//                userinfoEditor.putString("tradition", tradition);
//                userinfoEditor.putString("modern", modern);
//                userinfoEditor.putString("special", special);
//                userinfoEditor.clear().commit();
//                this.finish();
//                break;
//            case R.id.activity_sales_template_tradition_trench:
//                if (typeTemplate.getVisibility() == View.GONE) {
//                    typeTemplate.setVisibility(View.VISIBLE);
//                    traditionImageViewDown.setVisibility(View.GONE);
//                    traditionImageViewTop.setVisibility(View.VISIBLE);
//
//                } else {
//                    typeTemplate.setVisibility(View.GONE);
//                    traditionImageViewDown.setVisibility(View.VISIBLE);
//                    traditionImageViewTop.setVisibility(View.GONE);
//                }
//                break;
//            case R.id.activity_sales_template_RL_modern_trench:
//                if (typeTemplate1.getVisibility() == View.GONE) {
//                    typeTemplate1.setVisibility(View.VISIBLE);
//                    modernImageViewDown.setVisibility(View.GONE);
//                    modernImageViewTop.setVisibility(View.VISIBLE);
//
//                } else {
//                    typeTemplate1.setVisibility(View.GONE);
//                    modernImageViewDown.setVisibility(View.VISIBLE);
//                    modernImageViewTop.setVisibility(View.GONE);
//                }
//                break;
//            case R.id.activity_sales_template_RL_special_trench:
//                if (typeTemplate2.getVisibility() == View.GONE) {
//                    typeTemplate2.setVisibility(View.VISIBLE);
//                    specialImageViewDown.setVisibility(View.GONE);
//                    specialImageViewTop.setVisibility(View.VISIBLE);
//
//                } else {
//                    typeTemplate2.setVisibility(View.GONE);
//                    specialImageViewDown.setVisibility(View.VISIBLE);
//                    specialImageViewTop.setVisibility(View.GONE);
//                }
//                break;
//            case R.id.activity_sales_template_tradition_trench_RL_LL_Atype_trench:
//                if (typeTemplate.getVisibility() == View.GONE) {
//                    typeTemplate.setVisibility(View.VISIBLE);
//                    traditionImageViewDown.setVisibility(View.GONE);
//                    traditionImageViewTop.setVisibility(View.VISIBLE);
//
//                } else {
//                    typeTemplate.setVisibility(View.GONE);
//                    traditionImageViewDown.setVisibility(View.VISIBLE);
//                    traditionImageViewTop.setVisibility(View.GONE);
//
//                }
//                tradition = textTraditionAtype.getText().toString().trim();
//                textViewTradition.setText(tradition);
//                break;
//            case R.id.activity_sales_template_tradition_trench_RL_LL_Btype_trench:
//                if (typeTemplate.getVisibility() == View.GONE) {
//                    typeTemplate.setVisibility(View.VISIBLE);
//                    traditionImageViewDown.setVisibility(View.GONE);
//                    traditionImageViewTop.setVisibility(View.VISIBLE);
//                } else {
//                    typeTemplate.setVisibility(View.GONE);
//                    traditionImageViewDown.setVisibility(View.VISIBLE);
//                    traditionImageViewTop.setVisibility(View.GONE);
//                }
//                tradition = textTraditionBtype.getText().toString().trim();
//                textViewTradition.setText(tradition);
//                break;
//            case R.id.activity_sales_template_tradition_trench_RL_LL_Ctype_trench:
//                if (typeTemplate.getVisibility() == View.GONE) {
//                    typeTemplate.setVisibility(View.VISIBLE);
//                    traditionImageViewDown.setVisibility(View.GONE);
//                    traditionImageViewTop.setVisibility(View.VISIBLE);
//                } else {
//                    typeTemplate.setVisibility(View.GONE);
//                    traditionImageViewDown.setVisibility(View.VISIBLE);
//                    traditionImageViewTop.setVisibility(View.GONE);
//                }
//                tradition = textTraditionCtype.getText().toString().trim();
//                textViewTradition.setText(tradition);
//                break;
//            case R.id.activity_sales_template_tradition_trench_RL_LL_Dtype_trench:
//                if (typeTemplate.getVisibility() == View.GONE) {
//                    typeTemplate.setVisibility(View.VISIBLE);
//                    traditionImageViewDown.setVisibility(View.GONE);
//                    traditionImageViewTop.setVisibility(View.VISIBLE);
//                } else {
//                    typeTemplate.setVisibility(View.GONE);
//                    traditionImageViewDown.setVisibility(View.VISIBLE);
//                    traditionImageViewTop.setVisibility(View.GONE);
//                }
//                tradition = textTraditionDtype.getText().toString().trim();
//                textViewTradition.setText(tradition);
//                break;
//            case R.id.activity_sales_template_modern_trench_RL_LL_Atype_trench:
//                if (typeTemplate1.getVisibility() == View.GONE) {
//                    typeTemplate1.setVisibility(View.VISIBLE);
//                    modernImageViewDown.setVisibility(View.GONE);
//                    modernImageViewTop.setVisibility(View.VISIBLE);
//                } else {
//                    typeTemplate1.setVisibility(View.GONE);
//                    modernImageViewDown.setVisibility(View.VISIBLE);
//                    modernImageViewTop.setVisibility(View.GONE);
//                }
//                modern = textModernAtype.getText().toString().trim();
//                textViewModern.setText(modern);
//                break;
//            case R.id.activity_sales_template_modern_trench_RL_LL_Btype_trench:
//                if (typeTemplate1.getVisibility() == View.GONE) {
//                    typeTemplate1.setVisibility(View.VISIBLE);
//                    modernImageViewDown.setVisibility(View.GONE);
//                    modernImageViewTop.setVisibility(View.VISIBLE);
//                } else {
//                    typeTemplate1.setVisibility(View.GONE);
//                    modernImageViewDown.setVisibility(View.VISIBLE);
//                    modernImageViewTop.setVisibility(View.GONE);
//                }
//                modern = textModernBtype.getText().toString().trim();
//                textViewModern.setText(modern);
//                break;
//            case R.id.activity_sales_template_modern_trench_RL_LL_Ctype_trench:
//                if (typeTemplate1.getVisibility() == View.GONE) {
//                    typeTemplate1.setVisibility(View.VISIBLE);
//                    modernImageViewDown.setVisibility(View.GONE);
//                    modernImageViewTop.setVisibility(View.VISIBLE);
//                } else {
//                    typeTemplate1.setVisibility(View.GONE);
//                    modernImageViewDown.setVisibility(View.VISIBLE);
//                    modernImageViewTop.setVisibility(View.GONE);
//                }
//                modern = textModernCtype.getText().toString().trim();
//                textViewModern.setText(modern);
//                break;
//            case R.id.activity_sales_template_modern_trench_RL_LL_Dtype_trench:
//                if (typeTemplate1.getVisibility() == View.GONE) {
//                    typeTemplate1.setVisibility(View.VISIBLE);
//                    modernImageViewDown.setVisibility(View.GONE);
//                    modernImageViewTop.setVisibility(View.VISIBLE);
//                } else {
//                    typeTemplate1.setVisibility(View.GONE);
//                    modernImageViewDown.setVisibility(View.VISIBLE);
//                    modernImageViewTop.setVisibility(View.GONE);
//                }
//                modern = textModernDtype.getText().toString().trim();
//                textViewModern.setText(modern);
//                break;
//            case R.id.activity_sales_template_special_trench_RL_LL_Atype_trench:
//                if (typeTemplate2.getVisibility() == View.GONE) {
//                    typeTemplate2.setVisibility(View.VISIBLE);
//                    specialImageViewDown.setVisibility(View.GONE);
//                    specialImageViewTop.setVisibility(View.VISIBLE);
//                } else {
//                    typeTemplate2.setVisibility(View.GONE);
//                    specialImageViewDown.setVisibility(View.VISIBLE);
//                    specialImageViewTop.setVisibility(View.GONE);
//                }
//                special = textSpecialAtype.getText().toString().trim();
//                textViewSpecial.setText(special);
//                break;
//            case R.id.activity_sales_template_special_trench_RL_LL_Btype_trench:
//                if (typeTemplate2.getVisibility() == View.GONE) {
//                    typeTemplate2.setVisibility(View.VISIBLE);
//                    specialImageViewDown.setVisibility(View.GONE);
//                    specialImageViewTop.setVisibility(View.VISIBLE);
//                } else {
//                    typeTemplate2.setVisibility(View.GONE);
//                    specialImageViewDown.setVisibility(View.VISIBLE);
//                    specialImageViewTop.setVisibility(View.GONE);
//                }
//                special = textSpecialBtype.getText().toString().trim();
//                textViewSpecial.setText(special);
//                break;
//            case R.id.activity_sales_template_special_trench_RL_LL_Ctype_trench:
//                if (typeTemplate2.getVisibility() == View.GONE) {
//                    typeTemplate2.setVisibility(View.VISIBLE);
//                    specialImageViewDown.setVisibility(View.GONE);
//                    specialImageViewTop.setVisibility(View.VISIBLE);
//                } else {
//                    typeTemplate2.setVisibility(View.GONE);
//                    specialImageViewDown.setVisibility(View.VISIBLE);
//                    specialImageViewTop.setVisibility(View.GONE);
//                }
//                special = textSpecialCtype.getText().toString().trim();
//                textViewSpecial.setText(special);
//                break;
//            case R.id.activity_sales_template_special_trench_RL_LL_Dtype_trench:
//                if (typeTemplate2.getVisibility() == View.GONE) {
//                    typeTemplate2.setVisibility(View.VISIBLE);
//                    specialImageViewDown.setVisibility(View.GONE);
//                    specialImageViewTop.setVisibility(View.VISIBLE);
//                } else {
//                    typeTemplate2.setVisibility(View.GONE);
//                    specialImageViewDown.setVisibility(View.VISIBLE);
//                    specialImageViewTop.setVisibility(View.GONE);
//                }
//                special = textSpecialDtype.getText().toString().trim();
//                textViewSpecial.setText(special);
//                break;
//
//        }
//    }
//}
