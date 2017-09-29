package router.android.lcx.basestudy;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import java.util.List;

import lcx.lcxpermission.PermissionCallBack;
import lcx.lcxpermission.SimplePermission;

public class PermissionTestActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_permission_test);
        SimplePermission.with(this).requestCode(1).callBack(new PermissionCallBack() {
            @Override
            public void permissionSuccess(int requestcode) {

            }

            @Override
            public void permissionFailed(int requestcode, List<String> deniedPermissions) {

            }
        }).start();
    }
}
