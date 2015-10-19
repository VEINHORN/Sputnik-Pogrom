package com.sputnikpogrom.ui.article;

import android.app.Activity;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import com.sputnikpogrom.utils.ShareUtil;

import butterknife.Bind;
import butterknife.ButterKnife;
import veinhorn.sputnikpogrom.R;

/**
 * Created by veinhorn on 15.7.15.
 */
public class ArticleFragment extends Fragment {
    @Bind(R.id.articleWebView) protected WebView articleWebView;
    private String articleUrl;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceS) {
        setHasOptionsMenu(true);
        Activity activity = getActivity();
        View view = inflater.inflate(R.layout.fragment_article, container, false);
        ButterKnife.bind(this, view);

        articleUrl = getArguments().getString("articleUrl");

        loadArticle();
        //new ArticleLoader(activity, articleWebView).execute(getArguments().getString("articleUrl"));
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

    private void loadArticle() {
        WebSettings settings = articleWebView.getSettings();
        settings.setJavaScriptEnabled(true);
        articleWebView.loadUrl(articleUrl);

        articleWebView.setWebViewClient(new CustomWebViewClient());
    }

    private class CustomWebViewClient extends WebViewClient {
        @Override
        public boolean shouldOverrideUrlLoading(WebView view, String url) {
            view.loadUrl(url);
            return true;
        }
    }
}
