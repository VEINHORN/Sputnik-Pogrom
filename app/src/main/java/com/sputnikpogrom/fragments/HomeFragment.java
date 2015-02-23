package com.sputnikpogrom.fragments;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.GridView;

import com.sputnikpogrom.adapters.ShortArticlesAdapter;
import com.sputnikpogrom.entities.containers.ShortArticlesContainer;
import com.sputnikpogrom.loaders.ArticlesLoader;

import butterknife.ButterKnife;
import butterknife.InjectView;
import veinhorn.sputnikpogrom.R;

/**
 * Created by veinhorn on 24.2.15.
 */

public class HomeFragment extends Fragment {
    @InjectView(R.id.short_articles_list_view) GridView shortArticlesGridView;
    private Integer addedToAdapterItemsCounter = 0; // contains number of added to adapter items
    private Integer pageNumber = 1; // page number for Sputnik&Pogrom fetcher

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_home, container, false);
        ButterKnife.inject(this, view);

        final ShortArticlesContainer shortArticlesContainer = new ShortArticlesContainer();
        final ShortArticlesContainer adapterShortArticlesContainer = new ShortArticlesContainer();

        final ShortArticlesAdapter shortArticlesAdapter = new ShortArticlesAdapter(getActivity(), adapterShortArticlesContainer);
        shortArticlesGridView.setAdapter(shortArticlesAdapter);

        ArticlesLoader articlesLoader = new ArticlesLoader(shortArticlesAdapter, shortArticlesContainer,
                adapterShortArticlesContainer, addedToAdapterItemsCounter, shortArticlesGridView, pageNumber);
        articlesLoader.execute();

        return view;
    }
}