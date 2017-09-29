package lcx.lcxpermission;


import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Build;
import android.support.annotation.NonNull;
import android.support.v4.content.ContextCompat;

import java.util.ArrayList;
import java.util.List;

import lcx.lcxpermission.Target.TargetAction;

/**
 * Created by lichenxi on 2017/9/28.
 */

public class RequestCreator implements PermissionActivity.PermissionListener,PermissionActivity.RationaleListener{
     private TargetAction mTarget;
     private int requestCode;
     private Object mPermissionObject;
     private String[] mPermissions;
    private String[] mDeniedPermissions;
     public RequestCreator(TargetAction target) {
         mTarget = target;
     }

    public RequestCreator requestCode(int requestCode){
        this.requestCode=requestCode;
        return  this;
    }
    public  RequestCreator callBack(@NonNull Object object){
        mPermissionObject=object;
        return this;
    }
   public RequestCreator Permissions(String...permissions){
       mPermissions=permissions;
       return this;
   }
    public  void start(){
       if (Build.VERSION.SDK_INT < Build.VERSION_CODES.M){
              callbackSuccess();
       }
       else{
           mDeniedPermissions= getDeniedPermission(mTarget.getContext(),mPermissions);
           if (mDeniedPermissions.length>0){
              //开始请求权限了    开启一个Activity或者什么的
               Intent intent = new Intent(mTarget.getContext(), PermissionActivity.class);
               intent.putExtra(PermissionActivity.INPUT_PERMISSION, mDeniedPermissions);
               intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
               mTarget.startActivity(intent);
           }else {
               callbackSuccess();
           }
       }
    }

    private void callbackSuccess() {
        if (mPermissionObject!=null){
            if (mPermissionObject instanceof  PermissionCallBack){
                ((PermissionCallBack)mPermissionObject).permissionSuccess(requestCode);
            }else{
                //// TODO: 2017/9/28  注解写法

            }
        }
    }
    private void callbackFailed(List<String> deniedList) {
        if (mPermissionObject!=null){
            if (mPermissionObject instanceof  PermissionCallBack){
                ((PermissionCallBack)mPermissionObject).permissionFailed(requestCode,deniedList);
            }else{
                //// TODO: 2017/9/28  注解写法

            }
        }
    }
    private String[] getDeniedPermission(Context context, String[] permissions) {
        ArrayList<String> dentist=new ArrayList<>(1);
        for (String permission: permissions)
          if (ContextCompat.checkSelfPermission(context,permission)!= PackageManager.PERMISSION_GRANTED)
              dentist.add(permission);
        return  dentist.toArray(new String[dentist.size()]);
    }

    @Override
    public void onRequestPermissionsResult(@NonNull String[] permissions, @NonNull int[] grantResults) {

    }

    @Override
    public void onRationaleResult(boolean showRationale) {

    }
}
