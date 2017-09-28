package demo.supermvp;

import android.support.annotation.NonNull;

/**
 * Created by lichenxi on 2017/9/11.
 */

public interface MvpDelegateCallback<V extends MvpView,P extends MvpPresenter<V>> {

    @NonNull P createPresenter();

    P getPresenter();

    void setPresenter(P presenter);

    V getMvpView();

}
