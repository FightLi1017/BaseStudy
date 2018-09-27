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
import io.reactivex.functions.Consumer;

public class TestKeyBoardActivity extends AppCompatActivity {
    @BindView(R.id.status)
    TextView tvStatus;
    KeyboardDetector mKeyboardDetector;
    @BindView(R.id.image)
    ImageView image;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test_key_board);
        ButterKnife.bind(this);
        mKeyboardDetector = new KeyboardDetector(this);
        mKeyboardDetector.observe().subscribe(new Consumer<KeyboardStatus>() {
            @Override
            public void accept(KeyboardStatus status) throws Exception {
                switch (status) {
                    case OPEN:
                        image.setVisibility(View.VISIBLE);
                        tvStatus.setText(getString(R.string.opened));
                        break;
                    case CLOSED:
                        image.setVisibility(View.GONE);
                        tvStatus.setText(getString(R.string.closed));
                        break;
                }
            }
        });
    }
}
