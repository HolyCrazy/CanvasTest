package com.example.leeduo.canvastest;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;


/**
 * Created by LeeDuo on 2018/11/30.
 * 全局设置类
 */

public class Setting extends Shape{

    private String background,textColor,shapeColor,textSize;

    public Setting(){
        background = "#ffffff";
        textColor = "#333333";
        shapeColor = "#333333";
        textSize = "80";
    }

    @Override
    public void draw(Canvas canvas, Paint shapePaint, Paint textPaint) {
        //设置背景颜色
        try {
            canvas.drawColor(Color.parseColor(background));
        }catch (Exception e){
            canvas.drawColor(Color.parseColor("#ffffff"));
        }
        //设置字体颜色
        try {
            textPaint.setColor(Color.parseColor(textColor));
        }catch (Exception e){
        }
        //设置形状颜色
        try {
            shapePaint.setColor(Color.parseColor(shapeColor));
        }catch (Exception e){
        }
        //设置字体大小，暂时不用，会影响位置
        try {
            textPaint.setTextSize(Integer.valueOf(textSize));
        }catch (Exception e){
        }

    }

    @Override
    public void set(String property, String value) {
          switch (property){
              case "background":
                  setBackground(value);
                  break;
              case "textColor":
                  setTextColor(value);
                  break;
              case "shapeColor":
                  setShapeColor(value);
                  break;
              case "textSize":
                  setTextSize(value);
                  break;
          }
    }

    @Override
    public int getBottomY() {
        return 0;
    }

    @Override
    public int getRightX() {
        return 0;
    }

    @Override
    public int getLeftX() {
        return 100;
    }

    @Override
    public int getTopY() {
        return 0;
    }

    @Override
    public boolean canDraw() {
        return true;
    }

    public String getBackground() {
        return background;
    }

    public void setBackground(String background) {
        this.background = background;
    }

    public String getTextColor() {
        return textColor;
    }

    public void setTextColor(String textColor) {
        this.textColor = textColor;
    }

    public String getShapeColor() {
        return shapeColor;
    }

    public void setShapeColor(String shapeColor) {
        this.shapeColor = shapeColor;
    }

    public String getTextSize() {
        return textSize;
    }

    public void setTextSize(String textSize) {
        this.textSize = textSize;
    }
}
