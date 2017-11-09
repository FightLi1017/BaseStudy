package fight.android.lcx.downmanager.SimpleDown;

/**
 * Created by lichenxi on 2017/11/10.
 */

public class Handleutil {

    public static void runUiThread(Runnable runnable){
        FileDownManager.getinstance().getDelivery().post(runnable);
    }
}
