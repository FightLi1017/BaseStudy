package android.com.pluginstudy.intercept_activity;


import android.content.ComponentName;
import android.content.Intent;
import android.os.Handler;
import android.os.Message;

import java.lang.reflect.Field;

import static android.PluginManager.KEY_IS_PLUGIN;
import static android.PluginManager.KEY_TARGET_ACTIVITY;
import static android.PluginManager.KEY_TARGET_PACKAGE;

/**
 * @author：lichenxi
 * @date 2018/8/31 18
 * email：525603977@qq.com
 * Fighting
 */
public class ActivityThreadHandlerCallback implements Handler.Callback {
     Handler mBase;

    public ActivityThreadHandlerCallback(Handler handler) {
        mBase = handler;
    }

    @Override
    public boolean handleMessage(Message msg) {
        switch (msg.what){
            case 100:
                handleLaunchActivity(msg);
                break;

        }
        mBase.handleMessage(msg);
        return true;
    }

    private void handleLaunchActivity(Message msg) {
        Object obj = msg.obj;
        // 根据源码:
        // 这个对象是 ActivityClientRecord 类型
        // 我们修改它的intent字段为我们原来保存的即可.
/*        switch (msg.what) {
/             case LAUNCH_ACTIVITY: {
/                 Trace.traceBegin(Trace.TRACE_TAG_ACTIVITY_MANAGER, "activityStart");
/                 final ActivityClientRecord r = (ActivityClientRecord) msg.obj;
/
/                 r.packageInfo = getPackageInfoNoCheck(
/                         r.activityInfo.applicationInfo, r.compatInfo);
/                 handleLaunchActivity(r, null);
*/
        try {
                Field field=obj.getClass().getDeclaredField("intent");
                field.setAccessible(true);
                Intent intent=(Intent) field.get(obj);
                boolean isplugin=intent.getBooleanExtra(KEY_IS_PLUGIN,false);
                if (isplugin){
                    String PackageName=intent.getStringExtra(KEY_TARGET_PACKAGE);
                    String className=intent.getStringExtra(KEY_TARGET_ACTIVITY);
                    intent.setComponent(new ComponentName(PackageName,className));
                }
        } catch (NoSuchFieldException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
    }

}
