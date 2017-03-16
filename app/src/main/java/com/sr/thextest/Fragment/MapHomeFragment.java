package com.sr.thextest.Fragment;


import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.Fragment;
import android.support.v4.view.accessibility.AccessibilityManagerCompat;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.google.android.gms.common.api.Status;
import com.google.android.gms.location.places.Place;
import com.google.android.gms.location.places.ui.PlaceAutocomplete;
import com.google.android.gms.maps.CameraUpdate;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapView;
import com.google.android.gms.maps.MapsInitializer;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.CameraPosition;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;

import com.sr.thextest.Database.SQLiteDatabaseHelper;
import com.sr.thextest.R;

import java.util.ArrayList;
import java.util.Iterator;

import static android.app.Activity.RESULT_CANCELED;
import static android.app.Activity.RESULT_OK;
import static android.content.Context.LOCATION_SERVICE;


/**
 * A simple {@link Fragment} subclass.
 */
public class MapHomeFragment extends Fragment implements com.google.android.gms.location.LocationListener,AccessibilityManagerCompat.TouchExplorationStateChangeListener,GoogleMap.OnMarkerClickListener {


    public MapHomeFragment() {
        // Required empty public constructor
    }


    MapView mMapView;
    private GoogleMap googleMap;
    private LocationListener listener;
    LatLng ff;
    private LocationManager locationManager;
    int PLACE_AUTOCOMPLETE_REQUEST_CODE = 14;


    double lng;
    double lat;

    String name1,lat1,lng1;

    SQLiteDatabaseHelper SQLITEHELPER;
    SQLiteDatabase SQLITEDATABASE;
    Cursor cursor;

    ArrayList<LatLng> latLngs = new ArrayList<LatLng>();
    ArrayList<String> location_name = new ArrayList<String>();
    LatLng newLatLng;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.map_fragment, container, false);

        getdataevent();

        //getdatafromevent();


        listener = new LocationListener() {
            @Override
            public void onLocationChanged(Location location) {

                if (location != null) {

                    ff = new LatLng(location.getLatitude(), location.getLongitude());

                    CameraPosition cameraPosition = new CameraPosition.Builder().target(ff).zoom(16).tilt(20).build();
                    googleMap.clear();
                    googleMap.addMarker(new MarkerOptions().position(ff).icon(BitmapDescriptorFactory.fromResource(R.mipmap.ic_location_on_black_48dp)).title("This My Location"));
                    googleMap.animateCamera(CameraUpdateFactory.newCameraPosition(cameraPosition), 700, null);












                }

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
        };
      //  getlatlong();




        mMapView = (MapView) rootView.findViewById(R.id.mapView);
        mMapView.onCreate(savedInstanceState);

        mMapView.onResume(); // needed to get the map to display immediately
        locationManager = (LocationManager) getActivity().getSystemService(LOCATION_SERVICE);


        try {
            MapsInitializer.initialize(getActivity().getApplicationContext());
        } catch (Exception e) {
            e.printStackTrace();
        }

        mMapView.getMapAsync(new OnMapReadyCallback() {
            @Override
            public void onMapReady(GoogleMap mMap) {
                googleMap = mMap;

                // For showing a move to my location button


                Iterator<LatLng> iterator = latLngs.iterator();
                Iterator<String> iterator2 = location_name.iterator();
                while (iterator.hasNext()) {

                    while (iterator.hasNext()) {




                        googleMap.addMarker(new MarkerOptions().position(iterator.next()).snippet("SR SR SR  SR SR SR  \n ").title(iterator2.next()));


                    }




                }




                LatLng sydney = new LatLng(lat, lng);


                googleMap.moveCamera(CameraUpdateFactory.newLatLng(sydney));
                CameraUpdate zoom=CameraUpdateFactory.zoomTo(14);
                googleMap.animateCamera(zoom);



                listener = new LocationListener() {
                    @Override
                    public void onLocationChanged(Location location) {

                        if (location != null) {
/*
                            ff = new LatLng(location.getLatitude(), location.getLongitude());

                            CameraPosition cameraPosition = new CameraPosition.Builder().target(ff).zoom(16).tilt(20).build();
                            googleMap.clear();
                            googleMap.addMarker(new MarkerOptions().position(ff).icon(BitmapDescriptorFactory.fromResource(R.mipmap.ic_location_on_black_48dp)).title("This My Location"));
                            googleMap.animateCamera(CameraUpdateFactory.newCameraPosition(cameraPosition), 700, null);

*/










                        }

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
                };
                getlatlong();


                // For zooming automatically to the location of the marker

            }
        });

        return rootView;
    }

    @Override
    public void onResume() {
        super.onResume();
        mMapView.onResume();
    }

    @Override
    public void onPause() {
        super.onPause();
        mMapView.onStop();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        mMapView.onStop();
    }

    @Override
    public void onLowMemory() {
        super.onLowMemory();
        mMapView.onLowMemory();
    }

    @Override
    public void onDetach() {
        super.onDetach();
    }

    @Override
    public void onLocationChanged(final Location location) {


    }


    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        switch (requestCode) {
            case 10:
                getlatlong();
                break;
            default:
                break;
        }
    }

   public void getlatlong() {
        // first check for permissions
        if (ActivityCompat.checkSelfPermission(getContext(), Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(getContext(), Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                requestPermissions(new String[]{Manifest.permission.ACCESS_COARSE_LOCATION, Manifest.permission.ACCESS_FINE_LOCATION, Manifest.permission.INTERNET}
                        , 10);
            }
            return;
        }
        // this code won't execute IF permissions are not allowed, because in the line above there is return statement.



        //noinspection MissingPermission

        if(isNetworkStatusAvialable (getContext())) {
          //  Toast.makeText(getContext(), "internet avialable", Toast.LENGTH_LONG).show();
            locationManager.requestLocationUpdates(LocationManager.NETWORK_PROVIDER, 0, 0.0f, listener );

        } else {
            Toast.makeText(getContext(), "internet is not avialable (For faster connect to internet)", Toast.LENGTH_LONG).show();
            locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER, 0, 0.0f, listener );


        }




    }


    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == PLACE_AUTOCOMPLETE_REQUEST_CODE) {
            if (resultCode == RESULT_OK) {
                Place place = PlaceAutocomplete.getPlace(getContext(), data);
                Log.i("SR", "Place: " + place.getName());
            } else if (resultCode == PlaceAutocomplete.RESULT_ERROR) {
                Status status = PlaceAutocomplete.getStatus(getContext(), data);
                // TODO: Handle the error.
                Log.i("SR", status.getStatusMessage());

            } else if (resultCode == RESULT_CANCELED) {

                // The user canceled the operation.
            }
        }
    }


    @Override
    public void onTouchExplorationStateChanged(boolean enabled) {

    }

    public static boolean isNetworkStatusAvialable (Context context) {
        ConnectivityManager connectivityManager = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        if (connectivityManager != null)
        {
            NetworkInfo netInfos = connectivityManager.getActiveNetworkInfo();
            if(netInfos != null)
                if(netInfos.isConnected())
                    return true;
        }
        return false;
    }

    private void  getdataevent(){


        SQLITEHELPER = new SQLiteDatabaseHelper(getContext());

        SQLITEDATABASE = SQLITEHELPER.getReadableDatabase();




        try {
            cursor = SQLITEDATABASE.rawQuery("SELECT * FROM event", null);
            if (cursor != null) {
                if (cursor.moveToFirst()) {
                    do {
                        name1 = cursor.getString(cursor.getColumnIndex("event_type"));
                        //det = cursor.getString(cursor.getColumnIndex("det"));
                        lat1 = cursor.getString(cursor.getColumnIndex("event_latitude"));
                        lng1 = cursor.getString(cursor.getColumnIndex("event_longitude"));
                        newLatLng = new LatLng(Double.parseDouble(lat1), Double.parseDouble(lng1));
                        latLngs.add(newLatLng);
                        location_name.add(name1);
                        lat = Double.parseDouble(lat1);
                        lng=Double.parseDouble(lng1);






                        Log.i("SR","SR");

                    } while (cursor.moveToNext());
                }
            }
        } catch (Exception e) {
        } finally {
            cursor.close();
        }


    }

    @Override
    public boolean onMarkerClick(Marker marker) {

        String name= (String) marker.getTag();


        if (name=="")
        {
            //handle click here
        }

        return false;
    }

    public void getmylocation() {
        listener = new LocationListener() {
            @Override
            public void onLocationChanged(Location location) {

                if (location != null) {

                            ff = new LatLng(location.getLatitude(), location.getLongitude());

                            CameraPosition cameraPosition = new CameraPosition.Builder().target(ff).zoom(16).tilt(20).build();
                            googleMap.clear();
                            googleMap.addMarker(new MarkerOptions().position(ff).icon(BitmapDescriptorFactory.fromResource(R.mipmap.ic_location_on_black_48dp)).title("This My Location"));
                            googleMap.animateCamera(CameraUpdateFactory.newCameraPosition(cameraPosition), 700, null);












                }

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
        };
        getlatlong();
    }




}


