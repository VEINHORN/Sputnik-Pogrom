package com.sputnikpogrom.loaders;

import android.content.Context;
import android.util.Log;

import com.sputnikpogrom.entities.Article;
import com.sputnikpogrom.entities.containers.ArticlesContainer;
import com.sputnikpogrom.fetchers.ArticlesFetcher;
import com.sputnikpogrom.ui.categories.ArticlesAdapter;

import org.jsoup.HttpStatusException;

import java.io.IOException;
import java.util.List;

import static com.sputnikpogrom.fetchers.ArticlesFetcher.NOT_FOUND;

/**
 * Created by veinhorn on 2.8.15.
 */
public class NewArticlesLoader extends ArticlesLoader {
    public NewArticlesLoader(Context context, ArticlesContainer articles, ArticlesAdapter articlesAdapter) {
        super(context, articles, articlesAdapter);
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
            Log.e(getClass().getName(), e.getMessage());
        }
        return articles == null ? null : new ArticlesContainer(new ArticlesContainer(articles).getNewArticles());
    }
}
