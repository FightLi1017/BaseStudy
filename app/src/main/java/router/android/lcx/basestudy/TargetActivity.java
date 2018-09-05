package router.android.lcx.basestudy;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;
import android.widget.ImageView;
import android.widget.TextView;

/**
 * @author：lichenxi
 * @date 2018/8/30 17
 * email：525603977@qq.com
 * Fighting
 * 这个没有被注册哦
 */
public class TargetActivity extends Activity {

    private static final String TAG = "TargetActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        Log.d(TAG, "onCreate() called with " + "savedInstanceState = [" + savedInstanceState + "]");
          ImageView tv = new ImageView(this);
          tv.setImageResource(R.drawable.bb);
//        setContentView(tv);
//        tv.setGravity(Gravity.CENTER);
//        tv.setText(getString(R.string.plugin_name));
          setContentView(R.layout.activity_list);
    }

    @Override
    protected void onPause() {
        super.onPause();
        Log.d(TAG, "onPause() called with " + "");
    }

    @Override
    protected void onResume() {
        super.onResume();
        Log.d(TAG, "onResume() called with " + "");
    }

    @Override
    protected void onStop() {
        super.onStop();
        Log.d(TAG, "onStop() called with " + "");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.d(TAG, "onDestroy() called with " + "");
    }
}
