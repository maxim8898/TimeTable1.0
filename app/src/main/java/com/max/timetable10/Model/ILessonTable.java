package com.max.timetable10.Model;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;

/**
 * Created by Влад on 18.10.2016.
 */

public interface ILessonTable {
    void downloadFile(FileOutputStream fos) throws IOException;
    public String[][] getLessonTable
            (InputStream inputStream, int numOfSheet, int COUNT_OF_GROUPS, int COUNT_OF_LESSONS) throws IOException;
}
