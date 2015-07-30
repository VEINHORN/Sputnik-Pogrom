package com.sputnikpogrom.entities.containers;

import com.sputnikpogrom.entities.Article;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by veinhorn on 2.7.15.
 */
public class ArticlesContainer {
    private static final int NEW_ARTICLES_SIZE = 5;

    private List<Article> articles;

    public ArticlesContainer() {
        articles = new ArrayList<>();
    }

    public ArticlesContainer(List<Article> articles) {
        this.articles = new ArrayList<>();
        for(Article article : articles) this.articles.add(article);
    }

    public void addArticle(Article article) {
        articles.add(article);
    }

    public void addArticles(ArticlesContainer articles) {
        this.articles.addAll(articles.getArticles());
    }

    public Article getArticle(int position) {
        return articles.get(position);
    }

    public int getSize() {
        return articles.size();
    }

    public List<Article> getArticles() {
        return articles;
    }

    public boolean isEmpty() {
        return articles.isEmpty();
    }

    public List<Article> getNewArticles() {
        return articles.subList(0, NEW_ARTICLES_SIZE);
    }
}
