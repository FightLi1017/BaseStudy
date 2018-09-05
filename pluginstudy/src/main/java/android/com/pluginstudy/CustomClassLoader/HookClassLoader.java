package android.com.pluginstudy.CustomClassLoader;

import android.content.Context;

import java.io.File;

import dalvik.system.DexClassLoader;

/**
 * @author：lichenxi
 * @date 2018/9/3 18
 * email：525603977@qq.com
 * Fighting
 */
public class HookClassLoader {
    public static final String OPTIMIZE_DIR = "dex";
    //需要研究下ClassLoader加载机制了 给一个路径 可以直接加载ClassLoader 感觉现在这个思路 可以直接写一个热修复的东西 哈哈 插件化和gradle都可以学习了 哈哈 期待
    public static void hookClassLoader(Context context, File apk,ClassLoader parent){
        File dexOutputDir = context.getDir(OPTIMIZE_DIR, Context.MODE_PRIVATE);
        String dexOutputPath = dexOutputDir.getAbsolutePath();

        DexClassLoader loader = new DexClassLoader(apk.getAbsolutePath(), dexOutputPath,null, parent);
        try {
            DexUtil.insertDex(loader);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
