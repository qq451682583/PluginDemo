package com.android.plugindemo;

import android.app.Application;
import android.content.Context;
import android.support.multidex.MultiDex;

/**
 * @author holy
 */
public class PluginApplication extends Application {

    @Override
    public void onCreate() {
        super.onCreate();
    }

    @Override
    protected void attachBaseContext(Context base) {
        super.attachBaseContext(base);
        MultiDex.install(base);
    }
}
