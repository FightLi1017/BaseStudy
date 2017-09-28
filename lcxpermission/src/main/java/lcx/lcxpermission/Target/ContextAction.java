package lcx.lcxpermission.Target;

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
        return null;
    }

    @Override
    public void startActivity(Intent intent) {

    }

    @Override
    public void startActivityForResult(Intent intent, int requestCode) {

    }
}
