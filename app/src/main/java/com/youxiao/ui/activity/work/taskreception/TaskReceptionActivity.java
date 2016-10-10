package com.youxiao.ui.activity.work.taskreception;

import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.youxiao.R;
import com.youxiao.adapter.CommonAdapter;
import com.youxiao.adapter.ViewHolder;
import com.youxiao.base.BaseActivity;
import com.youxiao.util.ListViewUtil;

import java.util.ArrayList;
import java.util.List;

/**
 * 任务接收
 *
 * @author StomHong
 * @since 2016-3-23
 */
public class TaskReceptionActivity extends BaseActivity implements View.OnClickListener, AdapterView.OnItemClickListener {

    private LinearLayout mLinearLayout_Back;
    private CommonAdapter<String> mAdapter;
    private List<String> mDatas;
    private ListView mListView_TaskReception;

    private ImageView mImageView_LineTop;
    private ImageView mImageView_LineBottom;


    private TextView mTextView_SaleTask;
    private TextView mTextView_VisitorTask;
    private TextView mTextView_ImportantTask;
    private TextView mTextView_Month;

    private View mView_LinkLine;
    EditText editText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_task_reception);

        super.init();
    }

    @Override
    public void initView() {

        mLinearLayout_Back = (LinearLayout) findViewById(R.id.id_lay_task_reception_back);
        mListView_TaskReception = (ListView) findViewById(R.id.id_lv_task_reception);

    }

    @Override
    public void initData() {
        mDatas = new ArrayList<>();

        final Drawable drawable = getResources().getDrawable(R.drawable.click_reception_clicked);

        mAdapter = new CommonAdapter<String>(this, mDatas, R.layout.item_for_task_reception) {

            @Override
            public void convert(final ViewHolder holder, String s) {

                if (holder.getPosition() == 0){
                    holder.setViewVisibility(R.id.id_iv_line_top,false);
                }
                if (holder.getPosition() == mDatas.size()-1){
                    holder.setViewVisibility(R.id.id_iv_line_bottom,false);
                }
                holder.setViewOnClick(new ViewHolder.ViewClick() {

                    @Override
                    public void onClick(View view, int position) {
                        holder.setBackground(R.id.id_btn_item_for_task_reception_click_reception, drawable);
                        holder.setText(R.id.id_btn_item_for_task_reception_click_reception, "已接收");
                        holder.setTextColor(R.id.id_btn_item_for_task_reception_click_reception, Color.parseColor("#bdbdbd"));
                    }
                }, R.id.id_btn_item_for_task_reception_click_reception);
            }

        };
        mListView_TaskReception.setAdapter(mAdapter);
        ListViewUtil.setListViewHeightBasedOnChildren(mListView_TaskReception);
        mListView_TaskReception.setFocusable(false);
    }

    @Override
    public void initEvent() {

        mLinearLayout_Back.setOnClickListener(this);
        mListView_TaskReception.setOnItemClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.id_lay_task_reception_back:
                finish();
                break;
            default:
                break;
        }
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        Toast.makeText(this, "你点击了第" + position + "条", Toast.LENGTH_SHORT).show();
    }
}
