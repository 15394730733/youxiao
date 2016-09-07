package com.youxiao.ui.activity.work.photomanager;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.youxiao.R;
import com.youxiao.base.BaseActivity;

import java.util.ArrayList;
import java.util.List;

/**
 * 查看照片页面
 *
 * @author StomHong
 * @since 2016-4-20
 */
public class PictureDetailActivity extends BaseActivity implements View.OnClickListener {

    private static final String TAG = PictureDetailActivity.class.getSimpleName();
    private ViewPager mViewPager;
    private LinearLayout mLinearLayout_Back;
    private List<ImageView> mImageViews = new ArrayList<>();
    private List<Bitmap> mBitmapList;
    private ImageView mImageView_Delete;
    int mRequestCode;
    private PagerAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_picture_detail);

        super.init();
    }

    @Override
    public void initView() {
        mLinearLayout_Back = (LinearLayout) findViewById(R.id.id_lay_back);
        mViewPager = (ViewPager) findViewById(R.id.id_vp_picture_detail_picture);
        mImageView_Delete = (ImageView) findViewById(R.id.id_iv_picture_detail_delete);
    }

    @Override
    public void initData() {
        mRequestCode = getIntent().getExtras().getInt("requestCode");
        initSet();
        //如果图片少于5张，则移除最后一个图片即点击添加照片的图片,如果等于5张，则需要判断最后一张是否是【添加图片】。
        if (mBitmapList.size() == 5) {
            if (mBitmapList.get(mBitmapList.size() -1) == PhotographUploadActivity.mSelectPicture) {
                mBitmapList.remove(mBitmapList.size() - 1);
            }
        }else {
            mBitmapList.remove(mBitmapList.size() - 1);
        }
        //将Bitmap转换成ImageView
        for (Bitmap bm : mBitmapList) {
            ImageView imageView = new ImageView(this);
            imageView.setScaleType(ImageView.ScaleType.FIT_XY);
            imageView.setImageBitmap(bm);
            mImageViews.add(imageView);
        }
        adapter = new MyPagerAdapter(mImageViews);
        mViewPager.setAdapter(adapter);
    }

    @Override
    public void initEvent() {
        mLinearLayout_Back.setOnClickListener(this);
        mImageView_Delete.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {

        switch (v.getId()) {

            case R.id.id_lay_back:
                Intent intent = new Intent();
                intent.putExtra("requestCode", mRequestCode);
                setResult(Activity.RESULT_FIRST_USER, intent);
                finish();
                break;

            case R.id.id_iv_picture_detail_delete:
                if (mImageViews.size() > 0) {
                    //获取当前照片的位置
                    int currentItem = mViewPager.getCurrentItem();
                    mImageViews.remove(currentItem);
                    mBitmapList.remove(currentItem);
                    adapter.notifyDataSetChanged();
                } else {
                    Toast.makeText(this, "没有可删除的图片了", Toast.LENGTH_SHORT).show();
                }
                break;

            default:
                break;
        }

    }

    class MyPagerAdapter extends PagerAdapter {

        private List<ImageView> imageViews;

        public MyPagerAdapter(List<ImageView> imageViews) {
            this.imageViews = imageViews;
        }

        @Override
        public Object instantiateItem(ViewGroup container, int position) {
            container.addView(imageViews.get(position));
            return imageViews.get(position);
        }

        @Override
        public int getItemPosition(Object object) {
            return POSITION_NONE;
        }

        @Override
        public void destroyItem(ViewGroup container, int position, Object object) {
            container.removeView((View) object);
        }

        @Override
        public boolean isViewFromObject(View view, Object object) {
            return view == object;
        }

        @Override
        public int getCount() {
            return imageViews.size();
        }

    }

    /**
     * 初始化集合数据
     */
    private void initSet() {
        switch (mRequestCode) {
            case PhotographUploadActivity.DISPLAY_PHOTO_CODE:
                mBitmapList = PhotographUploadActivity.mDisplayDatas;
                break;
            case PhotographUploadActivity.SIGNAGE_PHOTO_CODE:
                mBitmapList = PhotographUploadActivity.mSignageDatas;
                break;
            case PhotographUploadActivity.POP_PHOTO_CODE:
                mBitmapList = PhotographUploadActivity.mPopDatas;
                break;
            case PhotographUploadActivity.STANDEES_PHOTO_CODE:
                mBitmapList = PhotographUploadActivity.mStandeesDatas;
                break;
            default:
                break;
        }
    }

}
