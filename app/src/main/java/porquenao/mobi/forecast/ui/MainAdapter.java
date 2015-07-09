package porquenao.mobi.forecast.ui;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

/**
 * Created by lucas on 7/9/15.
 */
public class MainAdapter extends FragmentPagerAdapter {

    public MainAdapter(FragmentManager fm) {
        super(fm);
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return (position == 0) ? "Today" : "Forecast";
    }

    @Override
    public int getCount() {
        return 2;
    }

    @Override
    public Fragment getItem(int position) {
        return (position == 0) ? new TodayFragment() : new ListFragment();
    }
}
