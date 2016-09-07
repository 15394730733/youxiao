package com.youxiao.ui.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.youxiao.R;
import com.youxiao.ui.activity.me.systemsetting.CommoditySelectorActivity;

import java.util.Date;
import java.util.List;

/**
 * 商品选择器
 *
 * Created by 张小布 on 2016/03/10.
 */
public class FragmentTxtLeft extends Fragment implements AdapterView.OnItemClickListener {
    private static String TAG = "FragmentTxtLeft";
    private ListView listView;
    private ArrayAdapter<String> adapter;
    private List<String> listKIND_ID;
    private List<String> listKIND_NAME;
//    private List<String> listKIND_ID = new ArrayList<>();
//    private List<String> listKIND_NAME = new ArrayList<>();
    private String levelNO;
    private CommoditySelectorActivity mActivity;
    private Date refreshTime = new Date();  //刷屏时间

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, final ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_left, null);
        initView(view);
        initData();
        initEvent();
        return view;
    }

    private void initEvent() {

        listView.setOnItemClickListener(this);
        listView.setChoiceMode(ListView.CHOICE_MODE_SINGLE);
    }

    private void initData() {

        adapter = new ArrayAdapter<String>(getActivity(), R.layout.item_one_text, listKIND_NAME);
        listView.setAdapter(adapter);
    }

    private void initView(View view) {
        listView = (ListView) view.findViewById(R.id.listView);
    }







    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

    }



}
