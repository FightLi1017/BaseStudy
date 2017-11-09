package router.android.lcx.basestudy.Test;

import android.app.Activity;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Toast;

import java.io.File;

import fight.android.lcx.downmanager.SimpleDown.FileCallback;
import fight.android.lcx.downmanager.SimpleDown.FileDownManager;
import fight.android.lcx.downmanager.SimpleDown.Progress;
import fight.android.lcx.downmanager.TLog;
import router.android.lcx.basestudy.R;

public class Main2Activity extends AppCompatActivity {
    String url="http://img.kutoo8.com/upload/thumb/938479/9f043b3297e9ce3b101b44f5525ef205_960x540.jpg";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        findViewById(R.id.button5).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FileDownManager.url(url).name("hhh.jpg").callback(new FileCallback() {
                    @Override
                    public void success(File file) {
                        TLog.d(file.getAbsolutePath());
                        Toast.makeText(Main2Activity.this,"下载ok",Toast.LENGTH_SHORT).show();
                    }

                    @Override
                    public void downloadProgress(Progress progress) {
                        TLog.d("contentlength"+progress.contentLength+"totalReaded:"+progress.totalReaded);
                    }

                    @Override
                    public void error() {
                        Toast.makeText(getBaseContext(),"下载失败",Toast.LENGTH_SHORT).show();
                    }
                }).startdown();
            }
        });
    }
}
