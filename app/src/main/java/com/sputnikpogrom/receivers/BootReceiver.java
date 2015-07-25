package com.sputnikpogrom.receivers;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;

/**
 * Created by veinhorn on 21.7.15.
 */
public class BootReceiver extends BroadcastReceiver {
    private AlarmReceiver alarmReceiver = new AlarmReceiver();

    @Override
    public void onReceive(Context context, Intent intent) {
        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(context);
        boolean isNotificationsEnabled = preferences.getBoolean("pref_notifications", true);

        if(intent.getAction().equals("android.intent.action.BOOT_COMPLETED") && isNotificationsEnabled) {
            alarmReceiver.setAlarm(context);
        }
    }
}
