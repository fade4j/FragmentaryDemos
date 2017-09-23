package com.spacecowboy.fragmentarydemos;

import android.content.Intent;
import android.view.View;
import android.widget.ExpandableListView;

import com.spacecowboy.fragmentarydemos.app.BaseActivity;
import com.spacecowboy.fragmentarydemos.app.MainExpandableAdapter;
import com.spacecowboy.fragmentarydemos.customview.hencoder.unit1_1.activity.CustomViewActivity;
import com.spacecowboy.fragmentarydemos.opensource.ButterKnifeActivity;
import com.spacecowboy.fragmentarydemos.utils.UiUtil;
import com.spacecowboy.fragmentarydemos.workingdemo.ArcMenuActivity;
import com.spacecowboy.fragmentarydemos.workingdemo.VLayoutTestActivity;

import java.util.ArrayList;

public class MainActivity extends BaseActivity implements ExpandableListView.OnChildClickListener {
    private ArrayList<String> mParentTitles;
    private ArrayList<ArrayList<String>> mChildTitles;
    private ArrayList<ArrayList<Class>> mActivities;

    @Override
    protected void initView(View view) {
        ExpandableListView expandableListView = (ExpandableListView) findViewById(R.id.list_view_expandable);
        initData();
        MainExpandableAdapter mAdapter = new MainExpandableAdapter(mParentTitles, mChildTitles);
        expandableListView.setAdapter(mAdapter);
        expandableListView.setOnChildClickListener(this);
    }

    private void initData() {
        mParentTitles = new ArrayList<>();
        mChildTitles = new ArrayList<>();
        initParentData();
        initChildData();
    }

    /**
     * 添加子标题（小分类）
     */
    private void initChildData() {
        mActivities = new ArrayList<>();
        ArrayList<String> sectionOne = new ArrayList<>();
        ArrayList<Class> sectionOneActivities = new ArrayList<>();
        // 开源
        sectionOne.add("1. ButterKnife");
        sectionOneActivities.add(ButterKnifeActivity.class);
        sectionOne.add("2. Kotlin");
        sectionOneActivities.add(CustomViewActivity.class);
        sectionOne.add("3. EventBus");
        sectionOne.add("4. Unit Four");
        mChildTitles.add(sectionOne);
        mActivities.add(sectionOneActivities);
        // HenCoder
        ArrayList<String> sectionTwo = new ArrayList<>();
        ArrayList<Class> sectionTwoActivities = new ArrayList<>();
        mChildTitles.add(sectionTwo);
        mActivities.add(sectionTwoActivities);
        // 工作Demo
        ArrayList<String> sectionThree = new ArrayList<>();
        sectionThree.add("1. 弧形菜单");
        sectionThree.add("2. VLayoutTest");
        ArrayList<Class> sectionThreeActivities = new ArrayList<>();
        sectionThreeActivities.add(ArcMenuActivity.class);
        sectionThreeActivities.add(VLayoutTestActivity.class);
        mChildTitles.add(sectionThree);
        mActivities.add(sectionThreeActivities);

    }

    /**
     * 添加父标题（大分类）
     */
    private void initParentData() {
        mParentTitles.add("开源");
        mParentTitles.add("Android自定义View(HenCoder)");
        mParentTitles.add("工作Demo");
        mParentTitles.add("待添加标题");
        mParentTitles.add("待添加标题");
    }

    @Override
    protected View getSubContentView() {
        return UiUtil.inflate(this, R.layout.activity_main);
    }

    @Override
    public boolean onChildClick(ExpandableListView parent, View v, int groupPosition, int childPosition, long id) {
        startActivityPage(groupPosition, childPosition);
        return false;
    }

    private void startActivityPage(int groupPosition, int childPosition) {
        if (mActivities.get(groupPosition) != null) {
            if (mActivities.get(groupPosition).size() > childPosition) {
                Intent intent = new Intent(this, mActivities.get(groupPosition).get(childPosition));
                startActivity(intent);
            }
        }
    }

    @Override
    public void onClick(View v) {

    }
}
