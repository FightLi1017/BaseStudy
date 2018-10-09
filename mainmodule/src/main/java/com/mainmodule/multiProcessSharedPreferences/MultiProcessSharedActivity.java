package com.mainmodule.multiProcessSharedPreferences;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

import com.mainmodule.R;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MultiProcessSharedActivity extends AppCompatActivity {

    @BindView(R.id.textView2)
    TextView textView2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_multi_process_shared_preferences);
        ButterKnife.bind(this);
        String aa= SharedPreferenceUtil.getInstance(this).getValue("Text","123456");
        textView2.setText(aa);
    }
}
