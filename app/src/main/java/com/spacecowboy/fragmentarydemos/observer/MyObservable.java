package com.spacecowboy.fragmentarydemos.observer;

import java.util.ArrayList;
import java.util.Observable;

/**
 * @author: SpaceCowboy
 * @date: 2017/11/27
 * @description:
 */

public class MyObservable extends Observable {
    private ArrayList<String> mList;

    public MyObservable(){
        mList = new ArrayList<>();
    }

    public void addString(){
        for (int i = 0; i < 10; i++) {
            mList.add("Data " + i);
        }
        setChanged();
        notifyObservers(mList);
    }
}
