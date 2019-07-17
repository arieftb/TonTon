/*
 * Developed by arieftb on 6/28/19 6:28 AM.
 * Last Modified 6/28/19 6:28 AM.
 * Copyright (c) 2019. All rights reserved.
 * www.arieftb.com
 */

package com.arieftb.tonton.persentation.tvshow;

import androidx.arch.core.executor.testing.InstantTaskExecutorRule;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.Observer;

import com.arieftb.tonton.model.entity.TvShowEntity;
import com.arieftb.tonton.repo.tvshow.TvShowRepository;
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

public class TvShowsViewModelTest {

    @Rule
    public InstantTaskExecutorRule instantTaskExecutorRule = new InstantTaskExecutorRule();

    private TvShowsViewModel tvShowsViewModel;
    private TvShowRepository tvShowRepository = mock(TvShowRepository.class);

    @Before
    public void setTvShowsViewModel() {
        tvShowsViewModel = new TvShowsViewModel(tvShowRepository);
    }

    @Test
    public void getTvShows() {
        MutableLiveData<List<TvShowEntity>> tvShowsData = new MutableLiveData<>();
        tvShowsData.postValue(DataDummy.generateTvShows());

        when(tvShowRepository.onTvShowsReceived()).thenReturn((tvShowsData));

        Observer<List<TvShowEntity>> movieObserver = Mockito.mock(Observer.class);
        tvShowsViewModel.getTvShows().observeForever(movieObserver);

        verify(tvShowRepository).getTvShows();
        verify(tvShowRepository).onTvShowsReceived();

        assertEquals(Objects.requireNonNull(tvShowsViewModel.getTvShows().getValue()).get(0), Objects.requireNonNull(tvShowsData.getValue()).get(0));
    }
}