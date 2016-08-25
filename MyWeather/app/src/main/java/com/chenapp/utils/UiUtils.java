package com.chenapp.utils;

import android.content.Context;

import com.chenapp.myweather.MyApplication;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by Administrator on 2016/8/24 0024.
 */
public class UiUtils {
    public static Context getContext(){
        return MyApplication.getContext();
    }
    public static String getTime(){

        return new SimpleDateFormat("MM月dd日").format(new Date())+","
                +new SimpleDateFormat("EEEE").format(new Date())+","
                +new SimpleDateFormat("HH:mm").format(new Date());
    }
    public static boolean getIsFirst(String key,boolean value){
        return getContext().getSharedPreferences("config",Context.MODE_PRIVATE).getBoolean(key,value);
    }
    public static void putIsFirst(String key,boolean value){
        getContext().getSharedPreferences("config",Context.MODE_PRIVATE).edit().putBoolean(key,value).commit();
    }
    public static String getWeatherInfo(String key,String value){
        return getContext().getSharedPreferences("config",Context.MODE_PRIVATE).getString(key,value);
    }
    public static void putWeatherInfo(String key,String value){
        getContext().getSharedPreferences("config",Context.MODE_PRIVATE).edit().putString(key,value).commit();
    }
    public static int getTime(String key,int value){
        return getContext().getSharedPreferences("config",Context.MODE_PRIVATE).getInt(key,value);
    }
    public static void putTime(String key,int value){
        getContext().getSharedPreferences("config",Context.MODE_PRIVATE).edit().putInt(key,value).commit();
    }
}
