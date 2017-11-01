package com.spacecowboy.fragmentarydemos.normaltest;

import android.opengl.ETC1;
import android.view.View;
import android.widget.EditText;

import com.spacecowboy.fragmentarydemos.R;
import com.spacecowboy.fragmentarydemos.app.BaseActivity;
import com.spacecowboy.fragmentarydemos.utils.L;
import com.spacecowboy.fragmentarydemos.utils.UiUtil;

import butterknife.BindView;

/**
 * @author: SpaceCowboy
 * @date: 2017/10/30
 * @description:
 */

public class EditTextTestActivity extends BaseActivity {
    @BindView(R.id.root_edittext_activity)
    View mRoot;
    @BindView(R.id.et_input)
    EditText mEtInput;

    @Override
    protected void initView(View view) {
        L.e(NAME, "initView");
        mRoot.setOnClickListener(this);
        mEtInput.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                L.e(NAME, "hasFocus = " + hasFocus);
                if (!hasFocus) {
                    UiUtil.closeInputMethod(mEtInput);
                }
            }
        });
        mEtInput.requestFocus();
        UiUtil.openInputMethod(mEtInput);
    }

    @Override
    protected View getSubContentView() {
        return UiUtil.inflate(this, R.layout.activity_edittext_test);
    }

    @Override
    public void onClick(View v) {
        mEtInput.clearFocus();
        UiUtil.closeInputMethod(mEtInput);
        String text = mEtInput.getText().toString();
        String[] tArray = text.split(",");
        L.e(NAME, "length = " + tArray.length);
        for (int i = 0; i < tArray.length; i++) {
            L.e(NAME, "" + tArray[i]);
        }
    }
}
