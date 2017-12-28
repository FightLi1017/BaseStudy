package fight.android.lcx.typeadapter;

import android.support.annotation.NonNull;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by lichenxi on 2017/11/18.
 */

public class MultTypepoll implements TypePoll {
    private @NonNull final List<Class<?>> contents;
    private @NonNull final  List<BaseItemViewBinder<?,?>> mViewBinders;
    private @NonNull final List<Linker<?>> linkers;

    public MultTypepoll() {
        this.contents=new ArrayList<>();
        this.mViewBinders=new ArrayList<>();
        this.linkers=new ArrayList<>();
    }
    public MultTypepoll(int initcount) {
        this.contents=new ArrayList<>(initcount);
        this.mViewBinders=new ArrayList<>(initcount);
        this.linkers=new ArrayList<>(initcount);
    }

    @Override
    public <T> void register(@NonNull Class<? extends T> clazz,
                         @NonNull BaseItemViewBinder<?,T> itemViewBinder,
                         @NonNull Linker<T> linker) {
         this.contents.add(clazz);
         this.mViewBinders.add(itemViewBinder);
         this.linkers.add(linker);
    }

    @Override
    public int indexOf(@NonNull Class<?> clazz) {
        int index=contents.indexOf(clazz);
        if (index>=0){
            return index;
        }
       for (int i=0;i<contents.size();i++){
            if (contents.get(i).isAssignableFrom(clazz)){
                return i;
            }
       }
        return index;
    }



    @NonNull
    @Override
    public BaseItemViewBinder getItemBinder(int index) {
        return mViewBinders.get(index);
    }

    @NonNull
    @Override
    public BaseItemViewBinder getBinderByClass(@NonNull int index) {
        return mViewBinders.get(index);
    }

    @NonNull
    @Override
    public Linker<?> getLinker(int index) {
        return linkers.get(index);
    }
}
