package com.arieftb.tonton.repo.callback;

import com.arieftb.tonton.model.entity.TvShowEntity;

public interface TvShowCallback {
    void onTvShowReceived(TvShowEntity tvShowItem);
}
