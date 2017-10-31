package com.spacecowboy.fragmentarydemos.utils;

import android.os.Environment;

import java.io.File;

/**
 * Created by jiangtao on 2017/10/25.
 * Description:
 */

public class Util {
    public static String getExternalStorageState() {
        return Environment.getExternalStorageState();
    }

    public static File getExternalStorage() {
        if (!Environment.MEDIA_MOUNTED.equalsIgnoreCase(getExternalStorageState())) {
            return null;
        }

        return Environment.getExternalStorageDirectory();
    }
}
