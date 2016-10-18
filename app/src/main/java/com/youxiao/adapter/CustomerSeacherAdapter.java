package com.youxiao.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.youxiao.R;
import com.youxiao.model.CustomerManagerBean;

import java.util.List;

/**
 * Created by Administrator on 2016/10/17.
 */
public class CustomerSeacherAdapter extends BaseAdapter {
    private List<CustomerManagerBean.Customer> customer1;
    private Context context;

    public CustomerSeacherAdapter(Context context, List<CustomerManagerBean.Customer> customer) {
        this.context = context;
        this.customer1 = customer;
    }

    @Override
    public int getCount() {
        return customer1.size();
    }

    @Override
    public Object getItem(int i) {
        return customer1.get(i);
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        String names = customer1.get(position).custName;
        ViewHolder viewHolder = null;
        if (viewHolder == null) {
            convertView = View.inflate(context, R.layout.item_lv_search_result, null);
            viewHolder = new ViewHolder(convertView);
            convertView.setTag(viewHolder);

        } else {
            viewHolder = (ViewHolder) convertView.getTag();
            viewHolder.tv_search_result.setText(names);
        }
        return convertView;
    }

    public class ViewHolder {
        private TextView tv_search_result;

        public ViewHolder(View view) {

        }
    }
}
