/*
 * Developed by arieftb on 6/22/19 9:54 PM.
 * Last Modified 6/22/19 9:11 PM.
 * Copyright (c) 2019. All rights reserved.
 * www.arieftb.com
 */

package com.arieftb.tonton.ui.main;

import android.os.Bundle;
import android.view.MenuItem;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import com.arieftb.tonton.R;
import com.arieftb.tonton.ui.movie.MoviesFragment;
import com.arieftb.tonton.ui.tvshow.TvShowsFragment;
import com.google.android.material.bottomnavigation.BottomNavigationView;

public class MainActivity extends AppCompatActivity implements BottomNavigationView.OnNavigationItemSelectedListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        BottomNavigationView navMainBottom = findViewById(R.id.nav_main_bottom);

        onNavigationItemSelected(navMainBottom.getMenu().getItem(0));
        navMainBottom.setOnNavigationItemSelectedListener(this);
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
