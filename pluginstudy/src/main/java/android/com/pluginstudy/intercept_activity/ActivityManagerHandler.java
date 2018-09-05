package android.com.pluginstudy.intercept_activity;

import android.content.ComponentName;
import android.content.Intent;
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
public class ActivityManagerHandler implements InvocationHandler {

    Object mBase;
    private static final String TAG = "HookHandler";
    public ActivityManagerHandler(Object base) {
        mBase = base;
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        /**
         * 这里我们需要把我们要启动的目标Activity
         * 替换成我们的替身在Man里面注册好的Activity 做插件化的话 一般都会定义很多个
         */
        if ("startActivity".equals(method.getName())){
            Log.d(TAG, "method:" + method.getName() + " called with args:" + Arrays.toString(args));
            Intent raw;
            //1找到intent参数
            int index = 0;
            for (int i = 0; i < args.length; i++) {
                if (args[i] instanceof Intent){
                    index=i;
                    break;
                }
            }
            raw= (Intent) args[index];

            Intent newIntent = new Intent();

            //构造一个新的intent 并且保存真实的intent
            ComponentName componentName=new ComponentName("android.com.pluginstudy.intercept_activity",StubActivity.class.getName());
            newIntent.setComponent(componentName);

//            newIntent.putExtra(EXTRA_TARGET_INTENT, raw);

            args[index]=newIntent;

            Log.d(TAG, "hook success");
            return method.invoke(mBase,args);
        }
        return method.invoke(mBase,args);
    }
}
