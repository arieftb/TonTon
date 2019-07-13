/*
 * Developed by arieftb on 6/28/19 6:23 AM.
 * Last Modified 6/28/19 6:23 AM.
 * Copyright (c) 2019. All rights reserved.
 * www.arieftb.com
 */

package com.arieftb.tonton.pertentation.movie;

import com.arieftb.tonton.model.Movie;

import org.junit.Before;
import org.junit.Test;

import java.util.List;

import static org.junit.Assert.*;

public class MoviesViewModelTest {

    private MoviesViewModel viewModel;

    @Before
    public void setViewModel() {
        viewModel = new MoviesViewModel();
    }

    @Test
    public void getMovies() {
        List<Movie> movies = viewModel.getMovies();
        assertNotNull(movies);
        assertEquals(18, movies.size());
    }
}