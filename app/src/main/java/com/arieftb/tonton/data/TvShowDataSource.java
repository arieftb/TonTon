package com.arieftb.tonton.data;

import androidx.lifecycle.LiveData;
import com.arieftb.tonton.model.response.tvshow.TvShowItem;

import java.util.List;

public interface TvShowDataSource {
    LiveData<List<TvShowItem>> onTvShowsReceived();
}
