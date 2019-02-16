package com.mainmodule.windowManagerDemo;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.view.View;
import android.widget.ProgressBar;

import com.mainmodule.R;

/**
 * @author：lichenxi
 * @date 2018/12/21 16
 * email：525603977@qq.com
 * Fighting
 */
public class TextProgress extends ProgressBar {
    private float density = 1;
    private String text;
    private Paint mPaint;
    public TextProgress(Context context) {
        super(context);
        density=context.getResources().getDisplayMetrics().density;
        init();
    }

    public TextProgress(Context context, AttributeSet attrs) {
        super(context, attrs);
        density=context.getResources().getDisplayMetrics().density;
        init();
    }

    public void setText(String text) {
        if (TextUtils.isEmpty(text)){
            text="";
        }
        this.text = text;
        invalidate();
    }

//    @Override
//    protected void onFinishInflate() {
//        super.onFinishInflate();
//    }

    private void init(){
        mPaint=new Paint(Paint.ANTI_ALIAS_FLAG);
        mPaint.setColor(Color.parseColor("#333333"));
        mPaint.setTextSize(dp(13));
    }

    @Override
    protected synchronized void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        Rect rect=new Rect();
        if (!TextUtils.isEmpty(text)){
            mPaint.getTextBounds(text,0,text.length(),rect);
            int y=(getHeight()/2)-rect.centerY();
            canvas.drawText(text,dp(40),y,mPaint);
        }
    }

    private int dp(float value) {
        if (value == 0) {
            return 0;
        }
        return (int) Math.ceil(density * value);
    }
}
