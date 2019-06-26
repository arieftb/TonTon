/*
 * Developed by arieftb on 6/25/19 4:21 PM.
 * Last Modified 6/25/19 4:21 PM.
 * Copyright (c) 2019. All rights reserved.
 * www.arieftb.com
 */

package com.arieftb.tonton.ui.moviedetail;

import android.os.Bundle;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProviders;

import com.arieftb.tonton.R;
import com.arieftb.tonton.model.Movie;
import com.bumptech.glide.Glide;

public class MovieDetailActivity extends AppCompatActivity {

    public final static String MOVIE_ID = "MOVIE_ID";
    private TextView textMovieTitle, textMovieData, textMovieRating, textMovieDescription;
    private ImageView imageMovieBanner, imageMoviePoster;
    private ProgressBar progressMovieRating;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_movie_detail);
        setView();

        int movieId = getIntent().getIntExtra(MOVIE_ID, 0);

        MovieDetailViewModel movieDetailViewModel = ViewModelProviders.of(this).get(MovieDetailViewModel.class);
        movieDetailViewModel.setMovieId(movieId);

        setMovieDetail(movieDetailViewModel.getMovieDetail());
    }

    private void setView() {
        textMovieTitle = findViewById(R.id.text_movie_detail_title);
        textMovieData = findViewById(R.id.text_movie_detail_data);
        textMovieDescription = findViewById(R.id.text_movie_detail_description);
        textMovieRating = findViewById(R.id.text_movie_detail_rating);
        imageMovieBanner = findViewById(R.id.image_movie_detail_banner);
        imageMoviePoster = findViewById(R.id.image_movie_detail_poster);
        progressMovieRating = findViewById(R.id.progress_movie_detail_rating);
    }


    private void setMovieDetail(Movie movieDetail) {
        textMovieTitle.setText(movieDetail.getTitle());
        textMovieRating.setText(String.valueOf(movieDetail.getRating()));
        textMovieDescription.setText(movieDetail.getDescription());
        textMovieData.setText(getString(R.string.text_movie_data, movieDetail.getGenre(), movieDetail.getReleaseDate()));
        progressMovieRating.setProgress((int) (movieDetail.getRating() * 10));
        setMovieImage(imageMoviePoster, movieDetail.getPoster());
        setMovieImage(imageMovieBanner, movieDetail.getPoster());
    }

    private void setMovieImage(ImageView imageView, int poster) {
        Glide.with(this)
                .load(poster)
                .into(imageView);
    }
}
