package com.example.leeduo.canvastest;

import android.graphics.Canvas;
import android.graphics.Paint;

import java.util.Map;

/**
 * Created by LeeDuo on 2018/11/25.
 * 父类 形状
 */

public abstract class Shape {
    private String name,content;
    //绘制
    public  void draw(Canvas canvas, Paint shapePaint, Paint textPaint){}
    //文字内容
    public String getContent(){
        return content;
    }
    public void setContent(String content){}
    //名字
    public String getName(){
        return  name;
    }
    public  void setName(String name){}
    //在set中实现对类的属性的设置
    public abstract void set(String property,String value);
    //获取底部y坐标
    public abstract int getBottomY();
    //获取最右端x坐标
    public abstract int getRightX();
    //获取最左端x坐标
    public abstract int getLeftX();
    //获取顶部y坐标
    public abstract int getTopY();
    //能否被绘制
    public abstract boolean canDraw();
    //改变绘制的坐标
    public void findLocation(Location location, Map<Integer, Shape> map) {}
    //设置下一个绘制的位置坐标
    public void setNextPosition(Location location){}
}
