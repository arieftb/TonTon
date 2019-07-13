/*
 * Developed by arieftb on 6/26/19 12:00 PM.
 * Last Modified 6/26/19 12:00 PM.
 * Copyright (c) 2019. All rights reserved.
 * www.arieftb.com
 */

package com.arieftb.tonton.pertentation.moviedetail;

import androidx.lifecycle.ViewModel;

import com.arieftb.tonton.model.Movie;
import com.arieftb.tonton.utils.Dummy;

public class MovieDetailViewModel extends ViewModel {
    private int movieId;
    private Movie movieDetail;

    public void setMovieId(int movieId) {
        this.movieId = movieId;
    }

    public Movie getMovieDetail() {
        for (int i = 0; i < Dummy.getDummyMovies().size(); i++) {
            Movie movie = Dummy.getDummyMovies().get(i);
            if (movie.getId() == movieId) {
                movieDetail = movie;
            }
        }

        return movieDetail;
    }
}
