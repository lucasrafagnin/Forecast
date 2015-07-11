package porquenao.mobi.forecast.core.util;

import java.util.Calendar;

/**
 * Created by lucas on 7/11/15.
 */
public class Helper {

    public static boolean isDay() {
        int hour = Calendar.getInstance().get(Calendar.HOUR_OF_DAY);
        return hour >= 6 && hour <= 17;
    }
}
