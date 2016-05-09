package co.edu.udea.compumovil.gr1.weather.l4gx_weather;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.Icon;

import java.util.Calendar;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by Isabel on 08/05/2016.
 */
public class Util {
    Context mContext;

    public Util(Context context) {
        mContext = context;
    }

    public Drawable getIcon(String stringIcon) {
        switch (stringIcon) {
            case "01d":
                return (Drawable) mContext.getResources().getDrawable(R.drawable.clear_sky);
            case "02d":
                return (Drawable) mContext.getResources().getDrawable(R.drawable.few_clouds);
            case "03d":
                return (Drawable) mContext.getResources().getDrawable(R.drawable.scattered_clouds);
            case "04d":
                return (Drawable) mContext.getResources().getDrawable(R.drawable.broken_clouds);
            case "09d":
                return (Drawable) mContext.getResources().getDrawable(R.drawable.shower_rain);
            case "10d":
                return (Drawable) mContext.getResources().getDrawable(R.drawable.rain);
            case "11d":
                return (Drawable) mContext.getResources().getDrawable(R.drawable.thunderstorm);
            case "13d":
                return (Drawable) mContext.getResources().getDrawable(R.drawable.snow);
            case "50d":
                return (Drawable) mContext.getResources().getDrawable(R.drawable.mist);
            case "01n":
                return (Drawable) mContext.getResources().getDrawable(R.drawable.nclear_sky);
            case "02n":
                return (Drawable) mContext.getResources().getDrawable(R.drawable.nfew_clouds);
            case "03n":
                return (Drawable) mContext.getResources().getDrawable(R.drawable.nscattered_clouds);
            case "04n":
                return (Drawable) mContext.getResources().getDrawable(R.drawable.nbroken_clouds);
            case "09n":
                return (Drawable) mContext.getResources().getDrawable(R.drawable.nshower_rain);
            case "10n":
                return (Drawable) mContext.getResources().getDrawable(R.drawable.nrain);
            case "11n":
                return (Drawable) mContext.getResources().getDrawable(R.drawable.nthunderstorm);
            case "13n":
                return (Drawable) mContext.getResources().getDrawable(R.drawable.nsnow);
            case "50n":
                return (Drawable) mContext.getResources().getDrawable(R.drawable.nmist);

        }
        return null;
    }

    public Drawable getBg(String description) {
        if (description.contains("rain")) {
            return (Drawable) mContext.getResources().getDrawable(R.drawable.bg_rain);
        } else if (description.contains("clear")) {
            return (Drawable) mContext.getResources().getDrawable(R.drawable.sunny_bg);
        } else if (description.contains("snow")) {
            return (Drawable) mContext.getResources().getDrawable(R.drawable.bg_snow);
        } else if (description.contains("mist")) {
            return (Drawable) mContext.getResources().getDrawable(R.drawable.bg_mist);
        } else if (description.contains("clouds")) {
            return (Drawable) mContext.getResources().getDrawable(R.drawable.bg_bloken_clouds);
        } else if (description.contains("thunderstorm")) {
            return (Drawable) mContext.getResources().getDrawable(R.drawable.bg_thunder);
        }
        return null;
    }

    public String getDateFromUnix(long seconds) {
        Calendar mydate = Calendar.getInstance();
        mydate.setTimeInMillis(seconds * 1000);
        int value = mydate.get(Calendar.DAY_OF_WEEK);
        String day = "";

        switch (value) {
            case 1:
                day = "Sun";
                break;
            case 2:
                day = "Mon";
                break;
            case 3:
                day = "Tues";
                break;
            case 4:
                day = "Wed";
                break;
            case 5:
                day = "Thu";
                break;
            case 6:
                day = "Fri";
                break;
            case 7:
                day = "Sat";
                break;
        }
        return day;
    }

}
