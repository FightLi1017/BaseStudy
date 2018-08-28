package android.com.navi_kotlin.rx

import android.com.navi_kotlin.Event
import android.com.navi_kotlin.Listener
import android.com.navi_kotlin.NaviComponent
import io.reactivex.ObservableEmitter
import io.reactivex.ObservableOnSubscribe
import io.reactivex.disposables.Disposable
import java.util.concurrent.atomic.AtomicBoolean

/**
 * @author：lichenxi
 * @date 2018/7/11 14
 * email：525603977@qq.com
 * Fighting
 */
class NaviOnSubscribe<T>(val component: NaviComponent, val event: Event<T>) : ObservableOnSubscribe<T> {

    override fun subscribe(emitter: ObservableEmitter<T>) {
       val listener=EmitterListener(emitter)
       emitter.setDisposable(listener)
       component.addListener(event,listener)
    }

  inner class EmitterListener(private val emitter: ObservableEmitter<T>) :Listener<T>,Disposable,AtomicBoolean(){

       override fun call(t:T) {
           emitter.onNext(t)
       }

       override fun isDisposed(): Boolean {
           return get()
       }

       override fun dispose() {
          if (compareAndSet(false,true)){
          }
       }
   }
}