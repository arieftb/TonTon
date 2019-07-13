package com.arieftb.tonton.repo.callback;

import com.arieftb.tonton.model.entity.TvShowEntity;

import java.util.List;

public interface TvShowCallback {
    void onTvShowReceived(List<TvShowEntity> tvShowItems);
}
