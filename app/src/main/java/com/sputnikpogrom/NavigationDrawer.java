package com.sputnikpogrom;

import android.os.Bundle;
import android.support.v4.app.Fragment;

import com.sputnikpogrom.fragments.HomeFragment;
import com.sputnikpogrom.fragments.SavedFragment;

import it.neokree.materialnavigationdrawer.MaterialNavigationDrawer;
import it.neokree.materialnavigationdrawer.elements.MaterialSection;
import veinhorn.sputnikpogrom.R;

/**
 * Created by veinhorn on 23.2.15.
 */

public class NavigationDrawer extends MaterialNavigationDrawer {
    @Override
    public void init(Bundle savedInstanceState) {
        addSection(newSection(getString(R.string.fragment_home_title), new HomeFragment()));
        addSection(newSection(getString(R.string.fragment_saved_title), new SavedFragment()));
        addSubheader(getString(R.string.nav_drawer_categories));
        addSection(newSection(getString(R.string.category_best_articles), new Fragment()));
        addSection(newSection(getString(R.string.category_important_articles), new Fragment()));
        addSection(newSection(getString(R.string.category_policy), new Fragment()));
        allowArrowAnimation();
    }
}