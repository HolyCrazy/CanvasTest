package com.example.leeduo.canvastest;

import android.graphics.Canvas;
import android.graphics.Paint;

/**
 * Created by LeeDuo on 2018/11/25.
 */
//矩形
public class Rect extends Shape {
    private String content="",name;
    private int centerX,topY,leftX,rightX,bottomY;
    private int widths;

    public Rect(int centerX,int topY){
        this.centerX = centerX;
        this.topY = topY;

    }
    @Override
    public void draw(Canvas canvas, Paint shapePaint, Paint textPaint){
        widths = TaskUtils.getLength(content);
        leftX = centerX - 30*widths;
        rightX = centerX + 30*widths;
        bottomY = topY +90;
        switch (ShapeFactory.getLocation().getDrawDirection()){
            case "down":
                canvas.drawRect(leftX,topY,rightX,bottomY,shapePaint);
                canvas.drawText(content,centerX-widths*19,bottomY-20,textPaint);
                break;
            case "up":
                bottomY = topY;
                topY = bottomY - 90;
                canvas.drawRect(leftX,topY,rightX,bottomY,shapePaint);
                canvas.drawText(content,centerX-widths*19,bottomY-20,textPaint);
                break;
            case "left":
                topY = topY - 45;
                bottomY = topY +90;
                leftX = centerX - 60*widths;
                rightX = centerX;
                centerX = centerX -30*widths;
                canvas.drawRect(leftX,topY,rightX,bottomY,shapePaint);
                canvas.drawText(content,centerX-widths*19,bottomY-20,textPaint);
                break;
            case "right":
                topY = topY - 45;
                bottomY = topY +90;
                leftX = centerX;
                rightX = centerX + 60*widths;
                centerX = centerX + 30*widths;
                canvas.drawRect(leftX,topY,rightX,bottomY,shapePaint);
                canvas.drawText(content,centerX-widths*19,bottomY-20,textPaint);
                break;
        }


    }

    @Override
    public void setNextPosition(Location location) {
         switch (location.getDrawDirection()){
             case "down":
                 location.setY(bottomY);
                 break;
             case "up":
                 location.setY(topY);
                 break;
             case "left":
                 location.setY(topY/2+bottomY/2);
                 location.setX(leftX);
                 break;
             case "right":
                 location.setY(topY/2+bottomY/2);
                 location.setX(rightX);
                 break;
         }
    }

    @Override
    public String getContent() {
        return content;
    }
    @Override
    public void setContent(String content) {
        this.content = content;
    }
    @Override
    public String getName() {
        return name;
    }
    @Override
    public void setName(String name) {
        this.name = name;
    }

    @Override
    public void set(String property,String value) {
        switch (property){
            case "content":
                setContent(value);
                break;
            case "name":
                setName(value);
                break;
        }
    }
    @Override
    public int getBottomY() {
        return bottomY;
    }
    @Override
    public int getRightX() {
        return rightX;
    }

    @Override
    public int getLeftX() {
        return leftX;
    }
    @Override
    public boolean canDraw() {
        return true;
    }
    @Override
    public int getTopY() {
        return topY;
    }
}
