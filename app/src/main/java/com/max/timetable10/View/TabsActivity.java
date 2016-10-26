package com.max.timetable10.View;

import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import com.max.timetable10.Model.UserSetups;
import com.max.timetable10.Presenter.TaskFactory;
import com.max.timetable10.R;
import java.io.FileNotFoundException;


public class TabsActivity extends AppCompatActivity {

    public static int downloadBorder;
    public static String  fileName = "newtestcurrent.xls";
    private Toolbar toolbar;
    private TabLayout tabLayout;
    private ViewPager viewPager;
    private UserSetups userSetups;


    public byte a;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tabs);





        userSetups = new UserSetups((byte)0,(byte)0);

        TaskFactory.setDayFragments( new DayFragment[]{
                new DayFragment(WeekDay.MONDAY,userSetups),
                new DayFragment(WeekDay.TUESDAY,userSetups),
                new DayFragment(WeekDay.WEDNESDAY,userSetups),
                new DayFragment(WeekDay.THURSDAY,userSetups),
                new DayFragment(WeekDay.FRIDAY,userSetups),
                new DayFragment(WeekDay.SATURDAY,userSetups)
        });




        downloadBorder = 0;



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
                if(downloadBorder<2) {
                    try {
                        TaskFactory.updateView(openFileInput(fileName),openFileOutput(fileName,MODE_PRIVATE),userSetups);
                    } catch (FileNotFoundException e) {
                        e.printStackTrace();
                    }
                    downloadBorder++;
                }else{
                    Toast.makeText(getApplicationContext(),"File downloading...",Toast.LENGTH_LONG);
                }
                break;
            case R.id.action_item_settings:
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
        adapter.addFragment(TaskFactory.dayFragments[4], "ПЯТ");
        adapter.addFragment(TaskFactory.dayFragments[5], "СУБ");

        viewPager.setAdapter(adapter);
    }



}
