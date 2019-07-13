/*
 * Developed by arieftb on 6/26/19 12:00 PM.
 * Last Modified 6/26/19 12:00 PM.
 * Copyright (c) 2019. All rights reserved.
 * www.arieftb.com
 */

package com.arieftb.tonton.persentation.moviedetail;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;

import com.arieftb.tonton.model.entity.MovieEntity;
import com.arieftb.tonton.repo.movie.MovieRepository;

public class MovieDetailViewModel extends ViewModel {
    private MovieRepository movieRepository;

    public MovieDetailViewModel(MovieRepository movieRepository) {
        this.movieRepository = movieRepository;
    }

    void getMovieDetail(int movieId) {
        movieRepository.getMovieDetail(movieId);
    }

    LiveData<MovieEntity> getMovie() {
        return movieRepository.onMovieReceived();
    }

    LiveData<Boolean> getIsLoading() {
        return movieRepository.isLoading();
    }

    LiveData<String> getErrorMessage() {
        return movieRepository.onError();
    }
}
