package lcx.lcxpermission;

import android.app.Activity;
import android.os.Build;
import android.support.annotation.NonNull;
import android.support.annotation.RequiresApi;
import android.os.Bundle;

@RequiresApi(api = Build.VERSION_CODES.M)
public final class PermissionActivity extends Activity {
    private String[] mDeniedPermissions;
    private static RationaleListener mRationaleListener;
    private static PermissionListener mPermissionListener;
    public static  final String INPUT_PERMISSION="INPUT_PERMISSION";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        String[] permissions = getIntent().getStringArrayExtra(INPUT_PERMISSION);
        if (null==permissions){
             finish();
             return;
        }
        //检测是否之前点击过拒绝按钮 如果有的话 那就给你提示 告诉他不要拒绝 否则会有可怕的时候发生
          if (mRationaleListener!=null){
              boolean rationale=false;
              for (String permission: permissions){
                  //检测是否拒绝过 默认是false 当拒绝了之后 就返回true了
                  rationale=shouldShowRequestPermissionRationale(permission);
                  if (rationale) break;
              }
              mRationaleListener.onRationaleResult(rationale);
              mRationaleListener=null;
              finish();
              return;
          }
          requestPermissions(permissions,1);
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        if (mPermissionListener!= null){
            mPermissionListener.onRequestPermissionsResult(permissions,grantResults);
            mPermissionListener=null;
        }

        finish();
    }
    public static void setRationaleListener(RationaleListener rationaleListener) {
        mRationaleListener = rationaleListener;
    }

    public static void setPermissionListener(PermissionListener permissionListener) {
        mPermissionListener = permissionListener;
    }

    interface RationaleListener {
        void onRationaleResult(boolean showRationale);
    }

    interface PermissionListener {
        void onRequestPermissionsResult(@NonNull String[] permissions, @NonNull int[] grantResults);
    }
}
