package com.chenapp.db;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;

/**
 * Created by Administrator on 2016/8/27 0027.
 */
public class WeatherInfoDao {
    public WeatherInfoDB weatherInfoDB;
    public WeatherInfoDao(Context context){
        weatherInfoDB = new WeatherInfoDB(context);
    }
    public String[] queryInfo(String city){
        SQLiteDatabase db = weatherInfoDB.getReadableDatabase();
        Cursor cursor = db.query(WeatherInfoDB.TABLENAME,new String[]{"time","json"},"city=?",new String[]{city},null,null,null);
        String[] info = new String[2];
        if(cursor.moveToNext()){
            info[0] = cursor.getString(0);
            info[1] = cursor.getString(1);
        }else{
            info = null;
        }
        cursor.close();
        db.close();
        return info;
    }
    public void updataInfo(String city,String json,String time){
        SQLiteDatabase db = weatherInfoDB.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("json",json);
        values.put("time",time);
        db.update(WeatherInfoDB.TABLENAME,values,"city=?",new String[]{city});
        db.close();
    }
    public void insertInfo(String city,String json,String time){
        SQLiteDatabase db = weatherInfoDB.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("json",json);
        values.put("time",time);
        values.put("city",city);
        db.insert(WeatherInfoDB.TABLENAME,null,values);
    }
    public void deleteInfo(String city){
        SQLiteDatabase db = weatherInfoDB.getWritableDatabase();
        db.delete(WeatherInfoDB.TABLENAME,"city=?",new String[]{city});
    }
    public ArrayList<String> queryAll(){
        SQLiteDatabase db = weatherInfoDB.getReadableDatabase();
        Cursor cursor = db.query(WeatherInfoDB.TABLENAME,new String[]{"city"},null,null,null,null,null);
        ArrayList<String> list = new ArrayList<>();
        while (cursor.moveToNext()){
            list.add(cursor.getString(0));
        }
        cursor.close();;
        db.close();
        return list;
    }
}
