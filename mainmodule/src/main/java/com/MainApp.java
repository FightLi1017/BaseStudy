package com;

import android.app.Application;
import android.content.Context;
import android.os.Process;


/**
 * @author：lichenxi
 * @date 2018/9/14 14
 * email：525603977@qq.com
 * Fighting
 */
public class MainApp extends Application {
    @Override
    public void onCreate() {
//        LeakCanary.install(this);
        super.onCreate();
        //使用这个库的时候 要记得在配置清单文件下设置data  scheme host path都可以设置
//        SimpleArouter.configuration()
//                .setDebugEnabled(true)
//                .addRequestInterceptor(null)
//                .addTargetInterceptor(null)
//                .addTargetNotFoundHandler(new OpenDirectlyHandler());
//
//        SimpleArouter.navigation(this,"ssss")
//                     .appendQueryParams("tag","123456")
//                     .appendQueryParams("tab","3")
//                     .start();
//        Process.killProcess(Process.myPid());
    }

    @Override
    protected void attachBaseContext(Context base) {
        super.attachBaseContext(base);
    }
}
