package com.mainmodule;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.keyboard_detector.KeyboardDetector;
import com.keyboard_detector.KeyboardStatus;

import butterknife.BindView;
import butterknife.ButterKnife;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.functions.Consumer;

public class TestKeyBoardActivity extends AppCompatActivity {
    @BindView(R.id.status)
    TextView tvStatus;
    KeyboardDetector mKeyboardDetector;
    CompositeDisposable mCompositeDisposable=new CompositeDisposable();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test_key_board);
        ButterKnife.bind(this);
        mKeyboardDetector = new KeyboardDetector(this);
        mCompositeDisposable.add(mKeyboardDetector.observe().subscribe(new Consumer<KeyboardStatus>() {
            @Override
            public void accept(KeyboardStatus status) throws Exception {
                switch (status) {
                    case OPEN:
                        tvStatus.setText(getString(R.string.opened));
                        break;
                    case CLOSED:
                        tvStatus.setText(getString(R.string.closed));
                        break;
                }
            }
        }));

    }

    @Override
    protected void onDestroy() {
        mCompositeDisposable.clear();
        super.onDestroy();

    }
}
