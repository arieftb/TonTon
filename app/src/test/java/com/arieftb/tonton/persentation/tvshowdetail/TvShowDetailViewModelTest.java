/*
 * Developed by arieftb on 6/28/19 6:37 AM.
 * Last Modified 6/28/19 6:37 AM.
 * Copyright (c) 2019. All rights reserved.
 * www.arieftb.com
 */

package com.arieftb.tonton.persentation.tvshowdetail;

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

import java.util.Objects;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

public class TvShowDetailViewModelTest {
    @Rule
    public InstantTaskExecutorRule instantTaskExecutorRule = new InstantTaskExecutorRule();

    private TvShowDetailViewModel viewModel;
    private final TvShowRepository tvShowRepository = mock(TvShowRepository.class);

    @Before
    public void setViewModel() {
        viewModel = new TvShowDetailViewModel(tvShowRepository);
        viewModel.getTvShowDetail(456);
    }

    @Test
    public void getTvShowDetail() {
        MutableLiveData<TvShowEntity> tvShowData = new MutableLiveData<>();
        tvShowData.postValue(DataDummy.getTvShow(456));

        when(tvShowRepository.onTvShowReceived()).thenReturn((tvShowData));

        Observer<TvShowEntity> movieObserver = Mockito.mock(Observer.class);
        viewModel.getTvShow().observeForever(movieObserver);

        verify(tvShowRepository).getTvShow(456);
        verify(tvShowRepository).onTvShowReceived();

        assertNotNull(viewModel.getTvShow().getValue());
        assertEquals(Objects.requireNonNull(viewModel.getTvShow().getValue()).getTitle(), Objects.requireNonNull(tvShowData.getValue()).getTitle());
    }
}