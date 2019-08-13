package com.example.leeduo.canvastest;

import android.content.ContentResolver;
import android.content.ContentUris;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Build;
import android.os.Environment;
import android.provider.DocumentsContract;
import android.provider.MediaStore;
import android.support.v4.content.FileProvider;
import android.view.Display;
import android.view.WindowManager;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * Created by LeeDuo on 2018/11/23.
 */

public class TaskUtils {
    private  static WindowManager windowManager;
    private  static Display display;
    private  static int valueLength;
    private  static String chinese,temp;
    //获取屏幕宽度
    public static int getScreenWidth(Context context){
         windowManager = (WindowManager) context.getSystemService(Context.WINDOW_SERVICE);
         display = windowManager.getDefaultDisplay();
        return display.getWidth();
    }
    //获取屏幕高度
    public static int getScreenHeight(Context context){
        windowManager = (WindowManager) context.getSystemService(Context.WINDOW_SERVICE);
        display = windowManager.getDefaultDisplay();
        return display.getHeight();
    }
    //获取文字长度
    public static int getLength(String s) {
        valueLength = 0;
        chinese = "[\u0391-\uFFE5]";
        for (int i = 0; i < s.length(); i++) {
            temp = s.substring(i, i + 1);
            if (temp.matches(chinese)) {
                valueLength += 2;
            } else {
                valueLength += 1;
            }
        }
        return valueLength;
    }
    //保存图片，返回路径
    public static  String saveBitmap(Context context,Bitmap bitmap){
        String SD_PATH ="/sdcard/程序框图/截图/截图";
        String IN_PATH ="/程序框图/截图/截图";
        String savepath;
        File file;
        if(Environment.getExternalStorageState().equals(Environment.MEDIA_MOUNTED)){
            savepath = SD_PATH;
        }else {
            savepath =context.getApplicationContext().getFilesDir().getAbsolutePath()+IN_PATH;
        }
        file = new File(savepath+ new Random().nextInt(100000)+".jpg");
        if(!file.exists()){
            file.getParentFile().mkdirs();
            try {
                file.createNewFile();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        try {
            FileOutputStream fileOutputStream  = new FileOutputStream(file);
            bitmap.compress(Bitmap.CompressFormat.JPEG,50,fileOutputStream);
            try {
                fileOutputStream.flush();
            } catch (IOException e) {
                e.printStackTrace();
            }
            try {
                fileOutputStream.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        return file.getAbsolutePath();

    }
    //保存文件，返回路径
    public static String saveFile(Context context,String code){
        String SD_PATH ="/sdcard/程序框图/代码/代码";
        String IN_PATH ="/程序框图/代码/代码";
        String savepath;
        File file;
        byte[] bytes;
        if(Environment.getExternalStorageState().equals(Environment.MEDIA_MOUNTED)){
            savepath = SD_PATH;
        }else {
            savepath =context.getApplicationContext().getFilesDir().getAbsolutePath()+IN_PATH;
        }
        file = new File(savepath+ new Random().nextInt(100000)+".ptxt");
        if(!file.exists()){
            file.getParentFile().mkdirs();
            try {
                file.createNewFile();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        try {
            FileOutputStream fileOutputStream  = new FileOutputStream(file);
            bytes = code.getBytes();
            fileOutputStream.write(bytes);
            try {
                fileOutputStream.flush();
            } catch (IOException e) {
                e.printStackTrace();
            }
            try {
                fileOutputStream.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return file.getAbsolutePath();
    }
    //获取文件，返回Intent
    public static Intent getFile(Context context){
        String SD_PATH ="/sdcard/程序框图/代码/";
        String IN_PATH ="/程序框图/代码/";
        String savepath;
        Intent intent;
        File file;
        if(Environment.getExternalStorageState().equals(Environment.MEDIA_MOUNTED)){
            savepath = SD_PATH;
        }else {
            savepath =context.getApplicationContext().getFilesDir().getAbsolutePath()+IN_PATH;
        }
        file = new File(savepath);
        if(!file.exists()){
            file.getParentFile().mkdirs();
            try {
                file.createNewFile();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        Uri uri = FileProvider.getUriForFile(context,"com.example.leeduo.canvastest.fileProvider",file);
        intent = new Intent(Intent.ACTION_GET_CONTENT);
        intent.setDataAndType(uri,"*/*");
        intent.addCategory(Intent.CATEGORY_OPENABLE);
        return intent;
    }

    //获取文件真实路径
    public static String getRealPathFromUriBelowAPI19(Context context, Uri contentUri) {
        String res = null;
        String[] proj = { MediaStore.Images.Media.DATA };
        Cursor cursor = context.getContentResolver().query(contentUri, proj, null, null, null);
        if(null!=cursor&&cursor.moveToFirst()){;
            int column_index = cursor.getColumnIndexOrThrow(MediaStore.Images.Media.DATA);
            res = cursor.getString(column_index);
            cursor.close();
        }
        return res;
    }
    public static String getRealPathFromUriAboveApi19(Context context,Uri uri) {
        String path = null;
        // 以 file:// 开头的
        if (ContentResolver.SCHEME_FILE.equals(uri.getScheme())) {
            path = uri.getPath();
            return path;
        }
        // 以 content:// 开头的，比如 content://media/extenral/images/media/17766
        if (ContentResolver.SCHEME_CONTENT.equals(uri.getScheme()) && Build.VERSION.SDK_INT < Build.VERSION_CODES.KITKAT) {
            Cursor cursor = context.getContentResolver().query(uri, new String[]{MediaStore.Images.Media.DATA}, null, null, null);
            if (cursor != null) {
                if (cursor.moveToFirst()) {
                    int columnIndex = cursor.getColumnIndexOrThrow(MediaStore.Images.Media.DATA);
                    if (columnIndex > -1) {
                        path = cursor.getString(columnIndex);
                    }
                }
                cursor.close();
            }
            return path;
        }
        // 4.4及之后的 是以 content:// 开头的，比如 content://com.android.providers.media.documents/document/image%3A235700
        if (ContentResolver.SCHEME_CONTENT.equals(uri.getScheme()) && Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            if (DocumentsContract.isDocumentUri(context, uri)) {
                if (isExternalStorageDocument(uri)) {
                    // ExternalStorageProvider
                    final String docId = DocumentsContract.getDocumentId(uri);
                    final String[] split = docId.split(":");
                    final String type = split[0];
                    if ("primary".equalsIgnoreCase(type)) {
                        //获取内置存储卡
                        path = Environment.getExternalStorageDirectory() + "/" + split[1];
                        return path;
                    }else if("1C28-DBE0".equalsIgnoreCase(type)) {
                        //获取外置存储卡
                        List<String> extSDCardPath = getExtSDCardPath();
                        if(extSDCardPath!=null&&extSDCardPath.size()>0) {
                            return extSDCardPath.get(0)+ "/" + split[1];
                        }
                    }
                } else if (isDownloadsDocument(uri)) {
                    // DownloadsProvider
                    final String id = DocumentsContract.getDocumentId(uri);
                    final Uri contentUri = ContentUris.withAppendedId(Uri.parse("content://downloads/public_downloads"),
                            Long.valueOf(id));
                    path = getDataColumn(context, contentUri, null, null);
                    return path;
                } else if (isMediaDocument(uri)) {
                    // MediaProvider
                    final String docId = DocumentsContract.getDocumentId(uri);
                    final String[] split = docId.split(":");
                    final String type = split[0];
                    Uri contentUri = null;
                    if ("image".equals(type)) {
                        contentUri = MediaStore.Images.Media.EXTERNAL_CONTENT_URI;
                    } else if ("video".equals(type)) {
                        contentUri = MediaStore.Video.Media.EXTERNAL_CONTENT_URI;
                    } else if ("audio".equals(type)) {
                        contentUri = MediaStore.Audio.Media.EXTERNAL_CONTENT_URI;
                    }
                    final String selection = "_id=?";
                    final String[] selectionArgs = new String[]{split[1]};
                    path = getDataColumn(context, contentUri, selection, selectionArgs);
                    return path;
                }
            }
        }
        return null;
    }
    private static String getDataColumn(Context context, Uri uri, String selection, String[] selectionArgs) {
        Cursor cursor = null;
        final String column = "_data";
        final String[] projection = {column};
        try {
            cursor = context.getContentResolver().query(uri, projection, selection, selectionArgs, null);
            if (cursor != null && cursor.moveToFirst()) {
                final int column_index = cursor.getColumnIndexOrThrow(column);
                return cursor.getString(column_index);
            }
        } finally {
            if (cursor != null)
                cursor.close();
        }
        return null;
    }
    private static boolean isExternalStorageDocument(Uri uri) {
        return "com.android.externalstorage.documents".equals(uri.getAuthority());
    }
    private static boolean isDownloadsDocument(Uri uri) {
        return "com.android.providers.downloads.documents".equals(uri.getAuthority());
    }
    private static boolean isMediaDocument(Uri uri) {
        return "com.android.providers.media.documents".equals(uri.getAuthority());
    }

    /**
     * 获取外置SD卡路径
     * @return  应该就一条记录或空
     */
    public static List<String> getExtSDCardPath(){
        List<String> lResult = new ArrayList<>();
        try {
            Runtime rt = Runtime.getRuntime();
            Process proc = rt.exec("mount");
            InputStream is = proc.getInputStream();
            InputStreamReader isr = new InputStreamReader(is);
            BufferedReader br = new BufferedReader(isr);
            String line;
            while ((line = br.readLine()) != null) {
                if (line.contains("extSdCard"))
                {
                    String [] arr = line.split(" ");
                    String path = arr[1];
                    File file = new File(path);
                    if (file.isDirectory())
                    {
                        lResult.add(path);
                    }
                }
            }
            isr.close();
        } catch (Exception e) {
        }
        return lResult;
    }
}
