package com.sputnikpogrom.utils;

import android.os.Environment;
import android.util.Log;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

/**
 * Created by veinhorn on 26.2.15.
 */
public class FilesUtil {
    private static final String TAG = "FilesUtil";

    public static boolean isExternalStorageWritable() {
        if(Environment.MEDIA_MOUNTED.equals(Environment.getExternalStorageState())) return true;
        return false;
    }

    public static String getTextFromFile(File articleHtmlFile) {
        StringBuilder articleHtml = new StringBuilder();
        try {
            BufferedReader br = new BufferedReader(new FileReader(articleHtmlFile));
            String line;
            while ((line = br.readLine()) != null) {
                articleHtml.append(line);
                articleHtml.append('\n');
            }
            br.close();
        }
        catch (IOException e) {
            Log.e(TAG, "Cannot read text from file", e);
        }
        return articleHtml.toString();
    }
}