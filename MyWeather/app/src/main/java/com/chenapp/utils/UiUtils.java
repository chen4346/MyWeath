package com.chenapp.utils;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

import com.chenapp.bean.pccBean;
import com.chenapp.myweather.MyApplication;
import com.chenapp.myweather.R;
import com.google.gson.Gson;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by Administrator on 2016/8/24 0024.
 */
public class UiUtils {
    public static Context getContext(){
        return MyApplication.getContext();
    }
    public static String getTime(Long time){
        if(time == 0){
            return new SimpleDateFormat("MM月dd日").format(new Date())+","
                    +new SimpleDateFormat("EEEE").format(new Date())+","
                    +new SimpleDateFormat("HH:mm").format(new Date());
        }else{
            return new SimpleDateFormat("MM月dd日").format(new Date(time))+","
                    +new SimpleDateFormat("EEEE").format(new Date(time))+","
                    +new SimpleDateFormat("HH:mm").format(new Date(time));
        }

    }
    public static boolean getBoolean(String key, boolean value){
        return getContext().getSharedPreferences("config",Context.MODE_PRIVATE).getBoolean(key,value);
    }
    public static void putBoolean(String key, boolean value){
        getContext().getSharedPreferences("config",Context.MODE_PRIVATE).edit().putBoolean(key,value).commit();
    }
    public static String getString(String key, String value){
        return getContext().getSharedPreferences("config",Context.MODE_PRIVATE).getString(key,value);
    }
    public static void putString(String key, String value){
        getContext().getSharedPreferences("config",Context.MODE_PRIVATE).edit().putString(key,value).commit();
    }
    public static int getInt(String key, int value){
        return getContext().getSharedPreferences("config",Context.MODE_PRIVATE).getInt(key,value);
    }
    public static void putInt(String key, int value){
        getContext().getSharedPreferences("config",Context.MODE_PRIVATE).edit().putInt(key,value).commit();
    }
    public static pccBean readJson(){
        try {
            InputStream is = getContext().getResources().openRawResource(R.raw.city);
            InputStreamReader reader = new InputStreamReader(is);
            BufferedReader bufferedReader = new BufferedReader(reader);
            StringBuilder sb = new StringBuilder("");
            String str;
            while ((str = bufferedReader.readLine()) != null) {
                sb.append(str);
            }
            Gson gson = new Gson();
            pccBean pB = gson.fromJson(sb.toString(),pccBean.class);
            return pB;
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
            return null;
        }
    }
    public static boolean isConnect(){
        ConnectivityManager connectivityManager = (ConnectivityManager) UiUtils.getContext().getSystemService(Context.CONNECTIVITY_SERVICE);
        if (connectivityManager != null) {
            NetworkInfo info = connectivityManager.getActiveNetworkInfo();
            if (info != null && info.isConnected())
            {
                // 当前网络是连接的
                if (info.getState() == NetworkInfo.State.CONNECTED)
                {
                    // 当前所连接的网络可用
                    return true;
                }
            }
        }
        return false;
    }
}
