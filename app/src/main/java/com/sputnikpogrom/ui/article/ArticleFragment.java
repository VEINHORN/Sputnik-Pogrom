package com.sputnikpogrom.ui.article;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.support.v7.app.ActionBar;
import android.support.v7.app.ActionBarActivity;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.github.ksoichiro.android.observablescrollview.ObservableScrollViewCallbacks;
import com.github.ksoichiro.android.observablescrollview.ObservableWebView;
import com.github.ksoichiro.android.observablescrollview.ScrollState;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
import com.sputnikpogrom.R;
import com.sputnikpogrom.db.dao.FavouriteDAO;
import com.sputnikpogrom.db.dao.FavouriteDAOImpl;
import com.sputnikpogrom.entities.Article;
import com.sputnikpogrom.loaders.article.ArticleLoader;
import com.sputnikpogrom.utils.ShareUtil;
import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by veinhorn on 15.7.15.
 */
public class ArticleFragment extends Fragment implements ObservableScrollViewCallbacks {
    @Bind(R.id.articleWebView) protected ObservableWebView articleWebView;
    @Bind(R.id.articleAdView) protected AdView adView;
    @Bind(R.id.fab) protected FloatingActionButton fab;
    private Article article;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceS) {
        setHasOptionsMenu(true);
        View view = inflater.inflate(R.layout.fragment_article, container, false);
        ButterKnife.bind(this, view);

        AdRequest request = new AdRequest.Builder().build();
        adView.loadAd(request);

        article = fetchArticle();
        articleWebView.setScrollViewCallbacks(this);
        new ArticleLoader(articleWebView).load(article.getArticleUrl());

        return view;
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        super.onCreateOptionsMenu(menu, inflater);
        menu.clear();
        inflater.inflate(R.menu.article_fragment_actions, menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch(item.getItemId()) {
            case R.id.action_share:
                ShareUtil.share(getActivity(), article.getArticleUrl());
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    @Override
    public void onScrollChanged(int scrollY, boolean firstScroll, boolean dragging) { }

    @Override
    public void onDownMotionEvent() { }

    @Override
    public void onUpOrCancelMotionEvent(ScrollState scrollState) {
        ActionBar actionBar = ((ActionBarActivity)getActivity()).getSupportActionBar();
        if(actionBar == null) {
            return;
        }
        if(scrollState == ScrollState.UP) {
            if(actionBar.isShowing()) {
                actionBar.hide();
            }
        } else if(scrollState == ScrollState.DOWN) {
            if(!actionBar.isShowing()) {
                actionBar.show();
            }
        }
    }

    @OnClick(R.id.fab)
    public void onClick() {
        FavouriteDAO favouriteDAO = new FavouriteDAOImpl(getActivity());
        favouriteDAO.insert(article);
        Toast.makeText(getActivity(), "Article added to Favourite", Toast.LENGTH_SHORT).show();
    }

    private Article fetchArticle() {
        Bundle bundle = getArguments();
        return new Article(bundle.getString("articleTitle"),
                           bundle.getString("articlePosterUrl"),
                           bundle.getString("articleUrl"));
    }
}
