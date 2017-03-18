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

    public static  final String event_TABLE="event";
    public static  final String event_id="event_id";
    public static  final String event_account_id="event_account_id";
    public static  final String event_type="event_type";
    public static  final String event_time="event_time";
    public static  final String event_date="event_date";
    public static  final String event_latitude="event_latitude";
    public static  final String event_longitude="event_longitude";
    public static  final String event_locnam="event_locnam";
    public static  final String event_det="event_det";
    public static  final String event_photo="event_photo";
    public static  final String event_map_snap="event_map_snap";


    //EVENT Table

    public static final String event_area_TABLE="event_area";
    public static  final String event_area_id="event_area_id";
    public static  final String event_area_account_id="event_area_account_id";
    public static  final String event_area_type="event_area_type";
    public static  final String event_area_time="event_area_time";
    public static  final String event_area_date="event_area_date";
    public static  final String event_area_latitude="event_area_latitude";
    public static  final String event_area_longitude="event_area_longitude";
    public static  final String event_area_locnam="event_area_locnam";
    public static  final String event_area_area_det="event_area_det";
    public static  final String event_area_area_photo="event_area_photo";
    public static  final String event_area_map_snap="event_area_map_snap";





    // event_comment_TABLE

    public static  final  String event_comment_TABLE="event_comment";
    public static  final String comment_id="comment_id";
    public static  final String event_comment_id="event_comment_id";
    public static  final String event_account_comment_id="event_account_comment_id";
    public static  final String comment_text="comment_text";
    public static  final String event_comment_date="event_comment_date";
    public static  final String event_comment_time="event_comment_time";

    // event_like_TABLE

    public static final String event_like_TABLE="event_like";
    public static  final String like_id="like_id";
    public static  final String event_like_id="event_like_id";
    public static  final String event_account_like_id="event_account_like_id";
    public static  final String event_like_date="event_like_date";
    public static  final String event_like_time="event_like_time";

    // event_share_TABLE

    public static final String event_share_TABLE="event_share";
    public static  final String share_id="share_id";
    public static  final String event_share_id="event_share_id";
    public static  final String event_account_share_id="event_account_share_id";
    public static  final String event_share_date="event_share_date";
    public static  final String event_share_time="event_share_time";

    // event_share_TABLE

    public static final String event_ex_photo_TABLE="event_ex_photo";
    public static  final String photo_id="photo_id";
    public static  final String event_photo_id="event_photo_id";
    public static  final String event_account_photo_id="event_account_photo_id";
    public static  final String event_photo_date="event_photo_date";
    public static  final String event_photo_time="event_photo_time";
    public static  final String event_photo_data="event_photo_data";


    //ACCOUNT Table

    public static final String account_TABLE="account";
    public static  final String account_id="account_id";
    public static  final String account_type="account_type";
    public static  final String account_email="account_email";
    public static  final String account_phone="account_phone";
    public static  final String account_password="account_password";
    // ACCOUNT permission
    public static  final String permission_inter="permission_inter";
    public static  final String permission_event="permission_event";
    public static  final String permission_comm="permission_comm";
    public static  final String permission_share="permission_share";
    public static  final String permission_like="permission_like";
    public static  final String permission_camera="permission_camera";
    public static  final String permission_profile="permission_profile";
    public static  final String permission_editprofile="permission_editprofile";
    public static  final String permission_map="permission_map";
    public static  final String permission_noti="permission_noti";
    public static  final String permission_showevent="permission_showevent";
    public static  final String permission_make_event_solve="permission_make_event_solve";
    public static  final String permission_block="permission_block";
    public static  final String permission_report="permission_report";







    //PROFILE FOR ACCOUNT Table

    public static final String profile_TABLE="profile";
    public static  final String profile_id="profile_id";
    public static  final String profile_account_id="profile_account_id";
    public static  final String profile_name="profile_name";
    public static  final String profile_sex="profile_sex";
    public static  final String profile_age="profile_age";
    public static  final String profile_loc="profile_loc";
    public static  final String profile_edu="profile_edu";
    public static  final String profile_rel="profile_rel";


    //follow FOR ACCOUNT Table

    public static final String follow_TABLE="follow";
    public static  final String follow_id="follow_id";
    public static  final String follow_account_id="follow_account_id";
    public static  final String follower_account_id="follower_account_id";
    public static  final String following_account_id="following_account_id";





    public SQLiteDatabaseHelper(Context context ) {
        super(context, DATABASE_NAME, null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {



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



// insert data into table event

    public boolean insertDataforevent (String ievent_id,String ievent_account_id,String ievent_type,String ievent_time,String ievent_date
            ,String ievent_latitude,String ievent_longitude,String ievent_locnam,String ievent_det,String ievent_photo ,String ievent_map_snap) {




        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(event_id,ievent_id);
        contentValues.put(event_account_id,ievent_account_id);
        contentValues.put(event_type, ievent_type);
        contentValues.put(event_time, ievent_time);
        contentValues.put(event_date, ievent_date);
        contentValues.put(event_latitude, ievent_latitude);
        contentValues.put(event_longitude, ievent_longitude);
        contentValues.put(event_locnam, ievent_locnam);
        contentValues.put(event_det, ievent_det);
        contentValues.put(event_photo, ievent_photo);
        contentValues.put(event_map_snap,ievent_map_snap);




        //db.execSQL("DELETE FROM event " );



        long result = db.insert("event", null, contentValues);
        if (result == -1) {
            return false;
        }
        else {
            return true;
        }
    }

    //insert data into event_comment_TABLE  * M Y *
    public boolean insertDataForComment (String ievent_comment_id, String ievent_account_comment_id ,
                                         String icomment_text,String ievent_comment_date,String ievent_comment_time){

        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues contentValues = new ContentValues();
        contentValues.put(event_comment_id, ievent_comment_id);
        contentValues.put(event_account_comment_id, ievent_account_comment_id);
        contentValues.put(comment_text,icomment_text );
        contentValues.put(event_comment_date,ievent_comment_date );
        contentValues.put(event_comment_time,ievent_comment_time );

        long result = db.insert("event_comment", null, contentValues);
        if (result == -1) {
            return false;
        }
        else {
            return true;
        }


    }

    //insert data into event_like_TABLE * M Y *

    public boolean insertDataforLike (String ievent_like_id, String ievent_account_like_id , String ievent_like_date ,String ievent_like_time ){

        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues contentValues = new ContentValues();
        contentValues.put(event_like_id, ievent_like_id);
        contentValues.put(event_account_like_id, ievent_account_like_id);
        contentValues.put(event_like_date,ievent_like_date );
        contentValues.put(event_like_time,ievent_like_time );


        long result = db.insert("event_like", null, contentValues);
        if (result == -1) {
            return false;
        }
        else {
            return true;
        }


    }
//insert data into event_share_table  * M Y *
public boolean insertDataforShare (String ievent_share_id, String ievent_account_share_id , String ievent_share_date ,String ievent_share_time ){

    SQLiteDatabase db = this.getWritableDatabase();

    ContentValues contentValues = new ContentValues();
    contentValues.put(event_share_id,ievent_share_id );
    contentValues.put(event_account_share_id,ievent_account_share_id );
    contentValues.put(event_share_date,ievent_share_date );
    contentValues.put(event_share_time, ievent_share_time);


    long result = db.insert("event_share", null, contentValues);
    if (result == -1) {
        return false;
    }
    else {
        return true;
    }


}

//insert data into account table * M Y *
    public boolean insertDataforAccount(String iaccount_type,String iaccount_email,String iaccount_phone, String iaccount_password){
        SQLiteDatabase db =this.getWritableDatabase();
        ContentValues contentValues =new ContentValues();
        contentValues.put(account_type,iaccount_type);
        contentValues.put(account_email,iaccount_email);
        contentValues.put(account_phone,iaccount_phone);
        contentValues.put(account_password,iaccount_password);


        long result =db.insert("account",null,contentValues);
        if(result == -1) {
            return false;
        }
        else {
                return true;
        }
    }
    //insert data into profile table * M Y *
    public boolean insertDataforProfile(String iprofile_account_id,String iprofile_name,String iprofile_sex, String iprofile_age,
                                        String iprofile_loc ,String iprofile_edu,String iprofile_rel ){
        SQLiteDatabase db =this.getWritableDatabase();
        ContentValues contentValues =new ContentValues();
        contentValues.put(profile_account_id,iprofile_account_id);
        contentValues.put(profile_name,iprofile_name);
        contentValues.put(profile_sex,iprofile_sex);
        contentValues.put(profile_age,iprofile_age);
        contentValues.put(profile_loc,iprofile_loc);
        contentValues.put(profile_edu,iprofile_edu);
        contentValues.put(profile_rel,iprofile_rel);

        long result =db.insert("profile",null,contentValues);
        if(result == -1) {
            return false;
        }
        else {
            return true;
        }
    }

  //insert data into follow table
    public boolean insertDataforFollow(String ifollow_account_id,String ifollower_account_id,String ifollowing_account_id){
        SQLiteDatabase db= this.getWritableDatabase();
        ContentValues contentValues= new ContentValues();
        contentValues.put(follow_account_id,ifollow_account_id);
        contentValues.put(follower_account_id,ifollower_account_id);
        contentValues.put(following_account_id,ifollowing_account_id);

        long result =db.insert("follow",null,contentValues);
        if(result == -1){
            return false;
        }
        else {
            return  true;
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


