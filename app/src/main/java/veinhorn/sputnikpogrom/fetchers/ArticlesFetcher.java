package veinhorn.sputnikpogrom.fetchers;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import veinhorn.sputnikpogrom.entities.ShortArticle;

/**
 * Created by veinhorn on 19.2.15.
 */
public class ArticlesFetcher {
    public static final String BASE_URL = "http://sputnikipogrom.com";

    public static List<ShortArticle> getArticles(String url) throws IOException {
        List<ShortArticle> articles = new ArrayList<>();
        Element contentElement = Jsoup.connect(url).get().getElementById("content");
        Elements articleElements = contentElement.getElementsByClass("item-inner");
        for(Element article : articleElements) {
            ShortArticle shortArticle = new ShortArticle();
            shortArticle.setTitle(article.getElementsByTag("a").get(1).text());
            shortArticle.setArticleUrl(article.getElementsByTag("a").get(0).attr("href"));
            shortArticle.setPosterUrl(article.getElementsByTag("img").get(0).attr("src"));
            articles.add(shortArticle);
        }
        return articles;
    }

    public static List<ShortArticle> getArticles(String url, int pageNumber) throws IOException {
        return getArticles(url + "/" + pageNumber);
    }
}