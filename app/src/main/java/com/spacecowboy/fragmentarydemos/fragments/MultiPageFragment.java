package com.spacecowboy.fragmentarydemos.fragments;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;


import com.spacecowboy.fragmentarydemos.R;

import java.util.ArrayList;

/**
 * @author: SpaceCowboy
 * @date: 2018/3/6
 * @description:
 */

public class MultiPageFragment extends Fragment {
    private Activity mActivity;
    private RecyclerView mRecyclerView;

    private ArrayList<String> mList;
    private RecyclerAdapter mAdapter;

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mActivity = getActivity();
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.layout_fragment_multi_page, container, false);
        mRecyclerView = (RecyclerView) view.findViewById(R.id.recycler_view_multi_page);
        return view;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        initData();
    }

    private void initData() {
        if (mList == null) {
            mList = new ArrayList<>();
        }

        for (int i = 0; i < 100; i++) {
            mList.add("Test Data ".concat(String.valueOf(++i)));
        }

        if (mAdapter == null) {
            mAdapter = new RecyclerAdapter(mActivity, mList);
        }
        mRecyclerView.setLayoutManager(new LinearLayoutManager(mActivity));
        mRecyclerView.setAdapter(mAdapter);
    }
}
