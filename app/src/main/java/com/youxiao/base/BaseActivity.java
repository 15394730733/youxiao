package com.youxiao.base;

import android.content.Context;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Window;

/**
 * Activity基类，用于提供统一的编写步骤，
 * 但是子类必须要手动调用父类的init()方法
 * @author StomHong
 * @since 2016-3-15
 */
public abstract class BaseActivity extends AppCompatActivity {

    /**
     * 上下文属性，给子类操作提供方便
     */
    private Context mContext ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        super.onCreate(savedInstanceState);
        mContext = this;
    }

    /**
     * 主逻辑：三个步骤，用于规范化编写，使逻辑清晰，代码可读
     */
    public void init() {
        initView();
        initData();
        initEvent();

    }

    /**
     * 1、查找并绑定控件，或者绑定监听器
     */
    public abstract void initView();

    /**
     * 2、初始化数据
     */
    public abstract void initData();

    /**
     * 3、业务逻辑处理
     */
    public abstract void initEvent();

    /**
     * 获取上下文Context
     *
     * @return Context
     */
    public Context getContext(){
        return mContext;
    }
}
