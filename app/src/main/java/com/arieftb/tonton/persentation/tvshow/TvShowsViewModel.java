/*
 * Developed by arieftb on 6/25/19 3:44 PM.
 * Last Modified 6/25/19 3:44 PM.
 * Copyright (c) 2019. All rights reserved.
 * www.arieftb.com
 */

package com.arieftb.tonton.persentation.tvshow;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;

import com.arieftb.tonton.model.entity.TvShowEntity;
import com.arieftb.tonton.repo.tvshow.TvShowRepository;

import java.util.List;

public class TvShowsViewModel extends ViewModel {
    private final TvShowRepository tvShowRepository;

    public TvShowsViewModel(TvShowRepository tvShowRepository) {
        this.tvShowRepository = tvShowRepository;
        loadData();
    }

    void loadData() {
        tvShowRepository.getTvShows();
    }

    LiveData<List<TvShowEntity>> getTvShows() {
        return tvShowRepository.onTvShowsReceived();
    }

    LiveData<Boolean> getIsLoading() {
        return tvShowRepository.isLoading();
    }

    LiveData<String> getMessageError() {
        return tvShowRepository.onError();
    }
}
