package com.youxiao.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import java.util.List;

/**
 * @author StomHong
 * @param <T> 给定的泛型
 */
public abstract class CommonAdapter<T> extends BaseAdapter{

	private Context mContext;
	private List<T> mData;
	private int mLayoutId;

	 public CommonAdapter(Context context,List<T> datas,int layoutId) {
		mContext = context;
		mData = datas;
		mLayoutId = layoutId;
	}


	@Override
	public int getCount() {
		return mData.size();
	}

	@Override
	public T getItem(int position) {
		return mData.get(position);
	}

	@Override
	public long getItemId(int position) {
		return position;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		ViewHolder holder = ViewHolder.getViewHolder(mContext, convertView, parent, mLayoutId, position);
		convert(holder,getItem(position));
		return holder.getConvertView();
	}

	@Override
	public View getDropDownView(int position, View convertView, ViewGroup parent) {
		return super.getDropDownView(position, convertView, parent);
	}

	public abstract void convert(ViewHolder holder, T t);

	public void refreshData(List<T> list){
		if (list != null){
			mData.clear();
			mData.addAll(list);
			notifyDataSetChanged();
		}

	}
}
