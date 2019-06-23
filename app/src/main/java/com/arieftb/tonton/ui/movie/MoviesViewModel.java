/*
 * Developed by arieftb on 6/23/19 10:12 PM.
 * Last Modified 6/23/19 10:12 PM.
 * Copyright (c) 2019. All rights reserved.
 * www.arieftb.com
 */

package com.arieftb.tonton.ui.movie;

import androidx.lifecycle.ViewModel;

import com.arieftb.tonton.model.Movie;
import com.arieftb.tonton.utils.Dummy;

import java.util.List;

public class MoviesViewModel extends ViewModel {
    public List<Movie> getMovies() {
        return Dummy.getDummyMovies();
    }
}
