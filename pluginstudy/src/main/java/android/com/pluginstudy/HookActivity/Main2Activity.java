package android.com.pluginstudy.HookActivity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Gravity;
import android.widget.Button;
import android.widget.TextView;

public class Main2Activity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        TextView tv = new TextView(this);
        tv.setGravity(Gravity.CENTER);
        String text=getIntent().getStringExtra(HookHelper.Plugin);
        tv.setText(text);
        setContentView(tv);
    }
}
