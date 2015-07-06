package com.sputnikpogrom.fetchers;


import com.sputnikpogrom.entities.Article;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by veinhorn on 2.7.15.
 */
public class ArticlesFetcher {
    private static final String BASE_URL = "http://sputnikipogrom.com/";
    private static final String PAGE = "page/";

    private static final String BEST_ARTICLES_URL = "tag/premiumrussian/";

    public static final int HOME = 0;
    public static final int BEST = 1;

    public static List<Article> fetchArticles(int categoryType, int pageNumber) throws IOException {
        return fetchArticles(createUrl(categoryType) + PAGE + pageNumber);
    }

    private static String createUrl(int categoryType) {
        switch(categoryType) {
            case BEST: return BASE_URL + BEST_ARTICLES_URL;
            default: return BASE_URL;
        }
    }

    private static List<Article> fetchArticles(String url) throws IOException {
        List<Article> articles = new ArrayList<>();

        Element contentElement = Jsoup.connect(url).get().getElementById("content");
        Elements articleElements = contentElement.getElementsByClass("item-inner");

        for(Element articleElm : articleElements) {
            Article article = new Article();
            Elements articleHrefElms = articleElm.getElementsByTag("a");
            article.setTitle(articleHrefElms.get(1).text());
            article.setArticleUrl(articleHrefElms.get(0).attr("href"));
            article.setPosterUrl(articleElm.getElementsByTag("img").get(0).attr("src"));
            articles.add(article);
        }
        return articles;
    }
}
