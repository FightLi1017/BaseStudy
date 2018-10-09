package com.keyboard_detector;

import android.app.Activity;
import android.graphics.Rect;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.View;
import android.view.ViewTreeObserver;

import io.reactivex.Observable;
import io.reactivex.ObservableEmitter;
import io.reactivex.ObservableOnSubscribe;
import io.reactivex.functions.Cancellable;

/**
 * @author：lichenxi
 * @date 2018/9/27 11
 * email：525603977@qq.com
 * Fighting
 */
public class KeyboardDetector {
    private Activity activity;

    public KeyboardDetector(Activity activity) {
        this.activity = activity;
    }

    private static final String  TAG = "KeyboardDetector";
    private static final double MIN_KEYBOARD_HEIGHT_RATIO = 0.15;

    public Observable<KeyboardStatus> observe(){
        if (activity == null) {
            Log.w(TAG, "Activity is null");
            return Observable.just(KeyboardStatus.CLOSED);
        }
        final View rootView=activity.findViewById(android.R.id.content);
        //获取当前windows的高度
        DisplayMetrics dm = new DisplayMetrics();
        activity.getWindowManager().getDefaultDisplay().getMetrics(dm);
        final int screenHeight=dm.heightPixels;
        return Observable.create((ObservableOnSubscribe<KeyboardStatus>) emitter -> {
            final ViewTreeObserver.OnGlobalLayoutListener layoutListener= () -> {
                if (rootView==null){
                    Log.w(TAG, "Root view is null");
                    emitter.onNext(KeyboardStatus.CLOSED);
                    return;
                }
                //获取当前RootView当前显示的大小
                Rect rect=new Rect();
                rootView.getWindowVisibleDisplayFrame(rect);
                int keyboardHeight = screenHeight-rect.height();
                if (keyboardHeight>screenHeight*MIN_KEYBOARD_HEIGHT_RATIO){
                    emitter.onNext(KeyboardStatus.OPEN);
                }else {
                    emitter.onNext(KeyboardStatus.CLOSED);
                }
            };

            rootView.getViewTreeObserver().addOnGlobalLayoutListener(layoutListener);

            emitter.setCancellable(new Cancellable() {
                @Override
                public void cancel() throws Exception {
                    rootView.getViewTreeObserver().removeOnGlobalLayoutListener(layoutListener);
                }
            });

        }).distinctUntilChanged();
    }
}
