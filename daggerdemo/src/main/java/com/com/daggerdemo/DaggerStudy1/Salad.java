package com.com.daggerdemo.DaggerStudy1;

import android.util.Log;

import javax.inject.Inject;

/**
 * @author：lichenxi
 * @date 2018/3/11 23
 * email：525603977@qq.com
 * Fighting
 */
public class Salad {
    @Inject
    Pear pear;
    @Inject
    Pear pear1;
    @Inject
    Banana banana;
    @Inject
    SaladSauce saladSauce;
    @Inject
    Orange orange;
    @Inject
    Knife knife;

    @Inject
    @Type("normal")
    Apple normalApple;

    @Inject
    @Type("color")
    Apple colorApple;

    public Salad() {
        DaggerSaladComponent.builder().build().inject(this);
        makeSalad(pear, banana, saladSauce,orange);
    }

    private void makeSalad(Pear pear, Banana banana,
                           SaladSauce saladSauce, Orange orange) {
        Log.e("TAG", "我在搅拌制作水果沙拉"+orange.toString());
    }
}
