package fight.android.lcx.downmanager.SimpleDown;

/**
 * Created by lichenxi on 2017/10/9.
 */

public class Progress {
    public   long totalReaded = 0;
    public int contentLength;

    public long getTotalReaded() {
        return totalReaded;
    }

    public void setTotalReaded(long totalReaded) {
        this.totalReaded = totalReaded;
    }

    public int getContentLength() {
        return contentLength;
    }

    public void setContentLength(int contentLength) {
        this.contentLength = contentLength;
    }
}
