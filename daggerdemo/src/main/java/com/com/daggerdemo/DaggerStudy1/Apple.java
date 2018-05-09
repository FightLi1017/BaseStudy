package com.com.daggerdemo.DaggerStudy1;

import android.util.Log;

/**
 * @author：lichenxi
 * @date 2018/3/19 17
 * email：525603977@qq.com
 * Fighting
 */
public class Apple {
    String color;

    public Apple() {
        Log.e("TAG", "我是一个普通的苹果");

    }

    public Apple(String color) {
        this.color = color;
        Log.e("TAG", "我是一个有颜色的苹果");

    }

}
