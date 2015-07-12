package porquenao.mobi.forecast.ui;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import java.util.List;

import porquenao.mobi.forecast.R;
import porquenao.mobi.forecast.core.dao.WeatherDAO;
import porquenao.mobi.forecast.core.model.Weather;

public class ListFragment extends Fragment {

    private ListView vWeathers;
    private AdapterWeather mAdapter;
    private WeatherDAO sWeatherDao;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        final View rootView = inflater.inflate(R.layout.forecast, container, false);
        vWeathers = (ListView) rootView.findViewById(R.id.list);

        loadWeathers(rootView.getContext());

        return rootView;
    }

    public void loadWeathers(final Context context) {
        sWeatherDao = WeatherDAO.getInstance(context);
        List<Weather> weathers = sWeatherDao.getAll();

        mAdapter = new AdapterWeather(context, weathers);
        vWeathers.setAdapter(mAdapter);
    }
}
