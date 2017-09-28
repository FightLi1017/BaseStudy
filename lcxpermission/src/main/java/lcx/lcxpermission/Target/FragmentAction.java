package lcx.lcxpermission.Target;

import android.content.Context;
import android.content.Intent;
import android.support.v4.app.Fragment;

/**
 * Created by lichenxi on 2017/9/28.
 */

public class FragmentAction implements TargetAction {
   private Fragment mFragment;

    public FragmentAction(Fragment fragment) {
        mFragment = fragment;
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
