package lcx.lcxpermission;


import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Build;
import android.support.annotation.NonNull;
import android.support.annotation.RequiresApi;
import android.support.v4.content.ContextCompat;
import android.util.Log;
import android.widget.Toast;

import java.lang.annotation.Annotation;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

import lcx.lcxpermission.Target.TargetAction;

/**
 * Created by lichenxi on 2017/9/28.
 */

public class RequestCreator implements
        Rationale,
        PermissionActivity.PermissionListener,
        PermissionActivity.RationaleListener{
     private TargetAction mTarget;
     private int requestCode;
     private Object mCallBack;
     private String[] mPermissions;
     private String[] mDeniedPermissions;
     private RationaleListener mRationaleListener;

    public RequestCreator rationale(RationaleListener rationaleListener) {
        mRationaleListener = rationaleListener;
        return this;
    }

    public RequestCreator(TargetAction target) {
         mTarget = target;
     }

    public RequestCreator requestCode(int requestCode){
        this.requestCode=requestCode;
        return  this;
    }
    public  RequestCreator callBack(@NonNull Object object){
        mCallBack=object;
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
           if (null==mPermissions){
               return;
           }
           mDeniedPermissions= getDeniedPermission(mTarget.getContext(),mPermissions);
           if (mDeniedPermissions.length>0){
              //开始请求权限了    开启一个Activity或者什么的
               PermissionActivity.setRationaleListener(this);
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
        if (mCallBack!=null){
            if (mCallBack instanceof  PermissionCallBack){
                ((PermissionCallBack)mCallBack).permissionSuccess(requestCode);
            }else{
                // TODO: 2017/9/28  注解写法 mCallBack 我们从这里对象里找到注解了方法
                Method successMethod=getAnnotationMethod(mCallBack,requestCode,PermissionSuccess.class);
                if (null!=successMethod){
                    try {
                        successMethod.setAccessible(true);
                        successMethod.invoke(mCallBack);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            }
        }
    }

    private Method getAnnotationMethod(Object callback,int requestcode,Class<? extends Annotation> annotationClass) {
       Method[] methods= callback.getClass().getDeclaredMethods();
       for (Method method:methods){
              if (method.getAnnotation(annotationClass)!=null){
               //找到注解的方法了 因为可能存在一个类中 出现多个请求 所以我们要判断requestcode
                if (PermissionSuccess.class.equals(annotationClass)){
                  if (method.getAnnotation(PermissionSuccess.class).value()==requestcode){
                            //匹配Method成功
                      return method;
                  }
                }
               else
                if (PermissionFailed.class.equals(annotationClass)){
                    if (method.getAnnotation(PermissionFailed.class).value()==requestcode){
                        //匹配Method成功
                        return method;
                    }
                  }
              }
       }

        return null;
    }

    private void callbackFailed(List<String> deniedList) {
        if (mCallBack!=null){
            if (mCallBack instanceof  PermissionCallBack){
                ((PermissionCallBack)mCallBack).permissionFailed(requestCode,deniedList);
            }else{

                Method successMethod=getAnnotationMethod(mCallBack,requestCode,PermissionFailed.class);
                if (null!=successMethod){
                    try {
                        successMethod.setAccessible(true);
                        successMethod.invoke(mCallBack,deniedList);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }else{
                    Log.e("SimplePermission", "Do you forget @PermissionSuccess or @PermissionFailed for callback method ?");
                }
            }
        }
    }
    private String[] getDeniedPermission(Context context, @NonNull String[] permissions) {
        ArrayList<String> dentist=new ArrayList<>(1);
        for (String permission: permissions)
          if (ContextCompat.checkSelfPermission(context,permission)!= PackageManager.PERMISSION_GRANTED)
              dentist.add(permission);
        return  dentist.toArray(new String[dentist.size()]);
    }

    @Override
    public void onRequestPermissionsResult(@NonNull String[] permissions, @NonNull int[] grantResults) {
         ArrayList<String> deniedist=new ArrayList<>();
         for (int i=0;i<permissions.length;i++)
           if (grantResults[i]!=PackageManager.PERMISSION_GRANTED)
                  deniedist.add(permissions[i]);

        if (deniedist.isEmpty())
            callbackSuccess();
        else
            callbackFailed(deniedist);

    }

    @RequiresApi(api = Build.VERSION_CODES.M)
    @Override
    public void resume() {
        PermissionActivity.setPermissionListener(this);
        Intent intent = new Intent(mTarget.getContext(), PermissionActivity.class);
        intent.putExtra(PermissionActivity.INPUT_PERMISSION, mDeniedPermissions);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        mTarget.startActivity(intent);
    }

    @Override
    public void cancel() {
        int[] results = new int[mPermissions.length];
        for (int i = 0; i < mPermissions.length; i++)
            results[i] = ContextCompat.checkSelfPermission(mTarget.getContext(), mPermissions[i]);
        onRequestPermissionsResult(mPermissions, results);
    }

    @RequiresApi(api = Build.VERSION_CODES.M)
    @Override
    public void onRationaleResult(boolean showRationale) {
         if (showRationale && mRationaleListener!=null){
            //点击了拒绝的按钮 给出提示信息
             mRationaleListener.showRequestPermissionRationale(this);
         }
         else {
            resume();
         }
    }
}
