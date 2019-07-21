/*
 * Developed by arieftb on 7/9/19 3:42 PM.
 * Last Modified 7/9/19 3:42 PM.
 * Copyright (c) 2019. All rights reserved.
 * www.arieftb.com
 */

package com.arieftb.tonton.utils;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;

import com.arieftb.tonton.di.Injection;
import com.arieftb.tonton.persentation.moviedetail.MovieDetailViewModel;
import com.arieftb.tonton.persentation.tvshowdetail.TvShowDetailViewModel;
import com.arieftb.tonton.repo.movie.MovieRepository;
import com.arieftb.tonton.repo.tvshow.TvShowRepository;
import com.arieftb.tonton.persentation.movie.MoviesViewModel;
import com.arieftb.tonton.persentation.tvshow.TvShowsViewModel;

public class ViewModelFactory extends ViewModelProvider.NewInstanceFactory {
    private static volatile ViewModelFactory INSTANCE;

    private final MovieRepository movieRepository;
    private final TvShowRepository tvShowRepository;

    private ViewModelFactory(MovieRepository movieRepository, TvShowRepository tvShowRepository) {
        this.movieRepository = movieRepository;
        this.tvShowRepository = tvShowRepository;
    }

    public static ViewModelFactory getInstance(Application application) {
        if (INSTANCE == null) {
            synchronized (ViewModelFactory.class) {
                if (INSTANCE == null) {
                    INSTANCE = new ViewModelFactory(Injection.provideMovieRepo(application), Injection.provideTvShowRepo(application));
                }
            }
        }

        return INSTANCE;
    }

    @NonNull
    @Override
    public <T extends ViewModel> T create(@NonNull Class<T> modelClass) {
        if (modelClass.isAssignableFrom(MoviesViewModel.class)) {
            return (T) new MoviesViewModel(movieRepository);
        } else if (modelClass.isAssignableFrom(TvShowsViewModel.class)) {
            return (T) new TvShowsViewModel(tvShowRepository);
        } else if (modelClass.isAssignableFrom(MovieDetailViewModel.class)) {
            return (T) new MovieDetailViewModel(movieRepository);
        } else if (modelClass.isAssignableFrom(TvShowDetailViewModel.class)) {
            return (T) new TvShowDetailViewModel(tvShowRepository);
        }

        throw new IllegalArgumentException(modelClass.getName());
    }
}
