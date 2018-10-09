package demo.supermvp;

import android.accessibilityservice.AccessibilityServiceInfo;
import android.support.annotation.UiThread;

import java.lang.ref.WeakReference;

/**
 * Created by lichenxi on 2017/9/11.
 */

public class MvpBasePresenter<V extends MvpView> implements MvpPresenter<V> {

    private WeakReference<V> viewRef;

    @Override
    public void attachView(V view) {
        viewRef=new WeakReference<V>(view);
    }

    @UiThread
    public V getView(){
        return viewRef == null ? null : viewRef.get();
    }

    @UiThread
   public boolean isViewAttached(){
       return viewRef != null && viewRef.get() != null;
   }

    @Override
    public void detachView() {
        if (viewRef != null) {
            viewRef.clear();
            viewRef = null;
        }
    }
}
