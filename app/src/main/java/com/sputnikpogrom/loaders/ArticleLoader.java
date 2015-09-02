package com.sputnikpogrom.loaders;

import android.content.Context;
import android.os.AsyncTask;
import android.util.Log;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import com.sputnikpogrom.fetchers.ArticleFetcher;
import com.sputnikpogrom.utils.DialogsUtil;

import java.io.IOException;

/**
 * Created by veinhorn on 16.7.15.
 */
public class ArticleLoader extends AsyncTask<String, Void, String> {
    private static final String MIME_TYPE = "text/html";
    private static final String ENCODING = "utf-8";

    private Context context;
    private WebView articleWebView;

    public ArticleLoader(Context context, WebView articleWebView) {
        this.context = context;
        this.articleWebView = articleWebView;
    }

    @Override
    protected String doInBackground(String... urls) {
        String articleHtml = null;
        try {
            articleHtml = ArticleFetcher.fetchFullArticleHtml(urls[0]);
        } catch(IOException e) {
            Log.e(getClass().getName(), e.getMessage());
        }
        return articleHtml;
    }

    @Override
    protected void onPostExecute(String articleHtml) {
        if(articleHtml != null && !articleHtml.isEmpty()) {
            WebSettings settings = articleWebView.getSettings();
            settings.setDefaultTextEncodingName(ENCODING);
            settings.setJavaScriptEnabled(true);
            settings.setBuiltInZoomControls(true);

            articleWebView.setWebViewClient(new CustomWebViewClient());
            articleWebView.loadDataWithBaseURL(null, articleHtml, MIME_TYPE, ENCODING, null);
        } else {
            DialogsUtil.showCannotLoadArticleDialog(context);
        }
    }

    private class CustomWebViewClient extends WebViewClient {
        @Override
        public boolean shouldOverrideUrlLoading(WebView view, String url) {
            view.loadUrl(url);
            return true;
        }
    }
}
