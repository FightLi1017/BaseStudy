package fight.android.lcx.downmanager;

import android.util.Log;

/**
 * Created by lichenxi on 2017/8/14.
 */

public class TLog {
     private static String tag="DownManager";

    public static void e(String msg){
        Log.e(tag,msg);
    }
    public static void d(String msg){
        Log.d(tag,msg);
    }
    public static void i(String msg){
        Log.i(tag,msg);

    }
}
