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
                .positiveText(context.getString(R.string.yes))
                .negativeText(context.getString(R.string.no))
                .positiveColor(Color.GRAY)
                .negativeColor(Color.GRAY)
                .callback(new MaterialDialog.ButtonCallback() {
                    @Override
                    public void onPositive(MaterialDialog dialog) {
                        Toast.makeText(context, "Test", Toast.LENGTH_SHORT).show();
                    }
                })
                .show();
    }
}