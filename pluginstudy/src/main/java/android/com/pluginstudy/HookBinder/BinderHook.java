package android.com.pluginstudy.HookBinder;

import android.os.IBinder;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.util.HashMap;
import java.util.Map;

/**
 * @author：lichenxi
 * @date 2018/8/27 23
 * email：525603977@qq.com
 * Fighting
 */
public class BinderHook {
     public  static void hookClipboardService() throws Exception{
         final String CLIPBOARD_SERVICE = "clipboard";
         Class<?> serviceManager = Class.forName("android.os.ServiceManager");
         Method serviceMethod=serviceManager.getDeclaredMethod("getService",String.class);
         //这里的这里类 其实就是系统给我们返回的代理IBinder类
         IBinder rawBinder = (IBinder) serviceMethod.invoke(null,CLIPBOARD_SERVICE);
         //然后我们动态代理一下这个类

         IBinder hookedBinder=(IBinder) Proxy.newProxyInstance(serviceManager.getClassLoader(),
                 new Class[]{IBinder.class},
                  new BinderProxyHookHandler(rawBinder));
         //然后我们把这个Hook的Binder 放到缓存中
         Field field=serviceManager.getDeclaredField("sCache");
         field.setAccessible(true);
         Map<String, IBinder> cacheMap= (HashMap)field.get(null);
         cacheMap.put(CLIPBOARD_SERVICE,hookedBinder);
         field.set(null,cacheMap);
     }
}
