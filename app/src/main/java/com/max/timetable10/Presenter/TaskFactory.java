package com.max.timetable10.Presenter;

import android.database.sqlite.SQLiteDatabase;
import android.support.v4.view.ViewPager;

import com.max.timetable10.View.DayFragment;

import java.io.FileInputStream;
import java.io.FileOutputStream;

/**
 * Created by Влад on 23.10.2016.
 */

public class TaskFactory {

    public  static DayFragment[] dayFragments;
    public static ViewPager viewPager;
    public static SQLiteDatabase db;
   public static void setDayFragments(DayFragment[] dayFragments1){
        dayFragments = dayFragments1;
    }


   public static void updateView(FileInputStream AndroidInputStream,FileOutputStream AndroidOutputStream){
       //TODO записать проверку существования файла и прочитать из него, если он есть
       new AsyncUpdateView(viewPager,db,dayFragments,AndroidInputStream,AndroidOutputStream).execute();

   }

}
