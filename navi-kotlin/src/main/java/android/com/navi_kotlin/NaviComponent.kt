package android.com.navi_kotlin

/**
 * @author：lichenxi
 * @date 2018/7/11 14
 * email：525603977@qq.com
 * Fighting
 */
interface NaviComponent {
    /**
     * Determines whether this component can handle particular events.
     *
     * For example, Activities do not handle Event.CREATE_VIEW since they do not have that step.
     *
     * @param events the events to check
     * @return true if all events can be handled
     */
    fun handlesEvents(vararg events: Event<*>): Boolean

    /**
     * Adds a listener to this component.
     *
     * @param event an Event
     * @param listener the listener for that event
     * @param <T> the callback type for the event
     * @throws IllegalArgumentException if this component cannot handle the event
    </T> */
    fun <T> addListener(event: Event<T>, listener: Listener<T>)

    /**
     * Removes a listener from this component.
     *
     * @param <T> the callback type for the event
     * @param listener the listener for that event
    </T> */
    fun <T> removeListener(listener: Listener<T>)
}
