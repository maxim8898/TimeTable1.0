package com.max.timetable10;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

/**
 * Created by Максим on 16.10.2016.
 */

public class DayFragment extends Fragment {

    private byte a, group;

    private String[][] LessonSheet;

    public DayFragment(WeekDay weekDay, byte group, String[][] LessonSheet){
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
        this.LessonSheet = LessonSheet;
        this.group = group;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_day, container, false);
        TextView one = (TextView) view.findViewById(R.id.textView);
        TextView two = (TextView) view.findViewById(R.id.textView2);
        TextView three = (TextView) view.findViewById(R.id.textView3);
        TextView four = (TextView) view.findViewById(R.id.textView4);
        try{
            one.setText(LessonSheet[this.group][a]);
            two.setText(LessonSheet[this.group][a + 1]);
            three.setText(LessonSheet[this.group][a + 2]);
            four.setText(LessonSheet[this.group][a + 3]);
        }catch (NullPointerException e){

        }
        return view;
    }

    public void setLessonSheet(String[][] lessonSheet) {
        LessonSheet = lessonSheet;
    }
}
