package com.com.daggerdemo;

import com.squareup.otto.Bus;

/**
 * @author：lichenxi
 * @date 2018/3/27 20
 * email：525603977@qq.com
 * Fighting
 */
public final class BusProvider {
    private static final Bus BUS = new Bus();

    public static Bus getInstance() {
        return BUS;
    }

    private BusProvider() {
        // No instances.
    }
}
