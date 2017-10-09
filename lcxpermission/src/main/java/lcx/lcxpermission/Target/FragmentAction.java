package lcx.lcxpermission.Target;

import android.app.Fragment;
import android.content.Context;
import android.content.Intent;

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
