//package router.android.lcx.basestudy;
//
//import android.Manifest;
//import android.content.Intent;
//import android.content.SharedPreferences;
//import android.support.annotation.NonNull;
//import android.support.v7.app.AlertDialog;
//import android.support.v7.app.AppCompatActivity;
//import android.os.Bundle;
//import android.util.Log;
//import android.view.LayoutInflater;
//import android.view.View;
//import android.widget.Button;
//import android.widget.ListView;
//import android.widget.TextView;
//import android.widget.Toast;
//
//import java.util.ArrayList;
//import java.util.HashSet;
//import java.util.List;
//import java.util.Set;
//
//import lcx.lcxpermission.PermissionCallBack;
//import lcx.lcxpermission.PermissionFailed;
//import lcx.lcxpermission.PermissionSuccess;
//import lcx.lcxpermission.Rationale;
//import lcx.lcxpermission.RationaleListener;
//import lcx.lcxpermission.SimplePermission;
//import rx.Observable;
//import rx.Subscriber;
//import rx.functions.Action1;
//import rx.functions.Func1;
//
//public class PermissionTestActivity extends AppCompatActivity {
//    SharedPreferenceUtil  mPreferenceUtil;
//    private final String HHH="hhh";
//    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_permission_test);
//        mPreferenceUtil=SharedPreferenceUtil.getInstance(this);
//        final   SharedPreferences.Editor editor=mPreferenceUtil.getEditor();
//        TextView view= (TextView)LayoutInflater.from(this).inflate(R.layout.layout,null);
//        view.setText("到底行不行");
//        AlertDialog alertDialog=new AlertDialog.Builder(this).setView(view).create();
//        alertDialog.show();
////        button   .setOnClickListener(new View.OnClickListener() {
////            @Override
////            public void onClick(View view) {
//////                editor.remove(HHH);
//////                editor.putStringSet(HHH,getdata());
//////                editor.commit();
//////                SimplePermission.with(PermissionTestActivity.this)
//////                        .Permissions(Manifest.permission.READ_EXTERNAL_STORAGE)
//////                        .requestCode(1).callBack(PermissionTestActivity.this).rationale(new RationaleListener() {
//////                    @Override
//////                    public void showRequestPermissionRationale(Rationale rationale) {
//////                        SimplePermission.showRationaleDialog(PermissionTestActivity.this,rationale).show();
//////                    }
//////                }).start();
////            int[] screen=new int[2];
////                view.getLocationOnScreen(screen);
////                Log.d("LCX","Location"+screen[0]+","+screen[1]);
////
////                int[] window=new int[2];
////                view.getLocationInWindow(screen);
////                Log.d("LCX","Location"+window[0]+","+window[1]);
////            }
////        });
//        findViewById(R.id.button4).setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//              HashSet set=  mPreferenceUtil.getValue(HHH,new HashSet<>());
//              Toast(set.size()+"");
////                SimplePermission.with(PermissionTestActivity.this)
////                        .Permissions(Manifest.permission.USE_SIP)
////                        .requestCode(2).callBack(PermissionTestActivity.this).rationale(new RationaleListener() {
////                    @Override
////                    public void showRequestPermissionRationale(Rationale rationale) {
////                        SimplePermission.showRationaleDialog(PermissionTestActivity.this,rationale).show();
////                    }
////                }).callBack(new PermissionCallBack() {
////                    @Override
////                    public void permissionSuccess(int requestcode) {
////
////                        Toast.makeText(PermissionTestActivity.this, "成功了", Toast.LENGTH_SHORT).show();
////
////                    }
////
////                    @Override
////                    public void permissionFailed(int requestcode, List<String> deniedPermissions) {
////                        if (SimplePermission.hasAlwaysDentiedPermission(
////                                        PermissionTestActivity.this
////                                                ,deniedPermissions)){
////                            SimplePermission.SettingDialog(PermissionTestActivity.this).show();
////                        }
////                    }
////                }).start();
//            }
//        });
//
////        Observable.just("Hello Rx Sourse").map(new Func1<String,String>() {
////            @Override
////            public String call(String s) {
////                return s+"......Map操作符转换了";
////            }
////        }).subscribe(new Action1<String>() {
////            @Override
////            public void call(String result) {
////                Toast.makeText(PermissionTestActivity.this, result, Toast.LENGTH_SHORT).show();
////            }
////        });
////
////        Observable.just("Hello Rx Sourse").flatMap(new Func1<String, Observable<List<String>>>() {
////            @Override
////            public Observable<List<String>> call(String s) {
////               List<String> a =new ArrayList();
////                a.add(s);
////                a.add("sssss");
////                a.add("hahahhaa");
////                return Observable.just(a);
////            }
////        }).subscribe(new Action1<List<String>>() {
////            @Override
////            public void call(List<String> list) {
////                Toast.makeText(PermissionTestActivity.this, list.toString(), Toast.LENGTH_SHORT).show();
////            }
////        });
//
//    }
////   @PermissionSuccess(1)
////   public void success(){
////       Toast.makeText(this,"成功", Toast.LENGTH_SHORT).show();
////   }
////    @PermissionFailed(1)
////    public void failed( @NonNull List<String> deniedPermissions){
////        if (SimplePermission.hasPermission(PermissionTestActivity.this,deniedPermissions)){
////            Toast.makeText(this,"成功", Toast.LENGTH_SHORT).show();
////        }else{
////            Toast.makeText(this,"gg", Toast.LENGTH_SHORT).show();
////            if (SimplePermission.hasAlwaysDentiedPermission(
////                    PermissionTestActivity.this
////                    ,deniedPermissions)){
////                SimplePermission.SettingDialog(PermissionTestActivity.this).show();
////            }
////        }
////
////    }
////    @PermissionSuccess(2)
////    public void success2(){
////        Toast.makeText(this,"成功", Toast.LENGTH_SHORT).show();
////    }
////    @PermissionFailed(2)
////    public void failed2( @NonNull List<String> deniedPermissions){
////        Toast.makeText(this, "gg了"+deniedPermissions.toString(), Toast.LENGTH_SHORT).show();
////    }
// public void Toast(String s){
//        Toast.makeText(this,s,Toast.LENGTH_SHORT).show();
// }
// public HashSet getdata(){
//        HashSet hashSet=new HashSet<String>();
//        hashSet.add("aaaa");
//        hashSet.add("bbbb");
//        hashSet.add("cccc");
//     return hashSet;
// }
//}
