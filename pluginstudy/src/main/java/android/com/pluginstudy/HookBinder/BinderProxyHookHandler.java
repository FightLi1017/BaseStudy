package android.com.pluginstudy.HookBinder;

import android.os.IBinder;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

/**
 * @author：lichenxi
 * @date 2018/8/28 00
 * email：525603977@qq.com
 * Fighting
 */
public class BinderProxyHookHandler implements InvocationHandler {

    IBinder base;
    Class<?> iinterface;
    Class<?> stub;
    public BinderProxyHookHandler(IBinder base) {
        this.base = base;
        try {
            this.stub = Class.forName("android.content.IClipboard$Stub");
            this.iinterface = Class.forName("android.content.IClipboard");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
       if ("queryLocalInterface".equals(method.getName()))  {
         return Proxy.newProxyInstance(proxy.getClass().getClassLoader(),new Class[]{iinterface},
                 new BinderHookHandler(base,stub));
       }

        return method.invoke(base,args);
    }
}
