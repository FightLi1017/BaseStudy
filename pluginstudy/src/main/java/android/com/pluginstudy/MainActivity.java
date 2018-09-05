package android.com.pluginstudy;

import android.app.Activity;
import android.com.pluginstudy.CustomClassLoader.AssetUtil;
import android.com.pluginstudy.CustomClassLoader.HookClassLoader;
import android.com.pluginstudy.HookActivity.HookHelper;
import android.com.pluginstudy.HookActivity.Main2Activity;
import android.com.pluginstudy.HookAmsPms.HookAmsPMS;
import android.com.pluginstudy.intercept_activity.TargetActivity;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.orhanobut.logger.Logger;

import java.io.File;
import java.io.IOException;


public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


//        findViewById(R.id.loadplugin).setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//
//                String[] files = null;
//
//                try {// 遍历assest文件夹，读取压缩包及安装包
//                    files = getAssets().list("");
//                } catch (IOException e) {
//                    e.printStackTrace();
//                }
//                for (String file:files){
//                    if (file.contains(".apk")){
//                        //加载到系统ClassLoader里面去
//                       File  apkFile=new File(AssetUtil.getAssetsCacheFile(getBaseContext(),file));
//                        if (apkFile.exists()){
//                            HookClassLoader.hookClassLoader(getBaseContext(),apkFile,getClassLoader());
//                        }
//                    }
//                }
//            }
//        });
        findViewById(R.id.openplugin).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                Intent intent = new Intent(Intent.ACTION_VIEW);
//                intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
//                intent.setData(Uri.parse("http://www.baidu.com"));
                Intent intent=new Intent();
                intent.setComponent(new ComponentName(getBaseContext(),"com.mainmodule.MainActivity"));
                // 注意这里使用的ApplicationContext 启动的Activity
                // 因为Activity对象的startActivity使用的并不是ContextImpl的mInstrumentation
                // 而是自己的mInstrumentation, 如果你需要这样, 可以自己Hook
                // 比较简单, 直接替换这个Activity的此字段即可.
                startActivity(intent);
            }
        });
    }

}
