package lcx.lcxpermission.Target;

import android.content.Context;
import android.content.Intent;

/**
 * Created by lichenxi on 2017/9/28.
 */

public interface TargetAction {
    Context  getContext();
    void startActivity(Intent intent);
    void startActivityForResult(Intent intent, int requestCode);
}
