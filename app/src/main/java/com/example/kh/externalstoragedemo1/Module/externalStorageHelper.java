package com.example.kh.externalstoragedemo1.Module;

import android.os.Environment;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * Created by kh on 4/26/2017.
 */

public class externalStorageHelper {
    private  static  externalStorageHelper external;
    public static  externalStorageHelper getInstance(){
        if(external==null)
            external = new externalStorageHelper();
        return external;
    }

    public Boolean isExternalStorage(){
        if(Environment.MEDIA_MOUNTED.equals(Environment.getExternalStorageState())|| Environment.MEDIA_MOUNTED_READ_ONLY.equals(Environment.getExternalStorageState()))
        return true;
        else
            return false;
    }
        private static  File dir = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS);
    public void setData(String filename, String data) throws IOException {
        if ((isExternalStorage())) {
            File f = new File(dir, filename);
            if (!f.exists())
                f.createNewFile();
            FileOutputStream fileOutputStream = new FileOutputStream(f);
            fileOutputStream.write(data.getBytes());
            fileOutputStream.close();
        }
    }

    public String getData(String fileName) throws IOException {
        if(isExternalStorage()) {
            File f = new File(dir, fileName);
            FileInputStream fileInputStream = new FileInputStream(f);
            InputStreamReader inputStreamReader = new InputStreamReader(fileInputStream);
            BufferedReader bufferedReader = new BufferedReader(inputStreamReader);
            String line;
            StringBuffer stringBuffer = new StringBuffer();
            while ((line = bufferedReader.readLine()) != null) {
                stringBuffer.append(line + "\n");
            }
            return stringBuffer.toString();
        }
        else
            return null;
    }
    public boolean deleteData(String fileName){

            File f = new File(dir, fileName);
            return f.delete();

    }
}
