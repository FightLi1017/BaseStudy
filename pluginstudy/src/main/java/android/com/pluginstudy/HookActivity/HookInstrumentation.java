package android.com.pluginstudy.HookActivity;


import android.PluginManager;
import android.app.Activity;
import android.app.Instrumentation;
import android.com.pluginstudy.CustomClassLoader.ReflectUtil;
import android.com.pluginstudy.intercept_activity.StubActivity;
import android.content.ComponentName;
import android.content.ContentResolver;
import android.content.Context;
import android.content.ContextWrapper;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.content.pm.ResolveInfo;
import android.content.res.Resources;
import android.os.Bundle;
import android.os.IBinder;
import android.util.Log;
import android.view.ContextThemeWrapper;

import com.orhanobut.logger.Logger;

import java.lang.reflect.Method;

import static android.PluginManager.KEY_IS_PLUGIN;
import static android.PluginManager.KEY_TARGET_ACTIVITY;
import static android.PluginManager.KEY_TARGET_PACKAGE;


/**
 * @author：lichenxi
 * @date 2018/8/27 17
 * email：525603977@qq.com
 * Fighting
 */
public class HookInstrumentation extends Instrumentation {
    Instrumentation mBase;
    private static final String TAG = "HookInstrumentation";
    public HookInstrumentation(Instrumentation base) {
        mBase = base;
    }

    public ActivityResult execStartActivity(Context who, IBinder contextThread, IBinder token, Activity target,
            Intent intent, int requestCode, Bundle options) {
        Log.d(TAG, "\nexecStartActivity, 参数如下: \n" + "who = [" + who + "], " +
                "\ncontextThread = [" + contextThread + "], \ntoken = [" + token + "], " +
                "\ntarget = [" + target + "], \nintent = [" + intent +
                "], \nrequestCode = [" + requestCode + "], \noptions = [" + options + "]");

       //反射调用 execStartActivity方法  因为这个方法是hide的
        try {
            //构造一个新的intent 并且保存真实的intent
            ComponentName componentName = new ComponentName(intent.getComponent().getPackageName(), StubActivity.class.getName());
            intent.putExtra(KEY_IS_PLUGIN, true)
                    .putExtra(KEY_TARGET_PACKAGE, intent.getComponent().getPackageName())
                    .putExtra(KEY_TARGET_ACTIVITY, intent.getComponent().getClassName())
                    .setComponent(componentName);

            Class[] parameterTypes = {Context.class, IBinder.class, IBinder.class, Activity.class, Intent.class,
                    int.class, Bundle.class};

            Method execmethod =Instrumentation.class.getDeclaredMethod("execStartActivity",parameterTypes);
            execmethod.setAccessible(true);
           return (ActivityResult)execmethod.invoke(mBase,who,contextThread,token,target,intent,requestCode,options);
        } catch (Exception e) {
            e.printStackTrace();
            Log.d(TAG,e.getMessage());
            throw new RuntimeException("");
        }
    }

    @Override
    public void callActivityOnCreate(Activity activity, Bundle icicle) {
        final Intent intent = activity.getIntent();
        if (PluginManager.isIntentFromPlugin(intent)) {
            Context base = activity.getBaseContext();
            try {
                Resources resources=PluginManager.getPluginResources();
                ReflectUtil.setField(ContextThemeWrapper.class, activity, "mResources",resources);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        mBase.callActivityOnCreate(activity, icicle);
    }
    @Override
    public Activity newActivity(ClassLoader cl, String className, Intent intent) throws InstantiationException, IllegalAccessException, ClassNotFoundException {
        return mBase.newActivity(cl, className, intent);
    }
}
