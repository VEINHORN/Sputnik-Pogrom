package com.sputnikpogrom.ui.article;

import android.app.Activity;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView;

import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
import com.sputnikpogrom.loaders.ArticleLoader;

import butterknife.Bind;
import butterknife.ButterKnife;
import veinhorn.sputnikpogrom.R;

/**
 * Created by veinhorn on 15.7.15.
 */
public class ArticleFragment extends Fragment {
    @Bind(R.id.articleWebView) WebView articleWebView;
    @Bind(R.id.adView) AdView adView;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceS) {
        Activity activity = getActivity();
        View view = inflater.inflate(R.layout.fragment_article, container, false);
        ButterKnife.bind(this, view);

        AdRequest adRequest = new AdRequest.Builder().build();
        adView.loadAd(adRequest);

        new ArticleLoader(activity, articleWebView).execute(getArguments().getString("articleUrl"));
        return view;
    }
}
