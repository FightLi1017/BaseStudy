package com.mainmodule.windowManagerDemo;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.PixelFormat;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.Toast;

import com.RxActivityResult.ActivityResultInfo;
import com.RxActivityResult.RxActivityResult;
import com.mainmodule.ContextProgressView;
import com.mainmodule.MainActivity;
import com.mainmodule.R;
import com.mainmodule.TestOnResult;

import butterknife.BindView;
import butterknife.ButterKnife;
import io.reactivex.functions.Consumer;

public class WindowManagerActivity extends AppCompatActivity {

    @BindView(R.id.container)
    RelativeLayout container;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_window_manager);
        ButterKnife.bind(this);
        ContextProgressView contextProgressView=new ContextProgressView(this,0);
        container.addView(contextProgressView,100,100);
        findViewById(R.id.button2).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//               if (contextProgressView.getVisibility()==View.VISIBLE){
////                   contextProgressView.setVisibility(View.INVISIBLE);
////               }else {
////                   contextProgressView.setVisibility(View.VISIBLE);
////               }
                    hhhh();

            }
        });
//        initFloat();
//        textProgress.setMax(7);
//        textProgress.setText("文章发布中(1/3)...");
//        textProgress.postDelayed(new Runnable() {
//            @Override
//            public void run() {
//                new Thread() {
//                    @Override
//                    public void run() {
//                        int i = 0;
//                        while (i < 7) {
//                            i++;
//                            try {
//                                Thread.sleep(180);
//                            } catch (InterruptedException e) {
//                                e.printStackTrace();
//                            }
//                            textProgress.setProgress(i);
//                        }
//                    }
//                }.start();
//            }
//        }, 2000);
    }

    private void hhhh() {
        RxActivityResult.with(this)
                .startForResult(TestOnResult.class)
                .subscribe(new Consumer<ActivityResultInfo>() {
                    @Override
                    public void accept(ActivityResultInfo resultInfo) {
                        if (resultInfo.isNullData()){
                            Toast.makeText(WindowManagerActivity.this,
                                    resultInfo.getData().getStringExtra("value"),Toast.LENGTH_LONG).show();
                        }
                    }
                });

//        RxActivityResult.with(this)
//                .startForResult(TestOnResult.class, new RxActivityResult.Callback() {
//                    @Override
//                    public void onActivityResult(int requestCode, int resultCode, Intent data) {
//                       if (resultCode==Activity.RESULT_OK){
//                           Toast.makeText(WindowManagerActivity.this,
//                                   data.getStringExtra("value"),Toast.LENGTH_LONG).show();
//                       }
//                    }
//                });
    }

    private Button floatingButton;

    private void initFloat1() {
        Toast toast = new Toast(this);
        toast.setView(initView());
        toast.setGravity(Gravity.TOP, 0, 0);
        toast.setDuration(Toast.LENGTH_LONG);
        toast.show();
    }

    private View initView() {
        LayoutInflater inflater = (LayoutInflater) getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        WindowManager wm = (WindowManager) getSystemService(Context.WINDOW_SERVICE);
        //获得屏幕的宽度
        int width = wm.getDefaultDisplay().getWidth();
        //由layout文件创建一个View对象
        View view = inflater.inflate(R.layout.test_float, null);
        LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(width,
                ViewGroup.LayoutParams.WRAP_CONTENT);
//        TextView toastTextView = (TextView) view.findViewById(R.id.tv_toast);
//        //设置TextView的宽度为 屏幕宽度
//        toastTextView.setLayoutParams(layoutParams);
//        toastTextView.setText("测试下悬浮问题");
        return view;
    }

    private void initFloat() {

        WindowManager.LayoutParams layoutParams = new WindowManager.LayoutParams(
                WindowManager.LayoutParams.MATCH_PARENT,
                WindowManager.LayoutParams.WRAP_CONTENT,
                0, 0,
                PixelFormat.TRANSLUCENT
        );
        // flag 设置 Window 属性
        // type 设置 Window 类别（层级）
        layoutParams.gravity = Gravity.TOP;
        layoutParams.type = WindowManager.LayoutParams.TYPE_APPLICATION_OVERLAY;
        layoutParams.flags = WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON
                | WindowManager.LayoutParams.FLAG_NOT_FOCUSABLE
                | WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE;
        WindowManager windowManager = getWindowManager();
        View view = initView();
        TextProgress textProgress = view.findViewById(R.id.progress);
        textProgress.setText("文章发布中(1/3)...");
        windowManager.addView(view, layoutParams);

    }


}
