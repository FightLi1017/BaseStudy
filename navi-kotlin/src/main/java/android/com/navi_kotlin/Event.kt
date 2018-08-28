package android.com.navi_kotlin

import android.app.Activity
import android.app.Fragment
import android.content.Context
import android.content.Intent
import android.content.res.Configuration
import android.os.Bundle
import android.os.PersistableBundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

/**
 * @author：lichenxi
 * @date 2018/7/11 14
 * email：525603977@qq.com
 * Fighting
 */
// This is purposefully hidden so that we can control available events
data class Event<T>

private constructor(private val eventType: Type, private val callbackType: Class<T>) {

    enum class Type {
        ALL,

        // Shared
        CREATE,
        START,
        RESUME,
        PAUSE,
        STOP,
        DESTROY,
        SAVE_INSTANCE_STATE,
        CONFIGURATION_CHANGED,
        ACTIVITY_RESULT,
        REQUEST_PERMISSIONS_RESULT,

        // Activity-only
        CREATE_PERSISTABLE,
        POST_CREATE,
        POST_CREATE_PERSISTABLE,
        RESTART,
        SAVE_INSTANCE_STATE_PERSISTABLE,
        RESTORE_INSTANCE_STATE,
        RESTORE_INSTANCE_STATE_PERSISTABLE,
        NEW_INTENT,
        BACK_PRESSED,
        ATTACHED_TO_WINDOW,
        DETACHED_FROM_WINDOW,

        // Fragment-only
        ATTACH,
        CREATE_VIEW,
        VIEW_CREATED,
        ACTIVITY_CREATED,
        VIEW_STATE_RESTORED,
        DESTROY_VIEW,
        DETACH
    }

    companion object {

        /**
         * Emits all events (though without any extra data).
         */
        val ALL = Event(Type.ALL, Type::class.java)

        /**
         * Emits [Activity.onCreate] and [Fragment.onCreate]. Emitted after
         * super().
         */
        val CREATE = Event(Type.CREATE, Bundle::class.java)

        /**
         * Emits [Activity.onCreate]. Emitted after super().
         */
        val CREATE_PERSISTABLE = Event(Type.CREATE_PERSISTABLE, Bundle::class.java)

        /**
         * Emits [Activity.onStart] and [Fragment.onStart]. Emitted after super().
         */
        val START = Event(Type.START, Any::class.java)

        /**
         * Emits [Activity.onPostCreate]. Emitted after super().
         */
        val POST_CREATE = Event(Type.POST_CREATE, Bundle::class.java)

        /**
         * Emits [Activity.onCreate]. Emitted after super().
         */
        val POST_CREATE_PERSISTABLE = Event(Type.POST_CREATE_PERSISTABLE, Bundle::class.java)

        /**
         * Emits [Activity.onResume] and [Fragment.onResume]. Emitted after super().
         */
        val RESUME = Event(Type.RESUME, Any::class.java)

        /**
         * Emits [Activity.onPause] and [Fragment.onPause]. Emitted before super().
         */
        val PAUSE = Event(Type.PAUSE, Any::class.java)

        /**
         * Emits [Activity.onStop] and [Fragment.onStop]. Emitted before super().
         */
        val STOP = Event(Type.STOP, Any::class.java)

        /**
         * Emits [Activity.onDestroy] and [Fragment.onDestroy]. Emitted before super().
         */
        val DESTROY = Event(Type.DESTROY, Any::class.java)

        /**
         * Emits [Activity.onSaveInstanceState] and
         * [Fragment.onSaveInstanceState]. Emitted after super().
         */
        val SAVE_INSTANCE_STATE = Event(Type.SAVE_INSTANCE_STATE, Bundle::class.java)

        /**
         * Emits [Activity.onSaveInstanceState]. Emitted after super().
         */
        val SAVE_INSTANCE_STATE_PERSISTABLE = Event(Type.SAVE_INSTANCE_STATE_PERSISTABLE, Bundle::class.java)

        /**
         * Emits [Activity.onConfigurationChanged] and
         * [Fragment.onConfigurationChanged]. Emitted after super().
         */
        val CONFIGURATION_CHANGED = Event(Type.CONFIGURATION_CHANGED, Configuration::class.java)

        //    /**
        //     * Emits {@link Activity#onActivityResult(int, int, Intent)} and
        //     * {@link Fragment#onActivityResult(int, int, Intent)}. Emitted after super().
        //     */
        //    public static final Event<ActivityResult> ACTIVITY_RESULT =
        //            new Event<>(Type.ACTIVITY_RESULT, ActivityResult.class);
        //
        //    /**
        //     * Emits {@link Activity#onRequestPermissionsResult(int, String[], int[])} and
        //     * {@link Fragment#onRequestPermissionsResult(int, String[], int[])}. Emitted after super().
        //     */
        //    public static final Event<RequestPermissionsResult> REQUEST_PERMISSIONS_RESULT =
        //            new Event<>(Type.REQUEST_PERMISSIONS_RESULT, RequestPermissionsResult.class);

        /**
         * Emits [Activity.onRestart]. Emitted after super().
         */
        val RESTART = Event(Type.RESTART, Any::class.java)

        /**
         * Emits [Activity.onRestoreInstanceState]. Emitted after super().
         */
        val RESTORE_INSTANCE_STATE = Event(Type.RESTORE_INSTANCE_STATE, Bundle::class.java)

        /**
         * Emits [Activity.onRestoreInstanceState]. Emitted after
         * super().
         */
        val RESTORE_INSTANCE_STATE_PERSISTABLE = Event(Type.RESTORE_INSTANCE_STATE_PERSISTABLE, Bundle::class.java)

        /**
         * Emits [Activity.onNewIntent]. Emitted after super().
         */
        val NEW_INTENT = Event(Type.NEW_INTENT, Intent::class.java)

        /**
         * Emits [Activity.onBackPressed]. Emitted after super().
         */
        val BACK_PRESSED = Event(Type.BACK_PRESSED, Any::class.java)

        /**
         * Emits [Activity.onAttachedToWindow]. Emitted after super().
         */
        val ATTACHED_TO_WINDOW = Event(Type.ATTACHED_TO_WINDOW, Any::class.java)

        /**
         * Emits [Activity.onDetachedFromWindow]. Emitted after super().
         */
        val DETACHED_FROM_WINDOW = Event(Type.DETACHED_FROM_WINDOW, Any::class.java)

        /**
         * Emits [Fragment.onAttach]. Emitted after super().
         */
        val ATTACH = Event(Type.ATTACH, Context::class.java)

        /**
         * Emits [Fragment.onCreateView]. Emitted after super().
         */
        val CREATE_VIEW = Event(Type.CREATE_VIEW, Bundle::class.java)

        //    /**
        //     * Emits {@link Fragment#onViewCreated(View, Bundle)} ()}. Emitted before super().
        //     */
        //    public static final Event<ViewCreated> VIEW_CREATED = new Event<>(Type.VIEW_CREATED, ViewCreated.class);

        /**
         * Emits [Fragment.onActivityCreated]. Emitted after super().
         */
        val ACTIVITY_CREATED = Event(Type.ACTIVITY_CREATED, Bundle::class.java)

        /**
         * Emits [Fragment.onViewStateRestored]. Emitted after super().
         */
        val VIEW_STATE_RESTORED = Event(Type.VIEW_STATE_RESTORED, Bundle::class.java)

        /**
         * Emits [Fragment.onDestroyView]. Emitted before super().
         */
        val DESTROY_VIEW = Event(Type.DESTROY_VIEW, Any::class.java)

        /**
         * Emits [Fragment.onDetach]. Emitted before super().
         */
        val DETACH = Event(Type.DETACH, Any::class.java)
    }
}

