package com.example.leeduo.canvastest;

import android.graphics.Canvas;
import android.graphics.Paint;

/**
 * Created by LeeDuo on 2018/11/29.
 * line类的补充，可以设置长度，但是不改变方向
 */

public class Extend extends Shape {
    private int size=10;
    private int centerX,topY,leftX,rightX,bottomY;
    private String name;

    public Extend(int centerX,int topY){
        this.centerX = centerX;
        this.topY = topY;

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
    public void draw(Canvas canvas, Paint shapePaint, Paint textPaint) {
        switch (ShapeFactory.getLocation().getDrawDirection()){
            case "down":
                canvas.drawLine(centerX,topY,centerX,topY+size,shapePaint);
                leftX = centerX;
                rightX = centerX;
                bottomY = topY + size;
                break;
            case "up":
                canvas.drawLine(centerX,topY,centerX,topY-size,shapePaint);
                leftX = centerX;
                rightX = centerX;
                bottomY = topY;
                topY = topY - size;
                break;
            case "left":
                canvas.drawLine(centerX,topY,centerX-size,topY,shapePaint);
                leftX = centerX-size;
                rightX = centerX;
                bottomY = topY;
                break;
            case "right":
                canvas.drawLine(centerX,topY,centerX+size,topY,shapePaint);
                leftX = centerX;
                rightX = centerX+size;
                bottomY = topY;
                break;
        }
    }
    @Override
    public void setNextPosition(Location location){
        switch(ShapeFactory.getLocation().getDrawDirection()){
            case "down":
                location.setY(bottomY);
                break;
            case "up":
                location.setY(topY);
                break;
            case "left":
                location.setX(leftX);
                break;
            case "right":
                location.setX(rightX);
                break;
        }
    }

    public int getSize() {
        return size;
    }

    public void setSize(int size) {
        this.size = size;
    }

    @Override
    public void set(String property, String value) {
        switch (property){
            case "size":
                try {
                    setSize(Integer.valueOf(value));
                }catch (Exception e){
                    setSize(10);
                }
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
    public int getTopY() {
        return topY;
    }

    @Override
    public boolean canDraw() {
        return true;
    }


}
