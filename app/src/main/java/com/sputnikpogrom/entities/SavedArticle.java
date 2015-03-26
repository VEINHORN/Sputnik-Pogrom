package com.sputnikpogrom.entities;

import java.io.File;

/**
 * Created by veinhorn on 25.3.15.
 */
public class SavedArticle extends ShortArticle {
    private File poster;

    public File getPoster() {
        return poster;
    }

    public void setPoster(File poster) {
        this.poster = poster;
    }
}