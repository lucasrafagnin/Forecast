package porquenao.mobi.forecast.ui;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import java.util.List;

import porquenao.mobi.forecast.R;
import porquenao.mobi.forecast.core.model.Weather;

/**
 * Created by lucas on 7/9/15.
 */
public class AdapterWeather extends BaseAdapter {

    private LayoutInflater mInflater;
    private List<Weather> itens;

    public AdapterWeather(Context context, List<Weather> itens) {
        this.itens = itens;
        mInflater = LayoutInflater.from(context);
    }

    public int getCount() { return itens.size(); }

    public Weather getItem(int position) { return itens.get(position); }

    public long getItemId(int position) { return position; }

    public View getView(int position, View view, ViewGroup parent) {
        view = mInflater.inflate(R.layout.list_item, null);
        return view;
    }
}


