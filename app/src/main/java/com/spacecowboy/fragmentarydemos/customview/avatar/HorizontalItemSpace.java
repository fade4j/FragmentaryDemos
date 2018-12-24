package com.spacecowboy.fragmentarydemos.customview.avatar;

import android.graphics.Rect;
import android.support.v7.widget.RecyclerView;
import android.view.View;

/**
 * @author: SpaceCowboy
 * @date: 2018/1/13
 * @description:
 */

public class HorizontalItemSpace extends RecyclerView.ItemDecoration {
    @Override
    public void getItemOffsets(Rect outRect, View view, RecyclerView parent, RecyclerView.State state) {
        outRect.set(10, 0, 10, 0);
    }
}
