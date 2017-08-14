package fight.android.lcx.downmanager;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.support.annotation.Nullable;

import java.util.HashMap;
import java.util.concurrent.Executor;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * Created by lichenxi on 2017/8/13.
 */

public class DownLoadService extends Service {
    private HashMap<String,DownLoadTask> mDownLoadTasks=new HashMap<>();
    private ExecutorService mExecutors;

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        DownEntry entry=(DownEntry) intent.getSerializableExtra(Constants.DOWN_ENTRY);
        int action =intent.getIntExtra(Constants.KEY_DOWN_ACTION,-1);
        doAction(action,entry);

        return super.onStartCommand(intent, flags, startId);

    }

    @Override
    public void onCreate() {
        super.onCreate();
        mExecutors=Executors.newCachedThreadPool();
    }

    private void doAction(int action, DownEntry entry) {
        switch (action){
            case Constants.KEY_DOWM_ACTION_ADD:
                startDownload(entry);
                break;
            case Constants.KEY_DOWM_ACTION_PAUSE:
                 pauseDownload(entry);
                break;
            case Constants.KEY_DOWM_ACTION_RESUME:
                  resumeDownload(entry);
                break;
            case Constants.KEY_DOWM_ACTION_CANCEL:
                  cancelDownload(entry);
                break;

        }
    }

    private void cancelDownload(DownEntry entry) {
        DownLoadTask task=mDownLoadTasks.remove(entry.url);
        if (task!=null)
            task.cancel();
    }

    private void resumeDownload(DownEntry entry) {
           startDownload(entry);
    }

    private void pauseDownload(DownEntry entry) {
        DownLoadTask task=mDownLoadTasks.remove(entry.url);
        if (task!=null)
               task.pause();
    }

    private void startDownload(DownEntry entry) {
        DownLoadTask task=new DownLoadTask(entry);
        mDownLoadTasks.put(entry.url,task);
        mExecutors.execute(task);
    }
}
