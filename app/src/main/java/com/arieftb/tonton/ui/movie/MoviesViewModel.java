/*
 * Developed by arieftb on 6/23/19 10:12 PM.
 * Last Modified 6/23/19 10:12 PM.
 * Copyright (c) 2019. All rights reserved.
 * www.arieftb.com
 */

package com.arieftb.tonton.ui.movie;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.arieftb.tonton.model.response.movies.Movie;
import com.arieftb.tonton.repo.movie.MovieRepository;

import java.util.List;

public class MoviesViewModel extends ViewModel {
    private MovieRepository movieRepository;
    private LiveData<List<Movie>> moviesData = new MutableLiveData<>();
    private MutableLiveData<String> message = new MutableLiveData<>();

    public MoviesViewModel(MovieRepository movieRepository) {
        this.movieRepository = movieRepository;
        getMoviesData();
    }

    private void getMoviesData() {
        moviesData = movieRepository.getMovies();
    }

    LiveData<List<Movie>> getMovies() {
        return moviesData;
    }

    LiveData<Boolean> getIsLoading() {
        return movieRepository.isLoading();
    }

    LiveData<String> getMessageError() { return movieRepository.onError(); }
}
