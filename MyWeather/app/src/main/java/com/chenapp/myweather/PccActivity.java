package com.chenapp.myweather;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.GridView;
import android.widget.TextView;
import android.widget.Toast;

import com.chenapp.bean.pccBean;
import com.chenapp.utils.UiUtils;

public class PccActivity extends AppCompatActivity implements View.OnClickListener,AdapterView.OnItemClickListener{
    private Button btn_province, btn_city, btn_county;
    private GridView grid0;
    private int btOrder = 0;
    private int btMode = 0;
    private int province;
    private int city;
    private int county;
    private pccBean pb;
    private Button btOk;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.provinc_layout);
        pb = UiUtils.readJson();
        btMode = 0;
        initView();
        grid0.setVisibility(View.VISIBLE);
        grid0.setAdapter(new MyAdapter());
        btOk.setVisibility(View.GONE);
        loadGridView();
    }

    private void loadGridView() {

    }

    private void initView() {
        btn_province = (Button) findViewById(R.id.btn_province);
        btn_city = (Button) findViewById(R.id.btn_city);
        btn_county = (Button) findViewById(R.id.btn_county);
        grid0 = (GridView) findViewById(R.id.gridview_start_with0);
        btOk = (Button) findViewById(R.id.bt_address_ok);
        grid0.setOnItemClickListener(this);
        btn_city.setOnClickListener(this);
        btn_county.setOnClickListener(this);
        btn_province.setOnClickListener(this);
        btOk.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.btn_province:
                btOrder = 0;
                btMode = 0;
                btn_city.setText("城市");
                btn_county.setText("区县");
                btn_province.setText("省份");
                grid0.setVisibility(View.VISIBLE);
                grid0.setAdapter(new MyAdapter());
                btOk.setVisibility(View.GONE);
                break;
            case R.id.btn_city:

                btOk.setVisibility(View.GONE);
                if(btOrder==0){
                    Toast.makeText(UiUtils.getContext(),"请先选择省份",Toast.LENGTH_SHORT).show();
                }else{
                    btMode = 1;
                    btn_county.setText("区县");
                    grid0.setAdapter(new MyAdapter());
                }
                break;
            case R.id.btn_county:


                if(btOrder!=2){
                    Toast.makeText(UiUtils.getContext(),"请先选择省和市",Toast.LENGTH_SHORT).show();
                }else{
                    btMode = 2;
                    if(btn_county.getText().toString().equals("区县")){
                        btOk.setVisibility(View.GONE);
                    }else{
                        btOk.setVisibility(View.VISIBLE);
                    }
                    grid0.setAdapter(new MyAdapter());
                }
                break;
            case R.id.bt_address_ok:

                if(UiUtils.isConnect()){
                    UiUtils.putString("city",btn_county.getText().toString());
                }else{
                    Toast.makeText(UiUtils.getContext(),"请检查网络连接是否可用",Toast.LENGTH_SHORT).show();
                }
                finish();
                startActivity(new Intent(UiUtils.getContext(),MainActivity.class));
                break;
        }
    }

    @Override
    public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
        if(btMode==0){
            province = i;
            btn_province.setText(pb.getResult().get(province).getProvince());
            btMode=1;
            grid0.setAdapter(new MyAdapter());
            btOk.setVisibility(View.GONE);
            btOrder = 1;
        }else if (btMode==1){
            city = i;
            btn_city.setText(pb.getResult().get(province).getCity().get(i).getCity());
            btMode=2;
            grid0.setAdapter(new MyAdapter());
            btOrder = 2;
            btOk.setVisibility(View.GONE);
        }else if(btMode==2){
            county = i;
            btn_county.setText(pb.getResult().get(province).getCity().get(city).getDistrict().get(i).getDistrict());

            btOk.setVisibility(View.VISIBLE);
        }
    }

    public class MyAdapter extends BaseAdapter {

        @Override
        public int getCount() {
            int count = 0;
            if(btMode==0){
                count  = pb.getResult().size();
            }else if (btMode==1){
                count  = pb.getResult().get(province).getCity().size();
            }else if(btMode==2){
                count  = pb.getResult().get(province).getCity().get(city).getDistrict().size();
            }
            return count;
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
            if(btMode==0){
                tv.setText(pb.getResult().get(i).getProvince());
            }else if(btMode==1){
                tv.setText(pb.getResult().get(province).getCity().get(i).getCity());
            }else if(btMode==2){
                tv.setText(pb.getResult().get(province).getCity().get(city).getDistrict().get(i).getDistrict());
            }

            return v;
        }
    }

    @Override
    public void onBackPressed() {
        finish();
        startActivity(new Intent(UiUtils.getContext(),MainActivity.class));
    }
}
