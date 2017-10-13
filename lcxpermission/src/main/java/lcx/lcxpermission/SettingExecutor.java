package lcx.lcxpermission;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.provider.Settings;

import java.lang.annotation.Target;

import lcx.lcxpermission.Target.TargetAction;

/**
 * Created by lichenxi on 2017/10/9.
 */

public class SettingExecutor {
    private TargetAction mTarget;
    private int mRequestCode;

    public SettingExecutor(TargetAction target, int requestCode) {
        mTarget = target;
        mRequestCode = requestCode;
    }

    public void enterSetting(){
        Context context=mTarget.getContext();
        Intent intent = new Intent(Settings.ACTION_APPLICATION_DETAILS_SETTINGS);
        Uri uri = Uri.fromParts("package", context.getPackageName(), null);
        intent.setData(uri);
        mTarget.startActivityForResult(intent, mRequestCode);
    }

}
