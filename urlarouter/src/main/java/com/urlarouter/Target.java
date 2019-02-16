package com.urlarouter;

import android.net.Uri;
import android.support.annotation.NonNull;

/**
 * @author：lichenxi
 * @date 2018/12/10 19
 * email：525603977@qq.com
 * Fighting
 */
public class Target {
    private @NonNull String url;

    public Target(@NonNull String url) {
        this.url = url;
    }

    @NonNull
    public String toTargetUrl(){
        return Uri.parse(url).buildUpon().build().toString();
    }

    @NonNull
    public String getTargetUrl(){
        return url;
    }
}
