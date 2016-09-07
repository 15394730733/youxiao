package com.youxiao.ui.activity.work.photomanager;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Paint;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AlertDialog;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.Window;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.youxiao.R;
import com.youxiao.adapter.CommonAdapter;
import com.youxiao.adapter.ViewHolder;
import com.youxiao.base.BaseActivity;
import com.youxiao.ui.activity.work.attendancesignature.FineAdjustmentActivity;
import com.youxiao.ui.activity.work.customermanager.CustomerManageActivity;

import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

/**
 * 拍照上传
 *
 * @author StomHong
 * @since 2016-3-23
 */
public class PhotographUploadActivity extends BaseActivity implements View.OnClickListener {


    private static final String TAG = PhotographUploadActivity.class.getSimpleName();
    private LinearLayout mLinearLayout_Back;
    private RelativeLayout mRelativeLayout_ClientName;

    private TextView mTextView_LocationAddress;
    private TextView mTextView_Commit;
    private TextView mTextView_Relocate;
    private TextView mTextView_CustomerName;

    private ImageView mImageView_ClearAddress;

    private EditText mEditText_DisplayDescribe;
    private EditText mEditText_StandeesDescribe;
    private EditText mEditText_POPDescribe;
    private EditText mEditText_SignageDescribe;

    /**
     * 拍照上传请求码
     **/
    public static final int PHOTOGRAPH_UPLOAD_REQUEST = 100;
    public static final int DISPLAY_PHOTO_CODE = 301;
    public static final int STANDEES_PHOTO_CODE = 302;
    public static final int SIGNAGE_PHOTO_CODE = 303;
    public static final int POP_PHOTO_CODE = 304;
    private static final String IMAGE_TYPE = "image/*";

    public static ArrayList<Bitmap> mDisplayDatas = new ArrayList<>();
    public static ArrayList<Bitmap> mStandeesDatas = new ArrayList<>();
    public static ArrayList<Bitmap> mSignageDatas = new ArrayList<>();
    public static ArrayList<Bitmap> mPopDatas = new ArrayList<>();

    private List<String> mDisplayAddr = new ArrayList<>();
    private List<String> mStandeesAddr = new ArrayList<>();
    private List<String> mSignageAddr = new ArrayList<>();
    private List<String> mPopAddr = new ArrayList<>();

    private CommonAdapter<Bitmap> mDisplayAdapter;
    private CommonAdapter<Bitmap> mStandeesAdapter;
    private CommonAdapter<Bitmap> mSignageAdapter;
    private CommonAdapter<Bitmap> mPopAdapter;

    private GridView mGridView_Display;
    private GridView mGridView_Standees;
    private GridView mGridView_Signage;
    private GridView mGridView_Pop;
    public static Bitmap mSelectPicture;
    private Context context;
    private LocationThread mLocationThread;

    private Handler mHandler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
//            if (msg.what == 0 && GLOBAL.LOCATION != null) {
//                mTextView_LocationAddress.setPaintFlags(Paint.UNDERLINE_TEXT_FLAG);
//                mTextView_LocationAddress.setText(GLOBAL.LOCATION.getAddrStr());
//                mImageView_ClearAddress.setVisibility(View.VISIBLE);
//            }
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_photograph_upload);
        context = this;

        super.init();

    }

    @Override
    public void initView() {

        mLinearLayout_Back = (LinearLayout) findViewById(R.id.id_lay_photograph_upload_back);
        mRelativeLayout_ClientName = (RelativeLayout) findViewById(R.id.id_lay_photograph_upload_client_name);

        mImageView_ClearAddress = (ImageView) findViewById(R.id.id_iv_photograph_upload_clear_address);

        mTextView_LocationAddress = (TextView) findViewById(R.id.id_tv_photograph_upload_location_address);
        mTextView_Commit = (TextView) findViewById(R.id.id_tv_photograph_upload_commit);
        mTextView_Relocate = (TextView) findViewById(R.id.id_tv_photograph_upload_refresh_location);
        mTextView_CustomerName = (TextView) findViewById(R.id.id_tv_photograph_upload_client_name);

        mEditText_DisplayDescribe = (EditText) findViewById(R.id.id_et_photograph_upload_display_describe);
        mEditText_POPDescribe = (EditText) findViewById(R.id.id_et_photograph_upload_pop_describe);
        mEditText_SignageDescribe = (EditText) findViewById(R.id.id_et_photograph_upload_signage_describe);
        mEditText_StandeesDescribe = (EditText) findViewById(R.id.id_et_photograph_upload_standees_describe);

        mGridView_Display = (GridView) findViewById(R.id.id_gv_photograph_upload_display);
        mGridView_Standees = (GridView) findViewById(R.id.id_gv_photograph_upload_standees);
        mGridView_Signage = (GridView) findViewById(R.id.id_gv_photograph_upload_signage);
        mGridView_Pop = (GridView) findViewById(R.id.id_gv_photograph_upload_pop);

    }


    @Override
    public void initData() {
        handleLocation();

        mSelectPicture = BitmapFactory.decodeResource(getResources(), R.drawable.photo);
        //陈列照片
        if (mDisplayDatas.size() == 0) mDisplayDatas.add(mSelectPicture);
        mDisplayAdapter = initAdapter(mDisplayDatas, mGridView_Display);
        //店招照片
        if (mSignageDatas.size() == 0) mSignageDatas.add(mSelectPicture);
        mSignageAdapter = initAdapter(mSignageDatas, mGridView_Signage);
        //堆头照片
        if (mStandeesDatas.size() == 0) mStandeesDatas.add(mSelectPicture);
        mStandeesAdapter = initAdapter(mStandeesDatas, mGridView_Standees);
        //Pop照片
        if (mPopDatas.size() == 0) mPopDatas.add(mSelectPicture);
        mPopAdapter = initAdapter(mPopDatas, mGridView_Pop);
    }

    @Override
    public void initEvent() {

        mLinearLayout_Back.setOnClickListener(this);
        mRelativeLayout_ClientName.setOnClickListener(this);

        mImageView_ClearAddress.setOnClickListener(this);
        mTextView_LocationAddress.setOnClickListener(this);
        mTextView_Commit.setOnClickListener(this);
        mTextView_Relocate.setOnClickListener(this);

        mGridView_Display.setOnItemClickListener(new DisplayItemClickListener());
        mGridView_Pop.setOnItemClickListener(new PopItemClickListener());
        mGridView_Signage.setOnItemClickListener(new SignageItemClickListener());
        mGridView_Standees.setOnItemClickListener(new StandeesItemClickListener());
    }

    @Override
    protected void onNewIntent(Intent intent) {
        super.onNewIntent(intent);
        setIntent(intent);
        //从自定义相机中返回的数据
        if (getIntent() != null) {
            String pathName = getIntent().getStringExtra("pathName");
            int requestCode = getIntent().getIntExtra("requestCode", 0);
            mDisplayAddr.add(pathName);
            Bitmap bitmap = decodeSampledBitmapFromFile(pathName, 200, 200);
            doRequestResult(requestCode, bitmap);
        }
    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        //处理从PictureDetail返回的数据
        if (resultCode == Activity.RESULT_FIRST_USER) {
            switch (requestCode) {
                case DISPLAY_PHOTO_CODE:
                    addImageToLast(mDisplayDatas, mSelectPicture, mDisplayAdapter);
                    break;
                case SIGNAGE_PHOTO_CODE:
                    addImageToLast(mSignageDatas, mSelectPicture, mSignageAdapter);
                    break;
                case STANDEES_PHOTO_CODE:
                    addImageToLast(mStandeesDatas, mSelectPicture, mStandeesAdapter);
                    break;
                case POP_PHOTO_CODE:
                    addImageToLast(mPopDatas, mSelectPicture, mPopAdapter);
                    break;
                default:
                    break;
            }

        }
        if (resultCode == RESULT_OK) {
            if (requestCode == PHOTOGRAPH_UPLOAD_REQUEST) {
                String customerName = data.getStringExtra("customerName");
                mTextView_CustomerName.setText(customerName);
            }
            //处理从相册中返回的数据
            if (data != null && data.getData() != null) {
                Uri uri = data.getData();
                Bitmap bitmap = decodeSampledBitmapFromUri(uri, 200, 200);
                doRequestResult(requestCode, bitmap);
            }
        }
    }

    @Override
    public void onClick(View v) {
        Intent intent = new Intent();
        switch (v.getId()) {
            case R.id.id_lay_photograph_upload_back:
                //清除集合中的数据
                mDisplayDatas.clear();
                mStandeesDatas.clear();
                mSignageDatas.clear();
                mPopDatas.clear();
                finish();
                break;
            case R.id.id_iv_photograph_upload_clear_address:
                mTextView_LocationAddress.setPaintFlags(Paint.ANTI_ALIAS_FLAG);
                mTextView_LocationAddress.setText("点击获取位置信息");
                mImageView_ClearAddress.setVisibility(View.GONE);
                break;
            case R.id.id_tv_photograph_upload_location_address:
                if (mTextView_LocationAddress.getText().toString().equals("点击获取位置信息")) {
                    mTextView_LocationAddress.setPaintFlags(Paint.ANTI_ALIAS_FLAG);
                    mTextView_LocationAddress.setText("正在定位...");
                    mImageView_ClearAddress.setVisibility(View.GONE);
                    handleLocation();
                } else if (mTextView_LocationAddress.getText().toString().equals("暂无位置信息")) {

                } else {
                    intent.setClass(this, FineAdjustmentActivity.class);
                    startActivityForResult(intent, 0);
                }
                break;
            case R.id.id_tv_photograph_upload_commit:
                String displayStr = mEditText_DisplayDescribe.getText().toString();
                String standeesStr = mEditText_StandeesDescribe.getText().toString();
                String signageStr = mEditText_SignageDescribe.getText().toString();
                String popStr = mEditText_POPDescribe.getText().toString();
                break;
            case R.id.id_tv_photograph_upload_refresh_location:
                mTextView_LocationAddress.setPaintFlags(Paint.ANTI_ALIAS_FLAG);
                mTextView_LocationAddress.setText("正在定位...");
                mImageView_ClearAddress.setVisibility(View.GONE);
                handleLocation();
                break;
            case R.id.id_lay_photograph_upload_client_name:
                intent = new Intent();
                intent.setClass(this, CustomerManageActivity.class);
                intent.putExtra("requestCode", PHOTOGRAPH_UPLOAD_REQUEST);
                startActivityForResult(intent, PHOTOGRAPH_UPLOAD_REQUEST);
                break;
            default:
                break;
        }


    }

    class StandeesItemClickListener implements AdapterView.OnItemClickListener {

        @Override
        public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

        }
    }

    class PopItemClickListener implements AdapterView.OnItemClickListener {

        @Override
        public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

        }
    }

    class DisplayItemClickListener implements AdapterView.OnItemClickListener {

        @Override
        public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

        }
    }

    class SignageItemClickListener implements AdapterView.OnItemClickListener {

        @Override
        public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

        }
    }

    /**
     * 显示dialog并处理相关业务逻辑
     */
    private void pictureDialog(final Context sContext, final int requestCode) {
        final Intent intent = new Intent();

        View view = LayoutInflater.from(sContext).inflate(R.layout.item_for_photo_dialog, null);
        TextView cancel = (TextView) view.findViewById(R.id.id_tv_item_for_photo_dialog_cancel);
        TextView takePhoto = (TextView) view.findViewById(R.id.id_tv_item_for_photo_dialog_take_photo);
        TextView openAlbum = (TextView) view.findViewById(R.id.id_tv_item_for_photo_dialog_open_photo);

        final AlertDialog dialog = new AlertDialog.Builder(sContext, R.style.dialog1).create();
        dialog.setCancelable(false);
        dialog.getWindow().setGravity(Gravity.BOTTOM);
        dialog.show();
        dialog.setContentView(view);

        //处理点击 【取消】 按钮事件
        cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
            }
        });
        //处理点击 【拍照】 按钮事件
        takePhoto.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //打开自定义照相机
                intent.setClass(sContext, TakePictureActivity.class);
                intent.putExtra("requestCode", requestCode);
                startActivity(intent);
                dialog.dismiss();

            }

        });
        //处理点击 【从相册选择】 按钮事件
        openAlbum.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //打开相册
                intent.addCategory(Intent.CATEGORY_OPENABLE);
                intent.setType(IMAGE_TYPE);
                //根据版本号不同使用不同的Action
                if (Build.VERSION.SDK_INT < 19) {
                    intent.setAction(Intent.ACTION_GET_CONTENT);
                } else {
                    intent.setAction(Intent.ACTION_OPEN_DOCUMENT);
                }
                startActivityForResult(intent, requestCode);
                dialog.dismiss();
            }
        });
    }

    /**
     * 计算取样率
     *
     * @param options
     * @param reqWidth
     * @param reqHeight
     * @return
     */
    public static int calculateInSampleSize(BitmapFactory.Options options, int reqWidth, int reqHeight) {
        // Raw height and width of image
        final int height = options.outHeight;
        final int width = options.outWidth;
        int inSampleSize = 1;

        if (height > reqHeight || width > reqWidth) {

            final int halfHeight = height / 2;
            final int halfWidth = width / 2;

            // Calculate the largest inSampleSize value that is a power of 2 and keeps both
            // height and width larger than the requested height and width.
            while ((halfHeight / inSampleSize) > reqHeight && (halfWidth / inSampleSize) > reqWidth) {
                inSampleSize *= 2;
            }
        }

        return inSampleSize;
    }

    /**
     * 从文件路径中将图片解析成bitmap并压缩
     *
     * @param pathName
     * @param reqWidth
     * @param reqHeight
     * @return
     */
    public static Bitmap decodeSampledBitmapFromFile(String pathName, int reqWidth, int reqHeight) {

        // First decode with inJustDecodeBounds=true to check dimensions
        final BitmapFactory.Options options = new BitmapFactory.Options();
        options.inJustDecodeBounds = true;
        BitmapFactory.decodeFile(pathName, options);

        // Calculate inSampleSize
        options.inSampleSize = calculateInSampleSize(options, reqWidth, reqHeight);

        // Decode bitmap with inSampleSize set
        options.inJustDecodeBounds = false;
        return BitmapFactory.decodeFile(pathName, options);
    }

    /**
     * 从输入流中将图片解析成bitmap
     *
     * @param uri
     * @param reqWidth
     * @param reqHeight
     * @return
     */
    public Bitmap decodeSampledBitmapFromUri(Uri uri, int reqWidth, int reqHeight) {
        InputStream is = null;
        // First decode with inJustDecodeBounds=true to check dimensions
        final BitmapFactory.Options options = new BitmapFactory.Options();
        options.inJustDecodeBounds = true;

        try {
            is = getContentResolver().openInputStream(uri);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        BitmapFactory.decodeStream(is, null, options);

        // Calculate inSampleSize
        options.inSampleSize = calculateInSampleSize(options, reqWidth, reqHeight);

        // Decode bitmap with inSampleSize set
        options.inJustDecodeBounds = false;

        try {
            is = getContentResolver().openInputStream(uri);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        return BitmapFactory.decodeStream(is, null, options);
    }

    /**
     * 将从相机预览页面返回bitmap数据添加到集合中，添加之后，如果集合中的数量达到6，则移除最后的数据。
     *
     * @param bitmaps
     * @param bitmap
     * @param adapter
     */
    private void addOrRemoveImage(ArrayList<Bitmap> bitmaps, Bitmap bitmap, CommonAdapter<Bitmap> adapter) {
        bitmaps.add(0, bitmap);
        removeImageFromLast(bitmaps);
        adapter.notifyDataSetChanged();
    }

    /**
     * 如果集合中图片的数量达到6张，则将最后一张移除。
     *
     * @param imageViews
     */
    private void removeImageFromLast(List<Bitmap> imageViews) {
        int size;
        if ((size = imageViews.size()) == 6) {
            imageViews.remove(size - 1);
        }
    }

    /**
     * 添加一张图片到集合中
     *
     * @param bitmaps
     * @param selectPicture
     * @param adapter
     */
    private void addImageToLast(List<Bitmap> bitmaps, Bitmap selectPicture, CommonAdapter<Bitmap> adapter) {
        if (bitmaps.size() < 5) {
            bitmaps.add(bitmaps.size(), selectPicture);
        }
        adapter.notifyDataSetChanged();
    }

    /**
     * 处理【请求照片】返回的数据
     *
     * @param requestCode
     * @param bitmap
     */
    private void doRequestResult(int requestCode, Bitmap bitmap) {
        switch (requestCode) {
            case DISPLAY_PHOTO_CODE:
                addOrRemoveImage(mDisplayDatas, bitmap, mDisplayAdapter);
                break;
            case SIGNAGE_PHOTO_CODE:
                addOrRemoveImage(mSignageDatas, bitmap, mSignageAdapter);
                break;
            case STANDEES_PHOTO_CODE:
                addOrRemoveImage(mStandeesDatas, bitmap, mStandeesAdapter);
                break;
            case POP_PHOTO_CODE:
                addOrRemoveImage(mPopDatas, bitmap, mPopAdapter);
                break;
            default:
                break;
        }
    }

    /**
     * 初始化适配器
     *
     * @param datas
     * @param gridView
     */
    private CommonAdapter<Bitmap> initAdapter(ArrayList<Bitmap> datas, GridView gridView) {
        CommonAdapter<Bitmap> adapter = new CommonAdapter<Bitmap>(this, datas, R.layout.item_for_photograph_upload) {
            @Override
            public void convert(ViewHolder holder, Bitmap bitmap) {
                holder.setImageFromBitmap(R.id.id_iv_photograph_upload_photo, bitmap);
            }
        };
        gridView.setAdapter(adapter);
        return adapter;
    }


    /**
     * 启动获取位置信息的线程
     */
    private void handleLocation() {
        if (mLocationThread == null) {
            mLocationThread = new LocationThread();
            mLocationThread.start();
        }

    }

    /**
     * 处理定位的线程
     */
    private class LocationThread extends Thread {

        @Override
        public void run() {
            super.run();
            for (; ; ) {
//                if (GLOBAL.LOCATION != null) {
//                    try {
//                        Thread.sleep(500);
//                    } catch (InterruptedException e) {
//                        e.printStackTrace();
//                    }
//                    mHandler.sendEmptyMessage(0);
//                    break;
//                }
            }
        }
    }
}
