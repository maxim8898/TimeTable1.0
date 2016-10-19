package com.max.timetable10;

import android.os.AsyncTask;
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

import com.max.timetable10.Model.ILessonTable;
import com.max.timetable10.Model.XlsData;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class TabsActivity extends AppCompatActivity {

    public static int downloadBorder;
    public ILessonTable xlsData;
    public static String  fileName = "newtestcurrent.xls";
    private static DownloadTask downloadTask;
    private Toolbar toolbar;
    private TabLayout tabLayout;
    private ViewPager viewPager;
    private byte Group = 0;
    static DayFragment MondayFragment;

    public static String[][] lessonSheet = new String[1][24];
    public byte a;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tabs);

        downloadBorder = 0;
        xlsData = new XlsData
                ("https://psv4.vk.me/c810235/u163830924/docs/ad86fae96c92/current.xls?extra=WGivnWGkkDocYO4Psc7GGjfhghta6s39b_5-j-JNbtPwLp284lI7MsJnMQBOX20xBBIYqdt0TwsISj4fFipDrkLdzCSFSXxDKthRQPuqDdUXkc1deK19Aex3XA");

        DownloadTask downloadTask = new DownloadTask();

        toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        viewPager = (ViewPager) findViewById(R.id.viewpager);
        setupViewPager(viewPager);

        tabLayout = (TabLayout) findViewById(R.id.tabs);
        tabLayout.setupWithViewPager(viewPager);

        lessonSheet[Group][0] = "Ф И З И Ч Е С К А Я   К У Л Ь Т У Р А";
        lessonSheet[Group][1] = "Экономическая теория (пр)\nЖук Г.В. 412";
        lessonSheet[Group][2] = "Математический анализ (пр)\nГрицук Е.В. 412";
        lessonSheet[Group][3] = "Математический анализ\nГрицук Е.В. 502";
        lessonSheet[Group][4] = "\n";
        lessonSheet[Group][5] = "(2) ПРОГ\nКозинский А.А. 616";
        lessonSheet[Group][6] = "(1) ПРОГ Островкий А.С. 712\n(2) ДММЛ Кондратюк А.П. 601";
        lessonSheet[Group][7] = "Вычислительные методы алгебры\nМатысик О.В. 614";
        lessonSheet[Group][8] = "Ф И З И Ч Е К С А Я   К У Л Ь Т У Р А\n";
        lessonSheet[Group][9] = "Программирование\nКозинский А.А. 402";
        lessonSheet[Group][10] = "(1) ВМА Матысик О.В. 616\n(2) ПРОГ Козинский А.А. 620";
        lessonSheet[Group][11] = "(1) ПРОГ Островкий А.С. 714\n(2) ДММЛ Кондратюк А.П. 601";
        lessonSheet[Group][12] = "Экономическая теория\nМакаревич 617";
        lessonSheet[Group][13] = "(1) ВМА Матысик О.В. 616\n(2)ВМА Худяков А.П. 714";
        lessonSheet[Group][14] = "(1) ПРОГ Островский А.С. 714\n(2) ПРОГ Козинский А.А. 620";
        lessonSheet[Group][15] = "Математический анализ\nГрицук Е.В. 502";
        lessonSheet[Group][16] = "Иностранный язык\nКравченко Т.В. 305";
        lessonSheet[Group][17] = "Дискретная математика и математическая логика\nБудько А.Е. 402";
        lessonSheet[Group][18] = "Дифференциальные уравнения\nМельникова И.Н. 502";
        lessonSheet[Group][19] = "(1) ДММЛ Будько А.Е. 502\n";
        lessonSheet[Group][20] = "Дифференциальные уравнения\nЧичурин А.В. 502";
        lessonSheet[Group][21] = "Математический анализ\nГрицук Е.В. 402";
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
                    new DownloadTask().execute();
                    downloadBorder++;
                }
                break;
            case R.id.action_item_settings:
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    public void setupViewPager(ViewPager viewPager){
        ViewPagerAdapter adapter = new ViewPagerAdapter(getSupportFragmentManager());
        MondayFragment =new DayFragment(WeekDay.MONDAY, Group, lessonSheet);
        adapter.addFragment(MondayFragment, "ПН");
        adapter.addFragment(new DayFragment(WeekDay.TUESDAY, Group, lessonSheet), "ВТ");
        adapter.addFragment(new DayFragment(WeekDay.WEDNESDAY, Group, lessonSheet), "СР");
        adapter.addFragment(new DayFragment(WeekDay.THURSDAY, Group, lessonSheet), "ЧТ");
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

    public class DownloadTask extends AsyncTask<Void,Void,Void> {

        @Override
        protected Void doInBackground(Void... voids) {

            try {
                xlsData.downloadFile(openFileOutput(fileName,MODE_PRIVATE));
            } catch (IOException e) {
                e.printStackTrace();
            }

            return null;
        }

        @Override
        protected void onPostExecute(Void aVoid) {
            super.onPostExecute(aVoid);
            try {
                updateView();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public void updateView() throws IOException {
        MondayFragment.setLessonSheet(xlsData.getLessonTable(openFileInput(fileName),0,6,24));
    }
}
