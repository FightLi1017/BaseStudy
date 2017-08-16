package fight.android.lcx.downmanager;

import android.os.Handler;
import android.os.Message;

/**
 * Created by lichenxi on 2017/8/13.
 */

public class DownLoadTask implements Runnable{
   private DownEntry mDownEntry;
   private boolean ispause;
    private boolean iscancel;
    private Handler mHandler;
    public DownLoadTask(DownEntry entry) {
        this.mDownEntry=entry;
    }
    public DownLoadTask(DownEntry entry, Handler handler) {
        this.mDownEntry=entry;
        this.mHandler=handler;
    }

    public void pause() {
        TLog.d("pause");
        ispause=true;
    }

    public void cancel() {
        TLog.d("cancel");
        iscancel=true;
    }


    public void start() {
        mDownEntry.mDownloadStatus= DownEntry.DownloadStatus.downloading;
        mDownEntry.totallength=1024*20;
        sendmsg();
        for (int i=mDownEntry.currentlength;i<mDownEntry.totallength;i+=1024){
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            if (ispause || iscancel){
                mDownEntry.mDownloadStatus=ispause? DownEntry.DownloadStatus.paused: DownEntry.DownloadStatus.cancel;
                sendmsg();
                return;
            }
            mDownEntry.currentlength+=1024;
            sendmsg();
        }
        mDownEntry.mDownloadStatus= DownEntry.DownloadStatus.complete;
        sendmsg();
    }

    private void sendmsg() {
        Message msg=mHandler.obtainMessage();
        msg.obj=mDownEntry;
        mHandler.sendMessage(msg);
    }

    @Override
    public void run() {
            start();
    }
}
