package com.sputnikpogrom.holders;

/**
 * Created by veinhorn on 11.7.15.
 */
public class PageNumberHolder {
    private int pageNumber = 1;

    public void increment() {
        pageNumber++;
    }

    public int getPageNumber() {
        return pageNumber;
    }
}
