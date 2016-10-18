package com.youxiao.ui.activity.sales.salesmodule;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.google.gson.Gson;
import com.youxiao.R;
import com.youxiao.adapter.SearchResultAdapter;
import com.youxiao.base.BaseActivity;
import com.youxiao.model.SearchResultBean;
import com.youxiao.ui.activity.login.MyApplication;
import com.youxiao.util.SpUtil;
import com.youxiao.util.ToastUtil;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

/**
 * 营销界面的商品搜索对应的的界面
 * Created by Administrator on 2016/9/23.
 */
public class SearchActivity extends BaseActivity implements View.OnClickListener {
    private EditText activity_commodity_search_ET_search;//搜索框
    private TextView activity_commodity_search_TV_cacel;//取消
    private String et_input;
    private ListView listview;
    private int id;
    public Context context = this;
    private List<SearchResultBean.CommodityBean.Commodity>cmdtyNames;
    private SearchResultBean.CommodityBean.Commodity commodity;
    private List<SearchResultBean.CommodityBean.Commodity> commodities;
//    private List<SearchResultBean.CommodityBean.Commodity> commodities;
    private JSONObject jsonObject;
    private String cmdtySpell;
    private String cmdtyName;
    private String barCode;
    private Handler handler = new Handler() {



        public void handleMessage(Message msg) {
            try {
                boolean flag = jsonObject.getBoolean("flag");
                String result = jsonObject.getString("result");
                if (flag) {
                    Gson gson = new Gson();
                    SearchResultBean searchResultBean = gson.fromJson(jsonObject + "", SearchResultBean.class);
                    SearchResultBean.CommodityBean data = searchResultBean.data;
                    commodities = data.commodity;
                } else {
                    ToastUtil.show("暂无数据");
                }
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
    };


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_commodity_search);
        getInfo();
        initView();
        initData();
        initEvent();
    }

    @Override
    public void initView() {
        //搜索内容输入框
        activity_commodity_search_ET_search = (EditText) findViewById(R.id.activity_commodity_search_ET_search);
        //取消
        activity_commodity_search_TV_cacel = (TextView) findViewById(R.id.activity_commodity_search_TV_cacel);
        listview = (ListView) findViewById(R.id.listview);
        activity_commodity_search_ET_search.addTextChangedListener(new TextWatcher() {


            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
                //输入文字之前
            }
            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                //输入文字的时候
                et_input = s.toString();
                //创建一个集合用于存放商品名称
                cmdtyNames=new ArrayList<>();
                if(commodities!=null){
                    for (int i = 0; i < commodities.size(); i++) {
                        //拿到一个商品对象
                        commodity = commodities.get(i);
                        //再创建一个集合用于存放商品对象
                        //条形码
                        barCode = commodity.barCode;
                        //拼音
                        cmdtySpell = commodity.cmdtySpell;
                        //商品名称
                        cmdtyName = commodity.cmdtyName;
                        id = commodity.id;
                        if (barCode.contains(et_input) || cmdtyName.contains(et_input) || cmdtySpell.contains(et_input)) {
                            cmdtyNames.add(commodity);
                        }
                    }
                }
                SearchResultAdapter adapter = new SearchResultAdapter(context, cmdtyNames);
                listview.setAdapter(adapter);
            }

            @Override
            public void afterTextChanged(Editable s) {
                //输入文字之后
            }
        });
    }

    @Override
    public void initData() {
        et_input = activity_commodity_search_ET_search.getText().toString().trim();
    }

    @Override
    public void initEvent() {
        activity_commodity_search_TV_cacel.setOnClickListener(this);
        //给listview设置条目点击事件
        listview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                //从商品集合里面拿到一个商品对象
                //跳转页面至商品页面 把点击的条目对应的商品对象通过发送广播传递过去 关闭页面
                Intent intent=new Intent("com.youxiao.ui.fragment.SalesFragment.SlidingListener.onPanelClosed");
                intent.putExtra("commodity",cmdtyNames.get(position));
                sendBroadcast(intent);
                finish();
            }
        });
    }

    /**
     * 请求服务器
     */
    public void getInfo() {
        try {

            JSONObject object = new JSONObject();  SharedPreferences sp = SpUtil.getSp();
            String distr_id = sp.getString(SpUtil.DISTR_ID, "");
            String id = sp.getString(SpUtil.ID, "");
            object.put("employeeId", id);
            object.put("distributorId", distr_id);
            JsonObjectRequest jsonRequest1 = new JsonObjectRequest(
                    Request.Method.POST, SpUtil.BASE_URL + "/tPCommodity/AppchoosecSeach", object,
                    new Response.Listener<JSONObject>() {
                        @Override
                        public void onResponse(JSONObject response) {
                            if (response != null) {
                                jsonObject = response;
                                Message message = new Message();
                                message.what = 0;
                                handler.sendMessageDelayed(message, 1000);
                            }
                        }
                    }, new Response.ErrorListener() {
                @Override
                public void onErrorResponse(VolleyError error) {
                    Log.e("error", "错误信息：" + error.toString());
                }
            });
            MyApplication.queues.add(jsonRequest1);
        } catch (Exception e) {
            Log.e("error", "联网失败");
            e.printStackTrace();
        }
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.activity_commodity_search_TV_cacel:
                finish();
                break;
        }
    }
}
