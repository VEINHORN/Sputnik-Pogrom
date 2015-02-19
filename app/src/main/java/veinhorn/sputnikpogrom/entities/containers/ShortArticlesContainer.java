package veinhorn.sputnikpogrom.entities.containers;

import java.util.ArrayList;
import java.util.List;

import veinhorn.sputnikpogrom.entities.ShortArticle;

/**
 * Created by veinhorn on 19.2.15.
 */
public class ShortArticlesContainer {
    private List<ShortArticle> shortArticles;

    public ShortArticlesContainer() {
        shortArticles = new ArrayList<>();
    }

    public void addShortArticle(ShortArticle shortArticle) {
        shortArticles.add(shortArticle);
    }

    public void addAllShortArticles(List<ShortArticle> shortArticles) {
        this.shortArticles.addAll(shortArticles);
    }

    public ShortArticle getShortArticle(int index) {
        return shortArticles.get(index);
    }

    public int size() {
        return shortArticles.size();
    }
}