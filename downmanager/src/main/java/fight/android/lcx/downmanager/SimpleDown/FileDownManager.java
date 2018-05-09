package fight.android.lcx.downmanager.SimpleDown;

import android.os.Handler;
import android.os.Looper;

/**
 * Created by lichenxi on 2017/10/9.
 */

public class FileDownManager {

    private Handler mDelivery;

    private FileDownManager() {
        mDelivery=new Handler(Looper.getMainLooper());
    }

     public static FileDownManager getinstance(){
        return ManagerHolder.holder;
     }
     static class ManagerHolder{
         static FileDownManager holder=new FileDownManager();
     }
    public Handler getDelivery() {
        return mDelivery;
    }

    public static FileRequestOperator url(String uri){
         return new FileRequestOperator(uri);
    }


}
