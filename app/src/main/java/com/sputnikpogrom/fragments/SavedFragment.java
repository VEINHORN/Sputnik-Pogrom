package com.sputnikpogrom.fragments;

import android.app.Activity;
import android.graphics.Color;
import android.os.Bundle;
import android.os.Environment;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.afollestad.materialdialogs.MaterialDialog;
import com.sputnikpogrom.ArticleSaver;
import com.sputnikpogrom.utils.DialogsUtil;
import com.sputnikpogrom.utils.FilesUtil;

import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;

import veinhorn.sputnikpogrom.R;

/**
 * Created by veinhorn on 24.2.15.
 */
public class SavedFragment extends Fragment {
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        Activity activity = getActivity();
        if(FilesUtil.isExternalStorageWritable()) {
            ArticleSaver.saveArticle();
        } else {
            DialogsUtil.showNoSdCardDialog(activity);
        }
        return inflater.inflate(R.layout.fragment_saved, container, false);
    }
}