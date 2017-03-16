package com.sr.thextest.adapter.event;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;

 import com.sr.thextest.R;
import com.sr.thextest.Database.SQLiteDatabaseHelper;

import java.util.ArrayList;

public class ListViewEventActivity extends AppCompatActivity {

    SQLiteDatabaseHelper SQLITEHELPER;
    SQLiteDatabase SQLITEDATABASE;
    Cursor cursor;
    SQLiteListEventAdapter ListAdapter ;


    ArrayList<String> event_type_ArrayList = new ArrayList<String>();
    ArrayList<String> event_des_ArrayList = new ArrayList<String>();
    ListView LISTVIEW;
    Button buuplist;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.event_list_adapter);



        LISTVIEW = (ListView) findViewById(R.id.listev);

        SQLITEHELPER = new SQLiteDatabaseHelper(this);


                ShowSQLiteDBdata() ;



    }

    @Override
    protected void onResume() {


        super.onResume();
    }

    private void ShowSQLiteDBdata() {

        new CountDownTimer(1000, 800) {

            public void onTick(long millisUntilFinished) {
                findViewById(R.id.loadingPanelev).setVisibility(View.VISIBLE);
            }

            public void onFinish() {


                SQLITEDATABASE = SQLITEHELPER.getWritableDatabase();

                cursor = SQLITEDATABASE.rawQuery("SELECT * FROM event", null);


                event_type_ArrayList.clear();
                event_des_ArrayList.clear();

                if (cursor.moveToFirst()) {
                    do {

                        Log.i("SR", "DATALISTevent");


                        event_type_ArrayList.add(cursor.getString(cursor.getColumnIndex(SQLiteDatabaseHelper.event_type)));

                        event_des_ArrayList.add(cursor.getString(cursor.getColumnIndex(SQLiteDatabaseHelper.event_det)));


                    } while (cursor.moveToNext());
                }

                ListAdapter = new SQLiteListEventAdapter(ListViewEventActivity.this,

                        event_type_ArrayList,
                        event_des_ArrayList

                );

                LISTVIEW.setAdapter(ListAdapter);

                cursor.close();
                findViewById(R.id.loadingPanelev).setVisibility(View.GONE);

            }
        }.start();





    }
}
