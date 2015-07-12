package com.sputnikpogrom.ui;

import android.os.Bundle;

import com.sputnikpogrom.ui.categories.CategoryFragment;

import it.neokree.materialnavigationdrawer.MaterialNavigationDrawer;
import veinhorn.sputnikpogrom.R;

/**
 * Created by veinhorn on 23.2.15.
 */

public class NavigationDrawerActivity extends MaterialNavigationDrawer {
    @Override
    public void init(Bundle savedInstanceState) {
        setDrawerHeaderImage(R.drawable.nav_drawer_header);
        addSection(newSection(getString(R.string.fragment_home_title), new CategoryFragment()));

        disableLearningPattern();
        allowArrowAnimation();
    }
}