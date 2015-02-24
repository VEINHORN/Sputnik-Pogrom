package com.sputnikpogrom;

import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;

import butterknife.ButterKnife;
import butterknife.InjectView;
import veinhorn.sputnikpogrom.R;
import com.sputnikpogrom.activities.ArticleActivity;
import com.sputnikpogrom.activities.ChangeLogActivity;
import com.sputnikpogrom.adapters.ShortArticlesAdapter;
import com.sputnikpogrom.entities.containers.ShortArticlesContainer;
import com.sputnikpogrom.loaders.ArticlesLoader;


public class MainActivity extends ActionBarActivity {
    @InjectView(R.id.short_articles_list_view) GridView shortArticlesGridView;
    private Integer addedToAdapterItemsCounter = 0; // contains number of added to adapter items
    private Integer pageNumber = 1; // page number for Sputnik&Pogrom fetcher

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fragment_home);
        ButterKnife.inject(this);

        final ShortArticlesContainer shortArticlesContainer = new ShortArticlesContainer();
        final ShortArticlesContainer adapterShortArticlesContainer = new ShortArticlesContainer();

        final ShortArticlesAdapter shortArticlesAdapter = new ShortArticlesAdapter(this, adapterShortArticlesContainer);
        shortArticlesGridView.setAdapter(shortArticlesAdapter);

        ArticlesLoader articlesLoader = new ArticlesLoader(shortArticlesAdapter, shortArticlesContainer,
                adapterShortArticlesContainer, addedToAdapterItemsCounter, shortArticlesGridView, pageNumber);
        articlesLoader.execute();

        shortArticlesGridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent(MainActivity.this, ArticleActivity.class);
                String articleUrl = shortArticlesContainer.getShortArticle(position).getArticleUrl();
                String articleTitle = shortArticlesContainer.getShortArticle(position).getTitle();
                intent.putExtra("article_title", articleTitle);
                intent.putExtra("article_url", articleUrl);
                startActivity(intent);
            }
        });
    }


}