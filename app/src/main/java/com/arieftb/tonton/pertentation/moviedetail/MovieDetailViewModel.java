/*
 * Developed by arieftb on 6/26/19 12:00 PM.
 * Last Modified 6/26/19 12:00 PM.
 * Copyright (c) 2019. All rights reserved.
 * www.arieftb.com
 */

package com.arieftb.tonton.pertentation.moviedetail;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;

import com.arieftb.tonton.model.entity.MovieEntity;
import com.arieftb.tonton.repo.movie.MovieRepository;

public class MovieDetailViewModel extends ViewModel {
    private int movieId;
    private MovieRepository movieRepository;

    public MovieDetailViewModel(MovieRepository movieRepository) {
        this.movieRepository = movieRepository;
    }

    void setMovieId(int movieId) {
        this.movieId = movieId;
        getMovieDetail();
    }

    void getMovieDetail() {
        movieRepository.getMovieDetail(movieId);
    }

    LiveData<MovieEntity> getMovie() {
        return movieRepository.onMovieReceived();
    }

    LiveData<Boolean> getIsLoading() {
        return movieRepository.isLoading();
    }

    LiveData<String> getErroMessage() {
        return movieRepository.onError();
    }
}
