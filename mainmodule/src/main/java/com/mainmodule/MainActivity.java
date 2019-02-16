package com.mainmodule;

import android.app.Activity;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.res.AssetManager;
import android.content.res.Resources;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.RxActivityResult.ActivityResultInfo;
import com.RxActivityResult.RxActivityResult;

import io.reactivex.functions.Consumer;

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
        findViewById(R.id.imageView).setOnClickListener(new  View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(MainActivity.this,TestKeyBoardActivity.class);
                startActivity(intent);
            }
        });

    }

    @Override
    protected void onResume() {
        super.onResume();
       
    }

    @Override
    public Resources getResources() {
        if(getApplication() != null && getApplication().getResources() != null){
            return getApplication().getResources();
        }
        return super.getResources();
    }

}
