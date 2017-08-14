package router.android.lcx.basestudy;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import fight.android.lcx.downmanager.DataWatcher;
import fight.android.lcx.downmanager.DownEntry;
import fight.android.lcx.downmanager.DownManager;
import fight.android.lcx.downmanager.TLog;

public class MainActivity extends AppCompatActivity  implements View.OnClickListener{

    private Button mButton1;
    private Button mButton2;
    private Button mButton3;
    private DownManager mDownManager;
    private DownEntry downEntry;
    private DataWatcher mDataWatcher=new DataWatcher() {
        @Override
        protected void notifyUpdata(DownEntry data) {
            TLog.d(data.toString());
                    downEntry=data;
            if (downEntry.mDownloadStatus==DownEntry.DownloadStatus.cancel){
                  downEntry=null;
            }

        }
    };
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mButton1=(Button)findViewById(R.id.button1);
        mButton1.setOnClickListener(this);
        mButton2=(Button)findViewById(R.id.button2);
        mButton2.setOnClickListener(this);
        mButton3=(Button)findViewById(R.id.button3);
        mButton3.setOnClickListener(this);
        mDownManager=DownManager.getInstance(this);

    }

    @Override
    protected void onResume() {
        super.onResume();
        mDownManager.addObserver(mDataWatcher);

    }

    @Override
    protected void onPause() {
        super.onPause();
        mDownManager.removeObserver(mDataWatcher);
    }

    @Override
    public void onClick(View v) {
        if (downEntry==null){
            downEntry=new DownEntry();
            downEntry.id="1";
            downEntry.name="test.jpg";
            downEntry.url="http://www.baidu.com";
        }

        switch (v.getId()){
            case R.id.button1:
                mDownManager.add(downEntry);
                break;
            case R.id.button2:
                if (downEntry.mDownloadStatus== DownEntry.DownloadStatus.downloading){
                    mDownManager.pause(downEntry);
                }else if (downEntry.mDownloadStatus== DownEntry.DownloadStatus.pause){
                    mDownManager.resume(downEntry);
                }

                break;
            case R.id.button3:
                mDownManager.cancel(downEntry);
                break;
        }

    }
}
