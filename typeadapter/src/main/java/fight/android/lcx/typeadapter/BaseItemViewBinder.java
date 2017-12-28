package fight.android.lcx.typeadapter;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.RecyclerView.ViewHolder;
import android.view.LayoutInflater;
import android.view.ViewGroup;

/**
 * Created by lichenxi on 2017/11/18.
 */

public abstract class BaseItemViewBinder<V extends ViewHolder,T>  {

     RecyclerView.Adapter mAdapter;

     @NonNull
     protected abstract V onCreateViewHolder(@NonNull LayoutInflater inflater, ViewGroup viewGroup);

     protected abstract void onBindViewHolder(@NonNull V holder,@NonNull T t);

    @NonNull
    protected  final RecyclerView.Adapter getAdapter(){
       return mAdapter;
   }

   protected int getPosition(@NonNull final  ViewHolder holder){
        return holder.getAdapterPosition();
   }
}
