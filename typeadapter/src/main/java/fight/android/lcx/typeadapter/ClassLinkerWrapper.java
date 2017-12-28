package fight.android.lcx.typeadapter;

import android.support.annotation.NonNull;

import java.util.Arrays;

/**
 * @author：lichenxi
 * @date 2017/12/8 17
 * email：525603977@qq.com
 * Fighting
 */
public class ClassLinkerWrapper<T> implements Linker<T> {

    private @NonNull ClassLinker<T> classLinker;
    private @NonNull BaseItemViewBinder<?,T >[] binders;

    public ClassLinkerWrapper(
            @NonNull ClassLinker<T> classLinker,
            @NonNull BaseItemViewBinder<?, T>[] binders) {
        this.classLinker = classLinker;
        this.binders = binders;
    }

    static  @NonNull <T> ClassLinkerWrapper<T> wrap(
            @NonNull ClassLinker<T> classLinker,
            @NonNull BaseItemViewBinder<?, T>[] binders) {
        return new ClassLinkerWrapper<T>(classLinker, binders);
    }
    @Override
    public int index(int position, @NonNull T t) {
        // TODO: 2017/12/8  把转换类写完
        Class<?> userIndexClass=classLinker.index(position,t);
        for (int i = 0; i < binders.length; i++) {
              if (binders[i].getClass().equals(userIndexClass)){
                  return i;
              }
        }
        throw new IndexOutOfBoundsException(
                String.format("%s is out of your registered binders'(%s) bounds.",
                        userIndexClass.getName(), Arrays.toString(binders))
        );
    }
}
