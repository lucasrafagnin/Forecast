package porquenao.mobi.forecast.ui;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.RelativeLayout;
import android.widget.TextView;

import java.text.SimpleDateFormat;
import java.util.List;
import java.util.Locale;

import porquenao.mobi.forecast.R;
import porquenao.mobi.forecast.core.model.Weather;

/**
 * Created by lucas on 7/9/15.
 */
public class AdapterWeather extends BaseAdapter {

    private SimpleDateFormat mDateFormatter = new SimpleDateFormat("dd/MM", Locale.getDefault());
    private SimpleDateFormat mDayFormatter = new SimpleDateFormat("ccc", Locale.getDefault());
    private LayoutInflater mInflater;
    private List<Weather> mItens;

    TextView vDay;
    TextView vDate;
    TextView vTempMin;
    TextView vTempMax;
    RelativeLayout vRow;

    public AdapterWeather(Context context, List<Weather> itens) {
        this.mItens = itens;
        mInflater = LayoutInflater.from(context);
    }

    public int getCount() { return mItens.size(); }

    public Weather getItem(int position) { return mItens.get(position); }

    public long getItemId(int position) { return position; }

    public View getView(int position, View view, ViewGroup parent) {
        view = mInflater.inflate(R.layout.list_item, null);

        Weather item = mItens.get(position);

        //Typeface tf = Typeface.createFromAsset(mContext.getAssets(), "fonts/miso.ttf");
        //teste.setTypeface(tf);

        vDay = (TextView) view.findViewById(R.id.day);
        vDate = (TextView) view.findViewById(R.id.date);
        vTempMin = (TextView) view.findViewById(R.id.temp_min);
        vTempMax = (TextView) view.findViewById(R.id.temp_max);
        vRow = (RelativeLayout) view.findViewById(R.id.row);

        vDay.setText(mDayFormatter.format(item.getDate()));
        vDate.setText(mDateFormatter.format(item.getDate()));
        vTempMin.setText(item.getMin() + "º");
        vTempMax.setText(item.getMax() + "º");
        vRow.setBackgroundResource(item.getTempColor());

        return view;
    }
}


