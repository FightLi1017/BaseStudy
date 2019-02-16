package com.urlarouter;

import android.support.annotation.NonNull;

/**
 * @author：lichenxi
 * @date 2018/12/10 19
 * email：525603977@qq.com
 * Fighting
 */
public interface Interceptor {
    @NonNull Chain intercept(@NonNull Chain chain);
}
