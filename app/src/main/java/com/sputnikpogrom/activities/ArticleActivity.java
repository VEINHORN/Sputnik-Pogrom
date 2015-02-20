package com.sputnikpogrom.activities;

import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.View;
import android.webkit.WebView;

import butterknife.ButterKnife;
import butterknife.InjectView;
import veinhorn.sputnikpogrom.R;
import com.sputnikpogrom.loaders.ArticleLoader;

/**
 * Created by veinhorn on 19.2.15.
 */
public class ArticleActivity extends ActionBarActivity {
    @InjectView(R.id.article_webview) WebView articleWebView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_article);
        ButterKnife.inject(this);
        String articleTitle = getIntent().getStringExtra("article_title");
        getSupportActionBar().setTitle(articleTitle);
        String articleUrl = getIntent().getStringExtra("article_url");
        ArticleLoader articlesLoader = new ArticleLoader(articleUrl, articleWebView);
        articlesLoader.execute();
    }

    @Override
    protected void onDestroy() {
        articleWebView.setVisibility(View.GONE); // bug with zoom buttons without this line, still doesn't work
        super.onDestroy();
    }
}