package demo.supermvp.presentermanager;

import android.app.Activity;
import android.app.Application;
import android.os.Bundle;
import android.support.annotation.MainThread;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.util.ArrayMap;
import android.util.Log;

import java.util.Map;
import java.util.UUID;

import demo.supermvp.MvpPresenter;
import demo.supermvp.MvpView;
import demo.supermvp.abs.AbsLifecycleCallbacks;

/**
 * @author：lichenxi
 * @date 2018/9/25 18
 * email：525603977@qq.com
 * Fighting
 */
// TODO: 2018/9/25  先不考虑ViewState的问题
public class PresenterManager {
    final static String KEY_ACTIVITY_ID = "PresenterManagerActivityId";
    private final static Map<Activity, String> activityIdMap = new ArrayMap<>();
    private final static Map<String, ActivityScopedCache> activityScopedCacheMap = new ArrayMap<>();
  static final Application.ActivityLifecycleCallbacks activityLifecycleCallbacks =new AbsLifecycleCallbacks() {
      @Override
      public void onActivityCreated(Activity activity, Bundle savedInstanceState) {
         if (savedInstanceState!=null){
             String activityId = savedInstanceState.getString(KEY_ACTIVITY_ID);
             if (activityId != null) {
                 //屏幕方向发生改变或者配置发生改变之后 我们恢复之前的P
                 activityIdMap.put(activity, activityId);
             }
         }
      }

      @Override
      public void onActivitySaveInstanceState(Activity activity, Bundle outState) {
          String activityId = activityIdMap.get(activity);
          if (activityId != null) {
              outState.putString(KEY_ACTIVITY_ID, activityId);
          }
      }

      @Override
      public void onActivityDestroyed(Activity activity) {
          if (!activity.isChangingConfigurations()){
              //不是因为配置改变导致的destory 走常规的destory流程
             String activityId=  activityIdMap.get(activity);
             if (activityId!=null){
                ActivityScopedCache scopedCache=activityScopedCacheMap.get(activityId);
                if (scopedCache!=null){
                    scopedCache.clear();
                    activityScopedCacheMap.remove(activityId);
                }

                if (activityScopedCacheMap.isEmpty()){
                    activity.getApplication()
                            .unregisterActivityLifecycleCallbacks(activityLifecycleCallbacks);
                }
             }
          }
          activityIdMap.remove(activity);
      }
  };

    /**
     *
     * @param activity
     * @param viewId
     * @param mvpPresenter
     */
  public static  void putPresenter(@NonNull  Activity activity, @NonNull  String viewId,
                                    @NonNull MvpPresenter<? extends MvpView> mvpPresenter){
      if (activity == null) {
          throw new NullPointerException("Activity is null");
      }

      ActivityScopedCache scopedCache = getOrCreateActivityScopedCache(activity);

      scopedCache.putPresenter(viewId,mvpPresenter);
  }

 public static  <P> P getPresenter(@NonNull  Activity activity,@NonNull  String viewId){
     if (activity == null) {
         throw new NullPointerException("Activity is null");
     }

     if (viewId == null) {
         throw new NullPointerException("View id is null");
     }
     ActivityScopedCache scopedCache = getActivityScope(activity);

     return  scopedCache==null?null:(P)scopedCache.getPresenter(viewId);
 }


  @NonNull
  @MainThread
  static ActivityScopedCache getOrCreateActivityScopedCache(@NonNull Activity activity){

     if (activity == null) {
          throw new NullPointerException("Activity is null");
      }
    String activityId= activityIdMap.get(activity);
     if (activityId==null){
         activityId= UUID.randomUUID().toString();
         activityIdMap.put(activity,activityId);
     }

      if (activityIdMap.size() == 1) {
          activity.getApplication().registerActivityLifecycleCallbacks(activityLifecycleCallbacks);
      }
     ActivityScopedCache activityScopedCache=activityScopedCacheMap.get(activityId);
     if (activityScopedCache==null){
         activityScopedCache = new ActivityScopedCache();
         activityScopedCacheMap.put(activityId,activityScopedCache);
     }
   return  activityScopedCache;
  }

    @Nullable
    @MainThread
    static ActivityScopedCache getActivityScope(@NonNull Activity activity) {
        if (activity == null) {
            throw new NullPointerException("Activity is null");
        }
        String activityId = activityIdMap.get(activity);
        if (activityId == null) {
            return null;
        }
        return activityScopedCacheMap.get(activityId);
    }

    public static void remove(Activity activity, String viewId) {
        if (activity == null) {
            throw new NullPointerException("Activity is null");
        }
        ActivityScopedCache activityScopedCache=getActivityScope(activity);
        if (activityScopedCache!=null){
            activityScopedCache.remove(viewId);
        }
    }
}
