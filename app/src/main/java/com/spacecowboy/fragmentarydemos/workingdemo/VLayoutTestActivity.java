package com.spacecowboy.fragmentarydemos.workingdemo;

import android.graphics.Rect;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;

import com.alibaba.android.vlayout.LayoutHelper;
import com.alibaba.android.vlayout.VirtualLayoutAdapter;
import com.alibaba.android.vlayout.VirtualLayoutManager;
import com.alibaba.android.vlayout.layout.DefaultLayoutHelper;
import com.alibaba.android.vlayout.layout.FixLayoutHelper;
import com.alibaba.android.vlayout.layout.GridLayoutHelper;
import com.alibaba.android.vlayout.layout.ScrollFixLayoutHelper;
import com.alibaba.android.vlayout.layout.StaggeredGridLayoutHelper;
import com.spacecowboy.fragmentarydemos.R;
import com.spacecowboy.fragmentarydemos.app.BaseActivity;
import com.spacecowboy.fragmentarydemos.utils.L;
import com.spacecowboy.fragmentarydemos.utils.UiUtil;

import java.util.LinkedList;
import java.util.List;

/**
 * @author: SpaceCowboy
 * @date: 2017/9/18
 * @description:
 */

public class VLayoutTestActivity extends BaseActivity {

    private RecyclerView mRecyclerView;
    private EditText mEditText;

    @Override
    protected void initView(View view) {
        mRecyclerView = (RecyclerView) findViewById(R.id.main_view);
        VirtualLayoutManager layoutManager = new VirtualLayoutManager(this);
        mRecyclerView.setLayoutManager(layoutManager);
        mRecyclerView.addItemDecoration(new RecyclerView.ItemDecoration() {
            public void getItemOffsets(Rect outRect, View view, RecyclerView parent, RecyclerView.State state) {
                outRect.set(10, 10, 10, 10);
            }
        });
        final List<LayoutHelper> helpers = new LinkedList<>();

        final GridLayoutHelper gridLayoutHelper = new GridLayoutHelper(4);
        gridLayoutHelper.setItemCount(25);
        StaggeredGridLayoutHelper staggeredGridLayoutHelper = new StaggeredGridLayoutHelper(4);
        staggeredGridLayoutHelper.setItemCount(30);
        final ScrollFixLayoutHelper scrollFixLayoutHelper = new ScrollFixLayoutHelper(FixLayoutHelper.TOP_RIGHT, 100, 100);

        helpers.add(DefaultLayoutHelper.newHelper(7));
        helpers.add(scrollFixLayoutHelper);
        helpers.add(DefaultLayoutHelper.newHelper(2));
        helpers.add(gridLayoutHelper);
        helpers.add(staggeredGridLayoutHelper);
        layoutManager.setLayoutHelpers(helpers);
        mRecyclerView.setAdapter(
                new VirtualLayoutAdapter(layoutManager) {
                    @Override
                    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
                        L.e("VLayoutTestActivity", "viewType = " + viewType);
                        return new MainViewHolder(new TextView(VLayoutTestActivity.this));
                    }

                    @Override
                    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
                        VirtualLayoutManager.LayoutParams layoutParams = new VirtualLayoutManager.LayoutParams(
                                ViewGroup.LayoutParams.MATCH_PARENT, 300);
                        holder.itemView.setLayoutParams(layoutParams);

                        ((TextView) holder.itemView).setText(Integer.toString(position));

                        if (position == 7) {
                            layoutParams.height = 60;
                            layoutParams.width = 60;
                        } else if (position > 35) {
                            layoutParams.height = 200 + (position - 30) * 100;
                        }

                        if (position > 35) {
                            holder.itemView.setBackgroundColor(0x66cc0000 + (position - 30) * 128);
                        } else if (position % 2 == 0) {
                            holder.itemView.setBackgroundColor(0xaa00ff00);
                        } else {
                            holder.itemView.setBackgroundColor(0xccff00ff);
                        }
                    }

                    @Override
                    public int getItemCount() {
                        List<LayoutHelper> helpers = getLayoutHelpers();
                        if (helpers == null) {
                            return 0;
                        }
                        int count = 0;
                        for (int i = 0, size = helpers.size(); i < size; i++) {
                            count += helpers.get(i).getItemCount();
                        }
                        return count;
                    }
                });

        findViewById(R.id.jump).setOnClickListener(this);
        mEditText = (EditText) findViewById(R.id.position);
    }

    @Override
    protected View getSubContentView() {
        return UiUtil.inflate(this, R.layout.activity_vlayout_test);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.jump:
                String text = mEditText.getText().toString();
                if (TextUtils.isEmpty(text)){
                    return;
                }
                mRecyclerView.scrollToPosition(Integer.parseInt(text));
                mRecyclerView.getAdapter().notifyDataSetChanged();
                break;
        }
    }

    /*new Handler(Looper.getMainLooper()).postDelayed(new Runnable() {
                @Override
                public void run() {
                    recyclerView.scrollToPosition(7);
                    recyclerView.getAdapter().notifyDataSetChanged();
                }
            }, 6000);*/
    static class MainViewHolder extends RecyclerView.ViewHolder {

        public MainViewHolder(View itemView) {
            super(itemView);
        }
    }
}
