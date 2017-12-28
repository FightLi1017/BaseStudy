package router.android.lcx.basestudy.AdapterTest;

import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

import fight.android.lcx.typeadapter.BaseItemViewBinder;
import fight.android.lcx.typeadapter.ClassLinker;
import fight.android.lcx.typeadapter.Linker;
import fight.android.lcx.typeadapter.TypeAdapter;
import router.android.lcx.basestudy.R;

public class RichActivity extends AppCompatActivity {
    private TypeAdapter adapter;
    private List<Object> items;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rich);
        RecyclerView recyclerView = (RecyclerView) findViewById(R.id.list);

        adapter = new TypeAdapter();

//        adapter.register(RichItem.class, new RichItemViewBinder());
//        adapter.register(ImageItem.class, new ImageItemViewBinder());

//        adapter.register(RichItem.class).to(
//                new RichItemViewBinder(),
//                new RichItemViewBinder())
//                .withLinker(new Linker<RichItem>() {
//                    @Override
//                    public int index(int position, RichItem item) {
//                        return 0;
//                    }
//                });

        adapter.register(ImageItem.class, new ImageItemViewBinder());
        adapter.register(RichItem.class).to(
                new RichItemViewBinder(),
                new LinkerItemViewBinder()
        ).withClassLinker(new ClassLinker<RichItem>() {
            @NonNull
            @Override
            public Class<? extends BaseItemViewBinder<?, RichItem>> index(int position, @NonNull RichItem item) {
                if (item.type==RichItem.TYPE_1){
                     return RichItemViewBinder.class;
                 } else {
                     return LinkerItemViewBinder.class;
                 }
            }
        });
        recyclerView.setAdapter(adapter);
        ImageItem imageItem=new ImageItem(R.mipmap.ic_launcher);
        items = new ArrayList<>();
        for (int i = 0; i < 20; i++) {
            if (i%2==0){
                items.add(new RichItem("李晨希帅的一匹", R.drawable.avatar,1));
            } else {
                items.add(new RichItem("李晨希是真的帅的一匹", R.drawable.avatar,2));
            }
            items.add(imageItem);

        }
        adapter.setItems(items);
        adapter.notifyDataSetChanged();
    }
}
