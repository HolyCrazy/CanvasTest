package com.example.leeduo.canvastest;

import android.graphics.Color;
import android.graphics.Paint;

/**
 * Created by LeeDuo on 2018/11/25.
 * 三支笔
 */

public class PaintBox {
    private Paint textPaint,shapePaint,positionPaint;

    public PaintBox(){
        //写字的笔
        textPaint = new Paint();
        //画图的笔
        shapePaint = new Paint();
        //点点儿的笔
        positionPaint = new Paint();

        shapePaint.setAntiAlias(true);
        shapePaint.setColor(Color.parseColor("#333333"));
        shapePaint.setStyle(Paint.Style.STROKE);
        shapePaint.setStrokeWidth(4);

        textPaint.setAntiAlias(true);
        textPaint.setColor(Color.parseColor("#333333"));
        textPaint.setStrokeWidth(4);
        textPaint.setTextSize(80);

        positionPaint.setAntiAlias(true);
        positionPaint.setColor(Color.parseColor("#dd2222"));

    }

    public void clear(){
        shapePaint.setAntiAlias(true);
        shapePaint.setColor(Color.parseColor("#333333"));
        shapePaint.setStyle(Paint.Style.STROKE);
        shapePaint.setStrokeWidth(4);

        textPaint.setAntiAlias(true);
        textPaint.setColor(Color.parseColor("#333333"));
        textPaint.setStrokeWidth(4);
        textPaint.setTextSize(80);

        positionPaint.setAntiAlias(true);
        positionPaint.setColor(Color.parseColor("#dd2222"));
    }

    public Paint getShapePaint(){
        return  shapePaint;
    }
    public Paint getTextPaint(){
        return textPaint;
    }
    public Paint getPositionPaint(){
        return positionPaint;
    }

}
