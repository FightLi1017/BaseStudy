package android.com.pluginstudy.HookActivity;

import android.app.Activity;
import android.app.Instrumentation;
import android.com.pluginstudy.intercept_activity.ActivityThreadHandlerCallback;
import android.os.Handler;

import java.lang.reflect.Field;
import java.lang.reflect.Method;

/**
 * @author：lichenxi
 * @date 2018/8/27 17
 * email：525603977@qq.com
 * Fighting
 */
public class HookHelper {
    public static final String TAG = "MyApplication";
    public static final String Plugin = "Plugin";
    public static final String ACTIVIT_THREAD = "android.app.ActivityThread";
    public static final String CURRENT_ACTIVITY_THREAD = "currentActivityThread";
    public static final String INSTRUMENTATION = "mInstrumentation";

    public static void hookActivity(Activity activity) throws Exception{
          //步骤很简单吧 通过反射得到Activity的 Instrumentation字段 然后在运用反射替换下就可以
        Field field = activity.getClass().getSuperclass().getDeclaredField("mInstrumentation");

        field.setAccessible(true);

        Instrumentation instrumentation = (Instrumentation) field.get(activity);


        HookInstrumentation hookInstrumentation = new HookInstrumentation(instrumentation);

        field.set(activity, hookInstrumentation);
    }

    public static void HookApplication() throws Exception{
        Class<?> activityThreadClass = Class.forName(ACTIVIT_THREAD);
        Method currentThreadMethod = activityThreadClass.getDeclaredMethod(CURRENT_ACTIVITY_THREAD);
        currentThreadMethod.setAccessible(true);
        Object currentActivityThread = currentThreadMethod.invoke(null);
        //至此我们得到了ActivityThread对象

        Field field = activityThreadClass.getDeclaredField("mInstrumentation");
        field.setAccessible(true);
        Instrumentation instrumentation = (Instrumentation) field.get(currentActivityThread);


        HookInstrumentation hookInstrumentation = new HookInstrumentation(instrumentation);

        field.set(currentActivityThread, hookInstrumentation);


        Field mHField=activityThreadClass.getDeclaredField("mH");

        mHField.setAccessible(true);

        Handler mH=(Handler)mHField.get(currentActivityThread);

        //将我们的动态代理类 callback 设置给handler

        Field mCallback=Handler.class.getDeclaredField("mCallback");
        mCallback.setAccessible(true);
        mCallback.set(mH,new ActivityThreadHandlerCallback(mH));

   }
}
