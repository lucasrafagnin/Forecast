package porquenao.mobi.forecast.ui;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

import porquenao.mobi.forecast.R;
import porquenao.mobi.forecast.core.model.Weather;

public class ListFragment extends Fragment {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View rootView = inflater.inflate(R.layout.forecast, container, false);

        final ListView listview = (ListView) rootView.findViewById(R.id.list);
        Weather a = new Weather();
        Weather b = new Weather();

        final List<Weather> list = new ArrayList<Weather>();
        list.add(a);
        list.add(b);

        final AdapterWeather adapter = new AdapterWeather(rootView.getContext(), list);
        listview.setAdapter(adapter);

        return rootView;
    }
}
