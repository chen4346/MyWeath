package com.chenapp.ui;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.chenapp.myweather.R;
import com.chenapp.utils.UiUtils;

import java.util.ArrayList;

/**
 * Created by Administrator on 2016/8/25 0025.
 */
public class WeekView extends LinearLayout{
    private String[] week = new String[]{"星期一","星期二","星期三","星期四","星期五","星期六","星期日"};
    private ArrayList<String> list;
    private TextView week1;
    private TextView week2;
    private TextView week3;
    private TextView week4;
    private TextView week5;
    private TextView week6;


    public WeekView(Context context) {
        super(context);
        init();
    }

    public WeekView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public WeekView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }
    private void init() {
        list = new ArrayList<>();
        for (int i = 0 ; i<7 ; i++){
            list.add(week[i]);
        }

        View v = View.inflate(UiUtils.getContext(), R.layout.week_layout, this);
        week1 = (TextView) v.findViewById(R.id.tv_week1);
        week2 = (TextView) v.findViewById(R.id.tv_week2);
        week3 = (TextView) v.findViewById(R.id.tv_week3);
        week4 = (TextView) v.findViewById(R.id.tv_week4);
        week5 = (TextView) v.findViewById(R.id.tv_week5);
        week6 = (TextView) v.findViewById(R.id.tv_week6);


    }
    public void setText(String weekday){
        int i = list.indexOf(weekday);
        week1.setText(weekday);
        week2.setText(list.get((i+1)%7));
        week3.setText(list.get((i+2)%7));
        week4.setText(list.get((i+3)%7));
        week5.setText(list.get((i+4)%7));
        week6.setText(list.get((i+5)%7));

    }
}
