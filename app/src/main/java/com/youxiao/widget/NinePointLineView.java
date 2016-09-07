package com.youxiao.widget;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Paint.Cap;
import android.graphics.Typeface;
import android.os.Handler;
import android.os.Message;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;

import com.youxiao.ui.activity.login.LockActivity;
import com.youxiao.R;

/**
 * 作用：手势密码的九宫格
 * 作者：ufnind
 * 时间：2013年10月29日 09:34:52
 */
public class NinePointLineView extends View {


    Context context;

    Paint linePaint = new Paint();

    Paint redLinePaint = new Paint();

    Paint textPaint = new Paint();

    Bitmap defaultBitmap = BitmapFactory.decodeResource(getResources(), R.drawable.gesture_normal);

    int defaultBitmapRadius = defaultBitmap.getWidth() / 2;

    Bitmap selectedBitmap = BitmapFactory.decodeResource(getResources(), R.drawable.gesture_drawing);

    int selectedBitmapDiameter = selectedBitmap.getWidth();

    int selectedBitmapRadius = selectedBitmapDiameter / 2;

    PointInfo[] points = new PointInfo[9];

    PointInfo startPoint = null;

    int width, height;

    int moveX, moveY;

    boolean isUp = false;

    boolean isCheck;
    boolean loginCheck;
    Handler handler;

    LockActivity lockActivity;

    StringBuffer lockString = new StringBuffer();

    public NinePointLineView(Context context, Handler handler) {

        super(context);

        this.context = context;
        this.handler = handler;
//		this.setBackgroundColor(Color.WHITE);//设置整个背景
        initPaint();
    }

    public boolean isLoginCheck() {
        return loginCheck;
    }

    public void setLoginCheck(boolean loginCheck) {
        this.loginCheck = loginCheck;
    }

    public NinePointLineView(Context context, AttributeSet attrs) {

        super(context, attrs);
//		this.setBackgroundColor(Color.WHITE);//设置整个九宫格的背景
        initPaint();
    }

    public LockActivity getLockActivity() {
        return lockActivity;
    }

    public void setLockActivity(LockActivity activity) {
        this.lockActivity = activity;
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        width = getWidth();
        height = getHeight();
        if (width != 0 && height != 0) {
            initPoints(points);
        }
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);

    }

    @Override
    protected void onLayout(boolean changed, int left, int top, int right, int bottom) {
        super.onLayout(changed, left, top, right, bottom);

    }

    private int startX = 0, startY = 0;

    @Override
    protected void onDraw(Canvas canvas) {
        // canvas.drawText("passwd:" + lockString, 0, 40, textPaint);
        if (moveX != 0 && moveY != 0 && startX != 0 && startY != 0) {
            // drawLine(canvas, startX, startY, moveX, moveY);
        }
        drawNinePoint(canvas);
        super.onDraw(canvas);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        boolean flag = true;
        if (isUp) {
            finishDraw();
            flag = false;
        } else {
            handlingEvent(event);
            flag = true;
        }
        return flag;
    }

    private void handlingEvent(MotionEvent event) {

        switch (event.getAction()) {
            case MotionEvent.ACTION_MOVE:
                moveX = (int) event.getX();
                moveY = (int) event.getY();
                for (PointInfo temp : points) {
                    if (temp.isInMyPlace(moveX, moveY) && temp.isSelected() == false) {
                        temp.setSelected(true);
                        startX = temp.getCenterX();
                        startY = temp.getCenterY();
                        int len = lockString.length();
                        if (len != 0) {
                            int preId = lockString.charAt(len - 1) - 48;
                            points[preId].setNextId(temp.getId());
                        }
                        lockString.append(temp.getId());
                        break;
                    }
                }
                invalidate(0, height - width, width, height);
                break;

            case MotionEvent.ACTION_DOWN:
                int downX = (int) event.getX();
                int downY = (int) event.getY();

                for (PointInfo temp : points) {
                    if (temp.isInMyPlace(downX, downY)) {
                        temp.setSelected(true);
                        startPoint = temp;
                        startX = temp.getCenterX();
                        startY = temp.getCenterY();
                        lockString.append(temp.getId());
                        break;
                    }
                }
                invalidate(0, height - width, width, height);
                break;

            case MotionEvent.ACTION_UP:
                startX = startY = moveX = moveY = 0;
                isUp = true;
                invalidate();
                Message msg = new Message();
                msg.obj = "清空";
                msg.what = 10;
                handler.sendMessage(msg);
                break;
            default:
                break;
        }
    }

    private void finishDraw() {
        for (PointInfo temp : points) {
            temp.setSelected(false);
            temp.setNextId(temp.getId());
        }
        lockString.delete(0, lockString.length());
        isUp = false;
        invalidate();
    }

    private void initPoints(PointInfo[] points) {
        int len = points.length;
        int seletedSpacing = (width - selectedBitmapDiameter * 3) / 4;
        int seletedX = seletedSpacing;
        int seletedY = height - width + seletedSpacing;
        int defaultX = seletedX + selectedBitmapRadius - defaultBitmapRadius;
        int defaultY = seletedY + selectedBitmapRadius - defaultBitmapRadius;

        for (int i = 0; i < len; i++) {
            if (i == 3 || i == 6) {
                seletedX = seletedSpacing;
                seletedY += selectedBitmapDiameter + seletedSpacing;
                defaultX = seletedX + selectedBitmapRadius - defaultBitmapRadius;
                defaultY += selectedBitmapDiameter + seletedSpacing;
            }
            points[i] = new PointInfo(i, defaultX, defaultY, seletedX, seletedY);
            seletedX += selectedBitmapDiameter + seletedSpacing;
            defaultX += selectedBitmapDiameter + seletedSpacing;

        }
    }

    private void initPaint() {
        initLinePaint(linePaint);
        initTextPaint(textPaint);
        initWhiteLinePaint(redLinePaint);

    }

    /**
     * @param paint
     */
    private void initTextPaint(Paint paint) {
        textPaint.setTextSize(30);
        textPaint.setAntiAlias(true);
        textPaint.setTypeface(Typeface.MONOSPACE);

    }

    /**
     * @param paint
     */
    private void initLinePaint(Paint paint) {
        paint.setColor(getResources().getColor(R.color.red400));
        paint.setStrokeWidth(4);//设置两个点之间的线的北京的宽度
        paint.setAntiAlias(true);
        paint.setStrokeCap(Cap.ROUND);

    }

    /**
     * @param paint
     */
    private void initWhiteLinePaint(Paint paint) {
        paint.setColor(getResources().getColor(R.color.red400));
        paint.setStrokeWidth(2);//设置两个点之间的线的宽度
        paint.setAntiAlias(true);
        paint.setStrokeCap(Cap.ROUND);

    }

    /**
     * @param canvas
     */
    private void drawNinePoint(Canvas canvas) {
        if (startPoint != null) {
            drawEachLine(canvas, startPoint);
        }

        for (PointInfo pointInfo : points) {

            if (pointInfo != null) {
                canvas.drawBitmap(defaultBitmap, pointInfo.getDefaultX(),pointInfo.getDefaultY(), null);
                if (pointInfo.isSelected()) {
                    canvas.drawBitmap(selectedBitmap, pointInfo.getDefaultX(),pointInfo.getDefaultY(), null);
                    Message msg = new Message();
                    msg.obj = pointInfo.getId();
                    msg.what = 9;
                    handler.sendMessage(msg);
                }

            }

        }

    }

    /**
     * @param canvas
     * @param point
     */
    private void drawEachLine(Canvas canvas, PointInfo point) {
        if (point.hasNextId()) {
            int n = point.getNextId();
            drawLine(canvas, point.getCenterX(), point.getCenterY(),
                    points[n].getCenterX(), points[n].getCenterY());
            drawEachLine(canvas, points[n]);
        }
    }

    /**
     * @param canvas
     * @param startX
     * @param startY
     * @param stopX
     * @param stopY
     */
    private void drawLine(Canvas canvas, float startX, float startY,float stopX, float stopY) {
        canvas.drawLine(startX, startY, stopX, stopY, linePaint);
        canvas.drawLine(startX, startY, stopX, stopY, redLinePaint);

    }

    /**
     * @author zkwlx
     */
    private class PointInfo {

        private int id;

        private int nextId;

        private boolean selected;

        private int defaultX;

        private int defaultY;

        private int seletedX;

        private int seletedY;

        public PointInfo(int id, int defaultX, int defaultY, int seletedX,int seletedY) {
            this.id = id;
            this.nextId = id;
            this.defaultX = defaultX;
            this.defaultY = defaultY;
            this.seletedX = seletedX;
            this.seletedY = seletedY;
        }

        public boolean isSelected() {
            return selected;
        }

        public void setSelected(boolean selected) {
            this.selected = selected;
        }

        public int getId() {
            return id;
        }

        public int getDefaultX() {
            return defaultX;
        }

        public int getDefaultY() {
            return defaultY;
        }

        public int getSeletedX() {
            return seletedX;
        }

        public int getSeletedY() {
            return seletedY;
        }

        public int getCenterX() {
            return seletedX + selectedBitmapRadius;
        }

        public int getCenterY() {
            return seletedY + selectedBitmapRadius;
        }

        public boolean hasNextId() {
            return nextId != id;
        }

        public int getNextId() {
            return nextId;
        }

        public void setNextId(int nextId) {
            this.nextId = nextId;
        }

        /**
         * @param x
         * @param y
         */
        public boolean isInMyPlace(int x, int y) {
            boolean inX = x > seletedX
                    && x < (seletedX + selectedBitmapDiameter);
            boolean inY = y > seletedY
                    && y < (seletedY + selectedBitmapDiameter);

            return (inX && inY);
        }

    }

    public String getPwd() {//获取本次的密码
        return lockString.toString();

    }


}
