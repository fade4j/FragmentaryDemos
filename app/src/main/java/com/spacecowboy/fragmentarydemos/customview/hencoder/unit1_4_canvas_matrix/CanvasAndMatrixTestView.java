package com.spacecowboy.fragmentarydemos.customview.hencoder.unit1_4_canvas_matrix;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Camera;
import android.graphics.Canvas;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.Rect;
import android.graphics.RectF;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;

import com.spacecowboy.fragmentarydemos.R;
import com.spacecowboy.fragmentarydemos.utils.L;
import com.spacecowboy.fragmentarydemos.utils.UiUtil;

/**
 * @author: SpaceCowboy
 * @date: 2017/10/7
 * @description:
 */

public class CanvasAndMatrixTestView extends View {

    private Paint mPaint;
    private Bitmap mSrcBitmap;

    public CanvasAndMatrixTestView(Context context) {
        this(context, null);
    }

    public CanvasAndMatrixTestView(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public CanvasAndMatrixTestView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    private void init() {
        mPaint = new Paint();
        mPaint.setAntiAlias(true);
        mPaint.setStyle(Paint.Style.FILL);
        mPaint.setDither(true);
        mPaint.setColor(UiUtil.getResColor(R.color.colorPrimary));
        mSrcBitmap = BitmapFactory.decodeResource(getResources(), R.drawable.source);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        int widthMode = MeasureSpec.getMode(widthMeasureSpec);
        int heightMode = MeasureSpec.getMode(heightMeasureSpec);

        int width = MeasureSpec.getSize(widthMeasureSpec);
        int height = MeasureSpec.getSize(heightMeasureSpec);

        if (widthMode == MeasureSpec.EXACTLY) {
            if (heightMode != MeasureSpec.EXACTLY) {
                height = width;
            }
        } else {
            if (heightMode == MeasureSpec.EXACTLY) {
                width = height;
            } else {
                width = height = Math.min(UiUtil.getScreenWidth(), UiUtil.getScreenHeight());

            }
            //setMeasuredDimension(width, height);
        }

        setMeasuredDimension(width, height);
        /*BitmapFactory.Options options = new BitmapFactory.Options();
        options.inJustDecodeBounds = true;
        BitmapFactory.decodeResource(getResources(), R.drawable.source, options);
        int bWidth = options.outWidth;
        int bHeight = options.outHeight;
        int min = Math.min(width, Math.min(bWidth, bHeight));*/
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        int width = getMeasuredWidth() / 2;
        int height = getMeasuredHeight() / 2;
        //RectF rect = new RectF((getMeasuredWidth() - width) / 2, (getMeasuredHeight() - height) / 2, width + (getMeasuredWidth() - width) / 2, getMeasuredHeight() / 2 + height / 2);
        //canvas.drawRoundRect(rect, 15, 15, mPaint);
        //canvas.clipRect(rect);
        canvas.save();
        //canvas.translate(200, 200);
        //canvas.scale(1.5f, 1.5f);
        //canvas.skew(0, 0.1f);
        //canvas.rotate(45);
        //canvas.drawColor(UiUtil.getResColor(R.color.colorPrimaryDark));
        //Matrix matrix = new Matrix();
        //matrix.postScale(0.5f, 0.5f);
        //matrix.postRotate(30);
        //canvas.concat(matrix);
        Camera camera = new Camera();
        camera.save();
        canvas.translate(width, height);
        camera.setLocation(0, 0, -60);
        camera.rotateX(30);
        camera.applyToCanvas(canvas);
        canvas.translate(-width, -height);
        camera.restore();

        canvas.drawBitmap(mSrcBitmap, 0, 0, mPaint);
        canvas.restore();
    }
}
