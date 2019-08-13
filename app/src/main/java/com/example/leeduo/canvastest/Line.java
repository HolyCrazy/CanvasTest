package com.example.leeduo.canvastest;

import android.graphics.Canvas;
import android.graphics.Paint;

/**
 * Created by LeeDuo on 2018/11/25.
 * 箭头线类
 */

public class Line extends Shape {
    private String content="",name,direction = "down",head="true";
    private int centerX,topY,leftX,rightX,bottomY;
    private int widths;

    public Line(int centerX,int topY){
        this.centerX = centerX;
        this.topY = topY;

    }
    @Override
    public void draw(Canvas canvas, Paint shapePaint, Paint textPaint) {
        //设置默认长度
        if(content == null || content.equals("")){
            widths = 4;
        }else{
            widths = TaskUtils.getLength(content);
            if(widths<4){
                widths = 4;
            }
        }
        leftX = centerX;
        rightX = centerX;
        bottomY = topY +70;
        textPaint.setTextSize(40);
        //根据位置绘制
        switch (direction){
            case "down":
                canvas.drawLine(centerX,topY,centerX,bottomY,shapePaint);
                //是否允许画箭头
                if(head.equals("true")){
                    canvas.drawLine(centerX,bottomY,centerX-20,bottomY -20,shapePaint);
                    canvas.drawLine(centerX,bottomY,centerX+20,bottomY -20,shapePaint);
                }
                canvas.drawText(content,centerX+5,topY+40,textPaint);
                break;
            case "up":
                canvas.drawLine(centerX,topY-70,centerX,topY,shapePaint);
                if(head.equals("true")){
                    canvas.drawLine(centerX,topY-70,centerX-20,topY -50,shapePaint);
                    canvas.drawLine(centerX,topY-70,centerX+20,topY -50,shapePaint);
                }
                canvas.drawText(content,centerX+5,topY-20,textPaint);
                leftX = centerX;
                rightX = centerX;
                bottomY = topY;
                topY = bottomY-70;
                break;
            case "left":
                canvas.drawLine(centerX,topY,centerX-25*widths,topY,shapePaint);
                if(head.equals("true")){
                    canvas.drawLine(centerX-25*widths,topY,centerX-25*widths+20,topY -20,shapePaint);
                    canvas.drawLine(centerX-25*widths,topY,centerX-25*widths+20,topY +20,shapePaint);
                }
                canvas.drawText(content,centerX-20*widths,topY-10,textPaint);
                leftX = centerX-25*widths;
                rightX = centerX;
                bottomY = topY;
                break;
            case "right":
                canvas.drawLine(centerX,topY,centerX+25*widths,topY,shapePaint);
                if(head.equals("true")){
                    canvas.drawLine(centerX+25*widths,topY,centerX+25*widths-20,topY -20,shapePaint);
                    canvas.drawLine(centerX+25*widths,topY,centerX+25*widths-20,topY +20,shapePaint);
                }
                canvas.drawText(content,centerX+2*widths,topY-10,textPaint);
                leftX = centerX;
                rightX = centerX+25*widths;
                bottomY = topY;
                break;
        }

        textPaint.setTextSize(80);

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

    public void setHead(String head){
        this.head = head;
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
            case "direction":
                setDirection(value);
                break;
            case "head":
                if(value.equals("false")||value.equals("true")){
                    setHead(value);
                }
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

    public String getDirection() {
        return direction;
    }

    public void setDirection(String direction) {
        this.direction = direction;
    }
    @Override
    public boolean canDraw() {
        return true;
    }
    @Override
    public int getTopY() {
        return topY;
    }
    @Override
    public void setNextPosition(Location location){
        //设置下一个绘制的位置并改变方向
        switch(direction){
            case "down":
                location.setY(bottomY);
                location.setDrawDirection("down");
                break;
            case "up":
                location.setY(topY);
                location.setDrawDirection("up");
                break;
            case "left":
                location.setX(leftX);
                location.setDrawDirection("left");
                break;
            case "right":
                location.setX(rightX);
                location.setDrawDirection("right");
                break;
        }
    }
}
