package com.youxiao.ui.activity.work.taskboost;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;

import com.youxiao.R;
import com.youxiao.base.BaseActivity;

/**
 *  任务推进
 *
 * @author StomHong
 * @since 2016-7-27
 */
public class TaskBoostActivity extends BaseActivity implements View.OnClickListener{


    private LinearLayout mLinearLayout_Back;
    private RelativeLayout mRelativeLayout_SalesBoost;
    private RelativeLayout mRelativeLayout_ImportantProduceBoost;
    private RelativeLayout mRelativeLayout_VisitorBoost;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_task_boost);
        super.init();
    }

    @Override
    public void initView() {
        mLinearLayout_Back = (LinearLayout) findViewById(R.id.id_ll_task_boost_back);
        mRelativeLayout_SalesBoost = (RelativeLayout) findViewById(R.id.id_rl_task_boost_sales_boost);
        mRelativeLayout_ImportantProduceBoost = (RelativeLayout) findViewById(R.id.id_rl_task_boost_important_produce_boost);
        mRelativeLayout_VisitorBoost = (RelativeLayout) findViewById(R.id.id_rl_task_boost_visitor_boost);
    }

    @Override
    public void initData() {

    }

    @Override
    public void initEvent() {
        mLinearLayout_Back.setOnClickListener(this);
        mRelativeLayout_SalesBoost.setOnClickListener(this);
        mRelativeLayout_ImportantProduceBoost.setOnClickListener(this);
        mRelativeLayout_VisitorBoost.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        Intent intent = new Intent();
        switch (v.getId()){
            case R.id.id_ll_task_boost_back:
                finish();
                break;
            case R.id.id_rl_task_boost_important_produce_boost:
                intent.setClass(this,SalesBoostActivity.class);
                startActivity(intent);
                break;
            case R.id.id_rl_task_boost_sales_boost:
                intent.setClass(this,SalesBoostActivity.class);
                startActivity(intent);
                break;
            case R.id.id_rl_task_boost_visitor_boost:
                intent.setClass(this,SalesBoostActivity.class);
                startActivity(intent);
                break;
            default:
                break;
        }
    }
}
