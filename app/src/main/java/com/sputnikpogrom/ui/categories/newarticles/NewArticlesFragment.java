package com.sputnikpogrom.ui.categories.newarticles;

import android.app.Activity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.sputnikpogrom.entities.containers.ArticlesContainer;
import com.sputnikpogrom.fetchers.ArticlesFetcher;
import com.sputnikpogrom.holders.PageNumberHolder;
import com.sputnikpogrom.loaders.ArticlesLoader;
import com.sputnikpogrom.loaders.NewArticlesLoader;
import com.sputnikpogrom.ui.categories.CategoryFragment;
import com.sputnikpogrom.ui.categories.adapters.ArticlesAdapter;
import com.sputnikpogrom.utils.DialogsUtil;
import com.sputnikpogrom.utils.NetworkUtil;

import butterknife.ButterKnife;
import veinhorn.sputnikpogrom.R;

/**
 * Created by veinhorn on 2.8.15.
 */
public class NewArticlesFragment extends CategoryFragment {
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        final Activity activity = getActivity();
        View view = inflater.inflate(R.layout.fragment_category, container, false);

        if(NetworkUtil.isNetworkAvailable(activity)) {
            ButterKnife.bind(this, view);
            articles = new ArticlesContainer();
            articlesAdapter = new ArticlesAdapter(activity, articles);
            articlesListView.setAdapter(articlesAdapter);
            pageNumberHolder = new PageNumberHolder();
            categoryType = getArguments().getInt("categoryType");

            new NewArticlesLoader(activity, articles, articlesAdapter).execute(categoryType, pageNumberHolder.getPageNumber());
            return view;
        } else {
            DialogsUtil.showNoInternetConnectionDialog(activity);
            return view;
        }
    }
}
