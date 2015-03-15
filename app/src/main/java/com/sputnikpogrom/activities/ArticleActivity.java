package com.sputnikpogrom.activities;

import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.webkit.WebView;
import android.widget.Toast;

import butterknife.ButterKnife;
import butterknife.InjectView;
import veinhorn.sputnikpogrom.R;

import com.sputnikpogrom.ArticleSaver;
import com.sputnikpogrom.database.ArticleDbHelper;
import com.sputnikpogrom.entities.ShortArticle;
import com.sputnikpogrom.loaders.ArticleLoader;

import java.util.List;

/**
 * Created by veinhorn on 19.2.15.
 */
public class ArticleActivity extends ActionBarActivity {
    @InjectView(R.id.article_webview) WebView articleWebView;
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
        ArticleLoader articlesLoader = new ArticleLoader(articleUrl, articleWebView);
        articlesLoader.execute();
    }

    @Override
    protected void onDestroy() {
        articleWebView.setVisibility(View.GONE); // bug with zoom buttons without this line, still doesn't work
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
                //Toast.makeText(this, "Test", Toast.LENGTH_SHORT).show();
                //ArticleDbHelper articleDbHelper = new ArticleDbHelper(this);
                //articleDbHelper.deleteArticle("test art");
                //List<ShortArticle> articles = articleDbHelper.getAllArticles();
                //int t = 0;
                new ArticleSaver(article).execute();
                break;
        }
        return super.onOptionsItemSelected(item);
    }
}