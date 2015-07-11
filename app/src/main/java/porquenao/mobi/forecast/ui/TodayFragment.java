package porquenao.mobi.forecast.ui;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.Calendar;

import porquenao.mobi.forecast.R;

public class TodayFragment extends Fragment {

    private static TextView vCity;
    private static TextView vTempMin;
    private static TextView vTempMax;
    private static TextView vAverage;
    private static TextView vClock;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        int resource = getTheme(inflater, container);
        View view = inflater.inflate(resource, container, false);

        return view;
    }

    public static int getTheme(LayoutInflater inflater, ViewGroup container) {
        int hour = Calendar.getInstance().get(Calendar.HOUR_OF_DAY);
        if (hour >= 6 && hour <= 17)
            return R.layout.today_day;
        else
            return R.layout.today_night;
    }

    public static void todayWeather(View v) {
        vCity = (TextView) v.findViewById(R.id.city);
        vTempMin = (TextView) v.findViewById(R.id.temp_min);
        vTempMax = (TextView) v.findViewById(R.id.temp_max);
        vAverage = (TextView) v.findViewById(R.id.temp);
        vClock = (TextView) v.findViewById(R.id.time);
    }
}
