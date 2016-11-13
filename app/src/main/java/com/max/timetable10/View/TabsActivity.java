package com.max.timetable10.View;

import android.content.Intent;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import com.max.timetable10.DBTest.DBHelper;
import com.max.timetable10.Presenter.AsyncUpdateView;
import com.max.timetable10.Presenter.TaskFactory;
import com.max.timetable10.R;
import java.io.FileNotFoundException;


public class TabsActivity extends AppCompatActivity {
    @Override
    protected void onResume() {
        super.onResume();
        updateSettings();
    }

    void updateSettings(){
        DayFragment.group = Byte.parseByte(sPref.getString("Group","0"));
        AsyncUpdateView.Course = Byte.parseByte(sPref.getString("Course","0"));

    }




    public static int downloadBorder;
    public static String  fileName = "newtestcurrent.xls";
    private Toolbar toolbar;
    private TabLayout tabLayout;
    private ViewPager viewPager;
    SharedPreferences sPref;
    public byte a;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tabs);

        PreferenceManager.setDefaultValues(this,R.xml.pref,false);

        sPref = PreferenceManager.getDefaultSharedPreferences(this);


        TaskFactory.db = new DBHelper(this).getWritableDatabase();


        DayFragment.db = TaskFactory.db;
        TaskFactory.setDayFragments( new DayFragment[]{
                new DayFragment(WeekDay.MONDAY),
                new DayFragment(WeekDay.TUESDAY),
                new DayFragment(WeekDay.WEDNESDAY),
                new DayFragment(WeekDay.THURSDAY),
                new DayFragment(WeekDay.FRIDAY),
                new DayFragment(WeekDay.SATURDAY)
        });

        updateSettings();

        downloadBorder = 0;



        toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        viewPager = (ViewPager) findViewById(R.id.viewpager);
        setupViewPager(viewPager);
        TaskFactory.viewPager = viewPager;


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

                if(downloadBorder<2) {

                    try {
                        TaskFactory.updateView(openFileInput(fileName),openFileOutput(fileName,MODE_PRIVATE));


                    } catch (FileNotFoundException e) {
                        e.printStackTrace();
                    }
                    downloadBorder++;

                }else{
                    Toast.makeText(this,"File downloading...",Toast.LENGTH_LONG);
               }
                break;
            case R.id.action_item_settings:
                startActivity(new Intent(this,SettingsActivity.class));
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    public void setupViewPager(ViewPager viewPager){
        ViewPagerAdapter adapter = new ViewPagerAdapter(getSupportFragmentManager());

        adapter.addFragment(TaskFactory.dayFragments[0], "ПН");
        adapter.addFragment(TaskFactory.dayFragments[1], "ВТ");
        adapter.addFragment(TaskFactory.dayFragments[2], "СР");
        adapter.addFragment(TaskFactory.dayFragments[3], "ЧТ");
        adapter.addFragment(TaskFactory.dayFragments[4], "ПТ");
        adapter.addFragment(TaskFactory.dayFragments[5], "СБ");

        viewPager.setAdapter(adapter);
    }



}
