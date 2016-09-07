package com.youxiao.ui.fragment;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffXfermode;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;

import com.youxiao.R;
import com.youxiao.ui.activity.sales.commodityselector.CommoditySearchActivity;

/**
 * 通讯录
 * <p/>
 * Created by 张小布 on 2016/4/22.
 */
public class AddressListFragment extends Fragment implements View.OnClickListener {

    private RelativeLayout mRelativeLayout_CommoditySearch;
    private RelativeLayout mRelativeLayout_Board;
    private RelativeLayout gmoffice;

    private ImageView mImageView_Icon;
    private RelativeLayout mRelativeLayout_Linkman_1;
    private RelativeLayout mRelativeLayout_Linkman_2;
    private RelativeLayout mRelativeLayout_Linkman_3;
    private RelativeLayout mRelativeLayout_Linkman_4;
    private RelativeLayout mRelativeLayout_Linkman_5;
    private RelativeLayout mRelativeLayout_Linkman_6;


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.address_list_fragment, container, false);
        initView(view);
        initData();
        initEvent();
        return view;
    }

    private void initData() {
        Bitmap bitmap = BitmapFactory.decodeResource(getResources(), R.mipmap.logo);
        bitmap = createCircleView(bitmap, 120);
        mImageView_Icon.setImageBitmap(bitmap);
    }


    private void initView(View view) {
        mImageView_Icon = (ImageView) view.findViewById(R.id.id_iv_icon);
        mRelativeLayout_CommoditySearch = (RelativeLayout) view.findViewById(R.id.address_list_fragment_search);
        mRelativeLayout_Board = (RelativeLayout) view.findViewById(R.id.address_list_fragment_board);
        gmoffice = (RelativeLayout) view.findViewById(R.id.address_list_fragment_GMoffice);

        mRelativeLayout_Linkman_1 = (RelativeLayout) view.findViewById(R.id.id_rl_linkman_1);
        mRelativeLayout_Linkman_2 = (RelativeLayout) view.findViewById(R.id.id_rl_linkman_2);
        mRelativeLayout_Linkman_3 = (RelativeLayout) view.findViewById(R.id.id_rl_linkman_3);
        mRelativeLayout_Linkman_4 = (RelativeLayout) view.findViewById(R.id.id_rl_linkman_4);
        mRelativeLayout_Linkman_5 = (RelativeLayout) view.findViewById(R.id.id_rl_linkman_5);
        mRelativeLayout_Linkman_6 = (RelativeLayout) view.findViewById(R.id.id_rl_linkman_6);


    }

    private void initEvent() {
        mRelativeLayout_CommoditySearch.setOnClickListener(this);
        mRelativeLayout_Board.setOnClickListener(this);
        gmoffice.setOnClickListener(this);

        mRelativeLayout_Linkman_1.setOnClickListener(this);
        mRelativeLayout_Linkman_2.setOnClickListener(this);
        mRelativeLayout_Linkman_3.setOnClickListener(this);
        mRelativeLayout_Linkman_4.setOnClickListener(this);
        mRelativeLayout_Linkman_5.setOnClickListener(this);
        mRelativeLayout_Linkman_6.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.address_list_fragment_search:
                startActivity(new Intent(getActivity(), CommoditySearchActivity.class));
                break;
            case R.id.address_list_fragment_board:
                break;
            case R.id.address_list_fragment_GMoffice:
                break;
        }
    }

    /**
     * 根据给定的图片画圆形图片
     *
     * @param source 源图片bitmap
     * @param min    最小半径
     * @return
     */
    private Bitmap createCircleView(Bitmap source, int min) {
        Paint paint = new Paint();
        paint.setAntiAlias(true);
        Bitmap target = Bitmap.createBitmap(min, min, Bitmap.Config.ARGB_8888);
        Canvas canvas = new Canvas(target);
        canvas.drawCircle(min / 2, min / 2, min / 2, paint);
        paint.setXfermode(new PorterDuffXfermode(PorterDuff.Mode.SRC_IN));
        canvas.drawBitmap(source, -10, -10, paint);
        return target;
    }

}
