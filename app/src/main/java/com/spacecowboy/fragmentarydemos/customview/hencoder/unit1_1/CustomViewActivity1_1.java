package com.spacecowboy.fragmentarydemos.customview.hencoder.unit1_1;

import android.view.View;
import android.widget.TextView;

import com.spacecowboy.fragmentarydemos.app.BaseActivity;

/**
 * Created by SpaceCowboy on 2017/8/26.
 */

public class CustomViewActivity1_1 extends BaseActivity {

    private TextView textView;

    @Override
    protected void onStart() {
        super.onStart();

        textView.setText("" + getIntent().getIntExtra("ID", 5));
    }

    @Override
    protected void initView(View view) {

    }

    @Override
    protected View getSubContentView() {
        textView = new TextView(this);
        textView.setText(this.getClass().getSimpleName());
        textView.setTextScaleX(52);
        return textView;
    }
}
