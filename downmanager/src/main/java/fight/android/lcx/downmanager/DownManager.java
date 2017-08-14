package fight.android.lcx.downmanager;

import android.content.Context;
import android.content.Intent;

/**
 * Created by lichenxi on 2017/8/13.
 */

public class DownManager {
     private static DownManager instance;
    private Context mContext;
    private DownManager(Context context) {
        this.mContext=context;
    }

      public static DownManager getInstance(Context context){
             if (instance==null){
                 instance=new DownManager(context);
             }
             return instance;
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

    public void addObserver(DataWatcher dataWatcher){
        DataChange.getinstance().addObserver(dataWatcher);
    }

    public void removeObserver(DataWatcher dataWatcher){
        DataChange.getinstance().deleteObserver(dataWatcher);
    }
}
