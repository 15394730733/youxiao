package com.youxiao.ui.activity.work.contractsignature;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Paint;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AlertDialog;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.ScrollView;
import android.widget.TextView;

import com.youxiao.R;
import com.youxiao.base.BaseActivity;
import com.youxiao.ui.activity.work.attendancesignature.FineAdjustmentActivity;
import com.youxiao.ui.activity.work.customermanager.CustomerManageActivity;
import com.youxiao.ui.activity.work.customermanager.SelectDisplayWayActivity;
import com.youxiao.util.GetSystemTime;
import com.youxiao.util.HideInputMethod;
import com.youxiao.widget.Util;
import com.youxiao.widget.WheelView;

/**
 * 合同签订
 *
 * @author StomHong
 * @since 2016-7-27
 */
public class ContractSignatureActivity extends BaseActivity implements View.OnClickListener, View.OnTouchListener{


    public static final int CONTRACT_SIGNATURE_REQUEST = 104;
    public static final int CONTRACT_SIGNATURE_CUSTOMER_REQUEST = 1;
    public static final int CONTRACT_SIGNATURE_MANAGER_REQUEST = 2;
    private LinearLayout mLinearLayout_Back;
    private LinearLayout mLinearLayout_NumberZero;
    private LinearLayout mLinearLayout_NumberOne;
    private LinearLayout mLinearLayout_NumberTwo;
    private LinearLayout mLinearLayout_NumberThree;
    private LinearLayout mLinearLayout_NumberFour;
    private LinearLayout mLinearLayout_NumberFive;
    private LinearLayout mLinearLayout_NumberSix;
    private LinearLayout mLinearLayout_NumberSeven;
    private LinearLayout mLinearLayout_NumberEight;
    private LinearLayout mLinearLayout_NumberNine;
    private LinearLayout mLinearLayout_delete;
    private LinearLayout mLinearLayout_Keyboard;

    private TextView mTextView_RefreshLocation;
    private TextView mTextView_CustomerName;
    private TextView mTextView_LocationAddress;
    private TextView mTextView_SignatureTime;
    private TextView mTextView_ChargeIssueTime;
    private TextView mTextView_SignatureStartTime;
    private TextView mTextView_SignatureEndTime;
    private TextView mTextView_DispalyWay;
    private TextView mTextView_ChargeType;


    private RelativeLayout mRelativeLayout_ChargeType;
    private RelativeLayout mRelativeLayout_DisplayWay;
    private RelativeLayout mRelativeLayout_CustomerName;
    private FrameLayout mFrameLayout_CustomerSignature;
    private FrameLayout mFrameLayout_ManagerSignature;

    private EditText mEditText_AgreementNo;
    private EditText mEditText_Address;
    private EditText mEditText_Charge;
    private EditText mEditText_Quantity;
    private EditText mEditText_SalesVolume;

    private ImageView mImageView_AddressClear;
    private ImageView mImageView_AgreementClear;
    private ImageView mImageView_ChargeClear;
    private ImageView mImageView_Quantity;
    private ImageView mImageview_SalesVolume;
    private ImageView mImageView_ClearAddress;
    private ImageView mImageView_CustomerSignature;
    private ImageView mImageView_ManagerSignature;
    private ImageView mImageView_CustomerSignatureArrows;
    private ImageView mImageView_ManagerSignatureArrows;

    private Animation mAnimation;

    private View mTimePickerView;
    private WheelView mWheelView_Year;
    private WheelView mWheelView_Month;
    private WheelView mWheelView_Day;

    private LocationThread mLocationThread;
    private int timeFlag;
    private AlertDialog mProduceDateDialog;
    private ScrollView mScrollView;
    private String mCustomerPathName;
    private String mManagerPathName;

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
        setContentView(R.layout.activity_contract_signature);

        super.init();
    }

    @Override
    public void initView() {
        mLinearLayout_Back = (LinearLayout) findViewById(R.id.id_ll_contract_signature_back);
        mLinearLayout_Keyboard = (LinearLayout) findViewById(R.id.id_ll_contract_signature_keyboard);
        mLinearLayout_NumberZero = (LinearLayout) findViewById(R.id.id_lay_keyboard_number_0);
        mLinearLayout_NumberOne = (LinearLayout) findViewById(R.id.id_lay_keyboard_number_1);
        mLinearLayout_NumberTwo = (LinearLayout) findViewById(R.id.id_lay_keyboard_number_2);
        mLinearLayout_NumberThree = (LinearLayout) findViewById(R.id.id_lay_keyboard_number_3);
        mLinearLayout_NumberFour = (LinearLayout) findViewById(R.id.id_lay_keyboard_number_4);
        mLinearLayout_NumberFive = (LinearLayout) findViewById(R.id.id_lay_keyboard_number_5);
        mLinearLayout_NumberSix = (LinearLayout) findViewById(R.id.id_lay_keyboard_number_6);
        mLinearLayout_NumberSeven = (LinearLayout) findViewById(R.id.id_lay_keyboard_number_7);
        mLinearLayout_NumberEight = (LinearLayout) findViewById(R.id.id_lay_keyboard_number_8);
        mLinearLayout_NumberNine = (LinearLayout) findViewById(R.id.id_lay_keyboard_number_9);
        mLinearLayout_delete = (LinearLayout) findViewById(R.id.id_lay_keyboard_delete);

        mImageView_ClearAddress = (ImageView) findViewById(R.id.id_iv_contract_signature_clear_address);
        mImageView_AddressClear = (ImageView) findViewById(R.id.id_iv_contract_signature_address_clear);
        mImageView_AgreementClear = (ImageView) findViewById(R.id.id_iv_contract_signature_agreement_clear);
        mImageView_ChargeClear = (ImageView) findViewById(R.id.id_iv_contract_signature_charge_clear);
        mImageView_Quantity = (ImageView) findViewById(R.id.id_iv_contract_signature_quantity_clear);
        mImageview_SalesVolume = (ImageView) findViewById(R.id.id_iv_contract_signature_sales_volume_clear);
        mImageView_CustomerSignature = (ImageView) findViewById(R.id.id_iv_customer_signature);
        mImageView_ManagerSignature = (ImageView) findViewById(R.id.id_iv_manager_signature);
        mImageView_CustomerSignatureArrows = (ImageView) findViewById(R.id.id_iv_customer_signature_arrows);
        mImageView_ManagerSignatureArrows = (ImageView) findViewById(R.id.id_iv_manager_signature_arrows);

        mTextView_LocationAddress = (TextView) findViewById(R.id.id_tv_contract_signature_location_address);
        mTextView_RefreshLocation = (TextView) findViewById(R.id.id_tv_contract_signature_refresh_location);
        mTextView_CustomerName = (TextView) findViewById(R.id.id_tv_contract_signature_customer_name);
        mTextView_SignatureTime = (TextView) findViewById(R.id.id_tv_contract_signature_signature_time);
        mTextView_ChargeIssueTime = (TextView) findViewById(R.id.id_tv_contract_signature_charge_issue_time);
        mTextView_SignatureStartTime = (TextView) findViewById(R.id.id_tv_contract_signature_start_time);
        mTextView_SignatureEndTime = (TextView) findViewById(R.id.id_tv_contract_signature_end_time);
        mTextView_DispalyWay = (TextView) findViewById(R.id.id_tv_contract_signature_display_way);
        mTextView_ChargeType = (TextView) findViewById(R.id.id_tv_contract_signature_charge_type);

        mEditText_AgreementNo = (EditText) findViewById(R.id.id_et_contract_signature_agreement);
        mEditText_Address = (EditText) findViewById(R.id.id_et_contract_signature_address);
        mEditText_Charge = (EditText) findViewById(R.id.id_et_contract_signature_charge);
        mEditText_Quantity = (EditText) findViewById(R.id.id_et_contract_signature_quantity);
        mEditText_SalesVolume = (EditText) findViewById(R.id.id_et_contract_signature_sales_volume);

        mFrameLayout_CustomerSignature = (FrameLayout) findViewById(R.id.id_rl_contract_signature_customer_signature);
        mFrameLayout_ManagerSignature = (FrameLayout) findViewById(R.id.id_rl_contract_signature_manager_signature);
        mRelativeLayout_CustomerName = (RelativeLayout) findViewById(R.id.id_rl_contract_signature_customer_name);
        mRelativeLayout_ChargeType = (RelativeLayout) findViewById(R.id.id_rl_contract_signature_charge_type);
        mRelativeLayout_DisplayWay = (RelativeLayout) findViewById(R.id.id_rl_contract_signature_display_way);
        mScrollView = (ScrollView) findViewById(R.id.id_sv_contract_signature);
    }

    @Override
    public void initData() {
        mTextView_SignatureTime.setText(GetSystemTime.getTime("yyyy-MM-dd"));
        handleLocation();
    }

    @Override
    public void initEvent() {
        mLinearLayout_Back.setOnClickListener(this);
        mImageView_ClearAddress.setOnClickListener(this);

        mTextView_RefreshLocation.setOnClickListener(this);
        mTextView_LocationAddress.setOnClickListener(this);
        mTextView_ChargeIssueTime.setOnClickListener(this);
        mTextView_SignatureEndTime.setOnClickListener(this);
        mTextView_SignatureStartTime.setOnClickListener(this);

        mRelativeLayout_DisplayWay.setOnClickListener(this);
        mRelativeLayout_ChargeType.setOnClickListener(this);
        mRelativeLayout_CustomerName.setOnClickListener(this);
        mFrameLayout_CustomerSignature.setOnClickListener(this);
        mFrameLayout_ManagerSignature.setOnClickListener(this);

        mLinearLayout_NumberZero.setOnClickListener(this);
        mLinearLayout_NumberOne.setOnClickListener(this);
        mLinearLayout_NumberTwo.setOnClickListener(this);
        mLinearLayout_NumberThree.setOnClickListener(this);
        mLinearLayout_NumberFour.setOnClickListener(this);
        mLinearLayout_NumberFive.setOnClickListener(this);
        mLinearLayout_NumberSix.setOnClickListener(this);
        mLinearLayout_NumberSeven.setOnClickListener(this);
        mLinearLayout_NumberEight.setOnClickListener(this);
        mLinearLayout_NumberNine.setOnClickListener(this);
        mLinearLayout_delete.setOnClickListener(this);

        mEditText_Charge.addTextChangedListener(new MyTextWatch());
        mEditText_AgreementNo.addTextChangedListener(new MyTextWatch());
        mEditText_Address.addTextChangedListener(new MyTextWatch());
        mEditText_Quantity.addTextChangedListener(new MyTextWatch());
        mEditText_SalesVolume.addTextChangedListener(new MyTextWatch());


        mEditText_Charge.setOnTouchListener(this);
//        mScrollView.setOnDragListener(this);

        mImageView_CustomerSignature.setOnClickListener(this);
        mImageView_ManagerSignature.setOnClickListener(this);
    }

    @Override
    public boolean onTouch(View v, MotionEvent event) {
        switch (v.getId()) {
            case R.id.id_et_contract_signature_charge:
                HideInputMethod.hideInputMethod(this);
                mEditText_Charge.requestFocusFromTouch();
                if (mLinearLayout_Keyboard.getVisibility() == View.GONE) {
                    mAnimation = AnimationUtils.loadAnimation(this, R.anim.keyboard_enter);
                    mLinearLayout_Keyboard.setAnimation(mAnimation);
                    mLinearLayout_Keyboard.setVisibility(View.VISIBLE);
                }
                break;
            default:
                break;
        }

        return true;
    }

    @Override
    public void onClick(View v) {
        Intent intent = new Intent();
        String number = mEditText_Charge.getText().toString();
        switch (v.getId()) {
            case R.id.id_ll_contract_signature_back:
                finish();
                break;
            case R.id.id_iv_contract_signature_clear_address:
                mTextView_LocationAddress.setPaintFlags(Paint.ANTI_ALIAS_FLAG);
                mTextView_LocationAddress.setText("点击获取位置信息");
                mImageView_ClearAddress.setVisibility(View.GONE);
                break;
            case R.id.id_tv_contract_signature_refresh_location:
                mTextView_LocationAddress.setPaintFlags(Paint.ANTI_ALIAS_FLAG);
                mTextView_LocationAddress.setText("正在定位...");
                mImageView_ClearAddress.setVisibility(View.GONE);
                handleLocation();
                break;
            case R.id.id_tv_contract_signature_location_address:
                if (mTextView_LocationAddress.getText().toString().equals("点击获取位置信息")) {
                    mTextView_LocationAddress.setPaintFlags(Paint.ANTI_ALIAS_FLAG);
                    mTextView_LocationAddress.setText("正在定位...");
                    mImageView_ClearAddress.setVisibility(View.GONE);
                    handleLocation();
                } else {
                    intent.setClass(this, FineAdjustmentActivity.class);
                    startActivityForResult(intent, 0);
                    overridePendingTransition(0, R.anim.activity_enter);
                }
                break;
            case R.id.id_rl_contract_signature_customer_name:
                intent.setClass(this, CustomerManageActivity.class);
                intent.putExtra("requestCode", CONTRACT_SIGNATURE_REQUEST);
                startActivityForResult(intent, CONTRACT_SIGNATURE_REQUEST);
                break;
            case R.id.id_lay_keyboard_number_0:
                mEditText_Charge.setText(number + "0");
                break;
            case R.id.id_lay_keyboard_number_1:
                mEditText_Charge.setText(number + "1");
                break;
            case R.id.id_lay_keyboard_number_2:
                mEditText_Charge.setText(number + "2");
                break;
            case R.id.id_lay_keyboard_number_3:
                mEditText_Charge.setText(number + "3");
                break;
            case R.id.id_lay_keyboard_number_4:
                mEditText_Charge.setText(number + "4");
                break;
            case R.id.id_lay_keyboard_number_5:
                mEditText_Charge.setText(number + "5");
                break;
            case R.id.id_lay_keyboard_number_6:
                mEditText_Charge.setText(number + "6");
                break;
            case R.id.id_lay_keyboard_number_7:
                mEditText_Charge.setText(number + "7");
                break;
            case R.id.id_lay_keyboard_number_8:
                mEditText_Charge.setText(number + "8");
                break;
            case R.id.id_lay_keyboard_number_9:
                mEditText_Charge.setText(number + "9");
                break;
            case R.id.id_lay_keyboard_delete:
                if (!"".equals(number)) {
                    number = number.substring(0, number.length() - 1);
                    mEditText_Charge.setText(number);
                }
                break;
            case R.id.id_tv_contract_signature_charge_issue_time:
                HideInputMethod.hideInputMethod(this);
                timeFlag = 1;
                processDateDialog();
                break;
            case R.id.id_tv_contract_signature_end_time:
                HideInputMethod.hideInputMethod(this);
                timeFlag = 2;
                processDateDialog();
                break;
            case R.id.id_tv_contract_signature_start_time:
                HideInputMethod.hideInputMethod(this);
                timeFlag = 3;
                processDateDialog();
                break;
            case R.id.id_tv_time_picker_cancel:
                mProduceDateDialog.dismiss();
                break;
            case R.id.id_tv_time_picker_confirm:
                String day = mWheelView_Day.getSelectedText();
                String month = mWheelView_Month.getSelectedText();
                if (month.length() == 1) {//如果不足两位数，则补零
                    month = "0" + month;
                }
                if (day.length() == 1) {//如果不足两位数，则补零
                    day = "0" + day;
                }
                String year = mWheelView_Year.getSelectedText();
                String date = year + "-" + month + "-" + day;
                if (timeFlag == 1) {
                    mTextView_ChargeIssueTime.setText(date);

                } else if (timeFlag == 2) {
                    mTextView_SignatureEndTime.setText(date);

                } else if (timeFlag == 3) {
                    mTextView_SignatureStartTime.setText(date);
                }
                mProduceDateDialog.dismiss();
                break;
            case R.id.id_rl_contract_signature_customer_signature:
                intent.setClass(this, CustomerSignatureActivity.class);
                startActivityForResult(intent, CONTRACT_SIGNATURE_CUSTOMER_REQUEST);
                break;
            case R.id.id_rl_contract_signature_manager_signature:
                intent.setClass(this, ManagerSignatureActivity.class);
                startActivityForResult(intent, CONTRACT_SIGNATURE_MANAGER_REQUEST);
                break;
            case R.id.id_iv_customer_signature:
                intent.setClass(this, CustomerSignatureDetailActivity.class);
                intent.putExtra("pathName", mCustomerPathName);
                startActivity(intent);
                break;
            case R.id.id_iv_manager_signature:
                intent.setClass(this, ManagerSignatureDetailActivity.class);
                intent.putExtra("pathName", mManagerPathName);
                startActivity(intent);
                break;
            case R.id.id_rl_contract_signature_display_way:
                intent.setClass(this, SelectDisplayWayActivity.class);
                startActivityForResult(intent, 4);
                break;
            case R.id.id_rl_contract_signature_charge_type:
                intent.setClass(this, ChargeTypeActivity.class);
                startActivityForResult(intent, 5);
                break;
            default:
                break;
        }
    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == RESULT_OK && data != null) {
            if (requestCode == 0) {
                String name = data.getStringExtra("name");
                String address = data.getStringExtra("address");
                address = name + "，" + address;
                mTextView_LocationAddress.setText(address);
            } else if (requestCode == CONTRACT_SIGNATURE_REQUEST) {
                String customerName = data.getStringExtra("customerName");
                mTextView_CustomerName.setText(customerName);
            } else if (requestCode == CONTRACT_SIGNATURE_CUSTOMER_REQUEST) {
                mCustomerPathName = data.getStringExtra("pathName");
                Bitmap bitmap = decodeSampledBitmapFromFile(mCustomerPathName, mFrameLayout_CustomerSignature.getHeight(), mFrameLayout_CustomerSignature.getHeight());
                mImageView_CustomerSignatureArrows.setVisibility(View.GONE);
                mImageView_CustomerSignature.setVisibility(View.VISIBLE);
                mImageView_CustomerSignature.setImageBitmap(bitmap);

            } else if (requestCode == CONTRACT_SIGNATURE_MANAGER_REQUEST) {
                mManagerPathName = data.getStringExtra("pathName");
                Bitmap bitmap = decodeSampledBitmapFromFile(mManagerPathName, mFrameLayout_ManagerSignature.getHeight(), mFrameLayout_ManagerSignature.getHeight());
                mImageView_ManagerSignatureArrows.setVisibility(View.GONE);
                mImageView_ManagerSignature.setVisibility(View.VISIBLE);
                mImageView_ManagerSignature.setImageBitmap(bitmap);
            } else if (requestCode == 4) {
                String displayWay = data.getStringExtra("data");
                mTextView_DispalyWay.setText(displayWay);

            }else if (requestCode == 5){
                String chargeType = data.getStringExtra("chargeType");
                mTextView_ChargeType.setText(chargeType);
            }
        }
    }

    @Override
    public void onBackPressed() {
        if (mLinearLayout_Keyboard.getVisibility() == View.VISIBLE) {
            mAnimation = AnimationUtils.loadAnimation(this, R.anim.keyboard_exit);
            mLinearLayout_Keyboard.setAnimation(mAnimation);
            mLinearLayout_Keyboard.setVisibility(View.GONE);
        } else {
            super.onBackPressed();
        }
    }

    /**
     * 启动获取位置信息的线程
     */
    private void handleLocation() {
        if (mLocationThread == null) {
            mLocationThread = new LocationThread();
        }
        if (!mLocationThread.isAlive())
            mLocationThread.start();
    }
//
//    @Override
//    public boolean onDrag(View v, DragEvent event) {
//
//        if (mLinearLayout_Keyboard.getVisibility() == View.VISIBLE) {
//            mAnimation = AnimationUtils.loadAnimation(this, R.anim.keyboard_exit);
//            mLinearLayout_Keyboard.setAnimation(mAnimation);
//            mLinearLayout_Keyboard.setVisibility(View.GONE);
//        }
//        return true;
//    }


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

    /**
     * 监听输入框的变化
     */
    private class MyTextWatch implements TextWatcher {

        @Override
        public void beforeTextChanged(CharSequence s, int start, int count, int after) {

        }

        @Override
        public void onTextChanged(CharSequence s, int start, int before, int count) {

        }

        @Override
        public void afterTextChanged(Editable s) {
            if (mEditText_Charge.isFocused()) {
                mEditText_Charge.setSelection(mEditText_Charge.getText().length());
            }
        }
    }

//    @Override
//    public boolean dispatchTouchEvent(MotionEvent ev) {
//        if (mLinearLayout_Keyboard.getVisibility() == View.VISIBLE) {
//            mAnimation = AnimationUtils.loadAnimation(this, R.anim.keyboard_exit);
//            mLinearLayout_Keyboard.setAnimation(mAnimation);
//            mLinearLayout_Keyboard.setVisibility(View.GONE);
//        }
//        return super.dispatchTouchEvent(ev);
//    }


    /**
     * 日期选择Dialog
     */
    private void processDateDialog() {

        mTimePickerView = LayoutInflater.from(getContext()).inflate(R.layout.layout_time_picker_layout, null);

        TextView mTextView_Cancel = (TextView) mTimePickerView.findViewById(R.id.id_tv_time_picker_cancel);
        TextView mTextView_Confirm = (TextView) mTimePickerView.findViewById(R.id.id_tv_time_picker_confirm);
        mTextView_Cancel.setOnClickListener(this);
        mTextView_Confirm.setOnClickListener(this);

        mWheelView_Year = (WheelView) mTimePickerView.findViewById(R.id.year);
        mWheelView_Month = (WheelView) mTimePickerView.findViewById(R.id.month);
        mWheelView_Day = (WheelView) mTimePickerView.findViewById(R.id.day);

        mProduceDateDialog = new AlertDialog.Builder(getContext(), R.style.dialog1).create();
        mProduceDateDialog.show();
        mProduceDateDialog.setContentView(mTimePickerView);
        WindowManager.LayoutParams params = mProduceDateDialog.getWindow().getAttributes();
        params.width = Util.getScreenWidth(getContext());
        params.dimAmount = 0.3f;
        params.gravity = Gravity.BOTTOM;
        mProduceDateDialog.getWindow().setAttributes(params);

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
}
