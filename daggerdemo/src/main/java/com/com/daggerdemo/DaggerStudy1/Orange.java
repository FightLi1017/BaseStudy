package com.com.daggerdemo.DaggerStudy1;

import android.util.Log;

/**
 * @author：lichenxi
 * @date 2018/3/13 23
 * email：525603977@qq.com
 * Fighting
 */
public class Orange {
    Knife knife;
    public Orange(Knife knife){
        this.knife=knife;
        knife.cut();
        Log.e("TAG", "我是一个桔子");
    }

    @Override
    public String toString() {
        return "Orange{}";
    }
}
