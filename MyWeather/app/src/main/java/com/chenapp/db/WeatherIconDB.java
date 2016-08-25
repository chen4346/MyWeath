package com.chenapp.db;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by Administrator on 2016/8/25 0025.
 */
public class WeatherIconDB extends SQLiteOpenHelper{
    public  static final String TABLE_NAME = "iconurl";
    public WeatherIconDB(Context context) {
        super(context, "weathericon", null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL("create table "+TABLE_NAME+"(_id integer primary key autoincrement,code varchar(6),url varchar(60))");
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }
}
