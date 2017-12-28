package fight.android.lcx.typeadapter;

import android.support.annotation.NonNull;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by lichenxi on 2017/11/18.
 */

public interface TypePoll {
    /**
     * ddd
     * @param clazz
     * @param itemViewBinder
     * @param linker
     * @param <T>
     */
    <T> void register(@NonNull Class<? extends T> clazz,
                      @NonNull BaseItemViewBinder<?,T> itemViewBinder,
                      @NonNull Linker<T> linker);

   int indexOf(@NonNull Class<?> clazz);


    @NonNull
    BaseItemViewBinder getItemBinder(int index);

    @NonNull
    BaseItemViewBinder getBinderByClass(@NonNull int  index);


    @NonNull
    Linker<?> getLinker(int index);
}
