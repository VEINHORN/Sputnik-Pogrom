package veinhorn.sputnikpogrom.loaders;

import android.os.AsyncTask;
import android.util.Log;
import android.widget.AbsListView;
import android.widget.GridView;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import veinhorn.sputnikpogrom.adapters.ShortArticlesAdapter;
import veinhorn.sputnikpogrom.entities.ShortArticle;
import veinhorn.sputnikpogrom.entities.containers.ShortArticlesContainer;
import veinhorn.sputnikpogrom.fetchers.ArticleFetcher;
import veinhorn.sputnikpogrom.fetchers.ArticlesFetcher;

/**
 * Created by veinhorn on 19.2.15.
 */
public class ArticlesLoader extends AsyncTask<String, String, List<ShortArticle>> {
    private ShortArticlesAdapter shortArticlesAdapter;
    private ShortArticlesContainer shortArticlesContainer;
    private ShortArticlesContainer adapterShortArticlesContainer;
    private Integer addedToAdapterItemsCounter;
    private GridView shortArticlesGridView;

    public ArticlesLoader(ShortArticlesAdapter shortArticlesAdapter, ShortArticlesContainer shortArticlesContainer,
                          ShortArticlesContainer adapterShortArticlesContainer, Integer addedToAdapterItemsCounter,
                          GridView shortArticlesGridView) {
        this.shortArticlesAdapter = shortArticlesAdapter;
        this.shortArticlesContainer = shortArticlesContainer;
        this.adapterShortArticlesContainer = adapterShortArticlesContainer;
        this.addedToAdapterItemsCounter = addedToAdapterItemsCounter;
        this.shortArticlesGridView = shortArticlesGridView;
    }

    @Override
    protected List<ShortArticle> doInBackground(String... args) {
        try {
            return ArticlesFetcher.getArticles(ArticlesFetcher.BASE_URL);
        } catch(IOException e) {
            return new ArrayList<ShortArticle>();
        }
    }

    @Override
    protected void onPostExecute(List<ShortArticle> shortArticles) {
        shortArticlesContainer.addAllShortArticles(shortArticles);

        adapterShortArticlesContainer.addContainer(shortArticlesContainer, addedToAdapterItemsCounter, ShortArticlesContainer.SIZE);
        addedToAdapterItemsCounter += ShortArticlesContainer.SIZE;

        shortArticlesAdapter.notifyDataSetChanged();
        // add here listener because was bug when added seamless first 5 articles
        shortArticlesGridView.setOnScrollListener(new AbsListView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(AbsListView view, int scrollState) {

            }

            @Override
            public void onScroll(AbsListView view, int firstVisibleItem, int visibleItemCount, int totalItemCount) {
                if(firstVisibleItem != 0 && totalItemCount != 0 && firstVisibleItem + visibleItemCount == totalItemCount) {
                    adapterShortArticlesContainer.addContainer(shortArticlesContainer, addedToAdapterItemsCounter, ShortArticlesContainer.SIZE);
                    addedToAdapterItemsCounter += ShortArticlesContainer.SIZE;
                    shortArticlesAdapter.notifyDataSetChanged();
                    Log.d("End of the list., totalItemCount", Integer.toString(totalItemCount));
                } else {
                    Log.d("Not End of the list.", "");
                }
            }
        });
    }
}