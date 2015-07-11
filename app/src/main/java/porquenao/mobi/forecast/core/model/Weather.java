package porquenao.mobi.forecast.core.model;

import android.content.Context;

import java.util.Calendar;

import porquenao.mobi.forecast.R;

/**
 * Created by lucas on 7/9/15.
 */
public class Weather {

    String city;
    String status;
    Long date;
    String average;
    String min;
    String max;
    String url;

    public Weather() {
    }

    public String getCity() {
        return city;
    }

    public String getStatus() {
        return status;
    }

    public Long getDate() {
        return date;
    }

    public String getAverage() {
        return average;
    }

    public String getMin() {
        return min;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getMax() {
        return max;
    }

    public int getTempColor() {
        if (Integer.parseInt(getAverage()) < 20)
            return R.drawable.cell_blue;
        else if (Integer.parseInt(getAverage()) < 30)
            return R.drawable.cell_yellow;
        else
            return R.drawable.cell_red;
    }

    public int getTempIcon(boolean list, Context context) {
        StringBuilder resourceName = new StringBuilder();
        StringBuilder iconUrl = new StringBuilder();

        int hour = Calendar.getInstance().get(Calendar.HOUR_OF_DAY);
        boolean day = hour >= 6 && hour <= 16;
        resourceName.append("ic_placeholder_").append(list ? "list" : (day ? "day" : "night")).append("_").append(getStatus());

        //"/ic_list_clear_sky.png";
        float density = context.getResources().getDisplayMetrics().density;
        iconUrl.append("http://forecastpqn.parseapp.com/images/").append(density).append("/");
        iconUrl.append(resourceName.toString().replaceAll("placeholder_", "")).append(".png");
        setUrl(iconUrl.toString());

        return context.getResources().getIdentifier(resourceName.toString(), "drawable", context.getPackageName());
    }
}


