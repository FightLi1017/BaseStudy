package com.mainmodule;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.RectF;
import android.view.View;

/**
 * @author：lichenxi
 * @date 2019/1/26 17
 * email：525603977@qq.com
 * Fighting
 */
public class ContextProgressView extends View {

    private Paint innerPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
    private Paint outerPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
    private RectF cicleRect = new RectF();
    private int radOffset = 0;
    private long lastUpdateTime;
    private int currentColorType;

    public ContextProgressView(Context context, int colorType) {
        super(context);
        innerPaint.setStyle(Paint.Style.STROKE);
        innerPaint.setStrokeWidth(dp(2));
        outerPaint.setStyle(Paint.Style.STROKE);
        outerPaint.setStrokeWidth(dp(2));
        outerPaint.setStrokeCap(Paint.Cap.ROUND);
        currentColorType = colorType;
        updateColors();
    }

    public void updateColors() {
        if (currentColorType == 0) {
            innerPaint.setColor(getResources().getColor(R.color.colorPrimaryDark));
            outerPaint.setColor(getResources().getColor(R.color.colorAccent));
        }
// else if (currentColorType == 1) {
//            innerPaint.setColor(Theme.getColor(Theme.key_contextProgressInner2));
//            outerPaint.setColor(Theme.getColor(Theme.key_contextProgressOuter2));
//        } else if (currentColorType == 2) {
//            innerPaint.setColor(Theme.getColor(Theme.key_contextProgressInner3));
//            outerPaint.setColor(Theme.getColor(Theme.key_contextProgressOuter3));
//        }
        invalidate();
    }

    @Override
    public void setVisibility(int visibility) {
        super.setVisibility(visibility);
        lastUpdateTime = System.currentTimeMillis();
        invalidate();
    }

    @Override
    protected void onAttachedToWindow() {
        super.onAttachedToWindow();
        lastUpdateTime = System.currentTimeMillis();
        invalidate();
    }

    @Override
    protected void onDraw(Canvas canvas) {
        if (getVisibility() != VISIBLE) {
            return;
        }
        long newTime = System.currentTimeMillis();
        long dt = newTime - lastUpdateTime;
        lastUpdateTime = newTime;
        radOffset += 360 * dt / 1000.0f;

        int x = getMeasuredWidth() / 2 - dp(9);
        int y = getMeasuredHeight() / 2 - dp(9);
        cicleRect.set(x, y, x + dp(18), y + dp(18));
        canvas.drawCircle(getMeasuredWidth() / 2, getMeasuredHeight() / 2, dp(9), innerPaint);
        canvas.drawArc(cicleRect, -90 + radOffset, 90, false, outerPaint);
        invalidate();
    }
    private int dp(float value) {
        if (value == 0) {
            return 0;
        }
        return (int) Math.ceil(getContext().getResources().getDisplayMetrics().density * value);
    }
}
