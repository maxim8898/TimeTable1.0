package com.max.timetable10.Presenter;

import android.content.ContentValues;
import android.content.SharedPreferences;
import android.database.sqlite.SQLiteDatabase;
import android.os.AsyncTask;
import android.support.v4.view.ViewPager;

import com.max.timetable10.View.DayFragment;
import com.max.timetable10.Model.XlsData;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

/**
 * Created by Влад on 23.10.2016.
 */

public class AsyncUpdateView extends AsyncTask<SharedPreferences,Void,Void> {

    public DayFragment[] dayFragments;
    XlsData xlsData;
    SQLiteDatabase db;
    ViewPager viewPager;

    public static final String downloadUrl = "http://mftab.brsu.by/?curs=1&download";
    public static final int LessonCount = 24;
    public static final int GroupCount = 6;
    public static int Course;
//TODO для того, чтобы обновить курс, нужно перескачивать расписание, разберись с этим

    public AsyncUpdateView() {
        super();
        dayFragments = null;
        xlsData = null;
        this.Course = 0;
    }


    public AsyncUpdateView(ViewPager vp,SQLiteDatabase db, DayFragment[] dayFragments, FileInputStream AndroidFileInputStream, FileOutputStream AndroidFileOutputStream) {
        super();
        this.dayFragments = dayFragments;
        this.db = db;
        viewPager = vp;
        xlsData = new XlsData(downloadUrl,AndroidFileInputStream,AndroidFileOutputStream);
        Course = 0;
    }

    @Override
    protected Void doInBackground(SharedPreferences... sharedPreferences) {


        try {
            xlsData.downloadFile();
        } catch (IOException e) {
            e.printStackTrace();
        }
            updateDB();

        return null;
    }

    private void updateDB(){

        String[][] lessonSheet = new String[0][];
        try {
            lessonSheet = xlsData.getLessonTable(Course,GroupCount,LessonCount);
        } catch (IOException e) {
            e.printStackTrace();
        }

        db.execSQL("DELETE FROM LessonList");
        ContentValues cv = new ContentValues();
        for (int i = 0; i < lessonSheet.length; i++) {
            for (int j = 0; j < lessonSheet[i].length; j++) {
                cv.put("course_id",i);
                cv.put("group_id",j);
                cv.put("data",lessonSheet[i][j]);

                db.insert("LessonList",null,cv);
            }
        }


    }

    @Override
    protected void onPostExecute(Void aVoid) {
        super.onPostExecute(aVoid);
        int currentItem = viewPager.getCurrentItem();
        dayFragments[currentItem].updateDataOnViews();
        if(currentItem>0) dayFragments[currentItem-1].updateDataOnViews();
        if(currentItem<viewPager.getChildCount()-1) dayFragments[currentItem+1].updateDataOnViews();


    }
}


