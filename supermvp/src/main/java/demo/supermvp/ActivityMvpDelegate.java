package demo.supermvp;

import android.app.Activity;
import android.os.Bundle;

/**
 * Created by lichenxi on 2017/9/11.
 */

public interface ActivityMvpDelegate<V extends MvpView,P extends MvpPresenter<V>> {

    /**
     * this method must  be called from{@link Activity#onCreate(Bundle)}
     * because this method need create Presenter and attach view to it;
     */
    void onCreate(Bundle savedInstanceState);
    /**
     * this method must  be called from{@link Activity#onDestroy()}
     * this method internally detach the View from Presenter
     */
    void onDestory();

    void onPause();

    void onResume();

    void onStart();

    void onStop();

    void onRestart();

    void onSaveInstanceState(Bundle outState);


}
