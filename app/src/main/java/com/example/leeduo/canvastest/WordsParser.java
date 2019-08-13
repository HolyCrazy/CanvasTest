package com.example.leeduo.canvastest;
import java.util.ArrayList;
/**
 * Created by LeeDuo on 2018/11/25.
 * 将可执行的语句解析成对应的类和属性组
 */

public class WordsParser {

    private int i =0;
    private String[] words,codeSentences;
    private String singleSentence;
    private ArrayList<String> nameArray,propertyArray;
    public WordsParser(){
        nameArray = new ArrayList<>();
        propertyArray = new ArrayList<>();
    }
    //设置arrayList，并转换成string数组
    public void setCodeArray(ArrayList<String> codeArray){
        if(!codeArray.isEmpty()){
            codeSentences = new String[codeArray.size()];
            for(i=0;i<codeArray.size();i++){
                codeSentences[i]=codeArray.get(i);
            }
        }
    }
    //解析
    public void parseWords(){
            nameArray.clear();
            propertyArray.clear();

            for(i=0;i<codeSentences.length;i++){
                //去掉左右中括号
                singleSentence = codeSentences[i].replace("[","").replace("]","");
                //拆成类和属性两部分
                words = singleSentence.split("=");
                //正常情况
                if(words.length == 2){
                    //去掉空格
                    nameArray.add(words[0].trim());
                    propertyArray.add(words[1].trim());
                }else{
                    //防止空，不执行
                    if(words[0].equals("begin")||words[0].equals("end")){

                    }else{
                        //不正常情况清空，并添加错误信息
                        nameArray.clear();
                        propertyArray.clear();
                        nameArray.add("error");
                        propertyArray.add("error");
                        break;
                    }
                }
            }

    }
    //返回ArrayList
    public ArrayList<String> getNameArray() {
        return nameArray;
    }
    public ArrayList<String> getPropertyArray() {
        return propertyArray;
    }
}
