package com.max.timetable10.View;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.max.timetable10.R;

/**
 * Created by Максим on 16.10.2016.
 */

public class DayFragment extends Fragment {

    private byte a;
    public static byte group;
    View frag_view;

    private String[][] LessonSheet;
    Cursor c;
    public static SQLiteDatabase db;

    public DayFragment(WeekDay weekDay){
        //TODO Говорят, что переопределять конструктор фрагмента плохо и надо сделать фабрику
        group = 0;//Здесь работает смена группы, значит массив впорядке



        switch (weekDay){
            case MONDAY: a = 0;
                break;
            case TUESDAY: a = 4;
                break;
            case WEDNESDAY: a = 8;
                break;
            case THURSDAY: a = 12;
                break;
            case FRIDAY: a = 16;
                break;
            case SATURDAY: a = 20;
                break;
        }

    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        frag_view = inflater.inflate(R.layout.fragment_day, container, false);
        updateDataOnViews();
        return frag_view;
    }

    @Override
    public void onResume() {
        super.onResume();
        updateDataOnViews();
    }

    public void setLessonSheet(String[][] lessonSheet) {
        LessonSheet = lessonSheet;
    }

    public void updateDataOnViews(){
        Cursor c = db.query("LessonList", null, null, null, null, null, null);
        c.moveToFirst();
        int idColIndex = c.getColumnIndex("course_id");
        int idSecColIndex = c.getColumnIndex("group_id");
        int idDataColumn = c.getColumnIndex("data");
        do {
            // получаем значения по номерам столбцов и пишем все в лог
            Log.d("myLOG",
                    "Course = " + c.getInt(idColIndex) +
                            ", Group = " + c.getString(idSecColIndex) +
                            ", Data = " + c.getString(idDataColumn));
            // переход на следующую строку
            // а если следующей нет (текущая - последняя), то false - выходим из цикла
        } while (c.moveToNext());


        // c.moveToPosition(6*this.group-1);

        int pos = 24*group;
       // if (pos<0) pos = 0;

        c.moveToPosition(pos);
        TextView one = (TextView) frag_view.findViewById(R.id.textView);
        TextView two = (TextView) frag_view.findViewById(R.id.textView2);
        TextView three = (TextView) frag_view.findViewById(R.id.textView3);
        TextView four = (TextView) frag_view.findViewById(R.id.textView4);
        try{
            c.move(a);

            one.setText(c.getString(idDataColumn));
            c.moveToNext();
            two.setText(c.getString(idDataColumn));
            c.moveToNext();
            three.setText(c.getString(idDataColumn));
            c.moveToNext();
            four.setText(c.getString(idDataColumn));
        }catch (NullPointerException e){

        }
    }
}
