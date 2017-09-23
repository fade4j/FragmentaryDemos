package com.spacecowboy.fragmentarydemos.workingdemo.widgets;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapShader;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.Shader;
import android.graphics.drawable.BitmapDrawable;
import android.os.Handler;
import android.text.TextPaint;
import android.util.AttributeSet;
import android.view.Gravity;
import android.view.MotionEvent;
import android.view.VelocityTracker;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.spacecowboy.fragmentarydemos.R;
import com.spacecowboy.fragmentarydemos.utils.L;
import com.spacecowboy.fragmentarydemos.utils.UiUtil;
import com.spacecowboy.fragmentarydemos.workingdemo.HomeTabsBean;

import java.util.ArrayList;
import java.util.List;

/**
 * 作者： quietUncle on 2016/2/28
 * 项目地址:https://github.com/quietUncle/androidDemo
 */
public class SlidingArcView extends ViewGroup implements View.OnClickListener {
    public static String TAG = "QTView";
    //private String titles[] = {"跑步", "乒乓球", "排球", "攀岩", "骑马"};
    private List<SignView> views = new ArrayList<>();
    private int mTabCountToShow = 7;
    private final int CLASS_ID_SPECIAL = 2;

    public SlidingArcView(Context context) {
        this(context, null);
    }

    public SlidingArcView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    @Override
    protected void onLayout(boolean changed, int l, int t, int r, int b) {

    }

    //int mSize;
    private int mWidth;
    private int mHeight;

    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        //mSize = Math.min(getMeasuredWidth(), getMeasuredHeight());
        mWidth = MeasureSpec.getSize(widthMeasureSpec);
        mHeight = MeasureSpec.getSize(heightMeasureSpec);
        RADIUS = mHeight + mWidth * mWidth / (8 * mHeight);
        CentX = mWidth / 2;
        CentY = -RADIUS + mHeight;
        init();
    }

    private int itemHeight = UiUtil.dp2px(30);

    private void init() {
        removeAllViews();
        views.clear();
        for (int i = 0, len = mTabs.size(); i < len; i++) {
            final HomeTabsBean tab = mTabs.get(i);
            final TextView textView = getChildItem(tab.getTitle());
            SignView signView = new SignView(textView, i, tab.getClassId());
            views.add(signView);
            this.addView(textView);

            /*textView.setTag(tab);
            textView.setOnClickListener(this);*/
        }
        this.setClickable(true);
    }

    private TextView getChildItem(String title) {
        TextView textView = new TextView(getContext());
        textView.setGravity(Gravity.CENTER);
        textView.setText(title);
        textView.setTextColor(UiUtil.getResColor(R.color.black));
        TextPaint paint = new TextPaint();
        paint.setTextSize(UiUtil.sp2px(14));
        float measureText = paint.measureText(title);
        textView.setMinWidth((int) (measureText + UiUtil.dp2px(5)));
        return textView;
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        Paint paint = new Paint();
        paint.setStyle(Paint.Style.STROKE);
        paint.setColor(Color.GREEN);
        paint.setAntiAlias(true);
        paint.setTextSize(50f);
        for (SignView view : views) {
            view.flush();
        }
    }


    boolean canScroll = true;
    int lastX;
    int downPointId;
    int downX;
    int downY;

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        if (isAnimated) {//正在运行动画中
            return super.onTouchEvent(event);
        }
        if (mVelocityTracker == null) {
            mVelocityTracker = VelocityTracker.obtain();//获得VelocityTracker类实例
        }
        mVelocityTracker.addMovement(event);//将事件加入到VelocityTracker类实例中
        switch (event.getAction()) {
            case MotionEvent.ACTION_MOVE:
                if (canScroll) {
                    flushViews((int) (event.getX() - lastX));
                    lastX = (int) event.getX();
                    invalidate();
                }
                return true;
            case MotionEvent.ACTION_UP:
                //先判断是否是点击事件
                final int pi = event.findPointerIndex(downPointId);
                if (isClickable() && (Math.abs(event.getX(pi) - downX) <= 3) || Math.abs(event.getY(pi) - downY) <= 3) {
                    if (isFocusable() && isFocusableInTouchMode() && !isFocused())
                        requestFocus();
                    performViewClick();
                    return true;
                }
                //判断当ev事件是MotionEvent.ACTION_UP时：计算速率
                final VelocityTracker velocityTracker = mVelocityTracker;
                // 1000 provides pixels per second
                velocityTracker.computeCurrentVelocity(1, (float) 0.01);
                velocityTracker.computeCurrentVelocity(1000);//设置units的值为1000，意思为一秒时间内运动了多少个像素  
                if (velocityTracker.getXVelocity() > 2000 || velocityTracker.getXVelocity() < -2000) {//自动滚动最低要求
                    autoTime = (int) (velocityTracker.getXVelocity() / 1000 * 200);
                    autoTime = autoTime > 1500 ? 1500 : autoTime;
                    autoTime = autoTime < -1500 ? -1500 : autoTime;
                    isAnimated = true;
                    handler.sendEmptyMessageDelayed(1, 10);
                } else {
                    isAnimated = false;
                    resetView();
                }
                return true;
            case MotionEvent.ACTION_DOWN:
                downPointId = event.getPointerId(0);
                downX = lastX = (int) event.getX();
                downY = (int) event.getY();
                return true;
        }
        return super.onTouchEvent(event);
    }

    private void performViewClick() {
        for (SignView signView : views) {
            Rect r = new Rect(signView.centX - signView.size / 2, signView.centY - signView.size / 2 - viewTopChange, signView.centX + signView.size / 2, signView.centY + signView.size / 2 - viewTopChange);
            if (r.contains(downX, downY)) {
                if (qtItemClickListener != null && !isAnimated) {
                    isClick = true;
                    chooseView = signView;
                    autoScrollX = UiUtil.getScreenWidth() / 2 - signView.centX;
                    handler.sendEmptyMessageDelayed(0, 10);
                }
            }
        }
    }

    private void flushViews(int scrollX) {
        for (SignView view : views) {
            view.scroll(scrollX);
        }
    }


    //停止滚动，归位
    public void resetView() {
        for (SignView view : views) {
            if (view.centX > CentX && (view.centX - CentX < view.width)) {//屏幕右半部分移动运动，变小
                int dis = view.centX - CentX;
                if (dis > view.width / 2) {
                    autoScrollX = view.width - dis;
                } else {
                    autoScrollX = dis * -1;
                }
                break;
            }

        }
        handler.sendEmptyMessageDelayed(0, 10);
    }

    int veSpeed = 0;//松开自动滚动速度
    int autoTime = 0;//送开自动滚动
    int autoScrollX = 0;//归位滚动

    private Handler handler = new Handler() {
        public void handleMessage(android.os.Message msg) {
            switch (msg.what) {
                case 0:
                    if (autoScrollX != 0) {
                        if (Math.abs(autoScrollX) > SPEED) {
                            SPEED = Math.abs(SPEED);
                            if (autoScrollX > 0) {
                                autoScrollX -= SPEED;
                            } else {
                                autoScrollX += SPEED;
                                SPEED = SPEED * -1;
                            }
                            for (SignView view : views) {
                                view.scroll(SPEED);
                            }
                        } else {
                            for (SignView view : views) {
                                view.scroll(autoScrollX);
                            }
                            autoScrollX = 0;
                            isAnimated = false;
                            if (chooseView != null && qtScrollListener != null && lastChooseView != chooseView) {
                                if (!isClick) {
                                    qtScrollListener.onSelect(chooseView.view, chooseView.classId);
                                    lastChooseView = chooseView;
                                } else {
                                    qtItemClickListener.onClick(chooseView.view, chooseView.classId);
                                    lastChooseView = chooseView;
                                    isClick = false;
                                }
                            }
                        }
                        invalidate();
                        handler.sendEmptyMessageDelayed(0, 10);
                    }
                    break;
                case 1:
                    if (autoTime > 0) {
                        if (autoTime > 1500) {
                            veSpeed = 80;
                        } else if (autoTime > 1000) {
                            veSpeed = 80;
                        } else if (autoTime > 500) {
                            veSpeed = 40;
                        } else if (autoTime > 200) {
                            veSpeed = 20;
                        } else {
                            veSpeed = 10;
                        }
                        for (SignView view : views) {
                            view.scroll(veSpeed);
                        }
                        autoTime -= 20;
                        if (autoTime < 0) {
                            isAnimated = false;
                            autoTime = 0;
                        }
                        invalidate();
                        handler.sendEmptyMessageDelayed(1, 20);
                    } else if (autoTime < 0) {
                        if (autoTime < -1500) {
                            veSpeed = -80;
                        } else if (autoTime < -1000) {
                            veSpeed = -60;
                        } else if (autoTime < -500) {
                            veSpeed = -40;
                        } else if (autoTime < -200) {
                            veSpeed = -20;
                        } else {
                            veSpeed = -10;
                        }
                        for (SignView view : views) {
                            view.scroll(veSpeed);
                        }
                        autoTime += 20;
                        if (autoTime > 0) {
                            isAnimated = false;
                            autoTime = 0;
                        }
                        invalidate();
                        handler.sendEmptyMessageDelayed(1, 20);
                    } else {
                        resetView();
                        invalidate();
                    }
                    break;
                default:
                    break;
            }
        }

        ;
    };

    /**
     * listener
     */
    QTScrollListener qtScrollListener;
    QTItemClickListener qtItemClickListener;

    @Override
    public void onClick(View v) {
        Object tab = v.getTag();
        if (tab != null && tab instanceof HomeTabsBean) {
            if (qtItemClickListener != null) {
                qtItemClickListener.onClick(v, ((HomeTabsBean) tab).getClassId());
            }
        }

    }

    public interface QTScrollListener {
        void onSelect(View v, int index);
    }

    public interface QTItemClickListener {
        void onClick(View v, int index);
    }

    public void setQtScrollListener(QTScrollListener qtScrollListener) {
        this.qtScrollListener = qtScrollListener;
    }

    public void setQtItemClickListener(QTItemClickListener qtItemClickListener) {
        this.qtItemClickListener = qtItemClickListener;
    }

    private boolean isAnimated = false;//是否正在动画中
    private int viewTopChange = UiUtil.dp2px(10);//view往上偏移的位置
    private VelocityTracker mVelocityTracker;//速度跟踪
    private int SPEED = 30;//归位自动滚动速度
    private SignView leftView;//屏幕最左边的viwe
    private SignView rightView;//屏幕最右边的view
    private int CentX;//外层圆中心x
    private int CentY;//外层圆中心Y
    private int RADIUS;//外层圆半径
    private static final int VX = 50;//第一个view的x
    private SignView chooseView;
    private SignView lastChooseView;
    private boolean isClick = false;

    private class SignView {
        private int indexInScreen;//在屏幕中的位置
        private TextView view;
        private String title;
        private int centX;//view的中心点坐标
        private int centY;
        private int index;
        private int size;//view大小
        private int width = (UiUtil.getScreenWidth()) / mTabCountToShow;
        private float normalScale = 1.0f;
        private float maxScale = 0.2f;
        private boolean stop;//停止滚动，用来判断是否自动进行归位
        private boolean isChoose = false;
        private int classId;

        public SignView(TextView v, final int index, int classId) {
            this.classId = classId;
            this.index = index;
            this.view = v;
            this.title = mTabs.get(index).getTitle();
            if (index == 0) {
                leftView = this;
            }
            if (index == 6) {
                rightView = this;
            }
            if (classId == 2) {
                isChoose = true;
                chooseView = this;
            }
            size = v.getMinWidth();
            L.e("SIGNVIEW", "size = " + size);
            initView();
        }

        //计算view的坐标
        private void initView() {
            centX = (width) / 2 + width * index;
            L.e(RADIUS + " ,CentY " + CentY + " ,centX " + centX + " ,CentX " + CentX);
            centY = CentY + (int) Math.sqrt(Math.pow(RADIUS, 2) - Math.pow((centX - CentX), 2));
        }

        public void scroll(int scrollX) {
            this.centX += scrollX;
            centY = CentY + (int) Math.sqrt(Math.pow(RADIUS, 2) - Math.pow((centX - CentX), 2));
        }

        public int getCentX() {
            return centX;
        }

        public int getCentY() {
            return centY;
        }

        public View getView() {
            return view;
        }

        public void flush() {
            clean();
            //每次计算view的位置
            view.layout(centX - size / 2, centY - itemHeight / 2 - viewTopChange, centX + size / 2, centY + itemHeight / 2 - viewTopChange);
            L.e(centX + " , " + centY);
            if (Math.abs(CentX - centX) <= size / 2) {
                isChoose = true;
            } else {
                isChoose = false;
            }
            if (isChoose) {
                chooseView = this;
                view.setScaleX((normalScale + maxScale));
                view.setScaleY((normalScale + maxScale));
            } else {
                view.setScaleX((normalScale));
                view.setScaleY((normalScale));
            }
        }


        //无限循环的判断
        private void clean() {
            if (leftView.notLeftView()) {//最左边没有view了，把最右边的移到最左边
                rightView.centX = leftView.centX - width;
                rightView.changeY();
                leftView = rightView;
                rightView = views.get(rightView.index == 0 ? views.size() - 1 : rightView.index - 1);
            }
            if (rightView.notRightView()) {//最右边没有view了，把最左边的移到最右边
                leftView.centX = rightView.centX + width;
                leftView.changeY();
                rightView = leftView;
                leftView = views.get(leftView.index == views.size() - 1 ? 0 : leftView.index + 1);
            }
        }

        //重新计算Y点坐标
        public void changeY() {
            centY = CentY + (int) Math.sqrt(Math.pow(RADIUS, 2) - Math.pow((centX - CentX), 2));
        }

        public boolean notLeftView() {
            return centX - width / 2 > width / 2;
        }

        public boolean notRightView() {
            return centX + width / 2 + width / 2 < UiUtil.getScreenWidth();
        }
    }

    private ArrayList<HomeTabsBean> mTabs = new ArrayList<>();

    /*public void setData(ArrayList<HomeTabsBean> data){

    }*/
    public void notifyDataSetChanged(ArrayList<HomeTabsBean> data) {
        mTabs.clear();
        mTabs.addAll(data);
        init();
        invalidate();
    }
}
