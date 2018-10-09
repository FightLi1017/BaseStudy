package com.mainmodule.multiProcessSharedPreferences;

import android.content.Context;
import android.content.SharedPreferences;
import android.support.annotation.NonNull;

import static android.content.Context.MODE_MULTI_PROCESS;

/**
 * 共享参数类
 * Created by LCX on 2016/9/6.
 */

public class SharedPreferenceUtil {

    private static SharedPreferenceUtil instance;
    private SharedPreferences SP;

    private SharedPreferenceUtil(Context context) {
        SP = context.getSharedPreferences("TextMult",MODE_MULTI_PROCESS);
    }

    public static SharedPreferenceUtil getInstance(Context context) {
        if (instance == null) {
            synchronized (SharedPreferenceUtil.class) {
                if (instance == null) {
                    instance = new SharedPreferenceUtil(context);
                }
            }
        }
        return instance;
    }

    public SharedPreferences.Editor getEditor() {
        return SP.edit();
    }

    public <T> boolean saveValue(String keyword, T value) {
        SharedPreferences.Editor editor = getEditor();
        if (value instanceof String) {
            return editor.putString(keyword, (String) value).commit();
        }
        if (value instanceof Integer) {
            return editor.putInt(keyword, (Integer) value).commit();
        }
        if (value instanceof Boolean) {
            return editor.putBoolean(keyword, (Boolean) value).commit();
        }
        if (value instanceof Long) {
            return editor.putLong(keyword, (Long) value).commit();
        }
        if (value instanceof Float) {
            return editor.putFloat(keyword, (Float) value).commit();
        }
        return false;
    }

    public <T> T getValue(String keyword, @NonNull  T t) {
        if (t instanceof String) {
            String str = SP.getString(keyword, (String) t);
            t = (T)  str;
        } else if (t instanceof Integer) {
            Integer in = SP.getInt(keyword, (Integer) t);
            t = (T) in;
        } else if (t instanceof Long) {
            Long lon = SP.getLong(keyword, (Long) t);
            t = (T) lon;
        } else if (t instanceof Float) {
            Float fl = SP.getFloat(keyword, (Float) t);
            t = (T) fl;
        } else if (t instanceof Boolean) {
            Boolean bl = SP.getBoolean(keyword, (Boolean) t);
            t = (T) bl;
        }
        return t;
    }



}
