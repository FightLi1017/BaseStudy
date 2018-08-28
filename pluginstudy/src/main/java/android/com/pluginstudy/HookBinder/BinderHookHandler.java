package android.com.pluginstudy.HookBinder;

import android.content.ClipData;
import android.os.IBinder;
import android.util.Log;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

/**
 * @author：lichenxi
 * @date 2018/8/28 00
 * email：525603977@qq.com
 * Fighting
 */
public class BinderHookHandler implements InvocationHandler {
    private static final String TAG = "BinderHookHandler";
    // 原始的Service对象 (IInterface)
    Object base;
    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        // 把剪切版的内容替换为 "you are hooked"
        if ("getPrimaryClip".equals(method.getName())) {
            Log.d(TAG, "hook getPrimaryClip");
            return ClipData.newPlainText(null, "you are hooked");
        }

        // 欺骗系统,使之认为剪切版上一直有内容
        if ("hasPrimaryClip".equals(method.getName())) {
            return true;
        }

        return method.invoke(base,args);
    }

    public BinderHookHandler(IBinder base, Class<?> stubClass) {
        try {
            //在调用一次之后 让这个类 富有系统的功能
            Method asInterfaceMethod = stubClass.getDeclaredMethod("asInterface", IBinder.class);
            // IClipboard.Stub.asInterface(base);
            this.base = asInterfaceMethod.invoke(null, base);
        } catch (Exception e) {
            throw new RuntimeException("hooked failed!");
        }
    }


}
