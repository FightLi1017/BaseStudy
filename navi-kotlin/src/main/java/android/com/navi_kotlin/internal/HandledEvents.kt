package android.com.navi_kotlin.internal

import android.com.navi_kotlin.Event
import java.util.*

/**
 * @author：lichenxi
 * @date 2018/7/11 15
 * email：525603977@qq.com
 * Fighting
 */
class HandledEvents {
    private constructor(){
        throw AssertionError("No instances!")
    }
     companion object {
         val ACTIVITY_EVENTS:List<Event<*>> =Arrays.asList(
                 Event.CREATE,
                 Event.CREATE_PERSISTABLE,
                 Event.START,
                 Event.POST_CREATE,
                 Event.POST_CREATE_PERSISTABLE,
                 Event.RESUME,
                 Event.PAUSE,
                 Event.STOP,
                 Event.DESTROY,
                 Event.RESTART,
                 Event.SAVE_INSTANCE_STATE,
                 Event.SAVE_INSTANCE_STATE_PERSISTABLE,
                 Event.RESTORE_INSTANCE_STATE,
                 Event.RESTORE_INSTANCE_STATE_PERSISTABLE,
                 Event.NEW_INTENT,
                 Event.BACK_PRESSED,
                 Event.ATTACHED_TO_WINDOW,
                 Event.DETACHED_FROM_WINDOW,
                 Event.CONFIGURATION_CHANGED
//                 Event.ACTIVITY_RESULT,
//                 Event.REQUEST_PERMISSIONS_RESULT
         )
         val FRAGMENT_EVENTS: List<Event<*>> = Arrays.asList(
                 Event.ATTACH,
                 Event.CREATE,
                 Event.CREATE_VIEW,
//                 Event.VIEW_CREATED,
                 Event.ACTIVITY_CREATED,
                 Event.VIEW_STATE_RESTORED,
                 Event.START,
                 Event.RESUME,
                 Event.PAUSE,
                 Event.STOP,
                 Event.DESTROY_VIEW,
                 Event.DESTROY,
                 Event.DETACH,
                 Event.SAVE_INSTANCE_STATE,
                 Event.CONFIGURATION_CHANGED
//                 Event.ACTIVITY_RESULT,
//                 Event.REQUEST_PERMISSIONS_RESULT
         )
     }
}