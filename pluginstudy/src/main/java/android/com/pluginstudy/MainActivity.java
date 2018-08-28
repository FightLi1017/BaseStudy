package android.com.pluginstudy;

import android.app.Activity;
import android.com.pluginstudy.HookActivity.HookHelper;
import android.com.pluginstudy.HookActivity.Main2Activity;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);



        Button tv = new Button(this);
        tv.setText("测试界面");

        setContentView(tv);

        tv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                Intent intent = new Intent(Intent.ACTION_VIEW);
//                intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
//                intent.setData(Uri.parse("http://www.baidu.com"));
                  Intent intent=new Intent(MainActivity.this, Main2Activity.class);
                // 注意这里使用的ApplicationContext 启动的Activity
                // 因为Activity对象的startActivity使用的并不是ContextImpl的mInstrumentation
                // 而是自己的mInstrumentation, 如果你需要这样, 可以自己Hook
                // 比较简单, 直接替换这个Activity的此字段即可.
                 startActivity(intent);

            }
        });
    }


//    @Override
//    protected void attachBaseContext(Context newBase) {
//        super.attachBaseContext(newBase);
//        try {
//            HookHelper.hookActivity(this);
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//    }
}
