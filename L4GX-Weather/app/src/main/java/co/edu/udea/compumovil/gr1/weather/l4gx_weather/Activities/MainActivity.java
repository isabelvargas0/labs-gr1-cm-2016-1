package co.edu.udea.compumovil.gr1.weather.l4gx_weather.Activities;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import co.edu.udea.compumovil.gr1.weather.l4gx_weather.R;

public class MainActivity extends AppCompatActivity {

    private static String URL = "http://api.openweathermap.org/data/2.5/forecast";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

    }
}
