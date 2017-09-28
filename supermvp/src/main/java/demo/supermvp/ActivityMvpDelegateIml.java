package demo.supermvp;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.NonNull;

/**
 * Created by lichenxi on 2017/9/11.
 */

public class ActivityMvpDelegateIml<V extends MvpView,P extends MvpPresenter<V>> implements ActivityMvpDelegate {
    private Activity activity;
    private MvpDelegateCallback<V,P> mDelegateCallback;

    public ActivityMvpDelegateIml(@NonNull Activity activity,
                                  @NonNull MvpDelegateCallback mvpDelegateCallback) {
        if (activity == null) {
            throw new NullPointerException("Activity is null!");
        }

        if (mvpDelegateCallback == null) {
            throw new NullPointerException("MvpDelegateCallback is null!");
        }
       this.activity=activity;
       this.mDelegateCallback=mvpDelegateCallback;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        P presenter=createPresenter();
        mDelegateCallback.setPresenter(presenter);
        presenter.attachView(getMvpView());
    }

    private V getMvpView() {
      V view=mDelegateCallback.getMvpView();
     if (view==null){
         throw new NullPointerException("View returned from getMvpView() is null");
     }
       return  view;
    }

    private P createPresenter() {
        return  mDelegateCallback.createPresenter();
    }

    @Override
    public void onDestory() {
     getPresenter().detachView();
    }

    private P getPresenter() {
        P presenter = mDelegateCallback.getPresenter();
        if (presenter == null) {
            throw new NullPointerException("Presenter returned from getPresenter() is null");
        }
        return  presenter;
    }

    @Override
    public void onPause() {

    }

    @Override
    public void onResume() {

    }

    @Override
    public void onStart() {

    }

    @Override
    public void onStop() {

    }

    @Override
    public void onRestart() {

    }

    @Override
    public void onSaveInstanceState(Bundle outState) {

    }
}
