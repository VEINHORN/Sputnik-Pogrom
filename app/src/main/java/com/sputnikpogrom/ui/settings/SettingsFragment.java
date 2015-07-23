package com.sputnikpogrom.ui.settings;

import android.os.Bundle;
import android.preference.PreferenceFragment;

import veinhorn.sputnikpogrom.R;

/**
 * Created by veinhorn on 23.7.15.
 */
public class SettingsFragment extends PreferenceFragment {
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        addPreferencesFromResource(R.xml.preferences);
    }
}
