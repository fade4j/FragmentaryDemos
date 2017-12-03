package com.spacecowboy.fragmentarydemos.opengl;

import android.opengl.GLES20;
import android.opengl.GLSurfaceView;
import android.opengl.Matrix;

import com.spacecowboy.fragmentarydemos.utils.L;

import javax.microedition.khronos.egl.EGLConfig;
import javax.microedition.khronos.opengles.GL10;

/**
 * @author: SpaceCowboy
 * @date: 2017/12/2
 * @description:
 */

public class RectangleRender implements GLSurfaceView.Renderer {

    private Rectangle mRectangle;
    private float mNear;
    private float mFar;
    private float[] mProjectionMatrix;

    public RectangleRender(float left, float top, float right, float bottom, float near, float far) {
        mNear = near;
        mFar = far;
        float rl = 2.0f / (right - left);
        float tb = 2.0f / (top - bottom);
        float fn = -2.0f / (far - near);
        mProjectionMatrix = new float[]{
                rl, 0, 0, 0,
                0, tb, 0, 0,
                0, 0, fn, 0,
                0, 0, 0, 1
        };
    }

    @Override
    public void onSurfaceCreated(GL10 gl, EGLConfig config) {
        GLES20.glClearColor(1, 0, 0, 0);
        mRectangle = new Rectangle();
        GLES20.glEnable(GLES20.GL_DEPTH_TEST);
    }

    @Override
    public void onSurfaceChanged(GL10 gl, int width, int height) {
        L.e("RECTANGLERENDER", width + " -- " + height);
        GLES20.glViewport(100, 100, 300, 300);
        if (width > height) {
            float ratio = height * 1.0f / width;
            Matrix.orthoM(mProjectionMatrix, 0, -ratio, ratio, -1, 1, mNear, mFar);
        } else {
            float ratio = width * 1.0f / height;
            Matrix.orthoM(mProjectionMatrix, 0, -1, 1, -ratio, ratio, mNear, mFar);
        }
    }

    @Override
    public void onDrawFrame(GL10 gl) {
        GLES20.glClear(GLES20.GL_COLOR_BUFFER_BIT | GLES20.GL_DEPTH_BUFFER_BIT);
        mRectangle.draw();
    }
}
