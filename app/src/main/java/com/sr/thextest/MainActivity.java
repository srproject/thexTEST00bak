package com.sr.thextest;

import android.app.Fragment;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.MenuItem;
import android.view.Window;
import android.widget.TextView;

import com.sr.thextest.Fragment.CameraFragment;
import com.sr.thextest.Fragment.HomeFragment;
import com.sr.thextest.Fragment.MapHomeFragment;
import com.sr.thextest.Fragment.NewProFragment;
import com.sr.thextest.Fragment.NotiFragment;

public class MainActivity extends AppCompatActivity {

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
                                cameraFragment.closeCamera();

                                return true;
                            case R.id.navigation_time:
                                viewPager.setCurrentItem(1);
                                cameraFragment.closeCamera();

                                return true;
                            case R.id.navigation_noti:
                                viewPager.setCurrentItem(2);
                                cameraFragment.closeCamera();

                                return true;
                            case R.id.navigation_pro:
                                viewPager.setCurrentItem(3);
                                cameraFragment.closeCamera();

                                return true;
                            case R.id.navigation_camera:
                                viewPager.setCurrentItem(4);
                                cameraFragment.openCamera();
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
                        android.Manifest.permission.ACCESS_FINE_LOCATION},
                PERMISSIONS_MULTIPLE_REQUEST);

    }


}



