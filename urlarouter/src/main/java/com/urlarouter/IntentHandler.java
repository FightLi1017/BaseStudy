package com.urlarouter;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;

/**
 * @author：lichenxi
 * @date 2018/12/10 15
 * email：525603977@qq.com
 * Fighting
 */
public interface IntentHandler {
    /**
     * Called immediately after intent has created on start()
     * @param context
     * @param intent
     */
    void onIntentCreated(@NonNull Context context, @NonNull Intent intent);
}
