package com.spacecowboy.fragmentarydemos.widgets;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.util.AttributeSet;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.TableLayout;

import com.spacecowboy.fragmentarydemos.R;
import com.spacecowboy.fragmentarydemos.utils.L;


/**
 * @author: SpaceCowboy
 * @date: 2018/3/6
 * @description:
 */

public class MultiLayout extends FrameLayout {
    private ViewPager mViewPager;
    private View mViewHeader;
    private TableLayout mTabLayout;

    private FragmentPagerAdapter mPagerAdapter;

    public MultiLayout(@NonNull Context context) {
        super(context);
    }

    public MultiLayout(@NonNull Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public MultiLayout(@NonNull Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }


    @Override
    protected void onFinishInflate() {
        super.onFinishInflate();
        mViewPager = (ViewPager) findViewById(R.id.view_pager_multi_page);
        mViewHeader = findViewById(R.id.view_header_root);
        mTabLayout = (TableLayout) findViewById(R.id.tab_multi_page);
        L.e("MultiLayout", "onFinishInflate");
    }

    public void setPagerAdapter(FragmentPagerAdapter adapter) {
        L.e("MultiLayout", "setPagerAdapter");
        if (adapter == null) {
            throw new NullPointerException("FragmentPagerAdapter can not be null!");
        }
        mPagerAdapter = adapter;
        //mViewPager.setAdapter(mPagerAdapter);
    }
}
