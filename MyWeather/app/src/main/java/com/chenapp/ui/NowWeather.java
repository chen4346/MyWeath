package com.chenapp.ui;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.chenapp.myweather.R;
import com.chenapp.utils.UiUtils;

/**
 * Created by Administrator on 2016/8/24 0024.
 */
public class NowWeather extends RelativeLayout{

    private TextView tvCity;
    private TextView tvTime;
    private TextView tvTemp;
    private TextView tvAqi;
    private TextView tvWeather;
    public ImageView ivIcon;

    public NowWeather(Context context) {
        super(context);init();

    }

    public NowWeather(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();

    }

    public NowWeather(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    private void init(){
        View v = View.inflate(UiUtils.getContext(), R.layout.now_layout,this);
        tvCity = (TextView) v.findViewById(R.id.tv_city);
        tvTime = (TextView) v.findViewById(R.id.tv_time);
        tvTemp = (TextView) v.findViewById(R.id.tv_temp);
        tvAqi = (TextView) v.findViewById(R.id.tv_aqi);
        tvWeather = (TextView) v.findViewById(R.id.tv_weather);
        ivIcon = (ImageView) v.findViewById(R.id.iv_weaicon);
    }
    public void setData(String city,String time,String temp,String aqi,String wea){
        tvCity.setText(city);
        tvTime.setText(time);
        tvTemp.setText(temp);
        tvAqi.setText(aqi);
        tvWeather.setText(wea);

    }

}
