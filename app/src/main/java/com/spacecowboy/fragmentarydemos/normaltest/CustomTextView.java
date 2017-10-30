package com.spacecowboy.fragmentarydemos.normaltest;

import android.content.Context;
import android.graphics.Canvas;
import android.support.annotation.Nullable;
import android.support.v7.widget.AppCompatTextView;
import android.util.AttributeSet;

import com.spacecowboy.fragmentarydemos.utils.L;

/**
 * @author: SpaceCowboy
 * @date: 2017/10/26
 * @description:
 */

public class CustomTextView extends AppCompatTextView {
    private OnDrawFinishedListener mDrawFinishedListener;

    public CustomTextView(Context context) {
        super(context);
    }

    public CustomTextView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public CustomTextView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        L.e("HandlerThreadTestActivity", "  ---- onMeasure");
    }

    @Override
    protected void onLayout(boolean changed, int left, int top, int right, int bottom) {
        super.onLayout(changed, left, top, right, bottom);
        L.e("HandlerThreadTestActivity", "  ---- onLayout");
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        //L.e("HandlerThreadTestActivity", "  ---- onDraw");
        if (mDrawFinishedListener != null) {
            mDrawFinishedListener.onDrawFinished();
        }
    }

    public void setDrawFinishedListener(OnDrawFinishedListener listener) {
        mDrawFinishedListener = listener;
    }
}
