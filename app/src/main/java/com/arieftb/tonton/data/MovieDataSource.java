/*
 * Developed by arieftb on 7/9/19 1:58 PM.
 * Last Modified 7/9/19 1:58 PM.
 * Copyright (c) 2019. All rights reserved.
 * www.arieftb.com
 */

package com.arieftb.tonton.data;

import androidx.lifecycle.LiveData;

import com.arieftb.tonton.model.response.movies.Movie;

import java.util.List;

public interface MovieDataSource {
    void getMovies();
    LiveData<List<Movie>> onMoviesReceived();
}
