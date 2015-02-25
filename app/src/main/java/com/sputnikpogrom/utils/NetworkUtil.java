package com.sputnikpogrom.utils;

import android.content.Context;
import android.net.ConnectivityManager;

/**
 * Created by veinhorn on 24.2.15.
 */
public class NetworkUtil {
    public static boolean isNetworkAvailable(Context context) {
        return ((ConnectivityManager)context.getSystemService(Context.CONNECTIVITY_SERVICE)).getActiveNetworkInfo() != null;
    }
}