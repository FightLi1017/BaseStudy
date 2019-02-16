package com.urlarouter;

import android.support.annotation.NonNull;

import java.util.Map;

/**
 * @author：lichenxi
 * @date 2018/12/10 19
 * email：525603977@qq.com
 * Fighting
 */
public class TargetMap {
    private @NonNull  Map<String,Target>  map;

    public TargetMap(@NonNull Map<String, Target> map) {
        this.map = map;
    }

    @NonNull
    public Map<String, Target> get() {
        return map;
    }

    public void set(@NonNull Map<String, Target> map) {
        this.map = map;
    }

    @NonNull
    Target getTarget(@NonNull String url){
        return  map.get(url);
    }
}
