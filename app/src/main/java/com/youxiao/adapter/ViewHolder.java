package com.youxiao.adapter;

import android.app.Activity;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.drawable.Drawable;
import android.util.SparseArray;
import android.util.SparseBooleanArray;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.youxiao.R;

import java.util.ArrayList;
import java.util.List;

/**
 * 对外界公开传出去内容：1.layoutId填充的ConvertView 2. 提供设置各种组件内容的方法
 * 内部实现的逻辑: 1.得到convertView ，得到convertView的各种控件
 *
 * @author Stom
 */

public class ViewHolder {

    private Context mContext;
    private LayoutInflater mInflater;

    // 当前的item位置
    private int mPosition;

    private View mConvertView;

    // 存放View的集合
    private SparseArray<View> mViews;

    // 存放Boolean值的集合
    private static SparseBooleanArray mPos;

    // 存放被选中的checkedPos的位置的集合
    private static List<Integer> mCheckedPosition;

    private ViewClick mClick;

    private ViewHolder(Context context, int layoutId, ViewGroup parent, int position) {
        mContext = context;
        mViews = new SparseArray<View>();
        mInflater = LayoutInflater.from(context);
        mConvertView = mInflater.inflate(layoutId, parent, false);
        mPosition = position;
        mConvertView.setTag(this);
    }

    /**
     * 由于Adapter中总是从本ViewHolder内得到mConvertView; 所以不是每次都要实例化viewHolder
     */

    public static ViewHolder getViewHolder(Context context, View convertView, ViewGroup parent, int layoutId,
                                           int position) {
        if (convertView == null) {
            return new ViewHolder(context, layoutId, parent, position);
        } else {
            ViewHolder holder = (ViewHolder) convertView.getTag();
            // 更新ConvertView中的position
            holder.mPosition = position;

            return holder;
        }
    }

    public View getConvertView() {
        return mConvertView;
    }

    /**
     * 如果view容器中没有这个View，则从mConvertView中取
     *
     * @param viewId
     * @return
     */
    @SuppressWarnings("unchecked")
    public <E extends View> E getView(int viewId) {
        E v = (E) mViews.get(viewId);
        if (v == null) {
            v = (E) mConvertView.findViewById(viewId);
        }
        return v;

    }

    public int getPosition() {
        return mPosition;
    }

    /**
     * 设置文本框的值
     *
     * @param viewId
     * @param text
     * @return
     */
    public ViewHolder setText(int viewId, String text) {
        TextView view = getView(viewId);
        view.setText(text);
        return this;
    }


    public ViewHolder setTextViewPaintFlags(int viewId, int paintFlag) {
        TextView view = getView(viewId);
        view.getPaint().setFlags(paintFlag);
        return this;

    }

    /**
     * 设置文本颜色值
     *
     * @param viewId
     * @param color
     * @return
     */
    public ViewHolder setTextColor(int viewId, int color) {
        TextView view = getView(viewId);
        view.setTextColor(color);
        return this;
    }


    /**
     * 从指定Id资源的地方加载图片
     *
     * @param viewId
     * @param imageId
     * @return
     */
    public ViewHolder setImageFromResource(int viewId, int imageId) {
        ImageView view = getView(viewId);
        view.setImageResource(imageId);
        return this;
    }

    /**
     * 加载指定的Bitmap
     *
     * @param imageViewId
     * @param bitmap
     * @return
     */
    public ViewHolder setImageFromBitmap(int imageViewId, Bitmap bitmap) {
        if (getView(imageViewId) instanceof ImageView) {
            ImageView imageView = getView(imageViewId);
            imageView.setImageBitmap(bitmap);
        } else {
            throw new IllegalStateException("The view must be a ImageView!");
        }
        return this;
    }

    public ViewHolder setBackground(int viewId, Drawable drawable) {
        View view = getView(viewId);
        view.setBackgroundDrawable(drawable);
        return this;
    }

    public ViewHolder setBackground(int viewId, int color) {
        View view = getView(viewId);
        view.setBackgroundColor(color);
        return this;
    }


    /**
     * 从网络上加载图片
     *
     * @param url
     * @return
     */
    public ViewHolder setImageFromNet(Activity activity, int viewId, String url) {
        ImageView view = getView(viewId);
//        Glide.with(activity)
//                .load(url)
//                .centerCrop()
//                .error(R.drawable.icon_empty)
//                .placeholder(R.drawable.icon_stub)
//                .diskCacheStrategy(DiskCacheStrategy.ALL)
//                .into(view);
        return this;
    }


    /**
     * 设置checkbox
     *
     * @param viewId
     */
    public ViewHolder setCheckBox(int viewId) {
        if (mPos == null) {
            // 如果容器是第一次使用则实例化一个对象
            mPos = new SparseBooleanArray();
            mCheckedPosition = new ArrayList<Integer>();
        }
        final CheckBox cb = getView(viewId);
        // 以指定的position作为Key取出容器中的boolean的值，如果没有这个值则默认为false;
        cb.setChecked(mPos.get(mPosition));
        cb.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                // 如果checkbox是被选中的时候存入容器
                if (cb.isChecked()) {
                    cb.setButtonDrawable(R.drawable.commit);

                    mPos.put(mPosition, cb.isChecked());
                    mCheckedPosition.add(mPosition);
                } else {
                    // 如果checkbox没有被选中时则从容器中移除去
                    cb.setButtonDrawable(R.drawable.unselected);
                    mPos.delete(mPosition);
                    mCheckedPosition.remove((Integer) mPosition);
                }
            }
        });
        return this;
    }


//    /**
//     * 设置checkbox
//     *
//     * @param viewId
//     */
//    public ViewHolder setCheckImage(int viewId) {
//        if (mPos == null) {
//            // 如果容器是第一次使用则实例化一个对象
//            mPos = new SparseBooleanArray();
//            mCheckedPosition = new ArrayList<Integer>();
//        }
//        final ImageView iv = getView(viewId);
//        // 以指定的position作为Key取出容器中的boolean的值，如果没有这个值则默认为false;
//        iv.setImageResource(R.drawable.commit);
////        iv.setChecked(mPos.get(mPosition));
//        iv.setOnClickListener(new OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                // 如果checkbox是被选中的时候存入容器
//                if (iv.getDrawable() == mContext.getResources().getDrawable(R.drawable.commit)) {
//                    mPos.put(mPosition, cb.isChecked());
//                    mCheckedPosition.add(mPosition);
//                } else {
//                    // 如果checkbox没有被选中时则从容器中移除去
//                    mPos.delete(mPosition);
//                    mCheckedPosition.remove((Integer) mPosition);
//                }
//            }
//        });
//        return this;
//    }

    /**
     * 返回被选中的checkBox的position集合
     *
     * @return
     */
    public static List<Integer> getCheckedPosition() {
        return mCheckedPosition;
    }

    /**
     * 清空checkBox的选中集合
     */
    public static void clearPos() {
        mPos.clear();
    }

    /**
     * 设置进度条
     */
    public ViewHolder setProgress(int viewId, int prg) {
        ProgressBar pb = getView(viewId);
        pb.setProgress(prg);
        return this;
    }


    /**
     * 设置Buttons是否显示
     *
     * @param viewId
     * @param visible
     */
    public ViewHolder setButtonVisible(int viewId, boolean visible) {
        Button button = getView(viewId);
        if (visible) {
            button.setVisibility(View.VISIBLE);
        } else {
            button.setVisibility(View.INVISIBLE);
        }
        return this;
    }


    /**
     * 设置View是否显示
     *
     * @param viewId
     * @param visible
     */
    public ViewHolder setViewVisibility(int viewId, Boolean visible) {
        View view = getView(viewId);
        if (visible) {
            view.setVisibility(View.VISIBLE);
        } else {
            view.setVisibility(View.INVISIBLE);
        }
        return this;
    }

    /**
     * item布局的控件点击
     *
     * @param click
     * @param viewId
     */
    public void setViewOnClick(ViewClick click, int... viewId) {
        if (mClick == null) {
            mClick = click;
        }
        for (int vId : viewId) {
            View view = getView(vId);
            view.setOnClickListener(new OnViewItemClickListener());
        }
    }

    /**
     * 实现监听点击事件
     */
    class OnViewItemClickListener implements OnClickListener {

        @Override
        public void onClick(View v) {
            mClick.onClick(v, mPosition);
        }
    }

    /**
     * 监听item布局子控件点击事件
     */
    public interface ViewClick {
        /**
         * 处理点击事件
         *
         * @param v        返回所点击的View
         * @param position 返回所点击的View所在Item的位置
         */
        void onClick(View v, int position);
    }


}
