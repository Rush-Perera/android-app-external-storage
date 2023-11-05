package com.rush.internalstorage;

import androidx.appcompat.app.AppCompatActivity;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.provider.Settings;
import android.util.Log;
import android.view.View;

import com.google.android.material.snackbar.Snackbar;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.nio.channels.FileChannel;
import java.util.Arrays;
import java.util.Properties;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = MainActivity.class.getName();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

//        // Getting all the files list
//        String[] list = fileList();
//        for (String s : list) {
//            Log.i(TAG,s);
//        }

//        try {
//            //Reading a file
//            FileInputStream input = openFileInput("A.txt");
//            InputStreamReader reader = new InputStreamReader(input);
//
//            char[] buffer = new char[1024];
//            StringBuilder builder = new StringBuilder();
//            int read;
//            while ((read = reader.read(buffer)) > 0){
//                String s = String.copyValueOf(buffer, 0, read);
//                builder.append(s);
//            }
//            reader.close();
//            Log.i(TAG,builder.toString());
//
//        }catch (FileNotFoundException e){
//           throw new RuntimeException();
//        } catch (IOException e) {
//            throw new RuntimeException(e);
//        }

//        Properties properties = new Properties();

//        try {
////            FileOutputStream stream = openFileOutput("app.txt", Context.MODE_PRIVATE);
////            OutputStreamWriter writer = new OutputStreamWriter(stream);
////            writer.write("Hello Java");
////            writer.close();
//
//            FileOutputStream stream = openFileOutput("app.data", Context.MODE_PRIVATE);
//
//            properties.put("name","Internal Storage App");
//            properties.put("version", "1.0");
//
//            properties.store(stream, "This is a comment");
//
//            stream.close();
//        } catch (FileNotFoundException e) {
//            throw new RuntimeException(e);
//        } catch (IOException e) {
//            throw new RuntimeException(e);
//        }

//        try {
//            FileInputStream stream = openFileInput("app.data");
//            properties.load(stream);
//        } catch (FileNotFoundException e) {
//            throw new RuntimeException(e);
//        } catch (IOException e) {
//            throw new RuntimeException(e);
//        }

//        //Get files directory
//        File file = getFilesDir();
//        Log.i(TAG,file.getName());

//        //File writing using file object
//        File file = new File(getFilesDir(), "xyz.txt");
//        try {
//            FileOutputStream fileOutputStream = new FileOutputStream(file);
//            OutputStreamWriter outputStreamWriter = new OutputStreamWriter(fileOutputStream);
//            outputStreamWriter.write("File writing using file object");
//            outputStreamWriter.close();
//            fileOutputStream.close();
//
//        } catch (FileNotFoundException e) {
//            throw new RuntimeException(e);
//        } catch (IOException e) {
//            throw new RuntimeException(e);
//        }

//        //Delete a file
//        File file = new File(getFilesDir(), "xyz.txt");
//        file.delete();


//        try {
//            FileChannel channel = openFileInput("A.txt").getChannel();
//            channel.transferTo();
//        } catch (FileNotFoundException e) {
//            throw new RuntimeException(e);
//        }

//        File[] mediaDirs = getExternalMediaDirs();
//        Log.i(TAG,mediaDirs[0].getPath());

        String state = Environment.getExternalStorageState();
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.R) {
            if(!Environment.isExternalStorageManager()) {
                Log.i(TAG, String.valueOf(Environment.isExternalStorageManager()));
                Snackbar.make(findViewById(R.id.container),"Permission needed",Snackbar.LENGTH_INDEFINITE)
                        .setAction("Settings", new View.OnClickListener() {
                            @Override
                            public void onClick(View view) {
                                Intent intent = new Intent();
                                intent.setAction(Settings.ACTION_MANAGE_ALL_FILES_ACCESS_PERMISSION);
                                startActivity(intent);
                            }
                        }).show();
            }
        }else {
            requestPermissions(new String[] {
                    Manifest.permission.WRITE_EXTERNAL_STORAGE
            }, 0);
        }

        if (Environment.MEDIA_MOUNTED.equals(state)) {
            File externalStorageDirectory = Environment.getExternalStorageDirectory();
            String[] list = externalStorageDirectory.list();
            for (String s : list) {
                Log.i(TAG,s);
            }
        }


    }
}