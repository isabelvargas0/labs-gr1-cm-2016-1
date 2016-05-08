package co.edu.udea.compumovil.gr1.weather.l4gx_weather;

import co.edu.udea.compumovil.gr1.weather.l4gx_weather.Pojos.Model;
import retrofit.Callback;
import retrofit.http.GET;

/**
 * Created by Isabel on 08/05/2016.
 */
public interface RestInterface {
    String API_KEY = "8839b7df4f87730b86c09729e2c9c67f";

    @GET("/daily?q=London&mode=json&&units=metric&cnt=7&appid=" + API_KEY)
    void getWheatherReport(Callback<Model> cb);
}
