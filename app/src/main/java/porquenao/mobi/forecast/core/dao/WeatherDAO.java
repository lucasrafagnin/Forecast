package porquenao.mobi.forecast.core.dao;

import android.content.Context;

import porquenao.mobi.forecast.core.model.Weather;

/**
 * Created by lucas on 7/11/15.
 */
public class WeatherDAO extends GenericDAO<Weather> {

    private static WeatherDAO instance = null;

    private WeatherDAO(Context context) {
        super(context, Weather.class);
    }

    public static WeatherDAO getInstance(Context context) {
        if (instance == null) {
            instance = new WeatherDAO(context);
        }
        return instance;
    }
}
