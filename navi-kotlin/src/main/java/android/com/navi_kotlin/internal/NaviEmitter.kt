package android.com.navi_kotlin.internal

import android.com.navi_kotlin.Event
import android.com.navi_kotlin.Listener
import android.com.navi_kotlin.NaviComponent
import android.com.navi_kotlin.internal.Constants.Companion.SIGNAL
import java.util.*
import java.util.concurrent.ConcurrentHashMap
import java.util.concurrent.CopyOnWriteArrayList
import kotlin.collections.HashSet

/**
 * @author：lichenxi
 * @date 2018/7/11 15
 * email：525603977@qq.com
 * Fighting

 * interface Source<out T,in R>{
    fun 生产():T // 只能返回T,不能将T作为参数传入

    fun 消费(r:R) // 只能将R作为参数传入，不可返回R
   }
 */
class NaviEmitter(private var handledEvents:Collection<Event<*>>):NaviComponent  {

    private var listenerMap: MutableMap<Event<*>, MutableList<Listener<*> >>

    // Only used for fast removal of listeners
    private var eventMap: MutableMap<Listener<*>, Event<*>>

    init {
        handledEvents = Collections.unmodifiableSet(HashSet(handledEvents))
        listenerMap = ConcurrentHashMap()
        eventMap = ConcurrentHashMap()
    }

    companion object {
        fun createActivityEmitter(): NaviEmitter {
            return NaviEmitter(HandledEvents.ACTIVITY_EVENTS)
        }

        fun createFragmentEmitter(): NaviEmitter {
            return NaviEmitter(HandledEvents.FRAGMENT_EVENTS)
        }
    }


    override fun handlesEvents(vararg events: Event<*>): Boolean {
        for (event in events) {
            if (event != Event.ALL && !handledEvents.contains(event)) {
                return false
            }
        }
        return true
    }

    override fun <T> addListener(event: Event<T>, listener: Listener<T>) {
        if (!handlesEvents(event)) {
            throw IllegalArgumentException("This component cannot handle event " + event)
        }
        if (eventMap.containsKey(listener)) {
            val otherEvent = eventMap[listener]
            if (!event.equals(otherEvent)) {
                throw IllegalStateException(
                        "Cannot use the same listener for two events! e1: $event e2: $otherEvent")
            }
            return
        }
        eventMap.put(listener, event)

        if (!listenerMap.containsKey(event)) {
            listenerMap.put(event, CopyOnWriteArrayList())
        }

        val listeners = listenerMap[event]
        listeners?.add(listener)
    }

    override fun <T> removeListener(listener: Listener<T>) {
        val event = eventMap.get(listener)
        if (listenerMap.containsKey(event!!)) {
            listenerMap.remove(event)
        }
    }

    private fun emitEvent(event: Event<Any>) {
         emitEvent(event, SIGNAL)
    }

    private fun  <T> emitEvent(event: Event<T> ,  data: T ) {
        val listeners = listenerMap.get(event)
        if (listeners != null) {
          for (listener in listeners){
//                listener.call(data)
          }
        }

    }



////////////////////////////////////////////////////////////////////////////
    // Events

}