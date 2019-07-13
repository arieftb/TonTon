package com.arieftb.tonton.data;

import androidx.lifecycle.LiveData;

import com.arieftb.tonton.model.entity.TvShowEntity;

import java.util.List;

public interface TvShowDataSource {
    void getTvShows();
    LiveData<List<TvShowEntity>> onTvShowsReceived();
}
