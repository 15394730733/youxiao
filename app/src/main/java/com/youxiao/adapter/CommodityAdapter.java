package com.youxiao.adapter;

import android.app.Activity;
import android.graphics.drawable.BitmapDrawable;
import android.text.InputType;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.RadioButton;
import android.widget.TextView;

import com.youxiao.R;
import com.youxiao.model.CommoditySelectBean;

import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Map;

import static java.lang.String.valueOf;

/**
 * 商品页面对应的adapter
 * Created by Administrator on 2016/9/27.
 */
public class CommodityAdapter extends BaseAdapter  {

    private PopupWindow pop;
    private Activity activity;
    //        数字键盘
    private EditText input1;
    private EditText input2;
    private LinearLayout keyboard1;
    private LinearLayout keyboard2;
    private LinearLayout keyboard3;
    private LinearLayout keyboard4;
    private LinearLayout keyboard5;
    private LinearLayout keyboard6;
    private LinearLayout keyboard7;
    private LinearLayout keyboard8;
    private LinearLayout keyboard9;
    private LinearLayout keyboard0;

    ViewHolder holder = null;

    private TextView one;
    private TextView two;
    private TextView three;
    private TextView four;
    private TextView five;
    private TextView six;
    private TextView seven;
    private TextView eight;
    private TextView nine;
    private TextView quxiao;
    private TextView queding;
    private List<CommoditySelectBean.DataBean.NewkindcomlistBean.CommodityBean> listdata;
    private TextView cancel;
    private TextView confim;
    private TextView zero;
    private final EditText et1;
    private final EditText et2;

    public List<Map<String, Object>> data;

    public CommodityAdapter(final Activity activity, List<Map<String, Object>> data) {
        this.activity = activity;
        this.data = data;

        LayoutInflater inflater = LayoutInflater.from(activity);
        View view = inflater.inflate(R.layout.my_alert_dialog_cmdty_count_layout, null);
        cancel = (TextView) view.findViewById(R.id.cancel_action);//取消
//        confim = (TextView) view.findViewById(R.id.confim);//确定
//        zero = (TextView) view.findViewById(R.id.zero1);
//        one = (TextView) view.findViewById(R.id.one);
//        two = (TextView) view.findViewById(R.id.two);
//        three = (TextView) view.findViewById(R.id.three);
//        four = (TextView) view.findViewById(R.id.four);
//        five = (TextView) view.findViewById(R.id.five);
//        six = (TextView) view.findViewById(R.id.six);
//        seven = (TextView) view.findViewById(R.id.seven);
//        eight = (TextView) view.findViewById(R.id.eight);
//        nine = (TextView) view.findViewById(R.id.nine);
        et1 = (EditText) view.findViewById(R.id.num1);
        et2 = (EditText) view.findViewById(R.id.num2);
        //键盘隐藏
        et1.setInputType(InputType.TYPE_NULL);
        et2.setInputType(InputType.TYPE_NULL);
        //光标显示
        et1.setFocusable(true);
        et1.setFocusableInTouchMode(true);
        et1.requestFocus();
        et2.setFocusable(true);
        et2.setFocusableInTouchMode(true);
        et2.requestFocus();
//        one.setOnClickListener(this);
//        two.setOnClickListener(this);
//        three.setOnClickListener(this);
//        four.setOnClickListener(this);
//        five.setOnClickListener(this);
//        six.setOnClickListener(this);
//        seven.setOnClickListener(this);
//        eight.setOnClickListener(this);
//        nine.setOnClickListener(this);
//        zero.setOnClickListener(this);
//        confim.setOnClickListener(this);
//        cancel.setOnClickListener(this);
        pop = new PopupWindow(view, LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT, true);
        pop.setBackgroundDrawable(new BitmapDrawable());
        pop.setOutsideTouchable(true);
        pop.setFocusable(true);
    }

    @Override
    public int getCount() {
        return data.size();
    }

    @Override
    public Object getItem(int position) {
        return data.get(position);
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if (convertView == null) {
            convertView = View.inflate(activity, R.layout.item_for_commodity_item_sales, null);
            holder = new ViewHolder(convertView);
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }
        Map<String, Object> map = data.get(position);
        holder.tv_commodity_name.setText(map.get("name").toString());//名字
        holder.tv_commodity_type.setText(map.get("num").toString());//规格
        holder.tv_commodity_store.setText(map.get("desc").toString());//库存
        holder.tv_big_account.setText(map.get("bigUnitNum").toString());//大数量
        holder.tv_small_account.setText(map.get("smallUnitNum").toString());//小数量
        holder.tv_big_unit.setText(map.get("bigUnit").toString());//大单位
        holder.tv_small_unit.setText(map.get("smallUnit").toString());//小单位
        holder.tv_big_price.setText(map.get("bigPrice").toString());//大单价
        holder.tv_small_price.setText(map.get("smallPrice").toString());//小单价
        //日期格式转化
        SimpleDateFormat dateFormater = new SimpleDateFormat("yyyy-MM-dd");
        String time = dateFormater.format(new Date(Long.parseLong(map.get("time").toString())));
        holder.tv_item_production_date.setText(time);//日期
        Double sum = jisuan();
        map.put("sumPrice", sum);
        return convertView;

    }


    private Double jisuan() {
        DecimalFormat decimalFormat = new DecimalFormat("0.00");
        double big_num = Double.valueOf(holder.tv_big_account.getText().toString());//大数量
        double small_num = Double.valueOf(holder.tv_small_account.getText().toString());//小数量
        double big_price = Double.valueOf(holder.tv_big_price.getText().toString());//大价格
        double small_price = Double.valueOf(holder.tv_small_price.getText().toString());//小价格
        String sum_price = valueOf(decimalFormat.format(big_num * big_price + small_num * small_price));
        holder.tv_total_price.setText(sum_price);//小合计
        return Double.valueOf(sum_price);
    }


    //数值键盘输入内容获取和监听事件
//    @Override
//    public void onClick(View v) {
//        switch (v.getId()) {
//            //=====111
//            case R.id.one:
//                if (et1.isFocused()) {
//                    if (et1.getText().length() >= 7) {
////                        ToastUtil.show("您输入的数值过大！");
//                    } else {
//                        if (et1.getText().toString().equals("0")) {
//                            et1.setText("1");
//                        } else {
//                            et1.setText(et1.getText() + "1");
//                        }
//                    }
//                } else if (et2.isFocused()) {
//                    if (et2.getText().length() >= 7) {
////                        ToastUtil.show("您输入的数值过大！");
//                    } else {
//                        if (et2.getText().toString().equals("0")) {
//                            et2.setText("1");
//                        } else {
//                            et2.setText(et2.getText() + "1");
//                        }
//                    }
//                }
//                break;
//            //===222
//            case R.id.two:
//                if (et1.isFocused()) {
//                    if (et1.getText().length() >= 7) {
////                        ToastUtil.show("您输入的数值过大！");
//                    } else {
//                        if (et1.getText().toString().equals("0")) {
//                            et1.setText("2");
//                        } else {
//
//                            et1.setText(et1.getText() + "2");
//                        }
//                    }
//                } else if (et2.isFocused()) {
//                    if (et2.getText().length() >= 7) {
//                    } else {
//                        if (et2.getText().toString().equals("0")) {
//                            et2.setText("2");
//                        } else {
//                            et2.setText(et2.getText() + "2");
//                        }
//                    }
//                }
//                break;
//            //=====333
//            case R.id.three:
//                if (et1.isFocused()) {
//                    if (et1.getText().length() >= 7) {
////                        ToastUtil.show("您输入的数值过大！");
//                    } else {
//                        if (et1.getText().toString().equals("0")) {
//                            et1.setText("3");
//                        } else {
//                            et1.setText(et1.getText() + "3");
//                        }
//                    }
//                } else if (et2.isFocused()) {
//                    if (et2.getText().length() >= 7) {
//                    } else {
//                        if (et2.getText().toString().equals("0")) {
//                            et2.setText("3");
//                        } else {
//                            et2.setText(et2.getText() + "3");
//                        }
//                    }
//                }
//                break;
//            //=======444
//            case R.id.four:
//
//                if (et1.isFocused()) {
//                    if (et1.getText().length() >= 7) {
//                    } else {
//                        if (et1.getText().toString().equals("0")) {
//                            et1.setText("4");
//                        } else {
//                            et1.setText(et1.getText() + "4");
//                        }
//                    }
//                } else if (et2.isFocused()) {
//                    if (et2.getText().length() >= 7) {
////                        ToastUtil.show("您输入的数值过大！");
//                    } else {
//                        if (et2.getText().toString().equals("0")) {
//                            et2.setText("4");
//                        } else {
//
//                            et2.setText(et2.getText() + "4");
//                        }
//                    }
//                }
//                break;
//            //======555
//            case R.id.five:
//
//                if (et1.isFocused()) {
//                    if (et1.getText().length() >= 7) {
////                        ToastUtil.show("您输入的数值过大！");
//                    } else {
//                        if (et1.getText().toString().equals("0")) {
//                            et1.setText("5");
//                        } else {
//
//                            et1.setText(et1.getText() + "5");
//                        }
//                    }
//                } else if (et2.isFocused()) {
//                    if (et2.getText().length() >= 7) {
////                        ToastUtil.show("您输入的数值过大！");
//                    } else {
//                        if (et2.getText().toString().equals("0")) {
//                            et2.setText("5");
//                        } else {
//
//                            et2.setText(et2.getText() + "5");
//                        }
//                    }
//
//                }
//                break;
//            //=====666
//            case R.id.six:
//
//
//                if (et1.isFocused()) {
//                    if (et1.getText().length() >= 7) {
////                        ToastUtil.show("您输入的数值过大！");
//                    } else {
//                        if (et1.getText().toString().equals("0")) {
//                            et1.setText("6");
//                        } else {
//
//                            et1.setText(et1.getText() + "6");
//                        }
//                    }
//                } else if (et2.isFocused()) {
//                    if (et2.getText().length() >= 7) {
////                        ToastUtil.show("您输入的数值过大！");
//                    } else {
//                        if (et2.getText().toString().equals("0")) {
//                            et2.setText("6");
//                        } else {
//                            et2.setText(et2.getText() + "6");
//                        }
//
//                    }
//                }
//                break;
//            //=====7777
//            case R.id.seven:
//
//                if (et1.isFocused()) {
//                    if (et1.getText().length() >= 7) {
//                    } else {
//                        if (et1.getText().toString().equals("0")) {
//                            et1.setText("7");
//                        } else {
//                            et1.setText(et1.getText() + "7");
//                        }
//                    }
//                } else if (et2.isFocused()) {
//                    if (et2.getText().length() >= 7) {
//                    } else {
//                        if (et2.getText().toString().equals("0")) {
//                            et2.setText("7");
//                        } else {
//
//                            et2.setText(et2.getText() + "7");
//                        }
//                    }
//
//                }
//                break;
//            //======888
//            case R.id.eight:
//
//                if (et1.isFocused()) {
//                    if (et1.getText().length() >= 7) {
//                    } else {
//                        if (et1.getText().toString().equals("0")) {
//                            et1.setText("8");
//                        } else {
//
//                            et1.setText(et1.getText() + "8");
//                        }
//                    }
//                } else if (et2.isFocused()) {
//                    if (et2.getText().length() >= 7) {
//
//                    } else {
//                        if (et2.getText().toString().equals("0")) {
//                            et2.setText("8");
//                        } else {
//                            et2.setText(et2.getText() + "8");
//                        }
//                    }
//                }
//                break;
//            //======9999
//            case R.id.nine:
//                if (et1.isFocused()) {
//                    if (et1.getText().length() >= 7) {
//
//                    } else {
//                        if (et1.getText().toString().equals("0")) {
//                            et1.setText("9");
//
//                        } else {
//
//                            et1.setText(et1.getText() + "9");
//                        }
//                    }
//
//                } else if (et2.isFocused()) {
//                    if (et2.getText().length() >= 7) {
////                        ToastUtil.show("您输入的数值过大！");
//                    } else {
//                        if (et2.getText().toString().equals("0")) {
//                            et2.setText("9");
//                        } else {
//
//                            et2.setText(et2.getText() + "9");
//                        }
//                    }
//                }
//                break;
//            //======000
//            case R.id.zero1:
//                if (et1.isFocused()) {
//                    if (et1.getText().length() >= 7) {
////                        ToastUtil.show("您输入的数值过大！");
//                    } else {
//                        if (et1.getText().toString().equals("0")) {
//                            et1.setText("0");
//                        } else {
//                            et1.setText(et1.getText() + "0");
//                        }
//
//                    }
//
//
//                } else if (et2.isFocused()) {
//                    if (et2.getText().length() >= 7) {
////                        ToastUtil.show("您输入的数值过大！");
//                    } else {
//                        if (et2.getText().toString().equals("0")) {
//                            et2.setText("0");
//                        } else {
//                            et2.setText(et2.getText() + "0");
//                        }
//                    }
//                }
//                break;
//            case R.id.cancel_action:
//                pop.dismiss();
//                break;
//            case R.id.confim:
//                holder.tv_big_account.setText(et1.getText());
//                holder.tv_small_account.setText(et2.getText());
//                pop.dismiss();
//                break;
//        }
//    }


    private class ViewHolder implements View.OnClickListener {
        private TextView tv_commodity_name;//商品名称
        private TextView tv_commodity_type;//商品规格
        private TextView tv_commodity_store;//库存
        private TextView tv_big_account;//大数量
        private TextView tv_small_account;//小数量
        private TextView tv_big_unit;//大单位
        private TextView tv_small_unit;//小单位
        private TextView tv_big_price;//大单价
        private TextView tv_small_price;//小单价
        private TextView tv_total_price;//总价
        private TextView tv_item_production_date;//生产日期
        private LinearLayout item_for_commodity_item_sales_LL_Count;//数量修改弹框id
        private TextView bigunit;
        private TextView smallunit;
        public RadioButton rb;


        public ViewHolder(View view1) {
//            rb = (RadioButton) view1.findViewById(R.id.radio_button);
//            tv_commodity_name = (TextView) view1.findViewById(R.id.tv_commodity_name);
//            tv_commodity_type = (TextView) view1.findViewById(R.id.tv_commodity_type);
//            tv_commodity_store = (TextView) view1.findViewById(R.id.tv_commodity_store);
//            tv_big_account = (TextView) view1.findViewById(R.id.tv_big_account);
//            tv_small_account = (TextView) view1.findViewById(R.id.tv_small_account);
//            tv_big_unit = (TextView) view1.findViewById(R.id.tv_big_unit);
//            tv_small_unit = (TextView) view1.findViewById(R.id.tv_small_unit);
//            tv_big_price = (TextView) view1.findViewById(R.id.tv_big_price);
//            tv_small_price = (TextView) view1.findViewById(R.id.tv_small_price);
//            tv_total_price = (TextView) view1.findViewById(R.id.tv_total_price);
//            tv_item_production_date = (TextView) view1.findViewById(R.id.tv_item_production_date);
//            item_for_commodity_item_sales_LL_Count = (LinearLayout) view1.findViewById(R.id.item_for_commodity_item_sales_LL_Count1);
            item_for_commodity_item_sales_LL_Count.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            if (pop.isShowing()) {
                pop.dismiss();
            } else {
                pop.showAtLocation(v, Gravity.BOTTOM, 0, 0);
                pop.showAsDropDown(v);
                et1.setText(holder.tv_big_account.getText().toString());
                et2.setText(holder.tv_small_account.getText().toString());
            }
        }
    }
}
