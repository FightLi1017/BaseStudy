package com.RxActivityResult;


import android.app.Fragment;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;

import java.security.SecureRandom;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;

import io.reactivex.Observable;
import io.reactivex.subjects.PublishSubject;

/**
 * @author：lichenxi
 * @date 2019/2/12 14
 * email：525603977@qq.com
 * Fighting
 */
public class OnResultFragment extends Fragment {
    //这里用静态的原因是 如果在低内存情况下 之前的activity会系统回收了 那么在执行setResult之后
    // 之前的activity会被从新创建一次 所以会有可能没有收到回调

    private static Map<Integer, PublishSubject<ActivityResultInfo>> mSubjects = new HashMap<>();
    private static Map<Integer, RxActivityResult.Callback> mCallbacks = new HashMap<>();

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setRetainInstance(true);
    }

    public Observable<ActivityResultInfo>  startForResult(final Intent intent){
       int requestCode=generateRequestCode();
       PublishSubject<ActivityResultInfo> subject=PublishSubject.create();
       mSubjects.put(requestCode,subject);
       startActivityForResult(intent,requestCode);
       return  subject;
    }

    public void startForResult(Intent intent, RxActivityResult.Callback callback) {
        int requestCode = generateRequestCode();
        mCallbacks.put(requestCode, callback);
        startActivityForResult(intent, requestCode);
    }


    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        PublishSubject<ActivityResultInfo> subject=mSubjects.remove(requestCode);
        //Rxjava
        if (subject!=null){
            subject.onNext(new ActivityResultInfo(requestCode,resultCode,data));
            subject.onComplete();
        }

       //callBack
        RxActivityResult.Callback callback=mCallbacks.remove(requestCode);
        if (callback!=null){
            callback.onActivityResult(requestCode,resultCode,data);
        }
    }

    private int generateRequestCode(){
        SecureRandom random = new SecureRandom();
        for (;;){
            int code = random.nextInt(65536);
            if (!mSubjects.containsKey(code) && !mCallbacks.containsKey(code)){
                return code;
            }
        }
    }

}
