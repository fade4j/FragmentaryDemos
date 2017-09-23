package com.spacecowboy.fragmentarydemos.workingdemo;

import android.view.View;
import android.widget.Button;

import com.spacecowboy.fragmentarydemos.R;
import com.spacecowboy.fragmentarydemos.app.BaseActivity;
import com.spacecowboy.fragmentarydemos.utils.L;
import com.spacecowboy.fragmentarydemos.utils.UiUtil;
import com.spacecowboy.fragmentarydemos.workingdemo.widgets.SlidingArcView;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.OnClick;

public class ArcMenuActivity extends BaseActivity {
    @BindView(R.id.sliding_arc_view)
    SlidingArcView slidingArcView;
    @BindView(R.id.btn_refresh)
    Button mBtnRefresh;

    private ArrayList<HomeTabsBean> mTabs = new ArrayList<>();

    @Override
    protected void initView(View view) {
        //SlidingArcView slidingArcView = view.findViewById(R.id.sliding_arc_view);
        slidingArcView.setQtItemClickListener(new SlidingArcView.QTItemClickListener() {
            @Override
            public void onClick(View v, int index) {
                L.e("ArcMenuActivity", "classId = " + index);
            }
        });
        slidingArcView.setQtScrollListener(new SlidingArcView.QTScrollListener() {
            @Override
            public void onSelect(View v, int index) {

            }
        });
        initTabs("分类");
        slidingArcView.notifyDataSetChanged(mTabs);
        //mBtnRefresh.setOnClickListener(this);
    }

    private void initTabs(String prefix) {
        HomeTabsBean tab1 = new HomeTabsBean(1, prefix + "一");
        HomeTabsBean tab2 = new HomeTabsBean(2, prefix + "二");
        HomeTabsBean tab3 = new HomeTabsBean(3, prefix + "三");
        HomeTabsBean tab4 = new HomeTabsBean(4, prefix + "四");
        HomeTabsBean tab5 = new HomeTabsBean(5, prefix + "五");
        HomeTabsBean tab6 = new HomeTabsBean(6, prefix + "六");
        HomeTabsBean tab7 = new HomeTabsBean(7, prefix + "七");
        HomeTabsBean tab8 = new HomeTabsBean(8, prefix + "八");
        HomeTabsBean tab9 = new HomeTabsBean(9, prefix + "九");
        HomeTabsBean tab10 = new HomeTabsBean(10, prefix + "十");

        mTabs.add(tab1);
        mTabs.add(tab2);
        mTabs.add(tab3);
        mTabs.add(tab4);
        mTabs.add(tab5);
        mTabs.add(tab6);
        mTabs.add(tab7);
        mTabs.add(tab8);
        mTabs.add(tab9);
        mTabs.add(tab10);
    }

    @Override
    protected View getSubContentView() {
        return UiUtil.inflate(this, R.layout.activity_arc_menu);
    }

    @OnClick({R.id.btn_refresh})
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_refresh:
                mTabs.clear();
                initTabs("哈哈");
                slidingArcView.notifyDataSetChanged(mTabs);
                break;

        }
    }
}
