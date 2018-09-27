package com.mainmodule.testmvp;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import com.mainmodule.R;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import demo.supermvp.MvpActivity;

public class TestMvpActivity extends MvpActivity<TestView, TestPresenter> implements TestView {
    @BindView(R.id.username)
    EditText username;
    @BindView(R.id.editText)
    EditText editText;
    @BindView(R.id.submit)
    Button submit;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test_mvp);
        ButterKnife.bind(this);
    }

    @NonNull
    @Override
    public TestPresenter createPresenter() {
        return new TestPresenter();
    }

    @OnClick(R.id.submit)
    public void onViewClicked() {
        //项目中使用 应该是进行格式的判断 空 正则等
        presenter.login(text(username), text(editText));

    }

    public String text(TextView view) {
        return view.getText().toString().trim();
    }

    @Override
    public void showError() {

    }

    @Override
    public void showLoading() {
        submit.setText("正在加载 请稍后");
    }

    @Override
    public void loginSuccessful() {
        submit.setText("登陆成功就可以了 哈哈");
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);

    }
}
