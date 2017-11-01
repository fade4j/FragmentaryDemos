package com.spacecowboy.fragmentarydemos.normaltest;

import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.TextView;

import com.spacecowboy.fragmentarydemos.R;
import com.spacecowboy.fragmentarydemos.app.BaseActivity;
import com.spacecowboy.fragmentarydemos.utils.UiUtil;

import java.util.ArrayList;

import butterknife.BindView;

/**
 * @author: SpaceCowboy
 * @date: 2017/11/1
 * @description:
 */

public class ScrollableTestViewActivity extends BaseActivity {
    private ArrayList<String> mList;


    @BindView(R.id.gridview)
    GridView mGridView;

    @Override
    protected void initView(View view) {
        mGridView.setAdapter(new SimpleGridAdapter(mList));
    }

    @Override
    protected void initDataBeforeViews() {
        mList = new ArrayList<>();
        for (int i = 0; i < 25; i++) {
            mList.add("测试数据_测试数据_" + i);
        }
    }

    @Override
    protected View getSubContentView() {
        return UiUtil.inflate(this, R.layout.activity_scrollable_textview);
    }

    @Override
    public void onClick(View v) {

    }

    class SimpleGridAdapter extends BaseAdapter {
        private ArrayList<String> mDatas;

        public SimpleGridAdapter(ArrayList<String> datas) {
            mDatas = datas;
        }

        @Override
        public int getCount() {
            return mDatas == null ? 0 : mDatas.size();
        }

        @Override
        public String getItem(int position) {
            return mDatas.get(position);
        }

        @Override
        public long getItemId(int position) {
            return position;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            if (convertView == null) {
                convertView = UiUtil.inflate(parent.getContext(), R.layout.item_grid_view);
            }

            TextView textView = (TextView) convertView.findViewById(R.id.tv_grid_item);
            textView.setText(getItem(position));

            return convertView;
        }
    }
}
