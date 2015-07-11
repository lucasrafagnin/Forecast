package porquenao.mobi.forecast;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.Window;
import android.widget.LinearLayout;

import com.astuetz.PagerSlidingTabStrip;

import java.lang.reflect.Field;

import porquenao.mobi.forecast.core.util.Helper;
import porquenao.mobi.forecast.ui.MainAdapter;

public class MainActivity extends FragmentActivity {

    private static PagerSlidingTabStrip sTabs;
    private static LinearLayout sToolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);

        ViewPager pager = (ViewPager) findViewById(R.id.pager);
        pager.setAdapter(new MainAdapter(getSupportFragmentManager()));

        sToolbar = (LinearLayout) findViewById(R.id.toolbar);
        sTabs = (PagerSlidingTabStrip) findViewById(R.id.tabs_bar);
        sTabs.setViewPager(pager);

        setTabsIcon(getApplicationContext(), getWindow());
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
