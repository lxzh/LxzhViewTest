package com.lxzh123.demo;

import android.app.Application;
import android.content.Context;

/**
 * description $
 * author      Created by lxzh
 * date        2019-11-01
 */
public class LxzhApp extends Application {
    private static Context mContext;

    public static Context getContext() {
        return mContext;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        mContext = this;
    }
}
