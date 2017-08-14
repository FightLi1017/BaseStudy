package fight.android.lcx.downmanager;

/**
 * Created by lichenxi on 2017/8/13.
 */

public class DownLoadTask implements Runnable{
   private DownEntry mentry;
   private boolean ispause;
    private boolean iscancel;
    public DownLoadTask(DownEntry entry) {
        this.mentry=entry;
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
        mentry.mDownloadStatus= DownEntry.DownloadStatus.downloading;
        DataChange.getinstance().postStatus(mentry);
        mentry.totallength=1024*100;
        for (int i=mentry.currentlength;i<mentry.totallength;i+=1024){
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            if (ispause || iscancel){
               mentry.mDownloadStatus=ispause? DownEntry.DownloadStatus.pause: DownEntry.DownloadStatus.cancel;
               DataChange.getinstance().postStatus(mentry);
                return;
            }
            mentry.currentlength+=1024;
            DataChange.getinstance().postStatus(mentry);
        }
        mentry.mDownloadStatus= DownEntry.DownloadStatus.complete;
        DataChange.getinstance().postStatus(mentry);
    }

    @Override
    public void run() {
            start();
    }
}
