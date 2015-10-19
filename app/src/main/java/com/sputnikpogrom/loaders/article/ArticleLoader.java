package com.sputnikpogrom.loaders.article;

import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;

/**
 * Created by veinhorn on 19.10.15.
 */
public class ArticleLoader {
    private WebView webView;

    public ArticleLoader(WebView webView) {
        this.webView = webView;
    }

    public void load(String articleUrl) {
        WebSettings settings = webView.getSettings();
        settings.setJavaScriptEnabled(true);
        webView.setWebViewClient(new CustomWebViewClient());

        webView.loadUrl(articleUrl);
    }

    private class CustomWebViewClient extends WebViewClient {
        @Override
        public boolean shouldOverrideUrlLoading(WebView view, String url) {
            view.loadUrl(url);
            return true;
        }
    }
}
