package com.chenapp.db;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

/**
 * Created by Administrator on 2016/8/25 0025.
 */
public class WeatherIconDBDao {
    private WeatherIconDB weatherIconDB;
    public WeatherIconDBDao(Context context){
        weatherIconDB = new WeatherIconDB(context);
    }
    public void addUrl(String code,String url){
        SQLiteDatabase db = weatherIconDB.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("code",code);
        values.put("url",url);
        db.insert(WeatherIconDB.TABLE_NAME,null,values);
        db.close();
    }
    public String queryUrl(String code){
        SQLiteDatabase db = weatherIconDB.getWritableDatabase();
        Cursor cursor = db.query(WeatherIconDB.TABLE_NAME,new String[]{"url"},"code=?",new String[]{code},null,null,null);
        String url = null;
        while (cursor.moveToNext()){
            url = cursor.getString(0);
        }
        cursor.close();
        db.close();
        return url;
    }
}
