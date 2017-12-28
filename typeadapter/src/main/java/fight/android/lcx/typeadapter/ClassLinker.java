package fight.android.lcx.typeadapter;

import android.support.annotation.NonNull;

/**
 * @author：lichenxi
 * @date 2017/12/8 16
 * email：525603977@qq.com
 * Fighting
 */
public interface ClassLinker<T>  {

   @NonNull  Class<? extends BaseItemViewBinder<?,T>> index(int position, @NonNull T t);
}
