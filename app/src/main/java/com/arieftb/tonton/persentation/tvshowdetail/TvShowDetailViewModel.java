/*
 * Developed by arieftb on 6/26/19 1:22 PM.
 * Last Modified 6/26/19 1:22 PM.
 * Copyright (c) 2019. All rights reserved.
 * www.arieftb.com
 */

package com.arieftb.tonton.persentation.tvshowdetail;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;

import com.arieftb.tonton.model.TvShow;
import com.arieftb.tonton.model.entity.TvShowEntity;
import com.arieftb.tonton.repo.tvshow.TvShowRepository;
import com.arieftb.tonton.utils.Dummy;

public class TvShowDetailViewModel extends ViewModel {
    private int tvShowId;
    private TvShow tvShowDetail;
    private TvShowRepository tvShowRepository;

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

    public void setTvShowId(int tvShowId) {
        this.tvShowId = tvShowId;
    }

    public TvShow getTvShowDetail() {
        for (int i = 0; i < Dummy.getDummyTvShows().size(); i++) {
            TvShow tvShow = Dummy.getDummyTvShows().get(i);
            if (tvShow.getId() == tvShowId) {
                tvShowDetail = tvShow;
            }
        }

        return tvShowDetail;
    }
}
