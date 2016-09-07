package com.youxiao.ui.activity.work.marketpatrol;

import android.content.Intent;
import android.graphics.Paint;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.MotionEvent;
import android.view.View;
import android.view.Window;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.youxiao.R;
import com.youxiao.base.BaseActivity;
import com.youxiao.ui.activity.work.attendancesignature.FineAdjustmentActivity;
import com.youxiao.ui.activity.work.customermanager.CustomerManageActivity;
import com.youxiao.util.HideInputMethod;

/**
 * 市场巡查
 *
 * @author StomHong
 * @since 2016-7-23
 */
public class MarketPatrolActivity extends BaseActivity implements View.OnClickListener, View.OnTouchListener {

    /**
     * 市场巡查请求码
     **/
    public static final int MARKET_PATROL_REQUEST = 102;
    private static final String TAG = MarketPatrolActivity.class.getSimpleName();

    private TextView mTextView_LocationAddress;
    private TextView mTextView_RefreshLocation;
    private TextView mTextView_Score;
    private TextView mTextView_CustomerName;

    private ImageView mImageView_ClearAddress;
    private ImageView mImageView_Star1;
    private ImageView mImageView_Star2;
    private ImageView mImageView_Star3;
    private ImageView mImageView_Star4;
    private ImageView mImageView_Star5;

    private EditText mEditText_ConditionQuantity;
    private EditText mEditText_ExpireCommodity;

    private LinearLayout mLinearLayout_Back;
    private LinearLayout mLinearLayout_CustomerType;
    private LinearLayout mLinearLayout_DisplayPlace;
    private LinearLayout mLinearLayout_DisplayPerformance;

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

    private LinearLayout mLinearLayout_FirstPlace;
    private LinearLayout mLinearLayout_SecondPlace;
    private LinearLayout mLinearLayout_LastPlace;
    private LinearLayout mLinearLayout_PerformanceCleanliness;
    private LinearLayout mLinearLayout_PerformanceStandees;
    private LinearLayout mLinearLayout_PerformanceSpecialShelt;
    private LinearLayout mLinearLayout_PerformancePrice;
    private LinearLayout mLinearLayout_PerformanceCustomer;
    private LinearLayout mLinearLayout_TypeA;
    private LinearLayout mLinearLayout_TypeB;
    private LinearLayout mLinearLayout_TypeC;
    private LinearLayout mLinearLayout_TypeD;

    private RelativeLayout mRelativeLayout_CustomerType;
    private RelativeLayout mRelativeLayout_CustomerName;
    private RelativeLayout mRelativeLayout_DisplayPlace;
    private RelativeLayout mRelativeLayout_DisplayPerformance;

    private RelativeLayout mRelativeLayout_ConditionQuantity;
    private RelativeLayout mRelativeLayout_ExpireCommodity;
    private RelativeLayout mRelativeLayout_TitleBar;

    private Animation mDialogAnimation;
    private View mView_ContentView1;
    private View mView_ContentView2;
    private LinearLayout mLinearLayout_ContentView;
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
        setContentView(R.layout.activity_market_patrol);

        super.init();
    }

    @Override
    public void initView() {

        mTextView_LocationAddress = (TextView) findViewById(R.id.id_tv_market_patrol_location_address);
        mTextView_RefreshLocation = (TextView) findViewById(R.id.id_tv_market_patrol_refresh_location);
        mTextView_Score = (TextView) findViewById(R.id.id_tv_market_patrol_score);
        mTextView_CustomerName = (TextView) findViewById(R.id.id_tv_market_patrol_customer_name);

        mImageView_ClearAddress = (ImageView) findViewById(R.id.id_iv_market_patrol_clear_address);
        mImageView_Star1 = (ImageView) findViewById(R.id.id_iv_market_patrol_star_1);
        mImageView_Star2 = (ImageView) findViewById(R.id.id_iv_market_patrol_star_2);
        mImageView_Star3 = (ImageView) findViewById(R.id.id_iv_market_patrol_star_3);
        mImageView_Star4 = (ImageView) findViewById(R.id.id_iv_market_patrol_star_4);
        mImageView_Star5 = (ImageView) findViewById(R.id.id_iv_market_patrol_star_5);


        mEditText_ConditionQuantity = (EditText) findViewById(R.id.id_et_market_patrol_condition_quantity);
        mEditText_ExpireCommodity = (EditText) findViewById(R.id.id_et_market_patrol_expire_commodity);

        mLinearLayout_Back = (LinearLayout) findViewById(R.id.id_ll_work_market_patrol_back);

        mRelativeLayout_CustomerType = (RelativeLayout) findViewById(R.id.id_rl_market_patrol_customer_type);
        mRelativeLayout_DisplayPlace = (RelativeLayout) findViewById(R.id.id_rl_market_patrol_display_place);
        mRelativeLayout_DisplayPerformance = (RelativeLayout) findViewById(R.id.id_rl_market_patrol_display_performance);
        mRelativeLayout_CustomerName = (RelativeLayout) findViewById(R.id.id_rl_market_patrol_customer_name);

        mRelativeLayout_ConditionQuantity = (RelativeLayout) findViewById(R.id.id_rl_market_patrol_condition_quantity);
        mRelativeLayout_ExpireCommodity = (RelativeLayout) findViewById(R.id.id_rl_market_patrol_expiry_commodity);
        mRelativeLayout_TitleBar = (RelativeLayout) findViewById(R.id.id_rl_title_bar);

        mLinearLayout_CustomerType = (LinearLayout) findViewById(R.id.id_ll_market_patrol_customer_type);
        mLinearLayout_DisplayPlace = (LinearLayout) findViewById(R.id.id_ll_market_patrol_display_place);
        mLinearLayout_DisplayPerformance = (LinearLayout) findViewById(R.id.id_ll_market_patrol_display_performance);

        mLinearLayout_FirstPlace = (LinearLayout) findViewById(R.id.id_ll_market_patrol_first_place);
        mLinearLayout_SecondPlace = (LinearLayout) findViewById(R.id.id_ll_market_patrol_second_place);
        mLinearLayout_LastPlace = (LinearLayout) findViewById(R.id.id_ll_market_patrol_last_place);

        mLinearLayout_PerformanceCleanliness = (LinearLayout) findViewById(R.id.id_ll_market_patrol_performance_cleanliness);
        mLinearLayout_PerformanceCustomer = (LinearLayout) findViewById(R.id.id_ll_market_patrol_performance_customer);
        mLinearLayout_PerformancePrice = (LinearLayout) findViewById(R.id.id_ll_market_patrol_performance_price);
        mLinearLayout_PerformanceSpecialShelt = (LinearLayout) findViewById(R.id.id_ll_market_patrol_performance_special_shelf);
        mLinearLayout_PerformanceStandees = (LinearLayout) findViewById(R.id.id_ll_market_patrol_performance_standees);

        mLinearLayout_TypeA = (LinearLayout) findViewById(R.id.id_ll_market_patrol_type_a);
        mLinearLayout_TypeB = (LinearLayout) findViewById(R.id.id_ll_market_patrol_type_b);
        mLinearLayout_TypeC = (LinearLayout) findViewById(R.id.id_ll_market_patrol_type_c);
        mLinearLayout_TypeD = (LinearLayout) findViewById(R.id.id_ll_market_patrol_type_d);

        mLinearLayout_Keyboard = (LinearLayout) findViewById(R.id.id_market_patrol_keyboard);
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

        mView_ContentView1 = findViewById(R.id.id_ll_content_view_1);
        mView_ContentView2 = findViewById(R.id.id_ll_content_view_2);
        mLinearLayout_ContentView = (LinearLayout) findViewById(R.id.id_ll_content_view);
    }

    @Override
    public void initData() {
        HideInputMethod.hideInputMethod(this);
        handleLocation();
    }


    @Override
    public void initEvent() {
        mRelativeLayout_CustomerType.setOnTouchListener(this);
        mRelativeLayout_DisplayPlace.setOnTouchListener(this);
        mRelativeLayout_DisplayPerformance.setOnTouchListener(this);

        mTextView_RefreshLocation.setOnClickListener(this);
        mTextView_LocationAddress.setOnClickListener(this);

        mImageView_ClearAddress.setOnClickListener(this);

        mImageView_Star1.setOnClickListener(this);
        mImageView_Star2.setOnClickListener(this);
        mImageView_Star3.setOnClickListener(this);
        mImageView_Star4.setOnClickListener(this);
        mImageView_Star5.setOnClickListener(this);

        mLinearLayout_Back.setOnClickListener(this);
        mLinearLayout_FirstPlace.setOnClickListener(this);
        mLinearLayout_SecondPlace.setOnClickListener(this);
        mLinearLayout_LastPlace.setOnClickListener(this);

        mLinearLayout_PerformanceCleanliness.setOnClickListener(this);
        mLinearLayout_PerformanceStandees.setOnClickListener(this);
        mLinearLayout_PerformanceSpecialShelt.setOnClickListener(this);
        mLinearLayout_PerformancePrice.setOnClickListener(this);
        mLinearLayout_PerformanceCustomer.setOnClickListener(this);

        mLinearLayout_TypeA.setOnClickListener(this);
        mLinearLayout_TypeB.setOnClickListener(this);
        mLinearLayout_TypeC.setOnClickListener(this);
        mLinearLayout_TypeD.setOnClickListener(this);

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

        mRelativeLayout_ConditionQuantity.setOnClickListener(this);
        mRelativeLayout_ExpireCommodity.setOnClickListener(this);
        mRelativeLayout_CustomerName.setOnClickListener(this);

        mEditText_ExpireCommodity.setOnTouchListener(this);
        mEditText_ConditionQuantity.setOnTouchListener(this);

    }

    @Override
    public boolean onTouch(View v, MotionEvent event) {
        Animation animation;
        if (mLinearLayout_Keyboard.getVisibility() == View.VISIBLE) {
            animation = AnimationUtils.loadAnimation(this, R.anim.keyboard_exit);
            mLinearLayout_Keyboard.setAnimation(animation);
            mLinearLayout_Keyboard.setVisibility(View.GONE);
            recoverInitialState();
        }
        if (event.getAction() == MotionEvent.ACTION_UP) {
            switch (v.getId()) {
                case R.id.id_rl_market_patrol_customer_type:
                    if (mLinearLayout_CustomerType.getVisibility() == View.GONE) {
//                    mDialogAnimation = AnimationUtils.loadAnimation(this, R.anim.select_stock_enter);
//                    mLinearLayout_CustomerType.setAnimation(mDialogAnimation);
                        mLinearLayout_CustomerType.setVisibility(View.VISIBLE);
                    } else {
//                    mDialogAnimation = AnimationUtils.loadAnimation(this, R.anim.select_stock_exit);
//                    mLinearLayout_CustomerType.setAnimation(mDialogAnimation);
                        mLinearLayout_CustomerType.setVisibility(View.GONE);
                    }
                    break;
                case R.id.id_rl_market_patrol_display_performance:
                    if (mLinearLayout_DisplayPerformance.getVisibility() == View.GONE) {
//                    mDialogAnimation = AnimationUtils.loadAnimation(this, R.anim.select_stock_enter);
//                    mLinearLayout_DisplayPerformance.setAnimation(mDialogAnimation);
                        mLinearLayout_DisplayPerformance.setVisibility(View.VISIBLE);
                    } else {
//                    mDialogAnimation = AnimationUtils.loadAnimation(this, R.anim.select_stock_exit);
//                    mLinearLayout_DisplayPerformance.setAnimation(mDialogAnimation);
                        mLinearLayout_DisplayPerformance.setVisibility(View.GONE);
                    }
                    break;
                case R.id.id_rl_market_patrol_display_place:
                    if (mLinearLayout_DisplayPlace.getVisibility() == View.GONE) {
//                    mDialogAnimation = AnimationUtils.loadAnimation(this, R.anim.select_stock_enter);
//                    mLinearLayout_DisplayPlace.setAnimation(mDialogAnimation);
                        mLinearLayout_DisplayPlace.setVisibility(View.VISIBLE);
                    } else {
//                    mDialogAnimation = AnimationUtils.loadAnimation(this, R.anim.select_stock_exit);
//                    mLinearLayout_DisplayPlace.setAnimation(mDialogAnimation);
                        mLinearLayout_DisplayPlace.setVisibility(View.GONE);
                    }
                    break;
                case R.id.id_et_market_patrol_condition_quantity:
                    mEditText_ConditionQuantity.requestFocusFromTouch();
                    mEditText_ConditionQuantity.setSelection(mEditText_ConditionQuantity.getHint().length());
                    if (mLinearLayout_Keyboard.getVisibility() == View.GONE) {
                        animation = AnimationUtils.loadAnimation(this, R.anim.keyboard_enter);
                        mLinearLayout_Keyboard.setAnimation(animation);
                        mLinearLayout_Keyboard.setVisibility(View.VISIBLE);
//                    mView_ContentView1.setBottom(mLinearLayout_Keyboard.getHeight());
                        mLinearLayout_ContentView.scrollTo(0, 190);
                    }
                    break;
                case R.id.id_et_market_patrol_expire_commodity:
                    mEditText_ExpireCommodity.requestFocusFromTouch();
                    mEditText_ExpireCommodity.setSelection(mEditText_ExpireCommodity.getHint().length());
                    if (mLinearLayout_Keyboard.getVisibility() == View.GONE) {
                        animation = AnimationUtils.loadAnimation(this, R.anim.keyboard_enter);
                        mLinearLayout_Keyboard.setAnimation(animation);
                        mLinearLayout_Keyboard.setVisibility(View.VISIBLE);
//                    mView_ContentView2.setBottom(mLinearLayout_Keyboard.getHeight());

                        mLinearLayout_ContentView.scrollTo(0, 200);
                    }
                    break;
                default:
                    break;

            }
        }

        return true;
    }

    @Override
    public void onClick(View v) {
        Animation animation;
        Intent intent = new Intent();
        String number = "";
        if (mEditText_ConditionQuantity.isFocused()) {
            number = mEditText_ConditionQuantity.getText().toString();
        } else if (mEditText_ExpireCommodity.isFocused()) {
            number = mEditText_ExpireCommodity.getText().toString();
        }
        switch (v.getId()) {
            case R.id.id_iv_market_patrol_clear_address:
                mTextView_LocationAddress.setPaintFlags(Paint.ANTI_ALIAS_FLAG);
                mTextView_LocationAddress.setText("点击获取位置信息");
                mImageView_ClearAddress.setVisibility(View.GONE);
                break;
            case R.id.id_tv_market_patrol_refresh_location:
                mTextView_LocationAddress.setPaintFlags(Paint.ANTI_ALIAS_FLAG);
                mTextView_LocationAddress.setText("正在定位...");
                mImageView_ClearAddress.setVisibility(View.GONE);
                handleLocation();
                break;
            case R.id.id_tv_market_patrol_location_address:
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
            case R.id.id_ll_work_market_patrol_back:
                finish();
                break;

            case R.id.id_rl_market_patrol_customer_name:
                intent.setClass(this, CustomerManageActivity.class);
                intent.putExtra("requestCode", MARKET_PATROL_REQUEST);
                startActivityForResult(intent, MARKET_PATROL_REQUEST);
                break;

            case R.id.id_iv_market_patrol_star_1:
                mImageView_Star1.setImageResource(R.drawable.ratingbar_selected);
                mImageView_Star2.setImageResource(R.drawable.ratingbar_unselected);
                mImageView_Star3.setImageResource(R.drawable.ratingbar_unselected);
                mImageView_Star4.setImageResource(R.drawable.ratingbar_unselected);
                mImageView_Star5.setImageResource(R.drawable.ratingbar_unselected);
                mTextView_Score.setVisibility(View.VISIBLE);
                mTextView_Score.setText(1 + "分");
                break;
            case R.id.id_iv_market_patrol_star_2:
                mImageView_Star1.setImageResource(R.drawable.ratingbar_selected);
                mImageView_Star2.setImageResource(R.drawable.ratingbar_selected);
                mImageView_Star3.setImageResource(R.drawable.ratingbar_unselected);
                mImageView_Star4.setImageResource(R.drawable.ratingbar_unselected);
                mImageView_Star5.setImageResource(R.drawable.ratingbar_unselected);
                mTextView_Score.setVisibility(View.VISIBLE);
                mTextView_Score.setText(2 + "分");

                break;
            case R.id.id_iv_market_patrol_star_3:
                mImageView_Star1.setImageResource(R.drawable.ratingbar_selected);
                mImageView_Star2.setImageResource(R.drawable.ratingbar_selected);
                mImageView_Star3.setImageResource(R.drawable.ratingbar_selected);
                mImageView_Star4.setImageResource(R.drawable.ratingbar_unselected);
                mImageView_Star5.setImageResource(R.drawable.ratingbar_unselected);
                mTextView_Score.setVisibility(View.VISIBLE);
                mTextView_Score.setText(3 + "分");

                break;
            case R.id.id_iv_market_patrol_star_4:
                mImageView_Star1.setImageResource(R.drawable.ratingbar_selected);
                mImageView_Star2.setImageResource(R.drawable.ratingbar_selected);
                mImageView_Star3.setImageResource(R.drawable.ratingbar_selected);
                mImageView_Star4.setImageResource(R.drawable.ratingbar_selected);
                mImageView_Star5.setImageResource(R.drawable.ratingbar_unselected);
                mTextView_Score.setVisibility(View.VISIBLE);
                mTextView_Score.setText(4 + "分");
                break;
            case R.id.id_iv_market_patrol_star_5:
                mImageView_Star1.setImageResource(R.drawable.ratingbar_selected);
                mImageView_Star2.setImageResource(R.drawable.ratingbar_selected);
                mImageView_Star3.setImageResource(R.drawable.ratingbar_selected);
                mImageView_Star4.setImageResource(R.drawable.ratingbar_selected);
                mImageView_Star5.setImageResource(R.drawable.ratingbar_selected);
                mTextView_Score.setVisibility(View.VISIBLE);
                mTextView_Score.setText(5 + "分");
                break;
            case R.id.id_ll_market_patrol_performance_cleanliness:
                mLinearLayout_DisplayPerformance.setVisibility(View.GONE);
                break;
            case R.id.id_ll_market_patrol_performance_customer:
                mLinearLayout_DisplayPerformance.setVisibility(View.GONE);
                break;
            case R.id.id_ll_market_patrol_performance_price:
                mLinearLayout_DisplayPerformance.setVisibility(View.GONE);
                break;
            case R.id.id_ll_market_patrol_performance_special_shelf:
                mLinearLayout_DisplayPerformance.setVisibility(View.GONE);
                break;
            case R.id.id_ll_market_patrol_performance_standees:
                mLinearLayout_DisplayPerformance.setVisibility(View.GONE);
                break;
            case R.id.id_ll_market_patrol_second_place:
                mLinearLayout_DisplayPlace.setVisibility(View.GONE);
                break;
            case R.id.id_ll_market_patrol_first_place:
                mLinearLayout_DisplayPlace.setVisibility(View.GONE);
                break;
            case R.id.id_ll_market_patrol_last_place:
                mLinearLayout_DisplayPlace.setVisibility(View.GONE);
                break;
            case R.id.id_ll_market_patrol_type_a:
                mLinearLayout_CustomerType.setVisibility(View.GONE);
                break;
            case R.id.id_ll_market_patrol_type_b:
                mLinearLayout_CustomerType.setVisibility(View.GONE);
                break;
            case R.id.id_ll_market_patrol_type_c:
                mLinearLayout_CustomerType.setVisibility(View.GONE);

                break;
            case R.id.id_ll_market_patrol_type_d:
                mLinearLayout_CustomerType.setVisibility(View.GONE);

                break;
            case R.id.id_rl_market_patrol_condition_quantity:
                mEditText_ConditionQuantity.requestFocusFromTouch();
                if (mLinearLayout_Keyboard.getVisibility() == View.GONE) {
                    animation = AnimationUtils.loadAnimation(this, R.anim.keyboard_enter);
                    mLinearLayout_Keyboard.setAnimation(animation);
                    mLinearLayout_Keyboard.setVisibility(View.VISIBLE);
//                    mView_ContentView1.setBottom(mLinearLayout_Keyboard.getHeight());
                    mLinearLayout_ContentView.scrollTo(0, 190);
                }
                break;
            case R.id.id_rl_market_patrol_expiry_commodity:
                mEditText_ExpireCommodity.requestFocusFromTouch();
                if (mLinearLayout_Keyboard.getVisibility() == View.GONE) {
                    animation = AnimationUtils.loadAnimation(this, R.anim.keyboard_enter);
                    mLinearLayout_Keyboard.setAnimation(animation);
                    mLinearLayout_Keyboard.setVisibility(View.VISIBLE);
//                    mView_ContentView2.setBottom(mLinearLayout_Keyboard.getHeight());

                    mLinearLayout_ContentView.scrollTo(0, 200);
                }
                break;

            case R.id.id_lay_keyboard_number_0:
                setNumber(number, "0");
                break;
            case R.id.id_lay_keyboard_number_1:
                setNumber(number, "1");
                break;
            case R.id.id_lay_keyboard_number_2:
                setNumber(number, "2");
                break;
            case R.id.id_lay_keyboard_number_3:
                setNumber(number, "3");
                break;
            case R.id.id_lay_keyboard_number_4:
                setNumber(number, "4");
                break;
            case R.id.id_lay_keyboard_number_5:
                setNumber(number, "5");
                break;
            case R.id.id_lay_keyboard_number_6:
                setNumber(number, "6");
                break;
            case R.id.id_lay_keyboard_number_7:
                setNumber(number, "7");
                break;
            case R.id.id_lay_keyboard_number_8:
                setNumber(number, "8");
                break;
            case R.id.id_lay_keyboard_number_9:
                setNumber(number, "9");
                break;
            case R.id.id_lay_keyboard_delete:
                if (!"".equals(number)) {
                    if (mEditText_ConditionQuantity.isFocused()) {
                        number = number.substring(0, number.length() - 1);
                        mEditText_ConditionQuantity.setText(number);
                    } else if (mEditText_ExpireCommodity.isFocused()) {
                        number = number.substring(0, number.length() - 1);
                        mEditText_ExpireCommodity.setText(number);
                    }
                }
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
            } else if (requestCode == MARKET_PATROL_REQUEST) {
                String customerName = data.getStringExtra("customerName");
                mTextView_CustomerName.setText(customerName);
            }
        }
    }

    @Override
    public void onBackPressed() {
        if (mLinearLayout_Keyboard.getVisibility() == View.VISIBLE) {
            Animation animation = AnimationUtils.loadAnimation(this, R.anim.keyboard_exit);
            mLinearLayout_Keyboard.setAnimation(animation);
            mLinearLayout_Keyboard.setVisibility(View.GONE);
            recoverInitialState();
        } else {
            super.onBackPressed();
        }
    }

    /**
     * 将布局恢复至初始化状态
     */
    private void recoverInitialState() {
        mLinearLayout_ContentView.scrollTo(0, 0);
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
     * 响应键盘的点击事件
     *
     * @param number
     * @param key
     */
    private void setNumber(String number, String key) {
        if (mEditText_ConditionQuantity.isFocused()) {
            mEditText_ConditionQuantity.setText(number + key);
            mEditText_ConditionQuantity.setSelection(mEditText_ConditionQuantity.length());

        } else if (mEditText_ExpireCommodity.isFocused()) {
            mEditText_ExpireCommodity.setText(number + key);
            mEditText_ExpireCommodity.setSelection(mEditText_ExpireCommodity.length());

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
