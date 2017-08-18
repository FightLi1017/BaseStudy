package fight.android.lcx.downmanager;

import android.app.Service;
import android.content.Intent;
import android.os.Handler;
import android.os.IBinder;
import android.os.Message;
import android.support.annotation.Nullable;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.Executor;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.LinkedBlockingDeque;

/**
 * Created by lichenxi on 2017/8/13.
 */

public class DownLoadService extends Service {
    private HashMap<DownEntry,DownLoadTask> mDownLoadTasks=new HashMap<>();
    private LinkedBlockingDeque<DownEntry> mWaitDownLoadQueue=new LinkedBlockingDeque();
    private ExecutorService mExecutors;

    private Handler mHandler=new Handler(){
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            DownEntry entry=(DownEntry) msg.obj;
            switch (entry.mDownloadStatus){
                case cancel:
                case complete:
                case paused:
                    checkNext(entry);
                    break;
            }
            notifyState(entry);
        }
    };

    private void checkNext(DownEntry entry) {
        mDownLoadTasks.remove(entry);
        DownEntry newentry=mWaitDownLoadQueue.poll();
        if (newentry!=null){
            addDownLoad(newentry);
        }
    }

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
                addDownLoad(entry);
                break;
            case Constants.KEY_DOWM_ACTION_PAUSE:
                 pauseDownload(entry);
                break;
            case Constants.KEY_DOWM_ACTION_RESUME:
                  resumeDownload(entry);
                break;
            case Constants.KEY_DOWM_ACTION_CANCEL:
                  cancelDownload(entry);
            case Constants.KEY_DOWM_ACTION_PAUSEALL:
                pauseAllDownload();
                break;
            case Constants.KEY_DOWM_ACTION_RESUMEALL:

                break;

        }
    }

    private void pauseAllDownload() {
           for (Map.Entry<DownEntry,DownLoadTask> entry:mDownLoadTasks.entrySet()){
                entry.getValue().pause();
           }
          mDownLoadTasks.clear();

        while (mWaitDownLoadQueue.iterator().hasNext()){
            DownEntry entry=mWaitDownLoadQueue.poll();
            entry.mDownloadStatus= DownEntry.DownloadStatus.paused;
            notifyState(entry);
        }



    }

    private void addDownLoad(DownEntry entry){
      if (mDownLoadTasks.size()>=Constants.MAX_DOWN_SIZE){
          mWaitDownLoadQueue.offer(entry);
          entry.mDownloadStatus= DownEntry.DownloadStatus.waiting;
          notifyState(entry);
      }
      else{
           startDownload(entry);
      }
    }
    private void cancelDownload(DownEntry entry) {
        DownLoadTask task=mDownLoadTasks.remove(entry.url);
        if (task!=null){
            task.cancel();
        }
        else{
            mWaitDownLoadQueue.remove(entry);
            entry.mDownloadStatus= DownEntry.DownloadStatus.cancel;
            notifyState(entry);
        }

    }

    private void resumeDownload(DownEntry entry) {
           addDownLoad(entry);
    }

    private void pauseDownload(DownEntry entry) {
        DownLoadTask task=mDownLoadTasks.remove(entry.url);
        if (task!=null){
            task.pause();
        }
        else{
            mWaitDownLoadQueue.remove(entry);
            entry.mDownloadStatus= DownEntry.DownloadStatus.paused;
            notifyState(entry);
        }


    }


    private void notifyState(DownEntry entry) {
        DataChange.getinstance().postStatus(entry);
    }

    private void startDownload(DownEntry entry) {
        DownLoadTask task=new DownLoadTask(entry,mHandler);
        mDownLoadTasks.put(entry,task);
        mExecutors.execute(task);
    }
}
