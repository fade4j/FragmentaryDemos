package com.spacecowboy.fragmentarydemos.observer;

/**
 * @author: SpaceCowboy
 * @date: 2017/11/27
 * @description:
 */

public class Test {
    public static void main(String[] args){
        MyObservable observable = new MyObservable();
        MyObserver observer = new MyObserver();
        observable.addObserver(observer);
        observable.addString();
    }
}
