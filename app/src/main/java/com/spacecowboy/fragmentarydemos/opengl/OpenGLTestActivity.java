package com.spacecowboy.fragmentarydemos.opengl;

import android.view.View;
import android.widget.LinearLayout;

import com.spacecowboy.fragmentarydemos.R;
import com.spacecowboy.fragmentarydemos.app.BaseActivity;
import com.spacecowboy.fragmentarydemos.utils.UiUtil;

/**
 * @author: SpaceCowboy
 * @date: 2017/12/2
 * @description:
 */

public class OpenGLTestActivity extends BaseActivity {
    @Override
    protected void initView(View view) {

    }

    @Override
    protected View getSubContentView() {
        //RectangleView view = new RectangleView(this);
        //LinearLayout container = (LinearLayout) UiUtil.inflate(this, R.layout.activity_opengl_test);
        //container.addView(view);
        return UiUtil.inflate(this, R.layout.activity_opengl_test);
    }

    @Override
    public void onClick(View v) {

    }
}
