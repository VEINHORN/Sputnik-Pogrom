package com.sputnikpogrom.ui.categories;


import android.app.Activity;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AbsListView;
import android.widget.AdapterView;
import android.widget.GridView;
import com.sputnikpogrom.entities.Article;
import com.sputnikpogrom.entities.containers.ArticlesContainer;
import com.sputnikpogrom.holders.PageNumberHolder;
import com.sputnikpogrom.loaders.ArticlesLoader;
import com.sputnikpogrom.ui.NavigationDrawerActivity;
import com.sputnikpogrom.ui.article.ArticleFragment;
import com.sputnikpogrom.ui.categories.adapters.ArticlesAdapter;
import com.sputnikpogrom.utils.DialogsUtil;
import com.sputnikpogrom.utils.NetworkUtil;

import butterknife.Bind;
import butterknife.ButterKnife;
import veinhorn.sputnikpogrom.R;

/**
 * Created by veinhorn on 1.7.15.
 */
public class CategoryFragment extends Fragment {
    @Bind(R.id.articles_gridview) GridView articlesGridView;

    private ArticlesContainer articles;
    private ArticlesAdapter articlesAdapter;
    private PageNumberHolder pageNumberHolder;
    private int categoryType;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        final Activity activity = getActivity();
        View view = inflater.inflate(R.layout.fragment_category, container, false);

        if(NetworkUtil.isNetworkAvailable(activity)) {
            ButterKnife.bind(this, view);
            articles = new ArticlesContainer();
            articlesAdapter = new ArticlesAdapter(activity, articles);
            articlesGridView.setAdapter(articlesAdapter);
            pageNumberHolder = new PageNumberHolder();
            categoryType = getArguments().getInt("categoryType");

            new ArticlesLoader(activity, articles, articlesAdapter).execute(categoryType, pageNumberHolder.getPageNumber());
            pageNumberHolder.increment();

            articlesGridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                    Fragment articleFragment = new ArticleFragment();
                    Bundle bundle = new Bundle();
                    Article article = articles.getArticle(position);
                    bundle.putString("articleUrl", article.getArticleUrl());
                    articleFragment.setArguments(bundle);
                    ((NavigationDrawerActivity) activity).setFragmentChild(articleFragment, article.getTitle());
                }
            });

            articlesGridView.setOnScrollListener(new AbsListView.OnScrollListener() {
                private int firstVisibleItem;
                private int visibleItemCount;
                private int totalItemCount;

                @Override
                public void onScroll(AbsListView view, int firstVisibleItem, int visibleItemCount, int totalItemCount) {
                    this.firstVisibleItem = firstVisibleItem;
                    this.visibleItemCount = visibleItemCount;
                    this.totalItemCount = totalItemCount;
                }

                @Override
                public void onScrollStateChanged(AbsListView view, int scrollState) {
                    if (scrollState == SCROLL_STATE_IDLE && firstVisibleItem + visibleItemCount == totalItemCount) {
                        Log.d("End of the list.", "End of the list.");
                        Log.d("First visible item: ", Integer.toString(firstVisibleItem));
                        Log.d("Visible item count: ", Integer.toString(visibleItemCount));
                        Log.d("Total item count: ", Integer.toString(totalItemCount));

                        new ArticlesLoader(activity, articles, articlesAdapter).execute(categoryType, pageNumberHolder.getPageNumber());
                        pageNumberHolder.increment();
                    }
                }
            });
            return view;
        } else {
            DialogsUtil.showNoInternetConnectionDialog(activity);
            return view;
        }
    }
}
