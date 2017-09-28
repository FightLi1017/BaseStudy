package demo.supermvp;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;

/**
 * Created by lichenxi on 2017/9/11.
 */

public abstract class MvpActivity<V extends MvpView ,P extends MvpPresenter<V>>
        extends AppCompatActivity implements MvpDelegateCallback<V,P>  {
    protected P presenter;
    private ActivityMvpDelegate mMvpDelegate;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getMvpDelegate().onCreate(savedInstanceState);
    }

    @Override
    protected void onResume() {
        super.onResume();
        getMvpDelegate().onResume();
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        getMvpDelegate().onSaveInstanceState(outState);
    }

    @Override
    protected void onPause() {
        super.onPause();
        getMvpDelegate().onPause();
    }

    @Override
    protected void onStart() {
        super.onStart();
        getMvpDelegate().onStart();
    }

    @Override
    protected void onStop() {
        super.onStop();
        getMvpDelegate().onStop();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        getMvpDelegate().onDestory();
    }

    @Override
    protected void onRestart() {
        super.onRestart();
        getMvpDelegate().onRestart();
    }

    @NonNull
    @Override
    public abstract P createPresenter() ;


    @Override
    public P getPresenter() {
        return presenter;
    }

    @Override
    public void setPresenter(@NonNull P presenter) {
       this.presenter=presenter;
    }

    @Override
    public V getMvpView() {
        return (V)this;
    }

    private ActivityMvpDelegate<V,P> getMvpDelegate(){
         if (mMvpDelegate==null){
             mMvpDelegate=new ActivityMvpDelegateIml(this,this);
         }
      return mMvpDelegate;
    }
}
