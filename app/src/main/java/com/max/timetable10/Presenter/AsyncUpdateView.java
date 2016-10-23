package com.max.timetable10.Presenter;

import android.os.AsyncTask;

import com.max.timetable10.View.DayFragment;
import com.max.timetable10.Model.ILessonTable;
import com.max.timetable10.Model.XlsData;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

/**
 * Created by Влад on 23.10.2016.
 */

public class AsyncUpdateView extends AsyncTask<Void,Void,Void> {

    public DayFragment[] dayFragments;
    ILessonTable xlsData;
    public static final String downloadUrl = "http://mftab.brsu.by/?curs=1&download";
    public AsyncUpdateView() {
        super();
        dayFragments = null;
        xlsData = null;
    }

    public AsyncUpdateView(DayFragment[] dayFragments, FileInputStream AndroidFileInputStream,FileOutputStream AndroidFileOutputStream) {
        super();
        this.dayFragments = dayFragments;
        xlsData = new XlsData(downloadUrl,AndroidFileInputStream,AndroidFileOutputStream);
    }

    @Override
    protected Void doInBackground(Void... voids) {

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
            String[][] lessonSheet = xlsData.getLessonTable(0,6,24);

            for (int i = 0; i < dayFragments.length; i++) {
                dayFragments[i].setLessonSheet(lessonSheet);
            }


        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}


