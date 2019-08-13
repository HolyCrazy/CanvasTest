package com.example.leeduo.canvastest;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by LeeDuo on 2018/11/25.
 * 将属性拆分成键值模式
 */

public class PropertyParser {
    private Map<String,String> propertyMap;
    private String[] propertys,property;
    private int i=0;

    public PropertyParser(){
        propertyMap = new HashMap<>();
    }
    //设置属性组，拆分成键值对
    public void setSingleProperty(String singleProperty){
        propertys = singleProperty.split(",");
    }
    //解析属性，添加到map中，无顺序
    public Map<String,String> parseProperty(){
        propertyMap.clear();
        for(i=0;i<propertys.length;i++){
            property = propertys[i].split(":");
            if(property.length == 2){
                propertyMap.put(property[0].trim(),property[1]);
            }
        }
        return propertyMap;
    }
}
