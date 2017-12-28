package fight.android.lcx.typeadapter;

import android.support.annotation.NonNull;

/**
 * @author：lichenxi
 * @date 2017/12/8 16
 * email：525603977@qq.com
 * Fighting
 */
public interface OneToManyEndpoint<T> {

    void withLinker(@NonNull  Linker<T> linker);

    void withClassLinker(@NonNull  ClassLinker<T> classLinker);
}
