package android.com.pluginstudy.HookAmsPms;

import android.com.pluginstudy.intercept_activity.ActivityManagerHandler;
import android.content.Context;
import android.content.pm.PackageManager;
import android.util.Log;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

/**
 * @author：lichenxi
 * @date 2018/8/28 16
 * email：525603977@qq.com
 * Fighting
 * 有个疑问点 就是为什呢能够成功的Hook呢 很奇怪  答案: 因为这个字段是静态的 全局就只有一个 所以可以hook
 */
public class HookAmsPMS {


      public static void hookAMS() {
          try{
              Class<?>  amnClass=Class.forName("android.app.ActivityManager");
              Field field= amnClass.getDeclaredField("IActivityManagerSingleton");
              field.setAccessible(true);
              Object gDefault=field.get(null);//静态字段 不需要传递引用类型

              // 4.x以上的gDefault是一个 android.util.Singleton对象

              Class<?> singleton = Class.forName("android.util.Singleton");
              Field mInstanceField=singleton.getDeclaredField("mInstance");
              mInstanceField.setAccessible(true);

              // ActivityManagerNative 的gDefault对象里面原始的 IActivityManager对象

              Object activityManagerService=mInstanceField.get(gDefault);
              //接下来就是动态代理出马了

              Class<?> iActivityManagerInterface = Class.forName("android.app.IActivityManager");

              Object proxy = Proxy.newProxyInstance(ClassLoader.getSystemClassLoader(),new Class[]{iActivityManagerInterface},
                      new ActivityManagerHandler(activityManagerService));
              mInstanceField.set(gDefault,proxy);

          }catch (Exception e){
              Log.d("Hook",e.getLocalizedMessage());
          }

      }

    public static final String ACTIVIT_THREAD = "android.app.ActivityThread";
    public static final String CURRENT_ACTIVITY_THREAD = "currentActivityThread";
    //问 都是静态字段 为什么不能直接读取呢
    public static void HookPMS(Context context){
          try{
              Class<?>  activityThreadClass= Class.forName(ACTIVIT_THREAD);
              Method method=activityThreadClass.getDeclaredMethod(CURRENT_ACTIVITY_THREAD);
              method.setAccessible(true);
              Object currentActivityThread=method.invoke(null);
              Field  sPackageManagerField=activityThreadClass.getDeclaredField("sPackageManager");
              sPackageManagerField.setAccessible(true);
              Object  sPackageManager =sPackageManagerField.get(currentActivityThread);

              Class<?> iPackageManagerInterface = Class.forName("android.content.pm.IPackageManager");

              Object proxy=Proxy.newProxyInstance(ClassLoader.getSystemClassLoader(),new Class[]{iPackageManagerInterface},new HookHandler(sPackageManager));

              sPackageManagerField.set(currentActivityThread,proxy);

              PackageManager pm = context.getPackageManager();

              Field mPM= pm.getClass().getDeclaredField("mPM");
              mPM.setAccessible(true);

              mPM.set(pm,proxy);


          }catch (Exception e){
              Log.d("Hook",e.getLocalizedMessage());
          }

    }
}
