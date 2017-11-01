package com.spacecowboy.fragmentarydemos.customview.wedgits;

import android.content.Context;
import android.graphics.Rect;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.widget.TextView;

/**
 * @author: SpaceCowboy
 * @date: 2017/11/1
 * @description:
 */

public class ScrollableTextView extends TextView {
    public ScrollableTextView(Context context) {
        super(context);
    }

    public ScrollableTextView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public ScrollableTextView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    public boolean isFocused() {
        return true;
    }
}
