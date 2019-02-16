package com.mainmodule.windowManagerDemo;

import android.content.Context;
import android.content.res.TypedArray;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.view.LayoutInflaterFactory;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.FrameLayout;

import com.mainmodule.R;

/**
 * @author：lichenxi
 * @date 2018/12/24 12
 * email：525603977@qq.com
 * Fighting
 */
public class TextProgressLayout extends FrameLayout {
    private String text;
    private Context context;
    private TextProgress textProgress;
    private int max;
    public TextProgressLayout(@NonNull Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        this.context=context;
        TypedArray a = context.obtainStyledAttributes(attrs, R.styleable.TextProgress);
        text=a.getString(R.styleable.TextProgress_text);
        max=a.getInt(R.styleable.TextProgress_max,10);
        init();
    }

    private void init() {
        View view = LayoutInflater.from(context).inflate(R.layout.progress_text, this, true);
        textProgress=view.findViewById(R.id.progress);
        textProgress.setText(text);
        textProgress.setMax(max);
    }

    public void setProgress(int progress) {
        textProgress.setProgress(progress);
    }

    public void setMax(int max){
        textProgress.setMax(max);
    }
    public void setText(String text) {
        textProgress.setText(text);
    }
}
