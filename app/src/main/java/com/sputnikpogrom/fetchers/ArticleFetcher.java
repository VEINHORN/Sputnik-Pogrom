package com.sputnikpogrom.fetchers;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;

import java.io.IOException;

/**
 * Created by veinhorn on 16.7.15.
 */
public class ArticleFetcher {
    public static String fetchArticle(String articleUrl) throws IOException {
        Document document = Jsoup.connect(articleUrl).get();
        if(document != null) {
            Elements elements = document.getElementsByClass("item-main");
            if(!elements.isEmpty()) {
                return elements.get(0).html();
            } else {
                Elements bodyElms = document.getElementsByTag("body");
                if(bodyElms.isEmpty()) return null;
                else return bodyElms.get(0).html();
            }
        } else {
            return null;
        }
    }
}
