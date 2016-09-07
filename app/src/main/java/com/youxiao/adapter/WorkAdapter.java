package com.youxiao.adapter;

import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.youxiao.R;

import java.util.List;

/**
 * @author StomHong
 * @since 2016-08-24
 */
public class WorkAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private List<Object> mData;
    private List<String> mNames;
    //先定义两个ItemViewType，0代表头，1代表表格中间的部分
    private static final int ITEM_VIEW_TYPE_HEADER = 0;
    private static final int ITEM_VIEW_TYPE_ITEM = 1;

    public interface OnItemClickLitener {
        void onItemClick(View view, int position);

        void onItemLongClick(View view, int position);
    }

    private OnItemClickLitener mOnItemClickLitener;

    public void setOnItemClickLitener(OnItemClickLitener mOnItemClickLitener) {
        this.mOnItemClickLitener = mOnItemClickLitener;
    }

    /**
     * 判断当前position是否处于第一个
     *
     * @param position
     * @return
     */
    public boolean isHeader(int position) {
        switch (position) {
            case 0:
            case 7:
            case 15:
            case 22:
            case 31:
                return true;
        }
        return false;
    }

    public WorkAdapter(List<Object> data, List<String> names) {
        mData = data;
        mNames = names;
    }


    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return isHeader(viewType)
                ? new HeaderViewHolder(LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_header, parent, false))
                : new BodyViewHolder(LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_body, parent, false));
    }

    @Override
    public void onBindViewHolder(final RecyclerView.ViewHolder holder, int position) {
        switch (position) {
            case 0:
            case 7:
            case 15:
            case 22:
            case 31:
                ((HeaderViewHolder) holder).getTextView().setText((String) mData.get(position));
                break;
            default:
                ((BodyViewHolder) holder).getTextView().setText(mNames.get(position));
                ((BodyViewHolder) holder).getImageView().setImageResource((int) mData.get(position));
                break;
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
        Log.d("TAG", position + "position.............getItemViewType");
        //如果是0，就是头，否则则是其他的item
        return isHeader(position) ? ITEM_VIEW_TYPE_HEADER : ITEM_VIEW_TYPE_ITEM;
    }


    class HeaderViewHolder extends RecyclerView.ViewHolder {

        TextView textView;

        public HeaderViewHolder(View itemView) {
            super(itemView);
            textView = (TextView) itemView.findViewById(R.id.text);
        }

        public TextView getTextView() {
            return textView;
        }
    }

    class BodyViewHolder extends RecyclerView.ViewHolder {

        TextView textView;
        ImageView imageView;

        public BodyViewHolder(View itemView) {
            super(itemView);
            textView = (TextView) itemView.findViewById(R.id.text);
            imageView = (ImageView) itemView.findViewById(R.id.image);
        }

        public TextView getTextView() {
            return textView;
        }

        public ImageView getImageView() {
            return imageView;
        }
    }


}
