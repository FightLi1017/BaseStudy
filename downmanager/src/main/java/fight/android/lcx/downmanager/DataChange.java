package fight.android.lcx.downmanager;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Observable;

/**
 * Created by lichenxi on 2017/8/14.
 */

public class DataChange extends Observable {
    private static DataChange instance;
    private LinkedHashMap<String,DownEntry> mOperatorEntries;
    private DataChange() {
        mOperatorEntries=new LinkedHashMap<>();
    }

     public static DataChange getinstance(){
         if (instance==null){
             instance=new DataChange();
         }
         return instance;
     }
    public void postStatus(DownEntry downEntry){
         mOperatorEntries.put(downEntry.id,downEntry);
         setChanged();
         notifyObservers(downEntry);
     }
    public ArrayList<DownEntry> queryAllRecoverEntry(){
        ArrayList<DownEntry>  mRecoverEntry=null;
        for (Map.Entry<String,DownEntry> entry:mOperatorEntries.entrySet()){
                 if (entry.getValue().mDownloadStatus== DownEntry.DownloadStatus.paused){
                     if (mRecoverEntry==null){
                         mRecoverEntry=new ArrayList<>();
                     }
                     mRecoverEntry.add(entry.getValue());
                 }
        }
        return mRecoverEntry;
    }
}
