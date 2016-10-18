package com.youxiao.ui.activity.work.customermanager;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.IBinder;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.KeyEvent;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import com.youxiao.R;
import com.youxiao.adapter.CommonAdapter;
import com.youxiao.base.BaseActivity;
import com.youxiao.model.CustomerManagerBean;

import java.util.ArrayList;
import java.util.List;


/**
 * 客户搜索
 *
 * @author StomHong
 * @since 2016-3-25
 */
public class CustomerSearchActivity extends BaseActivity implements View.OnClickListener,
        AdapterView.OnItemClickListener, View.OnKeyListener {

    private static final String TAG = CustomerSearchActivity.class.getSimpleName();

    private LinearLayout mLinearLayout_Back;
    private TextView mTextView_SearchOrCancel;
    private ListView mListView_Customer;
    private EditText mEditText_SearchContent;
    private Button mButton_CleanText;
    private List<String> mDatas;
    private String searchContent;
    private CommonAdapter<String> adapter;
    private SharedPreferences sharedPreferences;
    private List<CustomerManagerBean.Customer> customerList;
    private CustomerManagerBean.Customer customer;

    private String Words;
    private String Number;
    private int id;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_client_search);
        super.init();
    }

    @Override
    public void initView() {

        mLinearLayout_Back = (LinearLayout) findViewById(R.id.id_lay_client_search_back);
        mTextView_SearchOrCancel = (TextView) findViewById(R.id.id_client_search_search_or_cancel);
        mListView_Customer = (ListView) findViewById(R.id.id_lv_client_search_searched_customer);
        mEditText_SearchContent = (EditText) findViewById(R.id.id_et_client_search_searched_content);
        mButton_CleanText = (Button) findViewById(R.id.id_btn_client_search_clean_text);
    }

    @Override
    public void initData() {

    }

    @Override
    public void initEvent() {

        mListView_Customer.setOnItemClickListener(this);
        mLinearLayout_Back.setOnClickListener(this);
        mTextView_SearchOrCancel.setOnClickListener(this);
        mButton_CleanText.setOnClickListener(this);
        mEditText_SearchContent.setOnKeyListener(this);
        mEditText_SearchContent.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                searchContent = s.toString();
                customerList = new ArrayList<CustomerManagerBean.Customer>();
                if (customerList != null) {
                    for (int i = 0; i < customerList.size(); i++) {
                        customer = customerList.get(i);
                        Words = customer.custName;
                        Number = String.valueOf(customer.custId);
                        id = customer.id;//
                        if (Words.contains(searchContent) || Number.contains(searchContent)) {

                        }
                    }
                }
            }

            @Override
            public void afterTextChanged(Editable s) {
                if (mDatas != null) {
                    mDatas.clear();
                    adapter.notifyDataSetChanged();
                }
                searchContent = mEditText_SearchContent.getText().toString();
                if ("".equals(searchContent)) {
                    mButton_CleanText.setVisibility(View.GONE);
                    mTextView_SearchOrCancel.setText("取消");
                } else {
                    mButton_CleanText.setVisibility(View.VISIBLE);
                    mTextView_SearchOrCancel.setText("搜索");
                }
            }
        });
    }

    @Override
    public void onClick(View v) {

        switch (v.getId()) {

            case R.id.id_lay_client_search_back:
                finish();
                break;

            case R.id.id_client_search_search_or_cancel:
                String searchOrCancel = mTextView_SearchOrCancel.getText().toString();
                if ("取消".equals(searchOrCancel)) {
                    finish();
                } else {
                    hideInputMethod();
                }
                break;

            case R.id.id_btn_client_search_clean_text:
                mEditText_SearchContent.getText().clear();
                break;

            default:
                break;
        }
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {


    }

    @Override
    public boolean onKey(View v, int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_ENTER) {
            hideInputMethod();
            return true;
        }
        return false;
    }

    /**
     * 隐藏输入法
     */
    private void hideInputMethod() {

        InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
        if (imm.isActive()) {
            IBinder token = getCurrentFocus().getWindowToken();
            imm.hideSoftInputFromWindow(token, InputMethodManager.HIDE_NOT_ALWAYS);
        }
    }

}
