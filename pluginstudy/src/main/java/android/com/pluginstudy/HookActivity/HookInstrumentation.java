package android.com.pluginstudy.HookActivity;


import android.app.Activity;
import android.app.Instrumentation;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.IBinder;
import android.util.Log;

import java.lang.reflect.Method;

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
        Log.d(TAG, "\n执行了startActivity, 参数如下: \n" + "who = [" + who + "], " +
                "\ncontextThread = [" + contextThread + "], \ntoken = [" + token + "], " +
                "\ntarget = [" + target + "], \nintent = [" + intent +
                "], \nrequestCode = [" + requestCode + "], \noptions = [" + options + "]");

       //反射调用 execStartActivity方法  因为这个方法是hide的
        intent.putExtra(HookHelper.Plugin,"My first plugin");
        try {
            Method execmethod =Instrumentation.class.getDeclaredMethod("execStartActivity",Context.class,
                    IBinder.class,IBinder.class,Activity.class,Intent.class,int.class,Bundle.class);
            execmethod.setAccessible(true);
           return (ActivityResult)execmethod.invoke(mBase,who,contextThread,token,target,intent,requestCode,options);
        } catch (Exception e) {
            throw new RuntimeException("do not support!!! pls adapt it");
        }
    }
}
