package com.max.timetable10;

import com.max.timetable10.Model.XlsData;

import org.junit.Before;
import org.junit.Test;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import static org.junit.Assert.*;
/**
 * Created by Влад on 23.10.2016.
 */

public class XlsDataTest {

    XlsData xlsData;
    static final String testFile = "D:\\Android\\AndroidStudioProjects\\TimeTable\\TimeTable1.0\\app\\src\\test\\Mocks\\current.xls";

    @Before
    public void init() throws FileNotFoundException {
        xlsData = new XlsData("",new FileInputStream(testFile),null);
    }

    @Test
    public void parseIsWork() throws Exception{
        String[][] mockLessonTable = new String[1][22];
        //TODO придумай что нибудь с моком

        mockLessonTable[0][0] = "Ф   И   З   И   Ч   Е   С   К   А   Я        К   У   Л   Ь   Т   У   Р   А ";



        String[][] lessonTable = xlsData.getLessonTable(1,6,24);
        //for (int i = 0; i < mockLessonTable[0].length; i++) {
            assertEquals(lessonTable[0][0],mockLessonTable[0][0]);
        //}

    }
}
