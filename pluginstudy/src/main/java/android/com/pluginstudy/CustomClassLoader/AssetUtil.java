package android.com.pluginstudy.CustomClassLoader;

import android.content.Context;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;

/**
 * @author：lichenxi
 * @date 2018/9/3 19
 * email：525603977@qq.com
 * Fighting
 */
public class AssetUtil {

    public static String getAssetsCacheFile(Context context, String fileName)   {
        File cacheFile = new File(context.getExternalCacheDir(), fileName);
        try {
//            if (cacheFile.exists()){
//                return cacheFile.getAbsolutePath();
//            }else {
//                cacheFile.createNewFile();
//            }
            InputStream inputStream = context.getAssets().open(fileName);
            try {
                FileOutputStream outputStream = new FileOutputStream(cacheFile);
                try {
                    byte[] buf = new byte[1024];
                    int len;
                    while ((len = inputStream.read(buf)) > 0) {
                        outputStream.write(buf, 0, len);
                    }
                } finally {
                    outputStream.close();
                }
            } finally {
                inputStream.close();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return cacheFile.getAbsolutePath();
    }
}
