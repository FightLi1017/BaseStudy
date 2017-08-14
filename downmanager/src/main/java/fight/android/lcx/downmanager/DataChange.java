package fight.android.lcx.downmanager;

import java.util.Observable;

/**
 * Created by lichenxi on 2017/8/14.
 */

public class DataChange extends Observable {
    private static DataChange instance;

    private DataChange() {
    }

     public static DataChange getinstance(){
         if (instance==null){
             instance=new DataChange();
         }
         return instance;
     }
    public void postStatus(DownEntry downEntry){
         setChanged();
         notifyObservers(downEntry);
     }
}
