package com.sr.thextest.activity;

import android.os.Bundle;
import android.preference.PreferenceActivity;

import com.sr.thextest.R;


public class SettingsActivity extends PreferenceActivity {



    @Override

    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);

        addPreferencesFromResource(R.xml.preferences);

    }

}