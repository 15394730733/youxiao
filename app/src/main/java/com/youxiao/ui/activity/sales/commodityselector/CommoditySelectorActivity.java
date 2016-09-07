//package com.youxiao.ui.activity.salesActivity.commoditySelector;
//
//import android.app.Activity;
//import android.app.AlertDialog;
//import android.app.FragmentManager;
//import android.app.FragmentTransaction;
//import android.content.Context;
//import android.content.Intent;
//import android.os.Bundle;
//import android.os.Handler;
//import android.os.Message;
//import android.text.Editable;
//import android.text.TextWatcher;
//import android.view.Gravity;
//import android.view.LayoutInflater;
//import android.view.MotionEvent;
//import android.view.View;
//import android.view.ViewGroup;
//import android.view.Window;
//import android.view.WindowManager;
//import android.view.inputmethod.InputMethodManager;
//import android.widget.BaseAdapter;
//import android.widget.Button;
//import android.widget.EditText;
//import android.widget.ImageView;
//import android.widget.LinearLayout;
//import android.widget.ListView;
//import android.widget.TextView;
//import android.widget.Toast;
//
//import com.youxiao.Activity.MainActivity;
//import com.youxiao.ui.fragment.FragmentTxtLeft;
//import com.youxiao.R;
//
//import com.youxiao.ui.activity.MainActivity;
//import com.youxiao.view.Util;
//import com.youxiao.view.WheelView;
//import com.youxiao.view.XKPrograssDialog;
//
//import java.util.ArrayList;
//import java.util.Date;
//import java.util.HashMap;
//import java.util.List;
//import java.util.Map;
//
///**
// * 商品选择器
// */
//public class CommoditySelectorActivity extends Activity implements View.OnClickListener, View.OnTouchListener {
//
//    private static String TAG = "CommoditySelectorActivity";
//    private ListView listViewRight;
//    private LinearLayout back;
//    private ImageView imageView2, imageView3, imageView4, imageView5, imageView6, imageView7, imageView8, imageView9, imageView10, imageView11;
//    private TextView textView1, textView2, textView3, textView4, textView5, textView6, textView7, textView8, textView9, textView10, textView11;
//    private Button submit;
//    private String str;
//    private Handler mHandler;
//    private MyAdapter myAdapter;
//    private Date refreshTime;  //刷屏时间
//    private XKPrograssDialog dialog;
//    private String FROM_FORM;
//    private Context mContext;
//    private List<String> listSelectedCmdtyId = new ArrayList<>();  //已选择的商品ID
//    private List<String> listSelectedCmdtyName = new ArrayList<>();  //已选择的商品名称
//    private List<STR_SELECTCMDTY> listSelectedCmdtyInfor = new ArrayList<>();  //已选的商品信息map
//
//    private AlertDialog mQuantityKeyboardDialog;
//    private AlertDialog mPriceKeyboardDialog;
//
//    private LinearLayout mLinearLayout_Backspace;
//    private LinearLayout mLinearLayout_Dot;
//    private LinearLayout mLinearLayout_NumberZero;
//    private LinearLayout mLinearLayout_NumberOne;
//    private LinearLayout mLinearLayout_NumberTwo;
//    private LinearLayout mLinearLayout_NumberThree;
//    private LinearLayout mLinearLayout_NumberFour;
//    private LinearLayout mLinearLayout_NumberFive;
//    private LinearLayout mLinearLayout_NumberSix;
//    private LinearLayout mLinearLayout_NumberSeven;
//    private LinearLayout mLinearLayout_NumberEight;
//    private LinearLayout mLinearLayout_NumberNine;
//    private LinearLayout mLinearLayout_Confirm;
//    private LinearLayout mLinearLayout_Cancel;
//
//    private EditText mEditText_InputBoxLeft;
//    private EditText mEditText_InputBoxRight;
//    private TextView mTextView_TextLeft;
//    private TextView mTextView_TextRight;
//
//    private ImageView mImageView_TextClearRight;
//    private ImageView mImageView_TextClearLeft;
//
//    private WheelView mWheelView_Year;
//    private WheelView mWheelView_Month;
//    private WheelView mWheelView_Day;
//    private AlertDialog mProduceDateDialog;
//    private View mTimePickerView;
//    int mClickedItemPosition;
//    View mKeyboardView;
//
//    Map<Integer,Integer> map = new HashMap();
//    private Handler m_Handler = new Handler() {
//        @Override
//        public void handleMessage(Message msg) {
//            super.handleMessage(msg);
//            switch (msg.what) {
//                case 0:
//                    InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
//                    imm.toggleSoftInput(0, InputMethodManager.HIDE_NOT_ALWAYS);
//                    break;
//                case 1:
//                    if (dialog != null) {
//                        dialog.cancel();
//                    }
//                    String RetVal = "";
//                    if (msg.obj == null) {
//                        Toast.makeText(CommoditySelectorActivity.this, "商品选择器记录保存到单据表失败（" + RetVal + "）", Toast.LENGTH_SHORT).show();
//                    } else {
//                        RetVal = msg.obj.toString();
//                        if (RetVal == null || FncCommon.FUN_ABORTION(RetVal).equals("执行失败")) {
//                            Toast.makeText(CommoditySelectorActivity.this, "商品选择器记录保存到单据表失败（" + RetVal + "）", Toast.LENGTH_SHORT).show();
//                        } else {
//                            Toast.makeText(CommoditySelectorActivity.this, "插入到销售表", Toast.LENGTH_SHORT).show();
//                            Intent intent = new Intent();
//                            intent.putExtra("FROM_FORM", FROM_FORM);
//                            intent.setClass(CommoditySelectorActivity.this, MainActivity.class);
//                            startActivity(intent);
//                            finish();
//                        }
//                    }
//                    break;
//                case 2:
//                    if (dialog != null) {
//                        dialog.cancel();
//                    }
//                    String RetVal2 = "";
//                    if (msg.obj == null) {
//                        Toast.makeText(CommoditySelectorActivity.this, "商品选择器记录保存到单据表失败（" + RetVal2 + "）", Toast.LENGTH_SHORT).show();
//                    } else {
//                        RetVal2 = msg.obj.toString();
//                        if (RetVal2 == null || FncCommon.FUN_ABORTION(RetVal2).equals("执行失败")) {
//                            Toast.makeText(CommoditySelectorActivity.this, "商品选择器记录保存到单据表失败（" + RetVal2 + "）", Toast.LENGTH_SHORT).show();
//                        } else {
//                            Toast.makeText(CommoditySelectorActivity.this, "插入到退货表", Toast.LENGTH_SHORT).show();
//                            Intent intent = new Intent();
//                            intent.putExtra("FROM_FORM", FROM_FORM);
//                            intent.setClass(CommoditySelectorActivity.this, MainActivity.class);
//                            startActivity(intent);
//                            finish();
//                        }
//                    }
//                    break;
//            }
//        }
//    };
//
//
//    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//        requestWindowFeature(Window.FEATURE_NO_TITLE);
//        super.onCreate(savedInstanceState);
//
//        setContentView(R.layout.activity_commodity_selector);
//        mContext = this;
//        initView();
//        initEvent();
//
//        FragmentManager fragmentManager = getFragmentManager();
//        FragmentTransaction beginTransaction = fragmentManager.beginTransaction();
//        beginTransaction.replace(R.id.replaceId, new FragmentTxtLeft());
//        beginTransaction.commit();
//    }
//
//    /**
//     * 通过商品种类获取商品名称和商品ID，然后传递到商品的listView显示
//     */
//    public void setData(Date refTime) {
//        refreshTime = refTime;
//        myAdapter = new MyAdapter(this, refreshTime, listSelectedCmdtyId, listSelectedCmdtyName, listSelectedCmdtyInfor);
//        listViewRight.setAdapter(myAdapter);
//    }
//
//    public void setTextKindNAME(final String levelNo, String kindNAME) {
//        int leNo;
//        if (!levelNo.equals("")) {
//            leNo = Integer.parseInt(levelNo);
//            if (leNo > 1) {
//                if (leNo == 2) {
//                    textView2.setText(kindNAME);
//                    textView2.setVisibility(View.VISIBLE);
//                    imageView2.setVisibility(View.VISIBLE);
//                    textView2.setOnClickListener(new View.OnClickListener() {
//
//                        @Override
//                        public void onClick(View v) {
//                            str = textView2.getText().toString().trim();
//
//                            Message message = new Message();
//                            message.obj = str;
//                            message.what = 2;
//                            mHandler.sendMessage(message);
//
//                            textView3.setVisibility(View.GONE);
//                            imageView3.setVisibility(View.GONE);
//                            textView4.setVisibility(View.GONE);
//                            imageView4.setVisibility(View.GONE);
//                            textView5.setVisibility(View.GONE);
//                            imageView5.setVisibility(View.GONE);
//                            textView6.setVisibility(View.GONE);
//                            imageView6.setVisibility(View.GONE);
//                            textView7.setVisibility(View.GONE);
//                            imageView7.setVisibility(View.GONE);
//                            textView8.setVisibility(View.GONE);
//                            imageView8.setVisibility(View.GONE);
//                            textView9.setVisibility(View.GONE);
//                            imageView9.setVisibility(View.GONE);
//                            textView10.setVisibility(View.GONE);
//                            imageView10.setVisibility(View.GONE);
//                            textView11.setVisibility(View.GONE);
//                            imageView11.setVisibility(View.GONE);
//                        }
//                    });
//                } else if (leNo == 3) {
//                    textView3.setText(kindNAME);
//                    textView3.setVisibility(View.VISIBLE);
//                    imageView3.setVisibility(View.VISIBLE);
//                    textView3.setOnClickListener(new View.OnClickListener() {
//                        @Override
//                        public void onClick(View v) {
//                            str = textView3.getText().toString().trim();
//                            Message message = new Message();
//                            message.obj = str;
//                            message.what = 3;
//                            mHandler.sendMessage(message);
//
//                            textView4.setVisibility(View.GONE);
//                            imageView4.setVisibility(View.GONE);
//                            textView5.setVisibility(View.GONE);
//                            imageView5.setVisibility(View.GONE);
//                            textView6.setVisibility(View.GONE);
//                            imageView6.setVisibility(View.GONE);
//                            textView7.setVisibility(View.GONE);
//                            imageView7.setVisibility(View.GONE);
//                            textView8.setVisibility(View.GONE);
//                            imageView8.setVisibility(View.GONE);
//                            textView9.setVisibility(View.GONE);
//                            imageView9.setVisibility(View.GONE);
//                            textView10.setVisibility(View.GONE);
//                            imageView10.setVisibility(View.GONE);
//                            textView11.setVisibility(View.GONE);
//                            imageView11.setVisibility(View.GONE);
//                        }
//                    });
//                } else if (leNo == 4) {
//                    textView4.setText(kindNAME);
//                    textView4.setVisibility(View.VISIBLE);
//                    imageView4.setVisibility(View.VISIBLE);
//                    textView4.setOnClickListener(new View.OnClickListener() {
//                        @Override
//                        public void onClick(View v) {
//
//                            str = textView4.getText().toString().trim();
//                            Message message = new Message();
//                            message.obj = str;
//                            message.what = 4;
//                            mHandler.sendMessage(message);
//
//                            textView5.setVisibility(View.GONE);
//                            imageView5.setVisibility(View.GONE);
//                            textView6.setVisibility(View.GONE);
//                            imageView6.setVisibility(View.GONE);
//                            textView7.setVisibility(View.GONE);
//                            imageView7.setVisibility(View.GONE);
//                            textView8.setVisibility(View.GONE);
//                            imageView8.setVisibility(View.GONE);
//                            textView9.setVisibility(View.GONE);
//                            imageView9.setVisibility(View.GONE);
//                            textView10.setVisibility(View.GONE);
//                            imageView10.setVisibility(View.GONE);
//                            textView11.setVisibility(View.GONE);
//                            imageView11.setVisibility(View.GONE);
//                        }
//                    });
//                } else if (leNo == 5) {
//                    textView5.setText(kindNAME);
//                    textView5.setVisibility(View.VISIBLE);
//                    imageView5.setVisibility(View.VISIBLE);
//                    textView5.setOnClickListener(new View.OnClickListener() {
//                        @Override
//                        public void onClick(View v) {
//
//                            str = textView5.getText().toString().trim();
//                            Message message = new Message();
//                            message.obj = str;
//                            message.what = 5;
//                            mHandler.sendMessage(message);
//
//                            textView6.setVisibility(View.GONE);
//                            imageView6.setVisibility(View.GONE);
//                            textView7.setVisibility(View.GONE);
//                            imageView7.setVisibility(View.GONE);
//                            textView8.setVisibility(View.GONE);
//                            imageView8.setVisibility(View.GONE);
//                            textView9.setVisibility(View.GONE);
//                            imageView9.setVisibility(View.GONE);
//                            textView10.setVisibility(View.GONE);
//                            imageView10.setVisibility(View.GONE);
//                            textView11.setVisibility(View.GONE);
//                            imageView11.setVisibility(View.GONE);
//                        }
//                    });
//                } else if (leNo == 6) {
//                    textView6.setText(kindNAME);
//                    textView6.setVisibility(View.VISIBLE);
//                    imageView6.setVisibility(View.VISIBLE);
//                    textView6.setOnClickListener(new View.OnClickListener() {
//                        @Override
//                        public void onClick(View v) {
//
//                            str = textView6.getText().toString().trim();
//                            Message message = new Message();
//                            message.obj = str;
//                            message.what = 6;
//                            mHandler.sendMessage(message);
//
//                            textView7.setVisibility(View.GONE);
//                            imageView7.setVisibility(View.GONE);
//                            textView8.setVisibility(View.GONE);
//                            imageView8.setVisibility(View.GONE);
//                            textView9.setVisibility(View.GONE);
//                            imageView9.setVisibility(View.GONE);
//                            textView10.setVisibility(View.GONE);
//                            imageView10.setVisibility(View.GONE);
//                            textView11.setVisibility(View.GONE);
//                            imageView11.setVisibility(View.GONE);
//                        }
//                    });
//                } else if (leNo == 7) {
//                    textView7.setText(kindNAME);
//                    textView7.setVisibility(View.VISIBLE);
//                    imageView7.setVisibility(View.VISIBLE);
//                    textView7.setOnClickListener(new View.OnClickListener() {
//                        @Override
//                        public void onClick(View v) {
//
//                            str = textView7.getText().toString().trim();
//                            Message message = new Message();
//                            message.obj = str;
//                            message.what = 7;
//                            mHandler.sendMessage(message);
//
//                            textView8.setVisibility(View.GONE);
//                            imageView8.setVisibility(View.GONE);
//                            textView9.setVisibility(View.GONE);
//                            imageView9.setVisibility(View.GONE);
//                            textView10.setVisibility(View.GONE);
//                            imageView10.setVisibility(View.GONE);
//                            textView11.setVisibility(View.GONE);
//                            imageView11.setVisibility(View.GONE);
//                        }
//                    });
//                } else if (leNo == 8) {
//                    textView8.setText(kindNAME);
//                    textView8.setVisibility(View.VISIBLE);
//                    imageView8.setVisibility(View.VISIBLE);
//                    textView8.setOnClickListener(new View.OnClickListener() {
//                        @Override
//                        public void onClick(View v) {
//
//                            str = textView8.getText().toString().trim();
//                            Message message = new Message();
//                            message.obj = str;
//                            message.what = 8;
//                            mHandler.sendMessage(message);
//
//                            textView9.setVisibility(View.GONE);
//                            imageView9.setVisibility(View.GONE);
//                            textView10.setVisibility(View.GONE);
//                            imageView10.setVisibility(View.GONE);
//                            textView11.setVisibility(View.GONE);
//                            imageView11.setVisibility(View.GONE);
//                        }
//                    });
//                } else if (leNo == 9) {
//                    textView9.setText(kindNAME);
//                    textView9.setVisibility(View.VISIBLE);
//                    imageView9.setVisibility(View.VISIBLE);
//                    textView9.setOnClickListener(new View.OnClickListener() {
//                        @Override
//                        public void onClick(View v) {
//
//                            str = textView9.getText().toString().trim();
//                            Message message = new Message();
//                            message.obj = str;
//                            message.what = 9;
//                            mHandler.sendMessage(message);
//
//                            textView10.setVisibility(View.GONE);
//                            imageView10.setVisibility(View.GONE);
//                            textView11.setVisibility(View.GONE);
//                            imageView11.setVisibility(View.GONE);
//                        }
//                    });
//                } else if (leNo == 10) {
//                    textView10.setText(kindNAME);
//                    textView10.setVisibility(View.VISIBLE);
//                    imageView10.setVisibility(View.VISIBLE);
//                    textView10.setOnClickListener(new View.OnClickListener() {
//                        @Override
//                        public void onClick(View v) {
//
//                            str = textView10.getText().toString().trim();
//                            Message message = new Message();
//                            message.obj = str;
//                            message.what = 10;
//                            mHandler.sendMessage(message);
//
//                            textView11.setVisibility(View.GONE);
//                            imageView11.setVisibility(View.GONE);
//                        }
//                    });
//                } else if (leNo == 11) {
//                    textView11.setText(kindNAME);
//                    imageView11.setVisibility(View.VISIBLE);
//                }
//            }
//        }
//
//    }
//
//
//    public void initView() {
//
//        back = (LinearLayout) findViewById(R.id.activity_commodity_selector_back);
//
//
//        listViewRight = (ListView) findViewById(R.id.fragment_right);
//
//        mTextView_TextLeft = (TextView) findViewById(R.id.id_tv_quantity_text_left);
//        mTextView_TextRight = (TextView) findViewById(R.id.id_tv_quantity_text_right);
//        textView1 = (TextView) findViewById(R.id.selector_HS_LL_TV_one);
//        textView2 = (TextView) findViewById(R.id.selector_HS_LL_TV_two);
//        textView3 = (TextView) findViewById(R.id.selector_HS_LL_TV_three);
//        textView4 = (TextView) findViewById(R.id.selector_HS_LL_TV_four);
//        textView5 = (TextView) findViewById(R.id.selector_HS_LL_TV_fif);
//        textView6 = (TextView) findViewById(R.id.selector_HS_LL_TV_six);
//        textView7 = (TextView) findViewById(R.id.selector_HS_LL_TV_seven);
//        textView8 = (TextView) findViewById(R.id.selector_HS_LL_TV_eight);
//        textView9 = (TextView) findViewById(R.id.selector_HS_LL_TV_nine);
//        textView10 = (TextView) findViewById(R.id.selector_HS_LL_TV_ten);
//        textView11 = (TextView) findViewById(R.id.selector_HS_LL_TV_eleven);
//
//        imageView2 = (ImageView) findViewById(R.id.selector_HS_LL_IV_two);
//        imageView3 = (ImageView) findViewById(R.id.selector_HS_LL_IV_three);
//        imageView4 = (ImageView) findViewById(R.id.selector_HS_LL_IV_four);
//        imageView5 = (ImageView) findViewById(R.id.selector_HS_LL_IV_fif);
//        imageView6 = (ImageView) findViewById(R.id.selector_HS_LL_IV_six);
//        imageView7 = (ImageView) findViewById(R.id.selector_HS_LL_IV_seven);
//        imageView8 = (ImageView) findViewById(R.id.selector_HS_LL_IV_eight);
//        imageView9 = (ImageView) findViewById(R.id.selector_HS_LL_IV_nine);
//        imageView10 = (ImageView) findViewById(R.id.selector_HS_LL_IV_ten);
//        imageView11 = (ImageView) findViewById(R.id.selector_HS_LL_IV_eleven);
//
//        submit = (Button) findViewById(R.id.selector_sure);
//    }
//
//
//    public void initEvent() {
//        textView1.setOnClickListener(this);
//        textView2.setOnClickListener(this);
//        textView3.setOnClickListener(this);
//        textView4.setOnClickListener(this);
//        textView5.setOnClickListener(this);
//        textView6.setOnClickListener(this);
//        textView7.setOnClickListener(this);
//        textView8.setOnClickListener(this);
//        textView9.setOnClickListener(this);
//        textView10.setOnClickListener(this);
//        textView11.setOnClickListener(this);
//        back.setOnClickListener(this);
//        submit.setOnClickListener(this);
//
//
//    }
//
//    public void setHandler(Handler handler) {
//        mHandler = handler;
//    }
//
//    @Override
//    public void onClick(View v) {
//        String numLeft = "";
//        String numRight = "";
//        if (mEditText_InputBoxLeft != null){
//            numLeft = mEditText_InputBoxLeft.getText().toString().trim();
//        }
//        if (mEditText_InputBoxRight != null){
//            numRight = mEditText_InputBoxRight.getText().toString().trim();
//        }
//        switch (v.getId()) {
//
//            case R.id.id_lay_keyboard_quantity_number_0:
//                setNumberToInputBox(numLeft, numRight, "0");
//                break;
//            case R.id.id_lay_keyboard_quantity_number_1:
//                setNumberToInputBox(numLeft, numRight, "1");
//                break;
//            case R.id.id_lay_keyboard_quantity_number_2:
//                setNumberToInputBox(numLeft, numRight, "2");
//                break;
//            case R.id.id_lay_keyboard_quantity_number_3:
//                setNumberToInputBox(numLeft, numRight, "3");
//                break;
//            case R.id.id_lay_keyboard_quantity_number_4:
//                setNumberToInputBox(numLeft, numRight, "4");
//                break;
//            case R.id.id_lay_keyboard_quantity_number_5:
//                setNumberToInputBox(numLeft, numRight, "5");
//                break;
//            case R.id.id_lay_keyboard_quantity_number_6:
//                setNumberToInputBox(numLeft, numRight, "6");
//                break;
//            case R.id.id_lay_keyboard_quantity_number_7:
//                setNumberToInputBox(numLeft, numRight, "7");
//                break;
//            case R.id.id_lay_keyboard_quantity_number_8:
//                setNumberToInputBox(numLeft, numRight, "8");
//                break;
//            case R.id.id_lay_keyboard_quantity_number_9:
//                setNumberToInputBox(numLeft, numRight, "9");
//                break;
//            case R.id.id_lay_keyboard_quantity_cancel:
//                dismissDialog();
//                break;
//
//            case R.id.id_lay_keyboard_quantity_confirm:
//                processNumberLeft(numLeft);
//                processNumberRight(numRight);
//                dismissDialog();
//                break;
//            case R.id.id_tv_time_picker_cancel:
//                mProduceDateDialog.dismiss();
//                break;
//
//            case R.id.id_tv_time_picker_confirm:
//                String day = mWheelView_Day.getSelectedText();
//                String month = mWheelView_Month.getSelectedText();
//                if (month.length() == 1){//如果不足两位数，则补零
//                    month = "0" + month;
//                }
//                if (day.length() == 1){//如果不足两位数，则补零
//                    day = "0" + day;
//                }
//                String year = mWheelView_Year.getSelectedText();
//                String date = year +"-"+ month +"-"+ day;
////                SELECTOR.listPRODUCTION_DT.set(mClickedItemPosition, date);
//                myAdapter.notifyDataSetChanged();
//                mProduceDateDialog.dismiss();
//                break;
//            case R.id.id_lay_keyboard_quantity_backspace:
//                if (mEditText_InputBoxLeft.isFocused()) {
//                    if (numLeft.length() > 0) {
//                        numLeft = numLeft.substring(0, numLeft.length() - 1);
//                        mEditText_InputBoxLeft.setText(numLeft);
//                        mEditText_InputBoxLeft.setSelection(numLeft.length());
//                    }
//                } else if (mEditText_InputBoxRight.isFocused()) {
//                    if (numRight.length() > 0) {
//                        numRight = numRight.substring(0, numRight.length() - 1);
//                        mEditText_InputBoxRight.setText(numRight);
//                        mEditText_InputBoxRight.setSelection(numRight.length());
//                    }
//                }
//                break;
//            case R.id.id_lay_keyboard_quantity_dot:
//                if (!numLeft.contains(".")) {
//                    if (numLeft.length() > 0) {
//                        setNumberToInputBox(numLeft, numRight, ".");
//                    }
//                }
//                if (!numRight.contains(".")) {
//                    if (numRight.length() > 0) {
//                        setNumberToInputBox(numLeft, numRight, ".");
//                    }
//                }
//                break;
//            case R.id.id_iv_input_box_clear_left:
//                mEditText_InputBoxLeft.setText("");
//                mImageView_TextClearLeft.setVisibility(View.GONE);
//                break;
//            case R.id.id_iv_input_box_clear_right:
//                mEditText_InputBoxRight.setText("");
//                mImageView_TextClearRight.setVisibility(View.GONE);
//                break;
//            case R.id.activity_commodity_selector_back:
//                finish();
//                break;
//            case R.id.selector_HS_LL_TV_one:
//                Message message = new Message();
//                message.what = 1;
//                mHandler.sendMessage(message);
//                textView2.setVisibility(View.GONE);
//                imageView2.setVisibility(View.GONE);
//                textView3.setVisibility(View.GONE);
//                imageView3.setVisibility(View.GONE);
//                textView4.setVisibility(View.GONE);
//                imageView4.setVisibility(View.GONE);
//                textView5.setVisibility(View.GONE);
//                imageView5.setVisibility(View.GONE);
//                textView6.setVisibility(View.GONE);
//                imageView6.setVisibility(View.GONE);
//                textView7.setVisibility(View.GONE);
//                imageView7.setVisibility(View.GONE);
//                textView8.setVisibility(View.GONE);
//                imageView8.setVisibility(View.GONE);
//                textView9.setVisibility(View.GONE);
//                imageView9.setVisibility(View.GONE);
//                textView10.setVisibility(View.GONE);
//                imageView10.setVisibility(View.GONE);
//                textView11.setVisibility(View.GONE);
//                imageView11.setVisibility(View.GONE);
//                break;
//            case R.id.selector_sure:
//                dialog = new XKPrograssDialog(this, R.style.Theme_dialog);
//                dialog.setDialogText("保存");
//                dialog.show();
//
////                //先将输入了信息的商品插入到商品选择品日志表中
////                RetVal = SELECTOR.saveCmdtyListMapToTableSelector(CommoditySelectorActivity.this);
////                if (FncCommon.FUN_ABORTION(RetVal).equals("执行失败")) {
////                    Toast.makeText(this,"保存商品选择器表失败（"+RetVal+"）",Toast.LENGTH_SHORT).show();
////                    return;
////                }
//                //标记从何而来
//                FROM_FORM = getIntent().getStringExtra("FROM_FORM");
//                if (FROM_FORM.equals("销售")) {
//                    //保存到销售表
//                    new Thread(saveSalesRunnable).start();
//                } else if (FROM_FORM.equals("退货")) {
//                    //保存到退货表
//                    new Thread(saveReturnRunnable).start();
//                } else if (FROM_FORM.equals("调拨")) {
//
//                } else if (FROM_FORM.equals("采购")) {
//
//                }
//
//                break;
//            default:
//                break;
//        }
//
//
//    }
//
//
//    private Runnable saveSalesRunnable = new Runnable() {
//        @Override
//        public void run() {
//            Message message = new Message();
//            message.what = 1;
////            message.obj = SALES.SelectorToTrans(CommoditySelectorActivity.this);
//            m_Handler.sendMessage(message);
//        }
//    };
//    private Runnable saveReturnRunnable = new Runnable() {
//        @Override
//        public void run() {
//            Message message = new Message();
//            message.what = 2;
////            message.obj = RETURN.SelectorToTrans(CommoditySelectorActivity.this);
//            m_Handler.sendMessage(message);
//        }
//    };
//
//    @Override
//    public boolean onTouch(View v, MotionEvent event) {
////        if (event.getAction() == MotionEvent.ACTION_DOWN) {
////            String numLeft = mEditText_InputBoxLeft.getText().toString().trim();
////            String numRight = mEditText_InputBoxRight.getText().toString().trim();
////            if (v.getId() == R.id.id_et_quantity_input_box_left) {
////                mEditText_InputBoxLeft.setSelection(0, numLeft.length());
////            } else if (v.getId() == R.id.id_et_quantity_input_box_right) {
////                mEditText_InputBoxRight.setSelection(0, numRight.length());
////            }
////        }
//        return false;
//    }
//
//
//    private class saveSelectorRunnable implements Runnable {
//
//        private String CMDTY_ID;
//        private String NONRETAIL_COUNT;
//        private String RETAIL_COUNT;
//        private String BOX_PRICE;
//        private String PRICE;
//        private String PRODUCTION_DT;
//        private Context context;
//
//        public saveSelectorRunnable(String CMDTY_ID, String NONRETAIL_COUNT, String RETAIL_COUNT,
//                                    String BOX_PRICE, String PRICE,
//                                    String PRODUCTION_DT, Context context) {
//            this.CMDTY_ID = CMDTY_ID;
//            this.NONRETAIL_COUNT = NONRETAIL_COUNT;
//            this.RETAIL_COUNT = RETAIL_COUNT;
//            this.BOX_PRICE = BOX_PRICE;
//            this.PRICE = PRICE;
//            this.PRODUCTION_DT = PRODUCTION_DT;
//            this.context = context;
//        }
//
//        @Override
//        public void run() {
//            //从结构体中更新商品的静态链表中指定的商品信息
//            SELECTOR.UpdStaticListFromStruct(CMDTY_ID, NONRETAIL_COUNT, RETAIL_COUNT, BOX_PRICE, PRICE, PRODUCTION_DT, context);
//            //商品插入到商品选择品日志表中
//            SELECTOR.saveCmdtyListMapToTableSelector(context);
//        }
//    }
//
//    /**
//     * 商品适配器
//     */
//    class MyAdapter extends BaseAdapter {
//
//        private Context context;
//        private Date refreshTime;  //刷屏时间
//
//        private List<String> listSelectedCmdtyId;
//        private List<String> listSelectedCmdtyName;
//        private List<STR_SELECTCMDTY> listSelectedCmdtyInfor;
//
//        public MyAdapter(Context context, Date refTime, List<String> listSelectedCmdtyId,
//                         List<String> listSelectedCmdtyName, List<STR_SELECTCMDTY> listSelectedCmdtyInfor) {
//            this.context = context;
//            this.refreshTime = refTime;
//
//            this.listSelectedCmdtyId = listSelectedCmdtyId;
//            this.listSelectedCmdtyName = listSelectedCmdtyName;
//            this.listSelectedCmdtyInfor = listSelectedCmdtyInfor;
//        }
//
//        @Override
//        public int getCount() {
//            return SELECTOR.listCMDTY_NAME.size();
//        }
//
//        @Override
//        public String getItem(int position) {
//            return SELECTOR.listCMDTY_NAME.get(position);
//        }
//
//        @Override
//        public long getItemId(int position) {
//            return position;
//        }
//
//        @Override
//        public View getView(final int position, View convertView, ViewGroup parent) {
//            final ViewHolder holder;
//            if (convertView == null) {
//                holder = new ViewHolder();
//                convertView = LayoutInflater.from(context).inflate(R.layout.item_selector_listviewright, null);
//                holder.cmdtyName = (TextView) convertView.findViewById(R.id.item_selector_listViewRight_LL_TV);
//                holder.textViewCmdtyId = (TextView) convertView.findViewById(R.id.item_selector_listViewRight_LL_TV_CMDTY_ID);
//                holder.textViewNonRetailUnit = (TextView) convertView.findViewById(R.id.item_selector_listViewRight_LL_TV_NonRetailUnit);
//                holder.textViewRetailUnit = (TextView) convertView.findViewById(R.id.item_selector_listViewRight_LL_TV_RetailUnit);
//
//                holder.textViewNonRetailNum = (TextView) convertView.findViewById(R.id.item_selector_listViewRight_LL_ET_NonRetailCount);
//                holder.textViewRetailNum = (TextView) convertView.findViewById(R.id.item_selector_listViewRight_LL_TV_RetailCount);
//                holder.textViewBox_Price = (TextView) convertView.findViewById(R.id.item_selector_listViewRight_LL_TV_BOX_PRICE);
//                holder.textViewPrice = (TextView) convertView.findViewById(R.id.item_selector_listViewRight_LL_TV_PRICE);
//                holder.textViewProduction_Dt = (TextView) convertView.findViewById(R.id.item_selector_listViewRight_LL_TV_PRODUCTION_DT);
//
//                convertView.setTag(holder);
//            } else {
//                holder = (ViewHolder) convertView.getTag();
//            }
//
//            //界面显示（初始化）
//            holder.cmdtyName.setText(SELECTOR.listCMDTY_NAME.get(position));
//            holder.textViewCmdtyId.setText(SELECTOR.listCMDTY_ID.get(position));
//            holder.textViewNonRetailUnit.setText(SELECTOR.listNONRETAIL_UNIT.get(position));
//            holder.textViewRetailUnit.setText(SELECTOR.listRETAIL_UNIT.get(position));
//            holder.textViewNonRetailNum.setText(SELECTOR.listNONRETAIL_COUNT.get(position));
//            holder.textViewRetailNum.setText(SELECTOR.listRETAIL_COUNT.get(position));
//            holder.textViewBox_Price.setText(SELECTOR.listBOX_PRICE.get(position));
//            holder.textViewPrice.setText(SELECTOR.listPRICE.get(position));
//            holder.textViewProduction_Dt.setText(SELECTOR.listPRODUCTION_DT.get(position));
//
//            if (FncCommon.FUN_CHECK_WHAT(SELECTOR.listCMDTY_ID.get(position)).equals("促销包ID")) {
//                holder.textViewRetailNum.setVisibility(View.GONE);
//                holder.textViewRetailUnit.setVisibility(View.GONE);
//            } else {
//                holder.textViewRetailNum.setVisibility(View.VISIBLE);
//                holder.textViewRetailUnit.setVisibility(View.VISIBLE);
//            }
//
//            System.out.println("----------->上次刷屏时间：" + refreshTime.toString() + " 当前时间：" + new Date().toString());
//
//            //获取当前行的商品名称和商品ID
//            String NONRETAIL_COUNT = holder.textViewNonRetailNum.getText().toString().trim();
//            String RETAIL_COUNT = holder.textViewRetailNum.getText().toString().trim();
//
//            //如果商品ID存在于连表中，则表示它是已经被选中的。
////            if (!"".equals(NONRETAIL_COUNT.trim()) || !"".equals(RETAIL_COUNT.trim())) {
////                holder.cmdtyName.setTextColor(Color.parseColor("#f44336"));
////            } else {
////                holder.cmdtyName.setTextColor(Color.parseColor("#424242"));
////            }
//
//            //大数量点击监听事件
//            holder.textViewNonRetailNum.setOnClickListener(new View.OnClickListener() {
//
//                @Override
//                public void onClick(View v) {
//                    quantityKeyboardDialog(position);
//
//                    //获取当前行上的商品信息
//                    String CMDTY_ID = holder.textViewCmdtyId.getText().toString().trim();
//                    String NONRETAIL_COUNT = holder.textViewNonRetailNum.getText().toString().trim();
//                    String RETAIL_COUNT = holder.textViewRetailNum.getText().toString().trim();
//                    String BOX_PRICE = holder.textViewBox_Price.getText().toString().trim();
//                    String PRICE = holder.textViewPrice.getText().toString().trim();
//                    String PRODUCTION_DT = holder.textViewProduction_Dt.getText().toString().trim();
//
//                    new Thread(new saveSelectorRunnable(CMDTY_ID, NONRETAIL_COUNT, RETAIL_COUNT, BOX_PRICE, PRICE, PRODUCTION_DT, context)).start();
//
////                        //从结构体中更新商品的静态链表中指定的商品信息
////                        SELECTOR.UpdStaticListFromStruct(CMDTY_ID, NONRETAIL_COUNT, RETAIL_COUNT, BOX_PRICE, PRICE, PRODUCTION_DT, context);
////                        //商品插入到商品选择品日志表中
////                        SELECTOR.saveCmdtyListMapToTableSelector(context);
//
//                }
//            });
//            //小数量点击监听事件
//            holder.textViewRetailNum.setOnClickListener(new View.OnClickListener() {
//                @Override
//                public void onClick(View v) {
//                    quantityKeyboardDialog(position);
//
//                    //获取当前行上的商品信息
//                    String CMDTY_ID = holder.textViewCmdtyId.getText().toString().trim();
//                    String NONRETAIL_COUNT = holder.textViewNonRetailNum.getText().toString().trim();
//                    String RETAIL_COUNT = holder.textViewRetailNum.getText().toString().trim();
//                    String BOX_PRICE = holder.textViewBox_Price.getText().toString().trim();
//                    String PRICE = holder.textViewPrice.getText().toString().trim();
//                    String PRODUCTION_DT = holder.textViewProduction_Dt.getText().toString().trim();
//                    new Thread(new saveSelectorRunnable(CMDTY_ID, NONRETAIL_COUNT, RETAIL_COUNT, BOX_PRICE, PRICE, PRODUCTION_DT, context)).start();
////                        //从结构体中更新商品的静态链表中指定的商品信息
////                        SELECTOR.UpdStaticListFromStruct(CMDTY_ID, NONRETAIL_COUNT, RETAIL_COUNT, BOX_PRICE, PRICE, PRODUCTION_DT, context);
////                        //商品插入到商品选择品日志表中
////                        SELECTOR.saveCmdtyListMapToTableSelector(context);
//                }
//            });
//            //箱价点击监听事件
//            holder.textViewBox_Price.setOnClickListener(new View.OnClickListener() {
//                @Override
//                public void onClick(View v) {
//                    priceKeyboardDialog(position);
//
//                    //获取当前行上的商品信息
//                    String CMDTY_ID = holder.textViewCmdtyId.getText().toString().trim();
//                    String NONRETAIL_COUNT = holder.textViewNonRetailNum.getText().toString().trim();
//                    String RETAIL_COUNT = holder.textViewRetailNum.getText().toString().trim();
//                    String BOX_PRICE = holder.textViewBox_Price.getText().toString().trim();
//                    String PRICE = holder.textViewPrice.getText().toString().trim();
//                    String PRODUCTION_DT = holder.textViewProduction_Dt.getText().toString().trim();
//                    new Thread(new saveSelectorRunnable(CMDTY_ID, NONRETAIL_COUNT, RETAIL_COUNT, BOX_PRICE, PRICE, PRODUCTION_DT, context)).start();
////                        //从结构体中更新商品的静态链表中指定的商品信息
////                        SELECTOR.UpdStaticListFromStruct(CMDTY_ID, NONRETAIL_COUNT, RETAIL_COUNT, BOX_PRICE, PRICE, PRODUCTION_DT, context);
////                        //商品插入到商品选择品日志表中
////                        SELECTOR.saveCmdtyListMapToTableSelector(context);
//                }
//            });
//            //单价点击监听事件
//            holder.textViewPrice.setOnClickListener(new View.OnClickListener() {
//                @Override
//                public void onClick(View v) {
//                    priceKeyboardDialog(position);
//
//                    //获取当前行上的商品信息
//                    String CMDTY_ID = holder.textViewCmdtyId.getText().toString().trim();
//                    String NONRETAIL_COUNT = holder.textViewNonRetailNum.getText().toString().trim();
//                    String RETAIL_COUNT = holder.textViewRetailNum.getText().toString().trim();
//                    String BOX_PRICE = holder.textViewBox_Price.getText().toString().trim();
//                    String PRICE = holder.textViewPrice.getText().toString().trim();
//                    String PRODUCTION_DT = holder.textViewProduction_Dt.getText().toString().trim();
//                    new Thread(new saveSelectorRunnable(CMDTY_ID, NONRETAIL_COUNT, RETAIL_COUNT, BOX_PRICE, PRICE, PRODUCTION_DT, context)).start();
////                        //从结构体中更新商品的静态链表中指定的商品信息
////                        SELECTOR.UpdStaticListFromStruct(CMDTY_ID, NONRETAIL_COUNT, RETAIL_COUNT, BOX_PRICE, PRICE, PRODUCTION_DT, context);
////                        //商品插入到商品选择品日志表中
////                        SELECTOR.saveCmdtyListMapToTableSelector(context);
//                }
//            });
//
//            //生产日期焦点监听事件
//            holder.textViewProduction_Dt.setOnClickListener(new View.OnClickListener() {
//                @Override
//                public void onClick(View v) {
//                    processProduceDateDialog(position);
//                    //获取当前行上的商品信息
//                    String CMDTY_ID = holder.textViewCmdtyId.getText().toString().trim();
//                    String NONRETAIL_COUNT = holder.textViewNonRetailNum.getText().toString().trim();
//                    String RETAIL_COUNT = holder.textViewRetailNum.getText().toString().trim();
//                    String BOX_PRICE = holder.textViewBox_Price.getText().toString().trim();
//                    String PRICE = holder.textViewPrice.getText().toString().trim();
//                    String PRODUCTION_DT = holder.textViewProduction_Dt.getText().toString().trim();
//                    new Thread(new saveSelectorRunnable(CMDTY_ID, NONRETAIL_COUNT, RETAIL_COUNT, BOX_PRICE, PRICE, PRODUCTION_DT, context)).start();
////                        //从结构体中更新商品的静态链表中指定的商品信息
////                        SELECTOR.UpdStaticListFromStruct(CMDTY_ID, NONRETAIL_COUNT, RETAIL_COUNT, BOX_PRICE, PRICE, PRODUCTION_DT, context);
////                        //商品插入到商品选择品日志表中
////                        SELECTOR.saveCmdtyListMapToTableSelector(context);
//                }
//            });
//
//            return convertView;
//        }
//
//        public class ViewHolder {
//            TextView textViewNonRetailNum;//大数量
//            TextView textViewRetailNum;//小数量
//            TextView textViewBox_Price;//箱价
//            TextView textViewPrice;//单价
//            TextView textViewProduction_Dt;//生产日期
//            TextView cmdtyName;//商品名称
//            TextView textViewNonRetailUnit;//大数量名称
//            TextView textViewRetailUnit;//小数量名称
//            TextView textViewCmdtyId;//商品ID
//
//
//        }
//    }
//
//    /**
//     * 日期选择Dialog
//     *
//     * @param position
//     */
//    private void processProduceDateDialog(int position) {
//
//        mClickedItemPosition = position;
//        mTimePickerView = LayoutInflater.from(mContext).inflate(R.layout.layout_time_picker_layout, null);
//
//        TextView mTextView_Cancel = (TextView) mTimePickerView.findViewById(R.id.id_tv_time_picker_cancel);
//        TextView mTextView_Confirm = (TextView) mTimePickerView.findViewById(R.id.id_tv_time_picker_confirm);
//        mTextView_Cancel.setOnClickListener(this);
//        mTextView_Confirm.setOnClickListener(this);
//
//        mWheelView_Year = (WheelView) mTimePickerView.findViewById(R.id.year);
//        mWheelView_Month = (WheelView) mTimePickerView.findViewById(R.id.month);
//        mWheelView_Day = (WheelView) mTimePickerView.findViewById(R.id.day);
//
//        mProduceDateDialog = new AlertDialog.Builder(mContext, R.style.dialog1).create();
//        mProduceDateDialog.show();
//        mProduceDateDialog.setContentView(mTimePickerView);
//        WindowManager.LayoutParams params = mProduceDateDialog.getWindow().getAttributes();
//        params.width = Util.getScreenWidth(mContext);
//        params.dimAmount = 0.3f;
//        params.gravity = Gravity.BOTTOM;
//        mProduceDateDialog.getWindow().setAttributes(params);
//
//    }
//
//    /**
//     * 价格键盘Dialog
//     *
//     * @param position
//     */
//    private void priceKeyboardDialog(int position) {
//        mClickedItemPosition = position;
//
//        mKeyboardView = LayoutInflater.from(mContext).inflate(R.layout.layout_price_keyboard, null);
//
//        mLinearLayout_NumberZero = (LinearLayout) mKeyboardView.findViewById(R.id.id_lay_keyboard_quantity_number_0);
//        mLinearLayout_NumberOne = (LinearLayout) mKeyboardView.findViewById(R.id.id_lay_keyboard_quantity_number_1);
//        mLinearLayout_NumberTwo = (LinearLayout) mKeyboardView.findViewById(R.id.id_lay_keyboard_quantity_number_2);
//        mLinearLayout_NumberThree = (LinearLayout) mKeyboardView.findViewById(R.id.id_lay_keyboard_quantity_number_3);
//        mLinearLayout_NumberFour = (LinearLayout) mKeyboardView.findViewById(R.id.id_lay_keyboard_quantity_number_4);
//        mLinearLayout_NumberFive = (LinearLayout) mKeyboardView.findViewById(R.id.id_lay_keyboard_quantity_number_5);
//        mLinearLayout_NumberSix = (LinearLayout) mKeyboardView.findViewById(R.id.id_lay_keyboard_quantity_number_6);
//        mLinearLayout_NumberSeven = (LinearLayout) mKeyboardView.findViewById(R.id.id_lay_keyboard_quantity_number_7);
//        mLinearLayout_NumberEight = (LinearLayout) mKeyboardView.findViewById(R.id.id_lay_keyboard_quantity_number_8);
//        mLinearLayout_NumberNine = (LinearLayout) mKeyboardView.findViewById(R.id.id_lay_keyboard_quantity_number_9);
//        mLinearLayout_Confirm = (LinearLayout) mKeyboardView.findViewById(R.id.id_lay_keyboard_quantity_confirm);
//        mLinearLayout_Cancel = (LinearLayout) mKeyboardView.findViewById(R.id.id_lay_keyboard_quantity_cancel);
//        mLinearLayout_Backspace = (LinearLayout) mKeyboardView.findViewById(R.id.id_lay_keyboard_quantity_backspace);
//        mLinearLayout_Dot = (LinearLayout) mKeyboardView.findViewById(R.id.id_lay_keyboard_quantity_dot);
//
//        mImageView_TextClearRight = (ImageView) mKeyboardView.findViewById(R.id.id_iv_input_box_clear_right);
//        mImageView_TextClearLeft = (ImageView) mKeyboardView.findViewById(R.id.id_iv_input_box_clear_left);
//
//        mEditText_InputBoxLeft = (EditText) mKeyboardView.findViewById(R.id.id_et_quantity_input_box_left);
//        mEditText_InputBoxRight = (EditText) mKeyboardView.findViewById(R.id.id_et_quantity_input_box_right);
//
////        mTextView_TextLeft = (TextView) mKeyboardView.findViewById(R.id.id_tv_quantity_text_left);
////        mTextView_TextRight = (TextView) mKeyboardView.findViewById(R.id.id_tv_quantity_text_right);
////
////        mTextView_TextLeft.setText(SELECTOR.listNONRETAIL_COUNT.get(position));
////        mTextView_TextRight.setText(SELECTOR.listRETAIL_COUNT.get(position));
//
//        mLinearLayout_NumberZero.setOnClickListener(this);
//        mLinearLayout_NumberOne.setOnClickListener(this);
//        mLinearLayout_NumberTwo.setOnClickListener(this);
//        mLinearLayout_NumberThree.setOnClickListener(this);
//        mLinearLayout_NumberFour.setOnClickListener(this);
//        mLinearLayout_NumberFive.setOnClickListener(this);
//        mLinearLayout_NumberSix.setOnClickListener(this);
//        mLinearLayout_NumberSeven.setOnClickListener(this);
//        mLinearLayout_NumberEight.setOnClickListener(this);
//        mLinearLayout_NumberNine.setOnClickListener(this);
//        mLinearLayout_Confirm.setOnClickListener(this);
//        mLinearLayout_Cancel.setOnClickListener(this);
//        mLinearLayout_Backspace.setOnClickListener(this);
//        mLinearLayout_Dot.setOnClickListener(this);
//
//        mImageView_TextClearLeft.setOnClickListener(this);
//        mImageView_TextClearRight.setOnClickListener(this);
//
//        mEditText_InputBoxLeft.setOnTouchListener(this);
//        mEditText_InputBoxRight.setOnTouchListener(this);
//
//        mEditText_InputBoxRight.addTextChangedListener(mTextWatcherRight);
//        mEditText_InputBoxLeft.addTextChangedListener(mTextWatcherLeft);
//
//        mPriceKeyboardDialog = new AlertDialog.Builder(mContext, R.style.dialog1).create();
//        mPriceKeyboardDialog.show();
//        mPriceKeyboardDialog.setContentView(mKeyboardView);
//        WindowManager.LayoutParams params = mPriceKeyboardDialog.getWindow().getAttributes();
//        params.width = Util.getScreenWidth(mContext);
//        params.dimAmount = 0.3f;
//        params.gravity = Gravity.BOTTOM;
//        mPriceKeyboardDialog.getWindow().setAttributes(params);
//    }
//
//    /**
//     * 数量键盘Dialog
//     *
//     * @param position
//     */
//    private void quantityKeyboardDialog(int position) {
//        mClickedItemPosition = position;
//
//        mKeyboardView = LayoutInflater.from(mContext).inflate(R.layout.layout_quantity_keyboard, null);
//        mLinearLayout_NumberZero = (LinearLayout) mKeyboardView.findViewById(R.id.id_lay_keyboard_quantity_number_0);
//        mLinearLayout_NumberOne = (LinearLayout) mKeyboardView.findViewById(R.id.id_lay_keyboard_quantity_number_1);
//        mLinearLayout_NumberTwo = (LinearLayout) mKeyboardView.findViewById(R.id.id_lay_keyboard_quantity_number_2);
//        mLinearLayout_NumberThree = (LinearLayout) mKeyboardView.findViewById(R.id.id_lay_keyboard_quantity_number_3);
//        mLinearLayout_NumberFour = (LinearLayout) mKeyboardView.findViewById(R.id.id_lay_keyboard_quantity_number_4);
//        mLinearLayout_NumberFive = (LinearLayout) mKeyboardView.findViewById(R.id.id_lay_keyboard_quantity_number_5);
//        mLinearLayout_NumberSix = (LinearLayout) mKeyboardView.findViewById(R.id.id_lay_keyboard_quantity_number_6);
//        mLinearLayout_NumberSeven = (LinearLayout) mKeyboardView.findViewById(R.id.id_lay_keyboard_quantity_number_7);
//        mLinearLayout_NumberEight = (LinearLayout) mKeyboardView.findViewById(R.id.id_lay_keyboard_quantity_number_8);
//        mLinearLayout_NumberNine = (LinearLayout) mKeyboardView.findViewById(R.id.id_lay_keyboard_quantity_number_9);
//        mLinearLayout_Confirm = (LinearLayout) mKeyboardView.findViewById(R.id.id_lay_keyboard_quantity_confirm);
//        mLinearLayout_Cancel = (LinearLayout) mKeyboardView.findViewById(R.id.id_lay_keyboard_quantity_cancel);
//
//        mTextView_TextLeft = (TextView) mKeyboardView.findViewById(R.id.id_tv_quantity_text_left);
//        mTextView_TextRight = (TextView) mKeyboardView.findViewById(R.id.id_tv_quantity_text_right);
//
//        mEditText_InputBoxLeft = (EditText) mKeyboardView.findViewById(R.id.id_et_quantity_input_box_left);
//        mEditText_InputBoxRight = (EditText) mKeyboardView.findViewById(R.id.id_et_quantity_input_box_right);
//
//        mTextView_TextLeft.setText(SELECTOR.listNONRETAIL_UNIT.get(position));
//        mTextView_TextRight.setText(SELECTOR.listRETAIL_UNIT.get(position));
//
//        mImageView_TextClearRight = (ImageView) mKeyboardView.findViewById(R.id.id_iv_input_box_clear_right);
//        mImageView_TextClearLeft = (ImageView) mKeyboardView.findViewById(R.id.id_iv_input_box_clear_left);
//
//        mLinearLayout_NumberZero.setOnClickListener(this);
//        mLinearLayout_NumberOne.setOnClickListener(this);
//        mLinearLayout_NumberTwo.setOnClickListener(this);
//        mLinearLayout_NumberThree.setOnClickListener(this);
//        mLinearLayout_NumberFour.setOnClickListener(this);
//        mLinearLayout_NumberFive.setOnClickListener(this);
//        mLinearLayout_NumberSix.setOnClickListener(this);
//        mLinearLayout_NumberSeven.setOnClickListener(this);
//        mLinearLayout_NumberEight.setOnClickListener(this);
//        mLinearLayout_NumberNine.setOnClickListener(this);
//        mLinearLayout_Confirm.setOnClickListener(this);
//        mLinearLayout_Cancel.setOnClickListener(this);
//
//        mImageView_TextClearLeft.setOnClickListener(this);
//        mImageView_TextClearRight.setOnClickListener(this);
//
//        mEditText_InputBoxLeft.setOnTouchListener(this);
//        mEditText_InputBoxRight.setOnTouchListener(this);
//
//        mEditText_InputBoxRight.addTextChangedListener(mTextWatcherRight);
//        mEditText_InputBoxLeft.addTextChangedListener(mTextWatcherLeft);
//
//        mQuantityKeyboardDialog = new AlertDialog.Builder(mContext, R.style.dialog1).create();
//        mQuantityKeyboardDialog.show();
//        mQuantityKeyboardDialog.setContentView(mKeyboardView);
//        WindowManager.LayoutParams lp = mQuantityKeyboardDialog.getWindow().getAttributes();
//        lp.width = Util.getScreenWidth(mContext);
//        lp.dimAmount = 0.3f;
//        lp.gravity = Gravity.BOTTOM;
//        mQuantityKeyboardDialog.getWindow().setAttributes(lp);
//    }
//
//    /**
//     * 关闭Dialog
//     */
//    private void dismissDialog() {
//        if (mQuantityKeyboardDialog != null)
//            mQuantityKeyboardDialog.dismiss();
//        if (mPriceKeyboardDialog != null)
//            mPriceKeyboardDialog.dismiss();
//    }
//
//    /**
//     * 在输入框显示点击按键的数值
//     *
//     * @param numLeft
//     * @param numRight
//     * @param number
//     */
//    private void setNumberToInputBox(String numLeft, String numRight, String number) {
//        if (mEditText_InputBoxLeft.isFocused()) {
//            if (numLeft.contains(".")) {
//                String[] arrNumLeft = numLeft.split("\\.", 2);
//                if (arrNumLeft[1].length() < 2) {
//                    setNumLeftText(numLeft, number);
//                }
//            } else {
//                setNumLeftText(numLeft, number);
//            }
//        } else if (mEditText_InputBoxRight.isFocused()) {
//            if (numRight.contains(".")) {
//                String[] arrNumRight = numRight.split("\\.", 2);
//                if (arrNumRight[1].length() < 2) {
//                    setNumRightText(numRight, number);
//                }
//            } else {
//                setNumRightText(numRight, number);
//            }
//        }
//    }
//
//    /**
//     * 设置按钮数值
//     *
//     * @param numRight
//     * @param number
//     */
//    private void setNumRightText(String numRight, String number) {
//        numRight = numRight + number;
//        mEditText_InputBoxRight.setText(numRight);
//        //设置光标的位置为最后
//        mEditText_InputBoxRight.setSelection(numRight.length());
//    }
//
//    /**
//     * 设置按钮数值
//     *
//     * @param numLeft
//     * @param number
//     */
//    private void setNumLeftText(String numLeft, String number) {
//        numLeft = numLeft + number;
//        mEditText_InputBoxLeft.setText(numLeft);
//        //设置光标的位置为最后
//        mEditText_InputBoxLeft.setSelection(numLeft.length());
//    }
//
//    /**
//     * 处理点击确定按钮之后，右边输入框的业务逻辑
//     *
//     * @param numRight
//     */
//    private void processNumberRight(String numRight) {
//        if (!"".equals(numRight)) {
//            if (mQuantityKeyboardDialog != null) {
//                SELECTOR.listRETAIL_COUNT.set(mClickedItemPosition, numRight);
//            } else if (mPriceKeyboardDialog != null) {
//                if (numRight.contains(".")) {
//                    String[] arrNumRight = numRight.split("\\.", 2);
//                    if (arrNumRight[1].length() == 0) {
//                        numRight += "00";
//                    } else if (arrNumRight[1].length() == 1) {
//                        numRight += "0";
//                    }
//                } else {
//                    numRight += ".00";
//                }
//                SELECTOR.listPRICE.set(mClickedItemPosition, numRight);
//            }
//            myAdapter.notifyDataSetChanged();
//        }
//    }
//
//    /**
//     * 处理点击确定按钮之后，左边输入框的业务逻辑
//     *
//     * @param numLeft
//     */
//    private void processNumberLeft(String numLeft) {
//        if (!"".equals(numLeft)) {
//            if (mQuantityKeyboardDialog != null) {
//                SELECTOR.listNONRETAIL_COUNT.set(mClickedItemPosition, numLeft);
//            } else if (mPriceKeyboardDialog != null) {
//                if (numLeft.contains(".")) {
//                    String[] arrNumLeft = numLeft.split("\\.", 2);
//                    if (arrNumLeft[1].length() == 0) {
//                        numLeft += "00";
//                    } else if (arrNumLeft[1].length() == 1) {
//                        numLeft += "0";
//                    }
//                } else {
//                    numLeft += ".00";
//                }
//                SELECTOR.listBOX_PRICE.set(mClickedItemPosition, numLeft);
//            }
//            myAdapter.notifyDataSetChanged();
//        }
//    }
//
//    TextWatcher mTextWatcherLeft = new TextWatcher() {
//        @Override
//        public void beforeTextChanged(CharSequence s, int start, int count, int after) {
//
//        }
//
//        @Override
//        public void onTextChanged(CharSequence s, int start, int before, int count) {
//
//        }
//
//        @Override
//        public void afterTextChanged(Editable s) {
//            if (mEditText_InputBoxLeft.getText().length() == 0) {
//                mImageView_TextClearLeft.setVisibility(View.GONE);
//            } else {
//                mImageView_TextClearLeft.setVisibility(View.VISIBLE);
//            }
//        }
//    };
//
//    TextWatcher mTextWatcherRight = new TextWatcher() {
//        @Override
//        public void beforeTextChanged(CharSequence s, int start, int count, int after) {
//
//        }
//
//        @Override
//        public void onTextChanged(CharSequence s, int start, int before, int count) {
//
//        }
//
//        @Override
//        public void afterTextChanged(Editable s) {
//            if (mEditText_InputBoxRight.getText().length() == 0) {
//                mImageView_TextClearRight.setVisibility(View.GONE);
//            } else {
//                mImageView_TextClearRight.setVisibility(View.VISIBLE);
//            }
//        }
//    };
//
//}
//
//
