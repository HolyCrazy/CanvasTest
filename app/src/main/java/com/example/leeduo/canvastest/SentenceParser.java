package com.example.leeduo.canvastest;
import java.util.ArrayList;
/**
 * Created by LeeDuo on 2018/11/25.
 * 通过分号解析成语句
 * arrayList返回注释语句和可以执行的语句
 */

public class SentenceParser {
    private String[] sentences;
    private int length;
    private int i = 0;
    private ArrayList<String> noteArray,codeArray;

    public SentenceParser(){
        noteArray = new ArrayList<>();
        codeArray = new ArrayList<>();
    }
    //设置语句数组
    public void setSentences(String[] sentences){
        if(sentences != null){
            this.sentences = sentences;
            length = sentences.length;
        }
    }
    //将句子字符串简单按分号拆分，同时去掉回车
    public static String[] codeInOrder(String string){
        string = string.replace("\n","").replace("\r","");
        if(!string.equals("")){
            return string.split(";");
        }else {
            return null;
        }
    }
    //解析句子
    public void parseSentences(){
            noteArray.clear();
            codeArray.clear();
        //防止空
        noteArray.add("begin");
        codeArray.add("begin");
        for(i=0;i<length;i++){
             if(sentences[i].indexOf("#") == 0){
                 noteArray.add(sentences[i]);
             }else{
                 codeArray.add(sentences[i]);
             }
        }
        //防止空
        noteArray.add("end");
        codeArray.add("end");
    }
    //返回的arrayList
    public ArrayList getCode(){
        return codeArray;
    }
    public ArrayList getNote(){
        return noteArray;
    }
}
