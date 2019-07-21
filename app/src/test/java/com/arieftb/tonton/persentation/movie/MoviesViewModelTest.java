/*
 * Developed by arieftb on 6/28/19 6:23 AM.
 * Last Modified 6/28/19 6:23 AM.
 * Copyright (c) 2019. All rights reserved.
 * www.arieftb.com
 */

package com.arieftb.tonton.persentation.movie;

import androidx.arch.core.executor.testing.InstantTaskExecutorRule;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.Observer;

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

public class MoviesViewModelTest {

    @Rule
    public InstantTaskExecutorRule instantTaskExecutorRule = new InstantTaskExecutorRule();

    private MoviesViewModel viewModel;
    private final MovieRepository movieRepository = mock(MovieRepository.class);


    @Before
    public void setViewModel() {
        viewModel = new MoviesViewModel(movieRepository);
    }

    @Test
    public void getMovies() {
        MutableLiveData<List<MovieEntity>> moviesData = new MutableLiveData<>();
        moviesData.postValue(DataDummy.generateMovie());

        when(movieRepository.onMoviesReceived()).thenReturn((moviesData));

        Observer<List<MovieEntity>> movieObserver = Mockito.mock(Observer.class);
        viewModel.getMovies().observeForever(movieObserver);

        verify(movieRepository).getMovies();
        verify(movieRepository).onMoviesReceived();

        assertNotNull(viewModel.getMovies().getValue());
        assertEquals(Objects.requireNonNull(viewModel.getMovies().getValue()).get(0), Objects.requireNonNull(moviesData.getValue()).get(0));
    }
}