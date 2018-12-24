package com.spacecowboy.fragmentarydemos.events;


import org.greenrobot.eventbus.EventBus;

/**
 * @author: SpaceCowboy
 * @date: 2018/12/24
 * @description:
 */
public class EUtil {
    public static EventBus get() {
        return EventBus.getDefault();
    }

    public static void register(Object o) {
        if (!get().isRegistered(o)) {
            get().register(o);
        }
    }

    public static void unregister(Object o) {
        if (get().isRegistered(o))
            get().unregister(o);
    }

    public static void post(Object t){
        get().post(t);
    }

    public static void postSticky(Object o){
        get().postSticky(o);
    }

    public static <T> T getSticky(Class<T> t){
        return get().getStickyEvent(t);
    }

    public static <T> T removeSticky(Class<T> t){
        return get().removeStickyEvent(t);
    }
}
