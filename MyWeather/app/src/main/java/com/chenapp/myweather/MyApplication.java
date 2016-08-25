package com.chenapp.myweather;

import android.app.Application;
import android.content.Context;

import org.xutils.x;

/**
 * Created by Administrator on 2016/8/24 0024.
 */
public class MyApplication extends Application{

    public static Context context;

    @Override
    public void onCreate() {
        super.onCreate();
        x.Ext.init(this);
        context = getApplicationContext();
    }

    public static Context getContext(){
        return context;
    }
}
