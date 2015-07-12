package porquenao.mobi.forecast.ui;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

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
    private Context mContext;

    public AdapterWeather(Context context, List<Weather> itens) {
        this.mItens = itens;
        mInflater = LayoutInflater.from(context);
        this.mContext = context;
    }

    public int getCount() { return mItens.size(); }

    public Weather getItem(int position) { return mItens.get(position); }

    public long getItemId(int position) { return position; }

    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder = null;

        LayoutInflater mInflater = (LayoutInflater) mContext.getSystemService(Activity.LAYOUT_INFLATER_SERVICE);
        if (convertView == null) {
            convertView = mInflater.inflate(R.layout.list_item, null);
            holder = new ViewHolder();

            holder.vIcon = (ImageView) convertView.findViewById(R.id.temp_icon);
            holder.vDay = (TextView) convertView.findViewById(R.id.day);
            holder.vDate = (TextView) convertView.findViewById(R.id.date);
            holder.vTempMin = (TextView) convertView.findViewById(R.id.temp_min);
            holder.vTempMax = (TextView) convertView.findViewById(R.id.temp_max);
            holder.vRow = (RelativeLayout) convertView.findViewById(R.id.row);

            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }

        Weather item = mItens.get(position);

        holder.vDay.setText(mDayFormatter.format(item.getDate()));
        holder.vDate.setText(mDateFormatter.format(item.getDate()));
        holder.vTempMin.setText(item.getMin() + "º");
        holder.vTempMax.setText(item.getMax() + "º");
        holder.vRow.setBackgroundResource(item.getTempColor());

        Picasso.with(mContext).load(item.getUrl(true, mContext)).placeholder(item.getTempIcon(true, mContext)).into(holder.vIcon);

        return convertView;
    }

    private class ViewHolder {

        TextView vDay;
        TextView vDate;
        TextView vTempMin;
        TextView vTempMax;
        ImageView vIcon;
        RelativeLayout vRow;
    }

}


