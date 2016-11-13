package com.chenapp.myweather;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.amap.api.location.AMapLocation;
import com.amap.api.location.AMapLocationClient;
import com.amap.api.location.AMapLocationClientOption;
import com.amap.api.location.AMapLocationListener;
import com.chenapp.bean.HeWeatherBean;
import com.chenapp.bean.IconBean;
import com.chenapp.db.WeatherIconDBDao;
import com.chenapp.db.WeatherInfoDao;
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
import java.util.ArrayList;
import java.util.Date;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{

    private NowWeather now;
    private WeatherIconDBDao weatherIconDBDao;
    private int[] day = new int[6];
    private int[] night = new int[6];
    private WeatherChartView mCharView;
    private WeekView wv;
    private WeatherInfoDao weatherInfoDao;
    private static Toast toast = null;
    private boolean location = false;
    private ArrayList<String> allCity;
    public AMapLocationClient mLocationClient = null;
    public AMapLocationClientOption mLocationOption = null;
    public AMapLocationListener mLocationListener = new AMapLocationListener() {
        @Override
        public void onLocationChanged(AMapLocation aMapLocation) {
            location = false;
            if (aMapLocation != null) {
                if (aMapLocation.getErrorCode() == 0) {
                    String locatio = aMapLocation.getCity();

                    showToast(UiUtils.getContext(),locatio.substring(0,locatio.length()-1));

                    UiUtils.putString("city",locatio.substring(0,locatio.length()-1));
                    requestData();
                    mLocationClient.onDestroy();
                }else {
                    //定位失败时，可通过ErrCode（错误码）信息来确定失败的原因，errInfo是错误信息，详见错误码表。

                    showToast(UiUtils.getContext(),"location Error, ErrCode:"
                            + aMapLocation.getErrorCode() + ", errInfo:"
                            + aMapLocation.getErrorInfo());
                }
            }
        }
    };


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        weatherIconDBDao = new WeatherIconDBDao(UiUtils.getContext());
        mCharView = (WeatherChartView) findViewById(R.id.line_char);
        wv = (WeekView) findViewById(R.id.wv);
        wv.setText(new SimpleDateFormat("EEEE").format(new Date()));
        TextView tvCity = (TextView) findViewById(R.id.tv_title1);
        tvCity.setOnClickListener(this);
        TextView tvAdd = (TextView) findViewById(R.id.tv_title2);
        tvAdd.setOnClickListener(this);
        TextView tvMore = (TextView) findViewById(R.id.tv_title3);
        tvMore.setOnClickListener(this);
        weatherInfoDao = new WeatherInfoDao(UiUtils.getContext());


        now = (NowWeather) findViewById(R.id.nw);
        if(!UiUtils.getBoolean("isFirst",false)){
            new MyAsync().execute();
            UiUtils.putBoolean("isFirst",true);
        }
        if(weatherInfoDao.queryInfo(UiUtils.getString("city","杭州"))!=null){
            if(Long.parseLong(weatherInfoDao.queryInfo(UiUtils.getString("city","杭州"))[0])+60*60*1000>System.currentTimeMillis()){
                if(!UiUtils.isConnect()){
                    Toast.makeText(UiUtils.getContext(),"请检查网络连接是否可用",Toast.LENGTH_SHORT).show();
                }
                resolve(weatherInfoDao.queryInfo(UiUtils.getString("city","杭州"))[1]);
            }else{
                if(!UiUtils.isConnect()){
                    resolve(weatherInfoDao.queryInfo(UiUtils.getString("city","杭州"))[1]);
                    Toast.makeText(UiUtils.getContext(),"请检查网络连接是否可用",Toast.LENGTH_SHORT).show();
                }else{
                    requestData();
                }
            }
        }else{
            if(!UiUtils.isConnect()){
                Toast.makeText(UiUtils.getContext(),"请检查网络连接是否可用",Toast.LENGTH_SHORT).show();
            }else{
                requestData();
            }

        }

    }



    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.tv_title1:
                Intent intentCity = new Intent(UiUtils.getContext(),CityActivity.class);
                finish();
                startActivity(intentCity);
                break;
            case R.id.tv_title2:
                if(!UiUtils.isConnect()){
                    Toast.makeText(UiUtils.getContext(),"请检查网络连接是否可用",Toast.LENGTH_SHORT).show();
                }else{
                    Intent intentAdd = new Intent(UiUtils.getContext(),PccActivity.class);
                    finish();
                    startActivity(intentAdd);
                }
                break;
            case R.id.tv_title3:
                if(!UiUtils.isConnect()){
                    showToast(UiUtils.getContext(),"请检查网络连接是否可用");

                }else{
                    if(!location){
                        aLocation();
                        location = true;
                    }else{
                        showToast(UiUtils.getContext(),"正在定位，请勿重复操作！");
                    }

                }

                break;
        }
    }

    private void aLocation() {
        showToast(UiUtils.getContext(),"正在定位....");
        //初始化定位
        mLocationClient = new AMapLocationClient(getApplicationContext());
        //设置定位回调监听
        mLocationClient.setLocationListener(mLocationListener);
        mLocationOption = new AMapLocationClientOption();
        mLocationOption.setLocationMode(AMapLocationClientOption.AMapLocationMode.Battery_Saving);
        mLocationOption.setOnceLocation(true);
        mLocationOption.setOnceLocationLatest(true);
        //给定位客户端对象设置定位参数
        mLocationClient.setLocationOption(mLocationOption);
        //启动定位
        mLocationClient.startLocation();
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
        RequestParams params = new RequestParams(Global.REQUESTWEATHER+Global.REQUESTMODE_CITY+UiUtils.getString("city","杭州")+Global.WEATHERKEY);
        params.addQueryStringParameter("wd","xUtils");
        x.http().get(params, new Callback.CommonCallback<String>() {
            @Override
            public void onSuccess(String result) {
                StringBuilder sb = new StringBuilder(result);
                sb.deleteCharAt(11);
                sb.deleteCharAt(15);
                sb.delete(22,26);
                if(weatherInfoDao.queryInfo(UiUtils.getString("city","杭州"))!=null){
                    weatherInfoDao.updataInfo(UiUtils.getString("city","杭州"),sb.toString(),System.currentTimeMillis()+"");
                }else{
                    weatherInfoDao.insertInfo(UiUtils.getString("city","杭州"),sb.toString(),System.currentTimeMillis()+"");
                }

                resolve(sb.toString());

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

        if(json.length()<60){
            Toast.makeText(UiUtils.getContext(),"暂无该地天气信息",Toast.LENGTH_SHORT).show();
            weatherInfoDao.deleteInfo(UiUtils.getString("city","杭州"));
            UiUtils.putString("city","杭州");
        }else{
            Gson gson = new Gson();
            HeWeatherBean wea = gson.fromJson(json.toString(), HeWeatherBean.class);
            setBrief(wea);
        }

    }

    private void setBrief(HeWeatherBean wea) {
        HeWeatherBean.HeWeatherdataserviceBean heWea = wea.getHeWeatherdataservice().get(0);
        if(!UiUtils.isConnect()){
            if(heWea.getAqi()==null){
                now.setData(heWea.getBasic().getCity(),
                        UiUtils.getTime(Long.parseLong(weatherInfoDao.queryInfo(UiUtils.getString("city","杭州"))[0])),
                        heWea.getNow().getTmp()+"°c",
                        "AQI 暂无数据",
                        heWea.getNow().getCond().getTxt()
                );
            }else{
                now.setData(heWea.getBasic().getCity(),
                        UiUtils.getTime(Long.parseLong(weatherInfoDao.queryInfo(UiUtils.getString("city","杭州"))[0])),
                        heWea.getNow().getTmp()+"°c",
                        "AQI "+heWea.getAqi().getCity().getAqi()+" "+heWea.getAqi().getCity().getQlty(),
                        heWea.getNow().getCond().getTxt()
                );
            }


        }else{
            if(heWea.getAqi()==null){

                now.setData(heWea.getBasic().getCity(),
                        UiUtils.getTime(0l),
                        heWea.getNow().getTmp()+"°c",
                        "AQI 暂无数据",
                        heWea.getNow().getCond().getTxt()
                );
            }else{

                now.setData(heWea.getBasic().getCity(),
                        UiUtils.getTime(0l),
                        heWea.getNow().getTmp()+"°c",
                        "AQI "+heWea.getAqi().getCity().getAqi()+" "+heWea.getAqi().getCity().getQlty(),
                        heWea.getNow().getCond().getTxt()
                );
            }

        }

        for(int i = 0;i<6;i++){
            day[i] = Integer.parseInt(heWea.getDaily_forecast().get(i).getTmp().getMax());
            night[i]=Integer.parseInt(heWea.getDaily_forecast().get(i).getTmp().getMin());
        }
        mCharView.setTempDay(day);
        mCharView.setTempNight(night);
        mCharView.invalidate();
        ImageOptions imageOptions = new ImageOptions.Builder()
                //设置加载过程中的图片
                .setLoadingDrawableId(R.mipmap.na)
                //设置加载失败后的图片
                .setFailureDrawableId(R.mipmap.na)
                //设置使用缓存
                .setUseMemCache(true)
                .build();
        x.image().bind(now.ivIcon,weatherIconDBDao.queryUrl(heWea.getNow().getCond().getCode()),imageOptions);

    }

    public static void showToast(Context mContext, String id) {
        if (toast == null) {
            toast = Toast.makeText(mContext, id, Toast.LENGTH_SHORT);
        }
        else {
            toast.setText(id);
        }
        toast.show();
    }
}
