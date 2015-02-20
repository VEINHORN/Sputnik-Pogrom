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
    private Integer pageNumber;

    public ArticlesLoader(ShortArticlesAdapter shortArticlesAdapter, ShortArticlesContainer shortArticlesContainer,
                          ShortArticlesContainer adapterShortArticlesContainer, Integer addedToAdapterItemsCounter,
                          GridView shortArticlesGridView, Integer pageNumber) {
        this.shortArticlesAdapter = shortArticlesAdapter;
        this.shortArticlesContainer = shortArticlesContainer;
        this.adapterShortArticlesContainer = adapterShortArticlesContainer;
        this.addedToAdapterItemsCounter = addedToAdapterItemsCounter;
        this.shortArticlesGridView = shortArticlesGridView;
        this.pageNumber = pageNumber;
    }

    @Override
    protected List<ShortArticle> doInBackground(String... args) {
        try {
            return ArticlesFetcher.getArticles(ArticlesFetcher.BASE_URL, pageNumber);
        } catch(IOException e) {
            return new ArrayList<ShortArticle>();
        }
    }

    @Override
    protected void onPostExecute(List<ShortArticle> shortArticles) {
        shortArticlesContainer.addAllShortArticles(shortArticles);

        adapterShortArticlesContainer.addContainer(shortArticlesContainer, addedToAdapterItemsCounter, ShortArticlesContainer.SIZE);
        addedToAdapterItemsCounter = adapterShortArticlesContainer.size();

        shortArticlesAdapter.notifyDataSetChanged();
        if(pageNumber == 1) { // add scroll listener only first time, when we try to fetch first page from site
            // add here listener because was bug when added seamless first 5 articles
            shortArticlesGridView.setOnScrollListener(new AbsListView.OnScrollListener() {
                private int firstVisibleItem;
                private int visibleItemCount;
                private int totalItemCount;

                @Override
                public void onScroll(AbsListView view, int firstVisibleItem, int visibleItemCount, int totalItemCount) {
                    this.firstVisibleItem = firstVisibleItem;
                    this.visibleItemCount = visibleItemCount;
                    this.totalItemCount = totalItemCount;
                }

                @Override
                public void onScrollStateChanged(AbsListView view, int scrollState) {
                    // bottom gridview event handling
                    if(scrollState == SCROLL_STATE_IDLE && firstVisibleItem + visibleItemCount == totalItemCount) {
                        adapterShortArticlesContainer.addContainer(shortArticlesContainer, addedToAdapterItemsCounter, ShortArticlesContainer.SIZE);
                        addedToAdapterItemsCounter = adapterShortArticlesContainer.size();
                        shortArticlesAdapter.notifyDataSetChanged();
                        Log.d("End of the list.", "End of the list.");
                        Log.d("First visible item: ", Integer.toString(firstVisibleItem));
                        Log.d("Total item count: ", Integer.toString(totalItemCount));
                        Log.d("Added to adapter: ", Integer.toString(addedToAdapterItemsCounter));
                    }
                    if(addedToAdapterItemsCounter == shortArticlesContainer.size()) {
                        pageNumber++;
                        ArticlesLoader articlesLoader = new ArticlesLoader(shortArticlesAdapter, shortArticlesContainer,
                                adapterShortArticlesContainer, addedToAdapterItemsCounter, shortArticlesGridView, pageNumber);
                        articlesLoader.execute();
                    }
                }
            });
        }
    }
}