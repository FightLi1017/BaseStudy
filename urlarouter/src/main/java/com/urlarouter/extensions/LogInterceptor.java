package com.urlarouter.extensions;

import android.support.annotation.NonNull;
import android.util.Log;

import com.urlarouter.Chain;
import com.urlarouter.Interceptor;

/**
 * @author：lichenxi
 * @date 2018/12/10 19
 * email：525603977@qq.com
 * Fighting
 */
public class LogInterceptor implements Interceptor {
    private @NonNull final String tag;

    public LogInterceptor(@NonNull String tag) {
        this.tag = tag;
    }

    @NonNull
    @Override
    public Chain intercept(@NonNull Chain chain) {
        Log.d("SimpleArouter","LogInterceptor"+tag+":  "+chain.request());
        return chain;
    }
}
