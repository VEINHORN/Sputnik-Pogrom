package com.sputnikpogrom.ui.settings;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v4.app.NavUtils;
import android.util.Log;
import android.view.MenuItem;

import com.sputnikpogrom.SPApplication;

import veinhorn.sputnikpogrom.R;

/**
 * Created by veinhorn on 23.7.15.
 */
public class SettingsActivity extends AppCompatPreferenceActivity implements SharedPreferences.OnSharedPreferenceChangeListener {
    private SPApplication application;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        addPreferencesFromResource(R.xml.preferences);
        application = (SPApplication)getApplication();
        getSupportActionBar().setHomeButtonEnabled(true);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch(item.getItemId()) {
            case android.R.id.home:
                NavUtils.navigateUpFromSameTask(this);
                return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void onResume() {
        super.onResume();
        getPreferenceScreen().getSharedPreferences().registerOnSharedPreferenceChangeListener(this);
    }

    @Override
    protected void onPause() {
        super.onPause();
        getPreferenceScreen().getSharedPreferences().unregisterOnSharedPreferenceChangeListener(this);
    }

    @Override
    public void onSharedPreferenceChanged(SharedPreferences preferences, String key) {
        boolean isNotificationsEnabled = preferences.getBoolean(key, true);
        if(isNotificationsEnabled) application.ALARM_RECEIVER.setAlarm(this);
        else application.ALARM_RECEIVER.cancelAlarm(this);
        Log.d(getClass().getName(), "Changed notification option to: " + isNotificationsEnabled);
    }
}
