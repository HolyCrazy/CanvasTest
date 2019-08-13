package com.example.leeduo.canvastest;

import android.content.Context;

/**
 * Created by LeeDuo on 2018/11/25.
 */

public class ShapeFactory {
    private static Shape shape;
    private static Location location;
    //根据解析的类名，生成并返回类的对象
    public static Shape getShape(String s, Context context){
        //全局location对象
        if(location == null){
            location = new Location(context);
            location.setDrawDirection("down");
            location.setLocationPoint("true");
        }
        shape = null;
        switch (s){
            case "start":
                shape =new Start(location.getX(),location.getY());
                break;
            case "rect":
                shape=new Rect(location.getX(),location.getY());
                break;
            case "line":
                shape=new Line(location.getX(),location.getY());
                break;
            case "diamond":
                shape=new Diamond(location.getX(),location.getY());
                break;
            case "sideling":
                shape = new Sideling(location.getX(),location.getY());
                break;
            case "pause":
                shape = new Pause(location.getX(),location.getY());
                break;
            case "left":
                shape = new Left();
                //使用方向类会改变方向
                location.setDrawDirection("left");
                break;
            case "right":
                shape = new Right();
                location.setDrawDirection("right");
                break;
            case "top":
                shape = new Top();
                location.setDrawDirection("up");
                break;
            case "bottom":
                shape = new Bottom();
                location.setDrawDirection("down");
                break;
            case "stop":
                shape = new Stop(location.getX(),location.getY());
                //结束不显示位置提示点
                location.setLocationPoint("false");
                break;
            case "extend":
                shape = new Extend(location.getX(),location.getY());
                break;
            case"setting":
                shape = new Setting();
                break;
        }
        return shape;
    }
    //静态获取位置
    public static Location getLocation(){
        if(location != null){
            return location;
        }else {
            return null;
        }
    }
}
