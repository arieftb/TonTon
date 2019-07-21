/*
 * Developed by arieftb on 6/26/19 1:22 PM.
 * Last Modified 6/26/19 1:22 PM.
 * Copyright (c) 2019. All rights reserved.
 * www.arieftb.com
 */

package com.arieftb.tonton.persentation.tvshowdetail;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;

import com.arieftb.tonton.model.entity.TvShowEntity;
import com.arieftb.tonton.repo.tvshow.TvShowRepository;

public class TvShowDetailViewModel extends ViewModel {
    private final TvShowRepository tvShowRepository;

    public TvShowDetailViewModel(TvShowRepository tvShowRepository) {
        this.tvShowRepository = tvShowRepository;
    }

    void getTvShowDetail(int id) {
        tvShowRepository.getTvShow(id);
    }

    LiveData<TvShowEntity> getTvShow() {
        return tvShowRepository.onTvShowReceived();
    }

    LiveData<Boolean> getIsLoading() {
        return tvShowRepository.isLoading();
    }

    LiveData<String> getErrorMessage() {
        return tvShowRepository.onError();
    }
}
