package com.sputnikpogrom.fetchers;

import org.jsoup.Jsoup;

import java.io.IOException;

/**
 * Created by veinhorn on 19.2.15.
 */
public class ArticleFetcher {
    public static String getArticle(String articleUrl) throws IOException {
        return Jsoup.connect(articleUrl).get().getElementsByClass("item-main").get(0).html();
    }
}