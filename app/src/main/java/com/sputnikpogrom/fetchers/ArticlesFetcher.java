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
    public static final int NOT_FOUND = 404;

    private static final String BEST_ARTICLES_URL = "tag/premiumrussian/";
    private static final String IMPORTANT_URL = "go/mustread/";
    private static final String POLICY_URL = "go/politics/";
    private static final String ECONOMIC_URL = "go/economics/";
    private static final String HISTORY_URL = "go/history/";
    private static final String CULTURE_URL = "go/culture/";
    private static final String SOCIETY_URL = "go/society/";
    private static final String PEOPLE_URL = "go/people/";
    private static final String TECH_URL = "go/tech/";
    private static final String SCIENCE_URL = "go/science/";
    private static final String TRANSLATIONS_URL = "go/translated/";
    private static final String READING_URL = "go/read/";
    private static final String IMAGES_URL = "go/pics/";
    private static final String VIDEO_URL = "go/video/";

    public static final int HOME = 0;
    public static final int BEST = 1;
    public static final int IMPORTANT = 2;
    public static final int POLICY = 3;
    public static final int ECONOMIC = 4;
    public static final int HISTORY = 5;
    public static final int CULTURE = 6;
    public static final int SOCIETY = 7;
    public static final int PEOPLE = 8;
    public static final int TECH = 9;
    public static final int SCIENCE = 10;
    public static final int TRANSLATIONS = 11;
    public static final int READING = 12;
    public static final int IMAGES = 13;
    public static final int VIDEOS = 14;

    public static List<Article> fetchArticles(int categoryType, int pageNumber) throws IOException {
        return fetchArticles(createUrl(categoryType) + PAGE + pageNumber);
    }

    private static String createUrl(int categoryType) {
        switch(categoryType) {
            case BEST: return BASE_URL + BEST_ARTICLES_URL;
            case IMPORTANT:    return BASE_URL + IMPORTANT_URL;
            case POLICY:       return BASE_URL + POLICY_URL;
            case ECONOMIC:     return BASE_URL + ECONOMIC_URL;
            case HISTORY:      return BASE_URL + HISTORY_URL;
            case CULTURE:      return BASE_URL + CULTURE_URL;
            case SOCIETY:      return BASE_URL + SOCIETY_URL;
            case PEOPLE:       return BASE_URL + PEOPLE_URL;
            case TECH:         return BASE_URL + TECH_URL;
            case SCIENCE:      return BASE_URL + SCIENCE_URL;
            case TRANSLATIONS: return BASE_URL + TRANSLATIONS_URL;
            case READING:      return BASE_URL + READING_URL;
            case IMAGES:       return BASE_URL + IMAGES_URL;
            case VIDEOS:       return BASE_URL + VIDEO_URL;
            default: return BASE_URL;
        }
    }

    private static List<Article> fetchArticles(String url) throws IOException {
        List<Article> articles = new ArrayList<>();
        Element contentElement = Jsoup.connect(url).get().getElementById("content");
        Elements articleElements = contentElement.getElementsByClass("item-inner");

        for(Element articleElm : articleElements) articles.add(createArticle(articleElm));
        return articles;
    }

    private static Article createArticle(Element articleElm) {
        Article article = new Article("", "", "");
        Elements articleHrefElms = articleElm.getElementsByTag("a");
        if(articleHrefElms.size() > 1) {
            article.setArticleUrl(articleHrefElms.get(0).attr("href"));
            article.setTitle(articleHrefElms.get(1).text());
        }
        Elements imgElements = articleElm.getElementsByTag("img");
        if(!imgElements.isEmpty()) article.setPosterUrl(imgElements.get(0).attr("src"));
        return article;
    }
}
