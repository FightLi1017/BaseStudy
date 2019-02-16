package com.RxActivityResult;

import android.app.Activity;
import android.app.Fragment;
import android.app.FragmentManager;
import android.content.Intent;

import io.reactivex.Observable;

/**
 * @author：lichenxi
 * @date 2019/2/12 14
 * email：525603977@qq.com
 * Fighting
 */
public class RxActivityResult {
    private static final String TAG = "RxActivityResult";
    private OnResultFragment onResultFragment;

    public static RxActivityResult with(Activity activity){
        return new RxActivityResult(activity);
    }

    public static RxActivityResult with(Fragment fragment){
        return new RxActivityResult(fragment);
    }

    private RxActivityResult(Activity activity) {
        onResultFragment=getOnResultFragment(activity.getFragmentManager());
    }

    private RxActivityResult(Fragment fragment) {
        onResultFragment=getOnResultFragment(fragment.getChildFragmentManager());
    }


    private OnResultFragment getOnResultFragment(FragmentManager fragmentManager){
        OnResultFragment onResultFragment=findTagFragment(fragmentManager);
        if (onResultFragment==null){
            onResultFragment=new OnResultFragment();
            fragmentManager.beginTransaction()
                    .add(onResultFragment,TAG)
                    .commitAllowingStateLoss();
            //马上进行事务的提交哦
            fragmentManager.executePendingTransactions();
        }
        return onResultFragment;
    }


    private OnResultFragment findTagFragment(FragmentManager fragmentManager) {
        return (OnResultFragment) fragmentManager.findFragmentByTag(TAG);
    }


    public Observable<ActivityResultInfo> startForResult(Intent intent){
      return  onResultFragment.startForResult(intent);
    }

    public Observable<ActivityResultInfo> startForResult(Class clazz){
      Intent intent=new Intent(onResultFragment.getActivity(),clazz);
      return startForResult(intent);
    }

    public void startForResult(Intent intent,Callback callback){
          onResultFragment.startForResult(intent,callback);
    }

    public void startForResult(Class clazz,Callback callback){
        Intent intent=new Intent(onResultFragment.getActivity(),clazz);
        startForResult(intent,callback);
    }
    public interface Callback {
        void onActivityResult(int requestCode, int resultCode, Intent data);
    }
}
