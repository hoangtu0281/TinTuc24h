package com.example.hoangtu.tintuc24h;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.squareup.picasso.Picasso;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.text.SimpleDateFormat;
import java.util.Date;

public class WeatherActivity extends AppCompatActivity implements View.OnClickListener {

    private EditText edtSeach;
    private Button btnSeach, btnChangeActivity;
    private ImageView imvIcon;
    private TextView tvName, tvCountry, tvTemp, tvStt, tvhumidity, tvclound, tvWind, tvViewDay;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.weather_layout);
        initView();
    }
    public void getCurrentWeatherData(String data){
        RequestQueue requestQueue = Volley.newRequestQueue(WeatherActivity.this);
        String url = "https://api.openweathermap.org/data/2.5/weather?q="+data+"&units=metric&appid=a925d1de0f5a50281c9bd78a506deb43";
        StringRequest stringRequest = new StringRequest(Request.Method.GET, url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        try {
                            JSONObject jsonObject = new JSONObject(response);
                            String day = jsonObject.getString("dt");
                            String name = jsonObject.getString("name");
                            tvName.setText("Tên Thành Phố: "+name);

                            long l = Long.valueOf(day);
                            Date date = new Date(l*1000L);
                            SimpleDateFormat simpleDateFormat = new SimpleDateFormat("EEEE yyyy-MM-dd HH-mm-ss");
                            String DAY = simpleDateFormat.format(date);

                            tvViewDay.setText(DAY);

                            JSONArray jsonArrayWeather = jsonObject.getJSONArray("weather");
                            JSONObject jsonObjectWeather = jsonArrayWeather.getJSONObject(0);
                            String status = jsonObjectWeather.getString("main");
                            String icon = jsonObjectWeather.getString("icon");

                            Picasso.with(WeatherActivity.this).load("http://openweathermap.org/img/wn/"+icon+".png").into(imvIcon);
                            tvStt.setText(status);

                            JSONObject jsonObjectMain = jsonObject.getJSONObject("main");
                            String nhietdo = jsonObjectMain.getString("temp");
                            String doam = jsonObjectMain.getString("humidity");

                            Double a = Double.valueOf(nhietdo);
                            String Nhietdo = String.valueOf(a.intValue());

                            tvTemp.setText(Nhietdo+"°C");
                            tvhumidity.setText(doam+" %");

                            JSONObject jsonObjectWind = jsonObject.getJSONObject("wind");
                            String gio = jsonObjectWind.getString("speed");
                            tvWind.setText(gio+" m/s");

                            JSONObject jsonObjectClouds = jsonObject.getJSONObject("clouds");
                            String may = jsonObjectClouds.getString("all");
                            tvclound.setText(may+" %");

                            JSONObject jsonObjectSys = jsonObject.getJSONObject("sys");
                            String country = jsonObjectSys.getString("country");
                            tvCountry.setText("Tên Quốc gia: "+country);


                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {

                    }
                });
        requestQueue.add(stringRequest);
    }

    private void initView() {
        edtSeach = findViewById(R.id.edtSeach);
        btnChangeActivity = findViewById(R.id.btnChangeActivity);
        btnSeach = findViewById(R.id.btnSeach);
        btnSeach.setOnClickListener(this);
        imvIcon = findViewById(R.id.imvIcon);
        tvName = findViewById(R.id.tvName);
        tvCountry = findViewById(R.id.tvCountry);
        tvTemp = findViewById(R.id.tvTemp);
        tvStt = findViewById(R.id.tvStt);
        tvhumidity = findViewById(R.id.tvhumidity);
        tvclound = findViewById(R.id.tvclound);
        tvWind = findViewById(R.id.tvWind);
        tvViewDay = findViewById(R.id.tvViewDay);
    }

    @Override
    public void onClick(View view) {
        String city = edtSeach.getText().toString();
        getCurrentWeatherData(city);
    }
}
