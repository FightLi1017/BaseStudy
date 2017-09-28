package demo.supermvp;

import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;

/**
 * Created by lichenxi on 2017/9/11.
 */

public abstract class MvpFragment<V extends MvpView ,P extends MvpPresenter<V>> extends Fragment
 implements MvpDelegateCallback<V,P> ,MvpView {

    private FragmentMvpDelegate mFragmentMvpDelegate;

    private P presenter;

   private FragmentMvpDelegate getFragmentMvpDelegate(){
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
}
