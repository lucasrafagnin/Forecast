package porquenao.mobi.forecast.core.model;

import android.content.Context;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

import java.io.Serializable;

import porquenao.mobi.forecast.R;
import porquenao.mobi.forecast.core.util.Helper;

/**
 * Created by lucas on 7/9/15.
 */
@DatabaseTable(tableName = "weather")
public class Weather implements Serializable {

    @DatabaseField(generatedId = true)
    private Long id;
    @DatabaseField
    private String city;
    @DatabaseField
    private String status;
    @DatabaseField
    private Long date;
    @DatabaseField
    private String average;
    @DatabaseField
    private String min;
    @DatabaseField
    private String max;

    public Weather() {
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Long getDate() {
        return date;
    }

    public void setDate(Long date) {
        this.date = date;
    }

    public String getAverage() {
        return average;
    }

    public void setAverage(String average) {
        this.average = average;
    }

    public String getMin() {
        return min;
    }

    public void setMin(String min) {
        this.min = min;
    }

    public String getMax() {
        return max;
    }

    public void setMax(String max) {
        this.max = max;
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

        boolean day = Helper.isDay();
        resourceName.append("ic_placeholder_").append(list ? "list" : (day ? "day" : "night")).append("_").append(getStatus());

        return context.getResources().getIdentifier(resourceName.toString(), "drawable", context.getPackageName());
    }

    public String getUrl(boolean list, Context context) {
        StringBuilder iconUrl = new StringBuilder();

        float density = context.getResources().getDisplayMetrics().density;
        iconUrl.append(context.getResources().getString(R.string.api_url)).append("images/").append(density).append("/ic_");
        boolean day = Helper.isDay();
        iconUrl.append(list ? "list" : (day ? "day" : "night")).append("_").append(getStatus()).append(".png");

        return iconUrl.toString();
    }

}


