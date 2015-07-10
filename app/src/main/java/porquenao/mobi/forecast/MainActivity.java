package porquenao.mobi.forecast;

import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.support.v4.view.ViewPager;

import com.astuetz.PagerSlidingTabStrip;

import porquenao.mobi.forecast.ui.MainAdapter;

public class MainActivity extends FragmentActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);

        ViewPager pager = (ViewPager) findViewById(R.id.pager);
        pager.setAdapter(new MainAdapter(getSupportFragmentManager()));

        PagerSlidingTabStrip tabs = (PagerSlidingTabStrip) findViewById(R.id.tabs_bar);
        tabs.setViewPager(pager);

    }

}
