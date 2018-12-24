package com.spacecowboy.fragmentarydemos.fragments;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import com.spacecowboy.fragmentarydemos.R;

import java.util.ArrayList;

/**
 * @author: SpaceCowboy
 * @date: 2018/3/6
 * @description:
 */

public class RecyclerAdapter extends RecyclerView.Adapter<RecyclerHolder> {
    private Context mContext;
    private ArrayList<String> mList;

    public RecyclerAdapter(Context context, ArrayList<String> list) {
        mContext = context;
        mList = list;
    }

    @Override
    public int getItemViewType(int position) {
        return super.getItemViewType(position);
    }

    @Override
    public RecyclerHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new RecyclerHolder(LayoutInflater.from(mContext).inflate(R.layout.item_recycler_multi_page, parent, false));
    }

    @Override
    public void onBindViewHolder(RecyclerHolder holder, int position) {
        holder.mTvItem.setText(mList.get(position));
    }

    @Override
    public int getItemCount() {
        if (mList == null) {
            return 0;
        }
        return mList.size();
    }
}
