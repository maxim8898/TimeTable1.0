package com.max.timetable10.Presenter;

import com.max.timetable10.View.DayFragment;

import java.io.FileInputStream;
import java.io.FileOutputStream;

/**
 * Created by Влад on 23.10.2016.
 */

public class TaskFactory {

    public  static DayFragment[] dayFragments;

   public static void setDayFragments(DayFragment[] dayFragments1){
        dayFragments = dayFragments1;
    }

   public static void updateView(FileInputStream AndroidInputStream,FileOutputStream AndroidOutputStream){
       new AsyncUpdateView(dayFragments,AndroidInputStream,AndroidOutputStream).execute();
    }

}
