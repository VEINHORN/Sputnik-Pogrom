package com.sputnikpogrom.loaders;

import android.content.Context;
import android.os.AsyncTask;
import android.util.Log;
import com.sputnikpogrom.entities.Article;
import com.sputnikpogrom.entities.containers.ArticlesContainer;
import com.sputnikpogrom.fetchers.ArticlesFetcher;
import com.sputnikpogrom.ui.categories.ArticlesAdapter;
import com.sputnikpogrom.utils.DialogsUtil;
import org.jsoup.HttpStatusException;
import java.io.IOException;
import java.util.List;
import static com.sputnikpogrom.fetchers.ArticlesFetcher.NOT_FOUND;

/**
 * Created by veinhorn on 2.7.15.
 */
public class ArticlesLoader extends AsyncTask<Integer, Integer, ArticlesContainer> {
    private static final String PAGE_NOT_FOUND = "Requested page wasn't found.";
    private Context context;
    private ArticlesContainer articles;
    private ArticlesAdapter articlesAdapter;

    public ArticlesLoader(Context context, ArticlesContainer articles, ArticlesAdapter articlesAdapter) {
        this.context = context;
        this.articles = articles;
        this.articlesAdapter = articlesAdapter;
    }

    @Override
    protected ArticlesContainer doInBackground(Integer... params) {
        Integer categoryType = params[0], pageNumber = params[1];
        List<Article> articles = null;
        try {
            articles = ArticlesFetcher.fetchArticles(categoryType, pageNumber);
        } catch(HttpStatusException e) {
            if(e.getStatusCode() == NOT_FOUND) return new ArticlesContainer();
        } catch(IOException e) {
            if(e.getMessage() != null) Log.e(getClass().getName(), e.getMessage());
        }
        return articles == null ? null : new ArticlesContainer(articles);
    }

    @Override
    protected void onPostExecute(ArticlesContainer articles) {
        if(articles == null) {
            DialogsUtil.showCannotLoadArticlesDialog(context);
        } else {
            if(!articles.isEmpty()) {
                this.articles.addArticles(articles);
                articlesAdapter.notifyDataSetChanged();
            } else {
                Log.i(getClass().getName(), PAGE_NOT_FOUND);
            }
        }
    }
}
