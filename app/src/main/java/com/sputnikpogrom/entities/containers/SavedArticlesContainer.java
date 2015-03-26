package com.sputnikpogrom.entities.containers;

import com.sputnikpogrom.entities.SavedArticle;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by veinhorn on 25.3.15.
 */
public class SavedArticlesContainer {
    private List<SavedArticle> savedArticles;

    public SavedArticlesContainer() {
        savedArticles = new ArrayList<>();
    }

    public int size() {
        return savedArticles.size();
    }

    public void addSavedArticle(SavedArticle article) {
        savedArticles.add(article);
    }

    public SavedArticle getSavedArticle(int index) {
        return savedArticles.get(index);
    }
}