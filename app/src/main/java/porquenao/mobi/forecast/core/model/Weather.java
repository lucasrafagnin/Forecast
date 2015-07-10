package porquenao.mobi.forecast.core.model;

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

}


