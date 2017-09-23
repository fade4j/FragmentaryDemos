package com.spacecowboy.fragmentarydemos.utils;

import android.util.Log;

/**
 * @author: SpaceCowboy
 * @date: 2017/9/16
 * @description:
 */

public class L {
    private static final String TAG = "FRAGMENTARY_DEMOS";

    public static void e(String msg) {
        e(TAG, msg);
    }

    public static void e(String tag, String msg) {
        Log.e(tag, msg == null ? "" : msg);
    }
}
