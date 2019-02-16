package com.urlarouter;

import android.support.annotation.NonNull;

/**
 * @author：lichenxi
 * @date 2018/12/10 20
 * email：525603977@qq.com
 * Fighting
 */
public class Preconditions {
     @NonNull
     public static <T> T checkNotNull(@NonNull final T object){
         if (object==null){
             throw new   NullPointerException();
         }
         return object;
     }
}
