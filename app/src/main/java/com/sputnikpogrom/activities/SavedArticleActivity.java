package com.sputnikpogrom.activities;

import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.webkit.WebView;

import com.sputnikpogrom.logic.ArticleSaver;
import com.sputnikpogrom.entities.ShortArticle;
import com.sputnikpogrom.loaders.SavedArticleLoader;

import butterknife.ButterKnife;
import butterknife.InjectView;
import veinhorn.sputnikpogrom.R;

public class SavedArticleActivity extends ActionBarActivity {
    @InjectView(R.id.article_webview) WebView savedArticleWebView;
    private ShortArticle article;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_article);
        ButterKnife.inject(this);
        String articleTitle = getIntent().getStringExtra("article_title");
        String articleUrl = getIntent().getStringExtra("article_url");
        String posterUrl = getIntent().getStringExtra("poster_url");
        article = new ShortArticle(articleTitle, articleUrl, posterUrl);
        getSupportActionBar().setTitle(articleTitle);
        //ArticleLoader articlesLoader = new ArticleLoader(articleUrl, articleWebView);
        //articlesLoader.execute();
        SavedArticleLoader savedArticlesLoader = new SavedArticleLoader(article, savedArticleWebView);
        savedArticlesLoader.execute();
    }


    @Override
    protected void onDestroy() {
        savedArticleWebView.setVisibility(View.GONE); // bug with zoom buttons without this line, still doesn't work
        super.onDestroy();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_article, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch(item.getItemId()) {
            case R.id.action_save_article:
                new ArticleSaver(this, article).execute();
                break;
        }
        return super.onOptionsItemSelected(item);
    }
}
