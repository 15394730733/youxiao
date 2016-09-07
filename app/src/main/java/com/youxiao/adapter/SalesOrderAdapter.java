package com.youxiao.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.youxiao.R;

import java.util.List;

/**
 * @author StomHong
 * @since 2016-08-27
 */
public class SalesOrderAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {


    private List<String> mData;
    private Context mContext;

    public interface OnItemClickLitener {
        void onItemClick(View view, int position);

        void onItemLongClick(View view, int position);
    }

    private OnItemClickLitener mOnItemClickLitener;

    public void setOnItemClickLitener(OnItemClickLitener mOnItemClickLitener) {
        this.mOnItemClickLitener = mOnItemClickLitener;
    }


    public SalesOrderAdapter(Context context,List<String> data) {
        mData = data;
        mContext = context;
    }


    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new MyViewHolder(LayoutInflater.from(parent.getContext())
                        .inflate(R.layout.item_sale_order_bill, parent, false));
    }

    @Override
    public void onBindViewHolder(final RecyclerView.ViewHolder holder, int position) {
        if (mData.get(position).equals("未生成调拨单")) {
            ((MyViewHolder) holder).getTextView().setText(mData.get(position));
            ((MyViewHolder) holder).getTextView().setTextColor(mContext.getResources().getColor(R.color.red400));
        } else {
            ((MyViewHolder) holder).getTextView().setText(mData.get(position));
            ((MyViewHolder) holder).getTextView().setTextColor(mContext.getResources().getColor(R.color.grey500));

        }

        // 如果设置了回调，则设置点击事件
        if (mOnItemClickLitener != null) {
            holder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    int pos = holder.getLayoutPosition();
                    mOnItemClickLitener.onItemClick(holder.itemView, pos);
                }
            });

            holder.itemView.setOnLongClickListener(new View.OnLongClickListener() {
                @Override
                public boolean onLongClick(View v) {
                    int pos = holder.getLayoutPosition();
                    mOnItemClickLitener.onItemLongClick(holder.itemView, pos);
                    return false;
                }
            });
        }
    }

    @Override
    public int getItemCount() {
        return mData.size();
    }

    /**
     * 复用getItemViewType方法，根据位置返回相应的ViewType
     *
     * @param position
     * @return
     */
    @Override
    public int getItemViewType(int position) {

        return position;
    }


    class MyViewHolder extends RecyclerView.ViewHolder {

        TextView textView;

        public MyViewHolder(View itemView) {
            super(itemView);
            textView = (TextView) itemView.findViewById(R.id.generate_transferring_order);
        }

        public TextView getTextView() {
            return textView;
        }
    }

}
