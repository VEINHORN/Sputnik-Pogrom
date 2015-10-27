package com.sputnikpogrom.db;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import com.sputnikpogrom.db.SPContract.FavouriteEntry;

/**
 * Created by veinhorn on 27.10.15.
 */
public class SPDbHelper extends SQLiteOpenHelper {
    // If you change the database schema, you must increment the database version.
    private static final int DATABASE_VERSION = 1;
    private static final String DATABASE_NAME = "SputnikPogrom.db";

    public SPDbHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(FavouriteEntry.SQL_CREATE_ENTRIES);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL(FavouriteEntry.SQL_DELETE_ENTRIES);
        onCreate(db);
    }
}
