package com.chenapp.myweather;

import android.app.ProgressDialog;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import com.chenapp.bean.HeWeatherBean;
import com.chenapp.bean.IconBean;
import com.chenapp.db.WeatherIconDBDao;
import com.chenapp.ui.NowWeather;
import com.chenapp.ui.WeatherChartView;
import com.chenapp.ui.WeekView;
import com.chenapp.utils.Global;
import com.chenapp.utils.UiUtils;
import com.google.gson.Gson;

import org.xutils.common.Callback;
import org.xutils.http.RequestParams;
import org.xutils.image.ImageOptions;
import org.xutils.x;

import java.text.SimpleDateFormat;
import java.util.Date;

public class MainActivity extends AppCompatActivity {

    private NowWeather now;
    private WeatherIconDBDao weatherIconDBDao;
    private int[] day = new int[6];
    private int[] night = new int[6];
    private WeatherChartView mCharView;
    private WeekView wv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        weatherIconDBDao = new WeatherIconDBDao(UiUtils.getContext());
        mCharView = (WeatherChartView) findViewById(R.id.line_char);
        wv = (WeekView) findViewById(R.id.wv);
        wv.setText(new SimpleDateFormat("EEEE").format(new Date()));
        Log.d("aaaaaaa",new SimpleDateFormat("EEEE").format(new Date()));
        now = (NowWeather) findViewById(R.id.nw);
        if(!UiUtils.getIsFirst("isFirst",false)){
            new MyAsync().execute();
            UiUtils.putIsFirst("isFirst",true);

        }
        ProgressDialog dialog = new ProgressDialog(MainActivity.this);
        dialog.setMessage("数据准备中，请稍后...");
        dialog.setCancelable(false);
        dialog.show();
        requestData();
        dialog.dismiss();



    }
    class MyAsync extends AsyncTask<Void,Void,Void>{

        private ProgressDialog dialog;

        @Override
        protected void onPreExecute() {
            dialog = new ProgressDialog(MainActivity.this);
            dialog.setMessage("数据准备中，请稍后...");
            dialog.setCancelable(false);
            dialog.show();
            super.onPreExecute();
        }

        @Override
        protected Void doInBackground(Void... voids) {
            requestIcon();
            return null;
        }

        @Override
        protected void onPostExecute(Void aVoid) {
            super.onPostExecute(aVoid);
            dialog.dismiss();
        }
    }
    private void requestIcon() {
        RequestParams params = new RequestParams(Global.REQUESTIMAGE+Global.WEATHERKEY);
        params.addQueryStringParameter("wd","xUtils");
        x.http().get(params, new Callback.CommonCallback<String>() {
            @Override
            public void onSuccess(String result) {
                resolveImg(result);
            }

            @Override
            public void onError(Throwable ex, boolean isOnCallback) {
                ex.printStackTrace();
            }

            @Override
            public void onCancelled(CancelledException cex) {

            }

            @Override
            public void onFinished() {

            }
        });
    }

    private void resolveImg(String result) {
        Gson gson = new Gson();
        IconBean icon = gson.fromJson(result,IconBean.class);
        for(int i = 0; i<icon.getCond_info().size();i++){
            weatherIconDBDao.addUrl(icon.getCond_info().get(i).getCode(),icon.getCond_info().get(i).getIcon());
        }
    }


    private void requestData() {
        RequestParams params = new RequestParams(Global.REQUESTWEATHER+Global.REQUESTMODE_CITY+"杭州"+Global.WEATHERKEY);
        params.addQueryStringParameter("wd","xUtils");
        x.http().get(params, new Callback.CommonCallback<String>() {
            @Override
            public void onSuccess(String result) {
                resolve(result);
            }

            @Override
            public void onError(Throwable ex, boolean isOnCallback) {
                ex.printStackTrace();
            }

            @Override
            public void onCancelled(CancelledException cex) {

            }

            @Override
            public void onFinished() {

            }
        });
    }

    private void resolve(String json) {
        StringBuilder sb = new StringBuilder(json);
        sb.deleteCharAt(11);
        sb.deleteCharAt(15);
        sb.delete(22,26);

        Gson gson = new Gson();
        HeWeatherBean wea = gson.fromJson(sb.toString(), HeWeatherBean.class);
        setBrief(wea);
    }

    private void setBrief(HeWeatherBean wea) {
        HeWeatherBean.HeWeatherdataserviceBean heWea = wea.getHeWeatherdataservice().get(0);
        now.setData(heWea.getBasic().getCity(),
                UiUtils.getTime(),
                heWea.getNow().getTmp()+"°c",
                "AQI "+heWea.getAqi().getCity().getAqi()+" "+heWea.getAqi().getCity().getQlty(),
                heWea.getNow().getCond().getTxt()
        );
        for(int i = 0;i<6;i++){
            day[i] = Integer.parseInt(heWea.getDaily_forecast().get(i).getTmp().getMax());
            night[i]=Integer.parseInt(heWea.getDaily_forecast().get(i).getTmp().getMin());
        }
        mCharView.setTempDay(day);

        mCharView.setTempNight(night);
        mCharView.invalidate();
        ImageOptions imageOptions = new ImageOptions.Builder()
                //设置加载过程中的图片
                .setLoadingDrawableId(R.drawable.ic_launcher)
                //设置加载失败后的图片
                .setFailureDrawableId(R.drawable.ic_launcher)
                //设置使用缓存
                .setUseMemCache(true)
                .build();
        x.image().bind(now.ivIcon,weatherIconDBDao.queryUrl(heWea.getNow().getCond().getCode()),imageOptions);

    }

}
