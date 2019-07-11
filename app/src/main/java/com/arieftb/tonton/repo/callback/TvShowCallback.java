package com.arieftb.tonton.repo.callback;

import com.arieftb.tonton.model.response.tvshow.TvShowItem;

import java.util.List;

public interface TvShowCallback {
    void onTvShowReceived(List<TvShowItem> tvShowItems);
}
