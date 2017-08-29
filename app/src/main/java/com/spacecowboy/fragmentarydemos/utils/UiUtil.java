package com.spacecowboy.fragmentarydemos.utils;

import android.app.Activity;
import android.content.Context;
import android.content.res.Resources;
import android.graphics.drawable.Drawable;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.Toast;

import com.spacecowboy.fragmentarydemos.app.AppApplication;

/**
 * Created by SpaceCowboy on 2017/8/26.
 */

public class UiUtil {
    public static View inflate(Context context, int resId) {
        return View.inflate(context, resId, null);
    }

    public static Context getAppContext() {
        return AppApplication.getContext();
    }

    public static Resources getResources() {
        return getAppContext().getResources();
    }

    public static Drawable getResDrawable(int resId) {
        return getResources().getDrawable(resId);
    }

    public static void showToast(@NonNull Object content) {
        if (content instanceof String) {
            Toast.makeText(getAppContext(), (String) content, Toast.LENGTH_SHORT).show();
            return;
        }

        if (content instanceof Integer) {
            Toast.makeText(getAppContext(), (Integer) content, Toast.LENGTH_SHORT).show();
        }
    }

    public static int getResColor(int resId) {

        return getResources().getColor(resId);
    }
}
