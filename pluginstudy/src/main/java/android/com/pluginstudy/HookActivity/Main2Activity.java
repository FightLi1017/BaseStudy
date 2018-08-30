package android.com.pluginstudy.HookActivity;

import android.animation.Animator;
import android.animation.ObjectAnimator;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Gravity;
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

        ObjectAnimator fadeAnim = ObjectAnimator.ofFloat(tv, "alpha", 1f, 0f);

    }
}
