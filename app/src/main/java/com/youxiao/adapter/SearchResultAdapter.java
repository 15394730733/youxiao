package com.youxiao.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.youxiao.R;
import com.youxiao.model.SearchResultBean;

import java.util.List;

/**
 * 搜索结果对应的adapter
 * Created by Administrator on 2016/9/24.
 */
public class SearchResultAdapter extends BaseAdapter {
    private Context context;
    private List<SearchResultBean.CommodityBean.Commodity> cmdtyNames;

    public SearchResultAdapter(Context context, List<SearchResultBean.CommodityBean.Commodity> cmdtyNames ) {
        this.context = context;
        this.cmdtyNames = cmdtyNames;

    }

    @Override
    public int getCount() {
        return cmdtyNames.size();
    }

    @Override
    public Object getItem(int position) {
        return cmdtyNames.get(position);
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        String s = cmdtyNames.get(position).cmdtyName;
        ViewHolder holder = null;
        if (convertView == null) {
            convertView = View.inflate(context, R.layout.item_lv_search_result, null);
            holder = new ViewHolder(convertView);
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }
        holder.tv_search_result.setText(s);
        return convertView;
    }

    private class ViewHolder {
        private TextView tv_search_result;

        public ViewHolder(View view) {
            tv_search_result = (TextView) view.findViewById(R.id.tv_search_result);
        }

    }
}
