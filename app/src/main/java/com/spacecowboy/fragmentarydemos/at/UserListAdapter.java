package com.spacecowboy.fragmentarydemos.at;

import android.content.Context;

import com.spacecowboy.fragmentarydemos.R;
import com.zhy.adapter.recyclerview.CommonAdapter;
import com.zhy.adapter.recyclerview.base.ViewHolder;

import java.util.List;

/**
 * @author: SpaceCowboy
 * @date: 2018/12/24
 * @description:
 */
public class UserListAdapter extends CommonAdapter<User> {
    public UserListAdapter(Context context, int layoutId, List<User> datas) {
        super(context, layoutId, datas);
    }

    @Override
    protected void convert(ViewHolder holder, User user, int position) {
        holder.setText(R.id.tv_user_id, user.id);
        holder.setText(R.id.tv_user_name, user.name);
    }
}
