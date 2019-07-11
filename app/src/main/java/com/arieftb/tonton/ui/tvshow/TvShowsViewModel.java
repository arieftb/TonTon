/*
 * Developed by arieftb on 6/25/19 3:44 PM.
 * Last Modified 6/25/19 3:44 PM.
 * Copyright (c) 2019. All rights reserved.
 * www.arieftb.com
 */

package com.arieftb.tonton.ui.tvshow;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;

import com.arieftb.tonton.model.TvShow;
import com.arieftb.tonton.model.response.tvshow.TvShowItem;
import com.arieftb.tonton.repo.tvshow.TvShowRepository;
import com.arieftb.tonton.utils.Dummy;

import java.util.List;

public class TvShowsViewModel extends ViewModel {
    private final TvShowRepository tvShowRepository;

    public TvShowsViewModel(TvShowRepository tvShowRepository) {
        this.tvShowRepository = tvShowRepository;
        loadData();
    }

//    public List<TvShow> getTvShows() {
//        return Dummy.getDummyTvShows();
//    }

    private void loadData() {
        tvShowRepository.onLoadData();
    }

    LiveData<List<TvShowItem>> getTvShows() {
        return tvShowRepository.onTvShowsReceived();
    }

    public LiveData<Boolean> getIsLoading() {
        return tvShowRepository.isLoading();
    }

    LiveData<String> getMessageError() {
        return tvShowRepository.onError();
    }
}
