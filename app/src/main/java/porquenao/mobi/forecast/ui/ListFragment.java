package porquenao.mobi.forecast.ui;

import android.content.Context;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import java.util.List;

import porquenao.mobi.forecast.R;
import porquenao.mobi.forecast.core.model.Weather;
import porquenao.mobi.forecast.core.service.ForecastAdapter;
import porquenao.mobi.forecast.core.service.ForecastClient;

public class ListFragment extends Fragment {

    private ListView vWeathers;
    private AdapterWeather mAdapter;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        final View rootView = inflater.inflate(R.layout.forecast, container, false);
        vWeathers = (ListView) rootView.findViewById(R.id.list);

        getAllWeathers(rootView.getContext());

        return rootView;
    }

    public void getAllWeathers(final Context context) {
        new AsyncTask<Void, Void, List<Weather>>() {
            @Override
            protected List<Weather> doInBackground(Void... params) {
                String API_URL = "http://forecastpqn.parseapp.com/";
                ForecastClient client = ForecastAdapter.createService(ForecastClient.class, API_URL);

                return client.temperatures("15");
            }

            @Override
            protected void onPostExecute(List<Weather> weathers) {
                super.onPostExecute(weathers);
                mAdapter = new AdapterWeather(context, weathers);
                vWeathers.setAdapter(mAdapter);
            }
        }.execute();
    }

}
