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
    public static List<ShortArticle> getArticles() throws IOException {
        List<ShortArticle> articles = new ArrayList<>();
        Element contentElement = Jsoup.connect("http://sputnikipogrom.com").get().getElementById("content");
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
}