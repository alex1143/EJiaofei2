package com.dxkj.ejiaofei.ejiaofei.lock;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.drawable.Drawable;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.view.View;

import com.dxkj.ejiaofei.R;

/**
 * 密码图案提示
 * Created by dxkj on 2015/9/29.
 */
public class LockIndicator extends View {

    private int numRow = 3;                                //3行
    private int numColum = 3;                              //3列
    private int patternWith = 40;
    private int patternHeight = 40;
    private int f = 5;
    private int g = 5;
    private int strokeWidth = 3;                           //线宽
    private Paint paint = null;
    private Drawable patternNoraml = null;
    private Drawable patternPressed = null;
    private String lockPassStr; // 手势密码

    public LockIndicator(Context context) {
        super(context);
    }

    public LockIndicator(Context context, AttributeSet attrs) {
        super(context, attrs);
        paint = new Paint();
        paint.setStrokeWidth(strokeWidth);
        //设置画笔风格风格：STROKE 仅绘形状的轮廓；FILL 仅填充形状；FILL_AND_STROKE 填充并绘制形状的轮廓
        paint.setStyle(Paint.Style.STROKE);
        patternNoraml = getResources().getDrawable(R.drawable.lock_pattern_node_normal);
        patternPressed = getResources().getDrawable(R.drawable.lock_pattern_node_pressed);
        if (patternPressed != null) {
            patternWith = patternPressed.getIntrinsicWidth();
            patternHeight = patternPressed.getIntrinsicHeight();
            this.f = patternWith / 4;
            this.g = patternHeight / 4;
            patternPressed.setBounds(0, 0, patternWith, patternHeight);
            patternNoraml.setBounds(0, 0, patternWith, patternHeight);
        }
    }

    @Override
    protected void onDraw(Canvas canvas) {
        if (patternNoraml == null || patternPressed == null) {
            return;
        }
        //绘制3*3图案
        for (int i = 0; i < numRow; i++) {
            for (int j = 0; j < numColum; j++) {
                paint.setColor(0x006699FF);
                int i1 = j * patternHeight + j * this.g;
                int i2 = i * patternWith + i * this.f;
                canvas.save();
                canvas.translate(i1, i2);
                String curNum = String.valueOf(numColum * i + (j + 1));
                if (!TextUtils.isEmpty(lockPassStr)) {
                    if (lockPassStr.indexOf(curNum) == -1) {
                        // 未选中
                        patternNoraml.draw(canvas);
                    } else {
                        // 被选中
                        patternPressed.draw(canvas);
                    }
                } else {
                    // 重置状态
                    patternNoraml.draw(canvas);
                }
                canvas.restore();
            }
        }
    }

    @Override
    protected void onMeasure(int paramInt1, int paramInt2) {
        if (patternPressed != null)
            setMeasuredDimension(numColum * patternHeight + this.g
                    * (-1 + numColum), numRow * patternWith + this.f
                    * (-1 + numRow));
    }

    /**
     * 请求重新绘制
     *
     * @param passString 手势密码字符序列
     */
    public void setPath(String passString) {
        lockPassStr = passString;
        invalidate();
    }

}
