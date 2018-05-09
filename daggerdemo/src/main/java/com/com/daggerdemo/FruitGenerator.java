package com.com.daggerdemo;

import java.util.Random;

/**
 * @author：lichenxi
 * @date 2018/3/16 17
 * email：525603977@qq.com
 * Fighting
 */
public class FruitGenerator implements Generator<String> {

    private String[] fruits = new String[]{"Apple", "Banana", "Pear"};

    @Override
    public String next() {
        return new String();
    }
}
