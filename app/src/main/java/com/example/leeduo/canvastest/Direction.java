package com.example.leeduo.canvastest;

import java.util.Map;
/**
 * Created by LeeDuo on 2018/11/26.
 * 方向类的父类，四个子类 left right top bottom
 * @where:根据map跳到where的值处
 */

public class Direction extends Shape {

    protected String name;

    public void setWhere(String name){
        this.name = name;
    }

    @Override
    public void findLocation(Location location, Map<Integer,Shape> map){

    }

    @Override
    public String getContent() {
        return null;
    }

    @Override
    public void setContent(String content) {

    }

    @Override
    public String getName() {
        return null;
    }

    @Override
    public void setName(String name) {

    }

    @Override
    public void set(String property, String value) {
        switch (property){
            case "where":
                this.name = value;
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
        return 0;
    }

    @Override
    public int getTopY() {
        return 0;
    }

    @Override
    public boolean canDraw() {
        return false;
    }
}
