package com.urlarouter;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;

/**
 * @author：lichenxi
 * @date 2018/12/10 19
 * email：525603977@qq.com
 * Fighting
 */
public interface TargetNotFoundHandler {
    /**
     * 当目标没有找到时 允许我们提前进行处理 不至于系统崩溃
     * @param context
     * @param uri
     * @param bundle
     * @return
     */
    boolean onTargetNotFound(@NonNull Context context, @NonNull Uri uri, @NonNull Bundle bundle,
                             @NonNull Integer intentFlags);
}
