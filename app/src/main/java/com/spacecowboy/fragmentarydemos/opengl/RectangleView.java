package com.spacecowboy.fragmentarydemos.opengl;

import android.content.Context;
import android.opengl.GLSurfaceView;
import android.util.AttributeSet;

/**
 * @author: SpaceCowboy
 * @date: 2017/12/2
 * @description:
 */

public class RectangleView extends GLSurfaceView {
    public RectangleView(Context context) {
        super(context);
        init(context);
    }

    public RectangleView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context);
    }


    private void init(Context context) {
        setEGLContextClientVersion(2);
        setRenderer(new RectangleRender(-0.3f, 0.5f, 0.5f, -0.3f, 0, 5));
    }
}
