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
        String[][] mockLessonTable = new String[1][24];

        //Для первой группы первого курса
        mockLessonTable[0][0] ="АНАЛИТИЧЕСКАЯ ГЕОМЕТРИЯ И ПРЕОБРАЗОВАНИЯ ПЛОСКОСТИ (ПР)\n" +
                "СЕРАЯ З.Н.                 602";
        mockLessonTable[0][1]="Ф   И   З   И   Ч   Е   С   К   А   Я        К   У   Л   Ь   Т   У   Р   А ";
        mockLessonTable[0][2]="ИНОСТРАННЫЙ ЯЗЫК                                         БАХУР И.Н.  412    ДОМБРОВСКАЯ Н.Н.  505   КАЛИЛЕЦ Л.М. 420 \n" +
                "   ИВАНЮК Н.В.  502   КОВАЛЕНКО О.Н.  602  КРАВЧЕНКО Т.В.  704    ЛЕВОНЮК Ю.Е. 608  МИЛАЧ С.В. 301  ТРОЦЮК Т.С.   606";
        mockLessonTable[0][3] ="\n" +
                "\n" +
                "(3) ТПМА ТКАЧ С.Н.                                          616";
        mockLessonTable[0][4] ="";
        mockLessonTable[0][5]="ВВЕДЕНИЕ В ПЕДАГОГИЧЕСКУЮ ПРОФЕССИЮ\n" +
                "доц. ИВАНОВ Ю.А.                                                                    502";
        mockLessonTable[0][6]="МАТЕМАТИЧЕСКАЯ ЛОГИКА И ДИСКРЕТНАЯ МАТЕМАТИКА (ПР)\n" +
                "БУДЬКО А.Е.                         502";
        mockLessonTable[0][7]="МАТЕМАТИЧЕСКИЙ АНАЛИЗ (ПР)\n" +
                "МЕЛЬНИКОВА И.Н.            608";
        mockLessonTable[0][8]="ВВЕДЕНИЕ В ПЕДАГОГИЧЕСКУЮ ПРОФЕССИЮ (ПР)\n" +
                "ЛВРЕЕНКО Л.В.                                  602";
        mockLessonTable[0][9]="Ф   И   З   И   Ч   Е   С   К   А   Я        К   У   Л   Ь   Т   У   Р   А ";
        mockLessonTable[0][10]="ИНОСТРАННЫЙ ЯЗЫК                                         БАХУР И.Н.  412    ДОМБРОВСКАЯ Н.Н.  603   КАЛИЛЕЦ Л.М. 420 \n" +
                "   ИВАНЮК Н.В.  501   КОВАЛЕНКО О.Н.  502  КРАВЧЕНКО Т.В.  602    ЛЕВОНЮК Ю.Е. 608  МИЛАЧ С.В. 301 ТРОЦЮК Т.С.   606";
        mockLessonTable[0][11]="(1) ТПМА ТКАЧ С.Н.      616";
        mockLessonTable[0][12]="\n(2) ТПМА ТКАЧ С.Н.                                        616\n";
        mockLessonTable[0][13]="(1) ТПМА ТКАЧ С.Н.                                     616\n" +
                "(2) ИТ КОТОВ И.В.                                        503\n";
        mockLessonTable[0][14]="(1) ИТ ГАЦКЕВИЧ О.А.                              503\n" +
                "\n" +
                "(3) ТПМА ТКАЧ С.Н.                                        616";
        mockLessonTable[0][15]="\n\n(3) ИТ ТКАЧ С.Н.                                          616";
        mockLessonTable[0][16]="ИНОСТРАННЫЙ ЯЗЫК                                         БАХУР И.Н.  502    ДОМБРОВСКАЯ Н.Н.  603   КАЛИЛЕЦ Л.М. 420 \n" +
                "   ИВАНЮК Н.В.  501   КОВАЛЕНКО О.Н.  704  КРАВЧЕНКО Т.В.  700    ЛЕВОНЮК Ю.Е. 608  МИЛАЧ С.В. 301 ТРОЦЮК Т.С.   614";
        mockLessonTable[0][17]="ПСИХОЛОГИЯ (ЛАБ)\n" +
                "ЯРМОЛЬЧИК Е.В.               601";
        mockLessonTable[0][18]="ПСИХОЛОГИЯ (ПР)\n" +
                "ЯРМОЛЬЧИК Е.В.                                       602";
        mockLessonTable[0][19]="";
        mockLessonTable[0][20]="";
        mockLessonTable[0][21]="";
        mockLessonTable[0][22]="";
        mockLessonTable[0][23]="";


        //TODO И всё таки массив в коде мне не очень нравится
        String[][] actualResult = xlsData.getLessonTable(0,6,24);


        for (int i = 0; i < actualResult[0].length; i++) {
            assertEquals(mockLessonTable[0][i],actualResult[0][i]);

        }

    }
}
