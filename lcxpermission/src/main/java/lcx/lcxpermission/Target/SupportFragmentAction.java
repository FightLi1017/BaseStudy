package lcx.lcxpermission.Target;

import android.content.Context;
import android.content.Intent;
import android.support.v4.app.Fragment;

/**
 * Created by lichenxi on 2017/9/28.
 */

public class SupportFragmentAction implements TargetAction {
   private Fragment mFragment;

    public SupportFragmentAction(Fragment fragment) {
        mFragment = fragment;
    }

    @Override
    public Context getContext() {
        return mFragment.getActivity();
    }

    @Override
    public void startActivity(Intent intent) {
        mFragment.startActivity(intent);
    }

    @Override
    public void startActivityForResult(Intent intent, int requestCode) {
        mFragment.startActivityForResult(intent,requestCode);
    }
}
