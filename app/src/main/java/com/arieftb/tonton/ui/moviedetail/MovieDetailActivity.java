/*
 * Developed by arieftb on 6/25/19 4:21 PM.
 * Last Modified 6/25/19 4:21 PM.
 * Copyright (c) 2019. All rights reserved.
 * www.arieftb.com
 */

package com.arieftb.tonton.ui.moviedetail;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Toast;

import com.arieftb.tonton.R;

public class MovieDetailActivity extends AppCompatActivity {

    public final static String MOVIE_ID = "MOVIE_ID";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_movie_detail);

        int movieId = getIntent().getIntExtra(MOVIE_ID, 0);

        Toast.makeText(this, String.valueOf(movieId), Toast.LENGTH_SHORT).show();
    }
}
