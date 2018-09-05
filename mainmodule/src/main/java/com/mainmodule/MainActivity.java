package com.mainmodule;

import android.app.Activity;
import android.content.res.AssetManager;
import android.content.res.Resources;
import android.os.Bundle;
import android.widget.ImageView;

public class MainActivity extends Activity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        TextView tv = new TextView(this);
//        tv.setText(R.string.aaa_1243);
//        tv.setGravity(Gravity.CENTER);
//        tv.setTextSize(20);
//        setContentView(tv);
        setContentView(R.layout.activity_main);
        ((ImageView)findViewById(R.id.imageView)).setImageResource(R.drawable.aa);
    }

    @Override
    public Resources getResources() {
        if(getApplication() != null && getApplication().getResources() != null){
            return getApplication().getResources();
        }
        return super.getResources();
    }

}
