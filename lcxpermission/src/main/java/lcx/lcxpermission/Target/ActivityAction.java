package lcx.lcxpermission.Target;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;

/**
 * Created by lichenxi on 2017/9/28.
 */

public class ActivityAction implements TargetAction {
    private Activity mActivity;
    public ActivityAction(Activity activity) {
        this.mActivity=activity;
    }

    @Override
    public Context getContext() {
        return null;
    }

    @Override
    public void startActivity(Intent intent) {

    }

    @Override
    public void startActivityForResult(Intent intent, int requestCode) {

    }
}
