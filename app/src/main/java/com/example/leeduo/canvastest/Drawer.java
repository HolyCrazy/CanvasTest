package com.example.leeduo.canvastest;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by LeeDuo on 2018/11/25.
 * 绘制类
 */

public class Drawer {
    private ArrayList<String> nameArray;
    private ArrayList<String> propertyArray;
    private PropertyParser propertyParser;
    private int i;
    private Map<String,String> map;
    private Shape shape;
    private PaintBox paintBox;
    private Map<Integer,Shape> logMap;


    public Drawer(){
        propertyParser = new PropertyParser();
        paintBox = new PaintBox();
        logMap = new HashMap<>();
        map = new HashMap<>();
    }
    //设置类名List和属性组List
    public void setArray(ArrayList<String> nameArray,ArrayList<String> propertyArray){
        this.nameArray = nameArray;
        this.propertyArray = propertyArray;
    }
    //绘图
    public void drawPicture(Canvas canvas, Context context){
        //对location初始化，清空设置
        if(ShapeFactory.getLocation() != null){
            ShapeFactory.getLocation().clear(context);
        }
        paintBox.clear();
        logMap.clear();
        map.clear();
        //绘制默认背景
        canvas.drawColor(Color.parseColor("#ffffff"));
        for(i=0;i<nameArray.size();i++){
            //根据类名字获取类对象
            shape = ShapeFactory.getShape(nameArray.get(i),context);
            //如果获取到了
            if(shape != null){
                //为对象设置属性
                propertyParser.setSingleProperty(propertyArray.get(i));
                map = propertyParser.parseProperty();
                for(String s:map.keySet()){
                    shape.set(s,map.get(s));
                }
                //开始绘制
                try {
                    //判断能否绘制
                    if(shape.canDraw()){//能绘制
                        //绘制
                        shape.draw(canvas,paintBox.getShapePaint(),paintBox.getTextPaint());
                        //设置下一个绘制的位置
                        shape.setNextPosition(ShapeFactory.getLocation());
                        //告诉location占用的位置
                        ShapeFactory.getLocation().setMaxY(shape.getBottomY());
                        ShapeFactory.getLocation().setMaxX(shape.getRightX());
                        ShapeFactory.getLocation().setMinX(shape.getLeftX());
                        //保存对象
                        logMap.put(i,shape);
                    }else{//不能绘制
                        //改变绘制的位置
                        shape.findLocation(ShapeFactory.getLocation(),logMap);
                    }
                }catch (Exception e){

                }
            }
        }
        //绘制位置提示点
        if(ShapeFactory.getLocation()!=null){
            //允许的话
            if(!ShapeFactory.getLocation().getLocationPoint().equals("false")){
                //画点
                canvas.drawCircle(ShapeFactory.getLocation().getX(),ShapeFactory.getLocation().getY(),5,paintBox.getPositionPaint());
            }
        }

    }
}
