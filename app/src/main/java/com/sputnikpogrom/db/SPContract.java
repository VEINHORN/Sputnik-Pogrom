package com.sputnikpogrom.db;

import android.provider.BaseColumns;

/**
 * Created by veinhorn on 27.10.15.
 */
public final class SPContract {
    private static final String TEXT_TYPE = " TEXT";
    private static final String COMMA_SEP = ",";

    public SPContract() {

    }

    public static abstract class FavouriteEntry implements BaseColumns {
        public static final String TABLE_NAME = "favourite";
        public static final String COLUMN_NAME_TITLE = "title";
        public static final String COLUMN_NAME_POSTER_URL = "poster_url";
        public static final String COLUMN_NAME_ARTICLE_URL = "article_url";

        public static final String SQL_CREATE_ENTRIES =
                "CREATE TABLE " + FavouriteEntry.TABLE_NAME + " (" +
                        FavouriteEntry._ID + " INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL" + COMMA_SEP +
                        FavouriteEntry.COLUMN_NAME_TITLE + TEXT_TYPE + COMMA_SEP +
                        FavouriteEntry.COLUMN_NAME_POSTER_URL + TEXT_TYPE + COMMA_SEP +
                        FavouriteEntry.COLUMN_NAME_ARTICLE_URL + TEXT_TYPE + " )";
        public static final String SQL_DELETE_ENTRIES =
                "DROP TABLE IF EXISTS " + FavouriteEntry.TABLE_NAME;
    }
}
