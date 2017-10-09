package router.android.lcx.basestudy;

import android.Manifest;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import java.util.List;

import lcx.lcxpermission.PermissionCallBack;
import lcx.lcxpermission.Rationale;
import lcx.lcxpermission.RationaleListener;
import lcx.lcxpermission.SimplePermission;

public class PermissionTestActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_permission_test);
        findViewById(R.id.button).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SimplePermission.with(PermissionTestActivity.this)
                        .Permissions(Manifest.permission.READ_EXTERNAL_STORAGE)
                        .requestCode(1).callBack(new PermissionCallBack() {
                    @Override
                    public void permissionSuccess(int requestcode) {
                        Toast.makeText(PermissionTestActivity.this,"成功了",Toast.LENGTH_SHORT).show();
                    }

                    @Override
                    public void permissionFailed(int requestcode, List<String> deniedPermissions) {
                        Toast.makeText(PermissionTestActivity.this,"失败了",Toast.LENGTH_SHORT).show();
                        if (SimplePermission.hasAlwaysDentiedPermission(PermissionTestActivity.this,deniedPermissions)){
                               SimplePermission.SettingDialog(PermissionTestActivity.this,101).show();
                        }
                    }
                }).rationale(new RationaleListener() {
                    @Override
                    public void showRequestPermissionRationale(Rationale rationale) {
                        SimplePermission.showRationaleDialog(PermissionTestActivity.this,rationale).show();
                    }
                }).start();
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        switch (requestCode) {
            case 101: {
                break;
            }
        }
    }
}
