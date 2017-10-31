package com.spacecowboy.fragmentarydemos;

import android.content.Intent;
import android.view.View;
import android.widget.ExpandableListView;

import com.spacecowboy.fragmentarydemos.app.BaseActivity;
import com.spacecowboy.fragmentarydemos.app.MainExpandableAdapter;
import com.spacecowboy.fragmentarydemos.customview.circleimage.CircleImageTestActivity;
import com.spacecowboy.fragmentarydemos.customview.hencoder.unit1_1.CustomViewActivity1_1;
import com.spacecowboy.fragmentarydemos.customview.hencoder.unit1_1.activity.CustomViewActivity;
import com.spacecowboy.fragmentarydemos.customview.hencoder.unit1_4_canvas_matrix.CanvasAndMatrixActivity;
import com.spacecowboy.fragmentarydemos.normaltest.IdleHandlerTestActivity;
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

    protected void initData() {
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
        ArrayList<String> section1 = new ArrayList<>();
        ArrayList<Class> sectionActivities1 = new ArrayList<>();
        // 开源
        section1.add("1. ButterKnife");
        sectionActivities1.add(ButterKnifeActivity.class);
        section1.add("2. Kotlin");
        sectionActivities1.add(CustomViewActivity.class);
        //sectionOne.add("3. EventBus");
        //sectionOne.add("4. Unit Four");
        mChildTitles.add(section1);
        mActivities.add(sectionActivities1);
        // HenCoder
        ArrayList<String> section2 = new ArrayList<>();
        ArrayList<Class> sectionActivities2 = new ArrayList<>();
        section2.add("CustomViewActivity1_1");
        section2.add("Canvas And Matrix Test");
        section2.add("CircleImageView Test");
        mChildTitles.add(section2);
        sectionActivities2.add(CustomViewActivity1_1.class);
        sectionActivities2.add(CanvasAndMatrixActivity.class);
        sectionActivities2.add(CircleImageTestActivity.class);
        mActivities.add(sectionActivities2);
        // 工作Demo
        ArrayList<String> sectionThree = new ArrayList<>();
        sectionThree.add("1. 弧形菜单");
        sectionThree.add("2. VLayoutTest");
        sectionThree.add("3. Bitmap size text");
        ArrayList<Class> sectionThreeActivities = new ArrayList<>();
        sectionThreeActivities.add(ArcMenuActivity.class);
        sectionThreeActivities.add(VLayoutTestActivity.class);
        sectionThreeActivities.add(DailyTestActivity.class);
        mChildTitles.add(sectionThree);
        mActivities.add(sectionThreeActivities);

        // Normal Test
        ArrayList<String> section4 = new ArrayList<>();
        section4.add("1. MessageQueue.IdleHandler");
        ArrayList<Class> sectionActivities4 = new ArrayList<>();
        sectionActivities4.add(IdleHandlerTestActivity.class);
        mChildTitles.add(section4);
        mActivities.add(sectionActivities4);

    }

    /**
     * 添加父标题（大分类）
     */
    private void initParentData() {
        mParentTitles.add("开源");
        mParentTitles.add("Android自定义View(HenCoder)");
        mParentTitles.add("工作Demo");
        mParentTitles.add("Normal Test");
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
