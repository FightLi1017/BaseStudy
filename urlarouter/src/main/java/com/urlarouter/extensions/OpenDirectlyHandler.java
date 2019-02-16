package com.urlarouter.extensions;

import android.content.ActivityNotFoundException;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;

import com.urlarouter.TargetNotFoundHandler;

/**
 * @author：lichenxi
 * @date 2018/12/10 20
 * email：525603977@qq.com
 * Fighting
 */
public class OpenDirectlyHandler implements TargetNotFoundHandler {

    @Override
    public boolean onTargetNotFound(@NonNull Context context, @NonNull Uri uri, @NonNull Bundle bundle, @NonNull Integer intentFlags) {
        Intent intent=new Intent(Intent.ACTION_VIEW,uri);
        intent.putExtras(bundle);
        if (intentFlags!=null){
            intent.setFlags(intentFlags);
        }
        try {
            context.startActivity(intent);
        }catch (ActivityNotFoundException exception){
            exception.printStackTrace();
            return false;
        }
        return true;
    }
}
