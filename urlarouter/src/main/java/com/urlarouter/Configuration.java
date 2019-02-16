package com.urlarouter;

import android.support.annotation.NonNull;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static com.urlarouter.Preconditions.checkNotNull;

/**
 * @author：lichenxi
 * @date 2018/12/10 15
 * email：525603977@qq.com
 * Fighting
 */
public class Configuration {
    private @NonNull final TargetMap targetMap;
    private @NonNull final List<Interceptor> requestInterceptors;
    private @NonNull final List<Interceptor> targetInterceptors;
    private @NonNull final List<TargetNotFoundHandler> targetNotFoundHandlers;
    private @NonNull IntentHandler intentHandler=new DefaultintentHandler();
    private boolean debug=false;

     Configuration() {
         targetMap=new TargetMap(Collections.<String, Target>emptyMap());
         requestInterceptors=new ArrayList<>();
         targetInterceptors=new ArrayList<>();
         targetNotFoundHandlers=new ArrayList<>();
    }

    void apply(@NonNull final Map<String,Target> map){
        checkNotNull(map);
        targetMap.set(new HashMap(map));
    }

    @NonNull
    public Configuration addRequestInterceptor(@NonNull Interceptor interceptor){
         checkNotNull(interceptor);
         requestInterceptors.add(interceptor);
         return this;
    }

    @NonNull
    public Configuration addTargetInterceptor(@NonNull Interceptor interceptor){
        checkNotNull(interceptor);
        targetInterceptors.add(interceptor);
        return this;
    }

    @NonNull
    public Configuration addTargetNotFoundHandler(@NonNull TargetNotFoundHandler handler){
        checkNotNull(handler);
        targetNotFoundHandlers.add(handler);
        return this;
    }

    public boolean isDebugEnabled() {
        return debug;
    }

    public Configuration setDebugEnabled(boolean debug) {
        this.debug = debug;
        return this;
    }

    @NonNull
    public List<Interceptor> getRequestInterceptors() {
        return requestInterceptors;
    }

    @NonNull
    public List<Interceptor> getTargetInterceptors() {
        return targetInterceptors;
    }

    @NonNull
    public List<TargetNotFoundHandler> getTargetNotFoundHandlers() {
        return targetNotFoundHandlers;
    }

    @NonNull
    public Target getTarget(String url){
         checkNotNull(url);
         return targetMap.getTarget(url);
    }

    @NonNull
    public Map<? extends String,? extends Target> getTargetMap() {
        return targetMap.get();
    }

    @NonNull
    public IntentHandler getIntentHandler() {
        return intentHandler;
    }
}
