package lcx.lcxpermission.ReflectTest;

import android.app.Activity;

import java.lang.reflect.Method;

/**
 * Created by lichenxi on 2017/9/29.
 */

public class ReflectTest {

    public static void main(String[] args) {
          Class jsandroidClass=JstoAndroidHelper.class;
        try {
            Method method=jsandroidClass.getDeclaredMethod("showdialog",String.class);
            method.invoke(jsandroidClass.newInstance(),"lichenxi");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
