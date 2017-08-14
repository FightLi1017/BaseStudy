package fight.android.lcx.downmanager;

import java.io.Serializable;

/**
 * Created by lichenxi on 2017/8/13.
 */

public class DownEntry  implements Serializable{
    public String id;
    public String name;
    public String url;

    public enum  DownloadStatus{waiting,downloading,pause,resume,cancel,complete}

    public DownloadStatus mDownloadStatus;

    public int currentlength;

    public int totallength;

    @Override
    public String toString() {
        return "DownEntry:" +
                "Status=" + mDownloadStatus +
                ",currentlength=" + currentlength +
                ",totallength=" + totallength;
    }
}
