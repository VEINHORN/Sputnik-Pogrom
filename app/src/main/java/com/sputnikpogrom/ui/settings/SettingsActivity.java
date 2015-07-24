package com.sputnikpogrom.ui.settings;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceActivity;

import veinhorn.sputnikpogrom.R;

/**
 * Created by veinhorn on 23.7.15.
 */
public class SettingsActivity extends PreferenceActivity implements SharedPreferences.OnSharedPreferenceChangeListener {
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        addPreferencesFromResource(R.xml.preferences);
        setTheme(android.R.style.Theme_Holo_Light_DarkActionBar);
    }

    @Override
    public void onSharedPreferenceChanged(SharedPreferences preferences, String key) {

    }
}
