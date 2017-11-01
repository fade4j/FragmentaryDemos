package com.spacecowboy.fragmentarydemos.utils;

import android.app.Activity;
import android.content.Context;
import android.content.res.Resources;
import android.graphics.drawable.Drawable;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
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

    public static int getScreenWidth() {
        return getResources().getDisplayMetrics().widthPixels;
    }

    public static int getScreenHeight() {
        return getResources().getDisplayMetrics().heightPixels;
    }

    public static int dp2px(float v) {
        return (int) (getDensity() * v + 0.5f);
    }

    public static int sp2px(float v) {
        return (int) (getScaleDensity() * v + 0.5f);
    }

    public static int px2dp(float v) {
        return (int) (v / getDensity() + 0.5f);
    }

    public static float getDensity() {
        return getResources().getDisplayMetrics().density;
    }

    public static float getScaleDensity() {
        return getResources().getDisplayMetrics().scaledDensity;
    }

    public static void toggleInputMethod(EditText editText) {
        InputMethodManager inputMethodManager = (InputMethodManager) getAppContext().getSystemService(Context.INPUT_METHOD_SERVICE);
        inputMethodManager.toggleSoftInput(0, InputMethodManager.HIDE_NOT_ALWAYS);
    }

    public static void closeInputMethod(EditText editText) {
        InputMethodManager inputMethodManager = (InputMethodManager) getAppContext().getSystemService(Context.INPUT_METHOD_SERVICE);
        inputMethodManager.hideSoftInputFromWindow(editText.getWindowToken(), InputMethodManager.RESULT_UNCHANGED_SHOWN);
    }

    public static void openInputMethod(EditText editText) {
        InputMethodManager inputMethodManager = (InputMethodManager) getAppContext().getSystemService(Context.INPUT_METHOD_SERVICE);
        inputMethodManager.showSoftInputFromInputMethod(editText.getWindowToken(), InputMethodManager.SHOW_FORCED);
    }
}
