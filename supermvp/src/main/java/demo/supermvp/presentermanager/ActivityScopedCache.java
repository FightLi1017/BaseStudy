package demo.supermvp.presentermanager;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.util.ArrayMap;

import java.util.Map;

import demo.supermvp.MvpPresenter;
import demo.supermvp.MvpView;

/**
 * @author：lichenxi
 * @date 2018/9/25 19
 * email：525603977@qq.com
 * Fighting
 * 保存Activity下信息
 */
public class ActivityScopedCache {
    private final Map<String, MvpPresenter<? extends MvpView>> presenterMap = new ArrayMap<>();

    public  void  clear(){
        presenterMap.clear();
    }

    public void remove(@NonNull String viewId) {
        if (viewId == null) {
            throw new NullPointerException("View Id is null");
        }

        presenterMap.remove(viewId);
    }

    public void putPresenter(@NonNull String viewId,
                             @NonNull MvpPresenter<? extends MvpView> presenter){
        if (viewId == null) {
            throw new NullPointerException("ViewId is null");
        }

        if (presenter == null) {
            throw new NullPointerException("Presenter is null");
        }
         presenterMap.put(viewId,presenter);
    }

    @Nullable
    public <P> P getPresenter(@NonNull String viewId) {
        MvpPresenter mvpPresenter = presenterMap.get(viewId);
        return mvpPresenter == null ? null : (P) mvpPresenter;
    }
}
