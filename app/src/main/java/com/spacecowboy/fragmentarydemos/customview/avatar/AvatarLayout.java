package com.spacecowboy.fragmentarydemos.customview.avatar;

import android.content.Context;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.AttributeSet;
import android.widget.LinearLayout;

import com.spacecowboy.fragmentarydemos.R;
import com.spacecowboy.fragmentarydemos.utils.L;

import java.util.ArrayList;

/**
 * @author: SpaceCowboy
 * @date: 2018/1/13
 * @description:
 */

public class AvatarLayout extends LinearLayout {
    private final String NAME = this.getClass().getSimpleName().toUpperCase();
    private RecyclerView mRecycler;
    private AvatarAdapter mAdapter;
    private ArrayList<String> mList = new ArrayList<>();

    public AvatarLayout(Context context) {
        super(context);
    }

    public AvatarLayout(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public AvatarLayout(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    protected void onFinishInflate() {
        super.onFinishInflate();
        mRecycler = (RecyclerView) findViewById(R.id.recycler_avatar);
        L.e(NAME, "onFinishInflate");

    }

    public void appendData(ArrayList<String> list) {
        L.e(NAME, "appendData");
        final int preSize = mList.size();
        mList.addAll(list);
        if (mAdapter != null) {
            mAdapter.notifyItemInserted(preSize);
            //mAdapter.notifyDataSetChanged();
        }else {
            mRecycler.setLayoutManager(new LinearLayoutManager(getContext(), LinearLayoutManager.HORIZONTAL, false));
            mRecycler.addItemDecoration(new HorizontalItemSpace());
            //mAdapter = new AvatarAdapter(getContext(), mList);
            mRecycler.setAdapter(mAdapter);
            //L.e(NAME, "" + (mRecycler == null));
        }
    }
}
