package fight.android.lcx.typeadapter;

import android.support.annotation.CheckResult;
import android.support.annotation.NonNull;

/**
 * @author：lichenxi
 * @date 2017/12/8 16
 * email：525603977@qq.com
 * Fighting
 */
public interface OneToManyFlow<T> {
    @CheckResult 
    @NonNull OneToManyEndpoint<T> to(@NonNull BaseItemViewBinder<?,T> ...binders);
}
