package veinhorn.sputnikpogrom.loaders;

import android.os.AsyncTask;
import android.webkit.WebView;

import java.io.IOException;

import veinhorn.sputnikpogrom.fetchers.ArticleFetcher;

/**
 * Created by veinhorn on 19.2.15.
 */
public class ArticleLoader extends AsyncTask<String, String, String> {
    private static final String MIME_TYPE = "text/html";
    private static final String ENCODING = "utf-8";

    private String articleUrl;
    private WebView articleWebView;

    public ArticleLoader(String articleUrl, WebView articleWebView) {
        this.articleUrl = articleUrl;
        this.articleWebView = articleWebView;
    }
    @Override
    protected String doInBackground(String... args) {
        try {
            return ArticleFetcher.getArticle(articleUrl);
        } catch(IOException e) {
            return "";
        }
    }

    @Override
    protected void onPostExecute(String articleHtml) {
        String start = "<html><head><style>img { width: 100%; height: auto; };</style></head><body>";
        String end = "</body></html>";

        StringBuilder resultArticleHtml = new StringBuilder();
        resultArticleHtml.append(start);
        resultArticleHtml.append(articleHtml);
        resultArticleHtml.append(end);

        articleWebView.getSettings().setDefaultTextEncodingName(ENCODING);
        articleWebView.getSettings().setBuiltInZoomControls(true);
        articleWebView.getSettings().setJavaScriptEnabled(true);

        articleWebView.loadDataWithBaseURL(null, resultArticleHtml.toString(), MIME_TYPE, ENCODING, null);
    }
}