package com.sputnikpogrom.services;

import android.app.IntentService;
import android.app.Notification;
import android.app.NotificationManager;
import android.content.Intent;
import android.support.v4.app.NotificationCompat;
import android.util.Log;

import com.sputnikpogrom.entities.Article;
import com.sputnikpogrom.entities.containers.ArticlesContainer;
import com.sputnikpogrom.fetchers.ArticlesFetcher;
import com.sputnikpogrom.holders.PageNumberHolder;

import java.io.IOException;
import java.util.List;

import veinhorn.sputnikpogrom.R;

/**
 * Created by veinhorn on 25.7.15.
 */
public class NotificationService extends IntentService {
    private static final int NOTIFICATION_ID = 1;

    public NotificationService() {
        super("NotificationService");
    }

    @Override
    protected void onHandleIntent(Intent intent) {
        ArticlesContainer articles = fetchNewArticles();
        if(articles == null) {
            Log.d(getClass().getName(), "Cannot show notification (articles was not loaded)");
        } else {
            showNotification(articles);
        }
    }

    private void showNotification(ArticlesContainer articles) {
        String contentText = createContentText(articles);
        NotificationCompat.Builder builder = new NotificationCompat.Builder(this)
                .setDefaults(Notification.DEFAULT_ALL)
                .setSmallIcon(R.drawable.notification_icon)
                .setContentTitle(getString(R.string.service_new_articles))
                .setContentText(contentText)
                .setStyle(new NotificationCompat.BigTextStyle().bigText(contentText));
        NotificationManager notificationManager = (NotificationManager)getSystemService(NOTIFICATION_SERVICE);
        notificationManager.notify(NOTIFICATION_ID, builder.build());
    }

    private String createContentText(ArticlesContainer articles) {
        StringBuilder stringBuilder = new StringBuilder();
        List<Article> articlesList = articles.getNewArticles();
        for(Article article : articlesList) {
            if(stringBuilder.length() == 0) stringBuilder.append(article.getTitle());
            else stringBuilder.append(", " + article.getTitle());
        }
        return stringBuilder.toString();
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
