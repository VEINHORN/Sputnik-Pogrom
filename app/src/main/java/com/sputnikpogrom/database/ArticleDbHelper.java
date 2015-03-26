package com.sputnikpogrom.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import com.sputnikpogrom.entities.ShortArticle;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by veinhorn on 15.3.15.
 */
public class ArticleDbHelper extends SQLiteOpenHelper {
    private static final String TAG = "ArticleDbHelper";
    private static final int DATABASE_VERSION = 1;
    private static final String DATABASE_NAME = "Article.db";
    private static final String SQL_CREATE_ARTICLES = "CREATE TABLE " + ArticleContract.ArticleEntry.TABLE_NAME +
        " (" + ArticleContract.ArticleEntry._ID + " INTEGER PRIMARY KEY," + ArticleContract.ArticleEntry.COLUMN_NAME_TITLE +
        " TEXT," + ArticleContract.ArticleEntry.COLUMN_NAME_ARTICLE_URL + " TEXT," + ArticleContract.ArticleEntry.COLUMN_NAME_POSTER_URL + " TEXT)";
    private static final String SQL_DELETE_ARTICLES = "DROP TABLE IF EXISTS " + ArticleContract.ArticleEntry.TABLE_NAME;

    public ArticleDbHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(SQL_CREATE_ARTICLES);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL(SQL_DELETE_ARTICLES);
        onCreate(db);
    }

    @Override
    public void onDowngrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        onUpgrade(db, oldVersion, newVersion);
    }

    public void saveArticle(ShortArticle article) {
        SQLiteDatabase db = getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(ArticleContract.ArticleEntry.COLUMN_NAME_TITLE, article.getTitle());
        values.put(ArticleContract.ArticleEntry.COLUMN_NAME_ARTICLE_URL, article.getArticleUrl());
        values.put(ArticleContract.ArticleEntry.COLUMN_NAME_POSTER_URL, article.getPosterUrl());
        long newRowId = db.insert(ArticleContract.ArticleEntry.TABLE_NAME, null, values);
        if(newRowId == -1) Log.w(TAG, "Cannot put article to database.");
    }

    public List<ShortArticle> getAllArticles() {
        SQLiteDatabase db = getReadableDatabase();
        String[] projection = {
                ArticleContract.ArticleEntry.COLUMN_NAME_TITLE,
                ArticleContract.ArticleEntry.COLUMN_NAME_ARTICLE_URL,
                ArticleContract.ArticleEntry.COLUMN_NAME_POSTER_URL};
        Cursor cursor = db.query(
                ArticleContract.ArticleEntry.TABLE_NAME, projection, null, null, null, null, null);
        List<ShortArticle> articles = new ArrayList<>();
        cursor.moveToFirst();
        while(cursor.isAfterLast() == false) {
            ShortArticle article = new ShortArticle();
            article.setTitle(cursor.getString(cursor.getColumnIndex(ArticleContract.ArticleEntry.COLUMN_NAME_TITLE)));
            article.setArticleUrl(cursor.getString(cursor.getColumnIndexOrThrow(ArticleContract.ArticleEntry.COLUMN_NAME_ARTICLE_URL)));
            article.setPosterUrl(cursor.getString(cursor.getColumnIndexOrThrow(ArticleContract.ArticleEntry.COLUMN_NAME_POSTER_URL)));
            articles.add(article);
            cursor.moveToNext();
        }
        return articles;
    }

    public void deleteArticle(String article) {
        SQLiteDatabase db = getWritableDatabase();
        String selection = ArticleContract.ArticleEntry.COLUMN_NAME_TITLE + " LIKE ?";
        String[] selectionArgs = { article };
        db.delete(ArticleContract.ArticleEntry.TABLE_NAME, selection, selectionArgs);
    }

    public void deleteAllArticles() {
        SQLiteDatabase db = getWritableDatabase();
        db.delete(ArticleContract.ArticleEntry.TABLE_NAME, null, null);
    }

    public boolean isSuchArticle(String articleName) {
        SQLiteDatabase db = getReadableDatabase();
        Cursor cursor = db.query(ArticleContract.ArticleEntry.TABLE_NAME,
                new String[] { ArticleContract.ArticleEntry.COLUMN_NAME_TITLE },
                new String(ArticleContract.ArticleEntry.COLUMN_NAME_TITLE + " = ?"),
                new String[] { articleName }, null, null, null, null);
        int t = 0;
        if(cursor.getCount() > 0) return true;
        return false;
    }
}