package com.youxiao.ui.fragment;


import android.annotation.SuppressLint;
import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.app.AlertDialog;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import com.youxiao.R;
import com.youxiao.adapter.CommonAdapter;
import com.youxiao.adapter.ViewHolder;
import com.youxiao.ui.activity.work.procurementmanager.SelectProviderActivity;
import com.youxiao.util.ListViewUtil;
import com.youxiao.widget.Util;
import com.youxiao.widget.WheelView;

import java.util.ArrayList;
import java.util.List;


/**
 * 采购订单
 *
 * @author StomHong
 * @since 2016-7-1
 */
public class ProcurementOrderFragment extends Fragment implements
        View.OnClickListener, View.OnTouchListener {

    private static final String TAG = ProcurementOrderFragment.class.getSimpleName();
    private static ProcurementOrderFragment mInstance;

    private LinearLayout mLinearLayout_SelectProvider;
    private ListView mListView_ProcurementOrder;
    private List<String> mProcurementOrders;
    private CommonAdapter<String> mAdapter;
    private TextView mTextView_Title;
    private TextView mTextView_Provider;
    private ImageView mImageView_Search;
    private static final int PROCUREMENT_ORDER_REQUEST = 0x101;

    private AlertDialog mQuantityKeyboardDialog;
    private AlertDialog mPriceKeyboardDialog;

    private LinearLayout mLinearLayout_Backspace;
    private LinearLayout mLinearLayout_Dot;
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
    private LinearLayout mLinearLayout_Confirm;
    private LinearLayout mLinearLayout_Cancel;

    private EditText mEditText_InputBoxLeft;
    private EditText mEditText_InputBoxRight;
    private TextView mTextView_TextLeft;
    private TextView mTextView_TextRight;

    private ImageView mImageView_TextClearRight;
    private ImageView mImageView_TextClearLeft;

    private WheelView mWheelView_Year;
    private WheelView mWheelView_Month;
    private WheelView mWheelView_Day;
    private AlertDialog mProduceDateDialog;
    private View mTimePickerView;
    int mClickedItemPosition;
    View mKeyboardView;

    public static ProcurementOrderFragment newInstance() {
        if (mInstance == null) {
            mInstance = new ProcurementOrderFragment();
        }
        return mInstance;
    }

    @SuppressLint("ValidFragment")
    private ProcurementOrderFragment() {

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_procurement_order, container, false);
        initView(view);
        initData();
        initEvent();
        return view;
    }

    private void initEvent() {
        mLinearLayout_SelectProvider.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mTextView_Title = (TextView) getActivity().findViewById(R.id.id_tv_title);
                if (!mTextView_Title.getText().toString().equals("选择仓库")) {
                    Intent intent = new Intent();
                    intent.setClass(getActivity(), SelectProviderActivity.class);
                    startActivityForResult(intent, PROCUREMENT_ORDER_REQUEST);
                }
            }
        });
    }

    private void initData() {
        mProcurementOrders = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            mProcurementOrders.add("");
        }
        mAdapter = new CommonAdapter<String>(getActivity(), mProcurementOrders, R.layout.item_procurement) {
            @Override
            public void convert(ViewHolder holder, String s) {

                holder.setViewOnClick(new ViewHolder.ViewClick() {
                    @Override
                    public void onClick(View v, int position) {
                        switch (v.getId()) {
                            case R.id.id_ll_quantity:
                                quantityKeyboardDialog(position);
                                break;
                            case R.id.id_ll_price:
                                priceKeyboardDialog(position);
                                break;
                            case R.id.id_ll_remark:
                                remarkDialog(position);
                                break;
                            case R.id.id_ll_production_date:
                                productionDateDialog(position);
                                break;
                            default:
                                break;
                        }

                    }
                }, R.id.id_ll_production_date,R.id.id_ll_price,R.id.id_ll_quantity,R.id.id_ll_remark);
            }

        };
        mListView_ProcurementOrder.setAdapter(mAdapter);
        ListViewUtil.setListViewHeightBasedOnChildren(mListView_ProcurementOrder);
    }

    private void initView(View view) {
        mImageView_Search = (ImageView) view.findViewById(R.id.id_iv_procurement_order_search);
        mTextView_Provider = (TextView) view.findViewById(R.id.id_tv_procurement_order_provider);
        mListView_ProcurementOrder = (ListView) view.findViewById(R.id.id_lv_procurement_order);
        mLinearLayout_SelectProvider = (LinearLayout) view.findViewById(R.id.id_ll_procurement_order_select_provider);

    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        String provider = data.getStringExtra("provider");
        if (provider != null) {
            mImageView_Search.setVisibility(View.GONE);
            mTextView_Provider.setText(provider);
        }
    }


    @Override
    public boolean onTouch(View v, MotionEvent event) {
        if (event.getAction() == MotionEvent.ACTION_DOWN) {
            if (v.getId() == R.id.id_et_quantity_input_box_left || v.getId() == R.id.id_et_quantity_input_box_right) {
                String numLeft = mEditText_InputBoxLeft.getText().toString().trim();
                String numRight = mEditText_InputBoxRight.getText().toString().trim();
                if (v.getId() == R.id.id_et_quantity_input_box_left) {
                    mEditText_InputBoxLeft.setSelection(0, numLeft.length());
                } else if (v.getId() == R.id.id_et_quantity_input_box_right) {
                    mEditText_InputBoxRight.setSelection(0, numRight.length());
                }
            }
        }
        return false;
    }

    @Override
    public void onClick(View v) {
        String numLeft = "";
        String numRight = "";
        if (mEditText_InputBoxLeft != null) {
            numLeft = mEditText_InputBoxLeft.getText().toString().trim();
        }
        if (mEditText_InputBoxRight != null) {
            numRight = mEditText_InputBoxRight.getText().toString().trim();
        }
        switch (v.getId()) {

            case R.id.id_lay_keyboard_quantity_number_0:
                setNumberToInputBox(numLeft, numRight, "0");
                break;
            case R.id.id_lay_keyboard_quantity_number_1:
                setNumberToInputBox(numLeft, numRight, "1");
                break;
            case R.id.id_lay_keyboard_quantity_number_2:
                setNumberToInputBox(numLeft, numRight, "2");
                break;
            case R.id.id_lay_keyboard_quantity_number_3:
                setNumberToInputBox(numLeft, numRight, "3");
                break;
            case R.id.id_lay_keyboard_quantity_number_4:
                setNumberToInputBox(numLeft, numRight, "4");
                break;
            case R.id.id_lay_keyboard_quantity_number_5:
                setNumberToInputBox(numLeft, numRight, "5");
                break;
            case R.id.id_lay_keyboard_quantity_number_6:
                setNumberToInputBox(numLeft, numRight, "6");
                break;
            case R.id.id_lay_keyboard_quantity_number_7:
                setNumberToInputBox(numLeft, numRight, "7");
                break;
            case R.id.id_lay_keyboard_quantity_number_8:
                setNumberToInputBox(numLeft, numRight, "8");
                break;
            case R.id.id_lay_keyboard_quantity_number_9:
                setNumberToInputBox(numLeft, numRight, "9");
                break;
            case R.id.id_lay_keyboard_quantity_cancel:
                dismissDialog();
                break;

            case R.id.id_lay_keyboard_quantity_confirm:
                processNumberLeft(numLeft);
                processNumberRight(numRight);
                dismissDialog();
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


                mAdapter.notifyDataSetChanged();

                mProduceDateDialog.dismiss();
                break;
            case R.id.id_lay_keyboard_quantity_backspace:
                if (mEditText_InputBoxLeft.isFocused()) {
                    if (numLeft.length() > 0) {
                        numLeft = numLeft.substring(0, numLeft.length() - 1);
                        mEditText_InputBoxLeft.setText(numLeft);
                        mEditText_InputBoxLeft.setSelection(numLeft.length());
                    }
                } else if (mEditText_InputBoxRight.isFocused()) {
                    if (numRight.length() > 0) {
                        numRight = numRight.substring(0, numRight.length() - 1);
                        mEditText_InputBoxRight.setText(numRight);
                        mEditText_InputBoxRight.setSelection(numRight.length());
                    }
                }
                break;
            case R.id.id_lay_keyboard_quantity_dot:
                if (!numLeft.contains(".")) {
                    if (numLeft.length() > 0) {
                        setNumberToInputBox(numLeft, numRight, ".");
                    }
                }
                if (!numRight.contains(".")) {
                    if (numRight.length() > 0) {
                        setNumberToInputBox(numLeft, numRight, ".");
                    }
                }
                break;
            case R.id.id_iv_input_box_clear_left:
                mEditText_InputBoxLeft.setText("");
                mImageView_TextClearLeft.setVisibility(View.GONE);
                break;
            case R.id.id_iv_input_box_clear_right:
                mEditText_InputBoxRight.setText("");
                mImageView_TextClearRight.setVisibility(View.GONE);
                break;
            default:
                break;
        }
    }

    /**
     * 备注Dialog
     */
    private void remarkDialog(final int position) {
        final View remarkView = LayoutInflater.from(getActivity()).inflate(R.layout.layout_remark, null);
        final Dialog remarkDialog = new AlertDialog.Builder(getActivity(), R.style.remarkDialogStyle).create();
        remarkDialog.show();
        remarkDialog.setContentView(remarkView);
        remarkDialog.setCancelable(false);

        final EditText remark = (EditText) remarkView.findViewById(R.id.id_et_remark);
        LinearLayout confirm = (LinearLayout) remarkView.findViewById(R.id.id_ll_confirm);
        LinearLayout cancel = (LinearLayout) remarkView.findViewById(R.id.id_ll_cancel);

        confirm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                remarkDialog.dismiss();
            }
        });
        cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                remarkDialog.dismiss();
            }
        });
        remark.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if (hasFocus) {
                    remarkDialog.getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_VISIBLE);
                }
            }
        });
        WindowManager.LayoutParams params = remarkDialog.getWindow().getAttributes();
        params.dimAmount = 0.4f;
        params.gravity = Gravity.TOP;
        remarkDialog.getWindow().setAttributes(params);
    }

    /**
     * 数量键盘Dialog
     *
     * @param position
     */
    private void quantityKeyboardDialog(int position) {
        mClickedItemPosition = position;

        mKeyboardView = LayoutInflater.from(getActivity()).inflate(R.layout.layout_quantity_keyboard, null);
        mLinearLayout_NumberZero = (LinearLayout) mKeyboardView.findViewById(R.id.id_lay_keyboard_quantity_number_0);
        mLinearLayout_NumberOne = (LinearLayout) mKeyboardView.findViewById(R.id.id_lay_keyboard_quantity_number_1);
        mLinearLayout_NumberTwo = (LinearLayout) mKeyboardView.findViewById(R.id.id_lay_keyboard_quantity_number_2);
        mLinearLayout_NumberThree = (LinearLayout) mKeyboardView.findViewById(R.id.id_lay_keyboard_quantity_number_3);
        mLinearLayout_NumberFour = (LinearLayout) mKeyboardView.findViewById(R.id.id_lay_keyboard_quantity_number_4);
        mLinearLayout_NumberFive = (LinearLayout) mKeyboardView.findViewById(R.id.id_lay_keyboard_quantity_number_5);
        mLinearLayout_NumberSix = (LinearLayout) mKeyboardView.findViewById(R.id.id_lay_keyboard_quantity_number_6);
        mLinearLayout_NumberSeven = (LinearLayout) mKeyboardView.findViewById(R.id.id_lay_keyboard_quantity_number_7);
        mLinearLayout_NumberEight = (LinearLayout) mKeyboardView.findViewById(R.id.id_lay_keyboard_quantity_number_8);
        mLinearLayout_NumberNine = (LinearLayout) mKeyboardView.findViewById(R.id.id_lay_keyboard_quantity_number_9);
        mLinearLayout_Confirm = (LinearLayout) mKeyboardView.findViewById(R.id.id_lay_keyboard_quantity_confirm);
        mLinearLayout_Cancel = (LinearLayout) mKeyboardView.findViewById(R.id.id_lay_keyboard_quantity_cancel);

        mTextView_TextLeft = (TextView) mKeyboardView.findViewById(R.id.id_tv_quantity_text_left);
        mTextView_TextRight = (TextView) mKeyboardView.findViewById(R.id.id_tv_quantity_text_right);

        mEditText_InputBoxLeft = (EditText) mKeyboardView.findViewById(R.id.id_et_quantity_input_box_left);
        mEditText_InputBoxRight = (EditText) mKeyboardView.findViewById(R.id.id_et_quantity_input_box_right);

//        mTextView_TextLeft.setText(SELECTOR.listNONRETAIL_UNIT.get(position));
//        mTextView_TextRight.setText(SELECTOR.listRETAIL_UNIT.get(position));

        mImageView_TextClearRight = (ImageView) mKeyboardView.findViewById(R.id.id_iv_input_box_clear_right);
        mImageView_TextClearLeft = (ImageView) mKeyboardView.findViewById(R.id.id_iv_input_box_clear_left);

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
        mLinearLayout_Confirm.setOnClickListener(this);
        mLinearLayout_Cancel.setOnClickListener(this);

        mImageView_TextClearLeft.setOnClickListener(this);
        mImageView_TextClearRight.setOnClickListener(this);

        mEditText_InputBoxLeft.setOnTouchListener(this);
        mEditText_InputBoxRight.setOnTouchListener(this);

        mEditText_InputBoxRight.addTextChangedListener(mTextWatcherRight);
        mEditText_InputBoxLeft.addTextChangedListener(mTextWatcherLeft);

        mQuantityKeyboardDialog = new AlertDialog.Builder(getActivity(), R.style.dialog1).create();
        mQuantityKeyboardDialog.show();
        mQuantityKeyboardDialog.setContentView(mKeyboardView);
        WindowManager.LayoutParams lp = mQuantityKeyboardDialog.getWindow().getAttributes();
        lp.width = Util.getScreenWidth(getActivity());
        lp.dimAmount = 0.4f;
        lp.gravity = Gravity.BOTTOM;
        mQuantityKeyboardDialog.getWindow().setAttributes(lp);
    }

    /**
     * 关闭Dialog
     */
    private void dismissDialog() {
        if (mQuantityKeyboardDialog != null)
            mQuantityKeyboardDialog.dismiss();
        if (mPriceKeyboardDialog != null)
            mPriceKeyboardDialog.dismiss();
    }

    /**
     * 在输入框显示点击按键的数值
     *
     * @param numLeft
     * @param numRight
     * @param number
     */
    private void setNumberToInputBox(String numLeft, String numRight, String number) {
        if (mEditText_InputBoxLeft.isFocused()) {
            if (numLeft.contains(".")) {
                String[] arrNumLeft = numLeft.split("\\.", 2);
                if (arrNumLeft[1].length() < 2) {
                    setNumLeftText(numLeft, number);
                }
            } else {
                setNumLeftText(numLeft, number);
            }
        } else if (mEditText_InputBoxRight.isFocused()) {
            if (numRight.contains(".")) {
                String[] arrNumRight = numRight.split("\\.", 2);
                if (arrNumRight[1].length() < 2) {
                    setNumRightText(numRight, number);
                }
            } else {
                setNumRightText(numRight, number);
            }
        }
    }

    /**
     * 设置按钮数值
     *
     * @param numRight
     * @param number
     */
    private void setNumRightText(String numRight, String number) {
        numRight = numRight + number;
        mEditText_InputBoxRight.setText(numRight);
        //设置光标的位置为最后
        mEditText_InputBoxRight.setSelection(numRight.length());
    }

    /**
     * 设置按钮数值
     *
     * @param numLeft
     * @param number
     */
    private void setNumLeftText(String numLeft, String number) {
        numLeft = numLeft + number;
        mEditText_InputBoxLeft.setText(numLeft);
        //设置光标的位置为最后
        mEditText_InputBoxLeft.setSelection(numLeft.length());
    }


    /**
     * 处理点击确定按钮之后，右边输入框的业务逻辑
     *
     * @param numRight
     */
    private void processNumberRight(String numRight) {
        if (!"".equals(numRight)) {
            if (mQuantityKeyboardDialog != null) {
//                mProcurement = mProcurementOrders.get(mClickedItemPosition);
//                mProcurement.setBag(numRight);

            } else if (mPriceKeyboardDialog != null) {
                if (numRight.contains(".")) {
                    String[] arrNumRight = numRight.split("\\.", 2);
                    if (arrNumRight[1].length() == 0) {
                        numRight += "00";
                    } else if (arrNumRight[1].length() == 1) {
                        numRight += "0";
                    }
                } else {
                    numRight += ".00";
                }
//                mProcurement = mProcurementOrders.get(mClickedItemPosition);
//                mProcurement.setPrice(numRight);
            }
            mAdapter.notifyDataSetChanged();
        }
    }

    /**
     * 处理点击确定按钮之后，左边输入框的业务逻辑
     *
     * @param numLeft
     */
    private void processNumberLeft(String numLeft) {
        if (!"".equals(numLeft)) {
            if (mQuantityKeyboardDialog != null) {
//                mProcurement = mProcurementOrders.get(mClickedItemPosition);
//                mProcurement.setBox(numLeft);

            } else if (mPriceKeyboardDialog != null) {
                if (numLeft.contains(".")) {
                    String[] arrNumLeft = numLeft.split("\\.", 2);
                    if (arrNumLeft[1].length() == 0) {
                        numLeft += "00";
                    } else if (arrNumLeft[1].length() == 1) {
                        numLeft += "0";
                    }
                } else {
                    numLeft += ".00";
                }
//                mProcurement = mProcurementOrders.get(mClickedItemPosition);
//                mProcurement.setBoxPrice(numLeft);
            }
            mAdapter.notifyDataSetChanged();
        }
    }

    /**
     * 价格键盘Dialog
     *
     * @param position
     */
    private void priceKeyboardDialog(int position) {
        mClickedItemPosition = position;

        mKeyboardView = LayoutInflater.from(getActivity()).inflate(R.layout.layout_price_keyboard, null);

        mLinearLayout_NumberZero = (LinearLayout) mKeyboardView.findViewById(R.id.id_lay_keyboard_quantity_number_0);
        mLinearLayout_NumberOne = (LinearLayout) mKeyboardView.findViewById(R.id.id_lay_keyboard_quantity_number_1);
        mLinearLayout_NumberTwo = (LinearLayout) mKeyboardView.findViewById(R.id.id_lay_keyboard_quantity_number_2);
        mLinearLayout_NumberThree = (LinearLayout) mKeyboardView.findViewById(R.id.id_lay_keyboard_quantity_number_3);
        mLinearLayout_NumberFour = (LinearLayout) mKeyboardView.findViewById(R.id.id_lay_keyboard_quantity_number_4);
        mLinearLayout_NumberFive = (LinearLayout) mKeyboardView.findViewById(R.id.id_lay_keyboard_quantity_number_5);
        mLinearLayout_NumberSix = (LinearLayout) mKeyboardView.findViewById(R.id.id_lay_keyboard_quantity_number_6);
        mLinearLayout_NumberSeven = (LinearLayout) mKeyboardView.findViewById(R.id.id_lay_keyboard_quantity_number_7);
        mLinearLayout_NumberEight = (LinearLayout) mKeyboardView.findViewById(R.id.id_lay_keyboard_quantity_number_8);
        mLinearLayout_NumberNine = (LinearLayout) mKeyboardView.findViewById(R.id.id_lay_keyboard_quantity_number_9);
        mLinearLayout_Confirm = (LinearLayout) mKeyboardView.findViewById(R.id.id_lay_keyboard_quantity_confirm);
        mLinearLayout_Cancel = (LinearLayout) mKeyboardView.findViewById(R.id.id_lay_keyboard_quantity_cancel);
        mLinearLayout_Backspace = (LinearLayout) mKeyboardView.findViewById(R.id.id_lay_keyboard_quantity_backspace);
        mLinearLayout_Dot = (LinearLayout) mKeyboardView.findViewById(R.id.id_lay_keyboard_quantity_dot);

        mImageView_TextClearRight = (ImageView) mKeyboardView.findViewById(R.id.id_iv_input_box_clear_right);
        mImageView_TextClearLeft = (ImageView) mKeyboardView.findViewById(R.id.id_iv_input_box_clear_left);

        mEditText_InputBoxLeft = (EditText) mKeyboardView.findViewById(R.id.id_et_quantity_input_box_left);
        mEditText_InputBoxRight = (EditText) mKeyboardView.findViewById(R.id.id_et_quantity_input_box_right);


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
        mLinearLayout_Confirm.setOnClickListener(this);
        mLinearLayout_Cancel.setOnClickListener(this);
        mLinearLayout_Backspace.setOnClickListener(this);
        mLinearLayout_Dot.setOnClickListener(this);

        mImageView_TextClearLeft.setOnClickListener(this);
        mImageView_TextClearRight.setOnClickListener(this);

        mEditText_InputBoxLeft.setOnTouchListener(this);
        mEditText_InputBoxRight.setOnTouchListener(this);

        mEditText_InputBoxRight.addTextChangedListener(mTextWatcherRight);
        mEditText_InputBoxLeft.addTextChangedListener(mTextWatcherLeft);

        mPriceKeyboardDialog = new AlertDialog.Builder(getActivity(), R.style.dialog1).create();
        mPriceKeyboardDialog.show();
        mPriceKeyboardDialog.setContentView(mKeyboardView);
        WindowManager.LayoutParams params = mPriceKeyboardDialog.getWindow().getAttributes();
        params.width = Util.getScreenWidth(getActivity());
        params.dimAmount = 0.4f;
        params.gravity = Gravity.BOTTOM;
        mPriceKeyboardDialog.getWindow().setAttributes(params);
    }


    TextWatcher mTextWatcherLeft = new TextWatcher() {
        @Override
        public void beforeTextChanged(CharSequence s, int start, int count, int after) {

        }

        @Override
        public void onTextChanged(CharSequence s, int start, int before, int count) {

        }

        @Override
        public void afterTextChanged(Editable s) {
            if (mEditText_InputBoxLeft.getText().length() == 0) {
                mImageView_TextClearLeft.setVisibility(View.GONE);
            } else {
                mImageView_TextClearLeft.setVisibility(View.VISIBLE);
            }
        }
    };

    TextWatcher mTextWatcherRight = new TextWatcher() {
        @Override
        public void beforeTextChanged(CharSequence s, int start, int count, int after) {

        }

        @Override
        public void onTextChanged(CharSequence s, int start, int before, int count) {

        }

        @Override
        public void afterTextChanged(Editable s) {
            if (mEditText_InputBoxRight.getText().length() == 0) {
                mImageView_TextClearRight.setVisibility(View.GONE);
            } else {
                mImageView_TextClearRight.setVisibility(View.VISIBLE);
            }
        }
    };

    /**
     * 日期选择Dialog
     *
     * @param position
     */
    private void productionDateDialog(int position) {

        mClickedItemPosition = position;
        mTimePickerView = LayoutInflater.from(getActivity()).inflate(R.layout.layout_time_picker_layout, null);

        TextView mTextView_Cancel = (TextView) mTimePickerView.findViewById(R.id.id_tv_time_picker_cancel);
        TextView mTextView_Confirm = (TextView) mTimePickerView.findViewById(R.id.id_tv_time_picker_confirm);
        mTextView_Cancel.setOnClickListener(this);
        mTextView_Confirm.setOnClickListener(this);

        mWheelView_Year = (WheelView) mTimePickerView.findViewById(R.id.year);
        mWheelView_Month = (WheelView) mTimePickerView.findViewById(R.id.month);
        mWheelView_Day = (WheelView) mTimePickerView.findViewById(R.id.day);

        mProduceDateDialog = new AlertDialog.Builder(getActivity(), R.style.dialog1).create();
        mProduceDateDialog.show();
        mProduceDateDialog.setContentView(mTimePickerView);
        WindowManager.LayoutParams params = mProduceDateDialog.getWindow().getAttributes();
        params.width = Util.getScreenWidth(getActivity());
        params.dimAmount = 0.5f;
        params.gravity = Gravity.BOTTOM;
        mProduceDateDialog.getWindow().setAttributes(params);

    }



}
