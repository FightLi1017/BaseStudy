package com.mainmodule.testmvp;

import demo.supermvp.MvpView;

/**
 * @author：lichenxi
 * @date 2018/9/20 19
 * email：525603977@qq.com
 * Fighting
 */
public interface TestView extends MvpView{
      void showError();

      void showLoading();

      void loginSuccessful();
}
