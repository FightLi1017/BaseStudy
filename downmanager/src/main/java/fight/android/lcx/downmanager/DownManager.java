package fight.android.lcx.downmanager;

import android.content.Context;
import android.content.Intent;

import java.util.LinkedHashMap;
import java.util.Map;

/**
 * Created by lichenxi on 2017/8/13.
 */

public class DownManager {
     private static DownManager instance;
    private Context mContext;
    private static ACache mCache;
    private DownManager(Context context) {
        this.mContext=context;
        mCache=ACache.get(mContext);
    }

      public static DownManager getInstance(Context context){
             if (instance==null){
                 instance=new DownManager(context);
             }
             InitBase(context);
             context.startService(new Intent(context,DownLoadService.class));
             return instance;
      }

    private static void InitBase(Context context) {
        LinkedHashMap<String,DownEntry> mOperatorEntries=(LinkedHashMap)mCache.getAsObject(Constants.KEY_DOWN_DISK);
        if (mOperatorEntries!=null){
            for (Map.Entry<String ,DownEntry> entry:mOperatorEntries.entrySet()){
                if (entry.getValue().mDownloadStatus == DownEntry.DownloadStatus.downloading || entry.getValue().mDownloadStatus == DownEntry.DownloadStatus.waiting){
                    entry.getValue().mDownloadStatus =  DownEntry.DownloadStatus.paused;
                }
            }
            DataChange.getinstance(context).updataOperator(mOperatorEntries);
        }
    }

    public void add(DownEntry entry){
        Intent intent =new Intent(mContext,DownLoadService.class);
        intent.putExtra(Constants.DOWN_ENTRY,entry);
        intent.putExtra(Constants.KEY_DOWN_ACTION, Constants.KEY_DOWM_ACTION_ADD);
        mContext.startService(intent);
    }

    public void pause(DownEntry entry){
        Intent intent =new Intent(mContext,DownLoadService.class);
        intent.putExtra(Constants.DOWN_ENTRY,entry);
        intent.putExtra(Constants.KEY_DOWN_ACTION, Constants.KEY_DOWM_ACTION_PAUSE);
        mContext.startService(intent);
    }

    public void  resume(DownEntry entry){
        Intent intent =new Intent(mContext,DownLoadService.class);
        intent.putExtra(Constants.DOWN_ENTRY,entry);
        intent.putExtra(Constants.KEY_DOWN_ACTION, Constants.KEY_DOWM_ACTION_RESUME);
        mContext.startService(intent);
    }
    public void cancel(DownEntry entry){
        Intent intent =new Intent(mContext,DownLoadService.class);
        intent.putExtra(Constants.DOWN_ENTRY,entry);
        intent.putExtra(Constants.KEY_DOWN_ACTION, Constants.KEY_DOWM_ACTION_CANCEL);
        mContext.startService(intent);
    }
    public void pauseAll(){
        Intent intent =new Intent(mContext,DownLoadService.class);
        intent.putExtra(Constants.KEY_DOWN_ACTION, Constants.KEY_DOWM_ACTION_PAUSEALL);
        mContext.startService(intent);
    }
    public void resumeAll(){
        Intent intent =new Intent(mContext,DownLoadService.class);
        intent.putExtra(Constants.KEY_DOWN_ACTION, Constants.KEY_DOWM_ACTION_RESUMEALL);
        mContext.startService(intent);
    }
    public void addObserver(DataWatcher dataWatcher){
        DataChange.getinstance(mContext).addObserver(dataWatcher);
    }

    public void removeObserver(DataWatcher dataWatcher){
        DataChange.getinstance(mContext).deleteObserver(dataWatcher);
    }

    public DownEntry queryDownEntry(String id) {
        return  DataChange.getinstance(mContext).queryDownEntry(id);
    }
}
