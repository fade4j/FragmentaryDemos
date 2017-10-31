package com.spacecowboy.fragmentarydemos.customview.circleimage;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import com.spacecowboy.fragmentarydemos.R;
import com.spacecowboy.fragmentarydemos.app.BaseActivity;
import com.spacecowboy.fragmentarydemos.utils.UiUtil;

public class CircleImageTestActivity extends BaseActivity {

    @Override
    protected void initView(View view) {

    }

    @Override
    protected View getSubContentView() {
        return UiUtil.inflate(this, R.layout.activity_circle_image_test);
    }

    @Override
    public void onClick(View v) {

    }
}
