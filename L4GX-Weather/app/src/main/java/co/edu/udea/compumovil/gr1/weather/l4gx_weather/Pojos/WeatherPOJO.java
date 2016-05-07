package co.edu.udea.compumovil.gr1.weather.l4gx_weather.Pojos;

import android.graphics.drawable.Icon;

/**
 * Created by felipe on 7/05/16.
 */
public class WeatherPOJO {
    private String diaSemana;
    private String temp;
    private String humedad;
    private String descr;
    private Icon icono;

    public String getDiaSemana() {
        return diaSemana;
    }

    public void setDiaSemana(String diaSemana) {
        this.diaSemana = diaSemana;
    }

    public String getTemp() {
        return temp;
    }

    public void setTemp(String temp) {
        this.temp = temp;
    }

    public String getHumedad() {
        return humedad;
    }

    public void setHumedad(String humedad) {
        this.humedad = humedad;
    }

    public String getDescr() {
        return descr;
    }

    public void setDescr(String descr) {
        this.descr = descr;
    }

    public Icon getIcono(String iconString) {
       /* switch (iconString){
            case "":
            case "":
            case "":
            case "":
            case "":
            case "":
            case "":
            case "":
                default:
        }*/
        return icono;
    }

    public void setIcono(Icon icono) {
        this.icono = icono;
    }
}
