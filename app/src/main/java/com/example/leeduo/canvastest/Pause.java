package com.example.leeduo.canvastest;

import android.graphics.Canvas;
import android.graphics.Paint;

/**
 * Created by LeeDuo on 2018/11/30.
 */
//结束框
public class Pause extends Shape {

    private String name;
    private int centerX,topY,leftX,rightX,bottomY;
    private String finish = "true";
    boolean aBoolean = true;

    public Pause(int centerX,int topY){
        this.centerX = centerX;
        this.topY = topY;

    }
    @Override
    public void draw(Canvas canvas, Paint shapePaint, Paint textPaint){
        bottomY = topY +90;
        try {
            aBoolean = Boolean.valueOf(finish);
        }catch (Exception e){
            aBoolean = true;
        }
        ShapeFactory.getLocation().setLocationPoint(String.valueOf(aBoolean));
        switch (ShapeFactory.getLocation().getDrawDirection()){
            case "down":
                leftX = centerX -30;
                rightX = centerX+30;
                canvas.drawCircle(centerX,topY+30,30,shapePaint);
                break;
            case "up":
                bottomY = topY;
                topY = bottomY - 90;
                leftX = centerX -30;
                rightX = centerX+30;
                canvas.drawCircle(centerX,bottomY-30,30,shapePaint);
                break;
            case "left":
                canvas.drawCircle(centerX-30,topY,30,shapePaint);
                topY = topY -30;
                bottomY = topY +60;
                leftX = centerX -90;
                rightX = centerX;
                break;
            case "right":
                canvas.drawCircle(centerX+30,topY,30,shapePaint);
                topY = topY-30;
                bottomY = topY+60;
                leftX = centerX;
                rightX = centerX+90;
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
    public String getName() {
        return name;
    }
    @Override
    public void setName(String name) {
        this.name = name;
    }

    public String getFinish() {
        return finish;
    }

    public void setFinish(String finish) {
        this.finish = finish;
    }

    @Override
    public void set(String property,String value) {
        switch (property){
            case "finish":
                if(value.equals("true")){
                    setFinish("false");
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
    public boolean canDraw() {
        return true;
    }
    @Override
    public int getTopY() {
        return topY;
    }
}
