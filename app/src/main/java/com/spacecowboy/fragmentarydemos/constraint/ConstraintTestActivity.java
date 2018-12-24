package com.spacecowboy.fragmentarydemos.constraint;

import android.view.View;

import com.spacecowboy.fragmentarydemos.R;
import com.spacecowboy.fragmentarydemos.app.BaseActivity;
import com.spacecowboy.fragmentarydemos.utils.UiUtil;

/**
 * @author: SpaceCowboy
 * @date: 2018/3/4
 * @description:
 */

public class ConstraintTestActivity extends BaseActivity {
    @Override
    protected void initView(View view) {

    }

    @Override
    protected View getSubContentView() {
        return UiUtil.inflate(this, R.layout.activity_constraint_test);
    }

    @Override
    public void onClick(View v) {

    }
}
