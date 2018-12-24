package com.spacecowboy.fragmentarydemos.fragments;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import java.util.ArrayList;

/**
 * @author: SpaceCowboy
 * @date: 2018/3/6
 * @description:
 */

public class MultiPageFragmentPagerAdapter extends FragmentPagerAdapter {
    private ArrayList<String> mPagerList;

    public MultiPageFragmentPagerAdapter(FragmentManager fm, ArrayList<String> pagerList) {
        super(fm);
        mPagerList = pagerList;
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return mPagerList.get(position);
    }

    @Override
    public Fragment getItem(int position) {
        /*Map<String, Object> map = mPagerList.get(position);
        if (map != null && map.size() > 0) {
            Bundle bundle = new Bundle();
            for (Object object : map.keySet()){
                bundle.put
            }
        }*/
        return new MultiPageFragment();
    }

    @Override
    public int getCount() {
        if (mPagerList == null) {
            return 0;
        }
        return mPagerList.size();
    }
}
