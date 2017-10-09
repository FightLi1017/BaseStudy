package lcx.lcxpermission.Target;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;

/**
 * Created by lichenxi on 2017/9/28.
 */

public class ContextAction implements TargetAction {
   private Context mContext;
    public ContextAction(Context context) {
        this.mContext=context;
    }

    @Override
    public Context getContext() {
        return mContext;
    }

    @Override
    public void startActivity(Intent intent) {
        mContext.startActivity(intent);
    }

    @Override
    public void startActivityForResult(Intent intent, int requestCode) {
        if (mContext instanceof Activity) ((Activity) mContext).startActivityForResult(intent, requestCode);
        else mContext.startActivity(intent);
    }
}
