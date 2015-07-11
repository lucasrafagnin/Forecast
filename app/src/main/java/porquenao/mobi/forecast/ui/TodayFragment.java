package porquenao.mobi.forecast.ui;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import porquenao.mobi.forecast.R;
import porquenao.mobi.forecast.core.util.Helper;

public class TodayFragment extends Fragment {

    private static TextView vCity;
    private static TextView vTempMin;
    private static TextView vTempMax;
    private static TextView vAverage;
    private static TextView vClock;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        int resource = Helper.isDay() ? R.layout.today_day : R.layout.today_night;
        View view = inflater.inflate(resource, container, false);

        return view;
    }

    public static void todayWeather(View v) {
        vCity = (TextView) v.findViewById(R.id.city);
        vTempMin = (TextView) v.findViewById(R.id.temp_min);
        vTempMax = (TextView) v.findViewById(R.id.temp_max);
        vAverage = (TextView) v.findViewById(R.id.temp);
        vClock = (TextView) v.findViewById(R.id.time);
    }
}
