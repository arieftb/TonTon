/*
 * Developed by arieftb on 6/26/19 11:09 AM.
 * Last Modified 6/26/19 11:09 AM.
 * Copyright (c) 2019. All rights reserved.
 * www.arieftb.com
 */

package com.arieftb.tonton.pertentation.tvshowdetail;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProviders;

import android.os.Bundle;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.arieftb.tonton.R;
import com.arieftb.tonton.model.TvShow;
import com.bumptech.glide.Glide;

public class TvShowDetailActivity extends AppCompatActivity {
    public final static String TV_SHOW_ID = "TV_SHOW_ID";

    private TextView textTvShowTitle, textTvShowData, textTvShowRating, textTvShowDescription;
    private ImageView imageTvShowBanner, imageTvShowPoster;
    private ProgressBar progressTvShowRating;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tv_show_detail);
        setView();
        
        if (getSupportActionBar() != null) {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        }

        int tvShowId = getIntent().getIntExtra(TV_SHOW_ID, 0);
        
        TvShowDetailViewModel tvShowDetailViewModel = ViewModelProviders.of(this).get(TvShowDetailViewModel.class);
        tvShowDetailViewModel.setTvShowId(tvShowId);
        
        setTvShowDetail(tvShowDetailViewModel.getTvShowDetail());
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        finish();
        return super.onOptionsItemSelected(item);
    }


    private void setView() {
        textTvShowTitle = findViewById(R.id.text_tv_show_detail_title);
        textTvShowData = findViewById(R.id.text_tv_show_detail_data);
        textTvShowDescription = findViewById(R.id.text_tv_show_detail_description);
        textTvShowRating = findViewById(R.id.text_tv_show_detail_rating);
        imageTvShowBanner = findViewById(R.id.image_tv_show_detail_banner);
        imageTvShowPoster = findViewById(R.id.image_tv_show_detail_poster);
        progressTvShowRating = findViewById(R.id.progress_tv_show_detail_rating);
    }


    private void setTvShowDetail(TvShow tvShowDetail) {
        textTvShowTitle.setText(tvShowDetail.getTitle());
        textTvShowRating.setText(String.valueOf(tvShowDetail.getRating()));
        textTvShowDescription.setText(tvShowDetail.getDescription());
        textTvShowData.setText(getString(R.string.text_movie_data, tvShowDetail.getGenre(), tvShowDetail.getReleaseDate()));
        progressTvShowRating.setProgress((int) (tvShowDetail.getRating() * 10));
        setTvShowImage(imageTvShowPoster, tvShowDetail.getPoster());
        setTvShowImage(imageTvShowBanner, tvShowDetail.getPoster());
    }

    private void setTvShowImage(ImageView imageView, int poster) {
        Glide.with(this)
                .load(poster)
                .into(imageView);
    }
}
