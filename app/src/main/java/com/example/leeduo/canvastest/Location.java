package com.example.leeduo.canvastest;

import android.content.Context;

/**
 * Created by LeeDuo on 2018/11/25.
 * 绘制时的位置信息：绘制的方向，绘制的最大长度，最大宽度，绘制的位置
 */

public class Location {

    private int X,Y,maxX,maxY,minX;
    private String drawDirection, locationPoint;

    public Location(Context context){
        //初始化时x,y坐标
        X = TaskUtils.getScreenWidth(context);
        Y = 100;
        //底部y的最大值
        maxY = 100;
        //右边x的最大值
        maxX = 100;
        //左边x的最大值
        minX = X;
        //绘制方向
        drawDirection = "down";
        //是否显示绘制的坐标点
        locationPoint = "true";
    }
    //清空当前，重新设置
    public void clear(Context context){
        drawDirection = "down";
        X = TaskUtils.getScreenWidth(context);
        Y = 100;
        maxY = 100;
        maxX = 100;
        minX = X;
        locationPoint = "true";
    }
    //获取是否可以绘制位置点
    public String getLocationPoint() {
        return locationPoint;
    }
    //设置绘制位置点
    public void setLocationPoint(String locationPoint) {
        this.locationPoint = locationPoint;
    }
    //获取绘制方向
    public String getDrawDirection() {
        return drawDirection;
    }
    //设置绘制方向
    public void setDrawDirection(String drawDirection) {
        this.drawDirection = drawDirection;
    }
    //获取和设置下一个要绘制的x坐标
    public int getX() {
        return X;
    }

    public void setX(int x) {
        X = x;
    }
    //获取和设置下一个要绘制的Y坐标
    public int getY() {
        return Y;
    }

    public void setY(int y) {
        Y = y;
    }
    //获取最大右边X值
    public int getMaxX() {
        return maxX;
    }
    //获取最大左边X值
    public int getMinX() {
        return minX;
    }
    //设置
    public void setMinX(int minX) {
        if(minX<this.minX){
            this.minX = minX;
        }
    }
    //设置
    public void setMaxX(int maxX) {
        if(maxX>this.maxX){
            this.maxX = maxX;
        }
    }
    //获取最底部Y值
    public int getMaxY() {
        return maxY;
    }
    //设置
    public void setMaxY(int maxY) {
        if(maxY>this.maxY){
            this.maxY = maxY;
        }
    }
}
