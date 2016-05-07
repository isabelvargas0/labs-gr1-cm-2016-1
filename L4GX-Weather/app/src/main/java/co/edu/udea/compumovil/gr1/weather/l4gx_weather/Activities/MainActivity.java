package co.edu.udea.compumovil.gr1.weather.l4gx_weather.Activities;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import co.edu.udea.compumovil.gr1.weather.l4gx_weather.R;

public class MainActivity extends AppCompatActivity {

    private static String API_KEY = "8839b7df4f87730b86c09729e2c9c67f";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }
}
