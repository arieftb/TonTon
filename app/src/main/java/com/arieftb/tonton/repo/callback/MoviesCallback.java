/*
 * Developed by arieftb on 7/9/19 3:09 PM.
 * Last Modified 7/9/19 3:09 PM.
 * Copyright (c) 2019. All rights reserved.
 * www.arieftb.com
 */

package com.arieftb.tonton.repo.callback;

import com.arieftb.tonton.model.response.movies.MovieItem;

import java.util.List;

public interface MoviesCallback {
    void onMoviesReceived(List<MovieItem> movies);
}
