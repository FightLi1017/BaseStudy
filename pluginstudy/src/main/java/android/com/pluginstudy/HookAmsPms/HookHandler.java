package android.com.pluginstudy.HookAmsPms;

import android.util.Log;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.util.Arrays;

/**
 * @author：lichenxi
 * @date 2018/8/30 00
 * email：525603977@qq.com
 * Fighting
 */
public class HookHandler implements InvocationHandler {
    Object mBase;
    private static final String TAG = "HookHandler";
    public HookHandler(Object base) {
        mBase = base;
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {

        Log.d(TAG, "hey, baby; you are hooked!!");
        Log.d(TAG, "method:" + method.getName() + " called with args:" + Arrays.toString(args));


        return method.invoke(mBase,args);
    }
}
