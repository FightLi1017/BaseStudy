package com.urlarouter;

import android.net.Uri;
import android.support.annotation.NonNull;

/**
 * @author：lichenxi
 * @date 2018/12/11 19
 * email：525603977@qq.com
 * Fighting
 */
public class Urls {
    @NonNull
    public  static String indexUrl(@NonNull final String uriString){
        return indexUrl(Uri.parse(uriString));
    }

    @NonNull
    public static  String indexUrl(@NonNull final Uri uri){
        return uri.getScheme()!=null && uri.getAuthority()!=null ?
               uri.getAuthority()+uri.getPath():
               uri.getPath();
    }
    public static boolean isWebScheme(@NonNull Uri sourceUri){
        return sourceUri.getScheme()!=null &&
                (sourceUri.getScheme().toLowerCase().equals("http"))||
                (sourceUri.getScheme().toLowerCase().equals("https"));
    }

}
