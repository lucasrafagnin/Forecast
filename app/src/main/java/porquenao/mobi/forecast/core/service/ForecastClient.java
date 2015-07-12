package porquenao.mobi.forecast.core.service;

import java.util.List;

import porquenao.mobi.forecast.core.model.Weather;
import retrofit.http.GET;
import retrofit.http.Query;

/**
 * Created by lucas on 7/9/15.
 */
public interface ForecastClient {

    @GET("/get")
    List<Weather> weathers(@Query("count") String count
    );
}
