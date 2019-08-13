package com.example.leeduo.canvastest;


import java.util.Map;

/**
 * Created by LeeDuo on 2018/11/26.
 */

public class Left extends Direction {

    private String name;
    private Shape shape;

    @Override
    public void setWhere(String name) {
        this.name = name;
    }

    @Override
    public void findLocation(Location location, Map<Integer, Shape> map) {
        for (int i:map.keySet()) {
            if(map.get(i).getName()!=null&&map.get(i).getName().equals(name)){
                shape = map.get(i);
                location.setX(shape.getLeftX());
                location.setY(shape.getBottomY()/2 + shape.getTopY()/2);
                break;
            }
        }

    }

    @Override
    public void set(String property, String value) {
        switch (property){
            case "where":
                setWhere(value);
                break;
        }
    }

    @Override
    public boolean canDraw() {
        return false;
    }
}
