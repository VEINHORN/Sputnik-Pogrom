package com.sputnikpogrom.logic;

import android.content.Context;
import android.os.AsyncTask;
import android.os.Environment;
import android.util.Log;
import android.widget.Toast;

import com.sputnikpogrom.database.ArticleDbHelper;
import com.sputnikpogrom.entities.ShortArticle;
import com.sputnikpogrom.fetchers.ArticleFetcher;
import com.sputnikpogrom.utils.FilesUtil;
import com.sputnikpogrom.utils.MD5Util;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 * Created by veinhorn on 26.2.15.
 */
public class ArticleSaver extends AsyncTask<String, String, Boolean> {
    private static final String TAG = ArticleSaver.class.getName();
    private static final String SPUTNIK_FOLDER = "/.sputnikpogrom";
    private static final String POSTER = ".poster";
    private static final String HTML = ".html";

    private Context context;
    private ShortArticle article;

    public ArticleSaver(Context context, ShortArticle article) {
        this.context = context;
        this.article = article;
    }

    @Override
    public Boolean doInBackground(String... args) {
        return saveArticle();
    }

    @Override
    protected void onPostExecute(Boolean isArticleSaved) {
        if(isArticleSaved) Toast.makeText(context, "Article is saved.", Toast.LENGTH_SHORT).show();
        else Toast.makeText(context, "You already saved this article.", Toast.LENGTH_SHORT).show();
    }

    private boolean saveArticle() {
        ArticleDbHelper articleDbHelper = new ArticleDbHelper(context);
        if(!articleDbHelper.isSuchArticle(article.getTitle())) {
            articleDbHelper.saveArticle(article);
            savePoster();
            saveArticleHtml();
            return true;
        }
        return false;
    }

    private void saveArticleHtml() {
        File targetFolder = getTargetFolder();
        String htmlFileName = MD5Util.md5Hex(article.getArticleUrl()) + HTML;
        BufferedWriter bufferedWriter = null;
        try {
            bufferedWriter = new BufferedWriter(new FileWriter(new File(targetFolder, "/" + htmlFileName)));
            String articleHtml = ArticleFetcher.getArticle(article.getArticleUrl());
            bufferedWriter.write(articleHtml);
            bufferedWriter.close();
        } catch(IOException e) {
            Log.e(TAG, "Cannot save article html.", e);
        }
    }

    private void savePoster() {
        InputStream inputStream = null;
        OutputStream outputStream = null;
        HttpURLConnection connection = null;

        try {
            URL url = new URL(article.getPosterUrl());
            connection = (HttpURLConnection)url.openConnection();
            connection.connect();

            if(connection.getResponseCode() != HttpURLConnection.HTTP_OK) {
                Log.d(TAG, "Server returned HTTP " + connection.getResponseCode() + " " + connection.getResponseMessage());
                return;
            }

            inputStream = connection.getInputStream();
            String posterFileName = MD5Util.md5Hex(article.getArticleUrl()) + POSTER;
            outputStream = new FileOutputStream(new File(getTargetFolder(), "/" + posterFileName));

            byte data[] = new byte[4096];
            int count;
            while((count = inputStream.read(data)) != -1) {
                if(isCancelled()) {
                    inputStream.close();
                    return;
                }
                outputStream.write(data, 0, count);
            }
        } catch(Exception e) {
            Log.e(TAG, "Cannot save poster.", e);
        } finally {
            try {
                if(outputStream != null) outputStream.close();
                if(inputStream != null) inputStream.close();
            } catch(IOException ignored) {}
            if(connection != null) connection.disconnect();
        }
    }

    private static File getTargetFolder() {
        return new File(Environment.getExternalStorageDirectory(), SPUTNIK_FOLDER);
    }

    public static File getPoster(ShortArticle article) {
        String posterFileName = MD5Util.md5Hex(article.getArticleUrl()) + POSTER;
        return new File(getTargetFolder(), "/" + posterFileName);
    }

    public static String getArticleHtml(ShortArticle article) {
        String htmlFileName = MD5Util.md5Hex(article.getArticleUrl()) + HTML;
        return FilesUtil.getTextFromFile(new File(getTargetFolder(), "/" + htmlFileName));
    }
}