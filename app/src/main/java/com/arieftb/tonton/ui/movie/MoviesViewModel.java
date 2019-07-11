/*
 * Developed by arieftb on 6/23/19 10:12 PM.
 * Last Modified 6/23/19 10:12 PM.
 * Copyright (c) 2019. All rights reserved.
 * www.arieftb.com
 */

package com.arieftb.tonton.ui.movie;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;

import com.arieftb.tonton.model.response.movies.MovieItem;
import com.arieftb.tonton.repo.movie.MovieRepository;

import java.util.List;

public class MoviesViewModel extends ViewModel {
    private MovieRepository movieRepository;

    public MoviesViewModel(MovieRepository movieRepository) {
        this.movieRepository = movieRepository;
        getMoviesData();
    }

    void getMoviesData() { movieRepository.onLoadData(); }

    LiveData<List<MovieItem>> getMovies() { return movieRepository.onMoviesReceived(); }

    LiveData<Boolean> getIsLoading() {
        return movieRepository.isLoading();
    }

    LiveData<String> getMessageError() { return movieRepository.onError(); }
}
