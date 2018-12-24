package com.spacecowboy.fragmentarydemos.at;

import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.spacecowboy.fragmentarydemos.R;
import com.spacecowboy.fragmentarydemos.app.BaseActivity;
import com.spacecowboy.fragmentarydemos.events.EUtil;
import com.spacecowboy.fragmentarydemos.events.EventSelectUser;
import com.spacecowboy.fragmentarydemos.utils.UiUtil;
import com.zhy.adapter.recyclerview.MultiItemTypeAdapter;

import java.util.ArrayList;

import butterknife.BindView;

/**
 * @author: SpaceCowboy
 * @date: 2018/12/24
 * @description:
 */
public class UserListActivity extends BaseActivity implements MultiItemTypeAdapter.OnItemClickListener {
    @BindView(R.id.recycler_user_list)
    RecyclerView mRecyclerView;
    private UserListAdapter mAdapter;
    private ArrayList<User> mUserList;

    @Override
    protected void initView(View view) {
        mUserList = new ArrayList<>();
        for (int i = 1; i <= 30; i++) {
            User user = new User(String.valueOf(i), "User." + i);
            mUserList.add(user);
        }
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        mRecyclerView.setAdapter(mAdapter = new UserListAdapter(this, R.layout.item_user_list, mUserList));
        mAdapter.setOnItemClickListener(this);
    }

    @Override
    protected View getSubContentView() {
        return UiUtil.inflate(this, R.layout.activity_usr_list);
    }

    @Override
    public void onClick(View v) {

    }

    @Override
    public void onItemClick(View view, RecyclerView.ViewHolder holder, int position) {
        EventSelectUser eventSelectUser = new EventSelectUser();
        ArrayList<User> users = new ArrayList<>();
        users.add(mUserList.get(position));
        eventSelectUser.users = users;
        EUtil.postSticky(eventSelectUser);
        finish();
    }

    @Override
    public boolean onItemLongClick(View view, RecyclerView.ViewHolder holder, int position) {
        return false;
    }
}
