package com.sputnikpogrom.loaders;

import android.content.Context;
import android.os.AsyncTask;

import com.sputnikpogrom.logic.ArticleSaver;
import com.sputnikpogrom.adapters.SavedArticlesAdapter;
import com.sputnikpogrom.database.ArticleDbHelper;
import com.sputnikpogrom.entities.SavedArticle;
import com.sputnikpogrom.entities.ShortArticle;
import com.sputnikpogrom.entities.containers.SavedArticlesContainer;

import java.util.List;

/**
 * Created by veinhorn on 25.3.15.
 */
public class SavedArticlesLoader extends AsyncTask<String, String, String> {
    private Context context;
    private SavedArticlesContainer savedArticlesContainer;
    private SavedArticlesAdapter savedArticlesAdapter;

    public SavedArticlesLoader(Context context, SavedArticlesContainer savedArticlesContainer, SavedArticlesAdapter savedArticlesAdapter) {
        this.context = context;
        this.savedArticlesContainer = savedArticlesContainer;
        this.savedArticlesAdapter = savedArticlesAdapter;
    }

    @Override
    protected String doInBackground(String... args) {
        fillSavedArticlesContainer();
        return "";
    }

    @Override
    protected void onPostExecute(String result) {
        savedArticlesAdapter.notifyDataSetChanged();
    }

    private void fillSavedArticlesContainer() {
        ArticleDbHelper articleDbHelper = new ArticleDbHelper(context);
        List<ShortArticle> articles = articleDbHelper.getAllArticles();
        for(ShortArticle shortArticle : articles) {
            SavedArticle savedArticle = new SavedArticle();
            savedArticle.setTitle(shortArticle.getTitle());
            savedArticle.setPosterUrl(shortArticle.getPosterUrl());
            savedArticle.setArticleUrl(shortArticle.getArticleUrl());
            savedArticle.setPoster(ArticleSaver.getPoster(shortArticle));
            savedArticlesContainer.addSavedArticle(savedArticle);
        }
    }
}