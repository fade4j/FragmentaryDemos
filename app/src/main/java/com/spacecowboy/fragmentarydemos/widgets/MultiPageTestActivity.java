package com.spacecowboy.fragmentarydemos.widgets;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.FragmentActivity;

import com.spacecowboy.fragmentarydemos.R;
import com.spacecowboy.fragmentarydemos.fragments.MultiPageFragmentPagerAdapter;

import java.util.ArrayList;

/**
 * @author: SpaceCowboy
 * @date: 2018/3/6
 * @description:
 */

public class MultiPageTestActivity extends FragmentActivity {
    private ArrayList<String> mTitleList;
    private MultiLayout multiLayout;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_multi_page_test);
        /*multiLayout = (MultiLayout) findViewById(R.id.multi_page_layout);
        mTitleList = new ArrayList<>();
        mTitleList.add("Tab1");
        mTitleList.add("Tab2");
        mTitleList.add("Tab3");*/
        //multiLayout.setPagerAdapter(new MultiPageFragmentPagerAdapter(getSupportFragmentManager(), mTitleList));
    }
}
