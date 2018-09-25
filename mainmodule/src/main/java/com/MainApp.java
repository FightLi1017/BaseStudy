package com;

import android.app.Application;

import com.squareup.leakcanary.LeakCanary;

/**
 * @author：lichenxi
 * @date 2018/9/14 14
 * email：525603977@qq.com
 * Fighting
 */
public class MainApp extends Application {
    @Override
    public void onCreate() {
        LeakCanary.install(this);
        super.onCreate();
    }
}
