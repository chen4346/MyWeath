package com.chenapp.myweather;

import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.chenapp.db.WeatherInfoDao;
import com.chenapp.ui.MyGridView;
import com.chenapp.utils.UiUtils;

import java.util.ArrayList;

public class CityActivity extends AppCompatActivity implements AdapterView.OnItemClickListener,AdapterView.OnItemLongClickListener{
    private ArrayList<String> list;
    private  WeatherInfoDao weatherInfoDao;
    private MyGridView gv;
    private MyAdapter adapter ;
    private AlertDialog dialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_city);
        weatherInfoDao = new WeatherInfoDao(UiUtils.getContext());
        new Async().execute();
        gv = (MyGridView) findViewById(R.id.gv_city);
        gv.setOnItemClickListener(this);
        gv.setOnItemLongClickListener(this);
    }

    @Override
    public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
        UiUtils.putString("city",list.get(i));
        finish();
        startActivity(new Intent(UiUtils.getContext(),MainActivity.class));

    }

    @Override
    public boolean onItemLongClick(AdapterView<?> adapterView, View view, int i, long l) {
        final int index = i;
        AlertDialog.Builder builder= new AlertDialog.Builder(CityActivity.this);
        builder.setMessage("是否删除？");
        builder.setCancelable(false);
        builder.setPositiveButton("删除", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                weatherInfoDao.deleteInfo(list.get(index));
                list.remove(index);
                UiUtils.putString("city",list.get(0));
                adapter = new MyAdapter();
                gv.setAdapter(new MyAdapter());
                dialog.dismiss();
            }
        });
        builder.setNegativeButton("取消",null);
        dialog = builder.create();
        dialog.show();
        return true;
    }

    private class Async extends AsyncTask<Void,Void,Void>{
        private AlertDialog pdialog;

        @Override
        protected void onPreExecute() {
            ProgressDialog.Builder builder = new AlertDialog.Builder(CityActivity.this);
            builder.setMessage("加载中");
            builder.setCancelable(false);
            pdialog =  builder.create();
            pdialog.show();
        }

        @Override
        protected Void doInBackground(Void... voids) {
            list = weatherInfoDao.queryAll();
            return null;
        }

        @Override
        protected void onPostExecute(Void aVoid) {
            pdialog.dismiss();
            adapter = new MyAdapter();
            gv.setAdapter(new MyAdapter());

        }
    }
    private  class MyAdapter extends BaseAdapter {

        @Override
        public int getCount() {

            return list.size();
        }

        @Override
        public Object getItem(int i) {
            return null;
        }

        @Override
        public long getItemId(int i) {
            return 0;
        }

        @Override
        public View getView(int i, View view, ViewGroup viewGroup) {
            View v;
            if(view==null){
                v = View.inflate(UiUtils.getContext(),R.layout.grid_item,null);
            }else{
                v = view;
            }
            TextView tv = (TextView) v.findViewById(R.id.tv_grid_item);
            tv.setText(list.get(i));

            return v;
        }
    }
    @Override
    public void onBackPressed() {
        finish();
        startActivity(new Intent(UiUtils.getContext(),MainActivity.class));
    }
}
