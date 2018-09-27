package demo.supermvp;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.View;

import java.util.UUID;

import demo.supermvp.presentermanager.PresenterManager;

/**
 * Created by lichenxi on 2017/9/11.
 * 默认fragment在配置发生改变的时候 保存P 在fragment还在返回栈里面 保存P
 */

public class FragmentMvpDelegateIml<V extends MvpView,P extends MvpPresenter<V>> implements FragmentMvpDelegate {
    protected static final String KEY_MVP_VIEW_ID = "supermvp.fragment.mvp.id";

    private Fragment fragment;
    private MvpDelegateCallback<V,P> delegateCallback;
    protected String mosbyViewId;
    public FragmentMvpDelegateIml(@NonNull Fragment fragment,
                                  @NonNull MvpDelegateCallback<V, P> delegateCallback) {
        if (fragment == null) {
            throw new NullPointerException("Fragment is null!");
        }

        if (delegateCallback == null) {
            throw new NullPointerException("MvpDelegateCallback is null!");
        }
        this.fragment=fragment;
        this.delegateCallback=delegateCallback;
    }

    @Override
    public void onCreate(Bundle bundle) {
        P presenter = null;
        if (bundle != null) {
            mosbyViewId = bundle.getString(KEY_MVP_VIEW_ID);
            if (mosbyViewId != null) {
                presenter = PresenterManager.getPresenter(getActivity(), mosbyViewId);
            }
        } else {
            presenter = createViewIdAndCreatePresenter();
        }

      delegateCallback.setPresenter(presenter);
    }

   private P createViewIdAndCreatePresenter(){
       P presenter = delegateCallback.createPresenter();
       if (presenter == null) {
           throw new NullPointerException(
                   "Presenter returned from createPresenter() is null. Activity is " + getActivity());
       }
       mosbyViewId = UUID.randomUUID().toString();
       PresenterManager.putPresenter(getActivity(),mosbyViewId,presenter);
       return  presenter;
   }

   @NonNull
   private Activity getActivity() {
        Activity activity = fragment.getActivity();
        if (activity == null) {
            throw new NullPointerException(
                    "Activity returned by Fragment.getActivity() is null. Fragment is " + fragment);
        }

        return activity;
    }

    @Override
    public void onDestroy() {
        Activity activity = getActivity();
        boolean isKeep= retainPresenterInstance(activity,fragment);
        if (isKeep&& mosbyViewId!=null){
            PresenterManager.remove(getActivity(),mosbyViewId);
        }
    }
    static boolean retainPresenterInstance(Activity activity, Fragment fragment) {

        if (activity.isChangingConfigurations()) {
            return  true;
        }

        if (activity.isFinishing()) {
            return false;
        }

        if (Util.isInBackStack(fragment)) {
            return true;
        }

        return !fragment.isRemoving();
    }
    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        P presenter=getPresenter();
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
        if (outState!=null){
            outState.putString(KEY_MVP_VIEW_ID,mosbyViewId);
        }

    }
    private V getMvpView() {
        V view = delegateCallback.getMvpView();
        if (view == null) {
            throw new NullPointerException("View returned from getMvpView() is null");
        }
        return view;
    }

    private P getPresenter() {
        P presenter = delegateCallback.getPresenter();
        if (presenter == null) {
            throw new NullPointerException("Presenter returned from getPresenter() is null");
        }
        return  presenter;
    }
}
