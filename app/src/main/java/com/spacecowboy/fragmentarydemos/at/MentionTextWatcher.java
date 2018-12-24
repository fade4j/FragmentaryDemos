package com.spacecowboy.fragmentarydemos.at;

import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;

/**
 * @author: SpaceCowboy
 * @date: 2018/12/24
 * @description:
 */
public class MentionTextWatcher implements TextWatcher {
    private OnAtInputListener onAtInputListener;
    private final AtEditText atEditText;

    public MentionTextWatcher(AtEditText editText) {
        atEditText = editText;
    }

    //若从整串string中间插入字符，需要将插入位置后面的range相应地挪位
    @Override
    public void beforeTextChanged(CharSequence s, int start, int count, int after) {
    }

    @Override
    public void onTextChanged(CharSequence charSequence, int index, int i1, int count) {
        if (atEditText.isChecking()) {
            return;
        }
        if (count == 1 && !TextUtils.isEmpty(charSequence)) {
            char mentionChar = charSequence.toString().charAt(index);
            if ('@' == mentionChar && onAtInputListener != null) {
                onAtInputListener.onAtCharacterInput();
            }
        }
        atEditText.removeCallbacks(atEditText);
        atEditText.postDelayed(atEditText, 500);
    }

    @Override
    public void afterTextChanged(Editable editable) {
    }

    public void setOnAtInputListener(OnAtInputListener onAtInputListener) {
        this.onAtInputListener = onAtInputListener;
    }

    /*@Override
    public void run() {
        isChecking = true;
        atEditText.setText(atEditText.changeTextColor(atEditText.getText().toString()));
        atEditText.removeCallbacks(this);
    }*/
}
