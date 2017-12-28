package lcx.lcxpermission;

import android.app.Activity;
import android.content.Context;
import android.content.pm.PackageManager;
import android.os.Build;
import android.support.annotation.NonNull;
import android.support.v4.app.AppOpsManagerCompat;
import android.support.v4.app.Fragment;
import android.support.v4.content.ContextCompat;
import android.text.TextUtils;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import lcx.lcxpermission.Target.ActivityAction;
import lcx.lcxpermission.Target.ContextAction;
import lcx.lcxpermission.Target.FragmentAction;
import lcx.lcxpermission.Target.SupportFragmentAction;

/**
 * Created by lichenxi on 2017/9/28.
 */

public class SimplePermission {
    /**
     * Check if the calling context has a set of permissions.
     *
     * @param context     {@link Context}.
     * @param permissions one or more permissions.
     * @return true, other wise is false.
     */
    public static boolean hasPermission(@NonNull Context context, @NonNull String... permissions) {
        return hasPermission(context, Arrays.asList(permissions));
    }

    /**
     * Check if the calling context has a set of permissions.
     *
     * @param context     {@link Context}.
     * @param permissions one or more permissions.
     * @return true, other wise is false.
     */
    public static boolean hasPermission(@NonNull Context context, @NonNull List<String> permissions) {
        if (Build.VERSION.SDK_INT < Build.VERSION_CODES.M) return true;
        for (String permission : permissions) {
            String op = AppOpsManagerCompat.permissionToOp(permission);

            if (TextUtils.isEmpty(op)) continue;
            int result = AppOpsManagerCompat.noteProxyOp(context, op, context.getPackageName());
            if (result == AppOpsManagerCompat.MODE_IGNORED) return false;
            result = ContextCompat.checkSelfPermission(context, permission);
            if (result != PackageManager.PERMISSION_GRANTED) return false;
        }
        return true;
    }
    public  static RequestCreator  with(Activity activity){
        return  new RequestCreator(new ActivityAction(activity)) ;
    }
    public  static RequestCreator  with(Fragment  fragment){
        return  new RequestCreator(new SupportFragmentAction(fragment));
    }
    public  static RequestCreator  with(android.app.Fragment fragment){
        return  new RequestCreator(new FragmentAction(fragment));
    }
    public  static RequestCreator  with(Context context){
        return  new RequestCreator(new ContextAction(context));
    }


    public static   RationaleDialog showRationaleDialog(@NonNull Context context, Rationale rationale) {
      return new RationaleDialog(context,rationale);
    }

    /**
     *  用户点击了禁止后不在询问的按钮
     * @param activity
     * @param deniedist
     * @return
     */
   public static boolean hasAlwaysDentiedPermission(Activity activity,List<String> deniedist){
       if (Build.VERSION.SDK_INT < Build.VERSION_CODES.M) return false;

       if (deniedist.size() == 0) return false;
       for (String permission:deniedist){
           boolean rationale=activity.shouldShowRequestPermissionRationale(permission);
           if (! rationale) return  true;
       }
       return  false;
   }
    public static boolean hasAlwaysDentiedPermission(@NonNull Fragment fragment,@NonNull ArrayList<String> deniedist){
        if (Build.VERSION.SDK_INT < Build.VERSION_CODES.M) return false;

        if (deniedist.size() == 0) return false;
        for (String permission:deniedist){
            boolean rationale=fragment.shouldShowRequestPermissionRationale(permission);
            if (! rationale) return  true;
        }
        return  false;
    }
    public static boolean hasAlwaysDentiedPermission(@NonNull android.app.Fragment fragment,@NonNull ArrayList<String> deniedist){
        if (Build.VERSION.SDK_INT < Build.VERSION_CODES.M) return false;

        if (deniedist.size() == 0) return false;
        for (String permission:deniedist){
            boolean rationale=fragment.shouldShowRequestPermissionRationale(permission);
            if (! rationale) return  true;
        }
        return  false;
    }

    public static SettingDialog SettingDialog(Activity activity) {
       return  new SettingDialog(activity,new SettingExecutor(new ActivityAction(activity),0));
    }
    public static SettingDialog SettingDialog(android.app.Fragment fragment, int requestCode) {
        return  new SettingDialog(fragment.getActivity(),new SettingExecutor(new FragmentAction(fragment),0));
    }
    public static SettingDialog SettingDialog(Fragment fragment, int requestCode) {
        return  new SettingDialog(fragment.getActivity(),new SettingExecutor(new SupportFragmentAction(fragment),0));
    }
}
