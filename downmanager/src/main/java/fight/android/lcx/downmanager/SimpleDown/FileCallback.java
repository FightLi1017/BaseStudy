package fight.android.lcx.downmanager.SimpleDown;

import java.io.File;

/**
 * Created by lichenxi on 2017/11/9.
 */

public interface FileCallback {
    void success(File file);
    void downloadProgress(Progress progress);
    void error();
}
