package com.sputnikpogrom.database;

import android.provider.BaseColumns;

/**
 * Created by veinhorn on 10.3.15.
 */
public final class ArticleContract {
    public ArticleContract() {}

    public static abstract class ArticleEntry implements BaseColumns {
        public static final String TABLE_NAME = "article";
        public static final String COLUMN_NAME_TITLE = "title";
        public static final String COLUMN_NAME_POSTER_URL = "poster_url";
        public static final String COLUMN_NAME_ARTICLE_URL = "article_url";
    }
}