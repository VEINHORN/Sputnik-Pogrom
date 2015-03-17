package com.sputnikpogrom.entities;

/**
 * Created by veinhorn on 19.2.15.
 */
public class ShortArticle {
    private String title;
    private String posterUrl;
    private String articleUrl;

    public ShortArticle() {}

    public ShortArticle(String title, String articleUrl, String posterUrl) {
        this.title = title;
        this.articleUrl = articleUrl;
        this.posterUrl = posterUrl;
    }

    public String getPosterUrl() {
        return posterUrl;
    }

    public void setPosterUrl(String posterUrl) {
        this.posterUrl = posterUrl;
    }

    public String getArticleUrl() {
        return articleUrl;
    }

    public void setArticleUrl(String articleUrl) {
        this.articleUrl = articleUrl;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }
}