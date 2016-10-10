package com.youxiao.ui.activity.me.personalinformation;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import com.google.gson.Gson;
import com.youxiao.R;
import com.youxiao.adapter.CommonAdapter;
import com.youxiao.adapter.ViewHolder;
import com.youxiao.base.BaseActivity;
import com.youxiao.entity.Province;

import java.io.IOException;
import java.io.InputStream;

/**
 * 省级地区
 */
public class ProvinceActivity extends BaseActivity {

    private static final String TAG = ProvinceActivity.class.getSimpleName();
    private ListView mListView_Province;
    private Province province;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_province);
        super.init();
    }

    @Override
    public void initView() {

        mListView_Province = (ListView) findViewById(R.id.lv_province);

    }


    @Override
    public void initData() {
        StringBuffer sb = new StringBuffer();
        InputStream is = null;
        try {
            is = getAssets().open("province_city.json");
            int len = -1;
            byte[] buf = new byte[1024];
            while ((len = is.read(buf)) != -1) {
                sb.append(new String(buf, 0, len, "utf-8"));
            }
            is.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        Gson gson = new Gson();
//        Type type = new TypeToken<Province>() {
//        }.getType();
        Log.d(TAG, sb.toString());
        province = gson.fromJson(sb.toString(), Province.class);
        CommonAdapter<Province.P> adapter = new CommonAdapter<Province.P>(this, province.getProvince(), android.R.layout.simple_list_item_1) {
            @Override
            public void convert(ViewHolder holder, Province.P p) {
                holder.setText(android.R.id.text1, province.getProvince().get(holder.getPosition()).getName());
            }
        };
        mListView_Province.setAdapter(adapter);
    }

    @Override
    public void initEvent() {

        mListView_Province.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent();
                intent.setClass(ProvinceActivity.this, CityActivity.class);
                intent.putExtra("p", position);
                startActivity(intent);
            }
        });
    }
}
