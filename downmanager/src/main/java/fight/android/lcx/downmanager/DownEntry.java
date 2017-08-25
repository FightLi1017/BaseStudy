package fight.android.lcx.downmanager;

import java.io.Serializable;

/**
 * Created by lichenxi on 2017/8/13.
 */

public class DownEntry  implements Serializable{
    public String id;
    public String name;
    public String url;
    public DownEntry(String url) {
        this.url = url;
        this.id = url;
        this.name = url.substring(url.lastIndexOf("/") + 1);
          }
    public enum  DownloadStatus{idle,waiting,downloading,paused,resume,cancel,complete}

    public DownloadStatus mDownloadStatus= DownloadStatus.idle;

    public int currentlength;

    public int totallength;

    @Override
    public String toString() {
        return "DownEntry:" +
                "Status=" + mDownloadStatus +
                ",currentlength=" + currentlength +
                ",totallength=" + totallength;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        DownEntry entry = (DownEntry) o;

        return id != null ? id.equals(entry.id) : entry.id == null;

    }

    @Override
    public int hashCode() {
        return id != null ? id.hashCode() : 0;
    }
}
