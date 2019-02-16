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

    public Observable<Keyboard> observe(){
        if (activity == null) {
            Log.w(TAG, "Activity is null");
            Keyboard keyboard=new Keyboard();
            keyboard.status=KeyboardStatus.CLOSED;
            return Observable.just(keyboard);
        }
        final View rootView=activity.findViewById(android.R.id.content);
        //获取当前windows的高度
        DisplayMetrics dm = new DisplayMetrics();
        activity.getWindowManager().getDefaultDisplay().getMetrics(dm);
        final int screenHeight=dm.heightPixels;
        return Observable.create((ObservableOnSubscribe<Keyboard>) emitter -> {
            final ViewTreeObserver.OnGlobalLayoutListener layoutListener= () -> {
                if (rootView==null){
                    Log.w(TAG, "Root view is null");
                    Keyboard keyboard=new Keyboard();
                    keyboard.status=KeyboardStatus.CLOSED;
                    emitter.onNext(keyboard);
                    return;
                }
                //获取当前RootView当前显示的大小
                Rect rect=new Rect();
                rootView.getWindowVisibleDisplayFrame(rect);
                int keyboardHeight = screenHeight-rect.height();
                if (keyboardHeight>screenHeight*MIN_KEYBOARD_HEIGHT_RATIO){
                    Keyboard keyboard=new Keyboard();
                    keyboard.status=KeyboardStatus.OPEN;
                    keyboard.height=keyboardHeight;
                    emitter.onNext(keyboard);
                }else {
                    Keyboard keyboard=new Keyboard();
                    keyboard.status=KeyboardStatus.CLOSED;
                    emitter.onNext(keyboard);
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
