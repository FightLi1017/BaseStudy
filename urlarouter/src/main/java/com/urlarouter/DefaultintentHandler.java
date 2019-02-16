package com.urlarouter;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;

/**
 * @author：lichenxi
 * @date 2018/12/11 17
 * email：525603977@qq.com
 * Fighting
 */
public class DefaultintentHandler implements IntentHandler {
    @Override
    public void onIntentCreated(@NonNull Context context, @NonNull Intent intent) {
        context.startActivity(intent);
    }
}
