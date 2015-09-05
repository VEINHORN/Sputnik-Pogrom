package com.sputnikpogrom.utils;

import android.content.Context;
import android.content.Intent;

import veinhorn.sputnikpogrom.R;

/**
 * Created by veinhorn on 5.9.15.
 */
public class ShareUtil {
    public static void share(Context context, String articleUrl) {
        Intent sendIntent = new Intent();
        sendIntent.setAction(Intent.ACTION_SEND);
        sendIntent.putExtra(Intent.EXTRA_TEXT, generateSharePost(context, articleUrl));
        sendIntent.setType("text/html");
        context.startActivity(Intent.createChooser(sendIntent, "Share"));
    }

    private static String generateSharePost(Context context, String articleUrl) {
        return articleUrl + " " + context.getString(R.string.appUrl);
    }
}
