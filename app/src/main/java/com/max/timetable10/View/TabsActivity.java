package com.max.timetable10.View;

import android.app.Activity;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.max.timetable10.Model.ILessonTable;
import com.max.timetable10.Model.XlsData;
import com.max.timetable10.R;

import java.io.IOException;
import java.util.Arrays;

public class TabsActivity extends Activity implements ILessonView,View.OnClickListener{

    public ILessonTable xlsData;
    public static String  fileName = "newtestcurrent.xls";
    private static DownloadTask downloadTask;
    TextView txtView;
    Button btn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tabs);


        xlsData = new XlsData
                ("https://psv4.vk.me/c810235/u163830924/docs/ad86fae96c92/current.xls?extra=WGivnWGkkDocYO4Psc7GGjfhghta6s39b_5-j-JNbtPwLp284lI7MsJnMQBOX20xBBIYqdt0TwsISj4fFipDrkLdzCSFSXxDKthRQPuqDdUXkc1deK19Aex3XA");

        DownloadTask downloadTask = new DownloadTask();

        txtView = (TextView)findViewById(R.id.textView);
        btn = (Button)findViewById(R.id.action_item_refresh);
    }



    @Override
    public void updateView() {
        try {
        //    if(openFileInput(fileName)==null) txtView.setText("NULL");



           txtView.setText(Arrays.toString(xlsData.getLessonTable(openFileInput(fileName),1,6,24)[0]));
        } catch (IOException e) {
            txtView.setText("File not exist");
            e.printStackTrace();
        }catch (NullPointerException e){
            e.printStackTrace();
            txtView.setText("NULL");
        }
    }

    @Override
    public void onClick(View view) {
        switch(view.getId()){
            case R.id.action_item_refresh:
                downloadTask.execute();
                    break;
        }
    }

    public class DownloadTask extends AsyncTask<Void,Void,Void>{

        @Override
        protected Void doInBackground(Void... voids) {

            try {
                xlsData.downloadFile(openFileOutput(fileName,MODE_PRIVATE));
            } catch (IOException e) {
                e.printStackTrace();
            }

            return null;
        }

        @Override
        protected void onPostExecute(Void aVoid) {
            super.onPostExecute(aVoid);
            updateView();
        }
    }
}
