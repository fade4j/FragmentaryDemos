package com.spacecowboy.fragmentarydemos.customview.truntable;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.Gravity;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.spacecowboy.fragmentarydemos.R;
import com.spacecowboy.fragmentarydemos.utils.L;
import com.spacecowboy.fragmentarydemos.utils.UiUtil;

import java.util.ArrayList;

/**
 * @author: SpaceCowboy
 * @date: 2017/11/5
 * @description:
 */

public class TurntableLayout extends ViewGroup {
    public TurntableLayout(Context context) {
        this(context, null);
    }

    public TurntableLayout(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public TurntableLayout(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    private ArrayList<Data> mDatas;
    private int mVolocity = 15;// 转盘转动的最小速度
    private int mRadius;
    private int mChildrenMaxWidth = UiUtil.dp2px(100);
    private Paint mPaint;
    private int mChildrenCount;
    private int mCenterX;
    private int mCenterY;


    private void init() {
        mRadius = (UiUtil.getScreenWidth() - mChildrenMaxWidth) / 2;
        mPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        mPaint.setColor(UiUtil.getResColor(R.color.colorPrimary));
        mPaint.setStyle(Paint.Style.FILL);
        mPaint.setStrokeWidth(UiUtil.dp2px(3));
        mDatas = new ArrayList<>();
        for (int i = 0; i < 5; i++) {
            TextView textView = new TextView(getContext());
            textView.setWidth(mChildrenMaxWidth);
            textView.setHeight(mChildrenMaxWidth);
            textView.setText("数据".concat(String.valueOf(i + 1)));
            textView.setGravity(Gravity.CENTER);
            textView.setTextSize(30);
            textView.setBackgroundColor(UiUtil.getResColor(R.color.colorAccent));
            //Data data = new Data();
            //data.mLabel = "数据".concat(String.valueOf(i + 1));
            //mDatas.add(data);
            addView(textView);
        }
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        int width = MeasureSpec.getSize(widthMeasureSpec);
        int widthMode = MeasureSpec.getMode(widthMeasureSpec);
        if (widthMode == MeasureSpec.AT_MOST || widthMode == MeasureSpec.UNSPECIFIED) {
            width = UiUtil.getScreenWidth() - mChildrenMaxWidth;
        }
        mRadius = (width - mChildrenMaxWidth) / 2;
        mChildrenCount = getChildCount();
        L.e("TurntableLayout", "onMeasure mChildrenCount = " + mChildrenCount);
        mCenterX = getMeasuredWidth() / 2;
        mCenterY = getMeasuredHeight() / 2;

        L.e("UNSPECIFIED", mCenterX + " , " + mCenterY);
        setMeasuredDimension(width, width);
    }

    @Override
    protected void onLayout(boolean changed, int l, int t, int r, int b) {
        for (int i = 0; i < mChildrenCount; i++) {
            View childAt = getChildAt(i);
            int cx = (int) (mCenterX + mRadius * Math.cos(i * 360 * Math.PI / mChildrenCount / 180));
            int cy = (int) (mCenterY + mRadius * Math.sin(i * 360 * Math.PI / mChildrenCount / 180));
            L.e("TurntableLayout", "onLayout" + i + " ：" + cx + " , " + cy);
            childAt.layout(cx - mChildrenMaxWidth / 2, cy - mChildrenMaxWidth / 2, cx + mChildrenMaxWidth / 2, cy + mChildrenMaxWidth / 2);
        }
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        L.e("TurntableLayout", "onDraw");
        canvas.drawCircle(mCenterX, mCenterY, mRadius, mPaint);
        View childAt = getChildAt(0);
        L.e("TurntableLayout", "onDraw " + childAt.getLeft() + "," + childAt.getTop() + " , " + childAt.getRight() + " , " + childAt.getBottom());
    }

    private float mLastX;
    private float mLastY;
    private boolean isOver = true;

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        float x = event.getX();
        float y = event.getY();
        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:
                mLastX = x;
                mLastY = y;
                return isOver;

            case MotionEvent.ACTION_MOVE:
                float dx = x - mLastX;
                float dy = y - mLastY;

                if (Math.abs(dx) > Math.abs(dy)) {
                    if (dx > 0) {
                        // 逆时针转动
                        if (y < mCenterY){
                            mVolocity = 5;
                        } else {

                            mVolocity = -5;
                        }
                    } else {
                        // 顺时针转动
                        if (y < mCenterY){
                            mVolocity = -5;
                        } else {

                            mVolocity = 5;
                        }
                    }

                } else {
                    if (dy > 0) {
                        // 顺时针转动
                        mVolocity = 5;
                        if (x < mCenterX){
                            mVolocity = -5;
                        } else {

                            mVolocity = 5;
                        }
                    } else {
                        // 逆时针转动
                        if (x < mCenterX){
                            mVolocity = 5;
                        } else {

                            mVolocity = -5;
                        }
                    }
                }
                int temp = 0;
                isOver = false;
                while (Math.abs((temp += mVolocity)) < 300) {
                    for (int i = 0; i < mChildrenCount; i++) {
                        View childAt = getChildAt(i);
                        int cx = (int) (mCenterX + mRadius * Math.cos((i * 360 / mChildrenCount + mVolocity) * Math.PI / 180));
                        int cy = (int) (mCenterY + mRadius * Math.sin((i * 360 / mChildrenCount + mVolocity) * Math.PI / 180));
                        L.e("TurntableLayout", "onLayout" + i + " ：" + cx + " , " + cy);
                        childAt.layout(cx - mChildrenMaxWidth / 2, cy - mChildrenMaxWidth / 2, cx + mChildrenMaxWidth / 2, cy + mChildrenMaxWidth / 2);
                    }
                }

                isOver = true;
                return false;

            case MotionEvent.ACTION_UP:
                break;
        }


        return super.onTouchEvent(event);
    }
}
