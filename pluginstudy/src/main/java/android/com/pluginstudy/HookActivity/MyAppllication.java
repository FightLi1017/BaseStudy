package android.com.pluginstudy.HookActivity;

import android.PluginManager;
import android.app.Application;
import android.com.pluginstudy.CustomClassLoader.HookClassLoader;
import android.com.pluginstudy.HookAmsPms.HookAmsPMS;
import android.content.Context;
import android.content.res.Resources;

import com.orhanobut.logger.AndroidLogAdapter;
import com.orhanobut.logger.Logger;

import java.io.IOException;

/**
 * @author：lichenxi
 * @date 2018/8/27 18
 * email：525603977@qq.com
 * Fighting
 */
public class MyAppllication extends Application {

    @Override
    public void onCreate() {
        super.onCreate();
        try {
            PluginManager.init(getBaseContext());
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    @Override
    public Resources getResources() {//这里需要返回插件框架的resources
        return PluginManager.getPluginResources();
    }


    @Override
    protected void attachBaseContext(Context base) {
        super.attachBaseContext(base);
        try {
//            HookAmsPMS.hookAMS();
            HookHelper.HookApplication();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
