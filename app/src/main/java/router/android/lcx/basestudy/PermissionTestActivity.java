package router.android.lcx.basestudy;

import android.Manifest;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import lcx.lcxpermission.PermissionCallBack;
import lcx.lcxpermission.PermissionFailed;
import lcx.lcxpermission.PermissionSuccess;
import lcx.lcxpermission.Rationale;
import lcx.lcxpermission.RationaleListener;
import lcx.lcxpermission.SimplePermission;
import rx.Observable;
import rx.Subscriber;
import rx.functions.Action1;
import rx.functions.Func1;

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
                        .requestCode(1).callBack(PermissionTestActivity.this).rationale(new RationaleListener() {
                    @Override
                    public void showRequestPermissionRationale(Rationale rationale) {
                        SimplePermission.showRationaleDialog(PermissionTestActivity.this,rationale).show();
                    }
                }).start();
            }
        });
        findViewById(R.id.button4).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SimplePermission.with(PermissionTestActivity.this)
                        .Permissions(Manifest.permission.CAMERA)
                        .requestCode(2).callBack(PermissionTestActivity.this).rationale(new RationaleListener() {
                    @Override
                    public void showRequestPermissionRationale(Rationale rationale) {
                        SimplePermission.showRationaleDialog(PermissionTestActivity.this,rationale).show();
                    }
                }).start();
            }
        });

        Observable.just("Hello Rx Sourse").map(new Func1<String,String>() {
            @Override
            public String call(String s) {
                return s+"......Map操作符转换了";
            }
        }).subscribe(new Action1<String>() {
            @Override
            public void call(String result) {
                Toast.makeText(PermissionTestActivity.this, result, Toast.LENGTH_SHORT).show();
            }
        });

        Observable.just("Hello Rx Sourse").flatMap(new Func1<String, Observable<List<String>>>() {
            @Override
            public Observable<List<String>> call(String s) {
               List<String> a =new ArrayList();
                a.add(s);
                a.add("sssss");
                a.add("hahahhaa");
                return Observable.just(a);
            }
        }).subscribe(new Action1<List<String>>() {
            @Override
            public void call(List<String> list) {
                Toast.makeText(PermissionTestActivity.this, list.toString(), Toast.LENGTH_SHORT).show();
            }
        });

    }
   @PermissionSuccess(1)
   public void success(){
       Toast.makeText(this,"成功", Toast.LENGTH_SHORT).show();
   }
    @PermissionFailed(1)
    public void failed( @NonNull List<String> deniedPermissions){
        Toast.makeText(this, "gg了"+deniedPermissions.toString(), Toast.LENGTH_SHORT).show();
    }
    @PermissionSuccess(2)
    public void success2(){
        Toast.makeText(this,"成功", Toast.LENGTH_SHORT).show();
    }
    @PermissionFailed(2)
    public void failed2( @NonNull List<String> deniedPermissions){
        Toast.makeText(this, "gg了"+deniedPermissions.toString(), Toast.LENGTH_SHORT).show();
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
