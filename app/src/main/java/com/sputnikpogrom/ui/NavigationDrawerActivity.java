package com.sputnikpogrom.ui;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;

import com.sputnikpogrom.fetchers.ArticlesFetcher;
import com.sputnikpogrom.receivers.AlarmReceiver;
import com.sputnikpogrom.ui.categories.CategoryFragment;
import com.sputnikpogrom.ui.categories.newarticles.NewArticlesFragment;
import com.sputnikpogrom.ui.settings.SettingsActivity;

import it.neokree.materialnavigationdrawer.MaterialNavigationDrawer;
import it.neokree.materialnavigationdrawer.elements.MaterialSection;
import veinhorn.sputnikpogrom.R;

/**
 * Created by veinhorn on 23.2.15.
 */

public class NavigationDrawerActivity extends MaterialNavigationDrawer {
    @Override
    public void init(Bundle savedInstanceState) {
        setDrawerHeaderImage(R.drawable.nav_drawer_header);

        addSection(newSection(getString(R.string.fragment_home_title), getFragment(ArticlesFetcher.HOME)));

        MaterialSection newArticlesSection = newSection(getString(R.string.fragment_new_articles_title), getNewArticlesFragment(ArticlesFetcher.HOME));
        addSection(newArticlesSection);

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

        addDivisor();
        addSection(newSection(getString(R.string.action_settings), R.drawable.ic_settings, new Intent(this, SettingsActivity.class)));

        disableLearningPattern();
        allowArrowAnimation();

        boolean isOpenNewArticlesFragment = getIntent().getBooleanExtra("open_new_articles_fragment", false);
        if(isOpenNewArticlesFragment) {

        }
    }

    private Fragment getFragment(int categoryType) {
        Fragment fragment = new CategoryFragment();
        fragment.setArguments(getBundle(categoryType));
        return fragment;
    }

    private Fragment getNewArticlesFragment(int categoryType) {
        Fragment fragment = new NewArticlesFragment();
        fragment.setArguments(getBundle(categoryType));
        return fragment;
    }

    private Bundle getBundle(int categoryType) {
        Bundle bundle = new Bundle();
        bundle.putInt("categoryType", categoryType);
        return bundle;
    }
}