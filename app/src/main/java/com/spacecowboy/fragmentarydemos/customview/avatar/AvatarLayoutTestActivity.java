package com.spacecowboy.fragmentarydemos.customview.avatar;

import android.support.v4.view.ViewPager;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.BaseAdapter;
import android.widget.ListView;

import com.spacecowboy.fragmentarydemos.R;
import com.spacecowboy.fragmentarydemos.app.BaseActivity;
import com.spacecowboy.fragmentarydemos.utils.L;
import com.spacecowboy.fragmentarydemos.utils.UiUtil;

import java.util.ArrayList;
import java.util.Random;

import butterknife.BindView;

/**
 * @author: SpaceCowboy
 * @date: 2018/1/13
 * @description:
 */

public class AvatarLayoutTestActivity extends BaseActivity {

    private AvatarAdapter mAdapter;
    private RecyclerView mRecycler;
    private ViewPager viewPager;

    //@BindView(R.id.recycler_avatar)
    //RecyclerView mRecycler;
    @Override
    protected void initView(View view) {
        final ArrayList<String> tempList = new ArrayList<>();
        for (int i = 1; i <= 60; i++) {
            tempList.add("数据" + i);
        }

        //final AvatarLayout layout = (AvatarLayout) view.findViewById(R.id.avatar_layout);

        mRecycler = (RecyclerView) findViewById(R.id.recycler_avatar1);
        mRecycler.addItemDecoration(new HorizontalItemSpace());
        mAdapter = new AvatarAdapter(this, tempList, this);
        mRecycler.setAdapter(mAdapter);
        mRecycler.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false));
        viewPager = (ViewPager) findViewById(R.id.viewpager);
        viewPager.setAdapter(new AvatarPagerAdapter(getSupportFragmentManager(), tempList.size()));
        viewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
                L.e(NAME, position + " , " + positionOffset + " , " + positionOffsetPixels);
                mAdapter.notifyScrolled(position, positionOffset);
            }

            @Override
            public void onPageSelected(int position) {
                mRecycler.scrollToPosition(position);
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
    }

    @Override
    protected View getSubContentView() {
        L.e(NAME, "getSubContentView");
        return UiUtil.inflate(this, R.layout.activity_avatar_layout_test);
    }

    @Override
    public void onClick(View v) {
        int pos = (int) v.getTag();
        viewPager.setCurrentItem(pos, false);
    }

    public void notifyChanged(View view)
    {
        //mAdapter.notifyDataSetChanged();
        Random random = new Random(System.currentTimeMillis());
        int pos = random.nextInt(60);
        UiUtil.showToast("" + pos);
        mRecycler.scrollToPosition(pos);
    }
}
