/*
 * Developed by arieftb on 6/25/19 4:21 PM.
 * Last Modified 6/25/19 4:21 PM.
 * Copyright (c) 2019. All rights reserved.
 * www.arieftb.com
 */

package com.arieftb.tonton.persentation.moviedetail;

import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.lifecycle.ViewModelProviders;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import com.arieftb.tonton.BuildConfig;
import com.arieftb.tonton.R;
import com.arieftb.tonton.model.entity.MovieEntity;
import com.arieftb.tonton.utils.DialogHelper;
import com.arieftb.tonton.utils.ViewModelFactory;
import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;

public class MovieDetailActivity extends AppCompatActivity implements SwipeRefreshLayout.OnRefreshListener {

    public final static String MOVIE_ID = "MOVIE_ID";
    private int movieId;
    private TextView textMovieTitle, textMovieData, textMovieRating, textMovieDescription;
    private ConstraintLayout constraintMovieContent;
    private ImageView imageMovieBanner, imageMoviePoster;
    private ProgressBar progressMovieRating;
    private SwipeRefreshLayout swipeMovieReload;
    private MovieDetailViewModel movieDetailViewModel;

    @NonNull
    private static MovieDetailViewModel obtainViewModel(AppCompatActivity activity) {
        ViewModelFactory factory = ViewModelFactory.getInstance(activity.getApplication());
        return ViewModelProviders.of(activity, factory).get(MovieDetailViewModel.class);
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_movie_detail);
        setView();

        if (getSupportActionBar() != null) {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        }


        movieId = getIntent().getIntExtra(MOVIE_ID, 0);
        movieDetailViewModel = obtainViewModel(this);
        movieDetailViewModel.getMovieDetail(movieId);

        onLoading();
        onError();
        onMovieReceived();
    }

    private void onMovieReceived() {
        movieDetailViewModel.getMovie().observe(this, movieEntity -> {
            if (movieEntity != null) {
                constraintMovieContent.setVisibility(View.VISIBLE);
                setMovieDetail(movieEntity);
            } else {
                constraintMovieContent.setVisibility(View.GONE);
            }
        });
    }

    private void onError() {
        movieDetailViewModel.getErroMessage().observe(this, errorMessage -> {
            if (errorMessage != null) {
                constraintMovieContent.setVisibility(View.VISIBLE);
                new DialogHelper(this)
                        .setMessage(errorMessage)
                        .setPrimaryButton(R.string.btn_title_ok, (dialogInterface, i) -> dialogInterface.dismiss())
                        .create().show();
            }
        });
    }

    private void onLoading() {
        movieDetailViewModel.getIsLoading().observe(this, isLoading -> {
            swipeMovieReload.setRefreshing(isLoading);
            constraintMovieContent.setVisibility(isLoading ? View.GONE : View.VISIBLE);
        });
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        finish();
        return super.onOptionsItemSelected(item);
    }

    private void setView() {
        textMovieTitle = findViewById(R.id.text_movie_detail_title);
        textMovieData = findViewById(R.id.text_movie_detail_data);
        textMovieDescription = findViewById(R.id.text_movie_detail_description);
        textMovieRating = findViewById(R.id.text_movie_detail_rating);
        imageMovieBanner = findViewById(R.id.image_movie_detail_banner);
        imageMoviePoster = findViewById(R.id.image_movie_detail_poster);
        progressMovieRating = findViewById(R.id.progress_movie_detail_rating);
        constraintMovieContent = findViewById(R.id.constraint_movie_detail_content);
        swipeMovieReload = findViewById(R.id.swipe_movie_detail_reload);

        swipeMovieReload.setColorSchemeColors(getResources().getColor(R.color.colorPrimary));
        swipeMovieReload.setOnRefreshListener(this);
    }


    private void setMovieDetail(MovieEntity movieDetail) {
        textMovieTitle.setText(movieDetail.getTitle());
        textMovieRating.setText(String.valueOf(movieDetail.getVoteAverage()));
        textMovieDescription.setText(movieDetail.getOverview());
        textMovieData.setText(getString(R.string.text_movie_data, movieDetail.getLang(), movieDetail.getReleaseDate()));
        progressMovieRating.setProgress((int) (movieDetail.getVoteAverage() * 10));
        setMovieImage(imageMoviePoster, movieDetail.getPoster());
        setMovieImage(imageMovieBanner, movieDetail.getPoster());
    }

    private void setMovieImage(ImageView imageView, String poster) {
        Glide.with(this)
                .load(BuildConfig.BASE_URL_POSTER + poster)
                .apply(new RequestOptions().placeholder(R.drawable.img_placeholder))
                .into(imageView);
    }

    @Override
    public void onRefresh() {
        movieDetailViewModel.getMovieDetail(movieId);
    }
}
