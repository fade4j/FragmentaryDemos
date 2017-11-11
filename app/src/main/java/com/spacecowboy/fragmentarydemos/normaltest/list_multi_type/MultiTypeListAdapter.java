package com.spacecowboy.fragmentarydemos.normaltest.list_multi_type;

import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.spacecowboy.fragmentarydemos.R;
import com.spacecowboy.fragmentarydemos.utils.UiUtil;

import java.util.ArrayList;

/**
 * @author: SpaceCowboy
 * @date: 2017/11/11
 * @description:
 */

public class MultiTypeListAdapter extends BaseAdapter {

    ArrayList<String> mDatas;

    public MultiTypeListAdapter(ArrayList<String> data)
    {
        mDatas = data;
    }

    @Override
    public int getCount() {
        if (mDatas == null || mDatas.size() == 0){
            return 0;
        }

        return mDatas.size() - 2;
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    /**
     * 由于系统内的type类型使用数组进行管理的，所以返回的type类型要从0开始，负责会抛出数组越界的异常
     * ArrayList<View>[] scrapViews = new ArrayList[viewTypeCount];// 新建一个数组长度为viewTypeCount的数组 -> type从0开始
     * @param position
     * @return
     */
    @Override
    public int getItemViewType(int position) {
        if (position % 3== 0){
            return 0;
        }
        return 1;
    }

    @Override
    public int getViewTypeCount() {
        return 2;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        int type = getItemViewType(position);

        if (type == 0){
            Holder1 holder1;
            if (convertView == null){
                holder1 = new Holder1();
                convertView = UiUtil.inflate(parent.getContext(), R.layout.item_list_type1);
                holder1.textView1 = (TextView) convertView.findViewById(R.id.text1);
                holder1.textView2 = (TextView) convertView.findViewById(R.id.text2);
                holder1.textView3 = (TextView) convertView.findViewById(R.id.text3);
                holder1.textpos = (TextView) convertView.findViewById(R.id.tv_position);
                convertView.setTag(holder1);
            } else {
                holder1 = (Holder1) convertView.getTag();
            }
            holder1.textpos.setText(String.valueOf(position));

            holder1.textView1.setText(mDatas.get(position));
            holder1.textView2.setText(mDatas.get(position + 1));
            holder1.textView3.setText(mDatas.get(position + 2));

        } else {
            Holder2 holder2;
            if (convertView == null){
                holder2 = new Holder2();
                convertView = UiUtil.inflate(parent.getContext(), R.layout.item_list_type2);
                holder2.textView = (TextView) convertView.findViewById(R.id.text);
                holder2.textpos = (TextView) convertView.findViewById(R.id.tv_position);
                convertView.setTag(holder2);
            } else {
                holder2 = (Holder2) convertView.getTag();
            }
            holder2.textpos.setText(String.valueOf(position));
            holder2.textView.setText(mDatas.get(position + 2));

        }

        return convertView;
    }

    class Holder1 {
        TextView textView1;
        TextView textView2;
        TextView textView3;
        TextView textpos;
    }

    class Holder2 {
        TextView textView;
        TextView textpos;
    }
}
