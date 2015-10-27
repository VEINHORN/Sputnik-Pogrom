package com.sputnikpogrom.db.dao;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import com.sputnikpogrom.entities.Article;
import com.sputnikpogrom.db.SPContract.FavouriteEntry;

/**
 * Created by veinhorn on 27.10.15.
 */
public class FavouriteDAOImpl extends DbHelperHolder implements FavouriteDAO {
    public FavouriteDAOImpl(Context context) {
        super(context);
    }

    @Override
    public void insert(Article article) {
        SQLiteDatabase db = dbHelper.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(FavouriteEntry.COLUMN_NAME_TITLE, article.getTitle());
        values.put(FavouriteEntry.COLUMN_NAME_POSTER_URL, article.getPosterUrl());
        values.put(FavouriteEntry.COLUMN_NAME_ARTICLE_URL, article.getArticleUrl());

        db.insert(FavouriteEntry.TABLE_NAME, null, values);

        db.close();
    }
}
