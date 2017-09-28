package demo.supermvp;

import android.support.annotation.UiThread;

/**
 * Created by lichenxi on 2017/9/11.
 */

public interface MvpPresenter<V extends MvpView> {

    @UiThread
    void attachView(V view);

    @UiThread
    void detachView();
}
