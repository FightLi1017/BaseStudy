package com.urlarouter;

import android.content.Intent;
import android.os.Bundle;
import android.os.Parcelable;
import android.support.annotation.CheckResult;
import android.support.annotation.NonNull;

import java.io.Serializable;

/**
 * @author：lichenxi
 * @date 2018/12/10 20
 * email：525603977@qq.com
 * Fighting
 */
public interface Navigation {

    @NonNull @CheckResult Navigation setFlags(int intentFlags);
    @NonNull @CheckResult Intent getIntent();
    @NonNull @CheckResult Navigation appendQueryParams(String key,String value);
    @NonNull @CheckResult Navigation putExtras(@NonNull Bundle bundle);
    @NonNull @CheckResult Navigation putExtras(@NonNull Intent intent);

    @NonNull @CheckResult Navigation putExtra(@NonNull String key,int value);
    @NonNull @CheckResult Navigation putExtra(@NonNull String key,double value);
    @NonNull @CheckResult Navigation putExtra(@NonNull String key,long value);
    @NonNull @CheckResult Navigation putExtra(@NonNull String key,float value);
    @NonNull @CheckResult Navigation putExtra(@NonNull String key,byte value);
    @NonNull @CheckResult Navigation putExtra(@NonNull String key,short value);
    @NonNull @CheckResult Navigation putExtra(@NonNull String key,String value);

    @NonNull @CheckResult Navigation putExtra(@NonNull String key,Serializable value);
    @NonNull @CheckResult Navigation putExtra(@NonNull String key,Parcelable value);
    @NonNull @CheckResult Navigation putExtra(@NonNull String key,CharSequence value);


    void start();
 }
