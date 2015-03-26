package com.sputnikpogrom.loaders;

import android.os.AsyncTask;
import android.webkit.WebView;

import com.sputnikpogrom.logic.ArticleSaver;
import com.sputnikpogrom.entities.ShortArticle;

/**
 * Created by veinhorn on 26.3.15.
 */
public class SavedArticleLoader extends AsyncTask<String, String, String> {
    private static final String MIME_TYPE = "text/html";
    private static final String ENCODING = "utf-8";
    
    private ShortArticle shortArticle;
    private WebView savedArticleWebView;

    public SavedArticleLoader(ShortArticle shortArticle, WebView savedArticleWebView) {
        this.shortArticle = shortArticle;
        this.savedArticleWebView = savedArticleWebView;
    }

    @Override
    protected String doInBackground(String... args) {
        return ArticleSaver.getArticleHtml(shortArticle);
    }

    @Override
    protected void onPostExecute(String articleHtml) {
        String start = "<html><head><style>img { width: 100%; height: auto; };</style></head><body>";
        String end = "</body></html>";

        StringBuilder resultArticleHtml = new StringBuilder();
        resultArticleHtml.append(start);
        resultArticleHtml.append(articleHtml);
        resultArticleHtml.append(end);

        savedArticleWebView.getSettings().setDefaultTextEncodingName(ENCODING);
        savedArticleWebView.getSettings().setBuiltInZoomControls(true);
        savedArticleWebView.getSettings().setJavaScriptEnabled(true);

        savedArticleWebView.loadDataWithBaseURL(null, resultArticleHtml.toString(), MIME_TYPE, ENCODING, null);
    }
}