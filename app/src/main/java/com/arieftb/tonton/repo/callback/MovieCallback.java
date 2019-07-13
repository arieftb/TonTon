package com.arieftb.tonton.repo.callback;

import com.arieftb.tonton.model.entity.MovieEntity;
import com.arieftb.tonton.model.entity.TvShowEntity;

import java.util.List;

public interface MovieCallback {
    void onMovieReceived(MovieEntity movieEntity);
}
