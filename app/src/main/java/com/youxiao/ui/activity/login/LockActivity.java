package com.youxiao.ui.activity.login;

import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;

import com.youxiao.R;
import com.youxiao.base.BaseActivity;
import com.youxiao.widget.LoadingDialog;
import com.youxiao.widget.NinePointLineView;
import com.youxiao.widget.XKPrograssDialog;

import java.util.Random;

/**
 * 手势密码界面
 * 需要重构
 *
 * @author 张小布
 */
public class LockActivity extends BaseActivity implements View.OnClickListener {

    private static final String TAG = LockActivity.class.getSimpleName();
    private FrameLayout mFrameLayout_NinePoint;
    private XKPrograssDialog dialog;
    private NinePointLineView nv;
    private int mRemainingTimes = 7;
    private Context context;
    private int type;
    private String Addr;
    private String strPsw;
    private TextView mTextView_Prompt;
    private TextView mTextView_ChangePassword;
    private TextView mTextView_ReturnLoginActivity;
    private TextView mTextView_Saying;
    private LoadingDialog mLoadingDialog;
    private int index;
    private ImageView mImageView_rotation;

    private static final String[] saying = new String[]{
            "世界上本来是没有路的，走的人多了，也就成了路了。",
            "认识你自己。",
            "你把周围的人看作魔鬼，你就生活在地狱；你把周围的人看作天使，你就生活在天堂。",
            "流星之所以美丽在于燃烧的过程，人生之所以美丽在于奋斗的过程。",
            "一个人有生就有死，但只要你活着，就要以最好的方式活下去。",
            "生活是一面镜子。你对它笑，它就对你笑；你对它哭，它也对你哭。",
            "活着一天，就是有福气，就该珍惜。当我哭泣我没有鞋子穿的时候，我发现有人却没有脚。",
            "人生是个圆，有的人走了一辈子也没有走出命运画出的圆圈，其实，圆上的每一个点都有一条腾飞的切线。",
            "我们心中的恐惧，永远比真正的危险巨大的多。",
            "宁愿做过了后悔，也不要错过了后悔。",
            "如果我们都去做自己能力做得到的事，我们会让自己大吃一惊。",
            "学的到东西的事情是锻炼，学不到的是磨练。",
            "环境不会改变，解决之道在于改变自己。",
            "勇气是控制恐惧心理，而不是心里毫无恐惧。",
            "还能冲动，表示你还对生活有激情，总是冲动，表示你还不懂生活。",
            "在实现理想的路途中，必须排除一切干扰，特别是要看清那些美丽的诱惑。",
            "人一生下就会哭，笑是后来才学会的。所以忧伤是一种低级的本能，而快乐是一种更高级的能力。",
            "放弃该放弃的是无奈，放弃不该放弃的是无能，不放弃该放弃的是无知，不放弃不该放弃的是执著！",
            "行动是治愈恐惧的良药，而犹豫、拖延将不断滋养恐惧。",
            "如果你不给自己烦恼，别人也永远不可能给你烦恼，烦恼都是自己内心制造的。",
            "运气就是机会碰巧撞到了你的努力。",
            "得之坦然，失之淡然，顺其自然，争其必然。",
            "时间是治疗心灵创伤的大师，但绝不是解决问题的高手。",
            "天道酬勤。也许你付出了不一定得到回报，但不付出一定得不到回报。",
            "逆境是成长必经的过程，能勇于接受逆境的人，生命就会日渐的茁壮。",
            "只有不断找寻机会的人才会及时把握机会。",
            "如果你能像看别人缺点一样，如此准确的发现自己的缺点，那么你的生命将会不平凡。",
            "无论你觉得自己多么的了不起，也永远有人比你更强；无论你觉得自己多么的不幸，永远有人比你更加不幸。",
            "对待生活中的每一天若都像生命中的最后一天去对待，人生定会更精彩。"

    };

    protected void onCreate(Bundle savedInstanceState) {
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lock_temp);

        //动态的画圆？
//        nv = new NinePointLineView(context, handler);
//        nv.setLockActivity(this);
//        mFrameLayout_NinePoint.removeAllViews();
//        mFrameLayout_NinePoint.addView(nv);

    }

    @Override
    public void initData() {
        Random random = new Random();
        index = random.nextInt(29);
        mTextView_Saying.setText(saying[index]);
    }

    @Override
    public void initEvent() {
        mTextView_ChangePassword.setOnClickListener(this);
        mTextView_ReturnLoginActivity.setOnClickListener(this);
    }

    public void initView() {
        mTextView_Saying = (TextView) findViewById(R.id.id_tv_lock_saying);
        mTextView_Prompt = (TextView) findViewById(R.id.id_tv_lock_prompt);
//        user_head_image = (ImageView) findViewById(R.id.user_head_image);
        mTextView_ReturnLoginActivity = (TextView) findViewById(R.id.id_tv_lock_reverse_login);
        mTextView_ChangePassword = (TextView) findViewById(R.id.id_tv_lock_change_gesture_password);

        mFrameLayout_NinePoint = (FrameLayout) findViewById(R.id.nine_con);
        mImageView_rotation = (ImageView) findViewById(R.id.director_main_image_8);
    }


    @Override
    public void onClick(View v) {

    }
}
