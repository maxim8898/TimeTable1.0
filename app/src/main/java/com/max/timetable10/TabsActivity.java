package com.max.timetable10;

import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import java.util.ArrayList;
import java.util.List;

public class TabsActivity extends AppCompatActivity {

    private Toolbar toolbar;
    private TabLayout tabLayout;
    private ViewPager viewPager;
    private byte Group = 0;

    public static String[][] lessonSheet;
    public byte a;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tabs);

        toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        viewPager = (ViewPager) findViewById(R.id.viewpager);
        setupViewPager(viewPager);

        tabLayout = (TabLayout) findViewById(R.id.tabs);
        tabLayout.setupWithViewPager(viewPager);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        switch (id){
            case R.id.action_item_refresh:
                break;
            case R.id.action_item_settings:
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    public void setupViewPager(ViewPager viewPager){
        ViewPagerAdapter adapter = new ViewPagerAdapter(getSupportFragmentManager());
        adapter.addFragment(new DayFragment(WeekDay.MONDAY, Group, lessonSheet), "ПН");
        adapter.addFragment(new DayFragment(WeekDay.TUESDAY, Group, lessonSheet), "ВТ");
        adapter.addFragment(new DayFragment(WeekDay.SATURDAY, Group, lessonSheet), "СР");
        adapter.addFragment(new DayFragment(WeekDay.WEDNESDAY, Group, lessonSheet), "ЧТ");
        adapter.addFragment(new DayFragment(WeekDay.FRIDAY, Group, lessonSheet), "ПТ");
        adapter.addFragment(new DayFragment(WeekDay.SATURDAY, Group, lessonSheet), "СБ");
        viewPager.setAdapter(adapter);
    }

    class ViewPagerAdapter extends FragmentPagerAdapter{
        private final List<Fragment> mFragmentList = new ArrayList<>();
        private final List<String> mFragmentTitleList = new ArrayList<>();

        public ViewPagerAdapter(FragmentManager manager){super(manager);}

        @Override
        public Fragment getItem(int position){return mFragmentList.get(position);}

        @Override
        public int getCount(){return mFragmentList.size();}

        public void addFragment(Fragment fragment, String title){
            mFragmentList.add(fragment);
            mFragmentTitleList.add(title);
        }

        @Override
        public CharSequence getPageTitle(int position){return mFragmentTitleList.get(position);}

    }
}
