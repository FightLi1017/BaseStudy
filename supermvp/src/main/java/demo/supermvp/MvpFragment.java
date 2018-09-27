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

public abstract class MvpFragment<V extends MvpView ,P extends MvpPresenter<V>> extends Fragment
 implements MvpDelegateCallback<V,P> ,MvpView {

    private FragmentMvpDelegate mFragmentMvpDelegate;

    private P presenter;



   private FragmentMvpDelegate getMvpDelegate(){
     if (mFragmentMvpDelegate==null){
         mFragmentMvpDelegate=new FragmentMvpDelegateIml(this,this);
     }
       return mFragmentMvpDelegate;
   }


    @NonNull
    @Override
    public abstract P createPresenter();

    @Override
    public P getPresenter() {
        return presenter;
    }

    @Override
    public void setPresenter(P presenter) {
    this.presenter=presenter;
    }

    @Override
    public V getMvpView() {
        return (V)this;
    }


    @Override public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        getMvpDelegate().onViewCreated(view, savedInstanceState);
    }

    @Override public void onDestroyView() {
        super.onDestroyView();
        getMvpDelegate().onDestroyView();
    }

    @Override public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getMvpDelegate().onCreate(savedInstanceState);
    }

    @Override public void onDestroy() {
        super.onDestroy();
        getMvpDelegate().onDestroy();
    }

    @Override public void onPause() {
        super.onPause();
        getMvpDelegate().onPause();
    }

    @Override public void onResume() {
        super.onResume();
        getMvpDelegate().onResume();
    }

    @Override public void onStart() {
        super.onStart();
        getMvpDelegate().onStart();
    }

    @Override public void onStop() {
        super.onStop();
        getMvpDelegate().onStop();
    }

    @Override public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        getMvpDelegate().onActivityCreated(savedInstanceState);
    }

    @Override public void onAttach(Activity activity) {
        super.onAttach(activity);
        getMvpDelegate().onAttach(activity);
    }

    @Override public void onDetach() {
        super.onDetach();
        getMvpDelegate().onDetach();
    }

    @Override public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        getMvpDelegate().onSaveInstanceState(outState);
    }

}
