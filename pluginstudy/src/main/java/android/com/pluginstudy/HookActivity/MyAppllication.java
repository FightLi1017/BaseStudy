package android.com.pluginstudy.HookActivity;

import android.app.Application;
import android.content.Context;

/**
 * @author：lichenxi
 * @date 2018/8/27 18
 * email：525603977@qq.com
 * Fighting
 */
public class MyAppllication extends Application {
    @Override
    protected void attachBaseContext(Context base) {
        super.attachBaseContext(base);
        try {
            HookHelper.HookApplication();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
