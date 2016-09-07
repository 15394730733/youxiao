package com.youxiao.ui.activity.work.contractsignature;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.os.Environment;
import android.view.View;
import android.view.Window;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.youxiao.R;
import com.youxiao.base.BaseActivity;
import com.youxiao.util.GetSystemTime;
import com.youxiao.widget.SignatureView;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

/**
 *
 * 经办签字
 *
 * @author StomHong
 * @since 2016-7-29
 */
public class ManagerSignatureActivity extends BaseActivity implements View.OnClickListener{

    private String mPathName;
    private LinearLayout mLinearLayout_Back;
    private SignatureView mSignatureView;
    private ImageView mImageView_SignatureClear;
    private ImageView mImageView_SignatureCommit;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_manager_signature);
        super.init();
    }

    @Override
    public void initView() {
        mLinearLayout_Back = (LinearLayout) findViewById(R.id.id_ll_manager_signature_back);
        mSignatureView = (SignatureView) findViewById(R.id.id_manager_signature);
        mImageView_SignatureClear = (ImageView) findViewById(R.id.id_iv_manager_signature_clear);
        mImageView_SignatureCommit = (ImageView) findViewById(R.id.id_iv_manager_signature_commit);
    }

    @Override
    public void initData() {

    }

    @Override
    public void initEvent() {
        mLinearLayout_Back.setOnClickListener(this);
        mImageView_SignatureClear.setOnClickListener(this);
        mImageView_SignatureCommit.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.id_ll_manager_signature_back:
                finish();
                break;
            case R.id.id_iv_manager_signature_commit:
                mSignatureView.setDrawingCacheEnabled(true);
                Bitmap image = mSignatureView.getDrawingCache();
                byte[] data = compressImage(image,100);
                savePicture(data);
                Intent returnIntent = new Intent();
                returnIntent.putExtra("pathName",mPathName);
                setResult(Activity.RESULT_OK, returnIntent);
                finish();
                break;
            case R.id.id_iv_manager_signature_clear:
                mSignatureView.clear();
            default:
                break;
        }
    }

    /**
     * 将文件以I/O流的方式写入SDCard
     *
     * @param data
     * @param path
     * @param fileName
     */
    private String writeFileToSDCard(byte[] data, String path, String fileName) {
        File file = new File(path, fileName);
        FileOutputStream fos = null;
        try {
            fos = new FileOutputStream(file);
            fos.write(data);
            fos.flush();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (fos != null) {
                try {
                    fos.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return path + File.separator + fileName;
    }

    /**
     * 对图片进行质量压缩，不会改变它的像素
     *
     * @param sBitmap 元bitmap
     * @param quality 压缩质量从0-100，100表示不压缩
     * @return bitmap Bitmap对象
     */
    private byte[] compressImage(Bitmap sBitmap, int quality) {

        ByteArrayOutputStream stream = new ByteArrayOutputStream();
        sBitmap.compress(Bitmap.CompressFormat.JPEG, quality, stream);
        return stream.toByteArray();
    }


    /**
     * 将照片文件以指定的路径保存到SDCard中
     *
     * @param data 需要保存的data数据
     */
    private void savePicture(byte[] data) {
        String p = Environment.getExternalStorageDirectory().getPath();
        File file = createDirectory(p, "YouXiao");
        String path = file.getAbsolutePath();
        String fileName = "MS"+ GetSystemTime.getTime("yyyyMMdd-HHmmss") + ".jpg";
        mPathName = writeFileToSDCard(data, path, fileName);
    }

    /**
     * 在指定的目录下创建文件夹
     */
    private File createDirectory(String path, String dirName) {
        File file = new File(path, dirName);
        if (!file.exists()) {
            file.mkdir();
        }
        return file;
    }
}
