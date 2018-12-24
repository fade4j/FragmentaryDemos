package com.spacecowboy.fragmentarydemos.customview.avatar;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

/**
 * @author: SpaceCowboy
 * @date: 2018/1/13
 * @description:
 */

public class AvatarPagerAdapter extends FragmentStatePagerAdapter {
    private int count;
    public AvatarPagerAdapter(FragmentManager fm, int count) {
        super(fm);
        this.count = count;
    }

    @Override
    public Fragment getItem(int position) {
        Fragment fragment = new AvatarPagerFragment();
        Bundle bundle = new Bundle();
        bundle.putInt("POS", position+ 1);
        fragment.setArguments(bundle);
        return fragment;
    }

    @Override
    public int getCount() {
        return count;
    }
}
