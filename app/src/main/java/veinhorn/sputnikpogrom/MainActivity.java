package veinhorn.sputnikpogrom;

import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AbsListView;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.ListView;

import java.util.logging.Logger;

import butterknife.ButterKnife;
import butterknife.InjectView;
import veinhorn.sputnikpogrom.activities.ArticleActivity;
import veinhorn.sputnikpogrom.adapters.ShortArticlesAdapter;
import veinhorn.sputnikpogrom.entities.containers.ShortArticlesContainer;
import veinhorn.sputnikpogrom.loaders.ArticlesLoader;


public class MainActivity extends ActionBarActivity {
    @InjectView(R.id.short_articles_list_view) GridView shortArticlesGridView;
    private Integer addedToAdapterItemsCounter = 0; // contains number of added to adapter items
    private Integer pageNumber = 1; // page number for Sputnik&Pogrom fetcher

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
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


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}