package com.sputnikpogrom;

import android.app.Application;

import com.sputnikpogrom.receivers.AlarmReceiver;

/**
 * Created by veinhorn on 24.7.15.
 */
public class SPApplication extends Application {
    private AlarmReceiver alarmReceiver = new AlarmReceiver();

    @Override
    public void onCreate() {
        super.onCreate();
        alarmReceiver.setAlarm(this);
    }
}
