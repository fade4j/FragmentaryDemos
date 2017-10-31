package com.spacecowboy.fragmentarydemos.customview.circleimage;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.BitmapShader;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffXfermode;
import android.graphics.Shader;
import android.graphics.Xfermode;
import android.support.annotation.Nullable;
import android.util.AttributeSet;

import com.spacecowboy.fragmentarydemos.R;
import com.spacecowboy.fragmentarydemos.utils.UiUtil;

/**
 * @author: SpaceCowboy
 * @date: 2017/10/10
 * @description:
 */

public class CircleImageView extends android.support.v7.widget.AppCompatImageView {
    private ImageType mType = ImageType.CIRCLE;
    private Paint mPatint;
    private Bitmap mSrcBitmap;

    public CircleImageView(Context context) {
        this(context, null);
    }

    public CircleImageView(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public CircleImageView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);

        init(context);
    }

    private void init(Context context) {
        mPatint = new Paint(Paint.ANTI_ALIAS_FLAG);
        mSrcBitmap = BitmapFactory.decodeResource(getResources(), R.drawable.source);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        int width = MeasureSpec.getSize(widthMeasureSpec);
        int height = MeasureSpec.getSize(heightMeasureSpec);
        int widthMode = MeasureSpec.getMode(widthMeasureSpec);
        int heightMode = MeasureSpec.getMode(heightMeasureSpec);

        if (widthMode == MeasureSpec.EXACTLY) {
            if (heightMode != MeasureSpec.EXACTLY) {
                height = width;
            } else {
                int min = Math.min(width, height);
                width = height = min;
            }
        } else {
            if (heightMode == MeasureSpec.EXACTLY) {
                width = height = Math.min(UiUtil.getScreenWidth(), Math.min(height, UiUtil.getScreenHeight()));
            } else {
                width = height = UiUtil.dp2px(150);
            }
        }

        setMeasuredDimension(width, height);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        if (mType == ImageType.NORMAL) {
            return;
        }

        if (mType == ImageType.CIRCLE) {
            //drawCircleImageByBitmapShader(canvas);
            drawCircleImageByXfermode(canvas);
        }
    }

    /**
     * 使用Xfermode画圆形Image
     * @param canvas
     */
    private void drawCircleImageByXfermode(Canvas canvas) {
        canvas.save();
        canvas.saveLayer(null, null, Canvas.ALL_SAVE_FLAG);
        canvas.drawCircle(getWidth() / 2, getWidth() / 2, getWidth() / 2, mPatint);
        Xfermode xfermode = new PorterDuffXfermode(PorterDuff.Mode.SRC_IN);
        mPatint.setXfermode(xfermode);
        canvas.drawBitmap(mSrcBitmap,0,0,mPatint);
        mPatint.setXfermode(null);
        canvas.restore();
    }
    /**
     * 使用BitmapShader画圆形Image
     * @param canvas
     */
    private void drawCircleImageByBitmapShader(Canvas canvas) {
        canvas.save();
        BitmapShader shader = new BitmapShader(mSrcBitmap, Shader.TileMode.CLAMP, Shader.TileMode.CLAMP);
        mPatint.setShader(shader);
        canvas.drawCircle(getWidth() / 2, getWidth() / 2, getWidth() / 2, mPatint);
        canvas.restore();
    }

    enum ImageType {
        NORMAL(0), ROUNT_RECT(1), CIRCLE(2);

        ImageType(int type) {

        }
    }
}
