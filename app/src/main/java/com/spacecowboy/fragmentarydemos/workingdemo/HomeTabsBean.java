package com.spacecowboy.fragmentarydemos.workingdemo;

/**
 * @author: SpaceCowboy
 * @date: 2017/9/17
 * @description:
 */

public class HomeTabsBean {
    private int classId;
    private String title;

    public HomeTabsBean(int classId, String title) {
        this.classId = classId;
        this.title = title;
    }

    public void setClassId(int classId) {
        this.classId = classId;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getClassId() {
        return classId;
    }

    public String getTitle() {
        return title;
    }
}
