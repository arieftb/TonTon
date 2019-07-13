/*
 * Developed by arieftb on 6/26/19 11:09 AM.
 * Last Modified 6/26/19 11:09 AM.
 * Copyright (c) 2019. All rights reserved.
 * www.arieftb.com
 */

package com.arieftb.tonton.persentation.tvshowdetail;

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
import com.arieftb.tonton.model.entity.TvShowEntity;
import com.arieftb.tonton.utils.DialogHelper;
import com.arieftb.tonton.utils.ViewModelFactory;
import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;

public class TvShowDetailActivity extends AppCompatActivity implements SwipeRefreshLayout.OnRefreshListener {
    public final static String TV_SHOW_ID = "TV_SHOW_ID";

    private int tvShowId;
    private TextView textTvShowTitle, textTvShowData, textTvShowRating, textTvShowDescription;
    private ImageView imageTvShowBanner, imageTvShowPoster;
    private ProgressBar progressTvShowRating;
    private SwipeRefreshLayout swipeTvShowReload;
    private ConstraintLayout constraintTvShowContent;
    private TvShowDetailViewModel tvShowDetailViewModel;

    @NonNull
    private static TvShowDetailViewModel obtainViewModel(AppCompatActivity activity) {
        ViewModelFactory factory = ViewModelFactory.getInstance(activity.getApplication());
        return ViewModelProviders.of(activity, factory).get(TvShowDetailViewModel.class);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tv_show_detail);
        setView();

        if (getSupportActionBar() != null) {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        }

        tvShowId = getIntent().getIntExtra(TV_SHOW_ID, 0);
        tvShowDetailViewModel = obtainViewModel(this);
        tvShowDetailViewModel.getTvShowDetail(tvShowId);

        onLoading();
        onError();
        onTvDetailReceived();
    }

    private void onTvDetailReceived() {
        tvShowDetailViewModel.getTvShow().observe(this, tvShowEntity -> {
            if (tvShowEntity != null) {
                setTvShowDetail(tvShowEntity);
            } else {
                constraintTvShowContent.setVisibility(View.GONE);
            }
        });
    }

    private void onError() {
        tvShowDetailViewModel.getErrorMessage().observe(this, errorMessage -> {
                    if (errorMessage != null) {
                        constraintTvShowContent.setVisibility(View.VISIBLE);
                        new DialogHelper(this)
                                .setMessage(errorMessage)
                                .setPrimaryButton(R.string.btn_title_ok, (dialogInterface, i) -> dialogInterface.dismiss())
                                .create().show();
                    }
                }
        );
    }

    private void onLoading() {
        tvShowDetailViewModel.getIsLoading().observe(this, isLoading -> {
            swipeTvShowReload.setRefreshing(isLoading);
            constraintTvShowContent.setVisibility(isLoading ? View.GONE : View.VISIBLE);
        });
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
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
        swipeTvShowReload = findViewById(R.id.swipe_tv_show_detail_reload);
        constraintTvShowContent = findViewById(R.id.constrain_tv_show_detail_content);

        swipeTvShowReload.setOnRefreshListener(this);
        swipeTvShowReload.setColorSchemeColors(getResources().getColor(R.color.colorPrimary));
    }


    private void setTvShowDetail(TvShowEntity tvShowDetail) {
        textTvShowTitle.setText(tvShowDetail.getTitle());
        textTvShowRating.setText(String.valueOf(tvShowDetail.getVoteAverage()));
        textTvShowDescription.setText(tvShowDetail.getOverview());
        textTvShowData.setText(getString(R.string.text_movie_data, tvShowDetail.getLang(), tvShowDetail.getReleaseDate()));
        progressTvShowRating.setProgress((int) (tvShowDetail.getVoteAverage() * 10));
        setTvShowImage(imageTvShowPoster, tvShowDetail.getPoster());
        setTvShowImage(imageTvShowBanner, tvShowDetail.getPoster());
    }

    private void setTvShowImage(ImageView imageView, String poster) {
        Glide.with(this)
                .load(BuildConfig.BASE_URL_POSTER + poster)
                .apply(new RequestOptions().placeholder(R.drawable.img_placeholder))
                .into(imageView);
    }

    @Override
    public void onRefresh() {
        tvShowDetailViewModel.getTvShowDetail(tvShowId);
    }
}
