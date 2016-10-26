package com.max.timetable10.Presenter;

import android.os.AsyncTask;

import com.max.timetable10.Model.UserSetups;
import com.max.timetable10.View.DayFragment;
import com.max.timetable10.Model.ILessonTable;
import com.max.timetable10.Model.XlsData;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

/**
 * Created by Влад on 23.10.2016.
 */

public class AsyncUpdateView extends AsyncTask<UserSetups,Void,Void> {

    public DayFragment[] dayFragments;
    ILessonTable xlsData;
    public static final String downloadUrl = "http://mftab.brsu.by/?curs=1&download";
    public static final int LessonCount = 24;
    public static final int GroupCount = 6;
    private int Course;

    public AsyncUpdateView() {
        super();
        dayFragments = null;
        xlsData = null;
        this.Course = 0;
    }

    public AsyncUpdateView(DayFragment[] dayFragments, FileInputStream AndroidFileInputStream,FileOutputStream AndroidFileOutputStream) {
        super();
        this.dayFragments = dayFragments;

        xlsData = new XlsData(downloadUrl,AndroidFileInputStream,AndroidFileOutputStream);
    }

    @Override
    protected Void doInBackground(UserSetups... us) {
        Course = us[0].getCourse();




        try {
            xlsData.downloadFile();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    protected void onPostExecute(Void aVoid) {
        super.onPostExecute(aVoid);
        try {
            String[][] lessonSheet = xlsData.getLessonTable(Course,GroupCount,LessonCount);

            for (int i = 0; i < dayFragments.length; i++) {
                dayFragments[i].setLessonSheet(lessonSheet);
            }


        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}


