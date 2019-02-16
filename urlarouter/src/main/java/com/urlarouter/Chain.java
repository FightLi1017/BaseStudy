package com.urlarouter;

import android.net.Uri;
import android.support.annotation.NonNull;

/**
 * @author：lichenxi
 * @date 2018/12/10 19
 * email：525603977@qq.com
 * Fighting
 */
public class Chain {
    private @NonNull Uri requestUri;
    private boolean process=true;

    public Chain(@NonNull Uri requestUri) {
        this.requestUri = requestUri;
    }

    public Chain(@NonNull Uri requestUri, boolean process) {
        this.requestUri = requestUri;
        this.process = process;
    }

    @NonNull
    public Uri request() {
        return requestUri;
    }

    @NonNull
    public Chain abort(){
        return new Chain(requestUri,false);
    }
    public boolean isProcess() {
        return process;
    }
}
