package com.spacecowboy.fragmentarydemos.app;

import android.app.Application;
import android.content.Context;

/**
 * Created by SpaceCowboy on 2017/8/26.
 */

public class AppApplication extends Application {
    private static Context sContext;
    @Override
    public void onCreate() {
        super.onCreate();

        sContext = getApplicationContext();
    }

    public static Context getContext(){return sContext;}
}
