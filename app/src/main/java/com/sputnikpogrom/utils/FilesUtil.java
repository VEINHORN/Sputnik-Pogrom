package com.sputnikpogrom.utils;

import android.os.Environment;

/**
 * Created by veinhorn on 26.2.15.
 */
public class FilesUtil {
    public static boolean isExternalStorageWritable() {
        if(Environment.MEDIA_MOUNTED.equals(Environment.getExternalStorageState())) return true;
        else return false;
    }
}