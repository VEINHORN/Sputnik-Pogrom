package com.sputnikpogrom.db.dao;

import android.content.Context;

import com.sputnikpogrom.db.SPDbHelper;

/**
 * Created by veinhorn on 27.10.15.
 */
public class DbHelperHolder {
    protected SPDbHelper dbHelper;

    public DbHelperHolder(Context context) {
        dbHelper = new SPDbHelper(context);
    }
}
