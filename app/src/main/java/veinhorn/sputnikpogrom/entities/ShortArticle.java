package veinhorn.sputnikpogrom.entities;

/**
 * Created by veinhorn on 19.2.15.
 */
public class ShortArticle {
    private String title;
    private String posterUrl;
    private String articleUrl;

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