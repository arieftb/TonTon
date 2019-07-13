/*
 * Developed by arieftb on 6/28/19 6:33 AM.
 * Last Modified 6/28/19 6:33 AM.
 * Copyright (c) 2019. All rights reserved.
 * www.arieftb.com
 */

package com.arieftb.tonton.pertentation.moviedetail;

import com.arieftb.tonton.R;
import com.arieftb.tonton.model.Movie;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class MovieItemDetailViewModelTest {

    private MovieDetailViewModel viewModel;
    private Movie movieTest;

    @Before
    public void setViewModel() {
//        viewModel = new MovieDetailViewModel();
        movieTest = new Movie(1, "A Start Is Born", "Drama", "Seasoned musician Jackson Maine discovers — and falls in love with — struggling artist Ally. She has just about given up on her dream to make it big as a singer — until Jack coaxes her into the spotlight. But even as Ally's career takes off, the personal side of their relationship is breaking down, as Jack fights an ongoing battle with his own internal demons.", "October 5, 2018", 7.5, R.drawable.poster_a_start_is_born);
    }

    @Test
    public void getMovieDetail() {
        viewModel.setMovieId(movieTest.getId());
//        Movie movie = viewModel.getMovieDetail();
//
//        assertNotNull(movie);
//        assertEquals(movieTest.getId(), movie.getId());
//        assertEquals(movieTest.getTitle(), movie.getTitle());
//        assertEquals(movieTest.getGenre(), movie.getGenre());
//        assertEquals(movieTest.getDescription(), movie.getDescription());
//        assertEquals(movieTest.getReleaseDate(), movie.getReleaseDate());
//        assertEquals(movieTest.getRating(), movie.getRating());
//        assertEquals(movieTest.getPoster(), movie.getPoster());
    }
}