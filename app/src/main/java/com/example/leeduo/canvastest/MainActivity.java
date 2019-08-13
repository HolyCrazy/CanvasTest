package com.example.leeduo.canvastest;

import android.Manifest;
import android.app.Activity;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

public class MainActivity extends AppCompatActivity implements View.OnTouchListener {

    private ImageView imageView;
    private EditText codeEdit;
    private Bitmap bitmap, myBitmap;
    private Canvas canvas;
    private int screenWidth, screenHeight, xLength;
    private Button runButton, saveButton, cutButton, intoButton;
    private String[] sentences, temp;
    private String code;
    private SentenceParser sParser;
    private WordsParser wParser;
    private Drawer drawer;
    private String filePath;
    private Intent intent;
    private int RESULT_LOAD_FILE = 10101;
    private Uri uri;
    private File file;
    private FileInputStream fis;
    private int len;
    private byte[] bytes;
    private int hasWriteStoragePermission;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        imageView = (ImageView) findViewById(R.id.my_image);
        runButton = (Button) findViewById(R.id.runButton);
        cutButton = (Button) findViewById(R.id.cutBtn);
        saveButton = (Button) findViewById(R.id.saveBtn);
        intoButton = (Button) findViewById(R.id.intoBtn);
        codeEdit = (EditText) findViewById(R.id.codeEdit);
        screenWidth = TaskUtils.getScreenWidth(this);
        screenHeight = TaskUtils.getScreenHeight(this);
        sParser = new SentenceParser();
        wParser = new WordsParser();
        drawer = new Drawer();
        bitmap = Bitmap.createBitmap(screenWidth * 2, screenHeight * 7 / 6, Bitmap.Config.RGB_565);
        canvas = new Canvas(bitmap);
        intent = new Intent();
        hasWriteStoragePermission = ContextCompat.checkSelfPermission(this, Manifest.permission.WRITE_EXTERNAL_STORAGE);
        if (hasWriteStoragePermission != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE}, 101);
        }

        runButton.setOnTouchListener(this);
        intoButton.setOnTouchListener(this);
        saveButton.setOnTouchListener(this);
        cutButton.setOnTouchListener(this);


    }

    private boolean isOutterUp(MotionEvent event,View view){
        float touchX = event.getX();
        float touchY = event.getY();
        float maxX = view.getWidth();
        float maxY = view.getHeight();

        return touchX<0||touchX>maxX||touchY<0||touchY>maxY;
    }

    @Override
    public boolean onTouch(View v, MotionEvent event) {
            switch (v.getId()) {
                case R.id.intoBtn:
                    if (event.getAction() == MotionEvent.ACTION_DOWN) {
                        intoButton.setBackgroundColor(Color.parseColor("#df3F51B5"));
                    } else if (event.getAction() == MotionEvent.ACTION_UP) {
                        intoButton.setBackgroundColor(Color.parseColor("#3F51B5"));
                        if(isOutterUp(event,v)){
                            event.setAction(MotionEvent.ACTION_CANCEL);
                            return onTouch(v,event);
                        }
                            intent = TaskUtils.getFile(this);
                            try {
                                startActivityForResult(intent, RESULT_LOAD_FILE);
                            } catch (Exception e) {
                                Toast.makeText(this, "导入失败", Toast.LENGTH_SHORT).show();
                            }
                    }

                    break;
                case R.id.saveBtn:
                    if (event.getAction() == MotionEvent.ACTION_DOWN) {
                        saveButton.setBackgroundColor(Color.parseColor("#df3F51B5"));
                    } else if (event.getAction() == MotionEvent.ACTION_UP) {
                        saveButton.setBackgroundColor(Color.parseColor("#3F51B5"));
                        if(isOutterUp(event,v)){
                            event.setAction(MotionEvent.ACTION_CANCEL);
                            return onTouch(v,event);
                        }
                        try {
                            code = codeEdit.getText().toString();
                            filePath = TaskUtils.saveFile(this, code);
                            Toast.makeText(this, "保存成功" + filePath, Toast.LENGTH_SHORT).show();
                        } catch (Exception e) {
                            Toast.makeText(this, "保存失败", Toast.LENGTH_SHORT).show();
                        }

                    }
                    break;
                case R.id.cutBtn:
                    if (event.getAction() == MotionEvent.ACTION_DOWN) {
                        cutButton.setBackgroundColor(Color.parseColor("#df3F51B5"));
                    } else if (event.getAction() == MotionEvent.ACTION_UP) {
                        cutButton.setBackgroundColor(Color.parseColor("#3F51B5"));
                        if(isOutterUp(event,v)){
                            event.setAction(MotionEvent.ACTION_CANCEL);
                            return onTouch(v,event);
                        }
                        try {
                            xLength = Math.max(Math.abs(ShapeFactory.getLocation().getMinX() - screenWidth), Math.abs(ShapeFactory.getLocation().getMaxX() - screenWidth));
                            myBitmap = Bitmap.createBitmap(bitmap, screenWidth - xLength - 50, 0, xLength * 2 + 100, ShapeFactory.getLocation().getMaxY() + 50);
                            try {
                                filePath = TaskUtils.saveBitmap(this, myBitmap);
                                Toast.makeText(this, "截图成功" + filePath, Toast.LENGTH_SHORT).show();
                            } catch (Exception e) {
                                Toast.makeText(this, "截图失败1", Toast.LENGTH_SHORT).show();
                            }
                        } catch (Exception e) {
                            Toast.makeText(this, "截图失败0", Toast.LENGTH_SHORT).show();
                        }

                    }
                    break;
                case R.id.runButton:
                    if (event.getAction() == MotionEvent.ACTION_DOWN) {
                        runButton.setBackgroundColor(Color.parseColor("#df3F51B5"));
                    } else if (event.getAction() == MotionEvent.ACTION_UP) {
                        runButton.setBackgroundColor(Color.parseColor("#3F51B5"));
                        if(isOutterUp(event,v)){
                            event.setAction(MotionEvent.ACTION_CANCEL);
                            return onTouch(v,event);
                        }
                        code = codeEdit.getText().toString();
                        sentences = SentenceParser.codeInOrder(code);
                        sParser.setSentences(sentences);
                        sParser.parseSentences();
                        wParser.setCodeArray(sParser.getCode());
                        wParser.parseWords();
                        drawer.setArray(wParser.getNameArray(), wParser.getPropertyArray());
                        drawer.drawPicture(canvas, MainActivity.this);
                        imageView.setImageBitmap(bitmap);
                    }
                    break;
            }
        return false;
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == RESULT_LOAD_FILE && resultCode == RESULT_OK && null != data) {
            uri = data.getData();
            filePath = TaskUtils.getRealPathFromUriAboveApi19(this, uri);
            temp = filePath.split("\\.");
            if (temp[temp.length - 1].equals("ptxt")) {
                file = new File(filePath);
                try {
                    fis = new FileInputStream(file);
                    bytes = new byte[1024];
                    code = "";
                    while ((len = fis.read(bytes)) != -1) {
                        code = code + new String(bytes, 0, len);
                    }
                    fis.close();
                    codeEdit.setText("");
                    codeEdit.setText(code);
                } catch (FileNotFoundException e) {
                    e.printStackTrace();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            } else {
                Toast.makeText(this, "文件类型不正确", Toast.LENGTH_SHORT).show();
            }

        }
    }



    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (requestCode == 101){
            if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED){
            }else{
                MainActivity.this.finish();
            }
        }
    }
}
