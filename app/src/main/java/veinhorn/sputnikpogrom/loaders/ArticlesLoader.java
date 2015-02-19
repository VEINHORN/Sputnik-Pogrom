package veinhorn.sputnikpogrom.loaders;

import android.os.AsyncTask;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import veinhorn.sputnikpogrom.adapters.ShortArticlesAdapter;
import veinhorn.sputnikpogrom.entities.ShortArticle;
import veinhorn.sputnikpogrom.entities.containers.ShortArticlesContainer;
import veinhorn.sputnikpogrom.fetchers.ArticlesFetcher;

/**
 * Created by veinhorn on 19.2.15.
 */
public class ArticlesLoader extends AsyncTask<String, String, List<ShortArticle>> {
    private ShortArticlesAdapter shortArticlesAdapter;
    private ShortArticlesContainer shortArticlesContainer;

    public ArticlesLoader(ShortArticlesAdapter shortArticlesAdapter, ShortArticlesContainer shortArticlesContainer) {
        this.shortArticlesAdapter = shortArticlesAdapter;
        this.shortArticlesContainer = shortArticlesContainer;
    }

    @Override
    protected List<ShortArticle> doInBackground(String... args) {
        try {
            return ArticlesFetcher.getArticles();
        } catch(IOException e) {
            return new ArrayList<ShortArticle>();
        }
    }

    @Override
    protected void onPostExecute(List<ShortArticle> shortArticles) {
        shortArticlesContainer.addAllShortArticles(shortArticles);
        shortArticlesAdapter.notifyDataSetChanged();
        int t = 0;
    }
}