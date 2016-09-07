package com.youxiao.ui.activity.login;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.View.OnClickListener;
import android.view.WindowManager;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation.AnimationListener;
import android.view.animation.Animation;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.lidroid.xutils.ViewUtils;
import com.lidroid.xutils.view.annotation.ViewInject;
import com.youxiao.ui.activity.MainActivity;
import com.youxiao.R;

/**
 *
 */
public class SplashActivity extends Activity implements OnClickListener{
    @ViewInject(R.id.viewpager)
    private ImageView viewpager; // 图片
    @ViewInject(R.id.button1)
    private Button button1;
    @ViewInject(R.id.button2)
    private Button button2;
    @ViewInject(R.id.viewpager1)
    private ImageView viewpager1; // 图片
    private int[] drawable = { R.drawable.bg1, R.drawable.bg2, R.drawable.bg3,
            R.drawable.bg4 };
    public static final int ANIMATION_TIME = 8000;
    public AlphaAnimation aa, aa1;
    public int index = 0;
    public int index1 = 1;
    public boolean isok = true;
    private SharedPreferences sharedPreferences;

    private TextView mTextView_FastExperience;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // 全屏
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        requestWindowFeature(Window.FEATURE_LEFT_ICON);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);

        setContentView(R.layout.activity_splash);

        ViewUtils.inject(this);
        sharedPreferences = getSharedPreferences("user_info",Activity.MODE_PRIVATE);
        viewpager.setScaleType(ImageView.ScaleType.FIT_XY);
        viewpager1.setScaleType(ImageView.ScaleType.FIT_XY);
        button1.setOnClickListener(this);
        // 图片渐变模糊度始终
        aa = new AlphaAnimation(1.0f, 0f);
        aa1 = new AlphaAnimation(0f, 1.0f);
        // 渐变时间
        aa.setDuration(ANIMATION_TIME);
        aa1.setDuration(ANIMATION_TIME);
        if (isok) {
            viewpager.setImageResource(drawable[index]);
            viewpager1.setImageResource(drawable[index1]);
            isok = false;
        }
        viewpager.setAnimation(aa);
        viewpager1.setAnimation(aa1);
        aa.setAnimationListener(new Animation.AnimationListener() {
            public void onAnimationStart(Animation arg0) {
            }

            public void onAnimationRepeat(Animation arg0) {
            }

            public void onAnimationEnd(Animation arg0) {
                aa.start();
                index = index + 1;
                if (index <= 4) {
                    if (index == 4) {
                        index = 0;
                    }
                    viewpager.setImageResource(drawable[index]);
                    viewpager.setAnimation(aa);
                }
            }
        });
        aa1.setAnimationListener(new AnimationListener() {
            public void onAnimationStart(Animation arg0) {
            }

            public void onAnimationRepeat(Animation arg0) {
            }

            public void onAnimationEnd(Animation arg0) {

                aa1.start();

                index1 = index1 + 1;
                if (index1 <= 4) {
                    if (index1 == 4) {
                        index1 = 0;
                    }
                    viewpager1.setImageResource(drawable[index1]);
                    viewpager1.setAnimation(aa1);
                }
            }
        });

        findViewById(R.id.tv_fast_experience).setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(SplashActivity.this, MainActivity.class);
                startActivity(intent);
            }
        });

    }


    @Override
    protected void onResume() {
        super.onResume();


    }

    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.button1:
//                GLOBAL.LAST_JOB_NO = sharedPreferences.getString("lastusername","");
//                if (!GLOBAL.LAST_JOB_NO.equals("")) {
//                    SharedPreferences.Editor editor = sharedPreferences.edit();
//                    editor.putBoolean("userIsFirst",false);
//                    editor.commit();
//                    Intent intent = new Intent(SplashActivity.this, LockActivity.class);
//                    startActivity(intent);
//                    SplashActivity.this.finish();
//                }else {
//                    startActivity(new Intent(SplashActivity.this, LoginActivity.class));
//                }
                break;

        }
    }
}
