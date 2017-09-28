package demo.supermvp;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.View;

/**
 * Created by lichenxi on 2017/9/11.
 */

public class FragmentMvpDelegateIml<V extends MvpView,P extends MvpPresenter<V>> implements FragmentMvpDelegate {
    private Fragment mFragment;
    private MvpDelegateCallback<V,P> mDelegateCallback;

    public FragmentMvpDelegateIml(@NonNull Fragment fragment,
                                  @NonNull MvpDelegateCallback<V, P> delegateCallback) {
        if (fragment == null) {
            throw new NullPointerException("Fragment is null!");
        }

        if (delegateCallback == null) {
            throw new NullPointerException("MvpDelegateCallback is null!");
        }
        this.mFragment=fragment;
        this.mDelegateCallback=delegateCallback;
    }

    @Override
    public void onCreate(Bundle saved) {

    }

    @Override
    public void onDestroy() {

    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        P presenter=createPresenter();
        mDelegateCallback.setPresenter(presenter);
        presenter.attachView(getMvpView());
    }

    @Override
    public void onDestroyView() {
        getPresenter().detachView();
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
    public void onActivityCreated(Bundle savedInstanceState) {

    }

    @Override
    public void onAttach(Activity activity) {

    }

    @Override
    public void onDetach() {

    }

    @Override
    public void onSaveInstanceState(Bundle outState) {

    }
    private V getMvpView() {
        V view = mDelegateCallback.getMvpView();
        if (view == null) {
            throw new NullPointerException("View returned from getMvpView() is null");
        }
        return view;
    }
    private P createPresenter() {
        return  mDelegateCallback.createPresenter();
    }
    private P getPresenter() {
        P presenter = mDelegateCallback.getPresenter();
        if (presenter == null) {
            throw new NullPointerException("Presenter returned from getPresenter() is null");
        }
        return  presenter;
    }
}
