package com.sputnikpogrom;

import android.app.Application;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import com.sputnikpogrom.receivers.AlarmReceiver;


/**
 * Created by veinhorn on 24.7.15.
 */
public class SPApplication extends Application {
    public static final AlarmReceiver ALARM_RECEIVER = new AlarmReceiver();

    @Override
    public void onCreate() {
        super.onCreate();

        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(this);
        boolean isNotificationsEnabled = preferences.getBoolean("pref_notifications", true);

        if(isNotificationsEnabled) ALARM_RECEIVER.setAlarm(this);
        else ALARM_RECEIVER.cancelAlarm(this);
    }
}
