package com.sr.thextest;

import android.*;
import android.Manifest;
import android.app.Fragment;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.NavigationView;
import android.support.design.widget.TabLayout;
import android.support.v4.view.GravityCompat;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Gravity;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.sr.thextest.Fragment.CameraFragment;
import com.sr.thextest.Fragment.HomeFragment;
import com.sr.thextest.Fragment.MapHomeFragment;
import com.sr.thextest.Fragment.NewProFragment;
import com.sr.thextest.Fragment.NotiFragment;
import com.sr.thextest.activity.AddEventActivity;
import com.sr.thextest.activity.MapActivity;

public class MainActivity extends AppCompatActivity implements   NavigationView.OnNavigationItemSelectedListener /*, View.OnClickListener */{

    BottomNavigationView bottomNavigationView;

    //This is our viewPager
    private ViewPager viewPager;


    //Fragments

   // ChatFragment chatFragment;
    MapHomeFragment mapHomeFragment;
    HomeFragment homeFragment;
    NotiFragment notiFragment;
    NewProFragment newProFragment;
    CameraFragment cameraFragment;
    //ContactsFragment contactsFragment;
    MenuItem prevMenuItem;
    public static final int PERMISSIONS_MULTIPLE_REQUEST = 11;
    DrawerLayout drawer;
    Button DVbu;


    //for fab

    private Boolean isFabOpen = false;
    public FloatingActionButton fab, fab1, fab2, fab3, fab4;
    public Animation fab_open, fab_close, rotate_forward, rotate_backward;
      View shadowView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

       getSupportActionBar().hide();








        permi2();


        //Initializing viewPager
        viewPager = (ViewPager) findViewById(R.id.viewpager);

        //Initializing the bottomNavigationView
        bottomNavigationView = (BottomNavigationView)findViewById(R.id.bottom_navigation);

        bottomNavigationView.setOnNavigationItemSelectedListener(
                new BottomNavigationView.OnNavigationItemSelectedListener() {
                    @Override
                    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                        switch (item.getItemId()) {
                            case R.id.navigation_home:
                                viewPager.setCurrentItem(0);

                                return true;
                            case R.id.navigation_time:
                                viewPager.setCurrentItem(1);

                                return true;
                            case R.id.navigation_noti:
                                viewPager.setCurrentItem(2);

                                return true;
                            case R.id.navigation_pro:
                                viewPager.setCurrentItem(3);

                                return true;
                            case R.id.navigation_camera:
                                viewPager.setCurrentItem(4);
                                 return true;

                        }
                        return false;
                    }
                });

        viewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                if (prevMenuItem != null) {
                    prevMenuItem.setChecked(false);
                }
                else
                {
                    bottomNavigationView.getMenu().getItem(0).setChecked(false);
                }
                Log.d("page", "onPageSelected: "+position);
                bottomNavigationView.getMenu().getItem(position).setChecked(true);
                prevMenuItem = bottomNavigationView.getMenu().getItem(position);

            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });

       /*  //Disable ViewPager Swipe

       viewPager.setOnTouchListener(new View.OnTouchListener()
        {
            @Override
            public boolean onTouch(View v, MotionEvent event)
            {
                return true;
            }
        });

        */

        setupViewPager(viewPager);
/*
        //fab
         fab = (FloatingActionButton)findViewById(R.id.fab);
        fab1 = (FloatingActionButton)findViewById(R.id.fab1);
        fab2 = (FloatingActionButton)findViewById(R.id.fab2);
        fab3 = (FloatingActionButton)findViewById(R.id.fab3);
        fab4 = (FloatingActionButton)findViewById(R.id.fab4);
        fab_open = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.fab_open);
        fab_close = AnimationUtils.loadAnimation(getApplicationContext(),R.anim.fab_close);
        rotate_forward = AnimationUtils.loadAnimation(getApplicationContext(),R.anim.rotate_forward);
        rotate_backward = AnimationUtils.loadAnimation(getApplicationContext(),R.anim.rotate_backward);
        shadowView =(View)findViewById(R.id.shadowView);
        fab.setOnClickListener(  this);
        fab1.setOnClickListener(  this);
        fab2.setOnClickListener(  this);
        fab3.setOnClickListener(  this);
        fab3.setOnClickListener(  this);
        fab.setAnimation(fab_open);
        */

        final DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        View headerview = navigationView.getHeaderView(0);
        LinearLayout header = (LinearLayout) headerview.findViewById(R.id.nav_view_header);
        navigationView.setNavigationItemSelectedListener(this);

        DVbu=(Button)findViewById(R.id.DVbu);
        DVbu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    drawer.openDrawer(Gravity.LEFT);

                }
                catch (Exception e) {

                }
            }
        });




    }





    private void setupViewPager(ViewPager viewPager) {
        ViewPagerAdapter adapter = new ViewPagerAdapter(getSupportFragmentManager());
        mapHomeFragment=new MapHomeFragment();
        homeFragment=new HomeFragment();
        notiFragment=new NotiFragment();
        newProFragment=new NewProFragment();
        cameraFragment=new CameraFragment();
        adapter.addFragment(mapHomeFragment);
        adapter.addFragment(homeFragment);
        adapter.addFragment(notiFragment);
        adapter.addFragment(newProFragment);
        adapter.addFragment(cameraFragment);
        viewPager.setAdapter(adapter);
    }

    //method for peremission
    public void permi2() {

        requestPermissions(new String[]{
                        android.Manifest.permission.CAMERA,
                        android.Manifest.permission.WRITE_EXTERNAL_STORAGE,
                        Manifest.permission.LOCATION_HARDWARE,
                        android.Manifest.permission.ACCESS_FINE_LOCATION},
                PERMISSIONS_MULTIPLE_REQUEST);

    }




//setup fab animation
/*
    @Override
    public void onClick(View v) {
        int id = v.getId();
        switch (id){
            case R.id.fab:
                animateFAB();
                break;
            case R.id.fab1:

                Log.d("Raj", "Fab 1");
                break;
            case R.id.fab2:

                Log.d("Raj", "Fab 2");
                break;
        }
    }


//This for move fab

    public void animateFAB(){


        if(isFabOpen){

            fab.startAnimation(rotate_backward);
            fab1.startAnimation(fab_close);
            fab2.startAnimation(fab_close);
            fab1.setClickable(false);
            fab2.setClickable(false);
            fab3.startAnimation(fab_close);
            fab4.startAnimation(fab_close);
            fab3.setClickable(false);
            fab4.setClickable(false);
            isFabOpen = false;
            Log.d("Raj", "close");

            //for undim screen

            shadowView.setVisibility(View.GONE);


        } else {

            fab.startAnimation(rotate_forward);
            fab1.startAnimation(fab_open);
            fab2.startAnimation(fab_open);
            fab1.setClickable(true);
            fab2.setClickable(true);
            fab3.startAnimation(fab_open);
            fab4.startAnimation(fab_open);
            fab3.setClickable(true);
            fab4.setClickable(true);
            isFabOpen = true;
            Log.d("Raj","open");

            //for dim screen

              shadowView.setVisibility(View.VISIBLE);


        }
    }

    public void showFloatingActionButton() {
        if (fab.getAnimation() == fab_close) {
            fab.setAnimation(fab_open);


        }
    }


    public void hideFloatingActionButton() {
        fab.setAnimation(fab_close);
    }
*/

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        int id = item.getItemId();

        if (id == R.id.nav_item_Timeline) {
            TabLayout tabLayout = (TabLayout) findViewById(R.id.tabs);
            TabLayout.Tab tab = tabLayout.getTabAt(0);
            tab.select();
            // Handle the camera action
        } else if (id == R.id.nav_item_Notification) {
            TabLayout tabLayout = (TabLayout) findViewById(R.id.tabs);
            TabLayout.Tab tab = tabLayout.getTabAt(1);
            tab.select();

        } else if (id == R.id.nav_item_Profile) {
            TabLayout tabLayout = (TabLayout) findViewById(R.id.tabs);
            TabLayout.Tab tab = tabLayout.getTabAt(2);
            tab.select();

        } else if (id == R.id.nav_item_Contacts) {

        } else if (id == R.id.nav_item_setting) {
            Intent intent = new Intent(MainActivity.this, AddEventActivity.class);
            startActivity(intent);

        } else if (id == R.id.nav_item_help) {
            Intent intent = new Intent(MainActivity.this, MapActivity.class);
            startActivity(intent);
        }



        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;

     }


}



