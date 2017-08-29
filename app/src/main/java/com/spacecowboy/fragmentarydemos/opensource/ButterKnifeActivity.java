package com.spacecowboy.fragmentarydemos.opensource;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.spacecowboy.fragmentarydemos.R;
import com.spacecowboy.fragmentarydemos.app.BaseActivity;
import com.spacecowboy.fragmentarydemos.utils.UiUtil;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * Created by SpaceCowboy on 2017/8/26.
 */

public class ButterKnifeActivity extends BaseActivity {
    @BindView(R.id.tv_butter_knife)
    TextView mTvButter;

    @BindView(R.id.tv_butter_knife1)
    TextView mTvButter1;

    @Override
    protected void initView(View view) {
        mTvButter.setText("ButterKnife");
    }

    @Override
    protected View getSubContentView() {
        return UiUtil.inflate(this, R.layout.activity_butter_knife);
    }

    @OnClick({R.id.tv_butter_knife, R.id.tv_butter_knife1})
    public void onClick(View view) {
        if (view.getId() == R.id.tv_butter_knife) {
            mTvButter1.setText("mTvButter1");
        } else {
            //Toast.makeText(this, "mTvButter1 onClick", Toast.LENGTH_SHORT).show();
            UiUtil.showToast("mTvButter1 onClick");
        }
    }
}
