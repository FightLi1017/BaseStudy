package android.com.navi_kotlin.component

import android.com.navi_kotlin.Event
import android.com.navi_kotlin.Listener
import android.com.navi_kotlin.NaviComponent
import android.com.navi_kotlin.internal.NaviEmitter
import android.support.v7.app.AppCompatActivity

/**
 * @author：lichenxi
 * @date 2018/7/11 19
 * email：525603977@qq.com
 * Fighting
 */
class NaviAppCompatActivity:AppCompatActivity(),NaviComponent {

    private val naviEmitter=NaviEmitter.createActivityEmitter()

    override fun handlesEvents(vararg events: Event<*>): Boolean {
        return  naviEmitter.handlesEvents(*events)
    }

    override fun <T> addListener(event: Event<T>, listener: Listener<T>) {
        naviEmitter.addListener(event,listener)
    }

    override fun <T> removeListener(listener: Listener<T>) {
        naviEmitter.removeListener(listener)
    }


}