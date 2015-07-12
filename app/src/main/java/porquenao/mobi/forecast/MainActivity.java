package porquenao.mobi.forecast;

import android.app.ProgressDialog;
import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.Window;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.astuetz.PagerSlidingTabStrip;

import java.lang.reflect.Field;
import java.util.List;

import porquenao.mobi.forecast.core.dao.WeatherDAO;
import porquenao.mobi.forecast.core.model.Weather;
import porquenao.mobi.forecast.core.service.ForecastAdapter;
import porquenao.mobi.forecast.core.service.ForecastClient;
import porquenao.mobi.forecast.core.util.Helper;
import porquenao.mobi.forecast.ui.MainAdapter;

public class MainActivity extends FragmentActivity {

    private static PagerSlidingTabStrip sTabs;
    private static LinearLayout sToolbar;
    private static WeatherDAO sWeatherDao;
    private static ViewPager pager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);

        getAllWeathers(getApplicationContext());

        pager = (ViewPager) findViewById(R.id.pager);
        sToolbar = (LinearLayout) findViewById(R.id.toolbar);
        sTabs = (PagerSlidingTabStrip) findViewById(R.id.tabs_bar);
    }

    public void getAllWeathers(final Context context) {
        ConnectivityManager connManager = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
        final NetworkInfo mWifi = connManager.getNetworkInfo(ConnectivityManager.TYPE_WIFI);
        final ProgressDialog dialog = ProgressDialog.show(MainActivity.this, getResources().getString(R.string.loading_title), getResources().getString(R.string.loading_message), true);

        new AsyncTask<Void, Void, Boolean>() {
            @Override
            protected void onPreExecute() {
                dialog.show();
            }

            @Override
            protected Boolean doInBackground(Void... params) {
                try {
                    if (mWifi.isConnected()) {
                        sWeatherDao = WeatherDAO.getInstance(context);
                        ForecastClient client = ForecastAdapter.createService(ForecastClient.class, getResources().getString(R.string.api_url));
                        List<Weather> weathers = client.weathers(getResources().getString(R.string.api_qnty));
                        sWeatherDao.insert(weathers);
                        return true;
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                    return false;
                }
                return false;
            }

            @Override
            protected void onPostExecute(Boolean connected) {
                if (!connected) {
                    Toast.makeText(context, getResources().getString(R.string.api_error), Toast.LENGTH_SHORT).show();
                }
                pager.setAdapter(new MainAdapter(getSupportFragmentManager()));
                sTabs.setViewPager(pager);
                setTabsIcon(getApplicationContext(), getWindow());
                dialog.dismiss();
            }
        }.execute();
    }

    public static void setTabsIcon(Context context, Window window) {
        if (Helper.isDay()) {
            sToolbar.setBackgroundColor(context.getResources().getColor(R.color.tabs_day));
            window.setStatusBarColor(context.getResources().getColor(R.color.status_bar_day));
        } else {
            sToolbar.setBackgroundColor(context.getResources().getColor(R.color.tabs_night));
            window.setStatusBarColor(context.getResources().getColor(R.color.status_bar_night));
        }

        Field field = null;
        try {
            field = PagerSlidingTabStrip.class.getDeclaredField("tabsContainer");
            field.setAccessible(true);
            LinearLayout tabsContainer = (LinearLayout) field.get(sTabs);
            tabsContainer.getChildAt(0).setSelected(true);

        } catch (NoSuchFieldException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }

        sTabs.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {

            private int currentPageSelected = 0;

            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                Log.v("onPageSelected", sTabs.getClass().toString());

                Field field = null;
                try {
                    field = PagerSlidingTabStrip.class.getDeclaredField("tabsContainer");
                    field.setAccessible(true);
                    LinearLayout tabsContainer = (LinearLayout) field.get(sTabs);
                    tabsContainer.getChildAt(currentPageSelected).setSelected(false);
                    currentPageSelected = position;
                    tabsContainer.getChildAt(position).setSelected(true);

                } catch (NoSuchFieldException e) {
                    e.printStackTrace();
                } catch (IllegalAccessException e) {
                    e.printStackTrace();
                }

            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
    }

}
