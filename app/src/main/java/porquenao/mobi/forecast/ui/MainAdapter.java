package porquenao.mobi.forecast.ui;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.astuetz.PagerSlidingTabStrip;

import porquenao.mobi.forecast.R;

/**
 * Created by lucas on 7/9/15.
 */
public class MainAdapter extends FragmentPagerAdapter implements PagerSlidingTabStrip.IconTabProvider {

    private int tabIcons[] = { R.drawable.tab_today, R.drawable.tab_forecast };
    final int PAGE_COUNT = 2;

    public MainAdapter(FragmentManager fm) {
        super(fm);
    }

    @Override
    public int getCount() {
        return PAGE_COUNT;
    }

    @Override
    public Fragment getItem(int position) {
        return (position == 0) ? new TodayFragment() : new ListFragment();
    }

    @Override
    public int getPageIconResId(int position) {
        return tabIcons[position];
    }
}
