package com.sputnikpogrom;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.Menu;
import android.view.MenuItem;

import com.sputnikpogrom.activities.ChangeLogActivity;
import com.sputnikpogrom.fetchers.ArticlesFetcher;
import com.sputnikpogrom.fragments.CategoryFragment;
import com.sputnikpogrom.fragments.HomeFragment;
import com.sputnikpogrom.fragments.SavedFragment;

import it.neokree.materialnavigationdrawer.MaterialNavigationDrawer;
import veinhorn.sputnikpogrom.R;

/**
 * Created by veinhorn on 23.2.15.
 */

public class NavigationDrawerActivity extends MaterialNavigationDrawer {
    @Override
    public void init(Bundle savedInstanceState) {
        addSection(newSection(getString(R.string.fragment_home_title), new HomeFragment()));
        addSection(newSection(getString(R.string.fragment_saved_title), new SavedFragment()));

        addSubheader(getString(R.string.nav_drawer_categories));
        addSection(newSection(getString(R.string.category_best_articles), getFragment(ArticlesFetcher.BEST)));
        addSection(newSection(getString(R.string.category_important_articles), getFragment(ArticlesFetcher.IMPORTANT)));
        addSection(newSection(getString(R.string.category_policy), getFragment(ArticlesFetcher.POLICY)));
        addSection(newSection(getString(R.string.category_economy), getFragment(ArticlesFetcher.ECONOMIC)));
        addSection(newSection(getString(R.string.category_history), getFragment(ArticlesFetcher.HISTORY)));
        addSection(newSection(getString(R.string.category_culture), getFragment(ArticlesFetcher.CULTURE)));
        addSection(newSection(getString(R.string.category_society), getFragment(ArticlesFetcher.SOCIETY)));
        addSection(newSection(getString(R.string.category_people), getFragment(ArticlesFetcher.PEOPLE)));
        addSection(newSection(getString(R.string.category_tech), getFragment(ArticlesFetcher.TECH)));
        addSection(newSection(getString(R.string.category_science), getFragment(ArticlesFetcher.SCIENCE)));

        addSubheader(getString(R.string.nav_drawer_another_categories));
        addSection(newSection(getString(R.string.category_translations), getFragment(ArticlesFetcher.TRANSLATIONS)));
        addSection(newSection(getString(R.string.category_reading), getFragment(ArticlesFetcher.READING)));
        addSection(newSection(getString(R.string.category_images), getFragment(ArticlesFetcher.IMAGES)));
        addSection(newSection(getString(R.string.category_videos), getFragment(ArticlesFetcher.VIDEOS)));

        allowArrowAnimation();
    }

    private Fragment getFragment(int categoryType) {
        CategoryFragment fragment = new CategoryFragment();
        fragment.setArguments(getBundle(categoryType));
        return fragment;
    }

    private Bundle getBundle(int categoryType) {
        Bundle bundle = new Bundle();
        bundle.putString("url", ArticlesFetcher.getUrl(categoryType));
        return bundle;
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch(item.getItemId()) {
            case R.id.action_settings:
                break;
            case R.id.action_changelog:
                startActivity(new Intent(NavigationDrawerActivity.this, ChangeLogActivity.class));
                break;
            case R.id.action_about:
                break;
        }
        return super.onOptionsItemSelected(item);
    }
}