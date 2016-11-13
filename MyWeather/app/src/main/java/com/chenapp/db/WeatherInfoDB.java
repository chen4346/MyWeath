package com.chenapp.db;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by Administrator on 2016/8/27 0027.
 */
public class WeatherInfoDB extends SQLiteOpenHelper{
    public static String TABLENAME = "weather";
    public WeatherInfoDB(Context context) {
        super(context, "Weatherinfo", null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL("create table "+TABLENAME+"(_id integer primary key autoincrement,city varchar(20),time varchar(20),json text)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }
}
