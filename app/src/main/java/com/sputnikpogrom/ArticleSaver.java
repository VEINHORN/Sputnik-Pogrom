package com.sputnikpogrom;

import android.os.Environment;
import android.util.Log;

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
public class ArticleSaver {
    private static final String TAG = ArticleSaver.class.getName();

    private static final String SPUTNIK_FOLDER = "/.sputnikpogrom";

    private static final String POSTER = "/poster/";
    private static final String HTML = "/html/";

    // store html in /html folder, posters to /posters
    public static void saveArticle(String articleUrl, String articleHtml, String posterUrl, String type) {
        String articleHtmlFileName = MD5Util.md5Hex(articleUrl + HTML);
        String articlePosterFileName = MD5Util.md5Hex(articleUrl + HTML);

        saveArticleHTML(articleHtmlFileName, articleHtml);
    }

    private static void saveArticleHTML(String articleHtmlFileName, String articleHtml) {
        BufferedWriter stream = null;
        try {
            File extStorageDir = Environment.getExternalStorageDirectory();
            File articleHtmlFile = new File(extStorageDir, SPUTNIK_FOLDER + HTML + articleHtmlFileName);
            stream = new BufferedWriter(new FileWriter(articleHtmlFile));
            stream.write("fgfdgdfgkdfg;ljtrlejgilfdjklgjsfdk;lgjskdfgjsdklgjsfdklgjfdklfd");
        } catch(IOException e) {
            Log.e(TAG, "Cannot save file.", e);
        } finally {
            try {
                if(stream != null) stream.close();
            } catch(IOException e) {
                Log.e(TAG, "Cannot close stream.", e);
            }
        }
    }

    private static void savePoster(String articlePosterFileName, String posterUrl) {

    }
}