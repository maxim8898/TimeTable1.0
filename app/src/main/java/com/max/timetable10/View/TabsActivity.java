package com.max.timetable10.View;

import android.app.Activity;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

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
        setContentView(R.layout.main_activity);


        xlsData = new XlsData
                ("https://psv4.vk.me/c810235/u163830924/docs/0ba9d53d634d/current.xls?extra=pqVsnJAjT_3Cs_3LLt8UklaoytWlZy9KJ5Dvao7xtx5mX_ELB-JFFhm-sWypzM_h5O6SyAEeWi0JJQXs1SP6xf-ZAv43ZtcFtvZKUFluf_oEo-X59mDYipf1");

        DownloadTask downloadTask = new DownloadTask();

        txtView = (TextView)findViewById(R.id.textView);
        btn = (Button)findViewById(R.id.button);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                new DownloadTask().execute();

            }
        });
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
            case R.id.button:
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
