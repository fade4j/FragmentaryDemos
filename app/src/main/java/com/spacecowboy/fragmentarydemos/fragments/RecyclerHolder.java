package com.spacecowboy.fragmentarydemos.fragments;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.spacecowboy.fragmentarydemos.R;


/**
 * @author: SpaceCowboy
 * @date: 2018/3/6
 * @description:
 */

public class RecyclerHolder extends RecyclerView.ViewHolder {
    TextView mTvItem;

    public RecyclerHolder(View itemView) {
        super(itemView);
        mTvItem = (TextView) itemView.findViewById(R.id.tv_item_recycler);
    }
}
