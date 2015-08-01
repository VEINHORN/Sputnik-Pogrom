package com.sputnikpogrom.utils;

import android.app.Activity;
import android.content.Context;
import android.graphics.Color;
import android.widget.Toast;

import com.afollestad.materialdialogs.MaterialDialog;

import veinhorn.sputnikpogrom.R;

/**
 * Created by veinhorn on 25.2.15.
 */
public class DialogsUtil {
    public static MaterialDialog showNoInternetConnectionDialog(final Context context) {
        return new MaterialDialog.Builder(context)
                .title(context.getString(R.string.no_internet_connection))
                .content(context.getString(R.string.no_internet_connection_message))
                .positiveText(context.getString(R.string.ok))
                .positiveColor(Color.GRAY)
                .show();
    }

    public static MaterialDialog showNoSdCardDialog(final Context context) {
        return new MaterialDialog.Builder(context)
                .title("Cannot save article")
                .content("You don't have SD card.")
                .positiveText("OK")
                .positiveColor(Color.GRAY)
                .show();
    }

    public static MaterialDialog showCannotLoadArticlesDialog(Context context) {
        return new MaterialDialog.Builder(context)
                .title("Cannot load articles")
                .content("Cannot load articles. Check your internet connection or try again.")
                .positiveText("OK")
                .positiveColor(Color.GRAY)
                .show();
    }

    public static MaterialDialog showCannotLoadArticleDialog(Context context) {
        return new MaterialDialog.Builder(context)
                .title("Cannot load article")
                .content("Cannot load article. Check your internet connection or try again.")
                .positiveText("OK")
                .positiveColor(Color.GRAY)
                .show();
    }
}