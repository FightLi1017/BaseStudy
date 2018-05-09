package router.android.lcx.basestudy;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;

import java.util.Set;

/**
 * 共享参数类
 * Created by LCX on 2016/9/6.
 */

public class SharedPreferenceUtil {

    private static SharedPreferenceUtil instance;
    private SharedPreferences SP;

    private SharedPreferenceUtil(Context context) {
        SP = PreferenceManager.getDefaultSharedPreferences(context);
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
        if (value instanceof Set) {
            return editor.putStringSet(keyword, (Set) value).commit();
        }
        return false;
    }

    public <T> T getValue(String keyword, T t) {
        if (t instanceof String) {
            String str = SP.getString(keyword, (String) t);
            t = (T) str;
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
        }else if (t instanceof Set) {
            Set stringSet = SP.getStringSet(keyword, (Set) t);
            t = (T) stringSet;
        }
        return t;
    }



}
