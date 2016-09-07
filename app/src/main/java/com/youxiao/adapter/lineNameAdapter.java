package com.youxiao.adapter;

import java.util.List;

import com.youxiao.R;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

public class lineNameAdapter extends BaseAdapter{

	private List<String> list;
	private LayoutInflater inflater;
	
	public lineNameAdapter(Context context,List<String> list){
		this.list=list;
		inflater = LayoutInflater.from(context);
	}

	@Override
	public int getCount() {
		// TODO Auto-generated method stub
		return list.size();
	}

	@Override
	public String getItem(int position) {
		// TODO Auto-generated method stub
		return list.get(position);
	}

	@Override
	public long getItemId(int position) {
		// TODO Auto-generated method stub
		return list.get(position).hashCode();
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		// TODO Auto-generated method stub
		if(convertView==null){
			convertView = inflater.inflate(R.layout.select_line_item, null);
		}
		TextView name = (TextView) convertView.findViewById(R.id.select_line_tv);
		name.setText(list.get(position));
		return convertView;
	}
}
