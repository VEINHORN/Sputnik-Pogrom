package com.sputnikpogrom.services;

import android.app.IntentService;
import android.content.Intent;
import android.util.Log;

import com.sputnikpogrom.entities.Article;
import com.sputnikpogrom.entities.containers.ArticlesContainer;
import com.sputnikpogrom.fetchers.ArticlesFetcher;
import com.sputnikpogrom.holders.PageNumberHolder;

import java.io.IOException;
import java.util.List;

/**
 * Created by veinhorn on 25.7.15.
 */
public class NotificationService extends IntentService {
    public NotificationService() {
        super("NotificationService");
    }

    @Override
    protected void onHandleIntent(Intent intent) {
        ArticlesContainer articles = fetchNewArticles();
        if(articles == null) {
            Log.d(getClass().getName(), "Cannot show notification (articles was not loaded)");
        } else {
            int b = 0;
        }
    }

    private ArticlesContainer fetchNewArticles() {
        List<Article> articles = null;
        try {
            articles = ArticlesFetcher.fetchArticles(ArticlesFetcher.HOME, new PageNumberHolder().getPageNumber());
        } catch(IOException e) {
            Log.e(getClass().getName(), e.getMessage());
        }
        return articles == null ? null : new ArticlesContainer(articles);
    }
}
