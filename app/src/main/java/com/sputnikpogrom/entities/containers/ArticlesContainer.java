package com.sputnikpogrom.entities.containers;

import com.sputnikpogrom.entities.Article;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by veinhorn on 2.7.15.
 */
public class ArticlesContainer {
    private List<Article> articles;

    public ArticlesContainer() {
        articles = new ArrayList<>();
    }

    public ArticlesContainer(List<Article> articlesList) {
        articles = new ArrayList<>();
        for(Article article : articlesList) articles.add(article);
    }

    public void addArticle(Article article) {
        articles.add(article);
    }

    public Article getArticle(int position) {
        return articles.get(position);
    }

    public int getSize() {
        return articles.size();
    }
}
