package com.sputnikpogrom.ui.categories;


import android.app.Activity;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.GridView;

import com.sputnikpogrom.entities.containers.ArticlesContainer;
import com.sputnikpogrom.loaders.ArticlesLoader;
import com.sputnikpogrom.ui.categories.adapters.ArticlesAdapter;
import com.sputnikpogrom.utils.DialogsUtil;
import com.sputnikpogrom.utils.NetworkUtil;

import butterknife.ButterKnife;
import butterknife.InjectView;
import veinhorn.sputnikpogrom.R;

/**
 * Created by veinhorn on 1.7.15.
 */
public class HomeFragment extends Fragment {
    @InjectView(R.id.articles_gridview) GridView articlesGridView;

    private ArticlesLoader articlesLoader;
    private ArticlesAdapter articlesAdapter;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        Activity activity = getActivity();
        View view = inflater.inflate(R.layout.fragment_home, container, false);

        if(NetworkUtil.isNetworkAvailable(activity)) {
            ButterKnife.inject(this, view);
            articlesAdapter = new ArticlesAdapter(activity);
            articlesGridView.setAdapter(articlesAdapter);

            articlesLoader = new ArticlesLoader(activity, articlesAdapter);
            articlesLoader.execute();
            return view;
        } else {
            DialogsUtil.showNoInternetConnectionDialog(activity);
            return view;
        }
    }
}
