/*
 * Developed by arieftb on 6/22/19 9:54 PM.
 * Last Modified 6/22/19 9:11 PM.
 * Copyright (c) 2019. All rights reserved.
 * www.arieftb.com
 */

package com.arieftb.tonton.persentation.main;

import android.os.Bundle;
import android.view.MenuItem;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import com.arieftb.tonton.R;
import com.arieftb.tonton.persentation.movie.MoviesFragment;
import com.arieftb.tonton.persentation.tvshow.TvShowsFragment;
import com.google.android.material.bottomnavigation.BottomNavigationView;

public class MainActivity extends AppCompatActivity implements BottomNavigationView.OnNavigationItemSelectedListener {

    public final String SELECTED_MENU = "selected_menu";
    private BottomNavigationView navMainBottom;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        navMainBottom = findViewById(R.id.nav_main_bottom);
        navMainBottom.setSelectedItemId(R.id.menu_movies);
        navMainBottom.setOnNavigationItemSelectedListener(this);

        if (savedInstanceState != null) {
            int menuId = savedInstanceState.getInt(SELECTED_MENU);
            navMainBottom.setSelectedItemId(menuId);
        } else {
            navMainBottom.setSelectedItemId(R.id.menu_movies);
        }
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case R.id.menu_movies:
                setContent(getString(R.string.menu_title_movies), MoviesFragment.getInstance());
                break;
            case R.id.menu_tv_shows:
                setContent(getString(R.string.menu_title_tv_shows), TvShowsFragment.getInstance());
                break;
            default:
                setContent(getString(R.string.app_name), null);
                break;
        }
        return true;
    }

    @Override
    protected void onSaveInstanceState(@NonNull Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putInt(SELECTED_MENU, navMainBottom.getSelectedItemId());
    }

    private void setContent(String title, Fragment fragment) {
        if (getSupportActionBar() != null) {
            getSupportActionBar().setTitle(title);
        }

        if (fragment != null) {
            getSupportFragmentManager()
                    .beginTransaction()
                    .setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN)
                    .replace(R.id.frame_main_content, fragment)
                    .commit();
        }
    }
}
