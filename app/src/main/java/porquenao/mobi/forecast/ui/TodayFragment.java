package porquenao.mobi.forecast.ui;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.Calendar;
import java.util.List;

import porquenao.mobi.forecast.R;
import porquenao.mobi.forecast.core.dao.WeatherDAO;
import porquenao.mobi.forecast.core.model.Weather;
import porquenao.mobi.forecast.core.util.Helper;

public class TodayFragment extends Fragment {

    private TextView vCity;
    private TextView vTempMin;
    private TextView vTempMax;
    private TextView vAverage;
    private TextView vClock;
    private ImageView vIcon;
    private View vDividerT;
    private View vDividerM;
    private WeatherDAO sWeatherDao;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        int resource = Helper.isDay() ? R.layout.today_day : R.layout.today_night;
        View view = inflater.inflate(resource, container, false);

        findViews(view);

        sWeatherDao = WeatherDAO.getInstance(view.getContext());
        todayWeather(sWeatherDao.getAll(), view.getContext());
        return view;
    }

    public void todayWeather(List<Weather> weathers, Context context) {
        Weather weather;
        if (weathers != null && weathers.size() > 0) {
            weather = weathers.get(0);
            vCity.setText(weather.getCity());
            vTempMin.setText(weather.getMin() + "º");
            vTempMax.setText(weather.getMax() + "º");
            vAverage.setText(weather.getAverage() + "º");
            Calendar now = Calendar.getInstance();
            vClock.setText(now.get(Calendar.HOUR_OF_DAY) + "h" + now.get(Calendar.MINUTE));
            Picasso.with(context).load(weather.getUrl(false, context)).placeholder(weather.getTempIcon(false, context)).into(vIcon);
        } else {
            vDividerT.setVisibility(View.GONE);
            vDividerM.setVisibility(View.GONE);
        }
    }

    public void findViews(View view) {
        vCity = (TextView) view.findViewById(R.id.city);
        vTempMin = (TextView) view.findViewById(R.id.temp_min);
        vTempMax = (TextView) view.findViewById(R.id.temp_max);
        vAverage = (TextView) view.findViewById(R.id.temp);
        vClock = (TextView) view.findViewById(R.id.time);
        vIcon = (ImageView) view.findViewById(R.id.icon);
        vDividerT = (View) view.findViewById(R.id.temp_divider);
        vDividerM = (View) view.findViewById(R.id.main_divider);
    }

}
