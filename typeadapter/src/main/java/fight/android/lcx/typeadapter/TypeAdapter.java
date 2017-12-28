package fight.android.lcx.typeadapter;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.RecyclerView.ViewHolder;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import java.util.Collections;
import java.util.List;

/**
 * Created by lichenxi on 2017/11/18.
 */

public class TypeAdapter extends RecyclerView.Adapter<ViewHolder> {

     private List<?> mItems;
     private TypePoll mTypepoll;
     private LayoutInflater mInflater;

      public TypeAdapter(){
          this(Collections.emptyList());
      }

     public TypeAdapter(List<?> items){
        this(items,new MultTypepoll());
     }

     public TypeAdapter(List<?> items,int initcount){
        this(items,new MultTypepoll(initcount));
    }
     private TypeAdapter(List<?> items, TypePoll typepoll) {
        mItems = items;
        mTypepoll = typepoll;
    }


    public <T> void register(@NonNull Class<? extends T > clazz, @NonNull BaseItemViewBinder<?,T> binder) {
        register(clazz,binder,new Defaultlinker<T>());
    }
    public <T> OneToManyFlow<T> register(@NonNull Class<? extends T > clazz) {
         return new OneToManyColl(this,clazz);
    }

     <T> void register(@NonNull Class<? extends T> clazz,
                       @NonNull BaseItemViewBinder<?, T> binder,
                       @NonNull Linker<T> linker) {
         mTypepoll.register(clazz, binder,linker);
    }


    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
    if (mInflater==null){
        mInflater=LayoutInflater.from(parent.getContext());
    }
      BaseItemViewBinder itemViewBinder=mTypepoll.getItemBinder(viewType);
      itemViewBinder.mAdapter=TypeAdapter.this;
      return itemViewBinder.onCreateViewHolder(mInflater,parent);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
      Object item=mItems.get(position);
      BaseItemViewBinder itemViewBinder=mTypepoll.getBinderByClass(holder.getItemViewType());
      itemViewBinder.onBindViewHolder(holder,item);
    }

    @Override
    public void onViewRecycled(ViewHolder holder) {
        super.onViewRecycled(holder);
    }

    @Override
    public int getItemViewType(int position) {
        Object object=mItems.get(position);
        int index= mTypepoll.indexOf(object.getClass());
        if (index!=-1){
            Linker<Object> linker=(Linker<Object>)mTypepoll.getLinker(index);
            return index+linker.index(position,object);
        }
        throw  new BinderNotFoundException(object.getClass());
        //我用集合存放的item 和binder 那么我只有 通过这个object 找到 item集合里面的index 那就是type了
    }

    @Override
    public int getItemCount() {
        return mItems.size();
    }

    public void setItems(List<?> items) {
        mItems = items;
    }
}
