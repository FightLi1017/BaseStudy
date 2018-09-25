package com.mainmodule.testmvp;

import android.os.Handler;

import demo.supermvp.MvpBasePresenter;

/**
 * @author：lichenxi
 * @date 2018/9/20 19
 * email：525603977@qq.com
 * Fighting
 */
public class TestPresenter extends MvpBasePresenter<TestView> {

    public void login(String name,String password){
          if (isViewAttached()){
            getView().showLoading();
          }

         new Handler().postDelayed(new Runnable() {
             @Override
             public void run() {
                 if (isViewAttached()){
                     getView().loginSuccessful();
                 }
             }
         },2000);

    }
}
