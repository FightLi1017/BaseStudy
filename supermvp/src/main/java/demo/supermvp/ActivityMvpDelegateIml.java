package demo.supermvp;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.util.Log;

import java.util.UUID;

import demo.supermvp.presentermanager.PresenterManager;

/**
 * Created by lichenxi on 2017/9/11.
 */

public class ActivityMvpDelegateIml<V extends MvpView,P extends MvpPresenter<V>> implements ActivityMvpDelegate {
    protected static final String KEY_MVP_VIEW_ID = "supermvp.activity.mvp.id";
    private Activity activity;
    private MvpDelegateCallback<V,P> mDelegateCallback;
    protected String mosbyViewId = null;
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
        P presenter=null;
        if (savedInstanceState != null) {
            mosbyViewId = savedInstanceState.getString(KEY_MVP_VIEW_ID);
            //获取P的实例
            if (mosbyViewId != null) {
                presenter = PresenterManager.getPresenter(activity, mosbyViewId);
            }
        } else {
            presenter = createViewIdAndCreatePresenter();
        }

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

    private P createViewIdAndCreatePresenter() {
        P presenter = mDelegateCallback.createPresenter();
        if (presenter == null) {
            throw new NullPointerException(
                    "Presenter returned from createPresenter() is null. Activity is " + activity);
        }
        mosbyViewId= UUID.randomUUID().toString();
        PresenterManager.putPresenter(activity,mosbyViewId,presenter);
        return presenter;
    }

    @Override
    public void onDestory() {
        //判断是否需要保持P的实例 在非正常情况下的onDestory
        boolean retainPresenterInstance=retainPresenterInstance(activity);
        getPresenter().detachView();
        if (!retainPresenterInstance&&mosbyViewId!=null){
             PresenterManager.remove(activity,mosbyViewId);
        }
    }





    /**
     * Determines whether or not a Presenter Instance should be kept
     *
     */
    static boolean retainPresenterInstance(Activity activity) {
        return  (activity.isChangingConfigurations() || !activity.isFinishing());
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
        if (outState != null) {
            outState.putString(KEY_MVP_VIEW_ID, mosbyViewId);
        }
    }
}
