package com.sr.thextest.activity;

import android.Manifest;
import android.app.Activity;
import android.app.Application;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Build;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.os.Environment;
import android.provider.Settings;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.common.GooglePlayServicesNotAvailableException;
import com.google.android.gms.common.GooglePlayServicesRepairableException;
import com.google.android.gms.location.places.Place;
import com.google.android.gms.location.places.ui.PlacePicker;
import com.sr.thextest.Database.SQLiteDatabaseHelper;
import com.sr.thextest.Fragment.MapHomeFragment;
import com.sr.thextest.R;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;

public class AddEventActivity extends AppCompatActivity implements LocationListener, Application.ActivityLifecycleCallbacks {


     FloatingActionButton fabloc,fabsend;
     int dc2;
     Bitmap bitmap;
     EditText datematab,timematab,loc,locname,detailsmatab;
     TextView detailscu;
     ImageView imageaddmatab;
     ImageView samplemap;
     int PLACE_PICKER_REQUEST=1;
     int CAMERA_PIC_REQUEST = 2;

    double sr1,sr2;
    StringBuilder text1 ,text2,text3;


    private LocationManager locationManager;
    private LocationListener listener;

    private static boolean isMySomeActivityVisible;

    SQLiteDatabaseHelper myDB;
    MapHomeFragment map;



    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.event_layout);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

//SR

        //for control database
        myDB= new SQLiteDatabaseHelper(this);
        map= new MapHomeFragment();


        myDB.copyDatabase(getApplicationContext(),"ThexData.db");


        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.setTitle("Event");
            Log.v("TEST", actionBar.getTitle().toString());
        }


        detailsmatab=(EditText)findViewById(R.id.detailsmatab) ;
        detailsmatab.requestFocus();


        //for location

        loc=(EditText)findViewById(R.id.locationmatab) ;
        locname=(EditText)findViewById(R.id.locname) ;

        getdatatext();

        loc.setText(text1+","+text2);
        locname.setText(text3);


        locationManager = (LocationManager) getSystemService(LOCATION_SERVICE);


        listener = new LocationListener() {
            @Override
            public void onLocationChanged(Location location) {

                loc.setText( location.getLongitude() + "," + location.getLatitude());

            }

            @Override
            public void onStatusChanged(String s, int i, Bundle bundle) {



            }

            @Override
            public void onProviderEnabled(String s) {


            }

            @Override
            public void onProviderDisabled(String s) {

                Intent i = new Intent(Settings.ACTION_LOCATION_SOURCE_SETTINGS);
                startActivity(i);
            }
        };

        getlatlong();





 //for date and time



        timematab = (EditText) findViewById(R.id.timematab);
        datematab =(EditText) findViewById(R.id.datematab);


        new CountDownTimer(300000000, 1000) {

            public void onTick(long millisUntilFinished) {

                DateFormat tf = new SimpleDateFormat("HH:mm:ss");
                String time = tf.format(Calendar.getInstance().getTime());
                timematab.setText(time);




                DateFormat df = new SimpleDateFormat("EEE, dd/MM/yyyy");
                String date = df.format(Calendar.getInstance().getTime());
                datematab.setText(date);



            }

            public void onFinish() {
             }
        }.start();



        // FindViewById
        imageaddmatab = (ImageView) findViewById(R.id.imageaddmatab);
        samplemap = (ImageView) findViewById(R.id.samplemap);

        // FindViewById//
        detailscu= (TextView) findViewById(R.id.detailscu);
        detailsmatab.addTextChangedListener(new TextWatcher() {
                @Override
                public void beforeTextChanged(CharSequence s, int start, int count, int after) {

                }

                @Override
                public void onTextChanged(CharSequence s, int start, int before, int count) {

                    if(detailsmatab.length()<1){


                         detailscu.setText("");

                    }
                    if(detailsmatab.length()>1){


                        dc2=detailsmatab.length();
                        detailscu.setText(Integer.toString(dc2));

                    }
                    if(detailsmatab.length()<49){

                        detailscu.setTextColor(Color.parseColor("#FF0000"));
                    }
                    if (detailsmatab.length()>=49){
                        detailscu.setTextColor(Color.parseColor("#3CDE00"));



                    }
                }

                @Override
                public void afterTextChanged(Editable s) {

                }
            });

        fabloc = (FloatingActionButton) findViewById(R.id.fabloc);
        fabsend= (FloatingActionButton) findViewById(R.id.fabsend);
        fabsend.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if(detailsmatab.length()<0){

                    Toast.makeText(getApplicationContext(), "Minimum number of characters is 50", Toast.LENGTH_SHORT).show();




                }
                else {

/*
                    boolean inserted =myDB.insertDataforevent("bomb"
                            ,timematab.getText().toString()
                            ,datematab.getText().toString()
                            ,text1.toString()
                            ,text2.toString()
                            ,text3.toString()
                            ,detailsmatab.getText().toString()
                            ,""
                             );

                    if( inserted == true){



                        Toast.makeText(getApplicationContext(),"حبيبي تسلم event",Toast.LENGTH_SHORT).show();
                        myDB.copyDatabase(getApplicationContext(),"data.db");
                          finish();




                    }
                    else{
                        Toast.makeText(getApplicationContext()," مش تسلم event",Toast.LENGTH_SHORT).show();
                    }
*/



                }
            }
        });

        fabloc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                PlacePicker.IntentBuilder builder = new PlacePicker.IntentBuilder();


                Intent intent;
                try {
                    intent=builder.build(AddEventActivity.this);
                    startActivityForResult(intent,PLACE_PICKER_REQUEST);
                } catch (GooglePlayServicesRepairableException e) {
                    e.printStackTrace();
                } catch (GooglePlayServicesNotAvailableException e) {
                    e.printStackTrace();
                }
            }
        });



        //ClickListener

        imageaddmatab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent cameraIntent = new Intent(android.provider.MediaStore.ACTION_IMAGE_CAPTURE);
                setResult(RESULT_OK, cameraIntent);
                startActivityForResult(cameraIntent, CAMERA_PIC_REQUEST);


            }
        });


        //ClickListener
    }


    //load Image From Storage
    private void loadImageFromStorage( )
    {
        String appname =  getString(R.string.app_name);


        try {

            File f = new File(Environment.getExternalStorageDirectory() + File.separator + "/" + appname + "/Image/location_add.png");
            if(f.exists()) {

                Bitmap b = BitmapFactory.decodeStream(new FileInputStream(f));

                samplemap.setImageBitmap(b);
            }
            else {
                //Intent locIntent = new Intent(getApplication(),MapActivity.class);
                //startActivity(locIntent);


            }
        }
        catch (FileNotFoundException e)
        {
            e.printStackTrace();
        }

    }
    //END//load Image From Storage




    //scale Down Bitmap

    public static Bitmap scaleDownBitmap(Bitmap photo, int newHeight, Context context) {

        final float densityMultiplier = context.getResources().getDisplayMetrics().density;

        int h= (int) (newHeight*densityMultiplier);
        int w= (int) (h * photo.getWidth()/((double) photo.getHeight()));

        photo=Bitmap.createScaledBitmap(photo, w, h, true);

        return photo;
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == PLACE_PICKER_REQUEST
                && resultCode == Activity.RESULT_OK){


            final Place place = PlacePicker.getPlace(this, data);
            final CharSequence name = place.getName();
            final CharSequence address = place.getAddress();

            String attributions = (String) place.getAttributions();
            if (attributions == null) {
                attributions = "";
            }


              sr1=place.getLatLng().latitude;
              sr2=place.getLatLng().longitude;



            writeToFile(String.valueOf(sr1),"sr1",getApplicationContext());
            writeToFile(String.valueOf(sr2),"sr2",getApplicationContext());
             writeToFile(name+","+address+","+attributions,"sr3",getApplicationContext());

            Intent intent = new Intent(AddEventActivity.this, MapActivity.class);
            startActivity(intent);
            // finish();





        }

        if( requestCode == CAMERA_PIC_REQUEST && resultCode == RESULT_OK && data != null)
        {
            bitmap = (Bitmap) data.getExtras().get("data");
            imageaddmatab.setImageBitmap(bitmap);

        }
        else
        {
            Toast.makeText(getApplicationContext(), "Picture Not taken", Toast.LENGTH_LONG);
        }
    }

    // for re
    // sume activity

    @Override
    protected void onResume() {

        super.onResume();
        getdatatext();
        if( MapActivity.activityPaused()==true){
            loadImageFromStorage();

        }
    }

    private void fillTextView (int id, String text) {
        TextView tv = (TextView) findViewById(id);
        tv.setText(text); // tv is null
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        switch (requestCode){
            case 10:
                getlatlong();
                break;
            default:
                break;
        }
    }

    void getlatlong(){
        // first check for permissions
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                requestPermissions(new String[]{Manifest.permission.ACCESS_COARSE_LOCATION,Manifest.permission.ACCESS_FINE_LOCATION,Manifest.permission.INTERNET}
                        ,10);
            }
            return;
        }
        // this code won't execute IF permissions are not allowed, because in the line above there is return statement.

                //noinspection MissingPermission
                locationManager.requestLocationUpdates("gps", 0, 0, listener);

    }

    @Override
    public void onLocationChanged(Location location) {
        loc.setText(  location.getLongitude() + "," + location.getLatitude());


    }


    @Override
    public void onStatusChanged(String provider, int status, Bundle extras) {

    }

    @Override
    public void onProviderEnabled(String provider) {

    }

    @Override
    public void onProviderDisabled(String provider) {

    }


    private void writeToFile(String data,String tx,Context context) {
        try {
            String appname = getString(R.string.app_name);

            File folder = new File(Environment.getExternalStorageDirectory() + File.separator + "/" + appname + "/Text/");
            if (!folder.exists()) {
                folder.mkdirs();
                Toast.makeText(getApplicationContext(), "Folder Maked", Toast.LENGTH_SHORT).show();

            } else {

                File f2 = new File(Environment.getExternalStorageDirectory() + File.separator + "/" + appname + "/Text/");

                File file = new File(String.valueOf(f2.getPath()), tx+".txt");
                FileOutputStream stream = new FileOutputStream(file);
                try {
                    stream.write(data.getBytes());
                } finally {
                    stream.close();
                }

            }

        }
        catch (IOException e) {
            Log.e("Exception", "File write failed: " + e.toString());
        }
    }


    @Override
    public void onActivityCreated(Activity activity, Bundle savedInstanceState) {

    }

    @Override
    public void onActivityStarted(Activity activity) {

    }

    @Override
    public void onActivityResumed(Activity activity) {
        if (activity instanceof AddEventActivity) {
            isMySomeActivityVisible = true;
        }
    }

    @Override
    public void onActivityPaused(Activity activity) {

    }

    @Override
    public void onActivityStopped(Activity activity) {
        if (activity instanceof AddEventActivity) {
            isMySomeActivityVisible = false;
        }
    }

    @Override
    public void onActivitySaveInstanceState(Activity activity, Bundle outState) {

    }

    @Override
    public void onActivityDestroyed(Activity activity) {

    }

    public void getdatatext() {


        String appname = getString(R.string.app_name);

        //  readFileAsString(Environment.getExternalStorageDirectory() + File.separator + "/" + appname + "/Text/");

        String path1 = Environment.getExternalStorageDirectory() + File.separator + "/" + appname + "/Text/";



//Get the text file
        File file1 = new File(path1,"sr1.txt");
        File file2 = new File(path1,"sr2.txt");
        File file3 = new File(path1,"sr3.txt");

//Read text from file
        text1 = new StringBuilder();
        text2 = new StringBuilder();
        text3 = new StringBuilder();

        try {
            BufferedReader br1 = new BufferedReader(new FileReader(file1));
            BufferedReader br2 = new BufferedReader(new FileReader(file2));
            BufferedReader br3 = new BufferedReader(new FileReader(file3));

            String line1;
            String line2;
            String line3;

            while ((line1 = br1.readLine()) != null) {
                text1.append(line1);
            }
            br1.close();
            while ((line2 = br2.readLine()) != null) {
                text2.append(line2);
            }
            br2.close();
            while ((line3 = br3.readLine()) != null) {
                text3.append(line3);
            }
            br3.close();
        }
        catch (IOException e) {
            //You'll need to add proper error handling here
        }


        loc.setText(text1+","+text2);
        locname.setText(text3);






    }

}
