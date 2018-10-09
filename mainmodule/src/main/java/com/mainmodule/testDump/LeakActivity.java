package com.mainmodule.testDump;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.mainmodule.R;

public class LeakActivity extends AppCompatActivity {
    private static Object inner;
    private Button button;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_leak);
        button = (Button) findViewById(R.id.bt_next);
        button.setText("MainActivity");
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                createInnerClass();
                finish();
            }
        });
    }

    class InnerClass {
    }

    private void createInnerClass() {
        inner = new InnerClass();
    }

}
