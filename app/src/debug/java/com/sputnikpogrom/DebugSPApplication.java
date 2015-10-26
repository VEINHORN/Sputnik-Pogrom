package com.sputnikpogrom;

import android.content.Context;

import com.facebook.stetho.Stetho;

/**
 * Created by veinhorn on 26.10.15.
 */
public class DebugSPApplication extends SPApplication {
    @Override
    public void onCreate() {
        super.onCreate();
        initializeStetho(this);
    }

    private void initializeStetho(final Context context) {
        Stetho.initializeWithDefaults(context);
    }
}
