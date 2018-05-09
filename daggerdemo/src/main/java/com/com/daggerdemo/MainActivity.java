package com.com.daggerdemo;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.Button;
import com.squareup.otto.Produce;
import com.squareup.otto.Subscribe;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class MainActivity extends AppCompatActivity {

    @BindView(R.id.button)
    Button button;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        BusProvider.getInstance().register(this);
    }

    @Subscribe
    public void Attach(Event1 event1){
        Log.d("llll",event1.toString());
    }

    @Produce
    public Event2 Attach1(){
        return new Event2();
    }

    @OnClick(R.id.button)
    public void onViewClicked() {
        Intent intent=new Intent(this,Main2Activity.class);
        startActivity(intent);
    }
    @Override
    protected void onDestroy() {
        super.onDestroy();
        BusProvider.getInstance().unregister(this);
    }
}
