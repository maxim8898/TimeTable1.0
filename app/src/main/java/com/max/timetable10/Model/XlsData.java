package com.max.timetable10.Model;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.util.CellRangeAddress;

import java.io.BufferedInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.util.Iterator;
import java.util.List;


/**
 * Created by Влад on 18.10.2016.
 */

public class XlsData implements ILessonTable {

    private  String URL ;
    public static final String FILE_NAME = "current.xls";

    public XlsData(String URL){
        this.URL = URL;
    }

    public void downloadFile(FileOutputStream outputStream) throws IOException {
        URL url = new URL(this.URL);
        BufferedInputStream bis = new BufferedInputStream(url.openStream());
        FileOutputStream fis = outputStream;
        byte[] buffer = new byte[1024];
        int count=0;
        while((count = bis.read(buffer,0,1024)) != -1)
        {
            fis.write(buffer, 0, count);
        }
        fis.close();
        bis.close();
    }

    public String[][] getLessonTable(InputStream inputStream, int numOfSheet, int COUNT_OF_GROUPS, int COUNT_OF_LESSONS) throws IOException{


        String arr[][] = new String[COUNT_OF_GROUPS][COUNT_OF_LESSONS];

        HSSFWorkbook workBook = new HSSFWorkbook(inputStream);
        HSSFSheet sheet = workBook.getSheetAt(numOfSheet);


        for (int l = 0, k = 5, c = 1; l < COUNT_OF_LESSONS; k++, c++) { //K for shift to start of list of lessons(in the begin useless information)
            if (c % 5 == 0) continue; //Ecsape empty rows

            HSSFRow row = sheet.getRow(k);
            List<CellRangeAddress> mergedRegions = sheet.getMergedRegions();


            for (int i = 0, j = 1; (j < row.getLastCellNum()) && (i < COUNT_OF_GROUPS); i++, j++) {//J for escaping first cell(date)

                HSSFCell buf = row.getCell(j);
                try {
                    if (buf.getStringCellValue().isEmpty() && isCellInMerged(mergedRegions, buf)) {
                        arr[i][l] = arr[i - 1][l];
                    } else {
                        arr[i][l] = buf.getStringCellValue();
                    }
                } catch (IllegalStateException e) {

                } catch (ArrayIndexOutOfBoundsException e) {


                } catch (NullPointerException e) {

                }
            }

            l++;
        }
        return arr;
    }

    private static boolean isCellInMerged(List<CellRangeAddress> mergedRegions, HSSFCell buf){
        Iterator<CellRangeAddress> iter = mergedRegions.iterator();
        while(iter.hasNext()){
            if(iter.next().intersects(new CellRangeAddress
                    (buf.getRowIndex(),buf.getRowIndex(),buf.getColumnIndex(),buf.getColumnIndex())
            )){
                return true;
            }

        }
        return false;
    }


}
