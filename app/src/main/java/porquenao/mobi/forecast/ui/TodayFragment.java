package porquenao.mobi.forecast.ui;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.Calendar;

import porquenao.mobi.forecast.R;

public class TodayFragment extends Fragment {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        int resource = getTheme(inflater, container);
        return inflater.inflate(resource, container, false);
    }

    public static int getTheme(LayoutInflater inflater, ViewGroup container) {
        int hour = Calendar.getInstance().get(Calendar.HOUR_OF_DAY);
        if (hour >= 6 && hour <= 16)
            return R.layout.today_day;
        else
            return R.layout.today_night;
    }
}
