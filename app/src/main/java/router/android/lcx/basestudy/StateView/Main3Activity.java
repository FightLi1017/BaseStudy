package router.android.lcx.basestudy.StateView;

import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;
import router.android.lcx.basestudy.R;

public class Main3Activity extends AppCompatActivity {

    @Bind(R.id.empty)
    Button empty;
    @Bind(R.id.nonetwork)
    Button nonetwork;
    @Bind(R.id.loading)
    Button loading;
    @Bind(R.id.error)
    Button error;
    @Bind(R.id.multstateview)
    MultStateView multstateview;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main3);
        ButterKnife.bind(this);
        multstateview.setOnRetryClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                multstateview.showView(MultStateView.STATUS_LOADING);
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        multstateview.showView(MultStateView.STATUS_CONTENT);
                    }
                },2000);
            }
        });
    }

    @OnClick({R.id.empty, R.id.nonetwork, R.id.loading, R.id.error})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.empty:
                multstateview.showView(MultStateView.STATUS_EMPTY);
                break;
            case R.id.nonetwork:
                multstateview.showView(MultStateView.STATUS_NO_NETWORK);
                break;
            case R.id.loading:
                multstateview.showView(MultStateView.STATUS_LOADING);
                break;
            case R.id.error:
                multstateview.showView(MultStateView.STATUS_ERROR);
                break;
        }
    }
}
