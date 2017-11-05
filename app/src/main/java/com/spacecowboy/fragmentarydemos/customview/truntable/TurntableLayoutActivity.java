package com.spacecowboy.fragmentarydemos.customview.truntable;

import android.view.View;

import com.spacecowboy.fragmentarydemos.R;
import com.spacecowboy.fragmentarydemos.app.BaseActivity;
import com.spacecowboy.fragmentarydemos.utils.UiUtil;

/**
 * @author: SpaceCowboy
 * @date: 2017/11/5
 * @description:
 */

public class TurntableLayoutActivity extends BaseActivity {
    @Override
    protected void initView(View view) {

    }

    @Override
    protected View getSubContentView() {
        return UiUtil.inflate(this, R.layout.activity_turntable_test);
    }

    @Override
    public void onClick(View v) {

    }
}
