package com.sputnikpogrom;

import android.os.AsyncTask;
import android.os.Environment;
import android.util.Log;

import com.sputnikpogrom.entities.ShortArticle;
import com.sputnikpogrom.fetchers.ArticlesFetcher;
import com.sputnikpogrom.utils.MD5Util;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * Created by veinhorn on 26.2.15.
 */
public class ArticleSaver extends AsyncTask<String, String, String> {
    private static final String TAG = ArticleSaver.class.getName();
    private static final String SPUTNIK_FOLDER = "/.sputnikpogrom";

    private ShortArticle article;

    public ArticleSaver(ShortArticle article) {
        this.article = article;
    }

    @Override
    public String doInBackground(String... args) {
        saveArticle(article);
        return "";
    }

    private void saveArticle(ShortArticle article) {
        savePoster();
    }

    private void savePoster() {

    }
}