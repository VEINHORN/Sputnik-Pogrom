package com.sputnikpogrom.ui.favourite;

import android.app.Activity;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.app.ActionBar;
import android.support.v7.app.ActionBarActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;

import com.github.ksoichiro.android.observablescrollview.ObservableListView;
import com.github.ksoichiro.android.observablescrollview.ObservableScrollViewCallbacks;
import com.github.ksoichiro.android.observablescrollview.ScrollState;
import com.sputnikpogrom.R;
import com.sputnikpogrom.db.dao.FavouriteDAO;
import com.sputnikpogrom.db.dao.FavouriteDAOImpl;
import com.sputnikpogrom.entities.Article;
import com.sputnikpogrom.entities.containers.ArticlesContainer;
import com.sputnikpogrom.ui.NavigationDrawerActivity;
import com.sputnikpogrom.ui.article.ArticleFragment;
import com.sputnikpogrom.ui.categories.ArticlesAdapter;
import com.sputnikpogrom.utils.DialogsUtil;
import com.sputnikpogrom.utils.NetworkUtil;
import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnItemClick;

/**
 * Created by veinhorn on 31.10.15.
 */
public class FavouriteFragment extends Fragment implements ObservableScrollViewCallbacks {
    @Bind(R.id.articles_listview) protected ObservableListView articlesListView;
    protected ArticlesContainer articles;
    protected ArticlesAdapter articlesAdapter;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        final Activity activity = getActivity();
        View view = inflater.inflate(R.layout.fragment_category, container, false);
        ButterKnife.bind(this, view);

        if (NetworkUtil.isNetworkAvailable(activity)) {
            articles = new ArticlesContainer();
            articlesAdapter = new ArticlesAdapter(activity, articles);
            articlesListView.setScrollViewCallbacks(this);
            articlesListView.setAdapter(articlesAdapter);
            new AsyncTask<String, Integer, ArticlesContainer>() {
                @Override
                protected ArticlesContainer doInBackground(String... args) {
                    FavouriteDAO favouriteDAO = new FavouriteDAOImpl(getActivity());
                    return new ArticlesContainer(favouriteDAO.findAll());
                }

                @Override
                protected void onPostExecute(ArticlesContainer favouriteArticles) {
                    if (favouriteArticles.getSize() != 0) {
                        articles.addArticles(favouriteArticles);
                        articlesAdapter.notifyDataSetChanged();
                    } else {
                        DialogsUtil.showNoArticlesDialog(getActivity());
                    }
                }
            }.execute();
        } else {
            DialogsUtil.showNoInternetConnectionDialog(activity);
        }
        return view;
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

    @OnItemClick(R.id.articles_listview)
    protected void selectCategory(AdapterView<?> parent, View view, int position, long id) {
        Fragment articleFragment = new ArticleFragment();
        Bundle bundle = new Bundle();
        Article article = articles.getArticle(position);
        bundle.putString("articleTitle", article.getTitle());
        bundle.putString("articlePosterUrl", article.getPosterUrl());
        bundle.putString("articleUrl", article.getArticleUrl());
        articleFragment.setArguments(bundle);
        ((NavigationDrawerActivity) getActivity()).setFragmentChild(articleFragment, article.getTitle());
    }
}
