package com.spacecowboy.fragmentarydemos.normaltest.list_multi_type;

import android.view.View;
import android.widget.ListView;

import com.spacecowboy.fragmentarydemos.R;
import com.spacecowboy.fragmentarydemos.app.BaseActivity;
import com.spacecowboy.fragmentarydemos.utils.UiUtil;

import java.util.ArrayList;

import butterknife.BindView;

public class ListMultiTypeActivity extends BaseActivity {
    @BindView(R.id.list_multi_type)
    ListView mMultiTypeList;
    private ArrayList<String> mDatas;
    private MultiTypeListAdapter mAdapter;

    @Override
    protected void initView(View view) {
        mAdapter = new MultiTypeListAdapter(mDatas);
        mMultiTypeList.setAdapter(mAdapter);
    }

    @Override
    protected void initDataBeforeViews() {
        mDatas = new ArrayList<>();
        for (int i = 0; i < 50; i++) {
            mDatas.add("数据".concat(String.valueOf(i)));
        }
    }

    @Override
    protected void initDataAfterViews() {

    }

    @Override
    protected View getSubContentView() {
        return UiUtil.inflate(this,R.layout.activity_list_multi_type);
    }

    @Override
    public void onClick(View v) {

    }
}
