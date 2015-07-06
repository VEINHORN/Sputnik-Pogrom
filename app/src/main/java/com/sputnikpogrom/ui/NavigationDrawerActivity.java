package com.sputnikpogrom.ui;

import android.os.Bundle;

import com.sputnikpogrom.ui.categories.HomeFragment;

import it.neokree.materialnavigationdrawer.MaterialNavigationDrawer;
import veinhorn.sputnikpogrom.R;

/**
 * Created by veinhorn on 23.2.15.
 */

public class NavigationDrawerActivity extends MaterialNavigationDrawer {
    @Override
    public void init(Bundle savedInstanceState) {
        addSection(newSection(getString(R.string.fragment_home_title), new HomeFragment()));

        disableLearningPattern();
        allowArrowAnimation();
    }
}