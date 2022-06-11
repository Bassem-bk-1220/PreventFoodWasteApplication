package com.example.projetlicence.Ui;

import android.content.Context;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;

public class MemoryData {
    public static void saveData(String data, Context context){
        try{
            FileOutputStream fileOutputStream=context.openFileOutput("data.txt",Context.MODE_PRIVATE);
            fileOutputStream.write(data.getBytes());
            fileOutputStream.close();

        }catch (IOException e){
            e.printStackTrace();
        }
    }

    public static void saveLastMsgTS(String data,String ChatId, Context context){
        try{
            FileOutputStream fileOutputStream=context.openFileOutput(ChatId+".txt",Context.MODE_PRIVATE);
            fileOutputStream.write(data.getBytes());
            fileOutputStream.close();

        }catch (IOException e){
            e.printStackTrace();
        }
    }

    public static String getData(Context context){
        String data="";
        try {
            FileInputStream fileInputStream=context.openFileInput("data.txt");
            InputStreamReader isr=new InputStreamReader(fileInputStream);
            BufferedReader bufferedReader=new BufferedReader(isr);
            StringBuilder sb=new StringBuilder();
            String line;
            while ((line= bufferedReader.readLine())!=null){
                sb.append(line);
            }
            data=sb.toString();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return data;
    }

    public static String getLastMsgTS(Context context, String ChatId){
        String data="0";
        try {
            FileInputStream fileInputStream=context.openFileInput(ChatId+".txt");
            InputStreamReader isr=new InputStreamReader(fileInputStream);
            BufferedReader bufferedReader=new BufferedReader(isr);
            StringBuilder sb=new StringBuilder();
            String line;
            while ((line= bufferedReader.readLine())!=null){
                sb.append(line);
            }
            data=sb.toString();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return data;
    }

}
