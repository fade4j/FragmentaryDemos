package com.spacecowboy.fragmentarydemos.app;

import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.TextView;

import com.spacecowboy.fragmentarydemos.R;
import com.spacecowboy.fragmentarydemos.utils.UiUtil;

import java.util.ArrayList;

/**
 * Created by SpaceCowboy on 2017/8/26.
 */

public class MainExpandableAdapter extends BaseExpandableListAdapter {
    private ArrayList<ArrayList<String>> mChildren;
    private ArrayList<String> mParents;

    public MainExpandableAdapter(ArrayList<String> parents, ArrayList<ArrayList<String>> children) {
        mParents = parents;
        mChildren = children;
    }

    @Override
    public int getGroupCount() {
        return mParents.size();
    }

    @Override
    public int getChildrenCount(int groupPosition) {
        if (groupPosition >= mChildren.size()) {
            return 0;
        }
        return mChildren.get(groupPosition).size();
    }

    @Override
    public Object getGroup(int groupPosition) {
        return mParents.get(groupPosition);
    }

    @Override
    public Object getChild(int groupPosition, int childPosition) {
        return mChildren.get(groupPosition).get(childPosition);
    }

    @Override
    public long getGroupId(int groupPosition) {
        return groupPosition;
    }

    @Override
    public long getChildId(int groupPosition, int childPosition) {
        return childPosition;
    }

    @Override
    public boolean hasStableIds() {
        return false;
    }

    @Override
    public View getGroupView(int groupPosition, boolean isExpanded, View convertView, ViewGroup parent) {
        ParentViewHolder viewHolder;
        if (convertView == null) {
            viewHolder = new ParentViewHolder();
            convertView = UiUtil.inflate(parent.getContext(), R.layout.item_expandable_parent);
            viewHolder.view = (TextView) convertView.findViewById(R.id.tv_item_parent);
            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ParentViewHolder) convertView.getTag();
        }
        String group = (String) getGroup(groupPosition);
        viewHolder.view.setText(group);
        return convertView;
    }

    @Override
    public View getChildView(int groupPosition, int childPosition, boolean isLastChild, View convertView, ViewGroup parent) {
        ChildViewHolder viewHolder;
        if (convertView == null) {
            viewHolder = new ChildViewHolder();
            convertView = UiUtil.inflate(parent.getContext(), R.layout.item_expandable_child);
            viewHolder.view = (TextView) convertView.findViewById(R.id.tv_item_child);
            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ChildViewHolder) convertView.getTag();
        }
        String child = (String) getChild(groupPosition, childPosition);
        viewHolder.view.setText(child);
        return convertView;
    }

    @Override
    public boolean isChildSelectable(int groupPosition, int childPosition) {
        return true;
    }

    static class ParentViewHolder {
        TextView view;
    }

    static class ChildViewHolder {
        TextView view;
    }
}
