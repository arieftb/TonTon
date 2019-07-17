/*
 * Developed by arieftb on 6/28/19 6:33 AM.
 * Last Modified 6/28/19 6:33 AM.
 * Copyright (c) 2019. All rights reserved.
 * www.arieftb.com
 */

package com.arieftb.tonton.persentation.moviedetail;

import androidx.arch.core.executor.testing.InstantTaskExecutorRule;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.Observer;

import com.arieftb.tonton.R;
import com.arieftb.tonton.model.Movie;
import com.arieftb.tonton.model.entity.MovieEntity;
import com.arieftb.tonton.repo.movie.MovieRepository;
import com.arieftb.tonton.utils.DataDummy;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.mockito.Mockito;

import java.util.List;
import java.util.Objects;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

public class MovieItemDetailViewModelTest {

    @Rule
    public InstantTaskExecutorRule instantTaskExecutorRule = new InstantTaskExecutorRule();

    private MovieDetailViewModel viewModel;
    private MovieRepository movieRepository = mock(MovieRepository.class);

    @Before
    public void setViewModel() {
        viewModel = new MovieDetailViewModel(movieRepository);
        viewModel.getMovieDetail(429617);
    }

    @Test
    public void getMovie() {
        MutableLiveData<MovieEntity> movieData = new MutableLiveData<>();
        movieData.postValue(DataDummy.getMovie(429617));

        when(movieRepository.onMovieReceived()).thenReturn((movieData));

        Observer<MovieEntity> movieObserver = Mockito.mock(Observer.class);
        viewModel.getMovie().observeForever(movieObserver);

        verify(movieRepository).getMovieDetail(429617);
        verify(movieRepository).onMovieReceived();

        assertNotNull(viewModel.getMovie().getValue());
        assertEquals(Objects.requireNonNull(viewModel.getMovie().getValue()).getTitle(), movieData.getValue().getTitle());
    }
}