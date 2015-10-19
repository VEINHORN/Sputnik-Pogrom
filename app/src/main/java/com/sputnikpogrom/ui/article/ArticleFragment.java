package com.sputnikpogrom.ui.article;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView;

import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
import com.sputnikpogrom.loaders.article.ArticleLoader;
import com.sputnikpogrom.utils.ShareUtil;

import butterknife.Bind;
import butterknife.ButterKnife;
import veinhorn.sputnikpogrom.R;

/**
 * Created by veinhorn on 15.7.15.
 */
public class ArticleFragment extends Fragment {
    @Bind(R.id.articleWebView) protected WebView articleWebView;
    @Bind(R.id.articleAdView) protected AdView adView;
    private String articleUrl;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceS) {
        setHasOptionsMenu(true);
        View view = inflater.inflate(R.layout.fragment_article, container, false);
        ButterKnife.bind(this, view);

        AdRequest request = new AdRequest.Builder().build();
        adView.loadAd(request);

        articleUrl = getArguments().getString("articleUrl");
        new ArticleLoader(articleWebView).load(articleUrl);

        return view;
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        super.onCreateOptionsMenu(menu, inflater);
        menu.clear();
        inflater.inflate(R.menu.article_fragment_actions, menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch(item.getItemId()) {
            case R.id.action_share:
                ShareUtil.share(getActivity(), articleUrl);
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }
}
