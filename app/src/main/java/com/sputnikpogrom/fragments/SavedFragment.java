package com.sputnikpogrom.fragments;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.GridView;

import com.sputnikpogrom.activities.SavedArticleActivity;
import com.sputnikpogrom.adapters.SavedArticlesAdapter;
import com.sputnikpogrom.entities.containers.SavedArticlesContainer;
import com.sputnikpogrom.loaders.SavedArticlesLoader;

import butterknife.ButterKnife;
import butterknife.InjectView;
import veinhorn.sputnikpogrom.R;

/**
 * Created by veinhorn on 24.2.15.
 */
public class SavedFragment extends Fragment {
    @InjectView(R.id.saved_articles_list_view) GridView savedArticlesGridView;

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        final Activity activity = getActivity();
        View view = inflater.inflate(R.layout.fragment_saved, container, false);
        ButterKnife.inject(this, view);

        final SavedArticlesContainer savedArticlesContainer = new SavedArticlesContainer();
        final SavedArticlesAdapter savedArticlesAdapter = new SavedArticlesAdapter(activity, savedArticlesContainer);
        savedArticlesGridView.setAdapter(savedArticlesAdapter);

        SavedArticlesLoader savedArticlesLoader = new SavedArticlesLoader(activity, savedArticlesContainer, savedArticlesAdapter);
        savedArticlesLoader.execute();

        savedArticlesGridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent(activity, SavedArticleActivity.class);
                String articleUrl = savedArticlesContainer.getSavedArticle(position).getArticleUrl();
                String articleTitle = savedArticlesContainer.getSavedArticle(position).getTitle();
                String posterUrl = savedArticlesContainer.getSavedArticle(position).getPosterUrl();
                intent.putExtra("article_title", articleTitle);
                intent.putExtra("article_url", articleUrl);
                intent.putExtra("poster_url", posterUrl);
                startActivity(intent);
            }
        });

        return view;
    }
}