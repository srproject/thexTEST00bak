package com.sr.thextest.Database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;

/**
 * Created by sr on 3/15/17.
 */

public class SQLiteDatabaseHelper extends SQLiteOpenHelper {
    public static  final String DATABASE_NAME="data.db";

    //EVENT Table

    public static final String event_TABLE="event";
    public static  final String event_id="event_id";
    public static  final String event_type="event_type";
    public static  final String event_time="event_time";
    public static  final String event_date="event_date";
    public static  final String event_latitude="event_latitude";
    public static  final String event_longitude="event_longitude";
    public static  final String event_locnam="event_locnam";
    public static  final String event_det="event_det";
    public static  final String event_photo="event_photo";

    //ACCOUNT Table

    public static final String account_TABLE="account";
    public static  final String account_id="account_id";
    public static  final String account_type="account_type";
    public static  final String account_email="account_email";
    public static  final String account_phone="account_phone";
    public static  final String account_password="account_password";



    //PROFILE FOR ACCOUNT Table

    public static final String profile_TABLE="profile";
    public static  final String profile_id="profile_id";
    public static  final String profile_account_id="profile_account_id";
    public static  final String profile_name="profile_name";
    public static  final String profile_sex="profile_sex";
    public static  final String profile_age="profile_age";








    public SQLiteDatabaseHelper(Context context ) {
        super(context, DATABASE_NAME, null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

        db.execSQL("create table event (event_id INTEGER PRIMARY KEY AUTOINCREMENT" +
                ", event_type VARCHAR ,event_time VARCHAR " +
                ",event_date VARCHAR ,event_latitude VARCHAR " +
                ",event_longitude VARCHAR " +
                ",event_locnam VARCHAR " +
                ",event_det VARCHAR , event_photo BLOB) ;" );


        // db.execSQL("create table * (ID INTEGER PRIMARY KEY AUTOINCREMENT, NAME VARCHAR ,LON VARCHAR ,LAT VARCHAR) ;  " );



    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {


        db.execSQL("create table IF NOT EXISTS event (event_id INTEGER PRIMARY KEY AUTOINCREMENT" +
                ", event_type VARCHAR ,event_time VARCHAR " +
                ",event_date VARCHAR ,event_latitude VARCHAR " +
                ",event_longitude VARCHAR " +
                ",event_det VARCHAR , event_photo BLOB) ;" );




      //  db.execSQL("create table IF EXISTS * (ID INTEGER PRIMARY KEY AUTOINCREMENT, NAME VARCHAR ,LON VARCHAR ,LAT VARCHAR) ;  " );


    }




    public boolean insertDataforevent (String ievent_type,String ievent_time,String ievent_date
            ,String ievent_latitude,String ievent_longitude,String ievent_locnam,String ievent_det,String ievent_photo) {




        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(event_type, ievent_type);
        contentValues.put(event_time, ievent_time);
        contentValues.put(event_date, ievent_date);
        contentValues.put(event_latitude, ievent_latitude);
        contentValues.put(event_longitude, ievent_longitude);
        contentValues.put(event_locnam, ievent_locnam);
        contentValues.put(event_det, ievent_det);
        contentValues.put(event_photo, ievent_photo);






        long result = db.insert("event", null, contentValues);
        if (result == -1) {
            return false;
        }
        else {
            return true;
        }
    }


    //function to show the data */

    public Cursor getAllDataforeventtest() {
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor res= db.rawQuery("select * from event ",null);
        return  res;

    }

    /* function to updata data */

    public boolean updataData(String name,String lon,String lat,String id){
        SQLiteDatabase db = this.getWritableDatabase();


      //  String UpdateRecordQuery = "UPDATE "+event_TABLE+" SET NAME='" + name + "', LON='" + lon + "', LAT='" + lat + "' WHERE ID=" + id + ";";



       // db.execSQL(UpdateRecordQuery);

        return true;
    }
    /* function to delet Data data */

    public boolean deletData(String id){
        SQLiteDatabase db = this.getWritableDatabase();


       // String UpdateRecordQuery = "DELETE FROM "+event_TABLE+" WHERE ID = "+id+";";



     //   db.execSQL(UpdateRecordQuery);

        return true;
    }
    /* function to delet all Data data */

    public boolean deletallData( ){
        SQLiteDatabase db = this.getWritableDatabase();


        //String UpdateRecordQuery = "DELETE FROM "+TABLE_NAME;


//        db.execSQL(UpdateRecordQuery);

        return true;
    }

    public static void copyDatabase(Context c, String DATABASE_NAME) {
        String databasePath = c.getDatabasePath(DATABASE_NAME).getPath();
        File f = new File(databasePath);
        OutputStream myOutput = null;
        InputStream myInput = null;
        Log.d("SR", " testing db path " + databasePath);
        Log.d("SR", " testing db exist " + f.exists());

        if (f.exists()) {
            try {

                File directory = new File("/mnt/sdcard/DB_SR");
                if (!directory.exists())
                    directory.mkdir();

                myOutput = new FileOutputStream(directory.getAbsolutePath()
                        + "/" + DATABASE_NAME);
                myInput = new FileInputStream(databasePath);

                byte[] buffer = new byte[1024];
                int length;
                while ((length = myInput.read(buffer)) > 0) {
                    myOutput.write(buffer, 0, length);
                }

                myOutput.flush();
            } catch (Exception e) {
            } finally {
                try {
                    if (myOutput != null) {
                        myOutput.close();
                        myOutput = null;
                    }
                    if (myInput != null) {
                        myInput.close();
                        myInput = null;
                    }
                } catch (Exception e) {
                }
            }
        }
    }


}


