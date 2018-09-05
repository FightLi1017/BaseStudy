package android;

import android.com.pluginstudy.CustomClassLoader.AssetUtil;
import android.com.pluginstudy.CustomClassLoader.HookClassLoader;
import android.com.pluginstudy.CustomClassLoader.ReflectUtil;
import android.content.Context;
import android.content.Intent;
import android.content.res.AssetManager;
import android.content.res.Resources;
import android.nfc.Tag;
import android.util.Log;

import java.io.File;
import java.io.IOException;
import java.lang.reflect.Array;
import java.util.Arrays;

/**
 * @author：lichenxi
 * @date 2018/9/3 23
 * email：525603977@qq.com
 * Fighting
 */
public class PluginManager {
    public static final String KEY_IS_PLUGIN = "isPlugin";
    public static final String KEY_TARGET_PACKAGE = "target.package";
    public static final String KEY_TARGET_ACTIVITY = "target.activity";

    public static String tag="PluginManager";
   static Resources pluginResources;

    public static Resources getPluginResources() {
        return pluginResources;
    }

    public  static  void init(Context context) throws IOException{
         String[] files = null;

         try {// 遍历assest文件夹，读取压缩包及安装包
             files = context.getAssets().list("");
         } catch (IOException e) {
             e.printStackTrace();
         }
         for (String file:files){
             if (file.contains(".apk")){
                 //加载到系统ClassLoader里面去
                 File apkFile=new File(AssetUtil.getAssetsCacheFile(context,file));
                 if (apkFile.exists()){
                     HookClassLoader.hookClassLoader(context,apkFile,context.getClassLoader());
                     //这里先尝试一下用addAssetPath试一下 看行不行 哈哈
                     Resources hostResources = context.getResources();
                     AssetManager assetManager=createAssetManager(context,apkFile);

                     pluginResources= new Resources(assetManager, hostResources.getDisplayMetrics(), hostResources.getConfiguration());
                     Log.d(tag,  Arrays.toString(pluginResources.getAssets().list("")));
                 }
             }
         }
     }

    private static AssetManager createAssetManager(Context context, File apk) {
        try {
            AssetManager am = AssetManager.class.newInstance();
            ReflectUtil.invoke(AssetManager.class, am, "addAssetPath", apk.getAbsolutePath());
//            ReflectUtil.invoke(AssetManager.class,am,"ensureStringBlocks");
            return am;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public static boolean isIntentFromPlugin(Intent intent) {
        return intent.getBooleanExtra(KEY_IS_PLUGIN, false);
    }
}
