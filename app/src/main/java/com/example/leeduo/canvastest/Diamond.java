package com.example.leeduo.canvastest;

import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Path;

/**
 * Created by LeeDuo on 2018/11/26.
 */
//菱形
public class Diamond extends Shape {

    private String content="",name;
    private int centerX,topY,leftX,rightX,bottomY;
    private int widths;
    private Path path;
    public Diamond(int centerX,int topY) {
        path = new Path();
        this.centerX = centerX;
        this.topY = topY;
    }
    @Override
    public void draw(Canvas canvas, Paint shapePaint, Paint textPaint) {
        widths = TaskUtils.getLength(content);
        leftX = centerX - 35*widths;
        rightX = centerX + 35*widths;
        bottomY = topY +180;
        switch (ShapeFactory.getLocation().getDrawDirection()){
            case"down":
                break;
            case"up":
                bottomY = topY;
                topY = bottomY - 180;
                break;
            case"left":
                topY = topY - 90;
                bottomY = topY +180;
                leftX = centerX - 70*widths;
                rightX = centerX;
                centerX = centerX -35*widths;
                break;
            case"right":
                topY = topY - 90;
                bottomY = topY +180;
                leftX = centerX;
                rightX = centerX + 70*widths;
                centerX = centerX + 35*widths;
                break;
        }
        path.moveTo(centerX,topY);
        path.lineTo(centerX-35*widths,topY+90);
        path.lineTo(centerX,topY+180);
        path.lineTo(centerX+35*widths,topY+90);
        path.lineTo(centerX,topY);
        canvas.drawPath(path,shapePaint);
        canvas.drawText(content,centerX-widths*20,bottomY-65,textPaint);
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
    public void set(String property, String value) {
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
    public int getTopY() {
        return topY;
    }

    @Override
    public boolean canDraw() {
        return true;
    }
}
