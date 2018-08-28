package android.com.navi_kotlin.rx

import android.com.navi_kotlin.Event
import android.com.navi_kotlin.NaviComponent
import io.reactivex.Observable

/**
 * @author：lichenxi
 * @date 2018/7/11 15
 * email：525603977@qq.com
 * Fighting
 */
class RxNavi {
    private constructor(){
        throw AssertionError("No instances!")
    }
    companion object {
        //该方法是整个工具的总入口 用来确定入口和对应的事件类型 通过Rxjava的特点 订阅的逻辑在NaviOnSubscribe里面
        fun <T> observe(component: NaviComponent,event: Event<T>):Observable<T>{
            if (component == null) throw IllegalArgumentException("component == null")
            if (event == null) throw IllegalArgumentException("event == null")
            return  Observable.create(NaviOnSubscribe(component, event))
        }
    }
}