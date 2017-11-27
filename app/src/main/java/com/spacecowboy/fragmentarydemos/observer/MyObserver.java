package com.spacecowboy.fragmentarydemos.observer;

import com.spacecowboy.fragmentarydemos.utils.L;

import java.util.ArrayList;
import java.util.Observable;
import java.util.Observer;

/**
 * @author: SpaceCowboy
 * @date: 2017/11/27
 * @description:
 */

public class MyObserver implements Observer {
    @Override
    public void update(Observable o, Object arg) {
        System.out.println("update");
        if (arg != null && arg instanceof ArrayList){
            ArrayList<String> list = (ArrayList<String>) arg;
            for (int i = 0; i < list.size(); i++) {
                //L.e("MYOBSERVER", list.get(i));
                System.out.println(list.get(i));
            }
        }
    }
}
