package fight.android.lcx.typeadapter;

import android.support.annotation.CheckResult;
import android.support.annotation.NonNull;

import java.util.List;

/**
 * @author：lichenxi
 * @date 2017/12/8 16
 * email：525603977@qq.com
 * Fighting
 */
public class OneToManyColl<T> implements OneToManyFlow<T>,OneToManyEndpoint<T> {
      private @NonNull final TypeAdapter adapter;
      private @NonNull final Class<? extends T> clazz;
      private BaseItemViewBinder<?,T>[] binders;

    public OneToManyColl(@NonNull TypeAdapter typeAdapter, @NonNull Class<? extends T> clazz) {
        this.adapter = typeAdapter;
        this.clazz = clazz;
    }

    @Override
    public void withLinker(@NonNull Linker<T> linker) {
        doRegister(linker);
    }

    private void doRegister(Linker<T> linker) {
        for (BaseItemViewBinder<?,T> binder:binders) {
             adapter.register(clazz,binder,linker);
        }
    }


    @Override @CheckResult
    public  @NonNull OneToManyEndpoint<T> to(@NonNull BaseItemViewBinder<?, T>... binders) {
        this.binders=binders;
        return this;
    }

    @Override
    public void withClassLinker(@NonNull ClassLinker<T> classLinker) {
        doRegister(ClassLinkerWrapper.wrap(classLinker,binders));
    }
}
