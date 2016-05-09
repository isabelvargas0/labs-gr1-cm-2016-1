package co.edu.udea.compumovil.gr1.weather.l4gx_weather.Activities;

import android.content.Context;
import android.os.Build;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import co.edu.udea.compumovil.gr1.weather.l4gx_weather.Pojos.Model;
import co.edu.udea.compumovil.gr1.weather.l4gx_weather.R;
import co.edu.udea.compumovil.gr1.weather.l4gx_weather.RestInterface;
import co.edu.udea.compumovil.gr1.weather.l4gx_weather.Util;
import retrofit.Callback;
import retrofit.RestAdapter;
import retrofit.RetrofitError;
import retrofit.client.Response;

public class MainActivity extends AppCompatActivity {

    private static String URL = "http://api.openweathermap.org/data/2.5/forecast";
    private static String GRADOS = "°C";
    private String queryCity;
    Util util;

    RelativeLayout relativeLayout;
    AutoCompleteTextView autvCity;

    TextView tvTemp;
    ImageView ivIcon;
    TextView tvDescr;
    TextView tvHumTxt;
    TextView tvHum;

    TextView tvDay1;
    TextView tvDay2;
    TextView tvDay3;
    TextView tvDay4;
    TextView tvDay5;

    TextView tvTemp1;
    TextView tvTemp2;
    TextView tvTemp3;
    TextView tvTemp4;
    TextView tvTemp5;

    ImageView ivDay1;
    ImageView ivDay2;
    ImageView ivDay3;
    ImageView ivDay4;
    ImageView ivDay5;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        relativeLayout = (RelativeLayout) findViewById(R.id.relative_img);
        autvCity = (AutoCompleteTextView) findViewById(R.id.auto_ciudad);
        String[] capitals = getResources().getStringArray(R.array.capitals);
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, capitals);
        autvCity.setAdapter(adapter);

        tvTemp = (TextView) findViewById(R.id.tv_temp);
        ivIcon = (ImageView) findViewById(R.id.iv_icon);
        tvDescr = (TextView) findViewById(R.id.tv_descr);
        tvHumTxt = (TextView) findViewById(R.id.tv_hum_txt);
        tvHum = (TextView) findViewById(R.id.tv_humi);

        tvDay1 = (TextView) findViewById(R.id.tv_day1);
        tvDay2 = (TextView) findViewById(R.id.tv_day2);
        tvDay3 = (TextView) findViewById(R.id.tv_day3);
        tvDay4 = (TextView) findViewById(R.id.tv_day4);
        tvDay5 = (TextView) findViewById(R.id.tv_day5);

        tvTemp1 = (TextView) findViewById(R.id.tv_temp1);
        tvTemp2 = (TextView) findViewById(R.id.tv_temp2);
        tvTemp3 = (TextView) findViewById(R.id.tv_temp3);
        tvTemp4 = (TextView) findViewById(R.id.tv_temp4);
        tvTemp5 = (TextView) findViewById(R.id.tv_temp5);

        ivDay1 = (ImageView) findViewById(R.id.iv_day1);
        ivDay2 = (ImageView) findViewById(R.id.iv_day2);
        ivDay3 = (ImageView) findViewById(R.id.iv_day3);
        ivDay4 = (ImageView) findViewById(R.id.iv_day4);
        ivDay5 = (ImageView) findViewById(R.id.iv_day5);


    }

    public void traerClima(final View view) {
        util = new Util(this);
        queryCity = autvCity.getText().toString();
        //making object of RestAdapter
        RestAdapter adapter = new RestAdapter.Builder().setEndpoint(URL).build();

        //Creating Rest Services
        RestInterface restInterface = adapter.create(RestInterface.class);

        //Calling method to get whether report
        restInterface.getWheatherReport(queryCity, new Callback<Model>() {
            @Override
            public void success(Model model, Response response) {
                InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
                imm.hideSoftInputFromWindow(view.getWindowToken(), 0);
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN) {
                    relativeLayout.setBackground(util.getBg(model.getList().get(0).getWeather().get(0)
                            .getDescription()));
                } else {
                    relativeLayout.setBackgroundDrawable(util.getBg(model.getList().get(0).getWeather().get(0)
                            .getDescription()));
                }
                tvTemp.setText(model.getList().get(0).getTemp().getDay().toString() + GRADOS);
                ivIcon.setImageDrawable(util.getIcon(model.getList().get(0).getWeather()
                        .get(0).getIcon()));
                tvDescr.setText(model.getList().get(0).getWeather().get(0).getDescription());
                tvHumTxt.setText("Humedad");
                tvHum.setText(model.getList().get(0).getHumidity().toString() + "%");

                tvDay1.setText(util.getDateFromUnix(model.getList().get(0).getDt()));
                tvDay2.setText(util.getDateFromUnix(model.getList().get(1).getDt()));
                tvDay3.setText(util.getDateFromUnix(model.getList().get(2).getDt()));
                tvDay4.setText(util.getDateFromUnix(model.getList().get(3).getDt()));
                tvDay5.setText(util.getDateFromUnix(model.getList().get(4).getDt()));

                tvTemp1.setText(model.getList().get(0).getTemp().getDay().toString() + GRADOS);
                tvTemp2.setText(model.getList().get(1).getTemp().getDay().toString() + GRADOS);
                tvTemp3.setText(model.getList().get(2).getTemp().getDay().toString() + GRADOS);
                tvTemp4.setText(model.getList().get(3).getTemp().getDay().toString() + GRADOS);
                tvTemp5.setText(model.getList().get(4).getTemp().getDay().toString() + GRADOS);

                ivDay1.setImageDrawable(util.getIcon(model.getList().get(0).getWeather()
                        .get(0).getIcon()));
                ivDay2.setImageDrawable(util.getIcon(model.getList().get(1).getWeather()
                        .get(0).getIcon()));
                ivDay3.setImageDrawable(util.getIcon(model.getList().get(2).getWeather()
                        .get(0).getIcon()));
                ivDay4.setImageDrawable(util.getIcon(model.getList().get(3).getWeather()
                        .get(0).getIcon()));
                ivDay5.setImageDrawable(util.getIcon(model.getList().get(4).getWeather()
                        .get(0).getIcon()));
            }

            @Override
            public void failure(RetrofitError error) {

                String merror = error.getMessage();
                Log.e("Error", merror);
                Toast.makeText(MainActivity.this, merror, Toast.LENGTH_SHORT).show();
            }
        });

    }
}
